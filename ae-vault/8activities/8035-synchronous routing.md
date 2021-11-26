---
tags:
  - status/todo
---
from [8000-pendingActivities](8000-pendingActivities.md)
#8035 Synchronous Message Routing

- ENTITYpublicREQUESTports should be dropped, as it is redundant. Instead, use the bottom entry of REQUESTportSTACK in each entity's map, which can be accessed synchronously via kernel/entity-map-atom. Message routing then is no longer asynchronous via the destination context.