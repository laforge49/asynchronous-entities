(ns ae.script1)

(def script1
  [{:target-requestid :REGISTER-ENTITY-REQUESTID
    :target-name      "ROOT/CONTEXTS"
    :name             "CONTEXTS/INSTANTIATOR-INSTANTIATOR"
    :descriptors      {:CONTEXTS/INVARIANT                 true
                       :CONTEXTS/REQUESTID-MAP             {:INSTANTIATE-REQUESTID :INSTANTIATE-OPERATIONID}
                       :CONTEXTS/INSTANTIATION-DESCRIPTORS {:CONTEXTS/INVARIANT     true
                                                            :CONTEXTS/REQUESTID-MAP {:INSTANTIATE-REQUESTID :INSTANTIATE-OPERATIONID}}
                       :CONTEXTS/INSTANTIATION-CLASSIFIERS {:CONTEXTS/ENTITY-TYPE "CONTEXTS/ENTITY-TYPE!INSTANTIATOR"}}
    :classifiers      {:CONTEXTS/ENTITY-TYPE "CONTEXTS/ENTITY-TYPE!INSTANTIATOR"}
    }
   {:target-requestid :INSTANTIATE-REQUESTID
    :target-name      "CONTEXTS/INSTANTIATOR-INSTANTIATOR"
    :name             "CONTEXTS/CONTEXT-INSTANTIATOR"
    :descriptors      {:CONTEXTS/INSTANTIATION-DESCRIPTORS {:CONTEXTS/REQUESTID-MAP {:REGISTER-ENTITY-REQUESTID     :REGISTER-ENTITY-OPERATIONID
                                                                                     :ROUTE-REQUESTID               :ROUTE-OPERATIONID
                                                                                     :REGISTER-CLASSIFIER-REQUESTID :REGISTER-CLASSIFIER-OPERATIONID}}
                       :CONTEXTS/INSTANTIATION-CLASSIFIERS {:CONTEXTS/ENTITY-TYPE "CONTEXTS/ENTITY-TYPE!CONTEXT"}}
    }
   {:target-requestid :INSTANTIATE-REQUESTID
    :target-name      "CONTEXTS/INSTANTIATOR-INSTANTIATOR"
    :name             "CONTEXTS/FEDERATOR-INSTANTIATOR"
    :descriptors      {:CONTEXTS/INSTANTIATION-DESCRIPTORS {:CONTEXTS/REQUESTID-MAP {:RUN-FEDERATION-REQUESTID :RUN-FEDERATION-OPERATIONID}}
                       :CONTEXTS/INSTANTIATION-CLASSIFIERS {:CONTEXTS/ENTITY-TYPE "CONTEXTS/ENTITY-TYPE!FEDERATOR"}}
    }
   {:target-requestid :INSTANTIATE-REQUESTID
    :target-name      "CONTEXTS/INSTANTIATOR-INSTANTIATOR"
    :name             "CONTEXTS/FEDERATION-CONTEXT-INSTANTIATOR"
    :descriptors      {:CONTEXTS/INSTANTIATION-DESCRIPTORS {:CONTEXTS/REQUESTID-MAP {:ACQUIRE-REQUESTID :FEDERATION-ACQUIRE-OPERATIONID
                                                                                     :RELEASE-REQUESTID :FEDERATION-RELEASE-OPERATIONID
                                                                                     :ROUTE-REQUESTID   :FEDERATION-ROUTE-OPERATIONID}}
                       :CONTEXTS/INSTANTIATION-CLASSIFIERS {:CONTEXTS/ENTITY-TYPE "CONTEXTS/ENTITY-TYPE!FEDERATION-CONTEXT"}}
    }
   {:target-requestid :INSTANTIATE-REQUESTID
    :target-name      "CONTEXTS/CONTEXT-INSTANTIATOR"
    :name             "CONTEXTS/MAIN"
    }
   {:target-requestid :INSTANTIATE-REQUESTID
    :target-name      "CONTEXTS/INSTANTIATOR-INSTANTIATOR"
    :name             "MAIN/SIMPLE-INSTANTIATOR"
    :descriptors      {:CONTEXTS/INSTANTIATION-DESCRIPTORS {:CONTEXTS/REQUESTID-MAP {:ADD-DESCRIPTOR-REQUESTID :ADD-DESCRIPTOR-OPERATIONID
                                                                                     :ADD-CLASSIFIER-REQUESTID :ADD-CLASSIFIER-OPERATIONID}}
                       :CONTEXTS/INSTANTIATION-CLASSIFIERS {:CONTEXTS/ENTITY-TYPE "MAIN/ENTITY-TYPE!SIMPLE"}}
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
                                          "MAIN/SIMPLE-INSTANTIATOR"]
                       :SCRIPT           [{:target-requestid :INSTANTIATE-REQUESTID
                                           :target-name      "MAIN/SIMPLE-INSTANTIATOR"
                                           :name             "MAIN/SIMPLE_3"
                                           }
                                          {:target-requestid :ADD-DESCRIPTOR-REQUESTID
                                           :target-name      "MAIN/SIMPLE_1"
                                           :descriptor       :DEGREE-OF-POLISH
                                           :descriptor-value "MIDDLING"
                                           }
                                          {:target-requestid :ADD-CLASSIFIER-REQUESTID
                                           :target-name      "MAIN/SIMPLE_1"
                                           :classifier       :APPLICATION
                                           :classifier-value :FIDDLING
                                           }
                                          {:target-requestid :ADD-DESCRIPTOR-REQUESTID
                                           :target-name      "MAIN/SIMPLE_3"
                                           :descriptor       :DEGREE-OF-POLISH
                                           :descriptor-value "MIDDLING"
                                           }
                                          {:target-requestid :ADD-CLASSIFIER-REQUESTID
                                           :target-name      "MAIN/SIMPLE_3"
                                           :classifier       :APPLICATION
                                           :classifier-value :FIDDLING
                                           }
                                          ]}
    }
   {:target-requestid :RUN-FEDERATION-REQUESTID
    :target-name      "MAIN/FEDERATOR_1"
    }
   ])
