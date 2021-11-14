---
DESCRIPTORS:
  descriptor-INVARIANT$bool: true
  descriptor_map-INSTANCE^classifier:
    classifier-ENTITYtype&classifierVALUE: classifierVALUE-DATAtype
  descriptor_map-INSTANCE^descriptor:
    descriptor-INVARIANT$bool: true
    descriptor_mapvec-REQUESTS^requestid$str:
      requestid-ENTITYreport:
      - ENTITY_REPORToperationid
      requestid-TYPEof:
      - operationidTYPE_OF
  descriptor_mapvec-REQUESTS^requestid$str:
    requestid-ENTITYreport:
    - ENTITY_REPORToperationid
    requestid-INSTANTIATE:
    - INSTANTIATEoperationid
TAGS:
- SYS+classifier-CLASS/SYS+class-CLASS
- SYS+classifier-ENTITYtype&classifierVALUE/SYS+classifierVALUE-CLASS
---
# Entity SYS+class-DATAtype

