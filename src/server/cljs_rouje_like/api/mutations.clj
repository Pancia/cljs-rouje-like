(ns cljs-rouje-like.api.mutations
  (:require
    
    [om.next.server :as oms]
    [taoensso.timbre :as timbre]
    [untangled.server.core :as core]))

(defmulti apimutate oms/dispatch)

(comment
  (defmethod apimutate 'thing/create [env k params]
    {:action (fn [] (timbre/info "Creating a thing"))}))
