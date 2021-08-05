(ns ae.script1)

(def script1
  [{:target_requestid :SYSTEMcontext/REGISTER_ENTITYrequestid
    :target_name      "ROOT+SYSTEMcontext"
    :name             "SYSTEMcontext+INSTANTIATORinstantiator"
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
    :target_name      "SYSTEMcontext+INSTANTIATORinstantiator"
    :name             "SYSTEMcontext+REQUESTIDinstantiator"
    :descriptors      {:SYSTEMcontext/INSTANTIATION_DESCRIPTORS {:SYSTEMcontext/INVARIANT     true
                                                                 :SYSTEMcontext/REQUESTID_MAP {:SYSTEMcontext/ENTITY_REPORTrequestid [:ENTITY_REPORToperationid]}}
                       :SYSTEMcontext/INSTANTIATION_CLASSIFIERS {:SYSTEMcontext/ENTITY_TYPE "SYSTEMcontext+REQUESTID"}}
    }
   {:target_requestid :SYSTEMcontext/INSTANTIATErequestid
    :target_name      "SYSTEMcontext+REQUESTIDinstantiator"
    :name             "SYSTEMcontext+ACQUIRErequestid"
    }
   {:target_requestid :SYSTEMcontext/INSTANTIATErequestid
    :target_name      "SYSTEMcontext+REQUESTIDinstantiator"
    :name             "SYSTEMcontext+ADD_CLASSIFIERrequestid"
    }
   {:target_requestid :SYSTEMcontext/INSTANTIATErequestid
    :target_name      "SYSTEMcontext+REQUESTIDinstantiator"
    :name             "SYSTEMcontext+ADD_DESCRIPTORrequestid"
    }
   {:target_requestid :SYSTEMcontext/INSTANTIATErequestid
    :target_name      "SYSTEMcontext+REQUESTIDinstantiator"
    :name             "SYSTEMcontext+INSTANTIATErequestid"
    :descriptors      {:SYSTEMcontext/READ_ONLY true}
    }
   {:target_requestid :SYSTEMcontext/INSTANTIATErequestid
    :target_name      "SYSTEMcontext+REQUESTIDinstantiator"
    :name             "SYSTEMcontext+REGISTER_CLASSIFIERrequestid"
    }
   {:target_requestid :SYSTEMcontext/INSTANTIATErequestid
    :target_name      "SYSTEMcontext+REQUESTIDinstantiator"
    :name             "SYSTEMcontext+REGISTER_ENTITYrequestid"
    }
   {:target_requestid :SYSTEMcontext/INSTANTIATErequestid
    :target_name      "SYSTEMcontext+REQUESTIDinstantiator"
    :name             "SYSTEMcontext+RELEASErequestid"
    }
   {:target_requestid :SYSTEMcontext/INSTANTIATErequestid
    :target_name      "SYSTEMcontext+REQUESTIDinstantiator"
    :name             "SYSTEMcontext+ROUTErequestid"
    }
   {:target_requestid :SYSTEMcontext/INSTANTIATErequestid
    :target_name      "SYSTEMcontext+REQUESTIDinstantiator"
    :name             "SYSTEMcontext+RUN_FEDERATIONrequestid"
    }
   {:target_requestid :SYSTEMcontext/INSTANTIATErequestid
    :target_name      "SYSTEMcontext+REQUESTIDinstantiator"
    :name             "SYSTEMcontext+CONTEXT_REPORTrequestid"
    :descriptors      {:SYSTEMcontext/READ_ONLY true}
    }
   {:target_requestid :SYSTEMcontext/INSTANTIATErequestid
    :target_name      "SYSTEMcontext+INSTANTIATORinstantiator"
    :name             "SYSTEMcontext+DESCRIPTORinstantiator"
    :descriptors      {:SYSTEMcontext/INSTANTIATION_DESCRIPTORS {:SYSTEMcontext/INVARIANT     true
                                                                 :SYSTEMcontext/REQUESTID_MAP {:SYSTEMcontext/ENTITY_REPORTrequestid [:ENTITY_REPORToperationid]}}
                       :SYSTEMcontext/INSTANTIATION_CLASSIFIERS {:SYSTEMcontext/ENTITY_TYPE "SYSTEMcontext+DESCRIPTOR"}}
    }
   {:target_requestid :SYSTEMcontext/INSTANTIATErequestid
    :target_name      "SYSTEMcontext+DESCRIPTORinstantiator"
    :name             "SYSTEMcontext+READ_ONLY"
    }
   {:target_requestid :SYSTEMcontext/INSTANTIATErequestid
    :target_name      "SYSTEMcontext+DESCRIPTORinstantiator"
    :name             "SYSTEMcontext+REQUESTID_MAP"
    }
   {:target_requestid :SYSTEMcontext/INSTANTIATErequestid
    :target_name      "SYSTEMcontext+DESCRIPTORinstantiator"
    :name             "SYSTEMcontext+INVARIANT"
    }
   {:target_requestid :SYSTEMcontext/INSTANTIATErequestid
    :target_name      "SYSTEMcontext+DESCRIPTORinstantiator"
    :name             "SYSTEMcontext+INSTANTIATION_DESCRIPTORS"
    }
   {:target_requestid :SYSTEMcontext/INSTANTIATErequestid
    :target_name      "SYSTEMcontext+DESCRIPTORinstantiator"
    :name             "SYSTEMcontext+INSTANTIATION_CLASSIFIERS"
    }
   {:target_requestid :SYSTEMcontext/INSTANTIATErequestid
    :target_name      "SYSTEMcontext+INSTANTIATORinstantiator"
    :name             "SYSTEMcontext+CLASSIFIERinstantiator"
    :descriptors      {:SYSTEMcontext/INSTANTIATION_DESCRIPTORS {:SYSTEMcontext/INVARIANT     true
                                                                 :SYSTEMcontext/REQUESTID_MAP {:SYSTEMcontext/ENTITY_REPORTrequestid [:ENTITY_REPORToperationid]}}
                       :SYSTEMcontext/INSTANTIATION_CLASSIFIERS {:SYSTEMcontext/ENTITY_TYPE "SYSTEMcontext+CLASSIFIER"}}
    }
   {:target_requestid :SYSTEMcontext/INSTANTIATErequestid
    :target_name      "SYSTEMcontext+CLASSIFIERinstantiator"
    :name             "SYSTEMcontext+ENTITY_TYPE"
    }
   {:target_requestid :SYSTEMcontext/INSTANTIATErequestid
    :target_name      "SYSTEMcontext+INSTANTIATORinstantiator"
    :name             "SYSTEMcontext+CLASSIFIER_VALUEinstantiator"
    :descriptors      {:SYSTEMcontext/INSTANTIATION_DESCRIPTORS {:SYSTEMcontext/INVARIANT     true
                                                                 :SYSTEMcontext/REQUESTID_MAP {:SYSTEMcontext/ENTITY_REPORTrequestid [:ENTITY_REPORToperationid]}}
                       :SYSTEMcontext/INSTANTIATION_CLASSIFIERS {:SYSTEMcontext/ENTITY_TYPE "SYSTEMcontext+CLASSIFIER_VALUEclassifier_value"}}
    }
   {:target_requestid :SYSTEMcontext/INSTANTIATErequestid
    :target_name      "SYSTEMcontext+CLASSIFIER_VALUEinstantiator"
    :name             "SYSTEMcontext+REQUESTID"
    }
   {:target_requestid :SYSTEMcontext/INSTANTIATErequestid
    :target_name      "SYSTEMcontext+CLASSIFIER_VALUEinstantiator"
    :name             "SYSTEMcontext+CLASSIFIERclassifier_value"
    }
   {:target_requestid :SYSTEMcontext/INSTANTIATErequestid
    :target_name      "SYSTEMcontext+CLASSIFIER_VALUEinstantiator"
    :name             "SYSTEMcontext+DESCRIPTORclassifier_value"
    }
   {:target_requestid :SYSTEMcontext/INSTANTIATErequestid
    :target_name      "SYSTEMcontext+CLASSIFIER_VALUEinstantiator"
    :name             "SYSTEMcontext+CLASSIFIER_VALUEclassifier_value"
    }
   {:target_requestid :SYSTEMcontext/INSTANTIATErequestid
    :target_name      "SYSTEMcontext+CLASSIFIER_VALUEinstantiator"
    :name             "SYSTEMcontext+CONTEXTclassifier_value"
    }
   {:target_requestid :SYSTEMcontext/INSTANTIATErequestid
    :target_name      "SYSTEMcontext+CLASSIFIER_VALUEinstantiator"
    :name             "SYSTEMcontext+FEDERATORclassifier_value"
    }
   {:target_requestid :SYSTEMcontext/INSTANTIATErequestid
    :target_name      "SYSTEMcontext+CLASSIFIER_VALUEinstantiator"
    :name             "SYSTEMcontext+FEDERATION_CONTEXTclassifier_value"
    }
   {:target_requestid :SYSTEMcontext/INSTANTIATErequestid
    :target_name      "SYSTEMcontext+INSTANTIATORinstantiator"
    :name             "SYSTEMcontext+CONTEXTinstantiator"
    :descriptors      {:SYSTEMcontext/INSTANTIATION_DESCRIPTORS {:SYSTEMcontext/REQUESTID_MAP {:SYSTEMcontext/REGISTER_ENTITYrequestid     [:REGISTER_ENTITYoperationid]
                                                                                               :SYSTEMcontext/ROUTErequestid               [:ROUTEoperationid]
                                                                                               :SYSTEMcontext/REGISTER_CLASSIFIERrequestid [:REGISTER_CLASSIFIERoperationid]
                                                                                               :SYSTEMcontext/ENTITY_REPORTrequestid       [:CONTEXT_REPORToperationid]}}
                       :SYSTEMcontext/INSTANTIATION_CLASSIFIERS {:SYSTEMcontext/ENTITY_TYPE "SYSTEMcontext+CONTEXTclassifier_value"}}
    }
   {:target_requestid :SYSTEMcontext/INSTANTIATErequestid
    :target_name      "SYSTEMcontext+INSTANTIATORinstantiator"
    :name             "SYSTEMcontext+FEDERATORinstantiator"
    :descriptors      {:SYSTEMcontext/INSTANTIATION_DESCRIPTORS {:SYSTEMcontext/REQUESTID_MAP {:SYSTEMcontext/RUN_FEDERATIONrequestid [:RUN_FEDERATIONoperationid]
                                                                                               :SYSTEMcontext/ENTITY_REPORTrequestid  [:ENTITY_REPORToperationid]}}
                       :SYSTEMcontext/INSTANTIATION_CLASSIFIERS {:SYSTEMcontext/ENTITY_TYPE "SYSTEMcontext+FEDERATOR"}}
    }
   {:target_requestid :SYSTEMcontext/INSTANTIATErequestid
    :target_name      "SYSTEMcontext+INSTANTIATORinstantiator"
    :name             "SYSTEMcontext+FEDERATION_CONTEXTinstantiator"
    :descriptors      {:SYSTEMcontext/INSTANTIATION_DESCRIPTORS {:SYSTEMcontext/REQUESTID_MAP {:SYSTEMcontext/ACQUIRErequestid [:FEDERATION_ACQUIREoperationid]
                                                                                               :SYSTEMcontext/RELEASErequestid [:FEDERATION_RELEASEoperationid]}}
                       :SYSTEMcontext/INSTANTIATION_CLASSIFIERS {:SYSTEMcontext/ENTITY_TYPE "SYSTEMcontext+FEDERATION_CONTEXT"}}
    }
   {:target_requestid :SYSTEMcontext/INSTANTIATErequestid
    :target_name      "SYSTEMcontext+CONTEXTinstantiator"
    :name             "SYSTEMcontext+SYSTEM_TESTcontext"
    }
   {:target_requestid :SYSTEMcontext/INSTANTIATErequestid
    :target_name      "SYSTEMcontext+INSTANTIATORinstantiator"
    :name             "SYSTEM_TESTcontext+SIMPLEinstantiator"
    :descriptors      {:SYSTEMcontext/INSTANTIATION_DESCRIPTORS {:SYSTEMcontext/REQUESTID_MAP {:SYSTEMcontext/ADD_DESCRIPTORrequestid [:ADD_DESCRIPTORoperationid]
                                                                                               :SYSTEMcontext/ADD_CLASSIFIERrequestid [:ADD_CLASSIFIERoperationid]
                                                                                               :SYSTEMcontext/ENTITY_REPORTrequestid  [:ENTITY_REPORToperationid]}}
                       :SYSTEMcontext/INSTANTIATION_CLASSIFIERS {:SYSTEMcontext/ENTITY_TYPE "SYSTEM_TESTcontext+SIMPLE"}}
    }
   {:target_requestid :SYSTEMcontext/INSTANTIATErequestid
    :target_name      "SYSTEMcontext+CLASSIFIER_VALUEinstantiator"
    :name             "SYSTEM_TESTcontext+SIMPLE"
    }
   {:target_requestid :SYSTEMcontext/INSTANTIATErequestid
    :target_name      "SYSTEM_TESTcontext+SIMPLEinstantiator"
    :name             "SYSTEM_TESTcontext+SIMPLE_1"
    }
   {:target_requestid :SYSTEMcontext/INSTANTIATErequestid
    :target_name      "SYSTEM_TESTcontext+SIMPLEinstantiator"
    :name             "SYSTEM_TESTcontext+SIMPLE_2"
    }
   #_{:target_requestid :SYSTEMcontext/ADD_CLASSIFIERrequestid
      :target_name      "SYSTEM_TESTcontext+SIMPLE_2"
      :classifier       :SYSTEM_TESTcontext/BASIC
      :classifier-value "SYSTEM_TESTcontext+SIMPLE_1"
      }
   {:target_requestid :SYSTEMcontext/INSTANTIATErequestid
    :target_name      "SYSTEMcontext+DESCRIPTORinstantiator"
    :name             "SYSTEM_TESTcontext+DEGREE_OF_POLISHdescriptor"
    }
   {:target_requestid :SYSTEMcontext/INSTANTIATErequestid
    :target_name      "SYSTEMcontext+CLASSIFIERinstantiator"
    :name             "SYSTEM_TESTcontext+BASIC"
    }
   {:target_requestid :SYSTEMcontext/INSTANTIATErequestid
    :target_name      "SYSTEMcontext+CLASSIFIERinstantiator"
    :name             "SYSTEM_TESTcontext+APPLICATION"
    }
   {:target_requestid :SYSTEMcontext/INSTANTIATErequestid
    :target_name      "SYSTEMcontext+CLASSIFIER_VALUEinstantiator"
    :name             "SYSTEM_TESTcontext+FIDDLING"
    }
   {:target_requestid :SYSTEMcontext/INSTANTIATErequestid
    :target_name      "SYSTEMcontext+FEDERATORinstantiator"
    :name             "SYSTEM_TESTcontext+FEDERATOR_1"
    :descriptors      {:SYSTEMcontext/FEDERATION_NAMES ["SYSTEM_TESTcontext+SIMPLE_1"]
                       :SYSTEMcontext/SCRIPT           [{:target_requestid :SYSTEMcontext/INSTANTIATErequestid
                                                         :target_name      "SYSTEM_TESTcontext+SIMPLEinstantiator"
                                                         :name             "SYSTEM_TESTcontext+SIMPLE_3"
                                                         }
                                                        {:target_requestid :SYSTEMcontext/ADD_DESCRIPTORrequestid
                                                         :target_name      "SYSTEM_TESTcontext+SIMPLE_1"
                                                         :descriptor       :SYSTEM_TESTcontext/DEGREE_OF_POLISHdescriptor
                                                         :descriptor-value "MIDDLING"
                                                         }
                                                        {:target_requestid :SYSTEMcontext/ADD_CLASSIFIERrequestid
                                                         :target_name      "SYSTEM_TESTcontext+SIMPLE_1"
                                                         :classifier       :SYSTEM_TESTcontext/APPLICATION
                                                         :classifier-value "SYSTEM_TESTcontext+FIDDLING"
                                                         }
                                                        {:target_requestid :SYSTEMcontext/ADD_DESCRIPTORrequestid
                                                         :target_name      "SYSTEM_TESTcontext+SIMPLE_3"
                                                         :descriptor       :SYSTEM_TESTcontext/DEGREE_OF_POLISHdescriptor
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
