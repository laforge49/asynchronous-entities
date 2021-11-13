---
tags:
  - status/todo
---
from [8000-pendingActivities](8000-pendingActivities.md)
# 8032 Named (Value) Type
Adding named (value) type to the form of a name: contextRoot+type_structureType-root^keyType&namedType$dataType.

A named type can only be present when no data type is present. Like data (value) type, a named type applies to the value when the name with the named type is used as a key. Again, like data type, the named type is used to validate the scalar, vector values or map values associated with the key containing the named type.

When both named type and data type are nil, the associated value is an unconstrained name.