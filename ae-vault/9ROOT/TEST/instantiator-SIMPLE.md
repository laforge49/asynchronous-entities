---
DESCRIPTORS:
  SYS+INSTANTIATION_CLASSIFIERSdescriptor:
    SYS+ENTITY_TYPEclassifier: TEST+classifier_value-SIMPLE
  SYS+INSTANTIATORdescriptor: SYS+instantiator-INSTANTIATOR
  SYS+descriptor-INSTANTIATION_DESCRIPTORS:
    SYS+descriptor_map-REQUESTID$operationid:
      SYS+ADD_CLASSIFIERSrequestid:
      - ADD_CLASSIFIERSoperationid
      SYS+ADD_DESCRIPTORSrequestid:
      - ADD_DESCRIPTORSoperationid
      SYS+requestid-ENTITY_REPORT:
      - ENTITY_REPORToperationid
  SYS+descriptorINVARIANT$bool: true
  SYS+descriptor_map-REQUESTID$operationid:
    SYS+requestid-ENTITY_REPORT:
    - ENTITY_REPORToperationid
    SYS+requestid-INSTANTIATE:
    - INSTANTIATEoperationid
tags:
- SYS+ENTITY_TYPEclassifier/SYS+INSTANTIATORclassifier_value
---
# Entity TEST+instantiator-SIMPLE

