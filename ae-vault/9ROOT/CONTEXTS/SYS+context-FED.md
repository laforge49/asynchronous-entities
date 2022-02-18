---
gem_map-FACETS^facet:
  facet-CONTENT$str:
    This is context FED.
  facet-NAME&%:
    context-FED
  facet_map-CLASSIFIERS^classifier:
    classifier-CLASS&class:
      class-CONTEXT
    classifier_vec-RESOURCES&context:
      - context-SYS
  facet_map-DESCRIPTORS^descriptor:
    descriptor_map-SCRIPT^request:
      0010010 request_map-REQUEST^param:
        param-NAME&%:
          descriptor_vec-FEDERATIONnames&%
        param-REQUESTID&requestid:
          requestid-INSTANTIATE
        param-TARGETname&%:
          class-DESCRIPTORvec
      0020000 request_map-REQUEST^param:
        param-NAME&%:
          class-FEDERATOR
        param-REQUESTID&requestid:
          requestid-INSTANTIATE
        param-TARGETname&%:
          class-CLASS
        param_map-DESCRIPTORS^descriptor:
          descriptor_map-INSTANCE^descriptor:
            descriptor_mapvec-REQUESTS^requestid$str:
              requestid-ENTITYreport:
                - ENTITY_REPORToperationid
              requestid-RUNfederation:
                - RUN_FEDERATIONoperationid
      0030000 request_map-REQUEST^param:
        param-NAME&%:
          class-RELATION
        param-REQUESTID&requestid:
          requestid-INSTANTIATE
        param-TARGETname&%:
          class-CLASS
        param_map-DESCRIPTORS^descriptor:
          descriptor_map-INSTANCE^descriptor:
            descriptor-INVARIANT$bool:
              true
            descriptor_mapvec-REQUESTS^requestid$str:
              requestid-ENTITYreport:
                - ENTITY_REPORToperationid
      0040010 request_map-REQUEST^param:
        param-NAME&%:
          requestid-ADDrelations
        param-REQUESTID&requestid:
          requestid-INSTANTIATE
        param-TARGETname&%:
          class-REQUESTID
      0040020 request_map-REQUEST^param:
        param-NAME&%:
          requestid-RUNfederation
        param-REQUESTID&requestid:
          requestid-INSTANTIATE
        param-TARGETname&%:
          class-REQUESTID
        param_map-DESCRIPTORS^descriptor:
          descriptor-READonly$bool:
            true
      0200110 request_map-REQUEST^param:
        param-REQUESTID&requestid:
          requestid-LOADscript
        param-TARGETname&%:
          context-FEDTEST
      0200120 request_map-REQUEST^param:
        param-REQUESTID&requestid:
          requestid-EVALscript
        param-TARGETname&%:
          context-FEDTEST
      0200130 request_map-REQUEST^param:
        param-REQUESTID&requestid:
          requestid-VALIDATEscriptNAMES
        param-TARGETname&%:
          context-FEDTEST
      0500010 request_map-REQUEST^param:
        param-REQUESTID&requestid:
          requestid-PRINTLN
        param-TARGETname&%:
          context-SYS
        param-TEXT$str:
          Finished FED
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
  facet_vec-REQUESTportSTACK$chan:
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

