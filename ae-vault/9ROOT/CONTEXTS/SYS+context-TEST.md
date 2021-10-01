---
DESCRIPTORS:
  descriptor_map-REQUESTID$operationid:
    requestid-ENTITY_REPORT:
    - CONTEXT_REPORToperationid
    requestid-LOAD_SCRIPT:
    - LOAD_SCRIPToperationid
    requestid-REGISTER_CLASSIFIER:
    - REGISTER_CLASSIFIERoperationid
    requestid-REGISTER_ENTITY:
    - REGISTER_ENTITYoperationid
    requestid-ROUTE:
    - ROUTEoperationid
TAGS:
- SYS+classifier-ENTITY_TYPE/SYS+classifier_value-CONTEXT
- SYS+classifier-INSTANTIATOR/SYS+instantiator-CONTEXT
- SYS+classifier-RESOURCES/ROOT+context-SYS
---
# Entity SYS+context-TEST

this is a test same line 
different line

---
1. Registered Entities of context SYS+context-TEST
(Default context is context-TEST.)

TEST+classifier_value-SIMPLE
TEST+descriptor-DEGREE_OF_POLISH$str
TEST+federator-A
TEST+instantiator-SIMPLE
TEST+relation-BASIC
TEST+simple-ALPHA
TEST+simple-BETA

Number of entities: 7

2. Classifier Values of context SYS+context-TEST
(Default context is context-TEST.)

classifier:  SYS+classifier-ENTITY_TYPE
  value:       SYS+classifier_value-CLASSIFIER
    entity:      TEST+relation-BASIC
  value:       SYS+classifier_value-CLASSIFIER_VALUE
    entity:      TEST+classifier_value-SIMPLE
  value:       SYS+classifier_value-DESCRIPTOR
    entity:      TEST+descriptor-DEGREE_OF_POLISH$str
  value:       SYS+classifier_value-FEDERATOR
    entity:      TEST+federator-A
  value:       SYS+classifier_value-INSTANTIATOR
    entity:      TEST+instantiator-SIMPLE
  value:       TEST+classifier_value-SIMPLE
    entity:      TEST+simple-ALPHA
    entity:      TEST+simple-BETA
classifier:  SYS+classifier-INSTANTIATOR
  value:       SYS+instantiator-CLASSIFIER_VALUE
    entity:      TEST+classifier_value-SIMPLE
  value:       SYS+instantiator-DESCRIPTOR
    entity:      TEST+descriptor-DEGREE_OF_POLISH$str
  value:       SYS+instantiator-FEDERATOR
    entity:      TEST+federator-A
  value:       SYS+instantiator-INSTANTIATOR
    entity:      TEST+instantiator-SIMPLE
  value:       SYS+instantiator-RELATION
    entity:      TEST+relation-BASIC
  value:       TEST+instantiator-SIMPLE
    entity:      TEST+simple-ALPHA
    entity:      TEST+simple-BETA

Number of classifiers: 2

