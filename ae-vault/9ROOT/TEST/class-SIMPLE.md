---
SYS+gem-FACETS^facet:
  REQUEST-PORT-STACK:
  - clojure.core.async.chan
  SYS+facet-CONTENT$str: ''
  SYS+facet-NAME&?: class-SIMPLE
  SYS+facet_map-CLASSIFIERS^classifier:
    SYS+classifier-CLASS: SYS+class-CLASS
    SYS+classifier-ENTITYtype&classifierVALUE: SYS+classifierVALUE-CLASS
  SYS+facet_map-DESCRIPTORS^descriptor:
    SYS+descriptor-INVARIANT$bool: true
    SYS+descriptor_map-INSTANCE^classifier:
      SYS+classifier-ENTITYtype&classifierVALUE: classifierVALUE-SIMPLE
    SYS+descriptor_map-INSTANCE^descriptor:
      SYS+descriptor_mapvec-REQUESTS^requestid$str:
        SYS+requestid-ADDdescriptors:
        - ADD_DESCRIPTORSoperationid
        SYS+requestid-ADDrelations:
        - ADD_RELATIONSoperationid
        SYS+requestid-ENTITYreport:
        - ENTITY_REPORToperationid
    SYS+descriptor_mapvec-REQUESTS^requestid$str:
      SYS+requestid-ENTITYreport:
      - ENTITY_REPORToperationid
      SYS+requestid-INSTANTIATE:
      - INSTANTIATEoperationid
---
# Entity TEST+class-SIMPLE

