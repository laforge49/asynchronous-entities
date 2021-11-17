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
  REQUEST-PORT-STACK:
  - clojure.core.async.chan
  facet-CLASSIFIERS^classifier:
    classifier-ENTITYtype&classifierVALUE: classifierVALUE-CLASS
  facet-CONTENT$str: ''
  facet-NAME&?: class-CLASS
---
# Entity SYS+class-CLASS

