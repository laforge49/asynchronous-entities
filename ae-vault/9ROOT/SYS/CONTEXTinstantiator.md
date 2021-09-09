---
DESCRIPTORS:
  SYS+INSTANTIATION_CLASSIFIERSdescriptor:
    SYS+ENTITY_TYPEclassifier: SYS+CONTEXTclassifier_value
  SYS+INSTANTIATION_DESCRIPTORSdescriptor:
    SYS+descriptor_map-REQUESTID$operationid:
      SYS+requestid-ENTITY_REPORT:
      - CONTEXT_REPORToperationid
      SYS+requestid-LOAD_SCRIPT:
      - LOAD_SCRIPToperationid
      SYS+requestid-REGISTER_CLASSIFIER:
      - REGISTER_CLASSIFIERoperationid
      SYS+requestid-REGISTER_ENTITY:
      - REGISTER_ENTITYoperationid
      SYS+requestid-ROUTE:
      - ROUTEoperationid
  SYS+INSTANTIATORdescriptor: SYS+INSTANTIATORinstantiator
  SYS+descriptorINVARIANT$bool: true
  SYS+descriptor_map-REQUESTID$operationid:
    SYS+INSTANTIATErequestid:
    - INSTANTIATEoperationid
    SYS+requestid-ENTITY_REPORT:
    - ENTITY_REPORToperationid
tags:
- SYS+ENTITY_TYPEclassifier/SYS+INSTANTIATORclassifier_value
---
# Entity SYS+CONTEXTinstantiator

