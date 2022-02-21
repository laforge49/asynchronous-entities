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
    SYS+descriptor_mapvec-REQUESTS^requestid$str:
      FED+requestid-RUNfederation:
        - RUN_FEDERATIONoperationid
      SYS+requestid-GEMreport:
        - GEM_REPORToperationid
  SYS+facet_vec-REQUESTportSTACK$chan:
    - clojure.core.async.chan
---
# Gem FEDTEST+federator-A

