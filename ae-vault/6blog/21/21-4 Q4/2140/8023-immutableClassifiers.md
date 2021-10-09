---
tags:
  - status/dropped
---
from [8000-pendingActivities](8000-pendingActivities.md)
# 8023 Immutable Classifiers
Created [2139.1 Monday (week 39), 9-27-2021](../../21-3%20Q3/2139/2139.1%20Monday%20(week%2039),%209-27-2021.md)

- Relations were separated from classifiers, as relations are not immutable.
- Classifier values are either entity names or vectors of entity names.
- Classifiers are stored in their own tree, allowing for easier access than if they were part of the entity they classify. This is practical as they are immutable. Classifier access then is synchronous.

Due to the reorganization of entities into a single tree, this activity has been dropped.

dropped: [2140.5 Friday (week 40), 10-8-2021](2140.5%20Friday%20(week%2040),%2010-8-2021.md)