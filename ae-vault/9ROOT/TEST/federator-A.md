---
DESCRIPTORS:
  SYS+FEDERATION_NAMESdescriptor:
  - TEST+simple-ALPHA
  SYS+INSTANTIATORdescriptor: SYS+FEDERATORinstantiator
  SYS+descriptorSCRIPT$yaml:
  - target_requestid: SYS+requestid-INSTANTIATE
    target_name: instantiator-SIMPLE
    name: simple-GAMMA
  - target_requestid: SYS+ADD_DESCRIPTORSrequestid
    target_name: simple-ALPHA
    descriptors:
      descriptor-DEGREE_OF_POLISH$str: MIDDLING
  - target_requestid: SYS+ADD_CLASSIFIERSrequestid
    target_name: simple-ALPHA
    classifiers:
      classifier-APPLICATION: classifier_value-FIDDLING
  - target_requestid: SYS+ADD_DESCRIPTORSrequestid
    target_name: simple-GAMMA
    descriptors:
      descriptor-DEGREE_OF_POLISH$str: MIDDLING
  - target_requestid: SYS+ADD_CLASSIFIERSrequestid
    target_name: simple-GAMMA
    classifiers:
      relation-BASIC: simple-ALPHA
  - target_requestid: SYS+ADD_CLASSIFIERSrequestid
    target_name: simple-GAMMA
    classifierS:
      classifier-APPLICATION: classifier_value-FIDDLING
  SYS+descriptor_map-REQUESTID$operationid:
    SYS+RUN_FEDERATIONrequestid:
    - RUN_FEDERATIONoperationid
    SYS+requestid-ENTITY_REPORT:
    - ENTITY_REPORToperationid
tags:
- SYS+ENTITY_TYPEclassifier/SYS+FEDERATORclassifier_value
---
# Entity TEST+federator-A

