---
gem_map-FACETS^facet:
  facet-CONTENT$str:
    ''
  facet-NAME&%:
    class-CONTEXT
  facet_map-CLASSIFIERS^classifier:
    classifier-CLASS&class:
      class-CLASS
  facet_map-DESCRIPTORS^descriptor:
    descriptor-INVARIANT$bool:
      true
    descriptor_map-INSTANCE^descriptor:
      descriptor_mapvec-REQUESTS^requestid$str:
        requestid-EVALscript:
          - EVAL_SCRIPToperationid
        requestid-GEMreport:
          - CONTEXT_REPORToperationid
        requestid-LOADscript:
          - LOAD_SCRIPToperationid
        requestid-REGISTERentity:
          - REGISTER_ENTITYoperationid
        requestid-VALIDATEscriptNAMES:
          - VALIDATE_SCRIPT_NAMESoperationid
    descriptor_mapvec-REQUESTS^requestid$str:
      requestid-GEMreport:
        - GEM_REPORToperationid
      requestid-INSTANTIATE:
        - INSTANTIATEoperationid
---
# Gem SYS+class-CONTEXT

