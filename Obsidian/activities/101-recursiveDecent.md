from [100-activities](activities/100-activities.md)
# 101 Recursive Decent
Previously, every operation had its own dedicated port. So once an operation
is started (asynchronously) for that operation, the operation could not begin on another entity until
the first invocation completes. This completely precludes the use of recursive decent.
(Synchronous operations do not use ports, but that is a very different story.)

All operations have been converted now so that the go block of an operation is invoked with each asynchronous operation. This allows multiple invocations of the same operation to be processed in parallel.

What remains is to test that recursive decent now works.

We also need to drop the code that supports the invocation of the old-style asynchronous operation.

status #todo