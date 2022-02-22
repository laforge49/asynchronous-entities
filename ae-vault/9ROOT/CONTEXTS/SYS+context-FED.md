---
gem_map-FACETS^facet:
  SYS+facet-CONTENT$str:
    This is context FED.
  SYS+facet-NAME&%:
    context-FED
  SYS+facet_map-CLASSIFIERS^classifier:
    SYS+classifier-CLASS&class:
      class-CONTEXT
    SYS+classifier_vec-RESOURCES&context:
      - context-SYS
  SYS+facet_map-DESCRIPTORS^descriptor:
    SYS+descriptor_map-SCRIPT^request:
      0010010 SYS+request_map-REQUEST^param:
        SYS+param-NAME&%:
          descriptor_vec-FEDERATIONnames&%
        SYS+param-REQUESTID&requestid:
          requestid-INSTANTIATE
        SYS+param-TARGETname&%:
          class-DESCRIPTORvec
      0020000 SYS+request_map-REQUEST^param:
        SYS+param-NAME&%:
          class-FEDERATOR
        SYS+param-REQUESTID&requestid:
          requestid-INSTANTIATE
        SYS+param-TARGETname&%:
          class-CLASS
        SYS+param_map-DESCRIPTORS^descriptor:
          SYS+descriptor_map-INSTANCE^descriptor:
            SYS+descriptor_mapvec-REQUESTS^requestid$str:
              FED+requestid-RUNfederation:
                - RUN_FEDERATIONoperationid
              SYS+requestid-GEMreport:
                - GEM_REPORToperationid
      0030000 SYS+request_map-REQUEST^param:
        SYS+param-NAME&%:
          class-RELATION
        SYS+param-REQUESTID&requestid:
          requestid-INSTANTIATE
        SYS+param-TARGETname&%:
          class-CLASS
        SYS+param_map-DESCRIPTORS^descriptor:
          SYS+descriptor_map-INSTANCE^descriptor:
            SYS+descriptor-INVARIANT$bool:
              true
            SYS+descriptor_mapvec-REQUESTS^requestid$str:
              SYS+requestid-GEMreport:
                - GEM_REPORToperationid
      0040010 SYS+request_map-REQUEST^param:
        SYS+param-NAME&%:
          requestid-ADDrelations
        SYS+param-REQUESTID&requestid:
          requestid-INSTANTIATE
        SYS+param-TARGETname&%:
          class-REQUESTID
      0040020 SYS+request_map-REQUEST^param:
        SYS+param-NAME&%:
          requestid-RUNfederation
        SYS+param-REQUESTID&requestid:
          requestid-INSTANTIATE
        SYS+param-TARGETname&%:
          class-REQUESTID
        SYS+param_map-DESCRIPTORS^descriptor:
          SYS+descriptor-READonly$bool:
            true
      0200110 SYS+request_map-REQUEST^param:
        SYS+param-REQUESTID&requestid:
          requestid-LOADscript
        SYS+param-TARGETname&%:
          context-FEDTEST
      0200120 SYS+request_map-REQUEST^param:
        SYS+param-REQUESTID&requestid:
          requestid-EVALscript
        SYS+param-TARGETname&%:
          context-FEDTEST
      0200130 SYS+request_map-REQUEST^param:
        SYS+param-REQUESTID&requestid:
          requestid-VALIDATEscriptNAMES
        SYS+param-TARGETname&%:
          context-FEDTEST
      0500010 SYS+request_map-REQUEST^param:
        SYS+param-REQUESTID&requestid:
          requestid-PRINTLN
        SYS+param-TARGETname&%:
          context-SYS
        SYS+param-TEXT$str:
          Finished FED
    SYS+descriptor_mapvec-REQUESTS^requestid$str:
      SYS+requestid-EVALscript:
        - EVAL_SCRIPToperationid
      SYS+requestid-GEMreport:
        - CONTEXT_REPORToperationid
      SYS+requestid-LOADscript:
        - LOAD_SCRIPToperationid
      SYS+requestid-REGISTERgem:
        - REGISTER_ENTITYoperationid
      SYS+requestid-VALIDATEscriptNAMES:
        - VALIDATE_SCRIPT_NAMESoperationid
  SYS+facet_vec-REQUESTportSTACK$chan:
    - clojure.core.async.chan
---
# Gem SYS+context-FED

This is context FED.

---
1. Classifier Values of context SYS+context-FED
(Default context is context-FED.)

classifier:  SYS+classifier-CLASS&class
  value:       SYS+class-CLASS
    entity:      FED+class-FEDERATOR
    entity:      FED+class-RELATION
  value:       SYS+class-DESCRIPTORvec
    entity:      FED+descriptor_vec-FEDERATIONnames&%
  value:       SYS+class-REQUESTID
    entity:      FED+requestid-ADDrelations
    entity:      FED+requestid-RUNfederation

Number of classifiers: 1

