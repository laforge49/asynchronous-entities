---
gem_map-FACETS^facet:
  SYS+facet-CONTENT$str:
    ''
  SYS+facet-NAME&%:
    context-SYS
  SYS+facet_map-DESCRIPTORS^descriptor:
    SYS+descriptor_map-SCRIPT^request:
      0000010 SYS+request_map-REQUEST^param:
        SYS+param-NAME&%:
          class-CLASS
        SYS+param-REQUESTID&requestid:
          requestid-REGISTERgem
        SYS+param-TARGETname&%:
          context-SYS
        SYS+param_map-DESCRIPTORS^descriptor:
          SYS+descriptor-INVARIANT$bool:
            true
          SYS+descriptor_map-INSTANCE^descriptor:
            SYS+descriptor-INVARIANT$bool:
              true
            SYS+descriptor_mapvec-REQUESTS^requestid$str:
              SYS+requestid-GEMreport:
                - GEM_REPORToperationid
              SYS+requestid-INSTANTIATE:
                - INSTANTIATEoperationid
          SYS+descriptor_mapvec-REQUESTS^requestid$str:
            SYS+requestid-GEMreport:
              - GEM_REPORToperationid
            SYS+requestid-INSTANTIATE:
              - INSTANTIATEoperationid
      0010000 SYS+request_map-REQUEST^param:
        SYS+param-NAME&%:
          class-CLASSIFIER
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
      0010010 SYS+request_map-REQUEST^param:
        SYS+param-NAME&%:
          classifier-CLASS&class
        SYS+param-REQUESTID&requestid:
          requestid-INSTANTIATE
        SYS+param-TARGETname&%:
          class-CLASSIFIER
      0020000 SYS+request_map-REQUEST^param:
        SYS+param-NAME&%:
          class-CLASSIFIERvec
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
      0020010 SYS+request_map-REQUEST^param:
        SYS+param-NAME&%:
          classifier_vec-RESOURCES&context
        SYS+param-REQUESTID&requestid:
          requestid-INSTANTIATE
        SYS+param-TARGETname&%:
          class-CLASSIFIERvec
      0030000 SYS+request_map-REQUEST^param:
        SYS+param-NAME&%:
          class-CONTEXT
        SYS+param-REQUESTID&requestid:
          requestid-INSTANTIATE
        SYS+param-TARGETname&%:
          class-CLASS
        SYS+param_map-DESCRIPTORS^descriptor:
          SYS+descriptor_map-INSTANCE^descriptor:
            SYS+descriptor_mapvec-REQUESTS^requestid$str:
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
      0030020 SYS+request_map-REQUEST^param:
        SYS+param-CONTENT$str:
          "This is a system test. Same line. \nDifferent line."
        SYS+param-NAME&%:
          context-SYSTEST
        SYS+param-REQUESTID&requestid:
          requestid-INSTANTIATE
        SYS+param-TARGETname&%:
          class-CONTEXT
        SYS+param_map-CLASSIFIERS^classifier:
          SYS+classifier_vec-RESOURCES&context:
            - context-SYS
      0030030 SYS+request_map-REQUEST^param:
        SYS+param-CONTENT$str:
          This is context FED.
        SYS+param-NAME&%:
          context-FED
        SYS+param-REQUESTID&requestid:
          requestid-INSTANTIATE
        SYS+param-TARGETname&%:
          class-CONTEXT
        SYS+param_map-CLASSIFIERS^classifier:
          SYS+classifier_vec-RESOURCES&context:
            - context-SYS
      0030040 SYS+request_map-REQUEST^param:
        SYS+param-CONTENT$str:
          This is a federation test.
        SYS+param-NAME&%:
          context-FEDTEST
        SYS+param-REQUESTID&requestid:
          requestid-INSTANTIATE
        SYS+param-TARGETname&%:
          class-CONTEXT
        SYS+param_map-CLASSIFIERS^classifier:
          SYS+classifier_vec-RESOURCES&context:
            - context-SYS
            - context-FED
      0040000 SYS+request_map-REQUEST^param:
        SYS+param-NAME&%:
          class-DESCRIPTOR
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
          descriptor-INVARIANT$bool
        SYS+param-REQUESTID&requestid:
          requestid-INSTANTIATE
        SYS+param-TARGETname&%:
          class-DESCRIPTOR
      0040020 SYS+request_map-REQUEST^param:
        SYS+param-NAME&%:
          descriptor-READonly$bool
        SYS+param-REQUESTID&requestid:
          requestid-INSTANTIATE
        SYS+param-TARGETname&%:
          class-DESCRIPTOR
      0050000 SYS+request_map-REQUEST^param:
        SYS+param-NAME&%:
          class-DESCRIPTORmap
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
      0050010 SYS+request_map-REQUEST^param:
        SYS+param-NAME&%:
          descriptor_map-INSTANCE^descriptor
        SYS+param-REQUESTID&requestid:
          requestid-INSTANTIATE
        SYS+param-TARGETname&%:
          class-DESCRIPTORmap
      0050020 SYS+request_map-REQUEST^param:
        SYS+param-NAME&%:
          descriptor_map-SCRIPT^request
        SYS+param-REQUESTID&requestid:
          requestid-INSTANTIATE
        SYS+param-TARGETname&%:
          class-DESCRIPTORmap
      0060000 SYS+request_map-REQUEST^param:
        SYS+param-NAME&%:
          class-DESCRIPTORmapvec
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
      0060010 SYS+request_map-REQUEST^param:
        SYS+param-NAME&%:
          descriptor_mapvec-REQUESTS^requestid$str
        SYS+param-REQUESTID&requestid:
          requestid-INSTANTIATE
        SYS+param-TARGETname&%:
          class-DESCRIPTORmapvec
      0070000 SYS+request_map-REQUEST^param:
        SYS+param-NAME&%:
          class-DESCRIPTORvec
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
      0090000 SYS+request_map-REQUEST^param:
        SYS+param-NAME&%:
          class-FACET
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
      0090010 SYS+request_map-REQUEST^param:
        SYS+param-NAME&%:
          facet-CONTENT$str
        SYS+param-REQUESTID&requestid:
          requestid-INSTANTIATE
        SYS+param-TARGETname&%:
          class-FACET
      0090020 SYS+request_map-REQUEST^param:
        SYS+param-NAME&%:
          facet-NAME&%
        SYS+param-REQUESTID&requestid:
          requestid-INSTANTIATE
        SYS+param-TARGETname&%:
          class-FACET
      0100000 SYS+request_map-REQUEST^param:
        SYS+param-NAME&%:
          class-FACETmap
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
      0100010 SYS+request_map-REQUEST^param:
        SYS+param-NAME&%:
          facet_map-CLASSIFIERS^classifier
        SYS+param-REQUESTID&requestid:
          requestid-INSTANTIATE
        SYS+param-TARGETname&%:
          class-FACETmap
      0100020 SYS+request_map-REQUEST^param:
        SYS+param-NAME&%:
          facet_map-DESCRIPTORS^descriptor
        SYS+param-REQUESTID&requestid:
          requestid-INSTANTIATE
        SYS+param-TARGETname&%:
          class-FACETmap
      0110000 SYS+request_map-REQUEST^param:
        SYS+param-NAME&%:
          class-FACETvec
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
      0110010 SYS+request_map-REQUEST^param:
        SYS+param-NAME&%:
          facet_vec-REQUESTportSTACK$chan
        SYS+param-REQUESTID&requestid:
          requestid-INSTANTIATE
        SYS+param-TARGETname&%:
          class-FACETvec
      0120000 SYS+request_map-REQUEST^param:
        SYS+param-NAME&%:
          class-PARAM
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
      0120010 SYS+request_map-REQUEST^param:
        SYS+param-NAME&%:
          param-CONTENT$str
        SYS+param-REQUESTID&requestid:
          requestid-INSTANTIATE
        SYS+param-TARGETname&%:
          class-PARAM
      0120020 SYS+request_map-REQUEST^param:
        SYS+param-NAME&%:
          param-NAME&%
        SYS+param-REQUESTID&requestid:
          requestid-INSTANTIATE
        SYS+param-TARGETname&%:
          class-PARAM
      0120030 SYS+request_map-REQUEST^param:
        SYS+param-NAME&%:
          param-REQUESTID&requestid
        SYS+param-REQUESTID&requestid:
          requestid-INSTANTIATE
        SYS+param-TARGETname&%:
          class-PARAM
      0120040 SYS+request_map-REQUEST^param:
        SYS+param-NAME&%:
          param-TARGETname&%
        SYS+param-REQUESTID&requestid:
          requestid-INSTANTIATE
        SYS+param-TARGETname&%:
          class-PARAM
      0120050 SYS+request_map-REQUEST^param:
        SYS+param-NAME&%:
          param-TEXT$str
        SYS+param-REQUESTID&requestid:
          requestid-INSTANTIATE
        SYS+param-TARGETname&%:
          class-PARAM
      0130000 SYS+request_map-REQUEST^param:
        SYS+param-NAME&%:
          class-PARAMmap
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
      0130010 SYS+request_map-REQUEST^param:
        SYS+param-NAME&%:
          param_map-CLASSIFIERS^classifier
        SYS+param-REQUESTID&requestid:
          requestid-INSTANTIATE
        SYS+param-TARGETname&%:
          class-PARAMmap
      0130020 SYS+request_map-REQUEST^param:
        SYS+param-NAME&%:
          param_map-DESCRIPTORS^descriptor
        SYS+param-REQUESTID&requestid:
          requestid-INSTANTIATE
        SYS+param-TARGETname&%:
          class-PARAMmap
      0140000 SYS+request_map-REQUEST^param:
        SYS+param-NAME&%:
          class-REQUESTmap
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
      0140010 SYS+request_map-REQUEST^param:
        SYS+param-NAME&%:
          request_map-REQUEST^param
        SYS+param-REQUESTID&requestid:
          requestid-INSTANTIATE
        SYS+param-TARGETname&%:
          class-REQUESTmap
      0150000 SYS+request_map-REQUEST^param:
        SYS+param-NAME&%:
          class-REQUESTID
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
      0150010 SYS+request_map-REQUEST^param:
        SYS+param-NAME&%:
          requestid-ADDdescriptors
        SYS+param-REQUESTID&requestid:
          requestid-INSTANTIATE
        SYS+param-TARGETname&%:
          class-REQUESTID
      0150020 SYS+request_map-REQUEST^param:
        SYS+param-NAME&%:
          requestid-GEMreport
        SYS+param-REQUESTID&requestid:
          requestid-INSTANTIATE
        SYS+param-TARGETname&%:
          class-REQUESTID
        SYS+param_map-DESCRIPTORS^descriptor:
          SYS+descriptor-READonly$bool:
            true
      0150030 SYS+request_map-REQUEST^param:
        SYS+param-NAME&%:
          requestid-EVALscript
        SYS+param-REQUESTID&requestid:
          requestid-INSTANTIATE
        SYS+param-TARGETname&%:
          class-REQUESTID
        SYS+param_map-DESCRIPTORS^descriptor:
          SYS+descriptor-READonly$bool:
            true
      0150040 SYS+request_map-REQUEST^param:
        SYS+param-NAME&%:
          requestid-INSTANTIATE
        SYS+param-REQUESTID&requestid:
          requestid-INSTANTIATE
        SYS+param-TARGETname&%:
          class-REQUESTID
        SYS+param_map-DESCRIPTORS^descriptor:
          SYS+descriptor-READonly$bool:
            true
      0150050 SYS+request_map-REQUEST^param:
        SYS+param-NAME&%:
          requestid-LOADscript
        SYS+param-REQUESTID&requestid:
          requestid-INSTANTIATE
        SYS+param-TARGETname&%:
          class-REQUESTID
        SYS+param_map-DESCRIPTORS^descriptor:
          SYS+descriptor-READonly$bool:
            true
      0150060 SYS+request_map-REQUEST^param:
        SYS+param-NAME&%:
          requestid-PRINTLN
        SYS+param-REQUESTID&requestid:
          requestid-INSTANTIATE
        SYS+param-TARGETname&%:
          class-REQUESTID
      0150070 SYS+request_map-REQUEST^param:
        SYS+param-NAME&%:
          requestid-REGISTERgem
        SYS+param-REQUESTID&requestid:
          requestid-INSTANTIATE
        SYS+param-TARGETname&%:
          class-REQUESTID
      0150080 SYS+request_map-REQUEST^param:
        SYS+param-NAME&%:
          requestid-VALIDATEscriptNAMES
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
          context-SYSTEST
      0200120 SYS+request_map-REQUEST^param:
        SYS+param-REQUESTID&requestid:
          requestid-EVALscript
        SYS+param-TARGETname&%:
          context-SYSTEST
      0200130 SYS+request_map-REQUEST^param:
        SYS+param-REQUESTID&requestid:
          requestid-VALIDATEscriptNAMES
        SYS+param-TARGETname&%:
          context-SYSTEST
      0200210 SYS+request_map-REQUEST^param:
        SYS+param-REQUESTID&requestid:
          requestid-LOADscript
        SYS+param-TARGETname&%:
          context-FED
      0200220 SYS+request_map-REQUEST^param:
        SYS+param-REQUESTID&requestid:
          requestid-EVALscript
        SYS+param-TARGETname&%:
          context-FED
      0200230 SYS+request_map-REQUEST^param:
        SYS+param-REQUESTID&requestid:
          requestid-VALIDATEscriptNAMES
        SYS+param-TARGETname&%:
          context-FED
      0400010 SYS+request_map-REQUEST^param:
        SYS+param-REQUESTID&requestid:
          requestid-GEMreport
        SYS+param-TARGETname&%:
          context-SYS
      0500010 SYS+request_map-REQUEST^param:
        SYS+param-REQUESTID&requestid:
          requestid-PRINTLN
        SYS+param-TARGETname&%:
          context-SYS
        SYS+param-TEXT$str:
          Finished SYS
    SYS+descriptor_mapvec-REQUESTS^requestid$str:
      SYS+requestid-EVALscript:
        - EVAL_SCRIPToperationid
      SYS+requestid-GEMreport:
        - CONTEXT_REPORToperationid
      SYS+requestid-LOADscript:
        - LOAD_SCRIPToperationid
      SYS+requestid-PRINTLN:
        - PRINTLNoperationid
      SYS+requestid-REGISTERgem:
        - REGISTER_GEMoperationid
      SYS+requestid-VALIDATEscriptNAMES:
        - VALIDATE_SCRIPT_NAMESoperationid
  SYS+facet_vec-REQUESTportSTACK$chan:
    - clojure.core.async.chan
