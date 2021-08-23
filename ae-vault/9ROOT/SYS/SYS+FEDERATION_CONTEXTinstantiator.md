---
DESCRIPTORS:
  SYS+INVARIANTdescriptor: true
  SYS+REQUESTID_MAP:
    SYS+INSTANTIATErequestid:
    - INSTANTIATEoperationid
    SYS+ENTITY_REPORTrequestid:
    - ENTITY_REPORToperationid
  SYS+INSTANTIATORdescriptor: SYS+INSTANTIATORinstantiator
  SYS+INSTANTIATION_DESCRIPTORSdescriptor:
    SYS+REQUESTID_MAP:
      SYS+ACQUIRErequestid:
      - FEDERATION_ACQUIREoperationid
      SYS+RELEASErequestid:
      - FEDERATION_RELEASEoperationid
  SYS+INSTANTIATION_CLASSIFIERSdescriptor:
    SYS+ENTITY_TYPEclassifier: SYS+FEDERATION_CONTEXTclassifier_value
tags:
- SYS+ENTITY_TYPEclassifier/SYS+INSTANTIATORclassifier_value
---
# Entity Report for SYS+FEDERATION_CONTEXTinstantiator

