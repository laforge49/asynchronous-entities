---
gem_map-FACETS^facet:
  facet-CONTENT$str: ''
  facet-NAME&%: class-DESCRIPTORvecmap
  facet_map-CLASSIFIERS^classifier:
    classifier-CLASS&class: class-CLASS
  facet_map-DESCRIPTORS^descriptor:
    descriptor-INVARIANT$bool: true
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
  facet_vec-REQUESTportSTACK$chan:
  - clojure.core.async.chan
---
# Entity SYS+class-DESCRIPTORvecmap

