(ns ae.script1)

(def script1
  [#_ {:target-requestid :REGISTER-ENTITY-REQUESTID
    :target-name      "ROOT/CONTEXTS"
    :name             "CONTEXTS/CONTEXT-PROTOTYPE"
    :descriptors      {:OPERATION-PORTIDS     {:INSTANTIATE-REQUESTID :INSTANTIATE-PORTID}
                       :PROTOTYPE-DESCRIPTORS {:OPERATION-PORTIDS {:REGISTER-ENTITY-REQUESTID :REGISTER-ENTITY-PORTID
                                                                   :ROUTE-REQUESTID           :ROUTE-PORTID}}
                       :PROTOTYPE-CLASSIFIERS {}}
    :classifiers      {}
    }
   #_ {:target-requestid :INSTANTIATE-REQUESTID
    :target-name      "CONTEXTS/CONTEXT-PROTOTYPE"
    :name             "CONTEXTS/MAIN"
    }
   #_ {:target-requestid :REGISTER-ENTITY-REQUESTID
    :target-name      "CONTEXTS/MAIN"
    :name             "MAIN/SIMPLE-PROTOTYPE"
    :descriptors      {:OPERATION-PORTIDS     {:INSTANTIATE-REQUESTID :INSTANTIATE-PORTID}
                       :PROTOTYPE-DESCRIPTORS {:OPERATION-PORTIDS {:ADD-PARENT-REQUESTID       :ADD-PARENT-PORTID
                                                                   :ADD-RELATIONSHIP-REQUESTID :ADD-RELATIONSHIP-PORTID}}
                       :PROTOTYPE-CLASSIFIERS {}}
    :classifiers      {}
    }
   #_ {:target-requestid :INSTANTIATE-REQUESTID
    :target-name      "MAIN/SIMPLE-PROTOTYPE"
    :name             "MAIN/SIMPLE_1"
    }
   #_ {:target-requestid :INSTANTIATE-REQUESTID
    :target-name      "MAIN/SIMPLE-PROTOTYPE"
    :name             "MAIN/SIMPLE_2"
    }
   #_ {:target-requestid  :ADD-RELATIONSHIP-REQUESTID
    :target-name       "MAIN/SIMPLE_1"
    :relationship      :BASIC
    :child-entity-name "MAIN/SIMPLE_2"
    }
   #_ {:target-requestid  :ADD-RELATIONSHIP-REQUESTID
    :target-name       "MAIN/SIMPLE_1"
    :relationship      :BASIC
    :child-entity-name "MAIN/SIMPLE_2"
    }
   ])
