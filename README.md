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
Operations on entities would be via messages sent via Clojure's async package. The first two concerns
here are (1) there will may well be too many entities (10,000+) to dedicate a channel to each 
entity. And (2) operations on both entities and entity sub-trees should be atomic.

## Entities

At its heart, an entity is a data structure which has an unchanging name. An entity can have one
or more ordered sets of child entite. 

Operations are applied atomically to an entity and any number of its child entities, recursively.
