# Asynchronous Entities

A knowledge management based framework for programming

* LANGUAGE: Clojure
* LICENSE: https://www.apache.org/licenses/LICENSE-2.0

Documentation is located in the ae-vault folder.
Start here: [0000-asynchronousEntities](https://github.com/laforge49/asynchronous-entities/blob/main/ae-vault/0000-asynchronousEntities.md)

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

When a request is to be processed by a subtree of entities, the child sends a :PUSH-REQUEST-PORT message to the 
applicable child entities. 
By changing the active request port on a child, only requests from the new port will be processed, leaving any 
requests on the public port pending.
And once all the child entities are acquired, the operation designated by the original request can proceed.
On completion of the top-level request, a :POP-REQUEST-PORT message is sent to each of the acquired 
entities and the acquired entities can resume processing from their public port.

Reversing the changes made to a child entity in a federated request is as easy as sending an :ABORT request.
The request must include a :saved-entity-map parameter set to the response from an eariler :PUSH-REQUEST-PORT message.

Entries in the persistent map of an entity may have the following entry keys and values:

* [:NAME, The name of the entity.]
  (When a name is converted to a keyword, illegal characters like space are encoded.)
* [:REQUEST-PORT-STACK, A stack of request ports used in federated requests.]
* [:OPERATIONS, A persistent map with requests for key and operation ids as values.]
* [:CHILDVECTORS, A persistent map whose values are vectors holding the keys of various children.]
* [:PARENTVECTORS, A persistent map whose values are vectors of the keys of various parents.]

Each vector of children or of parents implements a different relationship. And the key identifying
the relationship must be the same when navigating from parent to child and back to parent.
For example, a female child might have both father/daughter relaionship and a mother/daughter relationship but
a parent would generally have only father/daughter and father/son relationships or mother/daughter
and mother/son relationships.

As an entity can have multiple parents and children bound in different relationships, bush
structures are supported. But not cyclic graphs. So while this is a very rich 
system for relationships between entities, there are limits.

There are several built-in requests:
* :SNAPSHOT - returns the entity's persistent map.
* :PUSH-REQUEST-PORT - pushes the :new-request-port parameter onto the :REQUEST-PORT-STACK and returns the entity's persistent map.
* :POP-REQUEST-PORT - pops the :REQUEST-PORT-STACK.
* :ABORT - resets the entity's volatile map to the :saved-entity-map parameter.

Federated requests may cause deadlocks if not properly done. To avoid this, 
(1) never implement a cyclic graph of entities, 
(2) an entity may only include decendents in a federated request,
(3) the :PUSH-REQUEST-PORT request must be sent to those decendents in entity name order and
(4) a :POP-REQUEST-PORT or :ABORT request must be sent to all entities to which a :PUSH-REQUEST-PORT had been sent.

## Contexts

A context is a specialized entity in which other entities are embedded.

Entries in the persistent map of a context may have the following entry keys and values:

* [:NAME, The name of the context.]
* [:REQUEST-PORT-STACK, A stack of request ports used in federated requests.]
* [:OPERATIONS, A persistent map with requests for key and operation ids as values.]
* [:ENTITIES, A persistent map of entities.]
  (A persistent map rather than an ordered set is used here for scaling considerations.)

A context is not a child of another entity, nor is it a parent.

When an entity parent/child relation is broken and the child only has one parent, 
then the child is removed from the context of which it is a member, 
along with all the children of that child entity recursively, 
again, so long as those children only have a single parent.

## Environments

An environment is a persistent map. 
Entries in the persistent map of an environment may have the following entry keys and values:

* [:CONTEXTS-ATOM, An atom holding a persistent map of all contexts.]
* [:PARAMS, A persistent map of parameters being passed lower-level functions and sub-functions.]
