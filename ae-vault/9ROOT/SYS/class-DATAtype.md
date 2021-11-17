---
FACETS:
  DESCRIPTORS:
    descriptor-INVARIANT$bool: true
    descriptor_map-INSTANCE^classifier:
      classifier-ENTITYtype&classifierVALUE: classifierVALUE-DATAtype
    descriptor_map-INSTANCE^descriptor:
      descriptor-INVARIANT$bool: true
      descriptor_mapvec-REQUESTS^requestid$str:
        requestid-ENTITYreport:
        - ENTITY_REPORToperationid
        requestid-TYPEof:
        - operationidTYPE_OF
    descriptor_mapvec-REQUESTS^requestid$str:
      requestid-ENTITYreport:
      - ENTITY_REPORToperationid
      requestid-INSTANTIATE:
      - INSTANTIATEoperationid
  REQUEST-PORT-STACK:
  - clojure.core.async.chan
  facet-CLASSIFIERS^classifier:
    classifier-CLASS: class-CLASS
    classifier-ENTITYtype&classifierVALUE: classifierVALUE-CLASS
  facet-CONTENT$str: ''
  facet-NAME&?: class-DATAtype
---
# Entity SYS+class-DATAtype

