---
gem_map-FACETS^facet:
  SYS+facet-CLASSIFIERS_map^classifier:
    SYS+classifier-CLASS&class:
      class-CLASS
  SYS+facet-CONTENT$str:
    ''
  SYS+facet-DESCRIPTORS_map^descriptor:
    SYS+descriptor-INSTANCE_map^descriptor:
      SYS+descriptor-INVARIANT$bool:
        true
      SYS+descriptor-REQUESTS_mapvec^requestid$str:
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
    class-DESCRIPTOR
---
# Gem SYS+class-DESCRIPTOR

