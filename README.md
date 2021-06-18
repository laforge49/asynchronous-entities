# Asynchronous Entities

A universal persistent data structure.

* LANGUAGE: Clojure
* BLOG: https://asynchronous-entities.blogspot.com/
* LICENSE: https://www.apache.org/licenses/LICENSE-2.0

## Objective

For the past few years I've worked on a single-threaded program with all state collected
in a tree built using persistent maps with something under a million name/value pairs. 
It feels wrong, especially if I want to support swing or multi-threading in general.

What I'd like to do is to create a tree of entities, which in turn support name/value pairs.
Operations on entities would be via messages sent using Clojure's async package. The first concern
is that operations on multiple entities may need to be atomic. To avoid deadlocks, this will be done
by always acquiring the applicable entities in the same order before operating on them.

## Entities

At its heart, an entity is a data structure which has an unchanging name unique to the context it is embeded in. 
An entity can have one or more vectors of child entities. 

Operations are applied atomically to an entity and any number of its child entities, recursively.

An entity is implemented as a vector of 2 items: (1) an asynchronous channel for incoming requests and (2) a volatile 
holding a persistent map with the vectors of 
child entities, the entity's content (name/value pairs), metadata and other internal data.
On creation of an entity in memory, a go block is started which reads and processes requests from the entity's channel. 
A read loop is used to process successive
requests, exiting only when the channel is closed or when an acquire message is received.

When a request is to be processed by a subtree of entities, the child sends a acquire message to the appropriate child 
entities. 
The acquire message exits the go block without issuing a subsequent read, which blocks any further requests.
When the child reads the acquire message, the sender receives control indicating that the child is acquired. And once
all the child entities are acquired, the operation designated by the original request can proceed.
On completion of the top-level request, a new go block for processing requests is initiated for each of the acquired 
entities
and the next request for the entity can be read.

The persistent map of an entity has a map entry with a key of :NAME and a value which is the name of the entity, while 
the keys of the entities persistent map are encoded kewords derived from the names of the entities.
The persistent map of an entity has a map entry with a key of :CHILDREN and a value which is
a persistent map whose values are vectors holding the keys of selected children.
The persistent map of an entity also has a map entry with a key of :PARENTS and a value which is
a vector of vectors holding both the key of the entity which has that entity as a child and
the key of the vector in the parent which connects the parent to the child. 

## Contexts

A context is an entity with a map entry in its persistent map with a key of :ENTITIES and whose value is a persistent 
map of entities. 
A persistent map rather than an ordered set is used here for scaling considerations.

A context is not a child of another entity.

The persistent map of an entity has a map entry with a key of :CONTEXTS and a value which is a vector of the key of the 
contexts for which that entity is a member.

When a parent/child relation is broken, then the child is removed from the contexts for which it is a member, along with 
all the children of that child entity recursively.

Note that the value of a entity is the same reguardless of the context by which it is accessed.

There is a further restriction that the child entity must be a member of the same context as its parent.

## Environments

An environment is an atom holding a persistent map. One of the map entries in this persistent map has a key of :CONTEXTS 
and a value which is a persistent map holding all contexts, the keys being the names of those contexts.

Functions which modify state require an environment parameter. 
Multiple environments may be present, allowing different versions of the environment to be
operated on at the same time. 
It is best perhaps to think of an environment as a view into the state, as the same entity can be accessed via multiple
environments, with updates applied to an entity accessed by one context will be visible when accessed via another 
environment.

Another map entry in the persistent map of an environment has a key of :PARAMS and a value which is a persistent map of 
name/value pairs. 
This mechanism allows for the passing of paramaters to a function instead of passing positional parameters, 
with the added advantage of transparently passing parameters to subfunctions.
