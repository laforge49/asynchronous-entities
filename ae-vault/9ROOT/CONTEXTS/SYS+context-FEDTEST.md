---
gem_map-FACETS^facet:
  SYS+facet-CLASSIFIERS_map^classifier:
    SYS+classifier-CLASS&class:
      class-CONTEXT
    SYS+classifier-RESOURCES_vec&context:
      - context-SYS
      - context-FED
  SYS+facet-CONTENT$str:
    This is a federation test.
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
          descriptor-DEGREEofPOLISH$str
        SYS+param-REQUESTID&requestid:
          requestid-INSTANTIATE
        SYS+param-TARGETname&%:
          class-DESCRIPTOR
      0020010 SYS+request-REQUEST_map^param:
        SYS+param-NAME&%:
          relation-BASIC_vec
        SYS+param-REQUESTID&requestid:
          requestid-INSTANTIATE
        SYS+param-TARGETname&%:
          class-RELATION
      0030000 SYS+request-REQUEST_map^param:
        SYS+param-DESCRIPTORS_map^descriptor:
          SYS+descriptor-INSTANCE_map^descriptor:
            SYS+descriptor-REQUESTS_mapvec^requestid$str:
              FED+requestid-ADDrelations:
                - ADD_RELATIONSoperationid
              SYS+requestid-ADDdescriptors:
                - ADD_DESCRIPTORSoperationid
              SYS+requestid-GEMreport:
                - GEM_REPORToperationid
        SYS+param-NAME&%:
          class-SIMPLE
        SYS+param-REQUESTID&requestid:
          requestid-INSTANTIATE
        SYS+param-TARGETname&%:
          class-CLASS
      0040010 SYS+request-REQUEST_map^param:
        SYS+param-CONTENT$str:
          "this is a test same line \ndifferent line"
        SYS+param-NAME&%:
          simple-ALPHA
        SYS+param-REQUESTID&requestid:
          requestid-INSTANTIATE
        SYS+param-TARGETname&%:
          class-SIMPLE
      0040020 SYS+request-REQUEST_map^param:
        SYS+param-NAME&%:
          simple-BETA
        SYS+param-REQUESTID&requestid:
          requestid-INSTANTIATE
        SYS+param-TARGETname&%:
          class-SIMPLE
      0500010 SYS+request-REQUEST_map^param:
        SYS+param-DESCRIPTORS_map^descriptor:
          FED+descriptor-FEDERATIONnames_vec&%:
            - simple-ALPHA
            - simple-BETA
          SYS+descriptor-SCRIPT_map^request:
            0000010 SYS+request-REQUEST_map^param:
              SYS+param-NAME&%:
                simple-GAMMA
              SYS+param-REQUESTID&requestid:
                requestid-INSTANTIATE
              SYS+param-TARGETname&%:
                class-SIMPLE
            0000020 SYS+request-REQUEST_map^param:
              SYS+param-DESCRIPTORS_map^descriptor:
                FEDTEST+descriptor-DEGREEofPOLISH$str:
                  MIDDLING
              SYS+param-REQUESTID&requestid:
                requestid-ADDdescriptors
              SYS+param-TARGETname&%:
                simple-ALPHA
            0000030 SYS+request-REQUEST_map^param:
              SYS+param-DESCRIPTORS_map^descriptor:
                FEDTEST+descriptor-DEGREEofPOLISH$str:
                  MIDDLING
              SYS+param-REQUESTID&requestid:
                requestid-ADDdescriptors
              SYS+param-TARGETname&%:
                simple-GAMMA
            0000040 SYS+request-REQUEST_map^param:
              FED+param-RELATIONS_map^relation&%:
                FEDTEST+relation-BASIC_vec:
                  - simple-ALPHA
              SYS+param-REQUESTID&requestid:
                requestid-ADDrelations
              SYS+param-TARGETname&%:
                simple-GAMMA
            0000050 SYS+request-REQUEST_map^param:
              FED+param-RELATIONS_map^relation&%:
                FEDTEST+relation-BASIC_vec:
                  - simple-ALPHA
              SYS+param-REQUESTID&requestid:
                requestid-ADDrelations
              SYS+param-TARGETname&%:
                simple-BETA
        SYS+param-NAME&%:
          federator-A
        SYS+param-REQUESTID&requestid:
          requestid-INSTANTIATE
        SYS+param-TARGETname&%:
          class-FEDERATOR
      0500020 SYS+request-REQUEST_map^param:
        SYS+param-REQUESTID&requestid:
          requestid-RUNfederation
        SYS+param-TARGETname&%:
          federator-A
      0500030 SYS+request-REQUEST_map^param:
        SYS+param-REQUESTID&requestid:
          requestid-PRINTLN
        SYS+param-TARGETname&%:
          context-SYS
        SYS+param-TEXT$str:
          Finished FEDTEST
  SYS+facet-NAME&%:
    context-FEDTEST
  SYS+facet-REQUESTportSTACK_vec$chan:
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
    entity:      FEDTEST+relation-BASIC_vec
  value:       FEDTEST+class-SIMPLE
    entity:      FEDTEST+simple-ALPHA
    entity:      FEDTEST+simple-BETA
    entity:      FEDTEST+simple-GAMMA
  value:       SYS+class-CLASS
    entity:      FEDTEST+class-SIMPLE
  value:       SYS+class-DESCRIPTOR
    entity:      FEDTEST+descriptor-DEGREEofPOLISH$str

Number of classifiers: 1

