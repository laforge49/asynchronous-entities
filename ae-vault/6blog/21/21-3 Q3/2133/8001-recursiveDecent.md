---
tags:
  - status/completed
---
from [8000-pendingActivities](8000-pendingActivities.md)
# 8001 Recursive Decent
Previously, every operation had its own dedicated port. So once an operation
is started (asynchronously) for that operation, the operation could not begin on another entity until
the first invocation completes. This completely precludes the use of recursive decent.
(Synchronous operations do not use ports, but that is a very different story.)

All operations have been converted now so that the go block of an operation is invoked with each asynchronous operation. This allows multiple invocations of the same operation to be processed in parallel.

completed  [2133.4 Thursday (week 33), 8-19-2021](2133.4%20Thursday%20(week%2033),%208-19-2021.md)