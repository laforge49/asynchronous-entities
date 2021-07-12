(ns ae.script1)

(def script1
  [{:target-requestid :REGISTER-NEW-ENTITY-REQUESTID
    :target-name      "ROOT/CONTEXTS"
    :name             "CONTEXTS/PROTOTYPE-PROTOTYPE"
    :descriptors      {:OPERATION-PORTIDS     {:INSTANTIATE-REQUESTID :INSTANTIATE-PORTID}
                       :PROTOTYPE-DESCRIPTORS {:OPERATION-PORTIDS {:INSTANTIATE-REQUESTID :INSTANTIATE-PORTID}}}
    }
   {:target-requestid :INSTANTIATE-REQUESTID
    :target-name      "CONTEXTS/PROTOTYPE-PROTOTYPE"
    :name             "CONTEXTS/CONTEXT-PROTOTYPE"
    :descriptors      {:PROTOTYPE-DESCRIPTORS {:OPERATION-PORTIDS {:REGISTER-NEW-ENTITY-REQUESTID :REGISTER-NEW-ENTITY-PORTID
                                                                   :ROUTE-REQUESTID               :ROUTE-PORTID}}}
    }
   {:target-requestid :INSTANTIATE-REQUESTID
    :target-name      "CONTEXTS/PROTOTYPE-PROTOTYPE"
    :name             "CONTEXTS/FEDERATOR-PROTOTYPE"
    :descriptors      {:PROTOTYPE-DESCRIPTORS {:OPERATION-PORTIDS {:RUN-FEDERATION-REQUESTID :RUN-FEDERATION-PORTID}}}
    }
   {:target-requestid :INSTANTIATE-REQUESTID
    :target-name      "CONTEXTS/PROTOTYPE-PROTOTYPE"
    :name             "CONTEXTS/FEDERATION-CONTEXT-PROTOTYPE"
    :descriptors      {:PROTOTYPE-DESCRIPTORS {:OPERATION-PORTIDS {:ACQUIRE-REQUESTID :ACQUIRE-PORTID
                                                                   :RELEASE-REQUESTID :RELEASE-PORTID
                                                                   :ROUTE-REQUESTID   :FEDERATED-ROUTE-PORTID}}}
    }
   {:target-requestid :INSTANTIATE-REQUESTID
    :target-name      "CONTEXTS/CONTEXT-PROTOTYPE"
    :name             "CONTEXTS/MAIN"
    }
   {:target-requestid :INSTANTIATE-REQUESTID
    :target-name      "CONTEXTS/PROTOTYPE-PROTOTYPE"
    :name             "MAIN/SIMPLE-PROTOTYPE"
    :descriptors      {:PROTOTYPE-DESCRIPTORS {:OPERATION-PORTIDS {:ADD-PARENT-REQUESTID       :ADD-PARENT-PORTID
                                                                   :ADD-RELATIONSHIP-REQUESTID :ADD-RELATIONSHIP-PORTID}}}
    }
   {:target-requestid :INSTANTIATE-REQUESTID
    :target-name      "MAIN/SIMPLE-PROTOTYPE"
    :name             "MAIN/SIMPLE_1"
    }
   {:target-requestid :INSTANTIATE-REQUESTID
    :target-name      "MAIN/SIMPLE-PROTOTYPE"
    :name             "MAIN/SIMPLE_2"
    }
   {:target-requestid :INSTANTIATE-REQUESTID
    :target-name      "CONTEXTS/FEDERATOR-PROTOTYPE"
    :name             "MAIN/FEDERATOR_1"
    :descriptors      {:FEDERATION-NAMES ["MAIN/SIMPLE_1"
                                          "MAIN/SIMPLE_2"]
                       :SCRIPT           [{:target-requestid  :ADD-RELATIONSHIP-REQUESTID
                                           :target-name       "MAIN/SIMPLE_1"
                                           :relationship      :BASIC
                                           :child-entity-name "MAIN/SIMPLE_2"
                                           }]}
    }
   {:target-requestid :RUN-FEDERATION-REQUESTID
    :target-name      "MAIN/FEDERATOR_1"
    }
   ])
