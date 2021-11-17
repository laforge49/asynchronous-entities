---
gem-FACETS^facet:
  facet-CONTENT$str: ''
  facet-NAME&?: classifier-ENTITYtype&classifierVALUE
  facet_map-CLASSIFIERS^classifier:
    classifier-CLASS: class-CLASSIFIER
    classifier-ENTITYtype&classifierVALUE: classifierVALUE-CLASSIFIER
  facet_map-DESCRIPTORS^descriptor:
    descriptor-INVARIANT$bool: true
    descriptor_mapvec-REQUESTS^requestid$str:
      requestid-ENTITYreport:
      - ENTITY_REPORToperationid
  facet_vec-REQUESTportSTACK$chan:
  - clojure.core.async.chan
---
# Entity SYS+classifier-ENTITYtype&classifierVALUE

