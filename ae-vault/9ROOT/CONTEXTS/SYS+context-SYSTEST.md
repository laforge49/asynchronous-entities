---
SYS+gem-FACETS_map^facet:
  SYS+facet-CLASSIFIERS_map^classifier:
    SYS+classifier-CLASS&class:
      class-CONTEXT
    SYS+classifier-RESOURCES_vec&context:
      - context-SYS
  SYS+facet-CONTENT$str:
    "This is a system test. Same line. \nDifferent line."
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
      0500010 SYS+request-REQUEST_map^param:
        SYS+param-REQUESTID&requestid:
          requestid-PRINTLN
        SYS+param-TARGETname&%:
          context-SYS
        SYS+param-TEXT$str:
          Finished SYSTEST
  SYS+facet-NAME&%:
    context-SYSTEST
  SYS+facet-REQUESTportSTACK_vec$chan:
    - clojure.core.async.chan
---
# Gem SYS+context-SYSTEST

This is a system test. Same line. 
Different line.

---
1. Classifier Values of context SYS+context-SYSTEST
(Default context is context-SYSTEST.)


Number of classifiers: 0

