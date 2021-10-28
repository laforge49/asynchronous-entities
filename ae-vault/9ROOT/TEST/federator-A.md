---
DESCRIPTORS:
  SYS+descriptor-FEDERATION_NAMES:
  - simple-ALPHA
  - simple-BETA
  SYS+descriptor-SCRIPT$yaml:
  - target_requestid: SYS+requestid-INSTANTIATE
    target_name: class-SIMPLE
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
      relation-BASIC:
      - simple-ALPHA
  - target_requestid: SYS+requestid-ADD_RELATIONS
    target_name: simple-BETA
    relations:
      relation-BASIC:
      - simple-ALPHA
  SYS+descriptor_map-REQUESTID$str:
    SYS+requestid-ENTITYreport:
    - ENTITY_REPORToperationid
    SYS+requestid-RUN_FEDERATION:
    - RUN_FEDERATIONoperationid
TAGS:
- SYS+classifier-CLASS/SYS+class-FEDERATOR
- SYS+classifier-ENTITYtype/SYS+classifierVALUE-FEDERATOR
---
# Entity TEST+federator-A

