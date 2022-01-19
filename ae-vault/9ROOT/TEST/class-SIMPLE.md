---
gem_map-FACETS^facet:
  facet-CONTENT$str: ''
  facet-NAME&%: class-SIMPLE
  facet_map-CLASSIFIERS^classifier:
    classifier-CLASS&class: class-CLASS
  facet_map-DESCRIPTORS^descriptor:
    descriptor-INVARIANT$bool: true
    descriptor_map-INSTANCE^descriptor:
      descriptor_mapvec-REQUESTS^requestid$str:
        requestid-ADDdescriptors:
        - ADD_DESCRIPTORSoperationid
        requestid-ADDrelations:
        - ADD_RELATIONSoperationid
        requestid-ENTITYreport:
        - ENTITY_REPORToperationid
    descriptor_mapvec-REQUESTS^requestid$str:
      requestid-ENTITYreport:
      - ENTITY_REPORToperationid
      requestid-INSTANTIATE:
      - INSTANTIATEoperationid
---
# Entity TEST+class-SIMPLE

