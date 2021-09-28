---
tags:
  - status/todo
---
from [8000-pendingActivities](8000-pendingActivities.md)
# 8022 Immutable Classifiers
- Relations were separated from classifiers, as relations are not immutable.
- Classifier values are either entity names or vectors of entity names.
- Classifiers are stored in their own tree, allowing for easier access than if they were part of the entity they classify. This is practical as they are immutable. Classifier access then is synchronous.