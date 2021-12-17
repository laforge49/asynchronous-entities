---
SYS+gem_map-FACETS^facet:
  SYS+facet-CONTENT$str: ''
  SYS+facet-NAME&?: class-SIMPLE
  SYS+facet_map-CLASSIFIERS^classifier:
    SYS+classifier-CLASS&class: SYS+class-CLASS
  SYS+facet_map-DESCRIPTORS^descriptor:
    SYS+descriptor-INVARIANT$bool: true
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
  SYS+facet_vec-REQUESTportSTACK$chan:
  - clojure.core.async.chan
---
# Entity TEST+class-SIMPLE

