---
gem_map-FACETS^facet:
  facet-CONTENT$str: "this is a test same line \ndifferent line"
  facet-NAME&%: simple-ALPHA
  facet_map-CLASSIFIERS^classifier:
    classifier-CLASS&class: class-SIMPLE
  facet_map-DESCRIPTORS^descriptor:
    descriptor-DEGREEofPOLISH$str: MIDDLING
    descriptor_mapvec-REQUESTS^requestid$str:
      requestid-ADDdescriptors:
      - ADD_DESCRIPTORSoperationid
      requestid-ADDrelations:
      - ADD_RELATIONSoperationid
      requestid-ENTITYreport:
      - ENTITY_REPORToperationid
  facet_map-INVERSErelations^relation&%:
    relation_vec-BASIC:
    - simple-GAMMA
    - simple-BETA
  facet_vec-REQUESTportSTACK$chan:
  - clojure.core.async.chan
---
# Entity TEST+simple-ALPHA

this is a test same line 
different line
