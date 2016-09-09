(ns cljs-rouje-like.system
  (:require
    [untangled.server.core :as core]
    
    [om.next.server :as om]
    [cljs-rouje-like.api.read :as r]
    [cljs-rouje-like.api.mutations :as mut]

    [taoensso.timbre :as timbre]))

(defn logging-mutate [env k params]
  (timbre/info "Entering mutation:" k)
  (mut/apimutate env k params))

(defn make-system [config-path]
  (core/make-untangled-server
    :config-path config-path
    :parser (om/parser {:read r/api-read :mutate logging-mutate})
    :parser-injections #{:config }
    :components {  }))
