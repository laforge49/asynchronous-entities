---
DESCRIPTORS:
  SYS+INSTANTIATORdescriptor: SYS+instantiator-CONTEXT
  SYS+descriptor_map-REQUESTID$operationid:
    SYS+requestid-ENTITY_REPORT:
    - CONTEXT_REPORToperationid
    SYS+requestid-LOAD_SCRIPT:
    - LOAD_SCRIPToperationid
    SYS+requestid-REGISTER_CLASSIFIER:
    - REGISTER_CLASSIFIERoperationid
    SYS+requestid-REGISTER_ENTITY:
    - REGISTER_ENTITYoperationid
    SYS+requestid-ROUTE:
    - ROUTEoperationid
tags:
- SYS+classifier-ENTITY_TYPE/SYS+classifier_value-CONTEXT
---
# Entity SYS+TEST

this is a test same line 
different line

---
1. Registered Entities of context SYS+TEST
(Default context is TEST.)

+classifier-APPLICATION
+classifier_value-FIDDLING
+classifier_value-SIMPLE
+descriptor-DEGREE_OF_POLISH$str
+federator-A
+instantiator-SIMPLE
+relation-BASIC
+simple-ALPHA
+simple-BETA
+simple-GAMMA

Number of entities: 10

2. Classifier Values of context SYS+TEST
(Default context is TEST.)

classifier:    +classifier-APPLICATION
     value:        +classifier_value-FIDDLING
    entity:            +simple-ALPHA
classifier:    +relation-BASIC
     value:        +simple-ALPHA
    entity:            +simple-GAMMA
classifier:    SYS+classifier-ENTITY_TYPE
     value:        +classifier_value-SIMPLE
    entity:            +simple-ALPHA
    entity:            +simple-BETA
    entity:            +simple-GAMMA
     value:        SYS+classifier_value-CLASSIFIER
    entity:            +classifier-APPLICATION
    entity:            +relation-BASIC
     value:        SYS+classifier_value-CLASSIFIER_VALUE
    entity:            +classifier_value-FIDDLING
    entity:            +classifier_value-SIMPLE
     value:        SYS+classifier_value-DESCRIPTOR
    entity:            +descriptor-DEGREE_OF_POLISH$str
     value:        SYS+classifier_value-FEDERATOR
    entity:            +federator-A
     value:        SYS+classifier_value-INSTANTIATOR
    entity:            +instantiator-SIMPLE

Number of classifiers: 3

