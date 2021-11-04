---
tags:
  - status/todo
---
from [8000-pendingActivities](8000-pendingActivities.md)
# 8029 Key Type
Adding key type to the form of a name: contextRoot+type_structureType-root^keyType$dataType. The key type is present (and required) only when the structure type is "map". Additionally, the key type must be the same as the  type in the name of the keys.

So for example, a map named descriptors_map-INSTANCE^descriptor would have could have a key with the name descriptor-INVARIANT$bool.