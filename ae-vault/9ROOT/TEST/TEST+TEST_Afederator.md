---
DESCRIPTORS:
  SYS+REQUESTID_MAP:
    SYS+RUN_FEDERATIONrequestid:
    - RUN_FEDERATIONoperationid
    SYS+ENTITY_REPORTrequestid:
    - ENTITY_REPORToperationid
  SYS+INSTANTIATORdescriptor: SYS+FEDERATORinstantiator
  SYS+FEDERATION_NAMESdescriptor:
  - TEST+ALPHAsimple
  SYS+SCRIPTdescriptor:
  - target_requestid: SYS+INSTANTIATErequestid
    target_name: TEST+SIMPLEinstantiator
    name: TEST+GAMMAsimple
  - target_requestid: SYS+ADD_DESCRIPTORrequestid
    target_name: TEST+ALPHAsimple
    descriptor: TEST+DEGREE_OF_POLISHdescriptor
    descriptor-value: MIDDLING
  - target_requestid: SYS+ADD_CLASSIFIERrequestid
    target_name: TEST+ALPHAsimple
    classifier: TEST+APPLICATIONclassifier
    classifier-value: TEST+FIDDLINGclassifier_value
  - target_requestid: SYS+ADD_DESCRIPTORrequestid
    target_name: TEST+GAMMAsimple
    descriptor: TEST+DEGREE_OF_POLISHdescriptor
    descriptor-value: MIDDLING
  - target_requestid: SYS+ADD_CLASSIFIERrequestid
    target_name: TEST+GAMMAsimple
    classifier: TEST+BASICrelation
    classifier-value: TEST+ALPHAsimple
  - target_requestid: SYS+ADD_CLASSIFIERrequestid
    target_name: TEST+GAMMAsimple
    classifier: TEST+APPLICATIONclassifier
    classifier-value: TEST+FIDDLINGclassifier_value
tags:
- SYS+ENTITY_TYPEclassifier/SYS+FEDERATORclassifier_value
---
# Entity TEST+TEST_Afederator

