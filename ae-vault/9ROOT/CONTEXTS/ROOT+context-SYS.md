---
SYS+gem_map-FACETS^facet:
  SYS+facet-CONTENT$str: ''
  SYS+facet-NAME&?: context-SYS
  SYS+facet_map-DESCRIPTORS^descriptor:
    SYS+descriptor_mapvec-REQUESTS^requestid$str:
      SYS+requestid-ENTITYreport:
      - CONTEXT_REPORToperationid
      SYS+requestid-EVALscript:
      - EVAL_SCRIPToperationid
      SYS+requestid-LOADscript:
      - LOAD_SCRIPToperationid
      SYS+requestid-REGISTERentity:
      - REGISTER_ENTITYoperationid
      SYS+requestid-VALIDATEscriptNAMES:
      - VALIDATE_SCRIPT_NAMESoperationid
    SYS+descriptor_vecmap-SCRIPT^request:
    - SYS+request_map-REQUEST^param:
        SYS+param-NAME&?: SYS+class-CLASS
        SYS+param-REQUESTID&requestid: SYS+requestid-REGISTERentity
        SYS+param-TARGETname&?: context-SYS
        SYS+param_map-DESCRIPTORS^descriptor:
          SYS+descriptor-INVARIANT$bool: true
          SYS+descriptor_map-INSTANCE^descriptor:
            SYS+descriptor-INVARIANT$bool: true
            SYS+descriptor_mapvec-REQUESTS^requestid$str:
              SYS+requestid-ENTITYreport:
              - ENTITY_REPORToperationid
              SYS+requestid-INSTANTIATE:
              - INSTANTIATEoperationid
          SYS+descriptor_mapvec-REQUESTS^requestid$str:
            SYS+requestid-ENTITYreport:
            - ENTITY_REPORToperationid
            SYS+requestid-INSTANTIATE:
            - INSTANTIATEoperationid
    - SYS+request_map-REQUEST^param:
        SYS+param-NAME&?: SYS+class-PARAM
        SYS+param-REQUESTID&requestid: SYS+requestid-INSTANTIATE
        SYS+param-TARGETname&?: SYS+class-CLASS
        SYS+param_map-DESCRIPTORS^descriptor:
          SYS+descriptor_map-INSTANCE^descriptor:
            SYS+descriptor-INVARIANT$bool: true
            SYS+descriptor_mapvec-REQUESTS^requestid$str:
              SYS+requestid-ENTITYreport:
              - ENTITY_REPORToperationid
    - SYS+request_map-REQUEST^param:
        SYS+param-NAME&?: SYS+param-REQUESTID&requestid
        SYS+param-REQUESTID&requestid: SYS+requestid-INSTANTIATE
        SYS+param-TARGETname&?: SYS+class-PARAM
    - SYS+request_map-REQUEST^param:
        SYS+param-NAME&?: SYS+param_map-DESCRIPTORS^descriptor
        SYS+param-REQUESTID&requestid: SYS+requestid-INSTANTIATE
        SYS+param-TARGETname&?: SYS+class-PARAM
    - SYS+request_map-REQUEST^param:
        SYS+param-NAME&?: SYS+class-REQUESTID
        SYS+param-REQUESTID&requestid: SYS+requestid-INSTANTIATE
        SYS+param-TARGETname&?: SYS+class-CLASS
        SYS+param_map-DESCRIPTORS^descriptor:
          SYS+descriptor_map-INSTANCE^descriptor:
            SYS+descriptor-INVARIANT$bool: true
            SYS+descriptor_mapvec-REQUESTS^requestid$str:
              SYS+requestid-ENTITYreport:
              - ENTITY_REPORToperationid
    - SYS+request_map-REQUEST^param:
        SYS+param-NAME&?: SYS+requestid-ADDrelations
        SYS+param-REQUESTID&requestid: SYS+requestid-INSTANTIATE
        SYS+param-TARGETname&?: SYS+class-REQUESTID
    - SYS+request_map-REQUEST^param:
        SYS+param-NAME&?: SYS+requestid-ADDdescriptors
        SYS+param-REQUESTID&requestid: SYS+requestid-INSTANTIATE
        SYS+param-TARGETname&?: SYS+class-REQUESTID
    - SYS+request_map-REQUEST^param:
        SYS+param-NAME&?: SYS+requestid-INSTANTIATE
        SYS+param-REQUESTID&requestid: SYS+requestid-INSTANTIATE
        SYS+param-TARGETname&?: SYS+class-REQUESTID
        SYS+param_map-DESCRIPTORS^descriptor:
          SYS+descriptor-READonly$bool: true
    - SYS+request_map-REQUEST^param:
        SYS+param-NAME&?: SYS+requestid-REGISTERentity
        SYS+param-REQUESTID&requestid: SYS+requestid-INSTANTIATE
        SYS+param-TARGETname&?: SYS+class-REQUESTID
    - SYS+request_map-REQUEST^param:
        SYS+param-NAME&?: SYS+requestid-RUNfederation
        SYS+param-REQUESTID&requestid: SYS+requestid-INSTANTIATE
        SYS+param-TARGETname&?: SYS+class-REQUESTID
    - SYS+request_map-REQUEST^param:
        SYS+param-NAME&?: SYS+class-DESCRIPTOR
        SYS+param-REQUESTID&requestid: SYS+requestid-INSTANTIATE
        SYS+param-TARGETname&?: SYS+class-CLASS
        SYS+param_map-DESCRIPTORS^descriptor:
          SYS+descriptor_map-INSTANCE^descriptor:
            SYS+descriptor-INVARIANT$bool: true
            SYS+descriptor_mapvec-REQUESTS^requestid$str:
              SYS+requestid-ENTITYreport:
              - ENTITY_REPORToperationid
    - SYS+request_map-REQUEST^param:
        SYS+param-NAME&?: SYS+descriptor-READonly$bool
        SYS+param-REQUESTID&requestid: SYS+requestid-INSTANTIATE
        SYS+param-TARGETname&?: SYS+class-DESCRIPTOR
    - SYS+request_map-REQUEST^param:
        SYS+param-NAME&?: SYS+descriptor_mapvec-REQUESTS^requestid$str
        SYS+param-REQUESTID&requestid: SYS+requestid-INSTANTIATE
        SYS+param-TARGETname&?: SYS+class-DESCRIPTOR
    - SYS+request_map-REQUEST^param:
        SYS+param-NAME&?: SYS+descriptor-INVARIANT$bool
        SYS+param-REQUESTID&requestid: SYS+requestid-INSTANTIATE
        SYS+param-TARGETname&?: SYS+class-DESCRIPTOR
    - SYS+request_map-REQUEST^param:
        SYS+param-NAME&?: SYS+descriptor_map-INSTANCE^descriptor
        SYS+param-REQUESTID&requestid: SYS+requestid-INSTANTIATE
        SYS+param-TARGETname&?: SYS+class-DESCRIPTOR
    - SYS+request_map-REQUEST^param:
        SYS+param-NAME&?: SYS+descriptor_map-INSTANCE^classifier
        SYS+param-REQUESTID&requestid: SYS+requestid-INSTANTIATE
        SYS+param-TARGETname&?: SYS+class-DESCRIPTOR
    - SYS+request_map-REQUEST^param:
        SYS+param-NAME&?: SYS+class-CLASSIFIER
        SYS+param-REQUESTID&requestid: SYS+requestid-INSTANTIATE
        SYS+param-TARGETname&?: SYS+class-CLASS
        SYS+param_map-DESCRIPTORS^descriptor:
          SYS+descriptor_map-INSTANCE^descriptor:
            SYS+descriptor-INVARIANT$bool: true
            SYS+descriptor_mapvec-REQUESTS^requestid$str:
              SYS+requestid-ENTITYreport:
              - ENTITY_REPORToperationid
    - SYS+request_map-REQUEST^param:
        SYS+param-NAME&?: SYS+class-RELATION
        SYS+param-REQUESTID&requestid: SYS+requestid-INSTANTIATE
        SYS+param-TARGETname&?: SYS+class-CLASS
        SYS+param_map-DESCRIPTORS^descriptor:
          SYS+descriptor_map-INSTANCE^descriptor:
            SYS+descriptor-INVARIANT$bool: true
            SYS+descriptor_mapvec-REQUESTS^requestid$str:
              SYS+requestid-ENTITYreport:
              - ENTITY_REPORToperationid
    - SYS+request_map-REQUEST^param:
        SYS+param-NAME&?: SYS+class-CONTEXT
        SYS+param-REQUESTID&requestid: SYS+requestid-INSTANTIATE
        SYS+param-TARGETname&?: SYS+class-CLASS
        SYS+param_map-DESCRIPTORS^descriptor:
          SYS+descriptor_map-INSTANCE^descriptor:
            SYS+descriptor_mapvec-REQUESTS^requestid$str:
              SYS+requestid-ENTITYreport:
              - CONTEXT_REPORToperationid
              SYS+requestid-EVALscript:
              - EVAL_SCRIPToperationid
              SYS+requestid-LOADscript:
              - LOAD_SCRIPToperationid
              SYS+requestid-REGISTERentity:
              - REGISTER_ENTITYoperationid
              SYS+requestid-VALIDATEscriptNAMES:
              - VALIDATE_SCRIPT_NAMESoperationid
    - SYS+request_map-REQUEST^param:
        SYS+param-NAME&?: SYS+class-FEDERATOR
        SYS+param-REQUESTID&requestid: SYS+requestid-INSTANTIATE
        SYS+param-TARGETname&?: SYS+class-CLASS
        SYS+param_map-DESCRIPTORS^descriptor:
          SYS+descriptor_map-INSTANCE^descriptor:
            SYS+descriptor_mapvec-REQUESTS^requestid$str:
              SYS+requestid-ENTITYreport:
              - ENTITY_REPORToperationid
              SYS+requestid-RUNfederation:
              - RUN_FEDERATIONoperationid
    - SYS+request_map-REQUEST^param:
        SYS+param-NAME&?: SYS+context-TEST
        SYS+param-REQUESTID&requestid: SYS+requestid-INSTANTIATE
        SYS+param-TARGETname&?: SYS+class-CONTEXT
        SYS+param-content$str: "this is a test same line \ndifferent line"
        SYS+param_map-CLASSIFIERS^classifier:
          SYS+classifier_vec-RESOURCES&context:
          - context-SYS
    - SYS+request_map-REQUEST^param:
        SYS+param-REQUESTID&requestid: SYS+requestid-LOADscript
        SYS+param-TARGETname&?: SYS+context-TEST
    - SYS+request_map-REQUEST^param:
        SYS+param-REQUESTID&requestid: SYS+requestid-EVALscript
        SYS+param-TARGETname&?: SYS+context-TEST
    - SYS+request_map-REQUEST^param:
        SYS+param-REQUESTID&requestid: SYS+requestid-VALIDATEscriptNAMES
        SYS+param-TARGETname&?: SYS+context-TEST
    - SYS+request_map-REQUEST^param:
        SYS+param-REQUESTID&requestid: SYS+requestid-ENTITYreport
        SYS+param-TARGETname&?: context-SYS
  SYS+facet_vec-REQUESTportSTACK$chan:
  - clojure.core.async.chan
