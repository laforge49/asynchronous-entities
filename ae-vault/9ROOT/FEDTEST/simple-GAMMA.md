---
gem_map-FACETS^facet:
  FED+facet_map-RELATIONS^relation&%:
    FEDTEST+relation_vec-BASIC:
      - simple-ALPHA
  SYS+facet-CONTENT$str:
    ''
  SYS+facet-NAME&%:
    simple-GAMMA
  SYS+facet_map-CLASSIFIERS^classifier:
    SYS+classifier-CLASS&class:
      class-SIMPLE
  SYS+facet_map-DESCRIPTORS^descriptor:
    FEDTEST+descriptor-DEGREEofPOLISH$str:
      MIDDLING
    SYS+descriptor_mapvec-REQUESTS^requestid$str:
      FED+requestid-ADDrelations:
        - ADD_RELATIONSoperationid
      SYS+requestid-ADDdescriptors:
        - ADD_DESCRIPTORSoperationid
      SYS+requestid-GEMreport:
        - GEM_REPORToperationid
  SYS+facet_vec-REQUESTportSTACK$chan:
    - clojure.core.async.chan
---
# Gem FEDTEST+simple-GAMMA

