---
DESCRIPTORS:
  SYS+INSTANTIATORdescriptor: SYS+instantiator-INSTANTIATOR
  SYS+descriptor-INSTANTIATION_CLASSIFIERS:
    SYS+classifier-ENTITY_TYPE: SYS+classifier_value-FEDERATION_CONTEXT
  SYS+descriptor-INSTANTIATION_DESCRIPTORS:
    SYS+descriptor_map-REQUESTID$operationid:
      SYS+requestid-ACQUIRE:
      - FEDERATION_ACQUIREoperationid
      SYS+requestid-RELEASE:
      - FEDERATION_RELEASEoperationid
  SYS+descriptor-INVARIANT$bool: true
  SYS+descriptor_map-REQUESTID$operationid:
    SYS+requestid-ENTITY_REPORT:
    - ENTITY_REPORToperationid
    SYS+requestid-INSTANTIATE:
    - INSTANTIATEoperationid
tags:
- SYS+classifier-ENTITY_TYPE/SYS+classifier_value-INSTANTIATOR
---
# Entity SYS+instantiator-FEDERATION_CONTEXT

