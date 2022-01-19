---
tags:
  - status/todo
---
from [8000-pendingActivities](8000-pendingActivities.md)
# 8039 Async Processing

A descriptor-ASYNC$bool attribute, when true, indicates that the gem has a port and can be invoked asynchronously.

Update [2202.6 Saturday (week 02), 01-15-2022](../6blog/22/22-1%20Q1/2202/2202.6%20Saturday%20(week%2002),%2001-15-2022.md):
The invariant flag can be used in place of async, with only non-invariant gems supporting asynchronous processing.

Update [2203.1 Monday (week 03), 01-17-2022](../6blog/22/22-1%20Q1/2203/2203.1%20Monday%20(week%2003),%2001-17-2022.md):
When later processes a request, if there is no go-block for that request (because the gem is invariant), the function for that request is called instead.
