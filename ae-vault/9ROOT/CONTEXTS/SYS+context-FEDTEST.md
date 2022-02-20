---
gem_map-FACETS^facet:
  facet-CONTENT$str:
    This is a federation test.
  facet-NAME&%:
    context-FEDTEST
  facet_map-CLASSIFIERS^classifier:
    classifier-CLASS&class:
      class-CONTEXT
    classifier_vec-RESOURCES&context:
      - context-SYS
- context-FED
  facet_map-DESCRIPTORS^descriptor:
    descriptor_map-SCRIPT^request:
      0010010 request_map-REQUEST^param:
        param-NAME&%:
          descriptor-DEGREEofPOLISH$str
        param-REQUESTID&requestid:
          requestid-INSTANTIATE
        param-TARGETname&%:
          class-DESCRIPTOR
      0020010 request_map-REQUEST^param:
        param-NAME&%:
          relation_vec-BASIC
        param-REQUESTID&requestid:
          requestid-INSTANTIATE
        param-TARGETname&%:
          class-RELATION
      0030000 request_map-REQUEST^param:
        param-NAME&%:
          class-SIMPLE
        param-REQUESTID&requestid:
          requestid-INSTANTIATE
        param-TARGETname&%:
          class-CLASS
        param_map-DESCRIPTORS^descriptor:
          descriptor_map-INSTANCE^descriptor:
            descriptor_mapvec-REQUESTS^requestid$str:
              requestid-ADDdescriptors:
                - ADD_DESCRIPTORSoperationid
              requestid-ADDrelations:
                - ADD_RELATIONSoperationid
              requestid-ENTITYreport:
                - GEM_REPORToperationid
      0040010 request_map-REQUEST^param:
        param-CONTENT$str:
          "this is a test same line \ndifferent line"
        param-NAME&%:
          simple-ALPHA
        param-REQUESTID&requestid:
          requestid-INSTANTIATE
        param-TARGETname&%:
          class-SIMPLE
      0040020 request_map-REQUEST^param:
        param-NAME&%:
          simple-BETA
        param-REQUESTID&requestid:
          requestid-INSTANTIATE
        param-TARGETname&%:
          class-SIMPLE
      0500010 request_map-REQUEST^param:
        param-NAME&%:
          federator-A
        param-REQUESTID&requestid:
          requestid-INSTANTIATE
        param-TARGETname&%:
          class-FEDERATOR
        param_map-DESCRIPTORS^descriptor:
          descriptor_map-SCRIPT^request:
            0000010 request_map-REQUEST^param:
              param-NAME&%:
                simple-GAMMA
              param-REQUESTID&requestid:
                requestid-INSTANTIATE
              param-TARGETname&%:
                class-SIMPLE
          descriptor_vec-FEDERATIONnames&%:
            - simple-ALPHA
- simple-BETA
      0500020 request_map-REQUEST^param:
        param-REQUESTID&requestid:
          requestid-RUNfederation
        param-TARGETname&%:
          federator-A
      0500030 request_map-REQUEST^param:
        param-REQUESTID&requestid:
          requestid-PRINTLN
        param-TARGETname&%:
          context-SYS
        param-TEXT$str:
          Finished FEDTEST
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
# Gem SYS+context-FEDTEST

This is a federation test.

---
1. Classifier Values of context SYS+context-FEDTEST
(Default context is context-FEDTEST.)

classifier:  SYS+classifier-CLASS&class
  value:       FED+class-FEDERATOR
    entity:      FEDTEST+federator-A
  value:       FED+class-RELATION
    entity:      FEDTEST+relation_vec-BASIC
  value:       FEDTEST+class-SIMPLE
    entity:      FEDTEST+simple-ALPHA
    entity:      FEDTEST+simple-BETA
    entity:      FEDTEST+simple-GAMMA
  value:       SYS+class-CLASS
    entity:      FEDTEST+class-SIMPLE
  value:       SYS+class-DESCRIPTOR
    entity:      FEDTEST+descriptor-DEGREEofPOLISH$str

Number of classifiers: 1

