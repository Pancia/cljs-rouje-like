(ns cljs-rouje-like.spec-main
  (:require-macros
    [untangled-spec.reporters.suite :as ts])
  (:require
    untangled-spec.reporters.impl.suite
    cljs-rouje-like.tests-to-run))

(enable-console-print!)

(ts/deftest-all-suite specs #".*-spec")

(specs)
