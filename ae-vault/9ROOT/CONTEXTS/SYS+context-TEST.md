---
FACETS:
  DESCRIPTORS:
    descriptor_mapvec-REQUESTS^requestid$str:
      requestid-ENTITYreport:
      - CONTEXT_REPORToperationid
      requestid-LOADscript:
      - LOAD_SCRIPToperationid
      requestid-REGISTERentity:
      - REGISTER_ENTITYoperationid
      requestid-ROUTE:
      - ROUTEoperationid
  ENTITY-PUBLIC-REQUEST-PORTS:
    class-SIMPLE: clojure.core.async.chan
    classifierVALUE-SIMPLE: clojure.core.async.chan
    descriptor-DEGREEofPOLISH$str: clojure.core.async.chan
    federator-A: clojure.core.async.chan
    relation_vec-BASIC: clojure.core.async.chan
    simple-ALPHA: clojure.core.async.chan
    simple-BETA: clojure.core.async.chan
    simple-GAMMA: clojure.core.async.chan
  REQUEST-PORT-STACK:
  - clojure.core.async.chan
  facet-CLASSIFIERS^classifier:
    classifier-CLASS: class-CONTEXT
    classifier-ENTITYtype&classifierVALUE: classifierVALUE-CONTEXT
    classifier_vec-RESOURCES&context:
    - ROOT+context-SYS
  facet-CONTENT$str: "this is a test same line \ndifferent line"
  facet-NAME&?: context-TEST
---
# Entity SYS+context-TEST

this is a test same line 
different line

---
1. Registered Entities of context SYS+context-TEST
(Default context is context-TEST.)

TEST+class-SIMPLE
TEST+classifierVALUE-SIMPLE
TEST+descriptor-DEGREEofPOLISH$str
TEST+federator-A
TEST+relation_vec-BASIC
TEST+simple-ALPHA
TEST+simple-BETA
TEST+simple-GAMMA

Number of entities: 8

2. Classifier Values of context SYS+context-TEST
(Default context is context-TEST.)

classifier:  SYS+classifier-CLASS
  value:       SYS+class-CLASS
    entity:      TEST+class-SIMPLE
  value:       SYS+class-CLASSIFIERvalue
    entity:      TEST+classifierVALUE-SIMPLE
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
classifier:  SYS+classifier-ENTITYtype&classifierVALUE
  value:       SYS+classifierVALUE-CLASS
    entity:      TEST+class-SIMPLE
  value:       SYS+classifierVALUE-CLASSIFIER
    entity:      TEST+relation_vec-BASIC
  value:       SYS+classifierVALUE-CLASSIFIERvalue
    entity:      TEST+classifierVALUE-SIMPLE
  value:       SYS+classifierVALUE-DESCRIPTOR
    entity:      TEST+descriptor-DEGREEofPOLISH$str
  value:       SYS+classifierVALUE-FEDERATOR
    entity:      TEST+federator-A
  value:       TEST+classifierVALUE-SIMPLE
    entity:      TEST+simple-ALPHA
    entity:      TEST+simple-BETA
    entity:      TEST+simple-GAMMA

Number of classifiers: 2

