---
tags:
  - status/completed
---
from [8000-pendingActivities](8000-pendingActivities.md)
# 8020 Resources
The classifier RESOURCES is applied to contexts to identify other contexts whose entities can be referenced. For example, most contexts will import ROOT+context.SYS.

The entities of a context can only reference entities in the same context or resource contexts. A context then only depends on its self and its resources.

- Classifiers now allow vector values, as the value of the resources classifier needs to be a vector.
- The test context now has a resources classifier with a value that is a vector with the sys context as its only item.
- Contexts are now validated against resources during context binding.

completed [2142.6 Saturday (week 42), 10-23-2021](2142.6%20Saturday%20(week%2042),%2010-23-2021.md)