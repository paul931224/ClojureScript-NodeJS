(ns prod
  (:require [shadow.cljs.devtools.server :as server]
            [shadow.cljs.devtools.api    :as shadow]
            [babashka.process :refer [process sh shell]])
  (:gen-class))

(defn prod
  [{:keys  [java-config js-builds]}]
  (sh "rm -r prod")
  (sh "mkdir -p prod/resources")
  (sh "cp -r resources/frontend prod/resources/frontend")
  (sh "cp -r package.json       prod/package.json")  
  (sh "rm -r prod/resources/frontend/js")
  (shadow/release :backend-ready)
  (shadow/release :frontend-ready))
  
