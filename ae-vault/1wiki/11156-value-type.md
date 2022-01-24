from [11150-gem-name](11150-gem-name.md)
# 11156 Value Type

The value type identifies the type of value that can be assigned to a gem name when it is used as a key in a map. 

A value can be a gem name, in which case the value type starts with an & and the remainder is either % to indicate a wild card, or a [gem-type](11152-gem-type.md). 

Alternately, a value can be a constant, in which case the value type starts with a $ and the remainder is either a %, or one of bool, int or str.

The value type is only present in gem names which are used as keys
