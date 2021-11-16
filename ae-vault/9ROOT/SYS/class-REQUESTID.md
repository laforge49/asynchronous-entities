---
FACETS:
  CLASSIFIERS:
    classifier-CLASS: class-CLASS
    classifier-ENTITYtype&classifierVALUE: classifierVALUE-CLASS
  CONTENT$ml: ''
  DESCRIPTORS:
    descriptor-INVARIANT$bool: true
    descriptor_map-INSTANCE^classifier:
      classifier-ENTITYtype&classifierVALUE: classifierVALUE-REQUESTID
    descriptor_map-INSTANCE^descriptor:
      descriptor-INVARIANT$bool: true
      descriptor_mapvec-REQUESTS^requestid$str:
        requestid-ENTITYreport:
        - ENTITY_REPORToperationid
    descriptor_mapvec-REQUESTS^requestid$str:
      requestid-ENTITYreport:
      - ENTITY_REPORToperationid
      requestid-INSTANTIATE:
      - INSTANTIATEoperationid
  INVERSE-RELATIONS: {}
  NAME: class-REQUESTID
  RELATIONS: {}
  REQUEST-PORT-STACK:
  - clojure.core.async.chan
---
# Entity SYS+class-REQUESTID

