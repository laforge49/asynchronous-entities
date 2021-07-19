(ns ae.script1)

(def script1
  [{:target-requestid :REGISTER-NEW-ENTITY-REQUESTID
    :target-name      "ROOT/CONTEXTS"
    :name             "CONTEXTS/PROTOTYPE-PROTOTYPE"
    :descriptors      {:INVARIANT             true
                       :REQUESTID-MAP         {:INSTANTIATE-REQUESTID :INSTANTIATE-OPERATIONID}
                       :PROTOTYPE-DESCRIPTORS {:INVARIANT     true
                                               :REQUESTID-MAP {:INSTANTIATE-REQUESTID :INSTANTIATE-OPERATIONID}}}
    }
   {:target-requestid :INSTANTIATE-REQUESTID
    :target-name      "CONTEXTS/PROTOTYPE-PROTOTYPE"
    :name             "CONTEXTS/CONTEXT-PROTOTYPE"
    :descriptors      {:PROTOTYPE-DESCRIPTORS {:REQUESTID-MAP {:REGISTER-NEW-ENTITY-REQUESTID :REGISTER-NEW-ENTITY-OPERATIONID
                                                               :ROUTE-REQUESTID               :ROUTE-OPERATIONID}}}
    }
   {:target-requestid :INSTANTIATE-REQUESTID
    :target-name      "CONTEXTS/PROTOTYPE-PROTOTYPE"
    :name             "CONTEXTS/FEDERATOR-PROTOTYPE"
    :descriptors      {:PROTOTYPE-DESCRIPTORS {:REQUESTID-MAP {:RUN-FEDERATION-REQUESTID :RUN-FEDERATION-OPERATIONID}}}
    }
   {:target-requestid :INSTANTIATE-REQUESTID
    :target-name      "CONTEXTS/PROTOTYPE-PROTOTYPE"
    :name             "CONTEXTS/FEDERATION-CONTEXT-PROTOTYPE"
    :descriptors      {:PROTOTYPE-DESCRIPTORS {:REQUESTID-MAP {:ACQUIRE-REQUESTID :FEDERATION-ACQUIRE-OPERATIONID
                                                               :RELEASE-REQUESTID :FEDERATION-RELEASE-OPERATIONID
                                                               :ROUTE-REQUESTID   :FEDERATION-ROUTE-OPERATIONID}}}
    }
   {:target-requestid :INSTANTIATE-REQUESTID
    :target-name      "CONTEXTS/CONTEXT-PROTOTYPE"
    :name             "CONTEXTS/MAIN"
    }
   {:target-requestid :INSTANTIATE-REQUESTID
    :target-name      "CONTEXTS/PROTOTYPE-PROTOTYPE"
    :name             "MAIN/SIMPLE-PROTOTYPE"
    :descriptors      {:PROTOTYPE-DESCRIPTORS {:REQUESTID-MAP {:ADD-PARENT-REQUESTID       :ADD-PARENT-OPERATIONID
                                                               :ADD-RELATIONSHIP-REQUESTID :ADD-RELATIONSHIP-OPERATIONID
                                                               :ADD-NEW-CHILD-REQUESTID    :ADD-NEW-CHILD-OPERATIONID}}}
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
    :descriptors      {:FEDERATION-NAMES [; "MAIN/SIMPLE_1"
                                          "MAIN/SIMPLE_2"]
                       :SCRIPT           [{:target-requestid :ADD-PARENT-REQUESTID ; :ADD-RELATIONSHIP-REQUESTID
                                           :target-name      "MAIN/SIMPLE_2" ; "MAIN/SIMPLE_1"
                                           :relationship     :BASIC
                                           ; :child-name       "MAIN/SIMPLE_2"
                                           :parent-name      "MAIN/SIMPLE_1"
                                           }]}
    }
   {:target-requestid :RUN-FEDERATION-REQUESTID
    :target-name      "MAIN/FEDERATOR_1"
    }
   #_{:target-requestid :ADD-NEW-CHILD-REQUESTID
      :target-name      "MAIN/SIMPLE_1"
      :relationship     :BASIC
      :child-name       "MAIN/SIMPLE_3"
      :prototype        "MAIN/SIMPLE-PROTOTYPE"
      }
   ])
