---
SYS+gem-FACETS_map^facet:
  SYS+facet-CLASSIFIERS_map^classifier:
    SYS+classifier-CLASS&class:
      class-CONTEXT
    SYS+classifier-RESOURCES_vec&context:
      - context-SYS
  SYS+facet-CONTENT$str:
    This is context FED.
  SYS+facet-DESCRIPTORS_map^descriptor:
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
    SYS+descriptor-SCRIPT_map^request:
      0010010 SYS+request-REQUEST_map^param:
        SYS+param-NAME&%:
          descriptor-FEDERATIONnames
        SYS+param-REQUESTID&requestid:
          requestid-INSTANTIATE
        SYS+param-TARGETname&%:
          class-DESCRIPTOR
      0020010 SYS+request-REQUEST_map^param:
        SYS+param-NAME&%:
          facet-INVERSErelations_map^relation&%
        SYS+param-REQUESTID&requestid:
          requestid-INSTANTIATE
        SYS+param-TARGETname&%:
          class-FACETmap
      0030010 SYS+request-REQUEST_map^param:
        SYS+param-NAME&%:
          facet-RELATIONS_map^relation&%
        SYS+param-REQUESTID&requestid:
          requestid-INSTANTIATE
        SYS+param-TARGETname&%:
          class-FACETmap
      0040000 SYS+request-REQUEST_map^param:
        SYS+param-DESCRIPTORS_map^descriptor:
          SYS+descriptor-INSTANCE_map^descriptor:
            SYS+descriptor-REQUESTS_mapvec^requestid$str:
              FED+requestid-RUNfederation:
                - RUN_FEDERATIONoperationid
              SYS+requestid-GEMreport:
                - GEM_REPORToperationid
        SYS+param-NAME&%:
          class-FEDERATOR
        SYS+param-REQUESTID&requestid:
          requestid-INSTANTIATE
        SYS+param-TARGETname&%:
          class-CLASS
      0050000 SYS+request-REQUEST_map^param:
        SYS+param-DESCRIPTORS_map^descriptor:
          SYS+descriptor-INSTANCE_map^descriptor:
            SYS+descriptor-INVARIANT$bool:
              true
            SYS+descriptor-REQUESTS_mapvec^requestid$str:
              SYS+requestid-GEMreport:
                - GEM_REPORToperationid
        SYS+param-NAME&%:
          class-RELATION
        SYS+param-REQUESTID&requestid:
          requestid-INSTANTIATE
        SYS+param-TARGETname&%:
          class-CLASS
      0060010 SYS+request-REQUEST_map^param:
        SYS+param-NAME&%:
          param-RELATIONS_map^relation&%
        SYS+param-REQUESTID&requestid:
          requestid-INSTANTIATE
        SYS+param-TARGETname&%:
          class-PARAMmap
      0070010 SYS+request-REQUEST_map^param:
        SYS+param-NAME&%:
          requestid-ADDrelations
        SYS+param-REQUESTID&requestid:
          requestid-INSTANTIATE
        SYS+param-TARGETname&%:
          class-REQUESTID
      0070020 SYS+request-REQUEST_map^param:
        SYS+param-DESCRIPTORS_map^descriptor:
          SYS+descriptor-READonly$bool:
            true
        SYS+param-NAME&%:
          requestid-RUNfederation
        SYS+param-REQUESTID&requestid:
          requestid-INSTANTIATE
        SYS+param-TARGETname&%:
          class-REQUESTID
      0200110 SYS+request-REQUEST_map^param:
        SYS+param-REQUESTID&requestid:
          requestid-LOADscript
        SYS+param-TARGETname&%:
          context-FEDTEST
      0200120 SYS+request-REQUEST_map^param:
        SYS+param-REQUESTID&requestid:
          requestid-EVALscript
        SYS+param-TARGETname&%:
          context-FEDTEST
      0200130 SYS+request-REQUEST_map^param:
        SYS+param-REQUESTID&requestid:
          requestid-VALIDATEscriptNAMES
        SYS+param-TARGETname&%:
          context-FEDTEST
      0500010 SYS+request-REQUEST_map^param:
        SYS+param-REQUESTID&requestid:
          requestid-PRINTLN
        SYS+param-TARGETname&%:
          context-SYS
        SYS+param-TEXT$str:
          Finished FED
  SYS+facet-NAME&%:
    context-FED
  SYS+facet-REQUESTportSTACK_vec$chan:
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
  value:       SYS+class-DESCRIPTOR
    entity:      FED+descriptor-FEDERATIONnames
  value:       SYS+class-FACETmap
    entity:      FED+facet-INVERSErelations_map^relation&%
    entity:      FED+facet-RELATIONS_map^relation&%
  value:       SYS+class-PARAMmap
    entity:      FED+param-RELATIONS_map^relation&%
  value:       SYS+class-REQUESTID
    entity:      FED+requestid-ADDrelations
    entity:      FED+requestid-RUNfederation

Number of classifiers: 1

