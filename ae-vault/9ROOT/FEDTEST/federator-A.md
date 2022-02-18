---
gem_map-FACETS^facet:
  facet-CONTENT$str:
    ''
  facet-NAME&%:
    federator-A
  facet_map-CLASSIFIERS^classifier:
    classifier-CLASS&class:
      class-FEDERATOR
  facet_map-DESCRIPTORS^descriptor:
    descriptor_map-SCRIPT^request:
      0000010 request_map-REQUEST^param:
        param-NAME&%:
          simple-GAMMA
        param-REQUESTID&requestid:
          requestid-INSTANTIATE
        param-TARGETname&%:
          class-SIMPLE
    descriptor_mapvec-REQUESTS^requestid$str:
      requestid-ENTITYreport:
        - ENTITY_REPORToperationid
      requestid-RUNfederation:
        - RUN_FEDERATIONoperationid
    descriptor_vec-FEDERATIONnames&%:
      - simple-ALPHA
- simple-BETA
  facet_vec-REQUESTportSTACK$chan:
    - clojure.core.async.chan
---
# Gem FEDTEST+federator-A

