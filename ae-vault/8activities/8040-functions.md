---
tags:
  - status/todo
---
from [8000-pendingActivities](8000-pendingActivities.md)
# 8040 Functions

Functions currently implement federated operations. They need to be extended to cover synchronous unfederated operations. (Go blocks implement asynchronous operations.)

Of special concern is the instantiation operation. Currently it has both federated function and an asynchronous go block implementations. These need to be replaced by a single synchronous operation, allowing class gems to be synchronous.

Additionally, we need a synchronous implementation of the report operation for gems which are not asynchronous.