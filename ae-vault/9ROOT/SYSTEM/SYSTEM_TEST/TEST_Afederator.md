---
DESCRIPTORS:
  SYSTEMcontext+REQUESTID_MAP:
    SYSTEMcontext+RUN_FEDERATIONrequestid:
    - RUN_FEDERATIONoperationid
    SYSTEMcontext+ENTITY_REPORTrequestid:
    - ENTITY_REPORToperationid
  SYSTEMcontext+INSTANTIATORdescriptor: SYSTEMcontext+FEDERATORinstantiator
  SYSTEMcontext+FEDERATION_NAMESdescriptor:
  - SYSTEM_TESTcontext+ALPHAsimple
  SYSTEMcontext+SCRIPTdescriptor:
  - target_requestid: SYSTEMcontext+INSTANTIATErequestid
    target_name: SYSTEM_TESTcontext+SIMPLEinstantiator
    name: SYSTEM_TESTcontext+GAMMAsimple
  - target_requestid: SYSTEMcontext+ADD_DESCRIPTORrequestid
    target_name: SYSTEM_TESTcontext+ALPHAsimple
    descriptor: SYSTEM_TESTcontext+DEGREE_OF_POLISHdescriptor
    descriptor-value: MIDDLING
  - target_requestid: SYSTEMcontext+ADD_CLASSIFIERrequestid
    target_name: SYSTEM_TESTcontext+ALPHAsimple
    classifier: SYSTEM_TESTcontext+APPLICATIONclassifier
    classifier-value: SYSTEM_TESTcontext+FIDDLINGclassifier_value
  - target_requestid: SYSTEMcontext+ADD_DESCRIPTORrequestid
    target_name: SYSTEM_TESTcontext+GAMMAsimple
    descriptor: SYSTEM_TESTcontext+DEGREE_OF_POLISHdescriptor
    descriptor-value: MIDDLING
  - target_requestid: SYSTEMcontext+ADD_CLASSIFIERrequestid
    target_name: SYSTEM_TESTcontext+GAMMAsimple
    classifier: SYSTEM_TESTcontext+BASICrelation
    classifier-value: SYSTEM_TESTcontext+ALPHAsimple
  - target_requestid: SYSTEMcontext+ADD_CLASSIFIERrequestid
    target_name: SYSTEM_TESTcontext+GAMMAsimple
    classifier: SYSTEM_TESTcontext+APPLICATIONclassifier
    classifier-value: SYSTEM_TESTcontext+FIDDLINGclassifier_value
tags:
- SYSTEMcontext+ENTITY_TYPEclassifier/SYSTEMcontext+FEDERATORclassifier_value
---
# Entity Report for SYSTEM_TESTcontext+TEST_Afederator
