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
When the child reads the acquire message, the sender receives control indicating that the child is acquired. 
And once all the child entities are acquired, the operation designated by the original request can proceed.
On completion of the top-level request, a new go block for processing requests is initiated for each of the acquired 
entities and the next request for the entity can be read.

Entries in the persistent map of an entity may have the following entry keys and values:
* :NAME, The name of the entity.
  (When a name is converted to a keyword, illegal characters like space are encoded.)
* :CHILDVECTORS, A persistent map whose values are vectors holding the keys of various children.
* :PARENTVECTORS, A persistent map whose values are vectors of the keys of various parents. 

## Contexts

A context is an entity with a map entry in its persistent map with a key of :ENTITIES and whose value is a persistent 
map of entities. 
(A persistent map rather than an ordered set is used here for scaling considerations.)

A context is not a child of another entity.

When a parent/child relation is broken and the child only has one parent, 
then the child is removed from the context of which it is a member, 
along with all the children of that child entity recursively, 
again, so long as those children only have a single parent.

## Environments

An environment is a persistent map. One of the map entries in this persistent map has a key of :CONTEXTS 
and a value which is a persistent map holding all contexts.

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
