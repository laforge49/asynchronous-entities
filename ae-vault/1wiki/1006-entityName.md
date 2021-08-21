from [1003-entities](1003-entities.md)
# 1006 Entity Name
Entity names are unique identifiers of entities. An entity name has two parts, the context and the base, separated by a '+'.

Except for the ROOT+SYSTEMcontext entity, every entity has a [8013-keywords](../6blog/21/21-3%20Summer/21-33/8013-keywords.md) entity which associates its entity name with the port used to send requests to that. And the base name of the context is the first part of the names of its entities. So, for example, the full name of the READ_ONLYdescriptor entity is SYSTEMcontext+READ_ONLYdescriptor -- the context name of the context entity is not included.

The base name of entities need not be unique, as the same base name can be used in different context entities. The exception here are the base names of context entities, which must all be unique. 