---
FACETS:
  ENTITY-PUBLIC-REQUEST-PORTS:
    requestid-REGISTERentity: clojure.core.async.chan
    class-CLASSIFIERvalue: clojure.core.async.chan
    descriptor-READonly$bool: clojure.core.async.chan
    requestid-RUNfederation: clojure.core.async.chan
    class-RELATION: clojure.core.async.chan
    class-DATAtype: clojure.core.async.chan
    requestid-ADDdescriptors: clojure.core.async.chan
    classifierVALUE-DESCRIPTOR: clojure.core.async.chan
    classifier-ENTITYtype&classifierVALUE: clojure.core.async.chan
    classifierVALUE-CLASSIFIER: clojure.core.async.chan
    requestid-ROUTE: clojure.core.async.chan
    class-REQUESTID: clojure.core.async.chan
    classifierVALUE-CLASS: clojure.core.async.chan
    class-CLASS: clojure.core.async.chan
    descriptor_map-INSTANCE^descriptor: clojure.core.async.chan
    requestid-INSTANTIATE: clojure.core.async.chan
    class-DESCRIPTOR: clojure.core.async.chan
    class-CLASSIFIER: clojure.core.async.chan
    classifierVALUE-FEDERATOR: clojure.core.async.chan
    descriptor_mapvec-REQUESTS^requestid$str: clojure.core.async.chan
    classifierVALUE-FEDERATIONcontext: clojure.core.async.chan
    requestid-ADDrelations: clojure.core.async.chan
    class-FEDERATOR: clojure.core.async.chan
    class-CONTEXT: clojure.core.async.chan
    classifierVALUE-CLASSIFIERvalue: clojure.core.async.chan
    descriptor-INVARIANT$bool: clojure.core.async.chan
    classifierVALUE-CONTEXT: clojure.core.async.chan
    descriptor_map-INSTANCE^classifier: clojure.core.async.chan
    classifierVALUE-REQUESTID: clojure.core.async.chan
    context-TEST: clojure.core.async.chan
  REQUEST-PORT-STACK:
  - clojure.core.async.chan
  SYS+facet-CONTENT$str: ''
  SYS+facet-DESCRIPTORS^descriptor:
    SYS+descriptor_mapvec-REQUESTS^requestid$str:
      SYS+requestid-ENTITYreport:
      - CONTEXT_REPORToperationid
      SYS+requestid-LOADscript:
      - LOAD_SCRIPToperationid
      SYS+requestid-REGISTERentity:
      - REGISTER_ENTITYoperationid
      SYS+requestid-ROUTE:
      - ROUTEoperationid
  SYS+facet-NAME&?: context-SYS
---
# Entity ROOT+context-SYS

1. Registered Entities of context ROOT+context-SYS
(Default context is context-SYS.)

SYS+class-CLASS
SYS+class-CLASSIFIER
SYS+class-CLASSIFIERvalue
SYS+class-CONTEXT
SYS+class-DATAtype
SYS+class-DESCRIPTOR
SYS+class-FEDERATOR
SYS+class-RELATION
SYS+class-REQUESTID
SYS+classifier-ENTITYtype&classifierVALUE
SYS+classifierVALUE-CLASS
SYS+classifierVALUE-CLASSIFIER
SYS+classifierVALUE-CLASSIFIERvalue
SYS+classifierVALUE-CONTEXT
SYS+classifierVALUE-DESCRIPTOR
SYS+classifierVALUE-FEDERATIONcontext
SYS+classifierVALUE-FEDERATOR
SYS+classifierVALUE-REQUESTID
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

Number of entities: 30

2. Classifier Values of context ROOT+context-SYS
(Default context is context-SYS.)

classifier:  SYS+classifier-CLASS
  value:       SYS+class-CLASS
    entity:      SYS+class-CLASSIFIER
    entity:      SYS+class-CLASSIFIERvalue
    entity:      SYS+class-CONTEXT
    entity:      SYS+class-DATAtype
    entity:      SYS+class-DESCRIPTOR
    entity:      SYS+class-FEDERATOR
    entity:      SYS+class-RELATION
    entity:      SYS+class-REQUESTID
  value:       SYS+class-CLASSIFIER
    entity:      SYS+classifier-ENTITYtype&classifierVALUE
  value:       SYS+class-CLASSIFIERvalue
    entity:      SYS+classifierVALUE-CLASS
    entity:      SYS+classifierVALUE-CLASSIFIER
    entity:      SYS+classifierVALUE-CLASSIFIERvalue
    entity:      SYS+classifierVALUE-CONTEXT
    entity:      SYS+classifierVALUE-DESCRIPTOR
    entity:      SYS+classifierVALUE-FEDERATIONcontext
    entity:      SYS+classifierVALUE-FEDERATOR
    entity:      SYS+classifierVALUE-REQUESTID
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
classifier:  SYS+classifier-ENTITYtype&classifierVALUE
  value:       SYS+classifierVALUE-CLASS
    entity:      SYS+class-CLASS
    entity:      SYS+class-CLASSIFIER
    entity:      SYS+class-CLASSIFIERvalue
    entity:      SYS+class-CONTEXT
    entity:      SYS+class-DATAtype
    entity:      SYS+class-DESCRIPTOR
    entity:      SYS+class-FEDERATOR
    entity:      SYS+class-RELATION
    entity:      SYS+class-REQUESTID
  value:       SYS+classifierVALUE-CLASSIFIER
    entity:      SYS+classifier-ENTITYtype&classifierVALUE
  value:       SYS+classifierVALUE-CLASSIFIERvalue
    entity:      SYS+classifierVALUE-CLASS
    entity:      SYS+classifierVALUE-CLASSIFIER
    entity:      SYS+classifierVALUE-CLASSIFIERvalue
    entity:      SYS+classifierVALUE-CONTEXT
    entity:      SYS+classifierVALUE-DESCRIPTOR
    entity:      SYS+classifierVALUE-FEDERATIONcontext
    entity:      SYS+classifierVALUE-FEDERATOR
    entity:      SYS+classifierVALUE-REQUESTID
  value:       SYS+classifierVALUE-CONTEXT
    entity:      SYS+context-TEST
  value:       SYS+classifierVALUE-DESCRIPTOR
    entity:      SYS+descriptor-INVARIANT$bool
    entity:      SYS+descriptor-READonly$bool
    entity:      SYS+descriptor_map-INSTANCE^classifier
    entity:      SYS+descriptor_map-INSTANCE^descriptor
    entity:      SYS+descriptor_mapvec-REQUESTS^requestid$str
  value:       SYS+classifierVALUE-REQUESTID
    entity:      SYS+requestid-ADDdescriptors
    entity:      SYS+requestid-ADDrelations
    entity:      SYS+requestid-INSTANTIATE
    entity:      SYS+requestid-REGISTERentity
    entity:      SYS+requestid-ROUTE
    entity:      SYS+requestid-RUNfederation
classifier:  SYS+classifier_vec-RESOURCES&context
  value:       ROOT+context-SYS
    entity:      SYS+context-TEST

Number of classifiers: 3

