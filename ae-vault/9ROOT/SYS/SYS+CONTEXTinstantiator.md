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
      SYS+REGISTER_ENTITYrequestid:
      - REGISTER_ENTITYoperationid
      SYS+ROUTErequestid:
      - ROUTEoperationid
      SYS+REGISTER_CLASSIFIERrequestid:
      - REGISTER_CLASSIFIERoperationid
      SYS+ENTITY_REPORTrequestid:
      - CONTEXT_REPORToperationid
  SYS+INSTANTIATION_CLASSIFIERSdescriptor:
    SYS+ENTITY_TYPEclassifier: SYS+CONTEXTclassifier_value
tags:
- SYS+ENTITY_TYPEclassifier/SYS+INSTANTIATORclassifier_value
---
# Entity SYS+CONTEXTinstantiator

