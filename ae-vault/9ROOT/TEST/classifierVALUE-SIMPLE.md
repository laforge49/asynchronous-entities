---
SYS+gem_map-FACETS^facet:
  SYS+facet-CONTENT$str: ''
  SYS+facet-NAME&?: classifierVALUE-SIMPLE
  SYS+facet_map-CLASSIFIERS^classifier:
    SYS+classifier-CLASS&class: SYS+class-CLASSIFIERvalue
    SYS+classifier-ENTITYtype&classifierVALUE: SYS+classifierVALUE-CLASSIFIERvalue
  SYS+facet_map-DESCRIPTORS^descriptor:
    SYS+descriptor-INVARIANT$bool: true
    SYS+descriptor_mapvec-REQUESTS^requestid$str:
      SYS+requestid-ENTITYreport:
      - ENTITY_REPORToperationid
  SYS+facet_vec-REQUESTportSTACK$chan:
  - clojure.core.async.chan
---
# Entity TEST+classifierVALUE-SIMPLE

