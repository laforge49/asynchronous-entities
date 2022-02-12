---
gem_map-FACETS^facet:
  facet-CONTENT$str: This is context FED.
  facet-NAME&%: context-FED
  facet_map-CLASSIFIERS^classifier:
    classifier-CLASS&class: class-CONTEXT
    classifier_vec-RESOURCES&context:
    - context-SYS
  facet_map-DESCRIPTORS^descriptor:
    descriptor_map-SCRIPT^request:
      0030010 request_map-REQUEST^param:
        param-CONTENT$str: This is a federation test.
        param-NAME&%: context-FEDTEST
        param-REQUESTID&requestid: requestid-INSTANTIATE
        param-TARGETname&%: class-CONTEXT
        param_map-CLASSIFIERS^classifier:
          classifier_vec-RESOURCES&context:
          - context-SYS
          - context-FED
      0200110 request_map-REQUEST^param:
        param-REQUESTID&requestid: requestid-LOADscript
        param-TARGETname&%: context-FEDTEST
      0200120 request_map-REQUEST^param:
        param-REQUESTID&requestid: requestid-EVALscript
        param-TARGETname&%: context-FEDTEST
      0200130 request_map-REQUEST^param:
        param-REQUESTID&requestid: requestid-VALIDATEscriptNAMES
        param-TARGETname&%: context-FEDTEST
      0500010 request_map-REQUEST^param:
        param-REQUESTID&requestid: requestid-PRINTLN
        param-TARGETname&%: context-SYS
        param-TEXT$str: Finished FED
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
# Entity SYS+context-FED

This is context FED.

---
1. Classifier Values of context SYS+context-FED
(Default context is context-FED.)

classifier:  SYS+classifier-CLASS&class
  value:       SYS+class-CONTEXT
    entity:      FED+context-FEDTEST
classifier:  SYS+classifier_vec-RESOURCES&context
  value:       ROOT+context-SYS
    entity:      FED+context-FEDTEST
  value:       SYS+context-FED
    entity:      FED+context-FEDTEST

Number of classifiers: 2

