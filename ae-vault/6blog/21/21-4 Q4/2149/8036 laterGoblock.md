---
tags:
  - status/completed
---
from [8000-pendingActivities](8000-pendingActivities.md)
# 8036 Later Go Block

When processing reports, the contexts are left locked-which is bad practice. We want to create the reports asynchronously, locking only one gem at a time, while still ensuring that everything is completed prior to termination. So we introduce the concept of later.

The later go block receives requests which include the target name. It invokes the first and on completion it invokes the next.

If the later go block times out while reading a request, then the program exits. Main then consists of writing the top-level request to the later go block and then reading requests, processing them, and waiting for completion prior to repeating the loop, exiting the program on timeout.

Go later supports a stack of requests, so while processing a request, additional requests can be pushed on the stack.

Go later is designed to support the evaluation of multiple request stacks in parallel.

completed: [2149.5 Friday (week 49), 12-10-2021](2149.5%20Friday%20(week%2049),%2012-10-2021.md)