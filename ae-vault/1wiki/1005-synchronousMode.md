from [1003-entities](1003-entities.md)
# 1005 Synchronous Mode
- [1007-SYSTEMcontext+INVARIANTdescriptor](1007-SYSTEMcontext+INVARIANTdescriptor.md)

Synchronous mode provides interactions between entities via function calls. But this can only be done when the entities are part of the same federation. A federation is created/deleted on demand for a set of entities to run the provided script. When a federation is created, the entities are acquired, and always in the same order to prevent deadlock. Once execution of the script has stopped, the entities are released. 

The objective here is to execute the script atomically. No interactions are allowed, synchronous or asynchronous, with other entities that are not part of the same federation. Messages sent to the port of a federated entity remain unprocessed until the entity is released.

The restriction on synchronous interactions do not, how ever, apply to entities marked as [1007-SYSTEMcontext+INVARIANTdescriptor](1007-SYSTEMcontext+INVARIANTdescriptor.md).