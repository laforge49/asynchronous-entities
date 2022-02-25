---
gem_map-FACETS^facet:
  SYS+facet-CLASSIFIERS_map^classifier:
    SYS+classifier-CLASS&class:
      class-CLASS
  SYS+facet-CONTENT$str:
    ''
  SYS+facet-DESCRIPTORS_map^descriptor:
    SYS+descriptor-INSTANCE_map^descriptor:
      SYS+descriptor-REQUESTS_mapvec^requestid$str:
        FED+requestid-ADDrelations:
          - ADD_RELATIONSoperationid
        SYS+requestid-ADDdescriptors:
          - ADD_DESCRIPTORSoperationid
        SYS+requestid-GEMreport:
          - GEM_REPORToperationid
    SYS+descriptor-INVARIANT$bool:
      true
    SYS+descriptor-REQUESTS_mapvec^requestid$str:
      SYS+requestid-GEMreport:
        - GEM_REPORToperationid
      SYS+requestid-INSTANTIATE:
        - INSTANTIATEoperationid
  SYS+facet-NAME&%:
    class-SIMPLE
---
# Gem FEDTEST+class-SIMPLE

