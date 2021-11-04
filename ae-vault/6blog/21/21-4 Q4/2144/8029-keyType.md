---
tags:
  - status/completed
---
from [8000-pendingActivities](8000-pendingActivities.md)
# 8029 Key Type
Adding key type to the form of a name: contextRoot+type_structureType-root^keyType$dataType. The key type is present (and required) only when the structure type is "map". Additionally, the key type must be the same as the  type in the name of the keys.

So for example, a map named descriptors_map-INSTANCE^descriptor would have could have a key with the name descriptor-INVARIANT$bool.

- The names of both scripts have been updated.
- Name parser now validates ^s and extracts the key type.

completed [2144.4 Thursday (week 44), 11-4-2021](2144.4%20Thursday%20(week%2044),%2011-4-2021.md)