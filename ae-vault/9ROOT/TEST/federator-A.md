---
gem_map-FACETS^facet:
  facet-CONTENT$str: ''
  facet-NAME&%: federator-A
  facet_map-CLASSIFIERS^classifier:
    classifier-CLASS&class: class-FEDERATOR
  facet_map-DESCRIPTORS^descriptor:
    descriptor_mapvec-REQUESTS^requestid$str:
      requestid-ENTITYreport:
      - ENTITY_REPORToperationid
      requestid-RUNfederation:
      - RUN_FEDERATIONoperationid
    descriptor_vec-FEDERATIONnames&%:
    - simple-ALPHA
    - simple-BETA
    descriptor_vecmap-SCRIPT^request:
    - request_map-REQUEST^param:
        param-NAME&%: simple-GAMMA
        param-REQUESTID&requestid: requestid-INSTANTIATE
        param-TARGETname&%: class-SIMPLE
    - request_map-REQUEST^param:
        param-REQUESTID&requestid: requestid-ADDdescriptors
        param-TARGETname&%: simple-ALPHA
        param_map-DESCRIPTORS^descriptor:
          descriptor-DEGREEofPOLISH$str: MIDDLING
    - request_map-REQUEST^param:
        param-REQUESTID&requestid: requestid-ADDdescriptors
        param-TARGETname&%: simple-GAMMA
        param_map-DESCRIPTORS^descriptor:
          descriptor-DEGREEofPOLISH$str: MIDDLING
    - request_map-REQUEST^param:
        param-REQUESTID&requestid: requestid-ADDrelations
        param-TARGETname&%: simple-GAMMA
        param_map-RELATIONS^relation&%:
          relation_vec-BASIC:
          - simple-ALPHA
    - request_map-REQUEST^param:
        param-REQUESTID&requestid: requestid-ADDrelations
        param-TARGETname&%: simple-BETA
        param_map-RELATIONS^relation&%:
          relation_vec-BASIC:
          - simple-ALPHA
  facet_vec-REQUESTportSTACK$chan:
  - clojure.core.async.chan
---
# Entity TEST+federator-A

