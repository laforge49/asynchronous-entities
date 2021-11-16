---
FACETS:
  CLASSIFIERS:
    classifier-ENTITYtype&classifierVALUE: classifierVALUE-CLASS
  CONTENT$ml: ''
  DESCRIPTORS:
    descriptor-INVARIANT$bool: true
    descriptor_map-INSTANCE^classifier:
      classifier-ENTITYtype&classifierVALUE: classifierVALUE-CLASS
    descriptor_map-INSTANCE^descriptor:
      descriptor-INVARIANT$bool: true
      descriptor_mapvec-REQUESTS^requestid$str:
        requestid-ENTITYreport:
        - ENTITY_REPORToperationid
        requestid-INSTANTIATE:
        - INSTANTIATEoperationid
    descriptor_mapvec-REQUESTS^requestid$str:
      requestid-ENTITYreport:
      - ENTITY_REPORToperationid
      requestid-INSTANTIATE:
      - INSTANTIATEoperationid
  INVERSE-RELATIONS: {}
  NAME: class-CLASS
  RELATIONS: {}
  REQUEST-PORT-STACK:
  - clojure.core.async.chan
---
# Entity SYS+class-CLASS

