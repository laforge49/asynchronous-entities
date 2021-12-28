---
gem_map-FACETS^facet:
  facet-CONTENT$str: ''
  facet-NAME&%: facet_vec-REQUESTportSTACK$chan
  facet_map-CLASSIFIERS^classifier:
    classifier-CLASS&class: class-FACETvec
  facet_map-DESCRIPTORS^descriptor:
    descriptor-INVARIANT$bool: true
    descriptor_mapvec-REQUESTS^requestid$str:
      requestid-ENTITYreport:
      - ENTITY_REPORToperationid
  facet_vec-REQUESTportSTACK$chan:
  - clojure.core.async.chan
---
# Entity SYS+facet_vec-REQUESTportSTACK$chan

