---
DESCRIPTORS:
  SYS+REQUESTID_MAP:
    SYS+REGISTER_ENTITYrequestid:
    - REGISTER_ENTITYoperationid
    SYS+ROUTErequestid:
    - ROUTEoperationid
    SYS+REGISTER_CLASSIFIERrequestid:
    - REGISTER_CLASSIFIERoperationid
    SYS+ENTITY_REPORTrequestid:
    - CONTEXT_REPORToperationid
  SYS+INSTANTIATORdescriptor: SYS+CONTEXTinstantiator
tags:
- SYS+ENTITY_TYPEclassifier/SYS+CONTEXTclassifier_value
---
# Context Report for TEST

1. Registered Entities of context TEST
(Default context is TEST.)

+ALPHAsimple
+APPLICATIONclassifier
+BASICrelation
+BETAsimple
+DEGREE_OF_POLISHdescriptor
+FIDDLINGclassifier_value
+SIMPLEclassifier_value
+SIMPLEinstantiator
+TEST_Afederator

Number of entities: 9

2. Classifier Values of context TEST
(Default context is TEST.)

classifier:    SYS+ENTITY_TYPEclassifier
     value:        +SIMPLEclassifier_value
    entity:            +ALPHAsimple
    entity:            +BETAsimple
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

Number of classifiers: 1

