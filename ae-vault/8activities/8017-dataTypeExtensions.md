---
tags:
  - status/todo
---
from [8000-pendingActivities](8000-pendingActivities.md)
# 8017 Data Type Extensions
For purposes of context binding, an attribute value which starts with a + is a name in the local context and will be prefixed with the name of the context for internal processing. Which means a data value can not start with a +, which is a bit of an ugly constraint.

Rather, lets have the name of the attritube or sub-attribute which hold a non-name value have a data type extension, like $bool. Values are now unconstrained and we can drop all the leading +'s which have been used to designate names.

Names that need to be changed:

old name | new name
--- | ---
INVARIANTdescriptor | descriptorINVARIANT$bool
REQUESTID_MAP | descriptor_mapREQUESTID$operationid
READ_ONLYdescriptor | descriptorREAD_ONLY$bool
content | content$ml
SCRIPTdescriptor | descriptorSCRIPT$yaml