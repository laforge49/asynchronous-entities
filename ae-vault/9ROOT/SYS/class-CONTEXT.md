---
gem_map-FACETS^facet:
  SYS+facet-CONTENT$str:
    ''
  SYS+facet-NAME&%:
    class-CONTEXT
  SYS+facet_map-CLASSIFIERS^classifier:
    SYS+classifier-CLASS&class:
      class-CLASS
  SYS+facet_map-DESCRIPTORS^descriptor:
    SYS+descriptor-INVARIANT$bool:
      true
    SYS+descriptor_map-INSTANCE^descriptor:
      SYS+descriptor_mapvec-REQUESTS^requestid$str:
        SYS+requestid-EVALscript:
          - EVAL_SCRIPToperationid
        SYS+requestid-GEMreport:
          - CONTEXT_REPORToperationid
        SYS+requestid-LOADscript:
          - LOAD_SCRIPToperationid
        SYS+requestid-REGISTERgem:
          - REGISTER_GEMoperationid
        SYS+requestid-VALIDATEscriptNAMES:
          - VALIDATE_SCRIPT_NAMESoperationid
    SYS+descriptor_mapvec-REQUESTS^requestid$str:
      SYS+requestid-GEMreport:
        - GEM_REPORToperationid
      SYS+requestid-INSTANTIATE:
        - INSTANTIATEoperationid
---
# Gem SYS+class-CONTEXT

