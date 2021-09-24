---
DESCRIPTORS:
  SYS+INSTANTIATORdescriptor: SYS+instantiator-FEDERATOR
  SYS+descriptor-FEDERATION_NAMES:
  - simple-ALPHA
  - simple-BETA
  SYS+descriptor-SCRIPT$yaml:
  - target_requestid: SYS+requestid-INSTANTIATE
    target_name: instantiator-SIMPLE
    name: simple-GAMMA
  - target_requestid: SYS+requestid-ADD_DESCRIPTORS
    target_name: simple-ALPHA
    descriptors:
      descriptor-DEGREE_OF_POLISH$str: MIDDLING
  - target_requestid: SYS+requestid-ADD_DESCRIPTORS
    target_name: simple-GAMMA
    descriptors:
      descriptor-DEGREE_OF_POLISH$str: MIDDLING
  - target_requestid: SYS+requestid-ADD_RELATIONS
    target_name: simple-GAMMA
    relations:
      relation-BASIC: simple-ALPHA
  - target_requestid: SYS+requestid-ADD_RELATIONS
    target_name: simple-BETA
    relations:
      relation-BASIC: simple-ALPHA
  SYS+descriptor_map-REQUESTID$operationid:
    SYS+requestid-ENTITY_REPORT:
    - ENTITY_REPORToperationid
    SYS+requestid-RUN_FEDERATION:
    - RUN_FEDERATIONoperationid
TAGS:
- SYS+classifier-ENTITY_TYPE/SYS+classifier_value-FEDERATOR
---
# Entity TEST+federator-A

