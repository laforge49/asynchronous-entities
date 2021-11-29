---
gem_map-FACETS^facet:
  facet-CONTENT$str: "this is a test same line \ndifferent line"
  facet-NAME&?: context-TEST
  facet_map-CLASSIFIERS^classifier:
    classifier-CLASS&class: class-CONTEXT
    classifier_vec-RESOURCES&context:
    - ROOT+context-SYS
  facet_map-DESCRIPTORS^descriptor:
    descriptor_mapvec-REQUESTS^requestid$str:
      requestid-ENTITYreport:
      - CONTEXT_REPORToperationid
      requestid-LOADscript:
      - LOAD_SCRIPToperationid
      requestid-REGISTERentity:
      - REGISTER_ENTITYoperationid
      requestid-ROUTE:
      - ROUTEoperationid
  facet_map?-ENTITYpublicREQUESTports^?$chan:
    TEST+class-SIMPLE: clojure.core.async.chan
    TEST+descriptor-DEGREEofPOLISH$str: clojure.core.async.chan
    TEST+federator-A: clojure.core.async.chan
    TEST+relation_vec-BASIC: clojure.core.async.chan
    TEST+simple-ALPHA: clojure.core.async.chan
    TEST+simple-BETA: clojure.core.async.chan
    TEST+simple-GAMMA: clojure.core.async.chan
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

