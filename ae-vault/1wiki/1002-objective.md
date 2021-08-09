from [1000-wiki](1000-wiki.md)
# 1002 Objective

For the past few years I've worked on a single-threaded program with all state collected  
in a tree built using persistent maps with something under a million name/value pairs.   
It feels wrong, especially if I want to support swing or multi-threading in general.  
  
What I'd like to do is to create a tree of entities, which in turn support name/value pairs.  
Entities would have two modes of operation: asynchronous and synchronous. The asynchronous mode is the default, with entities sending request and response messages to each other using [Clojure core.async](https://clojure.org/news/2013/06/28/clojure-clore-async-channels)

The synchronous mode of operation requires a set of entities to become acquired by a federation. Once they are acquired, they can interact via function calls. To prevent deadlocks, all federations acquire entities in the same order. While in synchronous mode, entities do not respond to messages.

Every entity has a name, a context, a state map and a dedicated port for receiving messages. The state map includes classifiers and descriptors, name/value pairs used for navigation and to control an entity's behavior.