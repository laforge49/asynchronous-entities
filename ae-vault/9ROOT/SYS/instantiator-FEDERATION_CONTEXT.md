---
DESCRIPTORS:
  SYS+INSTANTIATION_CLASSIFIERSdescriptor:
    SYS+ENTITY_TYPEclassifier: SYS+FEDERATION_CONTEXTclassifier_value
  SYS+INSTANTIATORdescriptor: SYS+instantiator-INSTANTIATOR
  SYS+descriptor-INSTANTIATION_DESCRIPTORS:
    SYS+descriptor_map-REQUESTID$operationid:
      SYS+RELEASErequestid:
      - FEDERATION_RELEASEoperationid
      SYS+requestid-ACQUIRE:
      - FEDERATION_ACQUIREoperationid
  SYS+descriptorINVARIANT$bool: true
  SYS+descriptor_map-REQUESTID$operationid:
    SYS+requestid-ENTITY_REPORT:
    - ENTITY_REPORToperationid
    SYS+requestid-INSTANTIATE:
    - INSTANTIATEoperationid
tags:
- SYS+ENTITY_TYPEclassifier/SYS+INSTANTIATORclassifier_value
---
# Entity SYS+instantiator-FEDERATION_CONTEXT

