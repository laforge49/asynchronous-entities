---
DESCRIPTORS:
  SYS+descriptor-SCRIPT$yaml:
  - request_map-REQUEST^param: null
    SYS+param-TARGETrequestid: SYS+requestid-INSTANTIATE
    SYS+param-TARGETname: class-SIMPLE
    SYS+param-NAME: simple-GAMMA
  - request_map-REQUEST^param: null
    SYS+param-TARGETrequestid: SYS+requestid-ADDdescriptors
    SYS+param-TARGETname: simple-ALPHA
    SYS+param_map-DESCRIPTORS^descriptor:
      descriptor-DEGREEofPOLISH$str: MIDDLING
  - request_map-REQUEST^param: null
    SYS+param-TARGETrequestid: SYS+requestid-ADDdescriptors
    SYS+param-TARGETname: simple-GAMMA
    SYS+param_map-DESCRIPTORS^descriptor:
      descriptor-DEGREEofPOLISH$str: MIDDLING
  - request_map-REQUEST^param: null
    SYS+param-TARGETrequestid: SYS+requestid-ADDrelations
    SYS+param-TARGETname: simple-GAMMA
    relations:
      relation-BASIC:
      - simple-ALPHA
  - request_map-REQUEST^param: null
    SYS+param-TARGETrequestid: SYS+requestid-ADDrelations
    SYS+param-TARGETname: simple-BETA
    SYS+param-relations:
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

