---
tags:
  - status/todo
---
from [8000-pendingActivities](8000-pendingActivities.md)
# 8027 Validate item name's root
Names generally take the form contextRoot+type-root, optionally followed by $dataType. And the type is optionally followed by _structureType. The type matches the type of thing of items held by its container:

| container | type |
|---------------------|------------|
| CLASSIFIERS | classifier |
| DESCRIPTORS | descriptor |
| RELATIONS | relation |
| SYS+descriptor_map-REQUESTID$operationid | requestid |

The entity SYS+meta-VALIDATOR holds this table in its descriptor, SYS+descriptor_map-TYPE_NAME$str.