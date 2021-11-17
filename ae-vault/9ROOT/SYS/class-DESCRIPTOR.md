---
FACETS:
  DESCRIPTORS:
    descriptor-INVARIANT$bool: true
    descriptor_map-INSTANCE^classifier:
      classifier-ENTITYtype&classifierVALUE: classifierVALUE-DESCRIPTOR
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
  NAME: class-DESCRIPTOR
  REQUEST-PORT-STACK:
  - clojure.core.async.chan
  aspect-CLASSIFIERS^classifier:
    classifier-CLASS: class-CLASS
    classifier-ENTITYtype&classifierVALUE: classifierVALUE-CLASS
  aspect-CONTENT$str: ''
---
# Entity SYS+class-DESCRIPTOR

