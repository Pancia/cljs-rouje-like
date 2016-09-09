(ns cljs-rouje-like.core
  (:require [om.next :as om]
            [untangled.client.core :as uc]

            cljs-rouje-like.state.mutations ;; DO NOT DELETE, loads cljs-rouje-like's mutations
            [cljs-rouje-like.initial-state :refer [initial-state]]
            ))

(defonce app
  (atom (uc/new-untangled-client
          :initial-state initial-state
          :started-callback (fn [{:keys [reconciler]}]
                              ;;TODO: initial load of data
                              ))))
