(ns ae.script1)

(def script1
  [{:target-requestid :REGISTER-ENTITY-REQUESTID
    :target-name      "ROOT/CONTEXTS"
    :name             "CONTEXTS/INSTANTIATOR_INSTANTIATOR"
    :descriptors      {:CONTEXTS/INVARIANT                 true
                       :CONTEXTS/REQUESTID_MAP             {:INSTANTIATE-REQUESTID :INSTANTIATE-OPERATIONID}
                       :CONTEXTS/INSTANTIATION_DESCRIPTORS {:CONTEXTS/INVARIANT     true
                                                            :CONTEXTS/REQUESTID_MAP {:INSTANTIATE-REQUESTID :INSTANTIATE-OPERATIONID}}
                       :CONTEXTS/INSTANTIATION_CLASSIFIERS {:CONTEXTS/ENTITY_TYPE "CONTEXTS/ENTITY_TYPE!INSTANTIATOR"}}
    :classifiers      {:CONTEXTS/ENTITY_TYPE "CONTEXTS/ENTITY_TYPE!INSTANTIATOR"}
    }
   {:target-requestid :INSTANTIATE-REQUESTID
    :target-name      "CONTEXTS/INSTANTIATOR_INSTANTIATOR"
    :name             "CONTEXTS/CONTEXT-INSTANTIATOR"
    :descriptors      {:CONTEXTS/INSTANTIATION_DESCRIPTORS {:CONTEXTS/REQUESTID_MAP {:REGISTER-ENTITY-REQUESTID     :REGISTER-ENTITY-OPERATIONID
                                                                                     :ROUTE-REQUESTID               :ROUTE-OPERATIONID
                                                                                     :REGISTER-CLASSIFIER-REQUESTID :REGISTER-CLASSIFIER-OPERATIONID}}
                       :CONTEXTS/INSTANTIATION_CLASSIFIERS {:CONTEXTS/ENTITY_TYPE "CONTEXTS/ENTITY_TYPE!CONTEXT"}}
    }
   {:target-requestid :INSTANTIATE-REQUESTID
    :target-name      "CONTEXTS/INSTANTIATOR_INSTANTIATOR"
    :name             "CONTEXTS/FEDERATOR-INSTANTIATOR"
    :descriptors      {:CONTEXTS/INSTANTIATION_DESCRIPTORS {:CONTEXTS/REQUESTID_MAP {:RUN-FEDERATION-REQUESTID :RUN-FEDERATION-OPERATIONID}}
                       :CONTEXTS/INSTANTIATION_CLASSIFIERS {:CONTEXTS/ENTITY_TYPE "CONTEXTS/ENTITY_TYPE!FEDERATOR"}}
    }
   {:target-requestid :INSTANTIATE-REQUESTID
    :target-name      "CONTEXTS/INSTANTIATOR_INSTANTIATOR"
    :name             "CONTEXTS/FEDERATION-CONTEXT-INSTANTIATOR"
    :descriptors      {:CONTEXTS/INSTANTIATION_DESCRIPTORS {:CONTEXTS/REQUESTID_MAP {:ACQUIRE-REQUESTID :FEDERATION-ACQUIRE-OPERATIONID
                                                                                     :RELEASE-REQUESTID :FEDERATION-RELEASE-OPERATIONID
                                                                                     :ROUTE-REQUESTID   :FEDERATION-ROUTE-OPERATIONID}}
                       :CONTEXTS/INSTANTIATION_CLASSIFIERS {:CONTEXTS/ENTITY_TYPE "CONTEXTS/ENTITY_TYPE!FEDERATION-CONTEXT"}}
    }
   {:target-requestid :INSTANTIATE-REQUESTID
    :target-name      "CONTEXTS/CONTEXT-INSTANTIATOR"
    :name             "CONTEXTS/MAIN"
    }
   {:target-requestid :INSTANTIATE-REQUESTID
    :target-name      "CONTEXTS/INSTANTIATOR_INSTANTIATOR"
    :name             "MAIN/SIMPLE-INSTANTIATOR"
    :descriptors      {:CONTEXTS/INSTANTIATION_DESCRIPTORS {:CONTEXTS/REQUESTID_MAP {:ADD-DESCRIPTOR-REQUESTID :ADD-DESCRIPTOR-OPERATIONID
                                                                                     :ADD-CLASSIFIER-REQUESTID :ADD-CLASSIFIER-OPERATIONID}}
                       :CONTEXTS/INSTANTIATION_CLASSIFIERS {:CONTEXTS/ENTITY_TYPE "MAIN/ENTITY_TYPE!SIMPLE"}}
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
    :descriptors      {:CONTEXTS/FEDERATION-NAMES ["MAIN/SIMPLE_1"
                                                   "MAIN/SIMPLE-INSTANTIATOR"]
                       :CONTEXTS/SCRIPT           [{:target-requestid :INSTANTIATE-REQUESTID
                                                    :target-name      "MAIN/SIMPLE-INSTANTIATOR"
                                                    :name             "MAIN/SIMPLE_3"
                                                    }
                                                   {:target-requestid :ADD-DESCRIPTOR-REQUESTID
                                                    :target-name      "MAIN/SIMPLE_1"
                                                    :descriptor       :MAIN/DEGREE-OF-POLISH
                                                    :descriptor-value "MIDDLING"
                                                    }
                                                   {:target-requestid :ADD-CLASSIFIER-REQUESTID
                                                    :target-name      "MAIN/SIMPLE_1"
                                                    :classifier       :MAIN/APPLICATION
                                                    :classifier-value :MAIN/APPLICATION!FIDDLING
                                                    }
                                                   {:target-requestid :ADD-DESCRIPTOR-REQUESTID
                                                    :target-name      "MAIN/SIMPLE_3"
                                                    :descriptor       :MAIN/DEGREE-OF-POLISH
                                                    :descriptor-value "MIDDLING"
                                                    }
                                                   {:target-requestid :ADD-CLASSIFIER-REQUESTID
                                                    :target-name      "MAIN/SIMPLE_3"
                                                    :classifier       :MAIN/APPLICATION
                                                    :classifier-value :MAIN/APPLICATION!FIDDLING
                                                    }
                                                   ]}
    }
   {:target-requestid :RUN-FEDERATION-REQUESTID
    :target-name      "MAIN/FEDERATOR_1"
    }
   ])
