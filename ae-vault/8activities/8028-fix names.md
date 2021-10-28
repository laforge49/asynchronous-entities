---
tags:
  - status/todo
---
from [8000-pendingActivities](8000-pendingActivities.md)
# 8028 Fix Names
The pattern for names is contextRoot+type_structureType-root$dataType, where the  contextRoot, structureType and dataType are optional. Existing names do not all conform to this pattern and need to be fixed.

| old | new |
|----------------------------------|----------------------------------|
| descriptor-INSTANTIATION_DESCRIPTORS | descriptors_map-INSTANCEdescriptors |
| descriptor-INSTANTIATION_CLASSIFIERS | descriptors_map-INSTANCE_CLASSIFIERS |
| classifier_value-* | classifierVALUE-* |
| *$operationid | *$str |