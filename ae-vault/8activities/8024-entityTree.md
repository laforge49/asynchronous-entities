---
tags:
  - status/todo
---
from [8000-pendingActivities](8000-pendingActivities.md)
# 8024 Entity Tree
Created [2139.3 Wednesday (week 39), 9-29-2021](../6blog/21/21-3%20Q3/2139/2139.3%20Wednesday%20(week%2039),%209-29-2021.md)

Initially all activity was asynchronous. Synchronous access was added to support federations of entities. Then synchronous access was added to support immutable entity access. Now classifiers are immutable and there is a need for synchronous access to them, especially during federation creation. The result is a lot more code than is needed.

Currently entities reside in a tree as go blocks, as that was the base case, with synchronous access supported by various maps, each held by an atom. Simpler instead to put all entities into a single tree under an atom and place appropriate restrictions on access.