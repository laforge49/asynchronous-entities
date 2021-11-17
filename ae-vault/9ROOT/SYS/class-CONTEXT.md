---
gem-FACETS^facet:
  REQUEST-PORT-STACK:
  - clojure.core.async.chan
  facet-CONTENT$str: ''
  facet-NAME&?: class-CONTEXT
  facet_map-CLASSIFIERS^classifier:
    classifier-CLASS: class-CLASS
    classifier-ENTITYtype&classifierVALUE: classifierVALUE-CLASS
  facet_map-DESCRIPTORS^descriptor:
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
---
# Entity SYS+class-CONTEXT

