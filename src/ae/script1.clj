(ns ae.script1)

(def script1
  [{:target_requestid :REGISTER_ENTITY_REQUESTID
    :target_name      "ROOT/CONTEXTS"
    :name             "CONTEXTS/INSTANTIATOR_INSTANTIATOR"
    :descriptors      {:CONTEXTS/INVARIANT                 true
                       :CONTEXTS/REQUESTID_MAP             {:INSTANTIATE_REQUESTID :INSTANTIATE_OPERATIONID}
                       :CONTEXTS/INSTANTIATION_DESCRIPTORS {:CONTEXTS/INVARIANT     true
                                                            :CONTEXTS/REQUESTID_MAP {:INSTANTIATE_REQUESTID :INSTANTIATE_OPERATIONID}}
                       :CONTEXTS/INSTANTIATION_CLASSIFIERS {:CONTEXTS/ENTITY_TYPE "CONTEXTS/ENTITY_TYPE!INSTANTIATOR"}}
    :classifiers      {:CONTEXTS/ENTITY_TYPE "CONTEXTS/ENTITY_TYPE!INSTANTIATOR"}
    }
   {:target_requestid :INSTANTIATE_REQUESTID
    :target_name      "CONTEXTS/INSTANTIATOR_INSTANTIATOR"
    :name             "CONTEXTS/DESCRIPTOR_INSTANTIATOR"
    :descriptors      {:CONTEXTS/INSTANTIATION_CLASSIFIERS {:CONTEXTS/ENTITY_TYPE "CONTEXTS/ENTITY_TYPE!DESCRIPTOR"}}
    }
   {:target_requestid :INSTANTIATE_REQUESTID
    :target_name      "CONTEXTS/DESCRIPTOR_INSTANTIATOR"
    :name             "CONTEXTS/REQUESTID_MAP"
    }
   {:target_requestid :INSTANTIATE_REQUESTID
    :target_name      "CONTEXTS/DESCRIPTOR_INSTANTIATOR"
    :name             "CONTEXTS/INVARIANT"
    }
   {:target_requestid :INSTANTIATE_REQUESTID
    :target_name      "CONTEXTS/DESCRIPTOR_INSTANTIATOR"
    :name             "CONTEXTS/INSTANTIATION_DESCRIPTORS"
    }
   {:target_requestid :INSTANTIATE_REQUESTID
    :target_name      "CONTEXTS/DESCRIPTOR_INSTANTIATOR"
    :name             "CONTEXTS/INSTANTIATION_CLASSIFIERS"
    }
   {:target_requestid :INSTANTIATE_REQUESTID
    :target_name      "CONTEXTS/INSTANTIATOR_INSTANTIATOR"
    :name             "CONTEXTS/CONTEXT_INSTANTIATOR"
    :descriptors      {:CONTEXTS/INSTANTIATION_DESCRIPTORS {:CONTEXTS/REQUESTID_MAP {:REGISTER_ENTITY_REQUESTID     :REGISTER_ENTITY_OPERATIONID
                                                                                     :ROUTE_REQUESTID               :ROUTE_OPERATIONID
                                                                                     :REGISTER_CLASSIFIER_REQUESTID :REGISTER_CLASSIFIER_OPERATIONID}}
                       :CONTEXTS/INSTANTIATION_CLASSIFIERS {:CONTEXTS/ENTITY_TYPE "CONTEXTS/ENTITY_TYPE!CONTEXT"}}
    }
   {:target_requestid :INSTANTIATE_REQUESTID
    :target_name      "CONTEXTS/INSTANTIATOR_INSTANTIATOR"
    :name             "CONTEXTS/FEDERATOR_INSTANTIATOR"
    :descriptors      {:CONTEXTS/INSTANTIATION_DESCRIPTORS {:CONTEXTS/REQUESTID_MAP {:RUN_FEDERATION_REQUESTID :RUN_FEDERATION_OPERATIONID}}
                       :CONTEXTS/INSTANTIATION_CLASSIFIERS {:CONTEXTS/ENTITY_TYPE "CONTEXTS/ENTITY_TYPE!FEDERATOR"}}
    }
   {:target_requestid :INSTANTIATE_REQUESTID
    :target_name      "CONTEXTS/INSTANTIATOR_INSTANTIATOR"
    :name             "CONTEXTS/FEDERATION_CONTEXT_INSTANTIATOR"
    :descriptors      {:CONTEXTS/INSTANTIATION_DESCRIPTORS {:CONTEXTS/REQUESTID_MAP {:ACQUIRE_REQUESTID :FEDERATION_ACQUIRE_OPERATIONID
                                                                                     :RELEASE_REQUESTID :FEDERATION_RELEASE_OPERATIONID}}
                       :CONTEXTS/INSTANTIATION_CLASSIFIERS {:CONTEXTS/ENTITY_TYPE "CONTEXTS/ENTITY_TYPE!FEDERATION_CONTEXT"}}
    }
   {:target_requestid :INSTANTIATE_REQUESTID
    :target_name      "CONTEXTS/CONTEXT_INSTANTIATOR"
    :name             "CONTEXTS/MAIN"
    }
   {:target_requestid :INSTANTIATE_REQUESTID
    :target_name      "CONTEXTS/INSTANTIATOR_INSTANTIATOR"
    :name             "MAIN/SIMPLE_INSTANTIATOR"
    :descriptors      {:CONTEXTS/INSTANTIATION_DESCRIPTORS {:CONTEXTS/REQUESTID_MAP {:ADD_DESCRIPTOR_REQUESTID :ADD_DESCRIPTOR_OPERATIONID
                                                                                     :ADD_CLASSIFIER_REQUESTID :ADD_CLASSIFIER_OPERATIONID}}
                       :CONTEXTS/INSTANTIATION_CLASSIFIERS {:CONTEXTS/ENTITY_TYPE "MAIN/ENTITY_TYPE!SIMPLE"}}
    }
   {:target_requestid :INSTANTIATE_REQUESTID
    :target_name      "MAIN/SIMPLE_INSTANTIATOR"
    :name             "MAIN/SIMPLE_1"
    }
   {:target_requestid :INSTANTIATE_REQUESTID
    :target_name      "MAIN/SIMPLE_INSTANTIATOR"
    :name             "MAIN/SIMPLE_2"
    }
   {:target_requestid :INSTANTIATE_REQUESTID
    :target_name      "CONTEXTS/DESCRIPTOR_INSTANTIATOR"
    :name             "MAIN/DEGREE_OF_POLISH"
    }
   {:target_requestid :INSTANTIATE_REQUESTID
    :target_name      "CONTEXTS/FEDERATOR_INSTANTIATOR"
    :name             "MAIN/FEDERATOR_1"
    :descriptors      {:CONTEXTS/FEDERATION_NAMES ["MAIN/SIMPLE_1"
                                                   "MAIN/SIMPLE_INSTANTIATOR"]
                       :CONTEXTS/SCRIPT           [{:target_requestid :INSTANTIATE_REQUESTID
                                                    :target_name      "MAIN/SIMPLE_INSTANTIATOR"
                                                    :name             "MAIN/SIMPLE_3"
                                                    }
                                                   {:target_requestid :ADD_DESCRIPTOR_REQUESTID
                                                    :target_name      "MAIN/SIMPLE_1"
                                                    :descriptor       :MAIN/DEGREE_OF_POLISH
                                                    :descriptor-value "MIDDLING"
                                                    }
                                                   {:target_requestid :ADD_CLASSIFIER_REQUESTID
                                                    :target_name      "MAIN/SIMPLE_1"
                                                    :classifier       :MAIN/APPLICATION
                                                    :classifier-value :MAIN/APPLICATION!FIDDLING
                                                    }
                                                   {:target_requestid :ADD_DESCRIPTOR_REQUESTID
                                                    :target_name      "MAIN/SIMPLE_3"
                                                    :descriptor       :MAIN/DEGREE_OF_POLISH
                                                    :descriptor-value "MIDDLING"
                                                    }
                                                   {:target_requestid :ADD_CLASSIFIER_REQUESTID
                                                    :target_name      "MAIN/SIMPLE_3"
                                                    :classifier       :MAIN/APPLICATION
                                                    :classifier-value :MAIN/APPLICATION!FIDDLING
                                                    }
                                                   ]}
    }
   {:target_requestid :RUN_FEDERATION_REQUESTID
    :target_name      "MAIN/FEDERATOR_1"
    }
   ])
