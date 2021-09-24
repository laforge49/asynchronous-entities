---
tags:
  - status/todo
---
from [8000-pendingActivities](8000-pendingActivities.md)
# 8021 Classifier values are Immutable
To allow functional access to classifier values, they will be treated as immutable. However, relation values are not. For this reason we will elevate relation structures within an entity to the same level as classifiers and descriptors.
- Test script no longer adds a classifier.
- Renamed add-classifiers to add-relations.
- Add relations updates the relations attributes, which are reported.
- Enhanced test script. Add relation beta->alpha.