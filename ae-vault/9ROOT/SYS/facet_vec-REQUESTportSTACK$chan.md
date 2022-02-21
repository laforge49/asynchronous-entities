---
gem_map-FACETS^facet:
  SYS+facet-CONTENT$str:
    ''
  SYS+facet-NAME&%:
    facet_vec-REQUESTportSTACK$chan
  SYS+facet_map-CLASSIFIERS^classifier:
    SYS+classifier-CLASS&class:
      class-FACETvec
  SYS+facet_map-DESCRIPTORS^descriptor:
    SYS+descriptor-INVARIANT$bool:
      true
    SYS+descriptor_mapvec-REQUESTS^requestid$str:
      SYS+requestid-GEMreport:
        - GEM_REPORToperationid
---
# Gem SYS+facet_vec-REQUESTportSTACK$chan

