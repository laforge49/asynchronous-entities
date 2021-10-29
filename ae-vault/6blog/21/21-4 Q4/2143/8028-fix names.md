---
tags:
  - status/completed
---
from [8000-pendingActivities](8000-pendingActivities.md)
# 8028 Fix Names
The pattern for names is contextRoot+type_structureType-root$dataType, where the  contextRoot, structureType and dataType are optional. Existing names do not all conform to this pattern and need to be fixed.

| old | new |
|----------------------------------|----------------------------------|
| ...$operationid | ...$str |
| class-CLASSIFIER_VALUE | class-CLASSIFIERvalue |
| class-DATA_TYPE | class-DATA_type |
| classifier-ENTITY_TYPE | classifier-ENTITYtype |
| classifier_value-... | classifierVALUE-... |
| classifierVALUE-CLASSIFIER_VALUE | classifierVALUE-CLASSIFIERvalue |
| classifierVALUE-FEDERATION_CONTEXT | classifierVALUE-FEDERATIONcontext |
| classifierVALUE-DATA_TYPE | classifierVALUE-DATAtype |
| descriptor-INSTANTIATION_DESCRIPTORS | descriptors_map-INSTANCEdescriptors |
| descriptor-INSTANTIATION_CLASSIFIERS | descriptors_map-INSTANCEclassifiers |
| requestid-ADD_DESCRIPTORS | requestid-ADDdescriptors |
| requestid-ADD_RELATIONS | requestid-ADDrelations |
| requestid-CONTEXT_REPORT | unused, deleted |
| requestid-ENTITY_REPORT | requestid-ENTITYreport |
| requestid-LOAD_SCRIPT | requestid-LOADscript |
| requestid-REGISTER_ENTITY | requestid-REGISTERentity |
| requestid-RUN_FEDERATION | requestid-RUNfederation |
| requestid-TYPE_OF | requestid-TYPEof |

completed [2143.5 Friday (week 43), 10-29-2021](2143.5%20Friday%20(week%2043),%2010-29-2021.md)