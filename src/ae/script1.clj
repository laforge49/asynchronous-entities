(ns ae.script1)

(def script1
  [{:target_requestid :SYSTEMcontext/REGISTER_ENTITY_REQUESTID
    :target_name      "ROOT+SYSTEMcontext"
    :name             "SYSTEMcontext+INSTANTIATOR_INSTANTIATOR"
    :descriptors      {:SYSTEMcontext/INVARIANT                 true
                       :SYSTEMcontext/REQUESTID_MAP             {:SYSTEMcontext/INSTANTIATE_REQUESTID   [:INSTANTIATE_OPERATIONID]
                                                            :SYSTEMcontext/ENTITY_REPORT_REQUESTID [:ENTITY_REPORT_OPERATIONID]}
                       :SYSTEMcontext/INSTANTIATION_DESCRIPTORS {:SYSTEMcontext/INVARIANT     true
                                                            :SYSTEMcontext/REQUESTID_MAP {:SYSTEMcontext/INSTANTIATE_REQUESTID   [:INSTANTIATE_OPERATIONID]
                                                                                     :SYSTEMcontext/ENTITY_REPORT_REQUESTID [:ENTITY_REPORT_OPERATIONID]}}
                       :SYSTEMcontext/INSTANTIATION_CLASSIFIERS {:SYSTEMcontext/ENTITY_TYPE "SYSTEMcontext+INSTANTIATOR"}}
    :classifiers      {:SYSTEMcontext/ENTITY_TYPE "SYSTEMcontext+INSTANTIATOR"}
    }
   {:target_requestid :SYSTEMcontext/INSTANTIATE_REQUESTID
    :target_name      "SYSTEMcontext+INSTANTIATOR_INSTANTIATOR"
    :name             "SYSTEMcontext+REQUESTID_INSTANTIATOR"
    :descriptors      {:SYSTEMcontext/INSTANTIATION_DESCRIPTORS {:SYSTEMcontext/INVARIANT     true
                                                            :SYSTEMcontext/REQUESTID_MAP {:SYSTEMcontext/ENTITY_REPORT_REQUESTID [:ENTITY_REPORT_OPERATIONID]}}
                       :SYSTEMcontext/INSTANTIATION_CLASSIFIERS {:SYSTEMcontext/ENTITY_TYPE "SYSTEMcontext+REQUESTID"}}
    }
   {:target_requestid :SYSTEMcontext/INSTANTIATE_REQUESTID
    :target_name      "SYSTEMcontext+REQUESTID_INSTANTIATOR"
    :name             "SYSTEMcontext+ACQUIRE_REQUESTID"
    }
   {:target_requestid :SYSTEMcontext/INSTANTIATE_REQUESTID
    :target_name      "SYSTEMcontext+REQUESTID_INSTANTIATOR"
    :name             "SYSTEMcontext+ADD_CLASSIFIER_REQUESTID"
    }
   {:target_requestid :SYSTEMcontext/INSTANTIATE_REQUESTID
    :target_name      "SYSTEMcontext+REQUESTID_INSTANTIATOR"
    :name             "SYSTEMcontext+ADD_DESCRIPTOR_REQUESTID"
    }
   {:target_requestid :SYSTEMcontext/INSTANTIATE_REQUESTID
    :target_name      "SYSTEMcontext+REQUESTID_INSTANTIATOR"
    :name             "SYSTEMcontext+INSTANTIATE_REQUESTID"
    :descriptors      {:SYSTEMcontext/READ_ONLY true}
    }
   {:target_requestid :SYSTEMcontext/INSTANTIATE_REQUESTID
    :target_name      "SYSTEMcontext+REQUESTID_INSTANTIATOR"
    :name             "SYSTEMcontext+REGISTER_CLASSIFIER_REQUESTID"
    }
   {:target_requestid :SYSTEMcontext/INSTANTIATE_REQUESTID
    :target_name      "SYSTEMcontext+REQUESTID_INSTANTIATOR"
    :name             "SYSTEMcontext+REGISTER_ENTITY_REQUESTID"
    }
   {:target_requestid :SYSTEMcontext/INSTANTIATE_REQUESTID
    :target_name      "SYSTEMcontext+REQUESTID_INSTANTIATOR"
    :name             "SYSTEMcontext+RELEASE_REQUESTID"
    }
   {:target_requestid :SYSTEMcontext/INSTANTIATE_REQUESTID
    :target_name      "SYSTEMcontext+REQUESTID_INSTANTIATOR"
    :name             "SYSTEMcontext+ROUTE_REQUESTID"
    }
   {:target_requestid :SYSTEMcontext/INSTANTIATE_REQUESTID
    :target_name      "SYSTEMcontext+REQUESTID_INSTANTIATOR"
    :name             "SYSTEMcontext+RUN_FEDERATION_REQUESTID"
    }
   {:target_requestid :SYSTEMcontext/INSTANTIATE_REQUESTID
    :target_name      "SYSTEMcontext+REQUESTID_INSTANTIATOR"
    :name             "SYSTEMcontext+CONTEXT_REPORT_REQUESTID"
    :descriptors      {:SYSTEMcontext/READ_ONLY true}
    }
   {:target_requestid :SYSTEMcontext/INSTANTIATE_REQUESTID
    :target_name      "SYSTEMcontext+INSTANTIATOR_INSTANTIATOR"
    :name             "SYSTEMcontext+DESCRIPTOR_INSTANTIATOR"
    :descriptors      {:SYSTEMcontext/INSTANTIATION_DESCRIPTORS {:SYSTEMcontext/INVARIANT true
                                                            :SYSTEMcontext/REQUESTID_MAP {:SYSTEMcontext/ENTITY_REPORT_REQUESTID [:ENTITY_REPORT_OPERATIONID]}}
                       :SYSTEMcontext/INSTANTIATION_CLASSIFIERS {:SYSTEMcontext/ENTITY_TYPE "SYSTEMcontext+DESCRIPTOR"}}
    }
   {:target_requestid :SYSTEMcontext/INSTANTIATE_REQUESTID
    :target_name      "SYSTEMcontext+DESCRIPTOR_INSTANTIATOR"
    :name             "SYSTEMcontext+READ_ONLY"
    }
   {:target_requestid :SYSTEMcontext/INSTANTIATE_REQUESTID
    :target_name      "SYSTEMcontext+DESCRIPTOR_INSTANTIATOR"
    :name             "SYSTEMcontext+REQUESTID_MAP"
    }
   {:target_requestid :SYSTEMcontext/INSTANTIATE_REQUESTID
    :target_name      "SYSTEMcontext+DESCRIPTOR_INSTANTIATOR"
    :name             "SYSTEMcontext+INVARIANT"
    }
   {:target_requestid :SYSTEMcontext/INSTANTIATE_REQUESTID
    :target_name      "SYSTEMcontext+DESCRIPTOR_INSTANTIATOR"
    :name             "SYSTEMcontext+INSTANTIATION_DESCRIPTORS"
    }
   {:target_requestid :SYSTEMcontext/INSTANTIATE_REQUESTID
    :target_name      "SYSTEMcontext+DESCRIPTOR_INSTANTIATOR"
    :name             "SYSTEMcontext+INSTANTIATION_CLASSIFIERS"
    }
   {:target_requestid :SYSTEMcontext/INSTANTIATE_REQUESTID
    :target_name      "SYSTEMcontext+INSTANTIATOR_INSTANTIATOR"
    :name             "SYSTEMcontext+CLASSIFIER_INSTANTIATOR"
    :descriptors      {:SYSTEMcontext/INSTANTIATION_DESCRIPTORS {:SYSTEMcontext/INVARIANT true
                                                            :SYSTEMcontext/REQUESTID_MAP {:SYSTEMcontext/ENTITY_REPORT_REQUESTID [:ENTITY_REPORT_OPERATIONID]}}
                       :SYSTEMcontext/INSTANTIATION_CLASSIFIERS {:SYSTEMcontext/ENTITY_TYPE "SYSTEMcontext+CLASSIFIER"}}
    }
   {:target_requestid :SYSTEMcontext/INSTANTIATE_REQUESTID
    :target_name      "SYSTEMcontext+CLASSIFIER_INSTANTIATOR"
    :name             "SYSTEMcontext+ENTITY_TYPE"
    }
   {:target_requestid :SYSTEMcontext/INSTANTIATE_REQUESTID
    :target_name      "SYSTEMcontext+INSTANTIATOR_INSTANTIATOR"
    :name             "SYSTEMcontext+CLASSIFIER_VALUE_INSTANTIATOR"
    :descriptors      {:SYSTEMcontext/INSTANTIATION_DESCRIPTORS {:SYSTEMcontext/INVARIANT     true
                                                            :SYSTEMcontext/REQUESTID_MAP {:SYSTEMcontext/ENTITY_REPORT_REQUESTID [:ENTITY_REPORT_OPERATIONID]}}
                       :SYSTEMcontext/INSTANTIATION_CLASSIFIERS {:SYSTEMcontext/ENTITY_TYPE "SYSTEMcontext+CLASSIFIER_VALUE"}}
    }
   {:target_requestid :SYSTEMcontext/INSTANTIATE_REQUESTID
    :target_name      "SYSTEMcontext+CLASSIFIER_VALUE_INSTANTIATOR"
    :name             "SYSTEMcontext+REQUESTID"
    }
   {:target_requestid :SYSTEMcontext/INSTANTIATE_REQUESTID
    :target_name      "SYSTEMcontext+CLASSIFIER_VALUE_INSTANTIATOR"
    :name             "SYSTEMcontext+DESCRIPTOR"
    }
   {:target_requestid :SYSTEMcontext/INSTANTIATE_REQUESTID
    :target_name      "SYSTEMcontext+CLASSIFIER_VALUE_INSTANTIATOR"
    :name             "SYSTEMcontext+CLASSIFIER_VALUE"
    }
   {:target_requestid :SYSTEMcontext/INSTANTIATE_REQUESTID
    :target_name      "SYSTEMcontext+CLASSIFIER_VALUE_INSTANTIATOR"
    :name             "SYSTEMcontext+CONTEXT"
    }
   {:target_requestid :SYSTEMcontext/INSTANTIATE_REQUESTID
    :target_name      "SYSTEMcontext+CLASSIFIER_VALUE_INSTANTIATOR"
    :name             "SYSTEMcontext+FEDERATOR"
    }
   {:target_requestid :SYSTEMcontext/INSTANTIATE_REQUESTID
    :target_name      "SYSTEMcontext+CLASSIFIER_VALUE_INSTANTIATOR"
    :name             "SYSTEMcontext+FEDERATION_CONTEXT"
    }
   {:target_requestid :SYSTEMcontext/INSTANTIATE_REQUESTID
    :target_name      "SYSTEMcontext+INSTANTIATOR_INSTANTIATOR"
    :name             "SYSTEMcontext+CONTEXT_INSTANTIATOR"
    :descriptors      {:SYSTEMcontext/INSTANTIATION_DESCRIPTORS {:SYSTEMcontext/REQUESTID_MAP {:SYSTEMcontext/REGISTER_ENTITY_REQUESTID     [:REGISTER_ENTITY_OPERATIONID]
                                                                                     :SYSTEMcontext/ROUTE_REQUESTID               [:ROUTE_OPERATIONID]
                                                                                     :SYSTEMcontext/REGISTER_CLASSIFIER_REQUESTID [:REGISTER_CLASSIFIER_OPERATIONID]
                                                                                     :SYSTEMcontext/ENTITY_REPORT_REQUESTID       [:CONTEXT_REPORT_OPERATIONID]}}
                       :SYSTEMcontext/INSTANTIATION_CLASSIFIERS {:SYSTEMcontext/ENTITY_TYPE "SYSTEMcontext+CONTEXT"}}
    }
   {:target_requestid :SYSTEMcontext/INSTANTIATE_REQUESTID
    :target_name      "SYSTEMcontext+INSTANTIATOR_INSTANTIATOR"
    :name             "SYSTEMcontext+FEDERATOR_INSTANTIATOR"
    :descriptors      {:SYSTEMcontext/INSTANTIATION_DESCRIPTORS {:SYSTEMcontext/REQUESTID_MAP {:SYSTEMcontext/RUN_FEDERATION_REQUESTID [:RUN_FEDERATION_OPERATIONID]
                                                                                     :SYSTEMcontext/ENTITY_REPORT_REQUESTID  [:ENTITY_REPORT_OPERATIONID]}}
                       :SYSTEMcontext/INSTANTIATION_CLASSIFIERS {:SYSTEMcontext/ENTITY_TYPE "SYSTEMcontext+FEDERATOR"}}
    }
   {:target_requestid :SYSTEMcontext/INSTANTIATE_REQUESTID
    :target_name      "SYSTEMcontext+INSTANTIATOR_INSTANTIATOR"
    :name             "SYSTEMcontext+FEDERATION_CONTEXT_INSTANTIATOR"
    :descriptors      {:SYSTEMcontext/INSTANTIATION_DESCRIPTORS {:SYSTEMcontext/REQUESTID_MAP {:SYSTEMcontext/ACQUIRE_REQUESTID [:FEDERATION_ACQUIRE_OPERATIONID]
                                                                                     :SYSTEMcontext/RELEASE_REQUESTID [:FEDERATION_RELEASE_OPERATIONID]}}
                       :SYSTEMcontext/INSTANTIATION_CLASSIFIERS {:SYSTEMcontext/ENTITY_TYPE "SYSTEMcontext+FEDERATION_CONTEXT"}}
    }
   {:target_requestid :SYSTEMcontext/INSTANTIATE_REQUESTID
    :target_name      "SYSTEMcontext+CONTEXT_INSTANTIATOR"
    :name             "SYSTEMcontext+MAIN"
    }
   {:target_requestid :SYSTEMcontext/INSTANTIATE_REQUESTID
    :target_name      "SYSTEMcontext+INSTANTIATOR_INSTANTIATOR"
    :name             "MAIN+SIMPLE_INSTANTIATOR"
    :descriptors      {:SYSTEMcontext/INSTANTIATION_DESCRIPTORS {:SYSTEMcontext/REQUESTID_MAP {:SYSTEMcontext/ADD_DESCRIPTOR_REQUESTID [:ADD_DESCRIPTOR_OPERATIONID]
                                                                                     :SYSTEMcontext/ADD_CLASSIFIER_REQUESTID [:ADD_CLASSIFIER_OPERATIONID]
                                                                                     :SYSTEMcontext/ENTITY_REPORT_REQUESTID  [:ENTITY_REPORT_OPERATIONID]}}
                       :SYSTEMcontext/INSTANTIATION_CLASSIFIERS {:SYSTEMcontext/ENTITY_TYPE "MAIN+SIMPLE"}}
    }
   {:target_requestid :SYSTEMcontext/INSTANTIATE_REQUESTID
    :target_name      "SYSTEMcontext+CLASSIFIER_VALUE_INSTANTIATOR"
    :name             "MAIN+SIMPLE"
    }
   {:target_requestid :SYSTEMcontext/INSTANTIATE_REQUESTID
    :target_name      "MAIN+SIMPLE_INSTANTIATOR"
    :name             "MAIN+SIMPLE_1"
    }
   {:target_requestid :SYSTEMcontext/INSTANTIATE_REQUESTID
    :target_name      "MAIN+SIMPLE_INSTANTIATOR"
    :name             "MAIN+SIMPLE_2"
    }
   #_ {:target_requestid :SYSTEMcontext/ADD_CLASSIFIER_REQUESTID
    :target_name      "MAIN+SIMPLE_2"
    :classifier       :MAIN/BASIC
    :classifier-value "MAIN+SIMPLE_1"
    }
   {:target_requestid :SYSTEMcontext/INSTANTIATE_REQUESTID
    :target_name      "SYSTEMcontext+DESCRIPTOR_INSTANTIATOR"
    :name             "MAIN+DEGREE_OF_POLISH"
    }
   {:target_requestid :SYSTEMcontext/INSTANTIATE_REQUESTID
    :target_name      "SYSTEMcontext+CLASSIFIER_INSTANTIATOR"
    :name             "MAIN+BASIC"
    }
   {:target_requestid :SYSTEMcontext/INSTANTIATE_REQUESTID
    :target_name      "SYSTEMcontext+CLASSIFIER_INSTANTIATOR"
    :name             "MAIN+APPLICATION"
    }
   {:target_requestid :SYSTEMcontext/INSTANTIATE_REQUESTID
    :target_name      "SYSTEMcontext+CLASSIFIER_VALUE_INSTANTIATOR"
    :name             "MAIN+FIDDLING"
    }
   {:target_requestid :SYSTEMcontext/INSTANTIATE_REQUESTID
    :target_name      "SYSTEMcontext+FEDERATOR_INSTANTIATOR"
    :name             "MAIN+FEDERATOR_1"
    :descriptors      {:SYSTEMcontext/FEDERATION_NAMES ["MAIN+SIMPLE_1"]
                       :SYSTEMcontext/SCRIPT           [{:target_requestid :SYSTEMcontext/INSTANTIATE_REQUESTID
                                                    :target_name      "MAIN+SIMPLE_INSTANTIATOR"
                                                    :name             "MAIN+SIMPLE_3"
                                                    }
                                                   {:target_requestid :SYSTEMcontext/ADD_DESCRIPTOR_REQUESTID
                                                    :target_name      "MAIN+SIMPLE_1"
                                                    :descriptor       :MAIN/DEGREE_OF_POLISH
                                                    :descriptor-value "MIDDLING"
                                                    }
                                                   {:target_requestid :SYSTEMcontext/ADD_CLASSIFIER_REQUESTID
                                                    :target_name      "MAIN+SIMPLE_1"
                                                    :classifier       :MAIN/APPLICATION
                                                    :classifier-value "MAIN+FIDDLING"
                                                    }
                                                   {:target_requestid :SYSTEMcontext/ADD_DESCRIPTOR_REQUESTID
                                                    :target_name      "MAIN+SIMPLE_3"
                                                    :descriptor       :MAIN/DEGREE_OF_POLISH
                                                    :descriptor-value "MIDDLING"
                                                    }
                                                   {:target_requestid :SYSTEMcontext/ADD_CLASSIFIER_REQUESTID
                                                    :target_name      "MAIN+SIMPLE_3"
                                                    :classifier       :MAIN/BASIC
                                                    :classifier-value "MAIN+SIMPLE_1"
                                                    }
                                                   {:target_requestid :SYSTEMcontext/ADD_CLASSIFIER_REQUESTID
                                                    :target_name      "MAIN+SIMPLE_3"
                                                    :classifier       :MAIN/APPLICATION
                                                    :classifier-value "MAIN+FIDDLING"
                                                    }
                                                   ]}}
   {:target_requestid :SYSTEMcontext/RUN_FEDERATION_REQUESTID
      :target_name      "MAIN+FEDERATOR_1"
      }
   {:target_requestid :SYSTEMcontext/ENTITY_REPORT_REQUESTID
    :target_name      "ROOT+SYSTEMcontext"
    }
   {:target_requestid :SYSTEMcontext/ENTITY_REPORT_REQUESTID
    :target_name      "SYSTEMcontext+MAIN"
    }
   ])
