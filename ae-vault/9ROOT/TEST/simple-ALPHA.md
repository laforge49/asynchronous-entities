---
SYS+gem-FACETS^facet:
  REQUEST-PORT-STACK:
  - clojure.core.async.chan
  SYS+facet-CONTENT$str: "this is a test same line \ndifferent line"
  SYS+facet-NAME&?: simple-ALPHA
  SYS+facet_map-CLASSIFIERS^classifier:
    SYS+classifier-CLASS: class-SIMPLE
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
  SYS+facet_map-INVERSErelations^relation&?:
    relation_vec-BASIC:
    - simple-GAMMA
    - simple-BETA
---
# Entity TEST+simple-ALPHA

this is a test same line 
different line
