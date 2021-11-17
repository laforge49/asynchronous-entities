---
FACETS:
  DESCRIPTORS:
    SYS+descriptor_mapvec-REQUESTS^requestid$str:
      SYS+requestid-ADDdescriptors:
      - ADD_DESCRIPTORSoperationid
      SYS+requestid-ADDrelations:
      - ADD_RELATIONSoperationid
      SYS+requestid-ENTITYreport:
      - ENTITY_REPORToperationid
    descriptor-DEGREEofPOLISH$str: MIDDLING
  INVERSE-RELATIONS:
    relation_vec-BASIC:
    - simple-GAMMA
    - simple-BETA
  NAME: simple-ALPHA
  REQUEST-PORT-STACK:
  - clojure.core.async.chan
  SYS+aspect-CLASSIFIERS^classifier:
    SYS+classifier-CLASS: class-SIMPLE
    SYS+classifier-ENTITYtype&classifierVALUE: classifierVALUE-SIMPLE
  SYS+aspect-CONTENT$str: "this is a test same line \ndifferent line"
---
# Entity TEST+simple-ALPHA

this is a test same line 
different line
