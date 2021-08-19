---
tag:
  - status/todo
---
from [8000-pendingActivities](8000-pendingActivities.md)
# 8008 OperationID Vectors
Entities map requestids into vectors of operationids. In the past this was useful when performing validation, as a validation request could be defined as a series of validation operations.

Currently only the first operationid of the vector is used.