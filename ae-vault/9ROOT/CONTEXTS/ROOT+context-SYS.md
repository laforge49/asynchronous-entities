---
SYS+gem_map-FACETS^facet:
  SYS+facet-CONTENT$str: ''
  SYS+facet-NAME&?: context-SYS
  SYS+facet_map-DESCRIPTORS^descriptor:
    SYS+descriptor_mapvec-REQUESTS^requestid$str:
      SYS+requestid-ENTITYreport:
      - CONTEXT_REPORToperationid
      SYS+requestid-LOADscript:
      - LOAD_SCRIPToperationid
      SYS+requestid-REGISTERentity:
      - REGISTER_ENTITYoperationid
      SYS+requestid-ROUTE:
      - ROUTEoperationid
  SYS+facet_map?-ENTITYpublicREQUESTports^?$chan:
    SYS+requestid-INSTANTIATE: clojure.core.async.chan
    SYS+descriptor_map-INSTANCE^descriptor: clojure.core.async.chan
    SYS+descriptor_mapvec-REQUESTS^requestid$str: clojure.core.async.chan
    SYS+class-FEDERATOR: clojure.core.async.chan
    SYS+requestid-ADDdescriptors: clojure.core.async.chan
    SYS+requestid-RUNfederation: clojure.core.async.chan
    SYS+class-CLASSIFIER: clojure.core.async.chan
    SYS+class-RELATION: clojure.core.async.chan
    SYS+class-DESCRIPTOR: clojure.core.async.chan
    SYS+requestid-ADDrelations: clojure.core.async.chan
    SYS+descriptor_map-INSTANCE^classifier: clojure.core.async.chan
    SYS+requestid-REGISTERentity: clojure.core.async.chan
    SYS+class-CLASS: clojure.core.async.chan
    SYS+requestid-ROUTE: clojure.core.async.chan
    SYS+class-CONTEXT: clojure.core.async.chan
    SYS+class-REQUESTID: clojure.core.async.chan
    SYS+descriptor-READonly$bool: clojure.core.async.chan
    SYS+descriptor-INVARIANT$bool: clojure.core.async.chan
    SYS+context-TEST: clojure.core.async.chan
  SYS+facet_vec-REQUESTportSTACK$chan:
  - clojure.core.async.chan
---
# Entity ROOT+context-SYS

1. Registered Entities of context ROOT+context-SYS
(Default context is context-SYS.)

SYS+class-CLASS
SYS+class-CLASSIFIER
SYS+class-CONTEXT
SYS+class-DESCRIPTOR
SYS+class-FEDERATOR
SYS+class-RELATION
SYS+class-REQUESTID
SYS+context-TEST
SYS+descriptor-INVARIANT$bool
SYS+descriptor-READonly$bool
SYS+descriptor_map-INSTANCE^classifier
SYS+descriptor_map-INSTANCE^descriptor
SYS+descriptor_mapvec-REQUESTS^requestid$str
SYS+requestid-ADDdescriptors
SYS+requestid-ADDrelations
SYS+requestid-INSTANTIATE
SYS+requestid-REGISTERentity
SYS+requestid-ROUTE
SYS+requestid-RUNfederation

Number of entities: 19

2. Classifier Values of context ROOT+context-SYS
(Default context is context-SYS.)

classifier:  SYS+classifier-CLASS&class
  value:       SYS+class-CLASS
    entity:      SYS+class-CLASSIFIER
    entity:      SYS+class-CONTEXT
    entity:      SYS+class-DESCRIPTOR
    entity:      SYS+class-FEDERATOR
    entity:      SYS+class-RELATION
    entity:      SYS+class-REQUESTID
  value:       SYS+class-CONTEXT
    entity:      SYS+context-TEST
  value:       SYS+class-DESCRIPTOR
    entity:      SYS+descriptor-INVARIANT$bool
    entity:      SYS+descriptor-READonly$bool
    entity:      SYS+descriptor_map-INSTANCE^classifier
    entity:      SYS+descriptor_map-INSTANCE^descriptor
    entity:      SYS+descriptor_mapvec-REQUESTS^requestid$str
  value:       SYS+class-REQUESTID
    entity:      SYS+requestid-ADDdescriptors
    entity:      SYS+requestid-ADDrelations
    entity:      SYS+requestid-INSTANTIATE
    entity:      SYS+requestid-REGISTERentity
    entity:      SYS+requestid-ROUTE
    entity:      SYS+requestid-RUNfederation
classifier:  SYS+classifier_vec-RESOURCES&context
  value:       ROOT+context-SYS
    entity:      SYS+context-TEST

Number of classifiers: 2

