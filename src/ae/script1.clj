(ns ae.script1)

(def script1
  [{:target-request :REGISTER-ENTITY-REQUEST
    :target-name    "ROOT/CONTEXTS"
    :name           "CONTEXTS/CONTEXT-PROTOTYPE"
    :descriptors    {:OPERATION-PORTS       {:CLONE-REQUEST :CLONE-PORT}
                     :PROTOTYPE-DESCRIPTORS {:OPERATION-PORTS {:REGISTER-ENTITY-REQUEST :REGISTER-ENTITY-PORT
                                                               :ROUTE-REQUEST           :ROUTE-PORT}}
                     :PROTOTYPE-CLASSIFIERS {}}
    :classifiers    {}
    }
   {:target-request :CLONE-REQUEST
      :target-name    "CONTEXTS/CONTEXT-PROTOTYPE"
      :name           "CONTEXTS/MAIN"
      }
   {:target-request :REGISTER-ENTITY-REQUEST
    :target-name    "CONTEXTS/MAIN"
    :name           "MAIN/SIMPLE_1"
    :descriptors    {:OPERATION-PORTS {:ADD-PARENT-REQUEST       :ADD-PARENT-PORT
                                       :ADD-RELATIONSHIP-REQUEST :ADD-RELATIONSHIP-PORT}}
    :classifiers    {}
    }
   {:target-request :REGISTER-ENTITY-REQUEST
    :target-name    "CONTEXTS/MAIN"
    :name           "MAIN/SIMPLE_2"
    :descriptors    {:OPERATION-PORTS {:ADD-PARENT-REQUEST       :ADD-PARENT-PORT
                                       :ADD-RELATIONSHIP-REQUEST :ADD-RELATIONSHIP-PORT}}
    :classifiers    {}
    }
   {:target-request    :ADD-RELATIONSHIP-REQUEST
    :target-name       "MAIN/SIMPLE_1"
    :relationship      :BASIC
    :child-entity-name "MAIN/SIMPLE_2"
    }
   {:target-request     :ADD-PARENT-REQUEST
    :target-name        "MAIN/SIMPLE_2"
    :relationship       :BASIC
    :parent-entity-name "MAIN/SIMPLE_1"
    }
   ])
