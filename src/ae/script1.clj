(ns ae.script1)

(def script1
  [{:target_requestid :SYSTEMcontext/REGISTER_ENTITYrequestid
    :target_name      "ROOT+SYSTEMcontext"
    :name             "SYSTEMcontext+INSTANTIATOR_INSTANTIATOR"
    :descriptors      {:SYSTEMcontext/INVARIANT                 true
                       :SYSTEMcontext/REQUESTID_MAP             {:SYSTEMcontext/INSTANTIATErequestid   [:INSTANTIATEoperationid]
                                                            :SYSTEMcontext/ENTITY_REPORTrequestid [:ENTITY_REPORToperationid]}
                       :SYSTEMcontext/INSTANTIATION_DESCRIPTORS {:SYSTEMcontext/INVARIANT     true
                                                            :SYSTEMcontext/REQUESTID_MAP {:SYSTEMcontext/INSTANTIATErequestid   [:INSTANTIATEoperationid]
                                                                                     :SYSTEMcontext/ENTITY_REPORTrequestid [:ENTITY_REPORToperationid]}}
                       :SYSTEMcontext/INSTANTIATION_CLASSIFIERS {:SYSTEMcontext/ENTITY_TYPE "SYSTEMcontext+INSTANTIATOR"}}
    :classifiers      {:SYSTEMcontext/ENTITY_TYPE "SYSTEMcontext+INSTANTIATOR"}
    }
   {:target_requestid :SYSTEMcontext/INSTANTIATErequestid
    :target_name      "SYSTEMcontext+INSTANTIATOR_INSTANTIATOR"
    :name             "SYSTEMcontext+REQUESTID_INSTANTIATOR"
    :descriptors      {:SYSTEMcontext/INSTANTIATION_DESCRIPTORS {:SYSTEMcontext/INVARIANT     true
                                                            :SYSTEMcontext/REQUESTID_MAP {:SYSTEMcontext/ENTITY_REPORTrequestid [:ENTITY_REPORToperationid]}}
                       :SYSTEMcontext/INSTANTIATION_CLASSIFIERS {:SYSTEMcontext/ENTITY_TYPE "SYSTEMcontext+REQUESTID"}}
    }
   {:target_requestid :SYSTEMcontext/INSTANTIATErequestid
    :target_name      "SYSTEMcontext+REQUESTID_INSTANTIATOR"
    :name             "SYSTEMcontext+ACQUIRErequestid"
    }
   {:target_requestid :SYSTEMcontext/INSTANTIATErequestid
    :target_name      "SYSTEMcontext+REQUESTID_INSTANTIATOR"
    :name             "SYSTEMcontext+ADD_CLASSIFIERrequestid"
    }
   {:target_requestid :SYSTEMcontext/INSTANTIATErequestid
    :target_name      "SYSTEMcontext+REQUESTID_INSTANTIATOR"
    :name             "SYSTEMcontext+ADD_DESCRIPTORrequestid"
    }
   {:target_requestid :SYSTEMcontext/INSTANTIATErequestid
    :target_name      "SYSTEMcontext+REQUESTID_INSTANTIATOR"
    :name             "SYSTEMcontext+INSTANTIATErequestid"
    :descriptors      {:SYSTEMcontext/READ_ONLY true}
    }
   {:target_requestid :SYSTEMcontext/INSTANTIATErequestid
    :target_name      "SYSTEMcontext+REQUESTID_INSTANTIATOR"
    :name             "SYSTEMcontext+REGISTER_CLASSIFIERrequestid"
    }
   {:target_requestid :SYSTEMcontext/INSTANTIATErequestid
    :target_name      "SYSTEMcontext+REQUESTID_INSTANTIATOR"
    :name             "SYSTEMcontext+REGISTER_ENTITYrequestid"
    }
   {:target_requestid :SYSTEMcontext/INSTANTIATErequestid
    :target_name      "SYSTEMcontext+REQUESTID_INSTANTIATOR"
    :name             "SYSTEMcontext+RELEASErequestid"
    }
   {:target_requestid :SYSTEMcontext/INSTANTIATErequestid
    :target_name      "SYSTEMcontext+REQUESTID_INSTANTIATOR"
    :name             "SYSTEMcontext+ROUTErequestid"
    }
   {:target_requestid :SYSTEMcontext/INSTANTIATErequestid
    :target_name      "SYSTEMcontext+REQUESTID_INSTANTIATOR"
    :name             "SYSTEMcontext+RUN_FEDERATIONrequestid"
    }
   {:target_requestid :SYSTEMcontext/INSTANTIATErequestid
    :target_name      "SYSTEMcontext+REQUESTID_INSTANTIATOR"
    :name             "SYSTEMcontext+CONTEXT_REPORTrequestid"
    :descriptors      {:SYSTEMcontext/READ_ONLY true}
    }
   {:target_requestid :SYSTEMcontext/INSTANTIATErequestid
    :target_name      "SYSTEMcontext+INSTANTIATOR_INSTANTIATOR"
    :name             "SYSTEMcontext+DESCRIPTOR_INSTANTIATOR"
    :descriptors      {:SYSTEMcontext/INSTANTIATION_DESCRIPTORS {:SYSTEMcontext/INVARIANT true
                                                            :SYSTEMcontext/REQUESTID_MAP {:SYSTEMcontext/ENTITY_REPORTrequestid [:ENTITY_REPORToperationid]}}
                       :SYSTEMcontext/INSTANTIATION_CLASSIFIERS {:SYSTEMcontext/ENTITY_TYPE "SYSTEMcontext+DESCRIPTOR"}}
    }
   {:target_requestid :SYSTEMcontext/INSTANTIATErequestid
    :target_name      "SYSTEMcontext+DESCRIPTOR_INSTANTIATOR"
    :name             "SYSTEMcontext+READ_ONLY"
    }
   {:target_requestid :SYSTEMcontext/INSTANTIATErequestid
    :target_name      "SYSTEMcontext+DESCRIPTOR_INSTANTIATOR"
    :name             "SYSTEMcontext+REQUESTID_MAP"
    }
   {:target_requestid :SYSTEMcontext/INSTANTIATErequestid
    :target_name      "SYSTEMcontext+DESCRIPTOR_INSTANTIATOR"
    :name             "SYSTEMcontext+INVARIANT"
    }
   {:target_requestid :SYSTEMcontext/INSTANTIATErequestid
    :target_name      "SYSTEMcontext+DESCRIPTOR_INSTANTIATOR"
    :name             "SYSTEMcontext+INSTANTIATION_DESCRIPTORS"
    }
   {:target_requestid :SYSTEMcontext/INSTANTIATErequestid
    :target_name      "SYSTEMcontext+DESCRIPTOR_INSTANTIATOR"
    :name             "SYSTEMcontext+INSTANTIATION_CLASSIFIERS"
    }
   {:target_requestid :SYSTEMcontext/INSTANTIATErequestid
    :target_name      "SYSTEMcontext+INSTANTIATOR_INSTANTIATOR"
    :name             "SYSTEMcontext+CLASSIFIER_INSTANTIATOR"
    :descriptors      {:SYSTEMcontext/INSTANTIATION_DESCRIPTORS {:SYSTEMcontext/INVARIANT true
                                                            :SYSTEMcontext/REQUESTID_MAP {:SYSTEMcontext/ENTITY_REPORTrequestid [:ENTITY_REPORToperationid]}}
                       :SYSTEMcontext/INSTANTIATION_CLASSIFIERS {:SYSTEMcontext/ENTITY_TYPE "SYSTEMcontext+CLASSIFIER"}}
    }
   {:target_requestid :SYSTEMcontext/INSTANTIATErequestid
    :target_name      "SYSTEMcontext+CLASSIFIER_INSTANTIATOR"
    :name             "SYSTEMcontext+ENTITY_TYPE"
    }
   {:target_requestid :SYSTEMcontext/INSTANTIATErequestid
    :target_name      "SYSTEMcontext+INSTANTIATOR_INSTANTIATOR"
    :name             "SYSTEMcontext+CLASSIFIER_VALUE_INSTANTIATOR"
    :descriptors      {:SYSTEMcontext/INSTANTIATION_DESCRIPTORS {:SYSTEMcontext/INVARIANT     true
                                                            :SYSTEMcontext/REQUESTID_MAP {:SYSTEMcontext/ENTITY_REPORTrequestid [:ENTITY_REPORToperationid]}}
                       :SYSTEMcontext/INSTANTIATION_CLASSIFIERS {:SYSTEMcontext/ENTITY_TYPE "SYSTEMcontext+CLASSIFIER_VALUE"}}
    }
   {:target_requestid :SYSTEMcontext/INSTANTIATErequestid
    :target_name      "SYSTEMcontext+CLASSIFIER_VALUE_INSTANTIATOR"
    :name             "SYSTEMcontext+REQUESTID"
    }
   {:target_requestid :SYSTEMcontext/INSTANTIATErequestid
    :target_name      "SYSTEMcontext+CLASSIFIER_VALUE_INSTANTIATOR"
    :name             "SYSTEMcontext+DESCRIPTOR"
    }
   {:target_requestid :SYSTEMcontext/INSTANTIATErequestid
    :target_name      "SYSTEMcontext+CLASSIFIER_VALUE_INSTANTIATOR"
    :name             "SYSTEMcontext+CLASSIFIER_VALUE"
    }
   {:target_requestid :SYSTEMcontext/INSTANTIATErequestid
    :target_name      "SYSTEMcontext+CLASSIFIER_VALUE_INSTANTIATOR"
    :name             "SYSTEMcontext+CONTEXT"
    }
   {:target_requestid :SYSTEMcontext/INSTANTIATErequestid
    :target_name      "SYSTEMcontext+CLASSIFIER_VALUE_INSTANTIATOR"
    :name             "SYSTEMcontext+FEDERATOR"
    }
   {:target_requestid :SYSTEMcontext/INSTANTIATErequestid
    :target_name      "SYSTEMcontext+CLASSIFIER_VALUE_INSTANTIATOR"
    :name             "SYSTEMcontext+FEDERATION_CONTEXT"
    }
   {:target_requestid :SYSTEMcontext/INSTANTIATErequestid
    :target_name      "SYSTEMcontext+INSTANTIATOR_INSTANTIATOR"
    :name             "SYSTEMcontext+CONTEXT_INSTANTIATOR"
    :descriptors      {:SYSTEMcontext/INSTANTIATION_DESCRIPTORS {:SYSTEMcontext/REQUESTID_MAP {:SYSTEMcontext/REGISTER_ENTITYrequestid     [:REGISTER_ENTITYoperationid]
                                                                                     :SYSTEMcontext/ROUTErequestid               [:ROUTEoperationid]
                                                                                     :SYSTEMcontext/REGISTER_CLASSIFIERrequestid [:REGISTER_CLASSIFIERoperationid]
                                                                                     :SYSTEMcontext/ENTITY_REPORTrequestid       [:CONTEXT_REPORToperationid]}}
                       :SYSTEMcontext/INSTANTIATION_CLASSIFIERS {:SYSTEMcontext/ENTITY_TYPE "SYSTEMcontext+CONTEXT"}}
    }
   {:target_requestid :SYSTEMcontext/INSTANTIATErequestid
    :target_name      "SYSTEMcontext+INSTANTIATOR_INSTANTIATOR"
    :name             "SYSTEMcontext+FEDERATOR_INSTANTIATOR"
    :descriptors      {:SYSTEMcontext/INSTANTIATION_DESCRIPTORS {:SYSTEMcontext/REQUESTID_MAP {:SYSTEMcontext/RUN_FEDERATIONrequestid [:RUN_FEDERATIONoperationid]
                                                                                     :SYSTEMcontext/ENTITY_REPORTrequestid  [:ENTITY_REPORToperationid]}}
                       :SYSTEMcontext/INSTANTIATION_CLASSIFIERS {:SYSTEMcontext/ENTITY_TYPE "SYSTEMcontext+FEDERATOR"}}
    }
   {:target_requestid :SYSTEMcontext/INSTANTIATErequestid
    :target_name      "SYSTEMcontext+INSTANTIATOR_INSTANTIATOR"
    :name             "SYSTEMcontext+FEDERATION_CONTEXT_INSTANTIATOR"
    :descriptors      {:SYSTEMcontext/INSTANTIATION_DESCRIPTORS {:SYSTEMcontext/REQUESTID_MAP {:SYSTEMcontext/ACQUIRErequestid [:FEDERATION_ACQUIREoperationid]
                                                                                     :SYSTEMcontext/RELEASErequestid [:FEDERATION_RELEASEoperationid]}}
                       :SYSTEMcontext/INSTANTIATION_CLASSIFIERS {:SYSTEMcontext/ENTITY_TYPE "SYSTEMcontext+FEDERATION_CONTEXT"}}
    }
   {:target_requestid :SYSTEMcontext/INSTANTIATErequestid
    :target_name      "SYSTEMcontext+CONTEXT_INSTANTIATOR"
    :name             "SYSTEMcontext+SYSTEM_TESTcontext"
    }
   {:target_requestid :SYSTEMcontext/INSTANTIATErequestid
    :target_name      "SYSTEMcontext+INSTANTIATOR_INSTANTIATOR"
    :name             "SYSTEM_TESTcontext+SIMPLE_INSTANTIATOR"
    :descriptors      {:SYSTEMcontext/INSTANTIATION_DESCRIPTORS {:SYSTEMcontext/REQUESTID_MAP {:SYSTEMcontext/ADD_DESCRIPTORrequestid [:ADD_DESCRIPTORoperationid]
                                                                                     :SYSTEMcontext/ADD_CLASSIFIERrequestid [:ADD_CLASSIFIERoperationid]
                                                                                     :SYSTEMcontext/ENTITY_REPORTrequestid  [:ENTITY_REPORToperationid]}}
                       :SYSTEMcontext/INSTANTIATION_CLASSIFIERS {:SYSTEMcontext/ENTITY_TYPE "SYSTEM_TESTcontext+SIMPLE"}}
    }
   {:target_requestid :SYSTEMcontext/INSTANTIATErequestid
    :target_name      "SYSTEMcontext+CLASSIFIER_VALUE_INSTANTIATOR"
    :name             "SYSTEM_TESTcontext+SIMPLE"
    }
   {:target_requestid :SYSTEMcontext/INSTANTIATErequestid
    :target_name      "SYSTEM_TESTcontext+SIMPLE_INSTANTIATOR"
    :name             "SYSTEM_TESTcontext+SIMPLE_1"
    }
   {:target_requestid :SYSTEMcontext/INSTANTIATErequestid
    :target_name      "SYSTEM_TESTcontext+SIMPLE_INSTANTIATOR"
    :name             "SYSTEM_TESTcontext+SIMPLE_2"
    }
   #_ {:target_requestid :SYSTEMcontext/ADD_CLASSIFIERrequestid
    :target_name      "SYSTEM_TESTcontext+SIMPLE_2"
    :classifier       :SYSTEM_TESTcontext/BASIC
    :classifier-value "SYSTEM_TESTcontext+SIMPLE_1"
    }
   {:target_requestid :SYSTEMcontext/INSTANTIATErequestid
    :target_name      "SYSTEMcontext+DESCRIPTOR_INSTANTIATOR"
    :name             "SYSTEM_TESTcontext+DEGREE_OF_POLISH"
    }
   {:target_requestid :SYSTEMcontext/INSTANTIATErequestid
    :target_name      "SYSTEMcontext+CLASSIFIER_INSTANTIATOR"
    :name             "SYSTEM_TESTcontext+BASIC"
    }
   {:target_requestid :SYSTEMcontext/INSTANTIATErequestid
    :target_name      "SYSTEMcontext+CLASSIFIER_INSTANTIATOR"
    :name             "SYSTEM_TESTcontext+APPLICATION"
    }
   {:target_requestid :SYSTEMcontext/INSTANTIATErequestid
    :target_name      "SYSTEMcontext+CLASSIFIER_VALUE_INSTANTIATOR"
    :name             "SYSTEM_TESTcontext+FIDDLING"
    }
   {:target_requestid :SYSTEMcontext/INSTANTIATErequestid
    :target_name      "SYSTEMcontext+FEDERATOR_INSTANTIATOR"
    :name             "SYSTEM_TESTcontext+FEDERATOR_1"
    :descriptors      {:SYSTEMcontext/FEDERATION_NAMES ["SYSTEM_TESTcontext+SIMPLE_1"]
                       :SYSTEMcontext/SCRIPT           [{:target_requestid :SYSTEMcontext/INSTANTIATErequestid
                                                    :target_name      "SYSTEM_TESTcontext+SIMPLE_INSTANTIATOR"
                                                    :name             "SYSTEM_TESTcontext+SIMPLE_3"
                                                    }
                                                   {:target_requestid :SYSTEMcontext/ADD_DESCRIPTORrequestid
                                                    :target_name      "SYSTEM_TESTcontext+SIMPLE_1"
                                                    :descriptor       :SYSTEM_TESTcontext/DEGREE_OF_POLISH
                                                    :descriptor-value "MIDDLING"
                                                    }
                                                   {:target_requestid :SYSTEMcontext/ADD_CLASSIFIERrequestid
                                                    :target_name      "SYSTEM_TESTcontext+SIMPLE_1"
                                                    :classifier       :SYSTEM_TESTcontext/APPLICATION
                                                    :classifier-value "SYSTEM_TESTcontext+FIDDLING"
                                                    }
                                                   {:target_requestid :SYSTEMcontext/ADD_DESCRIPTORrequestid
                                                    :target_name      "SYSTEM_TESTcontext+SIMPLE_3"
                                                    :descriptor       :SYSTEM_TESTcontext/DEGREE_OF_POLISH
                                                    :descriptor-value "MIDDLING"
                                                    }
                                                   {:target_requestid :SYSTEMcontext/ADD_CLASSIFIERrequestid
                                                    :target_name      "SYSTEM_TESTcontext+SIMPLE_3"
                                                    :classifier       :SYSTEM_TESTcontext/BASIC
                                                    :classifier-value "SYSTEM_TESTcontext+SIMPLE_1"
                                                    }
                                                   {:target_requestid :SYSTEMcontext/ADD_CLASSIFIERrequestid
                                                    :target_name      "SYSTEM_TESTcontext+SIMPLE_3"
                                                    :classifier       :SYSTEM_TESTcontext/APPLICATION
                                                    :classifier-value "SYSTEM_TESTcontext+FIDDLING"
                                                    }
                                                   ]}}
   {:target_requestid :SYSTEMcontext/RUN_FEDERATIONrequestid
      :target_name      "SYSTEM_TESTcontext+FEDERATOR_1"
      }
   {:target_requestid :SYSTEMcontext/ENTITY_REPORTrequestid
    :target_name      "ROOT+SYSTEMcontext"
    }
   {:target_requestid :SYSTEMcontext/ENTITY_REPORTrequestid
    :target_name      "SYSTEMcontext+SYSTEM_TESTcontext"
    }
   ])
