---
DESCRIPTORS:
  SYS+FEDERATION_NAMESdescriptor:
  - TEST+simple-ALPHA
  SYS+INSTANTIATORdescriptor: SYS+instantiator-FEDERATOR
  SYS+descriptorSCRIPT$yaml:
  - target_requestid: SYS+requestid-INSTANTIATE
    target_name: instantiator-SIMPLE
    name: simple-GAMMA
  - target_requestid: SYS+requestid-ADD_DESCRIPTORS
    target_name: simple-ALPHA
    descriptors:
      descriptor-DEGREE_OF_POLISH$str: MIDDLING
  - target_requestid: SYS+requestid-ADD_CLASSIFIERS
    target_name: simple-ALPHA
    classifiers:
      classifier-APPLICATION: classifier_value-FIDDLING
  - target_requestid: SYS+requestid-ADD_DESCRIPTORS
    target_name: simple-GAMMA
    descriptors:
      descriptor-DEGREE_OF_POLISH$str: MIDDLING
  - target_requestid: SYS+requestid-ADD_CLASSIFIERS
    target_name: simple-GAMMA
    classifiers:
      relation-BASIC: simple-ALPHA
  - target_requestid: SYS+requestid-ADD_CLASSIFIERS
    target_name: simple-GAMMA
    classifierS:
      classifier-APPLICATION: classifier_value-FIDDLING
  SYS+descriptor_map-REQUESTID$operationid:
    SYS+requestid-ENTITY_REPORT:
    - ENTITY_REPORToperationid
    SYS+requestid-RUN_FEDERATION:
    - RUN_FEDERATIONoperationid
tags:
- SYS+classifier-ENTITY_TYPE/SYS+classifier_value-FEDERATOR
---
# Entity TEST+federator-A

