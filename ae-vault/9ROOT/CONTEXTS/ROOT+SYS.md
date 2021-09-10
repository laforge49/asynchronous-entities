---
DESCRIPTORS:
  SYS+descriptor_map-REQUESTID$operationid:
    SYS+requestid-REGISTER_ENTITY:
    - REGISTER_ENTITYoperationid
    SYS+requestid-ROUTE:
    - ROUTEoperationid
    SYS+requestid-REGISTER_CLASSIFIER:
    - REGISTER_CLASSIFIERoperationid
    SYS+requestid-ENTITY_REPORT:
    - CONTEXT_REPORToperationid
    SYS+requestid-LOAD_SCRIPT:
    - LOAD_SCRIPToperationid
---
# Entity ROOT+SYS

1. Registered Entities of context ROOT+SYS
(Default context is SYS.)

+TEST
+classifier-ENTITY_TYPE
+classifier_value-CLASSIFIER
+classifier_value-CLASSIFIER_VALUE
+classifier_value-CONTEXT
+classifier_value-DESCRIPTOR
+classifier_value-FEDERATION_CONTEXT
+classifier_value-FEDERATOR
+classifier_value-INSTANTIATOR
+classifier_value-REQUESTID
+descriptor-INSTANTIATION_CLASSIFIERS
+descriptor-INSTANTIATION_DESCRIPTORS
+descriptor-INVARIANT$bool
+descriptor-READ_ONLY$bool
+descriptor_map-REQUESTID$operationid
+instantiator-CLASSIFIER
+instantiator-CLASSIFIER_VALUE
+instantiator-CONTEXT
+instantiator-DATA_TYPE
+instantiator-DESCRIPTOR
+instantiator-FEDERATION_CONTEXT
+instantiator-FEDERATOR
+instantiator-INSTANTIATOR
+instantiator-RELATION
+instantiator-REQUESTID
+requestid-ACQUIRE
+requestid-ADD_CLASSIFIERS
+requestid-ADD_DESCRIPTORS
+requestid-CONTEXT_REPORT
+requestid-INSTANTIATE
+requestid-REGISTER_CLASSIFIER
+requestid-REGISTER_ENTITY
+requestid-RELEASE
+requestid-ROUTE
+requestid-RUN_FEDERATION

Number of entities: 35

2. Classifier Values of context ROOT+SYS
(Default context is SYS.)

classifier:    +classifier-ENTITY_TYPE
     value:        +classifier_value-CLASSIFIER
    entity:            +classifier-ENTITY_TYPE
     value:        +classifier_value-CLASSIFIER_VALUE
    entity:            +classifier_value-CLASSIFIER
    entity:            +classifier_value-CLASSIFIER_VALUE
    entity:            +classifier_value-CONTEXT
    entity:            +classifier_value-DESCRIPTOR
    entity:            +classifier_value-FEDERATION_CONTEXT
    entity:            +classifier_value-FEDERATOR
    entity:            +classifier_value-INSTANTIATOR
    entity:            +classifier_value-REQUESTID
     value:        +classifier_value-CONTEXT
    entity:            +TEST
     value:        +classifier_value-DESCRIPTOR
    entity:            +descriptor-INSTANTIATION_CLASSIFIERS
    entity:            +descriptor-INSTANTIATION_DESCRIPTORS
    entity:            +descriptor-INVARIANT$bool
    entity:            +descriptor-READ_ONLY$bool
    entity:            +descriptor_map-REQUESTID$operationid
     value:        +classifier_value-INSTANTIATOR
    entity:            +instantiator-CLASSIFIER
    entity:            +instantiator-CLASSIFIER_VALUE
    entity:            +instantiator-CONTEXT
    entity:            +instantiator-DATA_TYPE
    entity:            +instantiator-DESCRIPTOR
    entity:            +instantiator-FEDERATION_CONTEXT
    entity:            +instantiator-FEDERATOR
    entity:            +instantiator-INSTANTIATOR
    entity:            +instantiator-RELATION
    entity:            +instantiator-REQUESTID
     value:        +classifier_value-REQUESTID
    entity:            +requestid-ACQUIRE
    entity:            +requestid-ADD_CLASSIFIERS
    entity:            +requestid-ADD_DESCRIPTORS
    entity:            +requestid-CONTEXT_REPORT
    entity:            +requestid-INSTANTIATE
    entity:            +requestid-REGISTER_CLASSIFIER
    entity:            +requestid-REGISTER_ENTITY
    entity:            +requestid-RELEASE
    entity:            +requestid-ROUTE
    entity:            +requestid-RUN_FEDERATION

Number of classifiers: 1

