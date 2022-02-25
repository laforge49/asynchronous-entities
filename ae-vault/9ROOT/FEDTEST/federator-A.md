---
SYS+gem-FACETS_map^facet:
  SYS+facet-CLASSIFIERS_map^classifier:
    SYS+classifier-CLASS&class:
      class-FEDERATOR
  SYS+facet-CONTENT$str:
    ''
  SYS+facet-DESCRIPTORS_map^descriptor:
    FED+descriptor-FEDERATIONnames_vec&%:
      - simple-ALPHA
      - simple-BETA
    SYS+descriptor-REQUESTS_mapvec^requestid$str:
      FED+requestid-RUNfederation:
        - RUN_FEDERATIONoperationid
      SYS+requestid-GEMreport:
        - GEM_REPORToperationid
    SYS+descriptor-SCRIPT_map^request:
      0000010 SYS+request-REQUEST_map^param:
        SYS+param-NAME&%:
          simple-GAMMA
        SYS+param-REQUESTID&requestid:
          requestid-INSTANTIATE
        SYS+param-TARGETname&%:
          class-SIMPLE
      0000020 SYS+request-REQUEST_map^param:
        SYS+param-DESCRIPTORS_map^descriptor:
          FEDTEST+descriptor-DEGREEofPOLISH$str:
            MIDDLING
        SYS+param-REQUESTID&requestid:
          requestid-ADDdescriptors
        SYS+param-TARGETname&%:
          simple-ALPHA
      0000030 SYS+request-REQUEST_map^param:
        SYS+param-DESCRIPTORS_map^descriptor:
          FEDTEST+descriptor-DEGREEofPOLISH$str:
            MIDDLING
        SYS+param-REQUESTID&requestid:
          requestid-ADDdescriptors
        SYS+param-TARGETname&%:
          simple-GAMMA
      0000040 SYS+request-REQUEST_map^param:
        FED+param-RELATIONS_map^relation&%:
          FEDTEST+relation-BASIC_vec:
            - simple-ALPHA
        SYS+param-REQUESTID&requestid:
          requestid-ADDrelations
        SYS+param-TARGETname&%:
          simple-GAMMA
      0000050 SYS+request-REQUEST_map^param:
        FED+param-RELATIONS_map^relation&%:
          FEDTEST+relation-BASIC_vec:
            - simple-ALPHA
        SYS+param-REQUESTID&requestid:
          requestid-ADDrelations
        SYS+param-TARGETname&%:
          simple-BETA
  SYS+facet-NAME&%:
    federator-A
  SYS+facet-REQUESTportSTACK_vec$chan:
    - clojure.core.async.chan
---
# Gem FEDTEST+federator-A

