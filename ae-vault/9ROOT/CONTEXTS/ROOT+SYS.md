---
DESCRIPTORS:
  SYS+descriptor_map-REQUESTID$operationid:
    SYS+requestid-REGISTER_ENTITY:
    - REGISTER_ENTITYoperationid
    SYS+requestid-ROUTE:
    - ROUTEoperationid
    SYS+requestid-REGISTER_CLASSIFIER:
    - REGISTER_CLASSIFIERoperationid
    SYS+requestid-ENTITY_REPORT:
    - CONTEXT_REPORToperationid
    SYS+requestid-LOAD_SCRIPT:
    - LOAD_SCRIPToperationid
---
# Entity ROOT+SYS

1. Registered Entities of context ROOT+SYS
(Default context is SYS.)

+ADD_CLASSIFIERSrequestid
+ADD_DESCRIPTORSrequestid
+CLASSIFIER_VALUEclassifier_value
+CLASSIFIER_VALUEinstantiator
+CLASSIFIERclassifier_value
+CLASSIFIERinstantiator
+CONTEXT_REPORTrequestid
+CONTEXTclassifier_value
+CONTEXTinstantiator
+DESCRIPTORclassifier_value
+DESCRIPTORinstantiator
+ENTITY_TYPEclassifier
+FEDERATION_CONTEXTclassifier_value
+FEDERATORclassifier_value
+FEDERATORinstantiator
+INSTANTIATION_CLASSIFIERSdescriptor
+INSTANTIATORclassifier_value
+RELATIONinstantiator
+RELEASErequestid
+REQUESTIDclassifier_value
+REQUESTIDinstantiator
+RUN_FEDERATIONrequestid
+TEST
+descriptor-INSTANTIATION_DESCRIPTORS
+descriptorINVARIANT$bool
+descriptorREAD_ONLY$bool
+descriptor_map-REQUESTID$operationid
+instantiator-FEDERATION_CONTEXT
+instantiator-INSTANTIATOR
+instantiatorDATA_TYPE
+listSCRIPT
+mapREQUEST
+requestid-ACQUIRE
+requestid-INSTANTIATE
+requestid-REGISTER_CLASSIFIER
+requestid-REGISTER_ENTITY
+requestid-ROUTE
+stringPARAMATER_NAME

Number of entities: 38

2. Classifier Values of context ROOT+SYS
(Default context is SYS.)

classifier:    +ENTITY_TYPEclassifier
     value:        +CLASSIFIER_VALUEclassifier_value
    entity:            +CLASSIFIER_VALUEclassifier_value
    entity:            +CLASSIFIERclassifier_value
    entity:            +CONTEXTclassifier_value
    entity:            +DESCRIPTORclassifier_value
    entity:            +FEDERATION_CONTEXTclassifier_value
    entity:            +FEDERATORclassifier_value
    entity:            +INSTANTIATORclassifier_value
    entity:            +REQUESTIDclassifier_value
     value:        +CLASSIFIERclassifier_value
    entity:            +ENTITY_TYPEclassifier
     value:        +CONTEXTclassifier_value
    entity:            +TEST
     value:        +DESCRIPTORclassifier_value
    entity:            +INSTANTIATION_CLASSIFIERSdescriptor
    entity:            +descriptor-INSTANTIATION_DESCRIPTORS
    entity:            +descriptorINVARIANT$bool
    entity:            +descriptorREAD_ONLY$bool
    entity:            +descriptor_map-REQUESTID$operationid
     value:        +INSTANTIATORclassifier_value
    entity:            +CLASSIFIER_VALUEinstantiator
    entity:            +CLASSIFIERinstantiator
    entity:            +CONTEXTinstantiator
    entity:            +DESCRIPTORinstantiator
    entity:            +FEDERATORinstantiator
    entity:            +RELATIONinstantiator
    entity:            +REQUESTIDinstantiator
    entity:            +instantiator-FEDERATION_CONTEXT
    entity:            +instantiator-INSTANTIATOR
    entity:            +instantiatorDATA_TYPE
     value:        +REQUESTIDclassifier_value
    entity:            +ADD_CLASSIFIERSrequestid
    entity:            +ADD_DESCRIPTORSrequestid
    entity:            +CONTEXT_REPORTrequestid
    entity:            +RELEASErequestid
    entity:            +RUN_FEDERATIONrequestid
    entity:            +requestid-ACQUIRE
    entity:            +requestid-INSTANTIATE
    entity:            +requestid-REGISTER_CLASSIFIER
    entity:            +requestid-REGISTER_ENTITY
    entity:            +requestid-ROUTE
     value:        +classifier_valueDATA_TYPE
    entity:            +listSCRIPT
    entity:            +mapREQUEST
    entity:            +stringPARAMATER_NAME

Number of classifiers: 1

