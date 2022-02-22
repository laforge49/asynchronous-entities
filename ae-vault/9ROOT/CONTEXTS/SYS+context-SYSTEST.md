---
gem_map-FACETS^facet:
  SYS+facet-CONTENT$str:
    "This is a system test. Same line. \nDifferent line."
  SYS+facet-NAME&%:
    context-SYSTEST
  SYS+facet_map-CLASSIFIERS^classifier:
    SYS+classifier-CLASS&class:
      class-CONTEXT
    SYS+classifier_vec-RESOURCES&context:
      - context-SYS
  SYS+facet_map-DESCRIPTORS^descriptor:
    SYS+descriptor_map-SCRIPT^request:
      0500010 SYS+request_map-REQUEST^param:
        SYS+param-REQUESTID&requestid:
          requestid-PRINTLN
        SYS+param-TARGETname&%:
          context-SYS
        SYS+param-TEXT$str:
          Finished SYSTEST
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
# Gem SYS+context-SYSTEST

This is a system test. Same line. 
Different line.

---
1. Classifier Values of context SYS+context-SYSTEST
(Default context is context-SYSTEST.)


Number of classifiers: 0

