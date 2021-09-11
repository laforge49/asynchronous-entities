---
tags:
  - status/todo
---
from [8000-pendingActivities](8000-pendingActivities.md)
# 8020 Imports
The classifier IMPORTS is applied to contexts to identify other contexts whose entities can be referenced without any qualifier. For example, most contexts will import ROOT+context.SYS and without the need for a SYS+ prefix.

Additionally, the entities of a context can only reference entities in the same context or imported contexts. A context then only depends on the contexts which it imports.