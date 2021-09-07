---
DESCRIPTORS:
  SYS+FEDERATION_NAMESdescriptor:
  - TEST+ALPHAsimple
  SYS+INSTANTIATORdescriptor: SYS+FEDERATORinstantiator
  SYS+descriptorSCRIPT$yaml:
  - target_requestid: SYS+INSTANTIATErequestid
    target_name: +SIMPLEinstantiator
    name: +GAMMAsimple
  - target_requestid: SYS+ADD_DESCRIPTORrequestid
    target_name: +ALPHAsimple
    descriptor: +DEGREE_OF_POLISHdescriptor
    descriptor-value: MIDDLING
  - target_requestid: SYS+ADD_CLASSIFIERrequestid
    target_name: +ALPHAsimple
    classifier: +APPLICATIONclassifier
    classifier-value: +FIDDLINGclassifier_value
  - target_requestid: SYS+ADD_DESCRIPTORrequestid
    target_name: +GAMMAsimple
    descriptor: +DEGREE_OF_POLISHdescriptor
    descriptor-value: MIDDLING
  - target_requestid: SYS+ADD_CLASSIFIERrequestid
    target_name: +GAMMAsimple
    classifier: +BASICrelation
    classifier-value: +ALPHAsimple
  - target_requestid: SYS+ADD_CLASSIFIERrequestid
    target_name: +GAMMAsimple
    classifier: +APPLICATIONclassifier
    classifier-value: +FIDDLINGclassifier_value
  SYS+descriptor_mapREQUESTID$operationid:
    SYS+ENTITY_REPORTrequestid:
    - ENTITY_REPORToperationid
    SYS+RUN_FEDERATIONrequestid:
    - RUN_FEDERATIONoperationid
tags:
- SYS+ENTITY_TYPEclassifier/SYS+FEDERATORclassifier_value
---
# Entity TEST+TEST_Afederator

