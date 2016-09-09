(defproject cljs-rouje-like "0.1.0-SNAPSHOT"
  :description "Hello World, from Untangled!"

  :dependencies [[org.clojure/clojure "1.8.0"]
                 [org.clojure/clojurescript "1.8.51"]

                 [navis/untangled-client "0.5.3"]
                 [navis/untangled-spec "0.3.8" :scope "test"]

                 [com.taoensso/timbre "4.3.1"]
                 [navis/untangled-server "0.6.0"]]

  :plugins [[com.jakemccrary/lein-test-refresh "0.15.0"]]

  :test-refresh {:report untangled-spec.reporters.terminal/untangled-report
                 :with-repl true
                 :changes-only true}

  :source-paths ["src/server"]
  :jvm-opts ["-server" "-Xmx1024m" "-Xms512m" "-XX:-OmitStackTraceInFastThrow"]

  :test-paths ["specs" "specs/server" "specs/config"]
  :clean-targets ^{:protect false} ["target" "resources/public/js/compiled"]

  :figwheel {:css-dirs ["resources/public/css"]}

  :cljsbuild {:builds [{:id "dev"
                        :figwheel true
                        :source-paths ["src/client" "dev/client"]
                        :compiler {:main                 cljs.user
                                   :output-to            "resources/public/js/compiled/client.js"
                                   :output-dir           "resources/public/js/compiled/dev"
                                   :asset-path           "js/compiled/dev"
                                   :source-map-timestamp true
                                   :optimizations        :none}}

                       {:id           "production"
                        :source-paths ["src/client"]
                        :compiler     {:main          cljs-rouje-like.main
                                       :output-to     "resources/public/js/compiled/main.js"
                                       :output-dir    "resources/public/js/compiled/prod"
                                       :asset-path    "js/compiled/prod"
                                       :optimizations :none}}

                       {:id           "test"
                        :source-paths ["specs/client" "src/client"]
                        :figwheel     true
                        :compiler     {:main                 cljs-rouje-like.spec-main
                                       :output-to            "resources/public/js/specs/specs.js"
                                       :output-dir           "resources/public/js/compiled/specs"
                                       :asset-path           "js/compiled/specs"
                                       :optimizations        :none}}]}

  :profiles {:dev {:source-paths ["dev/client" "dev/server" "src/client" "src/server"]
                   :dependencies [[binaryage/devtools "0.6.1"]
                                  [com.cemerick/piggieback "0.2.1"]
                                  [figwheel-sidecar "0.5.3-1" :exclusions [joda-time clj-time ring/ring-core]]
                                  [org.clojure/tools.nrepl "0.2.12"]]
                   :repl-options {:init-ns user
                                  :nrepl-middleware [cemerick.piggieback/wrap-cljs-repl]}}})
