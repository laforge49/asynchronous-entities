---
FACETS:
  CLASSIFIERS:
    SYS+classifier-CLASS: class-SIMPLE
    SYS+classifier-ENTITYtype&classifierVALUE: classifierVALUE-SIMPLE
  CONTENT$ml: "this is a test same line \ndifferent line"
  DESCRIPTORS:
    SYS+descriptor_mapvec-REQUESTS^requestid$str:
      SYS+requestid-ADDdescriptors:
      - ADD_DESCRIPTORSoperationid
      SYS+requestid-ADDrelations:
      - ADD_RELATIONSoperationid
      SYS+requestid-ENTITYreport:
      - ENTITY_REPORToperationid
    descriptor-DEGREEofPOLISH$str: MIDDLING
  FEDERATOR-NAME: nil
  INVERSE-RELATIONS:
    relation_vec-BASIC:
    - simple-GAMMA
    - simple-BETA
  NAME: simple-ALPHA
  RELATIONS: {}
  REQUEST-PORT-STACK:
  - clojure.core.async.chan
---
# Entity TEST+simple-ALPHA

this is a test same line 
different line
