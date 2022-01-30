---
tags:
  - status/completed
---
from [8000-pendingActivities](8000-pendingActivities.md)

Maps provide access to there content by key or as a sequence that depends on the keys. In the first case you need to know the exact key to retrieve a value from the map. In the second case you can add a preface to the key to control the order of the map's contents as the exact key does not need to be known. In this latter case, the preface is called the [11157-order-value](../../../../1wiki/11157-order-value.md).  

Order values need to be transparent to all operations except for those that bear on ordering. In particular, when a key contains a [11150-gem-name](../../../../1wiki/11150-gem-name.md), the order value is not a part of the gem name.