---
DESCRIPTORS:
  SYS+descriptor-SCRIPT$yaml:
  - target_requestid: SYS+requestid-INSTANTIATE
    target_name: class-SIMPLE
    name: simple-GAMMA
  - target_requestid: SYS+requestid-ADDdescriptors
    target_name: simple-ALPHA
    descriptors:
      descriptor-DEGREEofPOLISH$str: MIDDLING
  - target_requestid: SYS+requestid-ADDdescriptors
    target_name: simple-GAMMA
    descriptors:
      descriptor-DEGREEofPOLISH$str: MIDDLING
  - target_requestid: SYS+requestid-ADDrelations
    target_name: simple-GAMMA
    relations:
      relation-BASIC:
      - simple-ALPHA
  - target_requestid: SYS+requestid-ADDrelations
    target_name: simple-BETA
    relations:
      relation-BASIC:
      - simple-ALPHA
  SYS+descriptor_map-REQUESTS^requestid$str:
    SYS+requestid-ENTITYreport:
    - ENTITY_REPORToperationid
    SYS+requestid-RUNfederation:
    - RUN_FEDERATIONoperationid
  SYS+descriptor_vec-FEDERATIONnames:
  - simple-ALPHA
  - simple-BETA
TAGS:
- SYS+classifier-CLASS/SYS+class-FEDERATOR
- SYS+classifier-ENTITYtype/SYS+classifierVALUE-FEDERATOR
---
# Entity TEST+federator-A

