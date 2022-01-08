---
gem_map-FACETS^facet:
  facet-CONTENT$str: ''
  facet-NAME&%: context-SYS
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
        param-NAME&%: class-CLASS
        param-REQUESTID&requestid: requestid-REGISTERentity
        param-TARGETname&%: context-SYS
        param_map-DESCRIPTORS^descriptor:
          descriptor-INVARIANT$bool: true
          descriptor_map-INSTANCE^descriptor:
            descriptor-INVARIANT$bool: true
            descriptor_mapvec-REQUESTS^requestid$str:
              requestid-ENTITYreport:
              - ENTITY_REPORToperationid
              requestid-INSTANTIATE:
              - INSTANTIATEoperationid
          descriptor_mapvec-REQUESTS^requestid$str:
            requestid-ENTITYreport:
            - ENTITY_REPORToperationid
            requestid-INSTANTIATE:
            - INSTANTIATEoperationid
    - request_map-REQUEST^param:
        param-NAME&%: class-CLASSIFIER
        param-REQUESTID&requestid: requestid-INSTANTIATE
        param-TARGETname&%: class-CLASS
        param_map-DESCRIPTORS^descriptor:
          descriptor_map-INSTANCE^descriptor:
            descriptor-INVARIANT$bool: true
            descriptor_mapvec-REQUESTS^requestid$str:
              requestid-ENTITYreport:
              - ENTITY_REPORToperationid
    - request_map-REQUEST^param:
        param-NAME&%: classifier-CLASS&class
        param-REQUESTID&requestid: requestid-INSTANTIATE
        param-TARGETname&%: class-CLASSIFIER
    - request_map-REQUEST^param:
        param-NAME&%: class-CLASSIFIERvec
        param-REQUESTID&requestid: requestid-INSTANTIATE
        param-TARGETname&%: class-CLASS
        param_map-DESCRIPTORS^descriptor:
          descriptor_map-INSTANCE^descriptor:
            descriptor-INVARIANT$bool: true
            descriptor_mapvec-REQUESTS^requestid$str:
              requestid-ENTITYreport:
              - ENTITY_REPORToperationid
    - request_map-REQUEST^param:
        param-NAME&%: classifier_vec-RESOURCES&context
        param-REQUESTID&requestid: requestid-INSTANTIATE
        param-TARGETname&%: class-CLASSIFIERvec
    - request_map-REQUEST^param:
        param-NAME&%: class-DESCRIPTOR
        param-REQUESTID&requestid: requestid-INSTANTIATE
        param-TARGETname&%: class-CLASS
        param_map-DESCRIPTORS^descriptor:
          descriptor_map-INSTANCE^descriptor:
            descriptor-INVARIANT$bool: true
            descriptor_mapvec-REQUESTS^requestid$str:
              requestid-ENTITYreport:
              - ENTITY_REPORToperationid
    - request_map-REQUEST^param:
        param-NAME&%: descriptor-READonly$bool
        param-REQUESTID&requestid: requestid-INSTANTIATE
        param-TARGETname&%: class-DESCRIPTOR
    - request_map-REQUEST^param:
        param-NAME&%: descriptor-INVARIANT$bool
        param-REQUESTID&requestid: requestid-INSTANTIATE
        param-TARGETname&%: class-DESCRIPTOR
    - request_map-REQUEST^param:
        param-NAME&%: class-CONTEXT
        param-REQUESTID&requestid: requestid-INSTANTIATE
        param-TARGETname&%: class-CLASS
        param_map-DESCRIPTORS^descriptor:
          descriptor_map-INSTANCE^descriptor:
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
    - request_map-REQUEST^param:
        param-CONTENT$str: "this is a test same line \ndifferent line"
        param-NAME&%: context-TEST
        param-REQUESTID&requestid: requestid-INSTANTIATE
        param-TARGETname&%: class-CONTEXT
        param_map-CLASSIFIERS^classifier:
          classifier_vec-RESOURCES&context:
          - context-SYS
    - request_map-REQUEST^param:
        param-NAME&%: context-UI
        param-REQUESTID&requestid: requestid-INSTANTIATE
        param-TARGETname&%: class-CONTEXT
        param_map-CLASSIFIERS^classifier:
          classifier_vec-RESOURCES&context:
          - context-SYS
        param_map-DESCRIPTORS^descriptor:
          descriptor_mapvec-REQUESTS^requestid$str:
            requestid-UIcreation:
            - UI_CREATIONoperationid
    - request_map-REQUEST^param:
        param-NAME&%: class-DESCRIPTORmap
        param-REQUESTID&requestid: requestid-INSTANTIATE
        param-TARGETname&%: class-CLASS
        param_map-DESCRIPTORS^descriptor:
          descriptor_map-INSTANCE^descriptor:
            descriptor-INVARIANT$bool: true
            descriptor_mapvec-REQUESTS^requestid$str:
              requestid-ENTITYreport:
              - ENTITY_REPORToperationid
    - request_map-REQUEST^param:
        param-NAME&%: descriptor_map-INSTANCE^descriptor
        param-REQUESTID&requestid: requestid-INSTANTIATE
        param-TARGETname&%: class-DESCRIPTORmap
    - request_map-REQUEST^param:
        param-NAME&%: descriptor_map-INSTANCE^classifier
        param-REQUESTID&requestid: requestid-INSTANTIATE
        param-TARGETname&%: class-DESCRIPTORmap
    - request_map-REQUEST^param:
        param-NAME&%: class-DESCRIPTORmapvec
        param-REQUESTID&requestid: requestid-INSTANTIATE
        param-TARGETname&%: class-CLASS
        param_map-DESCRIPTORS^descriptor:
          descriptor_map-INSTANCE^descriptor:
            descriptor-INVARIANT$bool: true
            descriptor_mapvec-REQUESTS^requestid$str:
              requestid-ENTITYreport:
              - ENTITY_REPORToperationid
    - request_map-REQUEST^param:
        param-NAME&%: descriptor_mapvec-REQUESTS^requestid$str
        param-REQUESTID&requestid: requestid-INSTANTIATE
        param-TARGETname&%: class-DESCRIPTORmapvec
    - request_map-REQUEST^param:
        param-NAME&%: class-DESCRIPTORvec
        param-REQUESTID&requestid: requestid-INSTANTIATE
        param-TARGETname&%: class-CLASS
        param_map-DESCRIPTORS^descriptor:
          descriptor_map-INSTANCE^descriptor:
            descriptor-INVARIANT$bool: true
            descriptor_mapvec-REQUESTS^requestid$str:
              requestid-ENTITYreport:
              - ENTITY_REPORToperationid
    - request_map-REQUEST^param:
        param-NAME&%: descriptor_vec-FEDERATIONnames&%
        param-REQUESTID&requestid: requestid-INSTANTIATE
        param-TARGETname&%: class-DESCRIPTORvec
    - request_map-REQUEST^param:
        param-NAME&%: class-RELATION
        param-REQUESTID&requestid: requestid-INSTANTIATE
        param-TARGETname&%: class-CLASS
        param_map-DESCRIPTORS^descriptor:
          descriptor_map-INSTANCE^descriptor:
            descriptor-INVARIANT$bool: true
            descriptor_mapvec-REQUESTS^requestid$str:
              requestid-ENTITYreport:
              - ENTITY_REPORToperationid
    - request_map-REQUEST^param:
        param-NAME&%: class-DESCRIPTORvecmap
        param-REQUESTID&requestid: requestid-INSTANTIATE
        param-TARGETname&%: class-CLASS
        param_map-DESCRIPTORS^descriptor:
          descriptor_map-INSTANCE^descriptor:
            descriptor-INVARIANT$bool: true
            descriptor_mapvec-REQUESTS^requestid$str:
              requestid-ENTITYreport:
              - ENTITY_REPORToperationid
    - request_map-REQUEST^param:
        param-NAME&%: descriptor_vecmap-SCRIPT^request
        param-REQUESTID&requestid: requestid-INSTANTIATE
        param-TARGETname&%: class-DESCRIPTORvecmap
    - request_map-REQUEST^param:
        param-NAME&%: class-FACET
        param-REQUESTID&requestid: requestid-INSTANTIATE
        param-TARGETname&%: class-CLASS
        param_map-DESCRIPTORS^descriptor:
          descriptor_map-INSTANCE^descriptor:
            descriptor-INVARIANT$bool: true
            descriptor_mapvec-REQUESTS^requestid$str:
              requestid-ENTITYreport:
              - ENTITY_REPORToperationid
    - request_map-REQUEST^param:
        param-NAME&%: facet-CONTENT$str
        param-REQUESTID&requestid: requestid-INSTANTIATE
        param-TARGETname&%: class-FACET
    - request_map-REQUEST^param:
        param-NAME&%: facet-NAME&%
        param-REQUESTID&requestid: requestid-INSTANTIATE
        param-TARGETname&%: class-FACET
    - request_map-REQUEST^param:
        param-NAME&%: class-FACETmap
        param-REQUESTID&requestid: requestid-INSTANTIATE
        param-TARGETname&%: class-CLASS
        param_map-DESCRIPTORS^descriptor:
          descriptor_map-INSTANCE^descriptor:
            descriptor-INVARIANT$bool: true
            descriptor_mapvec-REQUESTS^requestid$str:
              requestid-ENTITYreport:
              - ENTITY_REPORToperationid
    - request_map-REQUEST^param:
        param-NAME&%: facet_map-CLASSIFIERS^classifier
        param-REQUESTID&requestid: requestid-INSTANTIATE
        param-TARGETname&%: class-FACETmap
    - request_map-REQUEST^param:
        param-NAME&%: facet_map-DESCRIPTORS^descriptor
        param-REQUESTID&requestid: requestid-INSTANTIATE
        param-TARGETname&%: class-FACETmap
    - request_map-REQUEST^param:
        param-NAME&%: facet_map-INVERSErelations^relation&%
        param-REQUESTID&requestid: requestid-INSTANTIATE
        param-TARGETname&%: class-FACETmap
    - request_map-REQUEST^param:
        param-NAME&%: facet_map-RELATIONS^relation&%
        param-REQUESTID&requestid: requestid-INSTANTIATE
        param-TARGETname&%: class-FACETmap
    - request_map-REQUEST^param:
        param-NAME&%: class-FACETvec
        param-REQUESTID&requestid: requestid-INSTANTIATE
        param-TARGETname&%: class-CLASS
        param_map-DESCRIPTORS^descriptor:
          descriptor_map-INSTANCE^descriptor:
            descriptor-INVARIANT$bool: true
            descriptor_mapvec-REQUESTS^requestid$str:
              requestid-ENTITYreport:
              - ENTITY_REPORToperationid
    - request_map-REQUEST^param:
        param-NAME&%: facet_vec-REQUESTportSTACK$chan
        param-REQUESTID&requestid: requestid-INSTANTIATE
        param-TARGETname&%: class-FACETvec
    - request_map-REQUEST^param:
        param-NAME&%: class-FEDERATOR
        param-REQUESTID&requestid: requestid-INSTANTIATE
        param-TARGETname&%: class-CLASS
        param_map-DESCRIPTORS^descriptor:
          descriptor_map-INSTANCE^descriptor:
            descriptor_mapvec-REQUESTS^requestid$str:
              requestid-ENTITYreport:
              - ENTITY_REPORToperationid
              requestid-RUNfederation:
              - RUN_FEDERATIONoperationid
    - request_map-REQUEST^param:
        param-NAME&%: class-PARAM
        param-REQUESTID&requestid: requestid-INSTANTIATE
        param-TARGETname&%: class-CLASS
        param_map-DESCRIPTORS^descriptor:
          descriptor_map-INSTANCE^descriptor:
            descriptor-INVARIANT$bool: true
            descriptor_mapvec-REQUESTS^requestid$str:
              requestid-ENTITYreport:
              - ENTITY_REPORToperationid
    - request_map-REQUEST^param:
        param-NAME&%: param-CONTENT$str
        param-REQUESTID&requestid: requestid-INSTANTIATE
        param-TARGETname&%: class-PARAM
    - request_map-REQUEST^param:
        param-NAME&%: param-NAME&%
        param-REQUESTID&requestid: requestid-INSTANTIATE
        param-TARGETname&%: class-PARAM
    - request_map-REQUEST^param:
        param-NAME&%: param-REQUESTID&requestid
        param-REQUESTID&requestid: requestid-INSTANTIATE
        param-TARGETname&%: class-PARAM
    - request_map-REQUEST^param:
        param-NAME&%: param-TARGETname&%
        param-REQUESTID&requestid: requestid-INSTANTIATE
        param-TARGETname&%: class-PARAM
    - request_map-REQUEST^param:
        param-NAME&%: class-PARAMmap
        param-REQUESTID&requestid: requestid-INSTANTIATE
        param-TARGETname&%: class-CLASS
        param_map-DESCRIPTORS^descriptor:
          descriptor_map-INSTANCE^descriptor:
            descriptor-INVARIANT$bool: true
            descriptor_mapvec-REQUESTS^requestid$str:
              requestid-ENTITYreport:
              - ENTITY_REPORToperationid
    - request_map-REQUEST^param:
        param-NAME&%: param_map-CLASSIFIERS^classifier
        param-REQUESTID&requestid: requestid-INSTANTIATE
        param-TARGETname&%: class-PARAMmap
    - request_map-REQUEST^param:
        param-NAME&%: param_map-DESCRIPTORS^descriptor
        param-REQUESTID&requestid: requestid-INSTANTIATE
        param-TARGETname&%: class-PARAMmap
    - request_map-REQUEST^param:
        param-NAME&%: param_map-RELATIONS^relation&%
        param-REQUESTID&requestid: requestid-INSTANTIATE
        param-TARGETname&%: class-PARAMmap
    - request_map-REQUEST^param:
        param-NAME&%: class-REQUESTmap
        param-REQUESTID&requestid: requestid-INSTANTIATE
        param-TARGETname&%: class-CLASS
        param_map-DESCRIPTORS^descriptor:
          descriptor_map-INSTANCE^descriptor:
            descriptor-INVARIANT$bool: true
            descriptor_mapvec-REQUESTS^requestid$str:
              requestid-ENTITYreport:
              - ENTITY_REPORToperationid
    - request_map-REQUEST^param:
        param-NAME&%: request_map-REQUEST^param
        param-REQUESTID&requestid: requestid-INSTANTIATE
        param-TARGETname&%: class-REQUESTmap
    - request_map-REQUEST^param:
        param-NAME&%: class-REQUESTID
        param-REQUESTID&requestid: requestid-INSTANTIATE
        param-TARGETname&%: class-CLASS
        param_map-DESCRIPTORS^descriptor:
          descriptor_map-INSTANCE^descriptor:
            descriptor-INVARIANT$bool: true
            descriptor_mapvec-REQUESTS^requestid$str:
              requestid-ENTITYreport:
              - ENTITY_REPORToperationid
    - request_map-REQUEST^param:
        param-NAME&%: requestid-ADDrelations
        param-REQUESTID&requestid: requestid-INSTANTIATE
        param-TARGETname&%: class-REQUESTID
    - request_map-REQUEST^param:
        param-NAME&%: requestid-ADDdescriptors
        param-REQUESTID&requestid: requestid-INSTANTIATE
        param-TARGETname&%: class-REQUESTID
    - request_map-REQUEST^param:
        param-NAME&%: requestid-ENTITYreport
        param-REQUESTID&requestid: requestid-INSTANTIATE
        param-TARGETname&%: class-REQUESTID
        param_map-DESCRIPTORS^descriptor:
          descriptor-READonly$bool: true
    - request_map-REQUEST^param:
        param-NAME&%: requestid-EVALscript
        param-REQUESTID&requestid: requestid-INSTANTIATE
        param-TARGETname&%: class-REQUESTID
        param_map-DESCRIPTORS^descriptor:
          descriptor-READonly$bool: true
    - request_map-REQUEST^param:
        param-NAME&%: requestid-INSTANTIATE
        param-REQUESTID&requestid: requestid-INSTANTIATE
        param-TARGETname&%: class-REQUESTID
        param_map-DESCRIPTORS^descriptor:
          descriptor-READonly$bool: true
    - request_map-REQUEST^param:
        param-NAME&%: requestid-LOADscript
        param-REQUESTID&requestid: requestid-INSTANTIATE
        param-TARGETname&%: class-REQUESTID
        param_map-DESCRIPTORS^descriptor:
          descriptor-READonly$bool: true
    - request_map-REQUEST^param:
        param-NAME&%: requestid-REGISTERentity
        param-REQUESTID&requestid: requestid-INSTANTIATE
        param-TARGETname&%: class-REQUESTID
    - request_map-REQUEST^param:
        param-NAME&%: requestid-RUNfederation
        param-REQUESTID&requestid: requestid-INSTANTIATE
        param-TARGETname&%: class-REQUESTID
    - request_map-REQUEST^param:
        param-NAME&%: requestid-UIcreation
        param-REQUESTID&requestid: requestid-INSTANTIATE
        param-TARGETname&%: class-REQUESTID
    - request_map-REQUEST^param:
        param-NAME&%: requestid-VALIDATEscriptNAMES
        param-REQUESTID&requestid: requestid-INSTANTIATE
        param-TARGETname&%: class-REQUESTID
        param_map-DESCRIPTORS^descriptor:
          descriptor-READonly$bool: true
    - request_map-REQUEST^param:
        param-REQUESTID&requestid: requestid-LOADscript
        param-TARGETname&%: context-TEST
    - request_map-REQUEST^param:
        param-REQUESTID&requestid: requestid-EVALscript
        param-TARGETname&%: context-TEST
    - request_map-REQUEST^param:
        param-REQUESTID&requestid: requestid-VALIDATEscriptNAMES
        param-TARGETname&%: context-TEST
    - request_map-REQUEST^param:
        param-REQUESTID&requestid: requestid-LOADscript
        param-TARGETname&%: context-UI
    - request_map-REQUEST^param:
        param-REQUESTID&requestid: requestid-EVALscript
        param-TARGETname&%: context-UI
    - request_map-REQUEST^param:
        param-REQUESTID&requestid: requestid-VALIDATEscriptNAMES
        param-TARGETname&%: context-UI
    - request_map-REQUEST^param:
        param-REQUESTID&requestid: requestid-UIcreation
        param-TARGETname&%: context-UI
    - request_map-REQUEST^param:
        param-REQUESTID&requestid: requestid-ENTITYreport
        param-TARGETname&%: context-SYS
  facet_vec-REQUESTportSTACK$chan:
  - clojure.core.async.chan
