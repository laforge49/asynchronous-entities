---
DESCRIPTORS:
  SYS+descriptor-INVARIANT$bool: true
  SYS+descriptor_mapvec-REQUESTS^requestid$str:
    SYS+requestid-ENTITYreport:
    - ENTITY_REPORToperationid
    SYS+requestid-INSTANTIATE:
    - INSTANTIATEoperationid
  SYS+descriptors_map-INSTANCE^classifier:
    SYS+classifier-ENTITYtype: classifierVALUE-SIMPLE
  SYS+descriptors_map-INSTANCE^descriptor:
    SYS+descriptor_mapvec-REQUESTS^requestid$str:
      SYS+requestid-ADDdescriptors:
      - ADD_DESCRIPTORSoperationid
      SYS+requestid-ADDrelations:
      - ADD_RELATIONSoperationid
      SYS+requestid-ENTITYreport:
      - ENTITY_REPORToperationid
TAGS:
- SYS+classifier-CLASS/SYS+class-CLASS
- SYS+classifier-ENTITYtype/SYS+classifierVALUE-CLASS
---
# Entity TEST+class-SIMPLE

