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
  NAME: simple-BETA
  RELATIONS:
    relation_vec-BASIC:
    - simple-ALPHA
  REQUEST-PORT-STACK:
  - clojure.core.async.chan
  SYS+aspect-CLASSIFIERS^classifier:
    SYS+classifier-CLASS: class-SIMPLE
    SYS+classifier-ENTITYtype&classifierVALUE: classifierVALUE-SIMPLE
  SYS+aspect-CONTENT$str: ''
---
# Entity TEST+simple-BETA

