---
SYS+gem_map-FACETS^facet:
  SYS+facet-CONTENT$str: ''
  SYS+facet-NAME&?: simple-GAMMA
  SYS+facet_map-CLASSIFIERS^classifier:
    SYS+classifier-CLASS&class: class-SIMPLE
    SYS+classifier-ENTITYtype&classifierVALUE: classifierVALUE-SIMPLE
  SYS+facet_map-DESCRIPTORS^descriptor:
    SYS+descriptor_mapvec-REQUESTS^requestid$str:
      SYS+requestid-ADDdescriptors:
      - ADD_DESCRIPTORSoperationid
      SYS+requestid-ADDrelations:
      - ADD_RELATIONSoperationid
      SYS+requestid-ENTITYreport:
      - ENTITY_REPORToperationid
    descriptor-DEGREEofPOLISH$str: MIDDLING
  SYS+facet_map-RELATIONS^relation&?:
    relation_vec-BASIC:
    - simple-ALPHA
  SYS+facet_vec-REQUESTportSTACK$chan:
  - clojure.core.async.chan
---
# Entity TEST+simple-GAMMA

