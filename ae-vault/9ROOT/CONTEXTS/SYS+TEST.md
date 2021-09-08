---
DESCRIPTORS:
  SYS+INSTANTIATORdescriptor: SYS+CONTEXTinstantiator
  SYS+descriptor_mapREQUESTID$operationid:
    SYS+ENTITY_REPORTrequestid:
    - CONTEXT_REPORToperationid
    SYS+LOAD_SCRIPTrequestid:
    - LOAD_SCRIPToperationid
    SYS+REGISTER_CLASSIFIERrequestid:
    - REGISTER_CLASSIFIERoperationid
    SYS+REGISTER_ENTITYrequestid:
    - REGISTER_ENTITYoperationid
    SYS+ROUTErequestid:
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

+APPLICATIONclassifier
+BASICrelation
+BETAsimple
+FIDDLINGclassifier_value
+GAMMAsimple
+TEST_Afederator
+classifier_value-SIMPLE
+descriptor-DEGREE_OF_POLISH$str
+instantiator-SIMPLE
+simple-ALPHA

Number of entities: 10

2. Classifier Values of context SYS+TEST
(Default context is TEST.)

classifier:    +APPLICATIONclassifier
     value:        +FIDDLINGclassifier_value
    entity:            +simple-ALPHA
classifier:    +BASICrelation
     value:        +simple-ALPHA
    entity:            +GAMMAsimple
classifier:    SYS+ENTITY_TYPEclassifier
     value:        +classifier_value-SIMPLE
    entity:            +BETAsimple
    entity:            +GAMMAsimple
    entity:            +simple-ALPHA
     value:        SYS+CLASSIFIER_VALUEclassifier_value
    entity:            +FIDDLINGclassifier_value
    entity:            +classifier_value-SIMPLE
     value:        SYS+CLASSIFIERclassifier_value
    entity:            +APPLICATIONclassifier
    entity:            +BASICrelation
     value:        SYS+DESCRIPTORclassifier_value
    entity:            +descriptor-DEGREE_OF_POLISH$str
     value:        SYS+FEDERATORclassifier_value
    entity:            +TEST_Afederator
     value:        SYS+INSTANTIATORclassifier_value
    entity:            +instantiator-SIMPLE

Number of classifiers: 3

