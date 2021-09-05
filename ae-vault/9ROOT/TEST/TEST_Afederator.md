---
DESCRIPTORS:
  SYS+FEDERATION_NAMESdescriptor:
  - TEST+ALPHAsimple
  SYS+INSTANTIATORdescriptor: SYS+FEDERATORinstantiator
  SYS+SCRIPTdescriptor:
  - name: TEST+GAMMAsimple
    target_name: TEST+SIMPLEinstantiator
    target_requestid: SYS+INSTANTIATErequestid
  - descriptor: TEST+DEGREE_OF_POLISHdescriptor
    descriptor-value: MIDDLING
    target_name: TEST+ALPHAsimple
    target_requestid: SYS+ADD_DESCRIPTORrequestid
  - classifier: TEST+APPLICATIONclassifier
    classifier-value: TEST+FIDDLINGclassifier_value
    target_name: TEST+ALPHAsimple
    target_requestid: SYS+ADD_CLASSIFIERrequestid
  - descriptor: TEST+DEGREE_OF_POLISHdescriptor
    descriptor-value: MIDDLING
    target_name: TEST+GAMMAsimple
    target_requestid: SYS+ADD_DESCRIPTORrequestid
  - classifier: TEST+BASICrelation
    classifier-value: TEST+ALPHAsimple
    target_name: TEST+GAMMAsimple
    target_requestid: SYS+ADD_CLASSIFIERrequestid
  - classifier: TEST+APPLICATIONclassifier
    classifier-value: TEST+FIDDLINGclassifier_value
    target_name: TEST+GAMMAsimple
    target_requestid: SYS+ADD_CLASSIFIERrequestid
  SYS+descriptor_mapREQUESTID$operationid:
    SYS+ENTITY_REPORTrequestid:
    - ENTITY_REPORToperationid
    SYS+RUN_FEDERATIONrequestid:
    - RUN_FEDERATIONoperationid
tags:
- SYS+ENTITY_TYPEclassifier/SYS+FEDERATORclassifier_value
---
# Entity TEST+TEST_Afederator

