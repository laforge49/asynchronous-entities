---
SYS+gem-FACETS_map^facet:
  FED+facet-RELATIONS_map^relation&%:
    FEDTEST+relation-BASIC_vec:
      - simple-ALPHA
  SYS+facet-CLASSIFIERS_map^classifier:
    SYS+classifier-CLASS&class:
      class-SIMPLE
  SYS+facet-CONTENT$str:
    ''
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
    simple-GAMMA
  SYS+facet-REQUESTportSTACK_vec$chan:
    - clojure.core.async.chan
---
# Gem FEDTEST+simple-GAMMA

