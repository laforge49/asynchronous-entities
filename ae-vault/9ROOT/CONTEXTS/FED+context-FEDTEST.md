---
gem_map-FACETS^facet:
  facet-CONTENT$str: This is a federation test.
  facet-NAME&%: context-FEDTEST
  facet_map-CLASSIFIERS^classifier:
    classifier-CLASS&class: class-CONTEXT
    classifier_vec-RESOURCES&context:
    - context-SYS
    - context-FED
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
  facet_vec-REQUESTportSTACK$chan:
  - clojure.core.async.chan
---
# Entity FED+context-FEDTEST

This is a federation test.

---
1. Classifier Values of context FED+context-FEDTEST
(Default context is context-FEDTEST.)


Number of classifiers: 0

