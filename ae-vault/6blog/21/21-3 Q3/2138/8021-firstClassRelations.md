---
tags:
  - status/completed
---
from [8000-pendingActivities](8000-pendingActivities.md)
# 8021 First Class Relations
To allow functional access to classifier values, they will be treated as immutable. However, relation values are not. For this reason we will elevate relation structures within an entity to the same level as classifiers and descriptors.
- Test script no longer adds a classifier.
- Renamed add-classifiers to add-relations.
- Add relations updates the relations attributes, which are reported.
- Enhanced test script. Add relation beta->alpha.
- Relation processing parallel to classifier processing.

completed: [2138.5 Friday (week 38), 9-24-2021](2138.5%20Friday%20(week%2038),%209-24-2021.md)

Addendum: The values of relations are vectors. [2139.1 Monday (week 39), 9-27-2021](../2139/2139.1%20Monday%20(week%2039),%209-27-2021.md)

Addendum: inverted relations should mirror relations in their implementation, and so should be stored in the object of the relation. [2139.2 Tuesday (week 39), 9-28-2021](../2139/2139.2%20Tuesday%20(week%2039),%209-28-2021.md)