(ns ae.script1)

(def script1
  [{:target_requestid :SYSTEMcontext/REGISTER_ENTITYrequestid
    :target_name      "ROOT+SYSTEMcontext"
    :name             "SYSTEMcontext+INSTANTIATORinstantiator"
    :descriptors      {:SYSTEMcontext/INVARIANTdescriptor                 true
                       :SYSTEMcontext/REQUESTID_MAP                       {:SYSTEMcontext/INSTANTIATErequestid   [:INSTANTIATEoperationid]
                                                                           :SYSTEMcontext/ENTITY_REPORTrequestid [:ENTITY_REPORToperationid]}
                       :SYSTEMcontext/INSTANTIATION_DESCRIPTORSdescriptor {:SYSTEMcontext/INVARIANTdescriptor true
                                                                           :SYSTEMcontext/REQUESTID_MAP       {:SYSTEMcontext/INSTANTIATErequestid   [:INSTANTIATEoperationid]
                                                                                                               :SYSTEMcontext/ENTITY_REPORTrequestid [:ENTITY_REPORToperationid]}}
                       :SYSTEMcontext/INSTANTIATION_CLASSIFIERSdescriptor {:SYSTEMcontext/ENTITY_TYPEclassifier "SYSTEMcontext+INSTANTIATORclassifier_value"}}
    :classifiers      {:SYSTEMcontext/ENTITY_TYPEclassifier "SYSTEMcontext+INSTANTIATORclassifier_value"}
    }
   {:target_requestid :SYSTEMcontext/INSTANTIATErequestid
    :target_name      "SYSTEMcontext+INSTANTIATORinstantiator"
    :name             "SYSTEMcontext+REQUESTIDinstantiator"
    :descriptors      {:SYSTEMcontext/INSTANTIATION_DESCRIPTORSdescriptor {:SYSTEMcontext/INVARIANTdescriptor true
                                                                           :SYSTEMcontext/REQUESTID_MAP       {:SYSTEMcontext/ENTITY_REPORTrequestid [:ENTITY_REPORToperationid]}}
                       :SYSTEMcontext/INSTANTIATION_CLASSIFIERSdescriptor {:SYSTEMcontext/ENTITY_TYPEclassifier "SYSTEMcontext+REQUESTIDclassifier_value"}}
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
    :descriptors      {:SYSTEMcontext/READ_ONLYdescriptor true}
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
    :descriptors      {:SYSTEMcontext/READ_ONLYdescriptor true}
    }
   {:target_requestid :SYSTEMcontext/INSTANTIATErequestid
    :target_name      "SYSTEMcontext+INSTANTIATORinstantiator"
    :name             "SYSTEMcontext+DESCRIPTORinstantiator"
    :descriptors      {:SYSTEMcontext/INSTANTIATION_DESCRIPTORSdescriptor {:SYSTEMcontext/INVARIANTdescriptor true
                                                                           :SYSTEMcontext/REQUESTID_MAP       {:SYSTEMcontext/ENTITY_REPORTrequestid [:ENTITY_REPORToperationid]}}
                       :SYSTEMcontext/INSTANTIATION_CLASSIFIERSdescriptor {:SYSTEMcontext/ENTITY_TYPEclassifier "SYSTEMcontext+DESCRIPTORclassifier_value"}}
    }
   {:target_requestid :SYSTEMcontext/INSTANTIATErequestid
    :target_name      "SYSTEMcontext+DESCRIPTORinstantiator"
    :name             "SYSTEMcontext+READ_ONLYdescriptor"
    }
   {:target_requestid :SYSTEMcontext/INSTANTIATErequestid
    :target_name      "SYSTEMcontext+DESCRIPTORinstantiator"
    :name             "SYSTEMcontext+REQUESTID_MAP"
    }
   {:target_requestid :SYSTEMcontext/INSTANTIATErequestid
    :target_name      "SYSTEMcontext+DESCRIPTORinstantiator"
    :name             "SYSTEMcontext+INVARIANTdescriptor"
    }
   {:target_requestid :SYSTEMcontext/INSTANTIATErequestid
    :target_name      "SYSTEMcontext+DESCRIPTORinstantiator"
    :name             "SYSTEMcontext+INSTANTIATION_DESCRIPTORSdescriptor"
    }
   {:target_requestid :SYSTEMcontext/INSTANTIATErequestid
    :target_name      "SYSTEMcontext+DESCRIPTORinstantiator"
    :name             "SYSTEMcontext+INSTANTIATION_CLASSIFIERSdescriptor"
    }
   {:target_requestid :SYSTEMcontext/INSTANTIATErequestid
    :target_name      "SYSTEMcontext+INSTANTIATORinstantiator"
    :name             "SYSTEMcontext+CLASSIFIERinstantiator"
    :descriptors      {:SYSTEMcontext/INSTANTIATION_DESCRIPTORSdescriptor {:SYSTEMcontext/INVARIANTdescriptor true
                                                                           :SYSTEMcontext/REQUESTID_MAP       {:SYSTEMcontext/ENTITY_REPORTrequestid [:ENTITY_REPORToperationid]}}
                       :SYSTEMcontext/INSTANTIATION_CLASSIFIERSdescriptor {:SYSTEMcontext/ENTITY_TYPEclassifier "SYSTEMcontext+CLASSIFIERclassifier_value"}}
    }
   {:target_requestid :SYSTEMcontext/INSTANTIATErequestid
    :target_name      "SYSTEMcontext+INSTANTIATORinstantiator"
    :name             "SYSTEMcontext+RELATIONinstantiator"
    :descriptors      {:SYSTEMcontext/INSTANTIATION_DESCRIPTORSdescriptor {:SYSTEMcontext/INVARIANTdescriptor true
                                                                           :SYSTEMcontext/REQUESTID_MAP       {:SYSTEMcontext/ENTITY_REPORTrequestid [:ENTITY_REPORToperationid]}}
                       :SYSTEMcontext/INSTANTIATION_CLASSIFIERSdescriptor {:SYSTEMcontext/ENTITY_TYPEclassifier "SYSTEMcontext+CLASSIFIERclassifier_value"}}
    }
   {:target_requestid :SYSTEMcontext/INSTANTIATErequestid
    :target_name      "SYSTEMcontext+CLASSIFIERinstantiator"
    :name             "SYSTEMcontext+ENTITY_TYPEclassifier"
    }
   {:target_requestid :SYSTEMcontext/INSTANTIATErequestid
    :target_name      "SYSTEMcontext+INSTANTIATORinstantiator"
    :name             "SYSTEMcontext+CLASSIFIER_VALUEinstantiator"
    :descriptors      {:SYSTEMcontext/INSTANTIATION_DESCRIPTORSdescriptor {:SYSTEMcontext/INVARIANTdescriptor true
                                                                           :SYSTEMcontext/REQUESTID_MAP       {:SYSTEMcontext/ENTITY_REPORTrequestid [:ENTITY_REPORToperationid]}}
                       :SYSTEMcontext/INSTANTIATION_CLASSIFIERSdescriptor {:SYSTEMcontext/ENTITY_TYPEclassifier "SYSTEMcontext+CLASSIFIER_VALUEclassifier_value"}}
    }
   {:target_requestid :SYSTEMcontext/INSTANTIATErequestid
    :target_name      "SYSTEMcontext+CLASSIFIER_VALUEinstantiator"
    :name             "SYSTEMcontext+REQUESTIDclassifier_value"
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
    :target_name      "SYSTEMcontext+CLASSIFIER_VALUEinstantiator"
    :name             "SYSTEMcontext+INSTANTIATORclassifier_value"
    }
   {:target_requestid :SYSTEMcontext/INSTANTIATErequestid
    :target_name      "SYSTEMcontext+INSTANTIATORinstantiator"
    :name             "SYSTEMcontext+CONTEXTinstantiator"
    :descriptors      {:SYSTEMcontext/INSTANTIATION_DESCRIPTORSdescriptor {:SYSTEMcontext/REQUESTID_MAP {:SYSTEMcontext/REGISTER_ENTITYrequestid     [:REGISTER_ENTITYoperationid]
                                                                                                         :SYSTEMcontext/ROUTErequestid               [:ROUTEoperationid]
                                                                                                         :SYSTEMcontext/REGISTER_CLASSIFIERrequestid [:REGISTER_CLASSIFIERoperationid]
                                                                                                         :SYSTEMcontext/ENTITY_REPORTrequestid       [:CONTEXT_REPORToperationid]}}
                       :SYSTEMcontext/INSTANTIATION_CLASSIFIERSdescriptor {:SYSTEMcontext/ENTITY_TYPEclassifier "SYSTEMcontext+CONTEXTclassifier_value"}}
    }
   {:target_requestid :SYSTEMcontext/INSTANTIATErequestid
    :target_name      "SYSTEMcontext+INSTANTIATORinstantiator"
    :name             "SYSTEMcontext+FEDERATORinstantiator"
    :descriptors      {:SYSTEMcontext/INSTANTIATION_DESCRIPTORSdescriptor {:SYSTEMcontext/REQUESTID_MAP {:SYSTEMcontext/RUN_FEDERATIONrequestid [:RUN_FEDERATIONoperationid]
                                                                                                         :SYSTEMcontext/ENTITY_REPORTrequestid  [:ENTITY_REPORToperationid]}}
                       :SYSTEMcontext/INSTANTIATION_CLASSIFIERSdescriptor {:SYSTEMcontext/ENTITY_TYPEclassifier "SYSTEMcontext+FEDERATORclassifier_value"}}
    }
   {:target_requestid :SYSTEMcontext/INSTANTIATErequestid
    :target_name      "SYSTEMcontext+INSTANTIATORinstantiator"
    :name             "SYSTEMcontext+FEDERATION_CONTEXTinstantiator"
    :descriptors      {:SYSTEMcontext/INSTANTIATION_DESCRIPTORSdescriptor {:SYSTEMcontext/REQUESTID_MAP {:SYSTEMcontext/ACQUIRErequestid [:FEDERATION_ACQUIREoperationid]
                                                                                                         :SYSTEMcontext/RELEASErequestid [:FEDERATION_RELEASEoperationid]}}
                       :SYSTEMcontext/INSTANTIATION_CLASSIFIERSdescriptor {:SYSTEMcontext/ENTITY_TYPEclassifier "SYSTEMcontext+FEDERATION_CONTEXTclassifier_value"}}
    }
   {:target_requestid :SYSTEMcontext/INSTANTIATErequestid
    :target_name      "SYSTEMcontext+CONTEXTinstantiator"
    :name             "SYSTEMcontext+SYSTEM_TESTcontext"
    }
   {:target_requestid :SYSTEMcontext/INSTANTIATErequestid
    :target_name      "SYSTEMcontext+INSTANTIATORinstantiator"
    :name             "SYSTEM_TESTcontext+SIMPLEinstantiator"
    :descriptors      {:SYSTEMcontext/INSTANTIATION_DESCRIPTORSdescriptor {:SYSTEMcontext/REQUESTID_MAP {:SYSTEMcontext/ADD_DESCRIPTORrequestid [:ADD_DESCRIPTORoperationid]
                                                                                                         :SYSTEMcontext/ADD_CLASSIFIERrequestid [:ADD_CLASSIFIERoperationid]
                                                                                                         :SYSTEMcontext/ENTITY_REPORTrequestid  [:ENTITY_REPORToperationid]}}
                       :SYSTEMcontext/INSTANTIATION_CLASSIFIERSdescriptor {:SYSTEMcontext/ENTITY_TYPEclassifier "SYSTEM_TESTcontext+SIMPLEclassifier_value"}}
    }
   {:target_requestid :SYSTEMcontext/INSTANTIATErequestid
    :target_name      "SYSTEMcontext+CLASSIFIER_VALUEinstantiator"
    :name             "SYSTEM_TESTcontext+SIMPLEclassifier_value"
    }
   {:target_requestid :SYSTEMcontext/INSTANTIATErequestid
    :target_name      "SYSTEM_TESTcontext+SIMPLEinstantiator"
    :name             "SYSTEM_TESTcontext+ALPHAsimple"
    }
   {:target_requestid :SYSTEMcontext/INSTANTIATErequestid
    :target_name      "SYSTEM_TESTcontext+SIMPLEinstantiator"
    :name             "SYSTEM_TESTcontext+BETAsimple"
    }
   {:target_requestid :SYSTEMcontext/INSTANTIATErequestid
    :target_name      "SYSTEMcontext+RELATIONinstantiator"
    :name             "SYSTEM_TESTcontext+BASICrelation"
    }
   {:target_requestid :SYSTEMcontext/INSTANTIATErequestid
    :target_name      "SYSTEMcontext+DESCRIPTORinstantiator"
    :name             "SYSTEM_TESTcontext+DEGREE_OF_POLISHdescriptor"
    }
   {:target_requestid :SYSTEMcontext/INSTANTIATErequestid
    :target_name      "SYSTEMcontext+CLASSIFIERinstantiator"
    :name             "SYSTEM_TESTcontext+APPLICATIONclassifier"
    }
   {:target_requestid :SYSTEMcontext/INSTANTIATErequestid
    :target_name      "SYSTEMcontext+CLASSIFIER_VALUEinstantiator"
    :name             "SYSTEM_TESTcontext+FIDDLINGclassifier_value"
    }
   {:target_requestid :SYSTEMcontext/INSTANTIATErequestid
    :target_name      "SYSTEMcontext+FEDERATORinstantiator"
    :name             "SYSTEM_TESTcontext+TEST_Afederator"
    :descriptors      {:SYSTEMcontext/FEDERATION_NAMES ["SYSTEM_TESTcontext+ALPHAsimple"]
                       :SYSTEMcontext/SCRIPT           [{:target_requestid :SYSTEMcontext/INSTANTIATErequestid
                                                         :target_name      "SYSTEM_TESTcontext+SIMPLEinstantiator"
                                                         :name             "SYSTEM_TESTcontext+GAMMAsimple"
                                                         }
                                                        {:target_requestid :SYSTEMcontext/ADD_DESCRIPTORrequestid
                                                         :target_name      "SYSTEM_TESTcontext+ALPHAsimple"
                                                         :descriptor       :SYSTEM_TESTcontext/DEGREE_OF_POLISHdescriptor
                                                         :descriptor-value "MIDDLING"
                                                         }
                                                        {:target_requestid :SYSTEMcontext/ADD_CLASSIFIERrequestid
                                                         :target_name      "SYSTEM_TESTcontext+ALPHAsimple"
                                                         :classifier       :SYSTEM_TESTcontext/APPLICATIONclassifier
                                                         :classifier-value "SYSTEM_TESTcontext+FIDDLINGclassifier_value"
                                                         }
                                                        {:target_requestid :SYSTEMcontext/ADD_DESCRIPTORrequestid
                                                         :target_name      "SYSTEM_TESTcontext+GAMMAsimple"
                                                         :descriptor       :SYSTEM_TESTcontext/DEGREE_OF_POLISHdescriptor
                                                         :descriptor-value "MIDDLING"
                                                         }
                                                        {:target_requestid :SYSTEMcontext/ADD_CLASSIFIERrequestid
                                                         :target_name      "SYSTEM_TESTcontext+GAMMAsimple"
                                                         :classifier       :SYSTEM_TESTcontext/BASICrelation
                                                         :classifier-value "SYSTEM_TESTcontext+ALPHAsimple"
                                                         }
                                                        {:target_requestid :SYSTEMcontext/ADD_CLASSIFIERrequestid
                                                         :target_name      "SYSTEM_TESTcontext+GAMMAsimple"
                                                         :classifier       :SYSTEM_TESTcontext/APPLICATIONclassifier
                                                         :classifier-value "SYSTEM_TESTcontext+FIDDLINGclassifier_value"
                                                         }
                                                        ]}}
   {:target_requestid :SYSTEMcontext/RUN_FEDERATIONrequestid
    :target_name      "SYSTEM_TESTcontext+TEST_Afederator"
    }
   {:target_requestid :SYSTEMcontext/ENTITY_REPORTrequestid
    :target_name      "ROOT+SYSTEMcontext"
    }
   {:target_requestid :SYSTEMcontext/ENTITY_REPORTrequestid
    :target_name      "SYSTEMcontext+SYSTEM_TESTcontext"
    }
   ])
