---
gem_map-FACETS^facet:
  facet-CONTENT$str: "this is a test same line \ndifferent line"
  facet-NAME&%: context-TEST
  facet_map-CLASSIFIERS^classifier:
    classifier-CLASS&class: class-CONTEXT
    classifier_vec-RESOURCES&context:
    - context-SYS
  facet_map-DESCRIPTORS^descriptor:
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
    descriptor_vecmap-SCRIPT^request:
    - request_map-REQUEST^param:
        param-NAME&%: class-SIMPLE
        param-REQUESTID&requestid: requestid-INSTANTIATE
        param-TARGETname&%: class-CLASS
        param_map-DESCRIPTORS^descriptor:
          descriptor_map-INSTANCE^descriptor:
            descriptor_mapvec-REQUESTS^requestid$str:
              requestid-ADDdescriptors:
              - ADD_DESCRIPTORSoperationid
              requestid-ADDrelations:
              - ADD_RELATIONSoperationid
              requestid-ENTITYreport:
              - ENTITY_REPORToperationid
    - request_map-REQUEST^param:
        param-CONTENT$str: "this is a test same line \ndifferent line"
        param-NAME&%: simple-ALPHA
        param-REQUESTID&requestid: requestid-INSTANTIATE
        param-TARGETname&%: class-SIMPLE
    - request_map-REQUEST^param:
        param-NAME&%: simple-BETA
        param-REQUESTID&requestid: requestid-INSTANTIATE
        param-TARGETname&%: class-SIMPLE
    - request_map-REQUEST^param:
        param-NAME&%: relation_vec-BASIC
        param-REQUESTID&requestid: requestid-INSTANTIATE
        param-TARGETname&%: class-RELATION
    - request_map-REQUEST^param:
        param-NAME&%: descriptor-DEGREEofPOLISH$str
        param-REQUESTID&requestid: requestid-INSTANTIATE
        param-TARGETname&%: class-DESCRIPTOR
    - request_map-REQUEST^param:
        param-NAME&%: federator-A
        param-REQUESTID&requestid: requestid-INSTANTIATE
        param-TARGETname&%: class-FEDERATOR
        param_map-DESCRIPTORS^descriptor:
          descriptor_vec-FEDERATIONnames&%:
          - simple-ALPHA
          - simple-BETA
          descriptor_vecmap-SCRIPT^request:
          - request_map-REQUEST^param:
              param-NAME&%: simple-GAMMA
              param-REQUESTID&requestid: requestid-INSTANTIATE
              param-TARGETname&%: class-SIMPLE
          - request_map-REQUEST^param:
              param-REQUESTID&requestid: requestid-ADDdescriptors
              param-TARGETname&%: simple-ALPHA
              param_map-DESCRIPTORS^descriptor:
                descriptor-DEGREEofPOLISH$str: MIDDLING
          - request_map-REQUEST^param:
              param-REQUESTID&requestid: requestid-ADDdescriptors
              param-TARGETname&%: simple-GAMMA
              param_map-DESCRIPTORS^descriptor:
                descriptor-DEGREEofPOLISH$str: MIDDLING
          - request_map-REQUEST^param:
              param-REQUESTID&requestid: requestid-ADDrelations
              param-TARGETname&%: simple-GAMMA
              param_map-RELATIONS^relation&%:
                relation_vec-BASIC:
                - simple-ALPHA
          - request_map-REQUEST^param:
              param-REQUESTID&requestid: requestid-ADDrelations
              param-TARGETname&%: simple-BETA
              param_map-RELATIONS^relation&%:
                relation_vec-BASIC:
                - simple-ALPHA
    - request_map-REQUEST^param:
        param-REQUESTID&requestid: requestid-RUNfederation
        param-TARGETname&%: federator-A
  facet_vec-REQUESTportSTACK$chan:
  - clojure.core.async.chan
---
# Entity SYS+context-TEST

this is a test same line 
different line

---
1. Classifier Values of context SYS+context-TEST
(Default context is context-TEST.)

classifier:  SYS+classifier-CLASS&class
  value:       SYS+class-CLASS
    entity:      TEST+class-SIMPLE
  value:       SYS+class-DESCRIPTOR
    entity:      TEST+descriptor-DEGREEofPOLISH$str
  value:       SYS+class-FEDERATOR
    entity:      TEST+federator-A
  value:       SYS+class-RELATION
    entity:      TEST+relation_vec-BASIC
  value:       TEST+class-SIMPLE
    entity:      TEST+simple-ALPHA
    entity:      TEST+simple-BETA
    entity:      TEST+simple-GAMMA

Number of classifiers: 1

