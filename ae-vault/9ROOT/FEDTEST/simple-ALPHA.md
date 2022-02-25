---
gem_map-FACETS^facet:
  FED+facet-INVERSErelations_map^relation&%:
    FEDTEST+relation-BASIC_vec:
      - simple-GAMMA
      - simple-BETA
  SYS+facet-CLASSIFIERS_map^classifier:
    SYS+classifier-CLASS&class:
      class-SIMPLE
  SYS+facet-CONTENT$str:
    "this is a test same line \ndifferent line"
  SYS+facet-DESCRIPTORS_map^descriptor:
    FEDTEST+descriptor-DEGREEofPOLISH$str:
      MIDDLING
    SYS+descriptor-REQUESTS_mapvec^requestid$str:
      FED+requestid-ADDrelations:
        - ADD_RELATIONSoperationid
      SYS+requestid-ADDdescriptors:
        - ADD_DESCRIPTORSoperationid
      SYS+requestid-GEMreport:
        - GEM_REPORToperationid
  SYS+facet-NAME&%:
    simple-ALPHA
  SYS+facet-REQUESTportSTACK_vec$chan:
    - clojure.core.async.chan
---
# Gem FEDTEST+simple-ALPHA

this is a test same line 
different line
