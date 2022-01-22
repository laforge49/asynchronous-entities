from [11130-DESCRIPTORS](11130-DESCRIPTORS.md)

# 11131 descriptor-INVARIANT$bool
Gems with INVARIANT set to true can not be changed. Such gems always operate synchronously with their requests invoked as a function.

Invariant gems can not be federated, while mutable gems (which do not have INVARIANT set to true) can operate either asynchronously when not federated or synchronously within a federation.