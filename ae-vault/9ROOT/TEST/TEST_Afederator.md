---
DESCRIPTORS:
  SYS+FEDERATION_NAMESdescriptor:
  - TEST+simple-ALPHA
  SYS+INSTANTIATORdescriptor: SYS+FEDERATORinstantiator
  SYS+descriptorSCRIPT$yaml:
  - target_requestid: SYS+INSTANTIATErequestid
    target_name: instantiator-SIMPLE
    name: GAMMAsimple
  - target_requestid: SYS+ADD_DESCRIPTORrequestid
    target_name: simple-ALPHA
    descriptor: descriptor-DEGREE_OF_POLISH$str
    descriptor-value: MIDDLING
  - target_requestid: SYS+ADD_CLASSIFIERrequestid
    target_name: simple-ALPHA
    classifier: APPLICATIONclassifier
    classifier-value: FIDDLINGclassifier_value
  - target_requestid: SYS+ADD_DESCRIPTORrequestid
    target_name: GAMMAsimple
    descriptor: descriptor-DEGREE_OF_POLISH$str
    descriptor-value: MIDDLING
  - target_requestid: SYS+ADD_CLASSIFIERrequestid
    target_name: GAMMAsimple
    classifier: BASICrelation
    classifier-value: simple-ALPHA
  - target_requestid: SYS+ADD_CLASSIFIERrequestid
    target_name: GAMMAsimple
    classifier: APPLICATIONclassifier
    classifier-value: FIDDLINGclassifier_value
  SYS+descriptor_mapREQUESTID$operationid:
    SYS+ENTITY_REPORTrequestid:
    - ENTITY_REPORToperationid
    SYS+RUN_FEDERATIONrequestid:
    - RUN_FEDERATIONoperationid
tags:
- SYS+ENTITY_TYPEclassifier/SYS+FEDERATORclassifier_value
---
# Entity TEST+TEST_Afederator

