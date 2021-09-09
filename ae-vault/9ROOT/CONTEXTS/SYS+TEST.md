---
DESCRIPTORS:
  SYS+INSTANTIATORdescriptor: SYS+CONTEXTinstantiator
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
- SYS+ENTITY_TYPEclassifier/SYS+CONTEXTclassifier_value
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
classifier:    SYS+ENTITY_TYPEclassifier
     value:        +classifier_value-SIMPLE
    entity:            +simple-ALPHA
    entity:            +simple-BETA
    entity:            +simple-GAMMA
     value:        SYS+CLASSIFIER_VALUEclassifier_value
    entity:            +classifier_value-FIDDLING
    entity:            +classifier_value-SIMPLE
     value:        SYS+CLASSIFIERclassifier_value
    entity:            +classifier-APPLICATION
    entity:            +relation-BASIC
     value:        SYS+DESCRIPTORclassifier_value
    entity:            +descriptor-DEGREE_OF_POLISH$str
     value:        SYS+FEDERATORclassifier_value
    entity:            +federator-A
     value:        SYS+INSTANTIATORclassifier_value
    entity:            +instantiator-SIMPLE

Number of classifiers: 3

