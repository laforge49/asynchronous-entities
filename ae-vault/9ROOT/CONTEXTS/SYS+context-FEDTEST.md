---
gem_map-FACETS^facet:
  SYS+facet-CONTENT$str:
    This is a federation test.
  SYS+facet-NAME&%:
    context-FEDTEST
  SYS+facet_map-CLASSIFIERS^classifier:
    SYS+classifier-CLASS&class:
      class-CONTEXT
    SYS+classifier_vec-RESOURCES&context:
      - context-SYS
      - context-FED
  SYS+facet_map-DESCRIPTORS^descriptor:
    SYS+descriptor_map-SCRIPT^request:
      0010010 SYS+request_map-REQUEST^param:
        SYS+param-NAME&%:
          descriptor-DEGREEofPOLISH$str
        SYS+param-REQUESTID&requestid:
          requestid-INSTANTIATE
        SYS+param-TARGETname&%:
          class-DESCRIPTOR
      0020010 SYS+request_map-REQUEST^param:
        SYS+param-NAME&%:
          relation_vec-BASIC
        SYS+param-REQUESTID&requestid:
          requestid-INSTANTIATE
        SYS+param-TARGETname&%:
          class-RELATION
      0030000 SYS+request_map-REQUEST^param:
        SYS+param-NAME&%:
          class-SIMPLE
        SYS+param-REQUESTID&requestid:
          requestid-INSTANTIATE
        SYS+param-TARGETname&%:
          class-CLASS
        SYS+param_map-DESCRIPTORS^descriptor:
          SYS+descriptor_map-INSTANCE^descriptor:
            SYS+descriptor_mapvec-REQUESTS^requestid$str:
              FED+requestid-ADDrelations:
                - ADD_RELATIONSoperationid
              SYS+requestid-ADDdescriptors:
                - ADD_DESCRIPTORSoperationid
              SYS+requestid-GEMreport:
                - GEM_REPORToperationid
      0040010 SYS+request_map-REQUEST^param:
        SYS+param-CONTENT$str:
          "this is a test same line \ndifferent line"
        SYS+param-NAME&%:
          simple-ALPHA
        SYS+param-REQUESTID&requestid:
          requestid-INSTANTIATE
        SYS+param-TARGETname&%:
          class-SIMPLE
      0040020 SYS+request_map-REQUEST^param:
        SYS+param-NAME&%:
          simple-BETA
        SYS+param-REQUESTID&requestid:
          requestid-INSTANTIATE
        SYS+param-TARGETname&%:
          class-SIMPLE
      0500010 SYS+request_map-REQUEST^param:
        SYS+param-NAME&%:
          federator-A
        SYS+param-REQUESTID&requestid:
          requestid-INSTANTIATE
        SYS+param-TARGETname&%:
          class-FEDERATOR
        SYS+param_map-DESCRIPTORS^descriptor:
          FED+descriptor_vec-FEDERATIONnames&%:
            - simple-ALPHA
            - simple-BETA
          SYS+descriptor_map-SCRIPT^request:
            0000010 SYS+request_map-REQUEST^param:
              SYS+param-NAME&%:
                simple-GAMMA
              SYS+param-REQUESTID&requestid:
                requestid-INSTANTIATE
              SYS+param-TARGETname&%:
                class-SIMPLE
      0500020 SYS+request_map-REQUEST^param:
        SYS+param-REQUESTID&requestid:
          requestid-RUNfederation
        SYS+param-TARGETname&%:
          federator-A
      0500030 SYS+request_map-REQUEST^param:
        SYS+param-REQUESTID&requestid:
          requestid-PRINTLN
        SYS+param-TARGETname&%:
          context-SYS
        SYS+param-TEXT$str:
          Finished FEDTEST
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