---
# Gem ROOT+context-SYS

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
    entity:      SYS+context-FED
    entity:      SYS+context-FEDTEST
    entity:      SYS+context-SYSTEST
  value:       SYS+class-DESCRIPTOR
    entity:      SYS+descriptor-INVARIANT$bool
    entity:      SYS+descriptor-READonly$bool
  value:       SYS+class-DESCRIPTORmap
    entity:      SYS+descriptor_map-INSTANCE^descriptor
    entity:      SYS+descriptor_map-SCRIPT^request
  value:       SYS+class-DESCRIPTORmapvec
    entity:      SYS+descriptor_mapvec-REQUESTS^requestid$str
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
    entity:      SYS+requestid-ADDdescriptors
    entity:      SYS+requestid-EVALscript
    entity:      SYS+requestid-GEMreport
    entity:      SYS+requestid-INSTANTIATE
    entity:      SYS+requestid-LOADscript
    entity:      SYS+requestid-PRINTLN
    entity:      SYS+requestid-REGISTERgem
    entity:      SYS+requestid-VALIDATEscriptNAMES
  value:       SYS+class-REQUESTmap
    entity:      SYS+request_map-REQUEST^param
classifier:  SYS+classifier_vec-RESOURCES&context
  value:       ROOT+context-SYS
    entity:      SYS+context-FED
    entity:      SYS+context-FEDTEST
    entity:      SYS+context-SYSTEST
  value:       SYS+context-FED
    entity:      SYS+context-FEDTEST

Number of classifiers: 2

