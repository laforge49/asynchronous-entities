---
DESCRIPTORS:
  SYS+INSTANTIATION_CLASSIFIERSdescriptor:
    SYS+ENTITY_TYPEclassifier: SYS+classifier_valueDATA_TYPE
  SYS+INSTANTIATION_DESCRIPTORSdescriptor:
    SYS+descriptorINVARIANT$bool: true
    SYS+descriptor_mapREQUESTID$operationid:
      SYS+ENTITY_REPORTrequestid:
      - ENTITY_REPORToperationid
      SYS+requestidTYPE_OF:
      - operationidTYPE_OF
  SYS+INSTANTIATORdescriptor: SYS+INSTANTIATORinstantiator
  SYS+descriptorINVARIANT$bool: true
  SYS+descriptor_mapREQUESTID$operationid:
    SYS+ENTITY_REPORTrequestid:
    - ENTITY_REPORToperationid
    SYS+INSTANTIATErequestid:
    - INSTANTIATEoperationid
tags:
- SYS+ENTITY_TYPEclassifier/SYS+INSTANTIATORclassifier_value
---
# Entity SYS+instantiatorDATA_TYPE

