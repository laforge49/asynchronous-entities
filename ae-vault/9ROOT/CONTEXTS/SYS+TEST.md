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

SYS+this is a test same line 
different line

---
1. Registered Entities of context SYS+TEST
(Default context is TEST.)

+ALPHAsimple
+APPLICATIONclassifier
+BASICrelation
+BETAsimple
+DEGREE_OF_POLISHdescriptor
+FIDDLINGclassifier_value
+GAMMAsimple
+SIMPLEclassifier_value
+SIMPLEinstantiator
+TEST_Afederator

Number of entities: 10

2. Classifier Values of context SYS+TEST
(Default context is TEST.)

classifier:    +APPLICATIONclassifier
     value:        +FIDDLINGclassifier_value
    entity:            +ALPHAsimple
    entity:            +GAMMAsimple
classifier:    +BASICrelation
     value:        +ALPHAsimple
    entity:            +GAMMAsimple
classifier:    SYS+ENTITY_TYPEclassifier
     value:        +SIMPLEclassifier_value
    entity:            +ALPHAsimple
    entity:            +BETAsimple
    entity:            +GAMMAsimple
     value:        SYS+CLASSIFIER_VALUEclassifier_value
    entity:            +FIDDLINGclassifier_value
    entity:            +SIMPLEclassifier_value
     value:        SYS+CLASSIFIERclassifier_value
    entity:            +APPLICATIONclassifier
    entity:            +BASICrelation
     value:        SYS+DESCRIPTORclassifier_value
    entity:            +DEGREE_OF_POLISHdescriptor
     value:        SYS+FEDERATORclassifier_value
    entity:            +TEST_Afederator
     value:        SYS+INSTANTIATORclassifier_value
    entity:            +SIMPLEinstantiator

Number of classifiers: 3

