---
gem_map-FACETS^facet:
  SYS+facet-CONTENT$str:
    ''
  SYS+facet-NAME&%:
    class-FEDERATOR
  SYS+facet_map-CLASSIFIERS^classifier:
    SYS+classifier-CLASS&class:
      class-CLASS
  SYS+facet_map-DESCRIPTORS^descriptor:
    SYS+descriptor-INVARIANT$bool:
      true
    SYS+descriptor_map-INSTANCE^descriptor:
      SYS+descriptor_mapvec-REQUESTS^requestid$str:
        FED+requestid-RUNfederation:
          - RUN_FEDERATIONoperationid
        SYS+requestid-GEMreport:
          - GEM_REPORToperationid
    SYS+descriptor_mapvec-REQUESTS^requestid$str:
      SYS+requestid-GEMreport:
        - GEM_REPORToperationid
      SYS+requestid-INSTANTIATE:
        - INSTANTIATEoperationid
---
# Gem FED+class-FEDERATOR

