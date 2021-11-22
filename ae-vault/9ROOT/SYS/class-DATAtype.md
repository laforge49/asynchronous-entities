---
gem_map-FACETS^facet:
  facet-CONTENT$str: ''
  facet-NAME&?: class-DATAtype
  facet_map-CLASSIFIERS^classifier:
    classifier-CLASS&class: class-CLASS
    classifier-ENTITYtype&classifierVALUE: classifierVALUE-CLASS
  facet_map-DESCRIPTORS^descriptor:
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
  facet_vec-REQUESTportSTACK$chan:
  - clojure.core.async.chan
---
# Entity SYS+class-DATAtype

