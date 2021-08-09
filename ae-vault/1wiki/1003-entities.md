from [1000-wiki](1000-wiki.md)
# 1003 Entities
- [1004-asynchronousMode](1004-asynchronousMode.md)

Every entity has a name, a context, a state map and a dedicated port for receiving messages. The state map includes classifiers and descriptors, name/value pairs used for navigation and to control an entity's behavior. Entities would have two modes of operation: [1004-asynchronousMode](1004-asynchronousMode.md) and synchronous. 







The synchronous mode of operation requires a set of entities to become acquired by a federation. Once they are acquired, they can interact via function calls. To prevent deadlocks, all federations acquire entities in the same order. While in synchronous mode, entities do not respond to messages.