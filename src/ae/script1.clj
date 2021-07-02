(ns ae.script1)

(def script1
  [{:target-request :REGISTER-ENTITY-REQUESTID
    :target-name    "ROOT/CONTEXTS"
    :name           "CONTEXTS/CONTEXT-PROTOTYPE"
    :descriptors    {:OPERATION-PORTS       {:INSTANTIATE-REQUESTID :INSTANTIATE-PORT}
                     :PROTOTYPE-DESCRIPTORS {:OPERATION-PORTS {:REGISTER-ENTITY-REQUESTID :REGISTER-ENTITY-PORT
                                                               :ROUTE-REQUESTID           :ROUTE-PORT}}
                     :PROTOTYPE-CLASSIFIERS {}}
    :classifiers    {}
    }
   {:target-request :INSTANTIATE-REQUESTID
    :target-name    "CONTEXTS/CONTEXT-PROTOTYPE"
    :name           "CONTEXTS/MAIN"
    }
   {:target-request :REGISTER-ENTITY-REQUESTID
    :target-name    "CONTEXTS/MAIN"
    :name           "MAIN/SIMPLE-PROTOTYPE"
    :descriptors    {:OPERATION-PORTS       {:INSTANTIATE-REQUESTID :INSTANTIATE-PORT}
                     :PROTOTYPE-DESCRIPTORS {:OPERATION-PORTS {:ADD-PARENT-REQUESTID       :ADD-PARENT-PORT
                                                               :ADD-RELATIONSHIP-REQUESTID :ADD-RELATIONSHIP-PORT}}
                     :PROTOTYPE-CLASSIFIERS {}}
    :classifiers    {}
    }
   {:target-request :INSTANTIATE-REQUESTID
    :target-name    "MAIN/SIMPLE-PROTOTYPE"
    :name           "MAIN/SIMPLE_1"
    }
   {:target-request :INSTANTIATE-REQUESTID
    :target-name    "MAIN/SIMPLE-PROTOTYPE"
    :name           "MAIN/SIMPLE_2"
    }
   {:target-request    :ADD-RELATIONSHIP-REQUESTID
    :target-name       "MAIN/SIMPLE_1"
    :relationship      :BASIC
    :child-entity-name "MAIN/SIMPLE_2"
    }
   {:target-request     :ADD-PARENT-REQUESTID
    :target-name        "MAIN/SIMPLE_2"
    :relationship       :BASIC
    :parent-entity-name "MAIN/SIMPLE_1"
    }
   ])
