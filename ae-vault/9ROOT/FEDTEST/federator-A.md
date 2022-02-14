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
  facet_vec-REQUESTportSTACK$chan:
  - clojure.core.async.chan
---
# Entity FEDTEST+federator-A