---
# Entity ROOT+context-SYS

1. Classifier Values of context ROOT+context-SYS
(Default context is context-SYS.)

classifier:  SYS+classifier-CLASS&class
  value:       SYS+class-CLASS
    entity:      SYS+class-CLASSIFIER
    entity:      SYS+class-CONTEXT
    entity:      SYS+class-DESCRIPTOR
    entity:      SYS+class-FEDERATOR
    entity:      SYS+class-PARAM
    entity:      SYS+class-RELATION
    entity:      SYS+class-REQUESTID
  value:       SYS+class-CONTEXT
    entity:      SYS+context-TEST
  value:       SYS+class-DESCRIPTOR
    entity:      SYS+descriptor-INVARIANT$bool
    entity:      SYS+descriptor-READonly$bool
    entity:      SYS+descriptor_map-INSTANCE^classifier
    entity:      SYS+descriptor_map-INSTANCE^descriptor
    entity:      SYS+descriptor_mapvec-REQUESTS^requestid$str
  value:       SYS+class-PARAM
    entity:      SYS+param-REQUESTID&requestid
    entity:      SYS+param_map-DESCRIPTORS^descriptor
  value:       SYS+class-REQUESTID
    entity:      SYS+requestid-ADDdescriptors
    entity:      SYS+requestid-ADDrelations
    entity:      SYS+requestid-INSTANTIATE
    entity:      SYS+requestid-REGISTERentity
    entity:      SYS+requestid-RUNfederation
classifier:  SYS+classifier_vec-RESOURCES&context
  value:       ROOT+context-SYS
    entity:      SYS+context-TEST

Number of classifiers: 2

