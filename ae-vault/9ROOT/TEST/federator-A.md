---
DESCRIPTORS:
  SYS+descriptor_mapvec-REQUESTS^requestid$str:
    SYS+requestid-ENTITYreport:
    - ENTITY_REPORToperationid
    SYS+requestid-RUNfederation:
    - RUN_FEDERATIONoperationid
  SYS+descriptor_vec-FEDERATIONnames&?:
  - simple-ALPHA
  - simple-BETA
  SYS+descriptor_vecmap-SCRIPT^request:
  - request_map-REQUEST^param:
      SYS+param-NAME&?: simple-GAMMA
      SYS+param-TARGET&requestid: SYS+requestid-INSTANTIATE
      SYS+param-TARGETname&?: class-SIMPLE
  - request_map-REQUEST^param:
      SYS+param-TARGET&requestid: SYS+requestid-ADDdescriptors
      SYS+param-TARGETname&?: simple-ALPHA
      SYS+param_map-DESCRIPTORS^descriptor:
        descriptor-DEGREEofPOLISH$str: MIDDLING
  - request_map-REQUEST^param:
      SYS+param-TARGET&requestid: SYS+requestid-ADDdescriptors
      SYS+param-TARGETname&?: simple-GAMMA
      SYS+param_map-DESCRIPTORS^descriptor:
        descriptor-DEGREEofPOLISH$str: MIDDLING
  - request_map-REQUEST^param:
      SYS+param-TARGET&requestid: SYS+requestid-ADDrelations
      SYS+param-TARGETname&?: simple-GAMMA
      SYS+param_map-relations^relation&?:
        relation_vec-BASIC:
        - simple-ALPHA
  - request_map-REQUEST^param:
      SYS+param-TARGET&requestid: SYS+requestid-ADDrelations
      SYS+param-TARGETname&?: simple-BETA
      SYS+param_map-relations^relation&?:
        relation_vec-BASIC:
        - simple-ALPHA
TAGS:
- SYS+classifier-CLASS/SYS+class-FEDERATOR
- SYS+classifier-ENTITYtype&classifierVALUE/SYS+classifierVALUE-FEDERATOR
---
# Entity TEST+federator-A

