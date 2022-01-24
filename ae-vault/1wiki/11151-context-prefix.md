from [11150-gem-name](11150-gem-name.md)
# 11151 Context Prefix

Every gem, with the sole exception of the ROOT+context-SYS gem, operates within a context gem. The context prefix is the first part of a gem name and contains the base name of the gem's context followed by a +.

Internally, the context prefix is a part of every gem name. But external gem names do not include the context prefix when the context can be determined by where the gem name occurs.
