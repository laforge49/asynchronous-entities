---
FACETS:
  DESCRIPTORS:
    descriptor-INVARIANT$bool: true
    descriptor_map-INSTANCE^classifier:
      classifier-ENTITYtype&classifierVALUE: classifierVALUE-CONTEXT
    descriptor_map-INSTANCE^descriptor:
      descriptor_mapvec-REQUESTS^requestid$str:
        requestid-ENTITYreport:
        - CONTEXT_REPORToperationid
        requestid-LOADscript:
        - LOAD_SCRIPToperationid
        requestid-REGISTERentity:
        - REGISTER_ENTITYoperationid
        requestid-ROUTE:
        - ROUTEoperationid
    descriptor_mapvec-REQUESTS^requestid$str:
      requestid-ENTITYreport:
      - ENTITY_REPORToperationid
      requestid-INSTANTIATE:
      - INSTANTIATEoperationid
  NAME: class-CONTEXT
  REQUEST-PORT-STACK:
  - clojure.core.async.chan
  aspect-CLASSIFIERS^classifier:
    classifier-CLASS: class-CLASS
    classifier-ENTITYtype&classifierVALUE: classifierVALUE-CLASS
  aspect-CONTENT$str: ''
---
# Entity SYS+class-CONTEXT

