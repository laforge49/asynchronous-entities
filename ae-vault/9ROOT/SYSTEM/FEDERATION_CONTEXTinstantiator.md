---
DESCRIPTORS:
  SYSTEMcontext+INVARIANTdescriptor: true
  SYSTEMcontext+REQUESTID_MAP:
    SYSTEMcontext+INSTANTIATErequestid:
    - INSTANTIATEoperationid
    SYSTEMcontext+ENTITY_REPORTrequestid:
    - ENTITY_REPORToperationid
  SYSTEMcontext+INSTANTIATORdescriptor: SYSTEMcontext+INSTANTIATORinstantiator
  SYSTEMcontext+INSTANTIATION_DESCRIPTORSdescriptor:
    SYSTEMcontext+REQUESTID_MAP:
      SYSTEMcontext+ACQUIRErequestid:
      - FEDERATION_ACQUIREoperationid
      SYSTEMcontext+RELEASErequestid:
      - FEDERATION_RELEASEoperationid
  SYSTEMcontext+INSTANTIATION_CLASSIFIERSdescriptor:
    SYSTEMcontext+ENTITY_TYPEclassifier: SYSTEMcontext+FEDERATION_CONTEXTclassifier_value
tags:
- SYSTEMcontext+ENTITY_TYPEclassifier/SYSTEMcontext+INSTANTIATORclassifier_value
---
# Entity Report for SYSTEMcontext+FEDERATION_CONTEXTinstantiator
