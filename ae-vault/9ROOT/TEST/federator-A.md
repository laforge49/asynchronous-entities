---
SYS+gem_map-FACETS^facet:
  SYS+facet-CONTENT$str: ''
  SYS+facet-NAME&?: federator-A
  SYS+facet_map-CLASSIFIERS^classifier:
    SYS+classifier-CLASS&class: SYS+class-FEDERATOR
  SYS+facet_map-DESCRIPTORS^descriptor:
    SYS+descriptor_mapvec-REQUESTS^requestid$str:
      SYS+requestid-ENTITYreport:
      - ENTITY_REPORToperationid
      SYS+requestid-RUNfederation:
      - RUN_FEDERATIONoperationid
    SYS+descriptor_vec-FEDERATIONnames&?:
    - simple-ALPHA
    - simple-BETA
    SYS+descriptor_vecmap-SCRIPT^request:
    - SYS+request_map-REQUEST^param:
        SYS+param-NAME&?: simple-GAMMA
        SYS+param-REQUESTID&requestid: SYS+requestid-INSTANTIATE
        SYS+param-TARGETname&?: class-SIMPLE
    - SYS+request_map-REQUEST^param:
        SYS+param-REQUESTID&requestid: SYS+requestid-ADDdescriptors
        SYS+param-TARGETname&?: simple-ALPHA
        SYS+param_map-DESCRIPTORS^descriptor:
          descriptor-DEGREEofPOLISH$str: MIDDLING
    - SYS+request_map-REQUEST^param:
        SYS+param-REQUESTID&requestid: SYS+requestid-ADDdescriptors
        SYS+param-TARGETname&?: simple-GAMMA
        SYS+param_map-DESCRIPTORS^descriptor:
          descriptor-DEGREEofPOLISH$str: MIDDLING
    - SYS+request_map-REQUEST^param:
        SYS+param-REQUESTID&requestid: SYS+requestid-ADDrelations
        SYS+param-TARGETname&?: simple-GAMMA
        SYS+param_map-relations^relation&?:
          relation_vec-BASIC:
          - simple-ALPHA
    - SYS+request_map-REQUEST^param:
        SYS+param-REQUESTID&requestid: SYS+requestid-ADDrelations
        SYS+param-TARGETname&?: simple-BETA
        SYS+param_map-relations^relation&?:
          relation_vec-BASIC:
          - simple-ALPHA
  SYS+facet_vec-REQUESTportSTACK$chan:
  - clojure.core.async.chan
---
# Entity TEST+federator-A

