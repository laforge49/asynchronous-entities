---
DESCRIPTORS:
  SYS+descriptor-SCRIPT$yaml:
  - param-TARGETrequestid: SYS+requestid-INSTANTIATE
    param-TARGETname: class-SIMPLE
    param-NAME: simple-GAMMA
  - param-TARGETrequestid: SYS+requestid-ADDdescriptors
    param-TARGETname: simple-ALPHA
    param_map-DESCRIPTORS^descriptor:
      descriptor-DEGREEofPOLISH$str: MIDDLING
  - param-TARGETrequestid: SYS+requestid-ADDdescriptors
    param-TARGETname: simple-GAMMA
    param_map-DESCRIPTORS^descriptor:
      descriptor-DEGREEofPOLISH$str: MIDDLING
  - param-TARGETrequestid: SYS+requestid-ADDrelations
    param-TARGETname: simple-GAMMA
    relations:
      relation-BASIC:
      - simple-ALPHA
  - param-TARGETrequestid: SYS+requestid-ADDrelations
    param-TARGETname: simple-BETA
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

