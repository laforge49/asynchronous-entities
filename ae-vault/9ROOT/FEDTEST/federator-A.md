---
gem_map-FACETS^facet:
  SYS+facet-CONTENT$str:
    ''
  SYS+facet-NAME&%:
    federator-A
  SYS+facet_map-CLASSIFIERS^classifier:
    SYS+classifier-CLASS&class:
      class-FEDERATOR
  SYS+facet_map-DESCRIPTORS^descriptor:
    FED+descriptor_vec-FEDERATIONnames&%:
      - simple-ALPHA
      - simple-BETA
    SYS+descriptor_map-SCRIPT^request:
      0000010 SYS+request_map-REQUEST^param:
        SYS+param-NAME&%:
          simple-GAMMA
        SYS+param-REQUESTID&requestid:
          requestid-INSTANTIATE
        SYS+param-TARGETname&%:
          class-SIMPLE
      0000020 SYS+request_map-REQUEST^param:
        SYS+param-REQUESTID&requestid:
          requestid-ADDdescriptors
        SYS+param-TARGETname&%:
          simple-ALPHA
        SYS+param_map-DESCRIPTORS^descriptor:
          FEDTEST+descriptor-DEGREEofPOLISH$str:
            MIDDLING
      0000030 SYS+request_map-REQUEST^param:
        SYS+param-REQUESTID&requestid:
          requestid-ADDdescriptors
        SYS+param-TARGETname&%:
          simple-GAMMA
        SYS+param_map-DESCRIPTORS^descriptor:
          FEDTEST+descriptor-DEGREEofPOLISH$str:
            MIDDLING
      0000040 SYS+request_map-REQUEST^param:
        FED+param_map-RELATIONS^relation&%:
          FEDTEST+relation_vec-BASIC:
            - simple-ALPHA
        SYS+param-REQUESTID&requestid:
          requestid-ADDrelations
        SYS+param-TARGETname&%:
          simple-GAMMA
      0000050 SYS+request_map-REQUEST^param:
        FED+param_map-RELATIONS^relation&%:
          FEDTEST+relation_vec-BASIC:
            - simple-ALPHA
        SYS+param-REQUESTID&requestid:
          requestid-ADDrelations
        SYS+param-TARGETname&%:
          simple-BETA
    SYS+descriptor_mapvec-REQUESTS^requestid$str:
      FED+requestid-RUNfederation:
        - RUN_FEDERATIONoperationid
      SYS+requestid-GEMreport:
        - GEM_REPORToperationid
  SYS+facet_vec-REQUESTportSTACK$chan:
    - clojure.core.async.chan
---
# Gem FEDTEST+federator-A

