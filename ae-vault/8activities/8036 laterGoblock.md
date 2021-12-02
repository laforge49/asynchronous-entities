---
tags:
  - status/todo
---
from [8000-pendingActivities](8000-pendingActivities.md)
# 8036 Later Go Block

When processing reports, the contexts are left locked-which is bad practice. We want to create the reports asynchronously, locking only one gem at a time, while still ensuring that everything is completed prior to termination. So we introduce the concept of later.

The later go block receives requests which include the target name. It invokes the first and on completion it invokes the next.

If the later go block times out while reading a request, then the program exits. Main then consists of writing the top-level request to the later go block and then reading requests, processing them, and waiting for completion prior to repeating the loop, exiting the program on timeout.