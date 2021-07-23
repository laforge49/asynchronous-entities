(ns ae.script1)

(def script1
  [{:target-requestid :REGISTER-CLASSIFIER-REQUESTID
    :target-name      "ROOT/CONTEXTS"
    :name             "ROOT/CONTEXTS"
    :classifier       :ENTITY-TYPE
    :classifier-value "context"
    }
   {:target-requestid :REGISTER-ENTITY-REQUESTID
    :target-name      "ROOT/CONTEXTS"
    :name             "CONTEXTS/INSTANTIATOR-INSTANTIATOR"
    :descriptors      {:INVARIANT                 true
                       :REQUESTID-MAP             {:INSTANTIATE-REQUESTID :INSTANTIATE-OPERATIONID}
                       :INSTANTIATION-DESCRIPTORS {:INVARIANT     true
                                                   :REQUESTID-MAP {:INSTANTIATE-REQUESTID :INSTANTIATE-OPERATIONID}}
                       :INSTANTIATION-CLASSIFIERS {:ENTRY-TYPE "instantiator"}}
    }
   {:target-requestid :INSTANTIATE-REQUESTID
    :target-name      "CONTEXTS/INSTANTIATOR-INSTANTIATOR"
    :name             "CONTEXTS/CONTEXT-INSTANTIATOR"
    :descriptors      {:INSTANTIATION-DESCRIPTORS {:REQUESTID-MAP {:REGISTER-ENTITY-REQUESTID     :REGISTER-ENTITY-OPERATIONID
                                                                   :ROUTE-REQUESTID               :ROUTE-OPERATIONID
                                                                   :REGISTER-CLASSIFIER-REQUESTID :REGISTER-CLASSIFIER-OPERATIONID}}}
    }
   {:target-requestid :INSTANTIATE-REQUESTID
    :target-name      "CONTEXTS/INSTANTIATOR-INSTANTIATOR"
    :name             "CONTEXTS/FEDERATOR-INSTANTIATOR"
    :descriptors      {:INSTANTIATION-DESCRIPTORS {:REQUESTID-MAP {:RUN-FEDERATION-REQUESTID :RUN-FEDERATION-OPERATIONID}}}
    }
   {:target-requestid :INSTANTIATE-REQUESTID
    :target-name      "CONTEXTS/INSTANTIATOR-INSTANTIATOR"
    :name             "CONTEXTS/FEDERATION-CONTEXT-INSTANTIATOR"
    :descriptors      {:INSTANTIATION-DESCRIPTORS {:REQUESTID-MAP {:ACQUIRE-REQUESTID :FEDERATION-ACQUIRE-OPERATIONID
                                                                   :RELEASE-REQUESTID :FEDERATION-RELEASE-OPERATIONID
                                                                   :ROUTE-REQUESTID   :FEDERATION-ROUTE-OPERATIONID}}}
    }
   {:target-requestid :INSTANTIATE-REQUESTID
    :target-name      "CONTEXTS/CONTEXT-INSTANTIATOR"
    :name             "CONTEXTS/MAIN"
    }
   {:target-requestid :INSTANTIATE-REQUESTID
    :target-name      "CONTEXTS/INSTANTIATOR-INSTANTIATOR"
    :name             "MAIN/SIMPLE-INSTANTIATOR"
    :descriptors      {:INSTANTIATION-DESCRIPTORS {:REQUESTID-MAP {:ADD-PARENT-REQUESTID       :ADD-PARENT-OPERATIONID
                                                                   :ADD-RELATIONSHIP-REQUESTID :ADD-RELATIONSHIP-OPERATIONID
                                                                   :ADD-NEW-CHILD-REQUESTID    :ADD-NEW-CHILD-OPERATIONID}}}
    }
   {:target-requestid :INSTANTIATE-REQUESTID
    :target-name      "MAIN/SIMPLE-INSTANTIATOR"
    :name             "MAIN/SIMPLE_1"
    }
   {:target-requestid :INSTANTIATE-REQUESTID
    :target-name      "MAIN/SIMPLE-INSTANTIATOR"
    :name             "MAIN/SIMPLE_2"
    }
   {:target-requestid :INSTANTIATE-REQUESTID
    :target-name      "CONTEXTS/FEDERATOR-INSTANTIATOR"
    :name             "MAIN/FEDERATOR_1"
    :descriptors      {:FEDERATION-NAMES ["MAIN/SIMPLE_1"
                                          "MAIN/SIMPLE_2"
                                          "MAIN/SIMPLE-INSTANTIATOR"]
                       :SCRIPT           [{:target-requestid :ADD-RELATIONSHIP-REQUESTID
                                           :target-name      "MAIN/SIMPLE_1"
                                           :relationship     :BASIC
                                           :child-name       "MAIN/SIMPLE_2"
                                           }
                                          {:target-requestid   :ADD-RELATIONSHIP-REQUESTID
                                           :target-name        "MAIN/SIMPLE_1"
                                           :relationship       :BASIC
                                           :child-instantiator "MAIN/SIMPLE-INSTANTIATOR"
                                           :child-name         "MAIN/SIMPLE_3"
                                           }
                                          ]}
    }
   {:target-requestid :RUN-FEDERATION-REQUESTID
    :target-name      "MAIN/FEDERATOR_1"
    }
   ])
