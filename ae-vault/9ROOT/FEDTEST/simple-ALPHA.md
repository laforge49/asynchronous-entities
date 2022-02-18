---
gem_map-FACETS^facet:
  facet-CONTENT$str:
    "this is a test same line \ndifferent line"
  facet-NAME&%:
    simple-ALPHA
  facet_map-CLASSIFIERS^classifier:
    classifier-CLASS&class:
      class-SIMPLE
  facet_map-DESCRIPTORS^descriptor:
    descriptor_mapvec-REQUESTS^requestid$str:
      requestid-ADDdescriptors:
        - ADD_DESCRIPTORSoperationid
      requestid-ADDrelations:
        - ADD_RELATIONSoperationid
      requestid-ENTITYreport:
        - ENTITY_REPORToperationid
  facet_vec-REQUESTportSTACK$chan:
    - clojure.core.async.chan
---
# Gem FEDTEST+simple-ALPHA

this is a test same line 
different line
