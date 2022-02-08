---
gem_map-FACETS^facet:
  facet-CONTENT$str: ''
  facet-NAME&%: context-SYS
  facet_map-DESCRIPTORS^descriptor:
    descriptor_map-SCRIPT^request:
      0140050 request_map-REQUEST^param:
        param-NAME&%: requestid-PRINTLN
        param-REQUESTID&requestid: requestid-INSTANTIATE
        param-TARGETname&%: class-REQUESTID
      0400010 request_map-REQUEST^param:
        param-REQUESTID&requestid: requestid-ENTITYreport
        param-TARGETname&%: context-SYS
      0090010 request_map-REQUEST^param:
        param-NAME&%: facet_map-CLASSIFIERS^classifier
        param-REQUESTID&requestid: requestid-INSTANTIATE
        param-TARGETname&%: class-FACETmap
      0140030 request_map-REQUEST^param:
        param-NAME&%: requestid-INSTANTIATE
        param-REQUESTID&requestid: requestid-INSTANTIATE
        param-TARGETname&%: class-REQUESTID
        param_map-DESCRIPTORS^descriptor:
          descriptor-READonly$bool: true
      0050000 request_map-REQUEST^param:
        param-NAME&%: class-DESCRIPTORmap
        param-REQUESTID&requestid: requestid-INSTANTIATE
        param-TARGETname&%: class-CLASS
        param_map-DESCRIPTORS^descriptor:
          descriptor_map-INSTANCE^descriptor:
            descriptor-INVARIANT$bool: true
            descriptor_mapvec-REQUESTS^requestid$str:
              requestid-ENTITYreport:
              - ENTITY_REPORToperationid
      0090000 request_map-REQUEST^param:
        param-NAME&%: class-FACETmap
        param-REQUESTID&requestid: requestid-INSTANTIATE
        param-TARGETname&%: class-CLASS
        param_map-DESCRIPTORS^descriptor:
          descriptor_map-INSTANCE^descriptor:
            descriptor-INVARIANT$bool: true
            descriptor_mapvec-REQUESTS^requestid$str:
              requestid-ENTITYreport:
              - ENTITY_REPORToperationid
      0130000 request_map-REQUEST^param:
        param-NAME&%: class-REQUESTmap
        param-REQUESTID&requestid: requestid-INSTANTIATE
        param-TARGETname&%: class-CLASS
        param_map-DESCRIPTORS^descriptor:
          descriptor_map-INSTANCE^descriptor:
            descriptor-INVARIANT$bool: true
            descriptor_mapvec-REQUESTS^requestid$str:
              requestid-ENTITYreport:
              - ENTITY_REPORToperationid
      0120020 request_map-REQUEST^param:
        param-NAME&%: param_map-DESCRIPTORS^descriptor
        param-REQUESTID&requestid: requestid-INSTANTIATE
        param-TARGETname&%: class-PARAMmap
      0110030 request_map-REQUEST^param:
        param-NAME&%: param-REQUESTID&requestid
        param-REQUESTID&requestid: requestid-INSTANTIATE
        param-TARGETname&%: class-PARAM
      0110010 request_map-REQUEST^param:
        param-NAME&%: param-CONTENT$str
        param-REQUESTID&requestid: requestid-INSTANTIATE
        param-TARGETname&%: class-PARAM
      0120000 request_map-REQUEST^param:
        param-NAME&%: class-PARAMmap
        param-REQUESTID&requestid: requestid-INSTANTIATE
        param-TARGETname&%: class-CLASS
        param_map-DESCRIPTORS^descriptor:
          descriptor_map-INSTANCE^descriptor:
            descriptor-INVARIANT$bool: true
            descriptor_mapvec-REQUESTS^requestid$str:
              requestid-ENTITYreport:
              - ENTITY_REPORToperationid
      0130010 request_map-REQUEST^param:
        param-NAME&%: request_map-REQUEST^param
        param-REQUESTID&requestid: requestid-INSTANTIATE
        param-TARGETname&%: class-REQUESTmap
      0140020 request_map-REQUEST^param:
        param-NAME&%: requestid-EVALscript
        param-REQUESTID&requestid: requestid-INSTANTIATE
        param-TARGETname&%: class-REQUESTID
        param_map-DESCRIPTORS^descriptor:
          descriptor-READonly$bool: true
      0200020 request_map-REQUEST^param:
        param-REQUESTID&requestid: requestid-EVALscript
        param-TARGETname&%: context-SYSTEST
      0020010 request_map-REQUEST^param:
        param-NAME&%: classifier_vec-RESOURCES&context
        param-REQUESTID&requestid: requestid-INSTANTIATE
        param-TARGETname&%: class-CLASSIFIERvec
      0200010 request_map-REQUEST^param:
        param-REQUESTID&requestid: requestid-LOADscript
        param-TARGETname&%: context-SYSTEST
      0070010 request_map-REQUEST^param:
        param-NAME&%: descriptor_vecmap-SCRIPT^request
        param-REQUESTID&requestid: requestid-INSTANTIATE
        param-TARGETname&%: class-DESCRIPTORvecmap
      0070000 request_map-REQUEST^param:
        param-NAME&%: class-DESCRIPTORvecmap
        param-REQUESTID&requestid: requestid-INSTANTIATE
        param-TARGETname&%: class-CLASS
        param_map-DESCRIPTORS^descriptor:
          descriptor_map-INSTANCE^descriptor:
            descriptor-INVARIANT$bool: true
            descriptor_mapvec-REQUESTS^requestid$str:
              requestid-ENTITYreport:
              - ENTITY_REPORToperationid
      0500010 request_map-REQUEST^param:
        param-REQUESTID&requestid: requestid-PRINTLN
        param-TARGETname&%: context-SYS
        param-TEXT$str: Finished SYS
      0060000 request_map-REQUEST^param:
        param-NAME&%: class-DESCRIPTORmapvec
        param-REQUESTID&requestid: requestid-INSTANTIATE
        param-TARGETname&%: class-CLASS
        param_map-DESCRIPTORS^descriptor:
          descriptor_map-INSTANCE^descriptor:
            descriptor-INVARIANT$bool: true
            descriptor_mapvec-REQUESTS^requestid$str:
              requestid-ENTITYreport:
              - ENTITY_REPORToperationid
      0020000 request_map-REQUEST^param:
        param-NAME&%: class-CLASSIFIERvec
        param-REQUESTID&requestid: requestid-INSTANTIATE
        param-TARGETname&%: class-CLASS
        param_map-DESCRIPTORS^descriptor:
          descriptor_map-INSTANCE^descriptor:
            descriptor-INVARIANT$bool: true
            descriptor_mapvec-REQUESTS^requestid$str:
              requestid-ENTITYreport:
              - ENTITY_REPORToperationid
      0140040 request_map-REQUEST^param:
        param-NAME&%: requestid-LOADscript
        param-REQUESTID&requestid: requestid-INSTANTIATE
        param-TARGETname&%: class-REQUESTID
        param_map-DESCRIPTORS^descriptor:
          descriptor-READonly$bool: true
      0050010 request_map-REQUEST^param:
        param-NAME&%: descriptor_map-INSTANCE^descriptor
        param-REQUESTID&requestid: requestid-INSTANTIATE
        param-TARGETname&%: class-DESCRIPTORmap
      0110050 request_map-REQUEST^param:
        param-NAME&%: param-TEXT$str
        param-REQUESTID&requestid: requestid-INSTANTIATE
        param-TARGETname&%: class-PARAM
      0050020 request_map-REQUEST^param:
        param-NAME&%: descriptor_map-SCRIPT^request
        param-REQUESTID&requestid: requestid-INSTANTIATE
        param-TARGETname&%: class-DESCRIPTORmap
      0110000 request_map-REQUEST^param:
        param-NAME&%: class-PARAM
        param-REQUESTID&requestid: requestid-INSTANTIATE
        param-TARGETname&%: class-CLASS
        param_map-DESCRIPTORS^descriptor:
          descriptor_map-INSTANCE^descriptor:
            descriptor-INVARIANT$bool: true
            descriptor_mapvec-REQUESTS^requestid$str:
              requestid-ENTITYreport:
              - ENTITY_REPORToperationid
      0030000 request_map-REQUEST^param:
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
      0200030 request_map-REQUEST^param:
        param-REQUESTID&requestid: requestid-VALIDATEscriptNAMES
        param-TARGETname&%: context-SYSTEST
      0100000 request_map-REQUEST^param:
        param-NAME&%: class-FACETvec
        param-REQUESTID&requestid: requestid-INSTANTIATE
        param-TARGETname&%: class-CLASS
        param_map-DESCRIPTORS^descriptor:
          descriptor_map-INSTANCE^descriptor:
            descriptor-INVARIANT$bool: true
            descriptor_mapvec-REQUESTS^requestid$str:
              requestid-ENTITYreport:
              - ENTITY_REPORToperationid
      0040000 request_map-REQUEST^param:
        param-NAME&%: class-DESCRIPTOR
        param-REQUESTID&requestid: requestid-INSTANTIATE
        param-TARGETname&%: class-CLASS
        param_map-DESCRIPTORS^descriptor:
          descriptor_map-INSTANCE^descriptor:
            descriptor-INVARIANT$bool: true
            descriptor_mapvec-REQUESTS^requestid$str:
              requestid-ENTITYreport:
              - ENTITY_REPORToperationid
      0080000 request_map-REQUEST^param:
        param-NAME&%: class-FACET
        param-REQUESTID&requestid: requestid-INSTANTIATE
        param-TARGETname&%: class-CLASS
        param_map-DESCRIPTORS^descriptor:
          descriptor_map-INSTANCE^descriptor:
            descriptor-INVARIANT$bool: true
            descriptor_mapvec-REQUESTS^requestid$str:
              requestid-ENTITYreport:
              - ENTITY_REPORToperationid
      0110020 request_map-REQUEST^param:
        param-NAME&%: param-NAME&%
        param-REQUESTID&requestid: requestid-INSTANTIATE
        param-TARGETname&%: class-PARAM
      0080010 request_map-REQUEST^param:
        param-NAME&%: facet-CONTENT$str
        param-REQUESTID&requestid: requestid-INSTANTIATE
        param-TARGETname&%: class-FACET
      0060010 request_map-REQUEST^param:
        param-NAME&%: descriptor_mapvec-REQUESTS^requestid$str
        param-REQUESTID&requestid: requestid-INSTANTIATE
        param-TARGETname&%: class-DESCRIPTORmapvec
      0010010 request_map-REQUEST^param:
        param-NAME&%: classifier-CLASS&class
        param-REQUESTID&requestid: requestid-INSTANTIATE
        param-TARGETname&%: class-CLASSIFIER
      0040010 request_map-REQUEST^param:
        param-NAME&%: descriptor-INVARIANT$bool
        param-REQUESTID&requestid: requestid-INSTANTIATE
        param-TARGETname&%: class-DESCRIPTOR
      0140010 request_map-REQUEST^param:
        param-NAME&%: requestid-ENTITYreport
        param-REQUESTID&requestid: requestid-INSTANTIATE
        param-TARGETname&%: class-REQUESTID
        param_map-DESCRIPTORS^descriptor:
          descriptor-READonly$bool: true
      0120010 request_map-REQUEST^param:
        param-NAME&%: param_map-CLASSIFIERS^classifier
        param-REQUESTID&requestid: requestid-INSTANTIATE
        param-TARGETname&%: class-PARAMmap
      0000010 request_map-REQUEST^param:
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
      0080020 request_map-REQUEST^param:
        param-NAME&%: facet-NAME&%
        param-REQUESTID&requestid: requestid-INSTANTIATE
        param-TARGETname&%: class-FACET
      0090020 request_map-REQUEST^param:
        param-NAME&%: facet_map-DESCRIPTORS^descriptor
        param-REQUESTID&requestid: requestid-INSTANTIATE
        param-TARGETname&%: class-FACETmap
      0100010 request_map-REQUEST^param:
        param-NAME&%: facet_vec-REQUESTportSTACK$chan
        param-REQUESTID&requestid: requestid-INSTANTIATE
        param-TARGETname&%: class-FACETvec
      0110040 request_map-REQUEST^param:
        param-NAME&%: param-TARGETname&%
        param-REQUESTID&requestid: requestid-INSTANTIATE
        param-TARGETname&%: class-PARAM
      0030010 request_map-REQUEST^param:
        param-CONTENT$str: "This is a system test. Same line. \nDifferent line."
        param-NAME&%: context-SYSTEST
        param-REQUESTID&requestid: requestid-INSTANTIATE
        param-TARGETname&%: class-CONTEXT
        param_map-CLASSIFIERS^classifier:
          classifier_vec-RESOURCES&context:
          - context-SYS
      0010000 request_map-REQUEST^param:
        param-NAME&%: class-CLASSIFIER
        param-REQUESTID&requestid: requestid-INSTANTIATE
        param-TARGETname&%: class-CLASS
        param_map-DESCRIPTORS^descriptor:
          descriptor_map-INSTANCE^descriptor:
            descriptor-INVARIANT$bool: true
            descriptor_mapvec-REQUESTS^requestid$str:
              requestid-ENTITYreport:
              - ENTITY_REPORToperationid
      0140070 request_map-REQUEST^param:
        param-NAME&%: requestid-VALIDATEscriptNAMES
        param-REQUESTID&requestid: requestid-INSTANTIATE
        param-TARGETname&%: class-REQUESTID
        param_map-DESCRIPTORS^descriptor:
          descriptor-READonly$bool: true
      0140000 request_map-REQUEST^param:
        param-NAME&%: class-REQUESTID
        param-REQUESTID&requestid: requestid-INSTANTIATE
        param-TARGETname&%: class-CLASS
        param_map-DESCRIPTORS^descriptor:
          descriptor_map-INSTANCE^descriptor:
            descriptor-INVARIANT$bool: true
            descriptor_mapvec-REQUESTS^requestid$str:
              requestid-ENTITYreport:
              - ENTITY_REPORToperationid
      0040020 request_map-REQUEST^param:
        param-NAME&%: descriptor-READonly$bool
        param-REQUESTID&requestid: requestid-INSTANTIATE
        param-TARGETname&%: class-DESCRIPTOR
      0140060 request_map-REQUEST^param:
        param-NAME&%: requestid-REGISTERentity
        param-REQUESTID&requestid: requestid-INSTANTIATE
        param-TARGETname&%: class-REQUESTID
    descriptor_mapvec-REQUESTS^requestid$str:
      requestid-ENTITYreport:
      - CONTEXT_REPORToperationid
      requestid-EVALscript:
      - EVAL_SCRIPToperationid
      requestid-LOADscript:
      - LOAD_SCRIPToperationid
      requestid-PRINTLN:
      - PRINTLNoperationid
      requestid-REGISTERentity:
      - REGISTER_ENTITYoperationid
      requestid-VALIDATEscriptNAMES:
      - VALIDATE_SCRIPT_NAMESoperationid
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
    entity:      SYS+class-DESCRIPTORvecmap
    entity:      SYS+class-FACET
    entity:      SYS+class-FACETmap
    entity:      SYS+class-FACETvec
    entity:      SYS+class-PARAM
    entity:      SYS+class-PARAMmap
    entity:      SYS+class-REQUESTID
    entity:      SYS+class-REQUESTmap
  value:       SYS+class-CLASSIFIER
    entity:      SYS+classifier-CLASS&class
  value:       SYS+class-CLASSIFIERvec
    entity:      SYS+classifier_vec-RESOURCES&context
  value:       SYS+class-CONTEXT
    entity:      SYS+context-SYSTEST
  value:       SYS+class-DESCRIPTOR
    entity:      SYS+descriptor-INVARIANT$bool
    entity:      SYS+descriptor-READonly$bool
  value:       SYS+class-DESCRIPTORmap
    entity:      SYS+descriptor_map-INSTANCE^descriptor
    entity:      SYS+descriptor_map-SCRIPT^request
  value:       SYS+class-DESCRIPTORmapvec
    entity:      SYS+descriptor_mapvec-REQUESTS^requestid$str
  value:       SYS+class-DESCRIPTORvecmap
    entity:      SYS+descriptor_vecmap-SCRIPT^request
  value:       SYS+class-FACET
    entity:      SYS+facet-CONTENT$str
    entity:      SYS+facet-NAME&%
  value:       SYS+class-FACETmap
    entity:      SYS+facet_map-CLASSIFIERS^classifier
    entity:      SYS+facet_map-DESCRIPTORS^descriptor
  value:       SYS+class-FACETvec
    entity:      SYS+facet_vec-REQUESTportSTACK$chan
  value:       SYS+class-PARAM
    entity:      SYS+param-CONTENT$str
    entity:      SYS+param-NAME&%
    entity:      SYS+param-REQUESTID&requestid
    entity:      SYS+param-TARGETname&%
    entity:      SYS+param-TEXT$str
  value:       SYS+class-PARAMmap
    entity:      SYS+param_map-CLASSIFIERS^classifier
    entity:      SYS+param_map-DESCRIPTORS^descriptor
  value:       SYS+class-REQUESTID
    entity:      SYS+requestid-ENTITYreport
    entity:      SYS+requestid-EVALscript
    entity:      SYS+requestid-INSTANTIATE
    entity:      SYS+requestid-LOADscript
    entity:      SYS+requestid-PRINTLN
    entity:      SYS+requestid-REGISTERentity
    entity:      SYS+requestid-VALIDATEscriptNAMES
  value:       SYS+class-REQUESTmap
    entity:      SYS+request_map-REQUEST^param
classifier:  SYS+classifier_vec-RESOURCES&context
  value:       ROOT+context-SYS
    entity:      SYS+context-SYSTEST

Number of classifiers: 2