---
# Entity ROOT+context-SYS

1. Classifier Values of context ROOT+context-SYS
(Default context is context-SYS.)

classifier:  SYS+classifier-CLASS&class
  value:       SYS+class-CLASS
    entity:      SYS+class-CLASSIFIER
    entity:      SYS+class-CLASSIFIERvec
    entity:      SYS+class-CONTEXT
    entity:      SYS+class-DESCRIPTOR
    entity:      SYS+class-DESCRIPTORmap
    entity:      SYS+class-DESCRIPTORmapvec
    entity:      SYS+class-DESCRIPTORvec
    entity:      SYS+class-DESCRIPTORvecmap
    entity:      SYS+class-FACET
    entity:      SYS+class-FACETmap
    entity:      SYS+class-FACETvec
    entity:      SYS+class-FEDERATOR
    entity:      SYS+class-PARAM
    entity:      SYS+class-PARAMmap
    entity:      SYS+class-RELATION
    entity:      SYS+class-REQUESTID
    entity:      SYS+class-REQUESTmap
  value:       SYS+class-CLASSIFIER
    entity:      SYS+classifier-CLASS&class
  value:       SYS+class-CLASSIFIERvec
    entity:      SYS+classifier_vec-RESOURCES&context
  value:       SYS+class-CONTEXT
    entity:      SYS+context-TEST
    entity:      SYS+context-UI
  value:       SYS+class-DESCRIPTOR
    entity:      SYS+descriptor-INVARIANT$bool
    entity:      SYS+descriptor-READonly$bool
  value:       SYS+class-DESCRIPTORmap
    entity:      SYS+descriptor_map-INSTANCE^classifier
    entity:      SYS+descriptor_map-INSTANCE^descriptor
  value:       SYS+class-DESCRIPTORmapvec
    entity:      SYS+descriptor_mapvec-REQUESTS^requestid$str
  value:       SYS+class-DESCRIPTORvec
    entity:      SYS+descriptor_vec-FEDERATIONnames&%
  value:       SYS+class-DESCRIPTORvecmap
    entity:      SYS+descriptor_vecmap-SCRIPT^request
  value:       SYS+class-FACET
    entity:      SYS+facet-CONTENT$str
    entity:      SYS+facet-NAME&%
  value:       SYS+class-FACETmap
    entity:      SYS+facet_map-CLASSIFIERS^classifier
    entity:      SYS+facet_map-DESCRIPTORS^descriptor
    entity:      SYS+facet_map-INVERSErelations^relation&%
    entity:      SYS+facet_map-RELATIONS^relation&%
  value:       SYS+class-FACETvec
    entity:      SYS+facet_vec-REQUESTportSTACK$chan
  value:       SYS+class-PARAM
    entity:      SYS+param-CONTENT$str
    entity:      SYS+param-NAME&%
    entity:      SYS+param-REQUESTID&requestid
    entity:      SYS+param-TARGETname&%
  value:       SYS+class-PARAMmap
    entity:      SYS+param_map-CLASSIFIERS^classifier
    entity:      SYS+param_map-DESCRIPTORS^descriptor
    entity:      SYS+param_map-RELATIONS^relation&%
  value:       SYS+class-REQUESTID
    entity:      SYS+requestid-ADDdescriptors
    entity:      SYS+requestid-ADDrelations
    entity:      SYS+requestid-ENTITYreport
    entity:      SYS+requestid-EVALscript
    entity:      SYS+requestid-INSTANTIATE
    entity:      SYS+requestid-LOADscript
    entity:      SYS+requestid-REGISTERentity
    entity:      SYS+requestid-RUNfederation
    entity:      SYS+requestid-UIcreation
    entity:      SYS+requestid-VALIDATEscriptNAMES
  value:       SYS+class-REQUESTmap
    entity:      SYS+request_map-REQUEST^param
classifier:  SYS+classifier_vec-RESOURCES&context
  value:       ROOT+context-SYS
    entity:      SYS+context-TEST
    entity:      SYS+context-UI

Number of classifiers: 2

