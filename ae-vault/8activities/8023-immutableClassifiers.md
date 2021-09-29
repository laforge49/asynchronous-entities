---
tags:
  - status/todo
---
from [8000-pendingActivities](8000-pendingActivities.md)
# 8022 Immutable Classifiers
Created [2139.1 Monday (week 39), 9-27-2021](../6blog/21/21-3%20Q3/2139/2139.1%20Monday%20(week%2039),%209-27-2021.md)

- Relations were separated from classifiers, as relations are not immutable.
- Classifier values are either entity names or vectors of entity names.
- Classifiers are stored in their own tree, allowing for easier access than if they were part of the entity they classify. This is practical as they are immutable. Classifier access then is synchronous.