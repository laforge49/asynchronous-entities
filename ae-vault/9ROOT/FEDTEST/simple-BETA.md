---
gem_map-FACETS^facet:
  facet-CONTENT$str:
    ''
  facet-NAME&%:
    simple-BETA
  facet_map-CLASSIFIERS^classifier:
    classifier-CLASS&class:
      class-SIMPLE
  facet_map-DESCRIPTORS^descriptor:
    descriptor_mapvec-REQUESTS^requestid$str:
      requestid-ADDdescriptors:
        - ADD_DESCRIPTORSoperationid
      requestid-ADDrelations:
        - ADD_RELATIONSoperationid
      requestid-GEMreport:
        - GEM_REPORToperationid
  facet_vec-REQUESTportSTACK$chan:
    - clojure.core.async.chan
---
# Gem FEDTEST+simple-BETA

