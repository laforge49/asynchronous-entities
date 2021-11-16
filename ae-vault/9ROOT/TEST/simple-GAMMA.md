---
FACETS:
  CLASSIFIERS:
    SYS+classifier-CLASS: class-SIMPLE
    SYS+classifier-ENTITYtype&classifierVALUE: classifierVALUE-SIMPLE
  CONTENT$ml: ''
  DESCRIPTORS:
    SYS+descriptor_mapvec-REQUESTS^requestid$str:
      SYS+requestid-ADDdescriptors:
      - ADD_DESCRIPTORSoperationid
      SYS+requestid-ADDrelations:
      - ADD_RELATIONSoperationid
      SYS+requestid-ENTITYreport:
      - ENTITY_REPORToperationid
    descriptor-DEGREEofPOLISH$str: MIDDLING
  NAME: simple-GAMMA
  RELATIONS:
    relation_vec-BASIC:
    - simple-ALPHA
  REQUEST-PORT-STACK:
  - clojure.core.async.chan
---
# Entity TEST+simple-GAMMA

