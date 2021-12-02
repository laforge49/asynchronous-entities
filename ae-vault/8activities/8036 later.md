---
tags:
  - status/todo
---
from [8000-pendingActivities](8000-pendingActivities.md)
# 8036 Later

When processing reports, the contexts are left locked-which is bad practice. We want to create the reports asynchronously, locking only one gem at a time, while still ensuring that everything is completed prior to termination. So we introduce the concept of later.

The later gem receives requests which include the target name. It invokes the firs and on completion it invokes the next.

If the later gem times out while reading a request, then the program exits. Main then consists of placing the top-level request in the later queue and then reading requests, processing them, and waiting for completion prior to repeating the loop, exiting on timeout.