(ns cljs.user
  (:require
    [devtools.core :as devtools]
    [untangled.client.core :as uc]
    [om.next :as om]

    [cljs-rouje-like.core :as core]
    [cljs-rouje-like.ui.root :as root]

    [cljs.pprint :refer [pprint]]))

(enable-console-print!)

(defonce tools-startup
  (do
    (devtools/install! [:sanity-hints])
    ))

(reset! core/app (uc/mount @core/app root/Root "app"))

(defn app-state [] @(:reconciler @core/app))

(defn log-app-state [& keywords]
  (pprint (let [app-state (app-state)]
            (if (= 0 (count keywords))
              app-state
              (select-keys app-state keywords)))))
