---
gem_map-FACETS^facet:
  SYS+facet-CLASSIFIERS_map^classifier:
    SYS+classifier-CLASS&class:
      class-REQUESTID
  SYS+facet-CONTENT$str:
    ''
  SYS+facet-DESCRIPTORS_map^descriptor:
    SYS+descriptor-INVARIANT$bool:
      true
    SYS+descriptor-READonly$bool:
      true
    SYS+descriptor-REQUESTS_mapvec^requestid$str:
      SYS+requestid-GEMreport:
        - GEM_REPORToperationid
  SYS+facet-NAME&%:
    requestid-LOADscript
---
# Gem SYS+requestid-LOADscript

