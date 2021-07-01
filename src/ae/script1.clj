(ns ae.script1)

(def script1
  [{:request               :REGISTER-ENTITY-REQUEST
    :name                  "CONTEXTS/CONTEXT-PROTOTYPE"
    :descriptors           {:OPERATION-PORTS {:CLONE-REQUEST :CLONE-PORT}
                            :PROTOTYPE-DESCRIPTORS {:OPERATION-PORTS {:REGISTER-ENTITY-REQUEST :REGISTER-ENTITY-PORT
                                                                      :ROUTE-REQUEST           :ROUTE-PORT}}
                            :PROTOTYPE-CLASSIFIERS {}}
    :classifiers           {}
    }
   #_ {:request        :ROUTE-REQUEST
    :target-request :CLONE-REQUEST
    :target-name    "CONTEXTS/CONTEXT-PROTOTYPE"
    :name           "CONTENTS/FUDGE"
    }
   {:request     :REGISTER-ENTITY-REQUEST
    :name        "CONTEXTS/MAIN"
    :descriptors {:OPERATION-PORTS {:REGISTER-ENTITY-REQUEST :REGISTER-ENTITY-PORT
                                    :ROUTE-REQUEST           :ROUTE-PORT}}
    :classifiers {}
    }
   {:request        :ROUTE-REQUEST
    :target-request :REGISTER-ENTITY-REQUEST
    :target-name    "CONTEXTS/MAIN"
    :name           "MAIN/SIMPLE_1"
    :descriptors    {:OPERATION-PORTS {:ADD-PARENT-REQUEST       :ADD-PARENT-PORT
                                       :ADD-RELATIONSHIP-REQUEST :ADD-RELATIONSHIP-PORT}}
    :classifiers    {}
    }
   {:request        :ROUTE-REQUEST
    :target-request :REGISTER-ENTITY-REQUEST
    :target-name    "CONTEXTS/MAIN"
    :name           "MAIN/SIMPLE_2"
    :descriptors    {:OPERATION-PORTS {:ADD-PARENT-REQUEST       :ADD-PARENT-PORT
                                       :ADD-RELATIONSHIP-REQUEST :ADD-RELATIONSHIP-PORT}}
    :classifiers    {}
    }
   {:request           :ROUTE-REQUEST
    :target-request    :ADD-RELATIONSHIP-REQUEST
    :target-name       "MAIN/SIMPLE_1"
    :relationship      :BASIC
    :child-entity-name "MAIN/SIMPLE_2"
    }
   {:request            :ROUTE-REQUEST
    :target-request     :ADD-PARENT-REQUEST
    :target-name        "MAIN/SIMPLE_2"
    :relationship       :BASIC
    :parent-entity-name "MAIN/SIMPLE_1"
    }
   ])
