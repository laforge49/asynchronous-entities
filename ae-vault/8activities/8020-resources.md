---
tags:
  - status/todo
---
from [8000-pendingActivities](8000-pendingActivities.md)
# 8020 Resources
The classifier RESOURCES is applied to contexts to identify other contexts whose entities can be referenced. For example, most contexts will import ROOT+context.SYS.

The entities of a context can only reference entities in the same context or resource contexts. A context then only depends on its self and its resources.

- Classifiers now allow vector values, as the value of the resources classifier needs to be a vector.
- The test context now has a resources classifier with a value that is a vector with the sys context as its only item.