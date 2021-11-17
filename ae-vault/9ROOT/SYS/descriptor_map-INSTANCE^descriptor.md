---
gem-FACETS^facet:
  facet-CONTENT$str: ''
  facet-NAME&?: descriptor_map-INSTANCE^descriptor
  facet_map-CLASSIFIERS^classifier:
    classifier-CLASS: class-DESCRIPTOR
    classifier-ENTITYtype&classifierVALUE: classifierVALUE-DESCRIPTOR
  facet_map-DESCRIPTORS^descriptor:
    descriptor-INVARIANT$bool: true
    descriptor_mapvec-REQUESTS^requestid$str:
      requestid-ENTITYreport:
      - ENTITY_REPORToperationid
  facet_vec-REQUESTportSTACK$chan:
  - clojure.core.async.chan
---
# Entity SYS+descriptor_map-INSTANCE^descriptor

