---
DESCRIPTORS:
  SYS+FEDERATION_NAMESdescriptor:
  - TEST+simple-ALPHA
  SYS+INSTANTIATORdescriptor: SYS+FEDERATORinstantiator
  SYS+descriptorSCRIPT$yaml:
  - target_requestid: SYS+INSTANTIATErequestid
    target_name: instantiator-SIMPLE
    name: GAMMAsimple
  - target_requestid: SYS+ADD_DESCRIPTORSrequestid
    target_name: simple-ALPHA
    descriptors:
      descriptor-DEGREE_OF_POLISH$str: MIDDLING
  - target_requestid: SYS+ADD_CLASSIFIERSrequestid
    target_name: simple-ALPHA
    classifiers:
      APPLICATIONclassifier: FIDDLINGclassifier_value
  - target_requestid: SYS+ADD_DESCRIPTORSrequestid
    target_name: GAMMAsimple
    descriptors:
      descriptor-DEGREE_OF_POLISH$str: MIDDLING
  - target_requestid: SYS+ADD_CLASSIFIERSrequestid
    target_name: GAMMAsimple
    classifiers:
      BASICrelation: simple-ALPHA
  - target_requestid: SYS+ADD_CLASSIFIERSrequestid
    target_name: GAMMAsimple
    classifierS:
      APPLICATIONclassifier: FIDDLINGclassifier_value
  SYS+descriptor_mapREQUESTID$operationid:
    SYS+ENTITY_REPORTrequestid:
    - ENTITY_REPORToperationid
    SYS+RUN_FEDERATIONrequestid:
    - RUN_FEDERATIONoperationid
tags:
- SYS+ENTITY_TYPEclassifier/SYS+FEDERATORclassifier_value
---
# Entity TEST+TEST_Afederator

