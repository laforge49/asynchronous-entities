---
gem_map-FACETS^facet:
  facet-CONTENT$str: "This is a system test. Same line. \nDifferent line."
  facet-NAME&%: context-SYSTEST
  facet_map-CLASSIFIERS^classifier:
    classifier-CLASS&class: class-CONTEXT
    classifier_vec-RESOURCES&context:
    - context-SYS
  facet_map-DESCRIPTORS^descriptor:
    descriptor_map-SCRIPT^request:
      0500010 request_map-REQUEST^param:
        param-REQUESTID&requestid: requestid-PRINTLN
        param-TARGETname&%: context-SYS
        param-TEXT$str: Finished SYSTEST
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
# Entity SYS+context-SYSTEST

This is a system test. Same line. 
Different line.

---
1. Classifier Values of context SYS+context-SYSTEST
(Default context is context-SYSTEST.)


Number of classifiers: 0

