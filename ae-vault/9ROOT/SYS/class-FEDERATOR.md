---
FACETS:
  DESCRIPTORS:
    descriptor-INVARIANT$bool: true
    descriptor_map-INSTANCE^classifier:
      classifier-ENTITYtype&classifierVALUE: classifierVALUE-FEDERATOR
    descriptor_map-INSTANCE^descriptor:
      descriptor_mapvec-REQUESTS^requestid$str:
        requestid-ENTITYreport:
        - ENTITY_REPORToperationid
        requestid-RUNfederation:
        - RUN_FEDERATIONoperationid
    descriptor_mapvec-REQUESTS^requestid$str:
      requestid-ENTITYreport:
      - ENTITY_REPORToperationid
      requestid-INSTANTIATE:
      - INSTANTIATEoperationid
  NAME: class-FEDERATOR
  REQUEST-PORT-STACK:
  - clojure.core.async.chan
  aspect-CLASSIFIERS^classifier:
    classifier-CLASS: class-CLASS
    classifier-ENTITYtype&classifierVALUE: classifierVALUE-CLASS
  aspect-CONTENT$str: ''
---
# Entity SYS+class-FEDERATOR

