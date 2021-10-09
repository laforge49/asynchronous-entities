---
tags:
  - status/completed
---
from [8000-pendingActivities](8000-pendingActivities.md)
# 8024 Entity Tree
Created [2139.3 Wednesday (week 39), 9-29-2021](../../21-3%20Q3/2139/2139.3%20Wednesday%20(week%2039),%209-29-2021.md)

Initially all activity was asynchronous. Synchronous access was added to support federations of entities. Then synchronous access was added to support immutable entity access. Now classifiers are immutable and there is a need for synchronous access to them, especially during federation creation. The result is a lot more code than is needed.

Currently entities reside in a tree as go blocks, as that was the base case, with synchronous access supported by various maps, each held by an atom. Simpler instead to put all entities into a single tree under an atom and place appropriate restrictions on access.

The entity maps held by the go blocks:
- The entity-map-atom now holds all the entity maps previously held by the go blocks, making them very accessible.

Invariant maps
- Invariant maps are just those entity maps that are marked invariant.

The entity maps held by a federation:
- While a federation is active, the FEDERATOR-NAME in the federator's env names the federator. This will be used to identify the active federation.
- While a federation is active, the FEDERATOR-NAME in the entity maps will name the federation they are participating in.
- Simplified async dispatch to ignore the federation entity map; federation temporarily disabled.
- Enabled federation, disabled relation and children. Federation folded into the entity-map-atom.
- Enabled relation, updated addRelation.
- Dropped old code relating to adding classifiers.
- Enabled children and converted to new API.

completed: [2140.2 Tuesday (week 40), 10-5-2021](2140.2%20Tuesday%20(week%2040),%2010-5-2021.md)