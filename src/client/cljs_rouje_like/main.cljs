(ns cljs-rouje-like.main
  (:require [cljs-rouje-like.core :refer [app]]
            [untangled.client.core :as core]
            [cljs-rouje-like.ui.root :as root]))

(core/mount app root/Root "app")
