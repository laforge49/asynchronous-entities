---
gem_map-FACETS^facet:
  facet-CONTENT$str: ''
  facet-NAME&?: descriptor-INVARIANT$bool
  facet_map-CLASSIFIERS^classifier:
    classifier-CLASS&class: class-DESCRIPTOR
  facet_map-DESCRIPTORS^descriptor:
    descriptor-INVARIANT$bool: true
    descriptor_mapvec-REQUESTS^requestid$str:
      requestid-ENTITYreport:
      - ENTITY_REPORToperationid
  facet_vec-REQUESTportSTACK$chan:
  - clojure.core.async.chan
---
# Entity SYS+descriptor-INVARIANT$bool

