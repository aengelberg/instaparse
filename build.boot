(set-env! :dependencies '[[org.clojure/clojure "1.7.0"]
                          [org.clojure/clojurescript "1.7.28" :scope "test"]
                          [adzerk/bootlaces "0.1.11" :scope "test"]
                          [adzerk/boot-cljs "0.0-3308-0" :scope "test"]
                          [adzerk/boot-test "1.0.4" :scope "test"]
                          [aengelberg/boot-cljsee "0.1.0-SNAPSHOT" :scope "test"]
                          [boot/core "2.1.2" :scope "provided"]]
          :source-paths #{"src/clj"
                          "src/cljc"})

(require '[adzerk.bootlaces :refer :all]
         '[adzerk.boot-cljs :refer :all]
         '[adzerk.boot-test :refer :all]
         '[aengelberg.boot-cljsee :refer :all]
         '[boot.core :as core])

(def +version+ "1.4.1.1-SNAPSHOT")
(bootlaces! +version+)
(task-options!
  pom {:project 'com.lucasbradstreet/instaparse-cljs
       :version +version+})
(set-env! :resource-paths (get-env :source-paths)) ; hack around bootlaces behavior

(core/deftask build-cljs-tests
  []
  (set-env! :source-paths #{"src/cljs"
                            "src/cljc"
                            "runner/cljs"
                            "test/"})
  (task-options! cljs {:compiler-options {:target :nodejs}
                       :optimizations :advanced})
  (comp (cljsee) (cljs)))

(core/deftask test-clj
  []
  (set-env! :source-paths #(conj %
                                 "test/"))
  (comp (cljsee) (test)))
