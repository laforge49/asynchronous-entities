---
tags:
  - status/todo
---
from [8000-pendingActivities](8000-pendingActivities.md)
# 8019 Validate Entity Names
Entity names take the form type-NAME$data_type where
- $data_type is optional
- . is not allowed
- type can not contain a -

Entity names can be validated as part of the context binding process.