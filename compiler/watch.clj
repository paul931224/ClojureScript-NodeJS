(ns watch
  (:require
   [shadow.cljs.devtools.server :as server]
   [shadow.cljs.devtools.api    :as shadow]
   [babashka.process :refer [process]]))
  
(defn watch
  [config]
  (server/stop!)
  (server/start!)
  (shadow/watch :server)
  (shadow/watch :client) 
  (println "Watching :client and :server with shadow-cljs")
  (let [stream (-> (process "ls") :out)]
    @(process {:in stream
               :out :inherit} "node resources/backend/core.js")
    nil))
  
