from [11150-NAME](11150-NAME.md)
# 11153 Structure Type

The structure type determines the kind of structure that can be assigned as the value when a gem name is used as the key in a map. The structure type has a prefix of '_' followed by

- map
- mapmap
- mapvec
- vec
- vecmap.

For example, when the structure type is mapvec, then the value assigned to the gem name when used as a key is a map whose values are vectors.

The structure type is only present in gem names which are used as keys with values which are not simple scalars.
