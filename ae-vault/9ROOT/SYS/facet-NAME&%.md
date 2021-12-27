---
gem_map-FACETS^facet:
  facet-CONTENT$str: ''
  facet-NAME&%: facet-NAME&%
  facet_map-CLASSIFIERS^classifier:
    classifier-CLASS&class: class-FACET
  facet_map-DESCRIPTORS^descriptor:
    descriptor-INVARIANT$bool: true
    descriptor_mapvec-REQUESTS^requestid$str:
      requestid-ENTITYreport:
      - ENTITY_REPORToperationid
  facet_vec-REQUESTportSTACK$chan:
  - clojure.core.async.chan
---
# Entity SYS+facet-NAME&%

