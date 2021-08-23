---
DESCRIPTORS:
  SYSTEMcontext+REQUESTID_MAP:
    SYSTEMcontext+REGISTER_ENTITYrequestid:
    - REGISTER_ENTITYoperationid
    SYSTEMcontext+ROUTErequestid:
    - ROUTEoperationid
    SYSTEMcontext+REGISTER_CLASSIFIERrequestid:
    - REGISTER_CLASSIFIERoperationid
    SYSTEMcontext+ENTITY_REPORTrequestid:
    - CONTEXT_REPORToperationid
  SYSTEMcontext+INSTANTIATORdescriptor: SYSTEMcontext+CONTEXTinstantiator
tags:
- SYSTEMcontext+ENTITY_TYPEclassifier/SYSTEMcontext+CONTEXTclassifier_value
---
# Context Report for SYSTEMcontext+SYSTEM_TESTcontext

1. Registered Entities of context SYSTEMcontext+SYSTEM_TESTcontext
(Default context is SYSTEM_TESTcontext.)

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

2. Classifier Values of context SYSTEMcontext+SYSTEM_TESTcontext
(Default context is SYSTEM_TESTcontext.)

classifier:    +APPLICATIONclassifier
     value:        +FIDDLINGclassifier_value
    entity:            +ALPHAsimple
    entity:            +GAMMAsimple
classifier:    +BASICrelation
     value:        +ALPHAsimple
    entity:            +GAMMAsimple
classifier:    SYSTEMcontext+ENTITY_TYPEclassifier
     value:        +SIMPLEclassifier_value
    entity:            +ALPHAsimple
    entity:            +BETAsimple
    entity:            +GAMMAsimple
     value:        SYSTEMcontext+CLASSIFIER_VALUEclassifier_value
    entity:            +FIDDLINGclassifier_value
    entity:            +SIMPLEclassifier_value
     value:        SYSTEMcontext+CLASSIFIERclassifier_value
    entity:            +APPLICATIONclassifier
    entity:            +BASICrelation
     value:        SYSTEMcontext+DESCRIPTORclassifier_value
    entity:            +DEGREE_OF_POLISHdescriptor
     value:        SYSTEMcontext+FEDERATORclassifier_value
    entity:            +TEST_Afederator
     value:        SYSTEMcontext+INSTANTIATORclassifier_value
    entity:            +SIMPLEinstantiator

Number of classifiers: 3
