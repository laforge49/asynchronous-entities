---
FACETS:
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
  NAME: class-CLASS
  REQUEST-PORT-STACK:
  - clojure.core.async.chan
  aspect-CLASSIFIERS^classifier:
    classifier-ENTITYtype&classifierVALUE: classifierVALUE-CLASS
  aspect-CONTENT$str: ''
---
# Entity SYS+class-CLASS

