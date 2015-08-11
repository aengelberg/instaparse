(defproject com.lucasbradstreet/instaparse-cljs "1.4.1.1-SNAPSHOT"
  :description "Instaparse: No grammar left behind"
  :url "https://github.com/lbradstreet/instaparse-cljs"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.7.0"]
                 [org.clojure/clojurescript "1.7.28"]]
  :profiles {:dev {:dependencies 
                   [[org.clojure/tools.trace "0.7.5"]
                    [criterium "0.3.1"]
                    [rhizome "0.1.8"]]
                   :plugins [[lein-figwheel "0.3.3"]]}}
  :aliases {"cleantestcljs" ["do" "clean," "cljsbuild" "test" "unit-tests"]}
  :test-paths ["test/"]
  :source-paths ["src/cljs" "src/clj" "src/cljc"]
  :plugins [[lein-cljsbuild "1.0.6"]]
  ;:hooks [leiningen.cljsbuild]
  :target-path "target"
  :scm {:name "git"
        :url "https://github.com/lbradstreet/instaparse-cljs"}
  :cljsbuild {:builds [{:id "none"
                        :source-paths ["src/cljs"]
                        :compiler {:output-to "target/js/none.js"
                                   :optimizations :none
                                   :pretty-print true}}
                       {:id "test"
                        :source-paths ["src/cljs" 
                                       "runner/cljs"
                                       "test/"]
                        :compiler {:output-to "target/js/advanced-test.js"
                                   :optimizations :advanced
                                   :target :nodejs
                                   :pretty-print false}}]
              :test-commands {"unit-tests" ["node" "target/js/advanced-test.js"]}})
