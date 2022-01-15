---
gem_map-FACETS^facet:
  facet-CONTENT$str: ''
  facet-NAME&%: class-CONTEXT
  facet_map-CLASSIFIERS^classifier:
    classifier-CLASS&class: class-CLASS
  facet_map-DESCRIPTORS^descriptor:
    descriptor-ASYNC$bool: true
    descriptor-INVARIANT$bool: true
    descriptor_map-INSTANCE^descriptor:
      descriptor_mapvec-REQUESTS^requestid$str:
        requestid-ENTITYreport:
        - CONTEXT_REPORToperationid
        requestid-EVALscript:
        - EVAL_SCRIPToperationid
        requestid-LOADscript:
        - LOAD_SCRIPToperationid
        requestid-REGISTERentity:
        - REGISTER_ENTITYoperationid
        requestid-VALIDATEscriptNAMES:
        - VALIDATE_SCRIPT_NAMESoperationid
    descriptor_mapvec-REQUESTS^requestid$str:
      requestid-ENTITYreport:
      - ENTITY_REPORToperationid
      requestid-INSTANTIATE:
      - INSTANTIATEoperationid
  facet_vec-REQUESTportSTACK$chan:
  - clojure.core.async.chan
---
# Entity SYS+class-CONTEXT

