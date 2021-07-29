(ns ae.script1)

(def script1
  [{:target_requestid :CONTEXTS/REGISTER_ENTITY_REQUESTID
    :target_name      "ROOT+CONTEXTS"
    :name             "CONTEXTS+INSTANTIATOR_INSTANTIATOR"
    :descriptors      {:CONTEXTS/INVARIANT                 true
                       :CONTEXTS/REQUESTID_MAP             {:CONTEXTS/INSTANTIATE_REQUESTID [:INSTANTIATE_OPERATIONID]}
                       :CONTEXTS/INSTANTIATION_DESCRIPTORS {:CONTEXTS/INVARIANT     true
                                                            :CONTEXTS/REQUESTID_MAP {:CONTEXTS/INSTANTIATE_REQUESTID [:INSTANTIATE_OPERATIONID]}}
                       :CONTEXTS/INSTANTIATION_CLASSIFIERS {:CONTEXTS/ENTITY_TYPE "CONTEXTS+ENTITY_TYPE!INSTANTIATOR"}}
    :classifiers      {:CONTEXTS/ENTITY_TYPE "CONTEXTS+ENTITY_TYPE!INSTANTIATOR"}
    }
   {:target_requestid :CONTEXTS/INSTANTIATE_REQUESTID
    :target_name      "CONTEXTS+INSTANTIATOR_INSTANTIATOR"
    :name             "CONTEXTS+REQUESTID_INSTANTIATOR"
    :descriptors      {:CONTEXTS/INSTANTIATION_DESCRIPTORS {:CONTEXTS/INVARIANT true}
                       :CONTEXTS/INSTANTIATION_CLASSIFIERS {:CONTEXTS/ENTITY_TYPE "CONTEXTS+ENTITY_TYPE!REQUESTID"}}
    }
   {:target_requestid :CONTEXTS/INSTANTIATE_REQUESTID
    :target_name      "CONTEXTS+REQUESTID_INSTANTIATOR"
    :name             "CONTEXTS+ACQUIRE_REQUESTID"
    }
   {:target_requestid :CONTEXTS/INSTANTIATE_REQUESTID
    :target_name      "CONTEXTS+REQUESTID_INSTANTIATOR"
    :name             "CONTEXTS+ADD_CLASSIFIER_REQUESTID"
    }
   {:target_requestid :CONTEXTS/INSTANTIATE_REQUESTID
    :target_name      "CONTEXTS+REQUESTID_INSTANTIATOR"
    :name             "CONTEXTS+ADD_DESCRIPTOR_REQUESTID"
    }
   {:target_requestid :CONTEXTS/INSTANTIATE_REQUESTID
    :target_name      "CONTEXTS+REQUESTID_INSTANTIATOR"
    :name             "CONTEXTS+INSTANTIATE_REQUESTID"
    :descriptors      {:CONTEXTS/READ_ONLY true}
    }
   {:target_requestid :CONTEXTS/INSTANTIATE_REQUESTID
    :target_name      "CONTEXTS+REQUESTID_INSTANTIATOR"
    :name             "CONTEXTS+REGISTER_CLASSIFIER_REQUESTID"
    }
   {:target_requestid :CONTEXTS/INSTANTIATE_REQUESTID
    :target_name      "CONTEXTS+REQUESTID_INSTANTIATOR"
    :name             "CONTEXTS+REGISTER_ENTITY_REQUESTID"
    }
   {:target_requestid :CONTEXTS/INSTANTIATE_REQUESTID
    :target_name      "CONTEXTS+REQUESTID_INSTANTIATOR"
    :name             "CONTEXTS+RELEASE_REQUESTID"
    }
   {:target_requestid :CONTEXTS/INSTANTIATE_REQUESTID
    :target_name      "CONTEXTS+REQUESTID_INSTANTIATOR"
    :name             "CONTEXTS+ROUTE_REQUESTID"
    }
   {:target_requestid :CONTEXTS/INSTANTIATE_REQUESTID
    :target_name      "CONTEXTS+REQUESTID_INSTANTIATOR"
    :name             "CONTEXTS+RUN_FEDERATION_REQUESTID"
    }
   {:target_requestid :CONTEXTS/INSTANTIATE_REQUESTID
    :target_name      "CONTEXTS+REQUESTID_INSTANTIATOR"
    :name            "CONTEXTS+CONTEXT_REPORT_REQUESTID"
    :descriptors      {:CONTEXTS/READ_ONLY true}
    }
   {:target_requestid :CONTEXTS/INSTANTIATE_REQUESTID
    :target_name      "CONTEXTS+INSTANTIATOR_INSTANTIATOR"
    :name             "CONTEXTS+DESCRIPTOR_INSTANTIATOR"
    :descriptors      {:CONTEXTS/INSTANTIATION_DESCRIPTORS {:CONTEXTS/INVARIANT true}
                       :CONTEXTS/INSTANTIATION_CLASSIFIERS {:CONTEXTS/ENTITY_TYPE "CONTEXTS+ENTITY_TYPE!DESCRIPTOR"}}
    }
   {:target_requestid :CONTEXTS/INSTANTIATE_REQUESTID
    :target_name      "CONTEXTS+DESCRIPTOR_INSTANTIATOR"
    :name             "CONTEXTS+INSTANTIATOR"
    }
   {:target_requestid :CONTEXTS/INSTANTIATE_REQUESTID
    :target_name      "CONTEXTS+DESCRIPTOR_INSTANTIATOR"
    :name             "CONTEXTS+READ_ONLY"
    }
   {:target_requestid :CONTEXTS/INSTANTIATE_REQUESTID
    :target_name      "CONTEXTS+DESCRIPTOR_INSTANTIATOR"
    :name             "CONTEXTS+REQUESTID_MAP"
    }
   {:target_requestid :CONTEXTS/INSTANTIATE_REQUESTID
    :target_name      "CONTEXTS+DESCRIPTOR_INSTANTIATOR"
    :name             "CONTEXTS+INVARIANT"
    }
   {:target_requestid :CONTEXTS/INSTANTIATE_REQUESTID
    :target_name      "CONTEXTS+DESCRIPTOR_INSTANTIATOR"
    :name             "CONTEXTS+INSTANTIATION_DESCRIPTORS"
    }
   {:target_requestid :CONTEXTS/INSTANTIATE_REQUESTID
    :target_name      "CONTEXTS+DESCRIPTOR_INSTANTIATOR"
    :name             "CONTEXTS+INSTANTIATION_CLASSIFIERS"
    }
   {:target_requestid :CONTEXTS/INSTANTIATE_REQUESTID
    :target_name      "CONTEXTS+INSTANTIATOR_INSTANTIATOR"
    :name             "CONTEXTS+CLASSIFIER_INSTANTIATOR"
    :descriptors      {:CONTEXTS/INSTANTIATION_DESCRIPTORS {:CONTEXTS/INVARIANT true}
                       :CONTEXTS/INSTANTIATION_CLASSIFIERS {:CONTEXTS/ENTITY_TYPE "CONTEXTS+ENTITY_TYPE!CLASSIFIER"}}
    }
   {:target_requestid :CONTEXTS/INSTANTIATE_REQUESTID
    :target_name      "CONTEXTS+CLASSIFIER_INSTANTIATOR"
    :name             "CONTEXTS+ENTITY_TYPE"
    }
   {:target_requestid :CONTEXTS/INSTANTIATE_REQUESTID
    :target_name      "CONTEXTS+INSTANTIATOR_INSTANTIATOR"
    :name             "CONTEXTS+CLASSIFIER_VALUE_INSTANTIATOR"
    :descriptors      {:CONTEXTS/INSTANTIATION_DESCRIPTORS {:CONTEXTS/INVARIANT true}
                       :CONTEXTS/INSTANTIATION_CLASSIFIERS {:CONTEXTS/ENTITY_TYPE "CONTEXTS+ENTITY_TYPE!CLASSIFIER_VALUE"}}
    }
   {:target_requestid :CONTEXTS/INSTANTIATE_REQUESTID
    :target_name      "CONTEXTS+CLASSIFIER_VALUE_INSTANTIATOR"
    :name             "CONTEXTS+ENTITY_TYPE!INSTANTIATOR"
    :classifiers      {:CONTEXTS/VALUE_OF :CONTEXTS/ENTITY_TYPE}
    }
   {:target_requestid :CONTEXTS/INSTANTIATE_REQUESTID
    :target_name      "CONTEXTS+CLASSIFIER_VALUE_INSTANTIATOR"
    :name             "CONTEXTS+ENTITY_TYPE!REQUESTID"
    :classifiers      {:CONTEXTS/VALUE_OF :CONTEXTS/ENTITY_TYPE}
    }
   {:target_requestid :CONTEXTS/INSTANTIATE_REQUESTID
    :target_name      "CONTEXTS+CLASSIFIER_VALUE_INSTANTIATOR"
    :name             "CONTEXTS+ENTITY_TYPE!DESCRIPTOR"
    :classifiers      {:CONTEXTS/VALUE_OF :CONTEXTS/ENTITY_TYPE}
    }
   {:target_requestid :CONTEXTS/INSTANTIATE_REQUESTID
    :target_name      "CONTEXTS+CLASSIFIER_VALUE_INSTANTIATOR"
    :name             "CONTEXTS+ENTITY_TYPE!CLASSIFIER_VALUE"
    :classifiers      {:CONTEXTS/VALUE_OF :CONTEXTS/ENTITY_TYPE}
    }
   {:target_requestid :CONTEXTS/INSTANTIATE_REQUESTID
    :target_name      "CONTEXTS+CLASSIFIER_VALUE_INSTANTIATOR"
    :name             "CONTEXTS+ENTITY_TYPE!CONTEXT"
    :classifiers      {:CONTEXTS/VALUE_OF :CONTEXTS/ENTITY_TYPE}
    }
   {:target_requestid :CONTEXTS/INSTANTIATE_REQUESTID
    :target_name      "CONTEXTS+CLASSIFIER_VALUE_INSTANTIATOR"
    :name             "CONTEXTS+ENTITY_TYPE!FEDERATOR"
    :classifiers      {:CONTEXTS/VALUE_OF :CONTEXTS/ENTITY_TYPE}
    }
   {:target_requestid :CONTEXTS/INSTANTIATE_REQUESTID
    :target_name      "CONTEXTS+CLASSIFIER_VALUE_INSTANTIATOR"
    :name             "CONTEXTS+ENTITY_TYPE!FEDERATION_CONTEXT"
    :classifiers      {:CONTEXTS/VALUE_OF :CONTEXTS/ENTITY_TYPE}
    }
   {:target_requestid :CONTEXTS/INSTANTIATE_REQUESTID
    :target_name      "CONTEXTS+CLASSIFIER_INSTANTIATOR"
    :name             "CONTEXTS+VALUE_OF"
    }
   {:target_requestid :CONTEXTS/INSTANTIATE_REQUESTID
    :target_name      "CONTEXTS+CLASSIFIER_VALUE_INSTANTIATOR"
    :name             "CONTEXTS+VALUE_OF!ENTITY_TYPE"
    :classifiers      {:CONTEXTS/VALUE_OF :CONTEXTS/VALUE_OF}
    }
   {:target_requestid :CONTEXTS/INSTANTIATE_REQUESTID
    :target_name      "CONTEXTS+INSTANTIATOR_INSTANTIATOR"
    :name             "CONTEXTS+CONTEXT_INSTANTIATOR"
    :descriptors      {:CONTEXTS/INSTANTIATION_DESCRIPTORS {:CONTEXTS/REQUESTID_MAP {:CONTEXTS/REGISTER_ENTITY_REQUESTID     [:REGISTER_ENTITY_OPERATIONID]
                                                                                     :CONTEXTS/ROUTE_REQUESTID               [:ROUTE_OPERATIONID]
                                                                                     :CONTEXTS/REGISTER_CLASSIFIER_REQUESTID [:REGISTER_CLASSIFIER_OPERATIONID]}}
                       :CONTEXTS/INSTANTIATION_CLASSIFIERS {:CONTEXTS/ENTITY_TYPE "CONTEXTS+ENTITY_TYPE!CONTEXT"}}
    }
   {:target_requestid :CONTEXTS/INSTANTIATE_REQUESTID
    :target_name      "CONTEXTS+INSTANTIATOR_INSTANTIATOR"
    :name             "CONTEXTS+FEDERATOR_INSTANTIATOR"
    :descriptors      {:CONTEXTS/INSTANTIATION_DESCRIPTORS {:CONTEXTS/REQUESTID_MAP {:CONTEXTS/RUN_FEDERATION_REQUESTID [:RUN_FEDERATION_OPERATIONID]}}
                       :CONTEXTS/INSTANTIATION_CLASSIFIERS {:CONTEXTS/ENTITY_TYPE "CONTEXTS+ENTITY_TYPE!FEDERATOR"}}
    }
   {:target_requestid :CONTEXTS/INSTANTIATE_REQUESTID
    :target_name      "CONTEXTS+INSTANTIATOR_INSTANTIATOR"
    :name             "CONTEXTS+FEDERATION_CONTEXT_INSTANTIATOR"
    :descriptors      {:CONTEXTS/INSTANTIATION_DESCRIPTORS {:CONTEXTS/REQUESTID_MAP {:CONTEXTS/ACQUIRE_REQUESTID [:FEDERATION_ACQUIRE_OPERATIONID]
                                                                                     :CONTEXTS/RELEASE_REQUESTID [:FEDERATION_RELEASE_OPERATIONID]}}
                       :CONTEXTS/INSTANTIATION_CLASSIFIERS {:CONTEXTS/ENTITY_TYPE "CONTEXTS+ENTITY_TYPE!FEDERATION_CONTEXT"}}
    }
   {:target_requestid :CONTEXTS/INSTANTIATE_REQUESTID
    :target_name      "CONTEXTS+CONTEXT_INSTANTIATOR"
    :name             "CONTEXTS+MAIN"
    }
   {:target_requestid :CONTEXTS/INSTANTIATE_REQUESTID
    :target_name      "CONTEXTS+INSTANTIATOR_INSTANTIATOR"
    :name             "MAIN+SIMPLE_INSTANTIATOR"
    :descriptors      {:CONTEXTS/INSTANTIATION_DESCRIPTORS {:CONTEXTS/REQUESTID_MAP {:CONTEXTS/ADD_DESCRIPTOR_REQUESTID [:ADD_DESCRIPTOR_OPERATIONID]
                                                                                     :CONTEXTS/ADD_CLASSIFIER_REQUESTID [:ADD_CLASSIFIER_OPERATIONID]}}
                       :CONTEXTS/INSTANTIATION_CLASSIFIERS {:CONTEXTS/ENTITY_TYPE "MAIN+ENTITY_TYPE!SIMPLE"}}
    }
   {:target_requestid :CONTEXTS/INSTANTIATE_REQUESTID
    :target_name      "CONTEXTS+CLASSIFIER_VALUE_INSTANTIATOR"
    :name             "MAIN+ENTITY_TYPE!SIMPLE"
    :classifiers      {:CONTEXTS/VALUE_OF :CONTEXTS/ENTITY_TYPE}
    }
   {:target_requestid :CONTEXTS/INSTANTIATE_REQUESTID
    :target_name      "MAIN+SIMPLE_INSTANTIATOR"
    :name             "MAIN+SIMPLE_1"
    }
   {:target_requestid :CONTEXTS/INSTANTIATE_REQUESTID
    :target_name      "MAIN+SIMPLE_INSTANTIATOR"
    :name             "MAIN+SIMPLE_2"
    }
   {:target_requestid :CONTEXTS/INSTANTIATE_REQUESTID
    :target_name      "CONTEXTS+DESCRIPTOR_INSTANTIATOR"
    :name             "MAIN+DEGREE_OF_POLISH"
    }
   {:target_requestid :CONTEXTS/INSTANTIATE_REQUESTID
    :target_name      "CONTEXTS+CLASSIFIER_INSTANTIATOR"
    :name             "MAIN+APPLICATION"
    }
   {:target_requestid :CONTEXTS/INSTANTIATE_REQUESTID
    :target_name      "CONTEXTS+CLASSIFIER_VALUE_INSTANTIATOR"
    :name             "MAIN+APPLICATION!FIDDLING"
    :classifiers      {:CONTEXTS/VALUE_OF :MAIN/APPLICATION}
    }
   {:target_requestid :CONTEXTS/INSTANTIATE_REQUESTID
    :target_name      "CONTEXTS+FEDERATOR_INSTANTIATOR"
    :name             "MAIN+FEDERATOR_1"
    :descriptors      {:CONTEXTS/FEDERATION_NAMES ["MAIN+SIMPLE_1"]
                       :CONTEXTS/SCRIPT           [{:target_requestid :CONTEXTS/INSTANTIATE_REQUESTID
                                                    :target_name      "MAIN+SIMPLE_INSTANTIATOR"
                                                    :name             "MAIN+SIMPLE_3"
                                                    }
                                                   {:target_requestid :CONTEXTS/ADD_DESCRIPTOR_REQUESTID
                                                    :target_name      "MAIN+SIMPLE_1"
                                                    :descriptor       :MAIN/DEGREE_OF_POLISH
                                                    :descriptor-value "MIDDLING"
                                                    }
                                                   {:target_requestid :CONTEXTS/ADD_CLASSIFIER_REQUESTID
                                                    :target_name      "MAIN+SIMPLE_1"
                                                    :classifier       :MAIN/APPLICATION
                                                    :classifier-value :MAIN/APPLICATION!FIDDLING
                                                    }
                                                   {:target_requestid :CONTEXTS/ADD_DESCRIPTOR_REQUESTID
                                                    :target_name      "MAIN+SIMPLE_3"
                                                    :descriptor       :MAIN/DEGREE_OF_POLISH
                                                    :descriptor-value "MIDDLING"
                                                    }
                                                   {:target_requestid :CONTEXTS/ADD_CLASSIFIER_REQUESTID
                                                    :target_name      "MAIN+SIMPLE_3"
                                                    :classifier       :MAIN/APPLICATION
                                                    :classifier-value :MAIN/APPLICATION!FIDDLING
                                                    }]}}
   {:target_requestid :CONTEXTS/RUN_FEDERATION_REQUESTID
    :target_name      "MAIN+FEDERATOR_1"
    }
   {:target_requestid :CONTEXTS/CONTEXT_REPORT_REQUESTID
    :target_name      "ROOT+CONTEXTS"
    }
   ])
