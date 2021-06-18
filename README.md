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
Operations on entities would be via messages sent using Clojure's async package. The first two concerns
here are (1) there may well be too many entities (10,000+) to dedicate a channel to each 
entity. And (2) operations on both entities and entity sub-trees should be atomic.

## Entities

At its heart, an entity is a data structure which has an unchanging name unique to the context it is embeded in. 
An entity can have one or more ordered sets of child entities. 

Operations are applied atomically to an entity and any number of its child entities, recursively.

An entity is implemented as a vector of 2 items: (1) an atom holding an asynchronous channel for requests (or nil) and (2) a volatile holding a persistent map with the sets of 
child entities, the entity's content (name/value pairs), metadata and other internal data.

The entity's atom will, at least initially, be nil except while a request is being processed. To keep things simple at first, every
request will be sent via a new channel. (When a request is completed, the channel will be closed. Any pending requests will then fail and the requestor 
can resend the request, creating a new channel as needed. By this means we can limit the number of open channels to just those which are processing a
request.)

When a request is to be processed by a subtree of entities, the child sends a acquire message to the appropriate child entities. 
The acquire message does nothing, which blocks any further requests.
On completion of the parent request, the request channel of the parent and all the acquired children are closed and the entity atoms are reset to nil.

The persistent map of an entity has an entry with a key of :NAME and a value which is the name of the entity, while the 
keys of the entities persistent map are encoded kewords derived from the names of the entities.
The persistent map of an entity also has an entry with a key of :PARENT and a value which is the names of the entity which 
has that entity as a child. 

## Contexts

A context is an entity with an entry in its persistent map with a key of :ENTITIES and whose value is a persistent map of entities. 

The persistent map of an entity has an entry with a key of CONTEXTS and a value which is a vector of the name of the contexts for which 
that entity is a member.

When a parent/child relation is broken, then the child is removed from the contexts for which it is a member, along with all the children
of that child entity recursively.

Note that the value of a entity is the same reguardless of the context by which it is accessed.

There is a further restriction that the child entity must be a member of the same context as its parent.

## Environments

An environment is an atom holding a persistent map. One of the entries in this persistent map has a key of :CONTEXTS and a value which is a persistent map
holding all contexts, the keys being the unique names of those contexts.

Functions which modify state require an environment parameter. Multiple environments may be present, allowing different versions of the environment to be
operated on at the same time.
