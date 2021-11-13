---
DESCRIPTORS:
  descriptor-INVARIANT$bool: true
  descriptor_map-INSTANCE^classifier:
    classifier-ENTITYtype: classifierVALUE-CONTEXT
  descriptor_map-INSTANCE^descriptor:
    descriptor_mapvec-REQUESTS^requestid$str:
      requestid-ENTITYreport:
      - CONTEXT_REPORToperationid
      requestid-LOADscript:
      - LOAD_SCRIPToperationid
      requestid-REGISTERentity:
      - REGISTER_ENTITYoperationid
      requestid-ROUTE:
      - ROUTEoperationid
  descriptor_mapvec-REQUESTS^requestid$str:
    requestid-ENTITYreport:
    - ENTITY_REPORToperationid
    requestid-INSTANTIATE:
    - INSTANTIATEoperationid
TAGS:
- SYS+classifier-CLASS/SYS+class-CLASS
- SYS+classifier-ENTITYtype/SYS+classifierVALUE-CLASS
---
# Entity SYS+class-CONTEXT

