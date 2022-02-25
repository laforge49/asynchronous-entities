---
SYS+gem-FACETS_map^facet:
  SYS+facet-CLASSIFIERS_map^classifier:
    SYS+classifier-CLASS&class:
      class-CLASS
  SYS+facet-CONTENT$str:
    ''
  SYS+facet-DESCRIPTORS_map^descriptor:
    SYS+descriptor-INSTANCE_map^descriptor:
      SYS+descriptor-REQUESTS_mapvec^requestid$str:
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
    SYS+descriptor-INVARIANT$bool:
      true
    SYS+descriptor-REQUESTS_mapvec^requestid$str:
      SYS+requestid-GEMreport:
        - GEM_REPORToperationid
      SYS+requestid-INSTANTIATE:
        - INSTANTIATEoperationid
  SYS+facet-NAME&%:
    class-CONTEXT
---
# Gem SYS+class-CONTEXT

