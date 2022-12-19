
(ns server.main
  (:require ["express" :as express]))

(def value-a 1)

(defonce value-b 2)



(set! *warn-on-infer* true)

(defonce server (atom nil))

(defn start-server []
  (println "Starting server")
  (let [app (express)]
    (.get app "/" (fn [req res] (.send res "Hello, world")))
    (.listen app 3000 (fn [] (println "Example app listening on port 3000!")))))

(defn ^:dev/before-load stop! []
  (println "Code updated.")
  (println "Trying values:" value-a value-b)
  (.close @server)
  (reset! server nil))

(defn ^:dev/after-load start! []
  (println "Code updated.")
  (println "Trying values:" value-a value-b)
  (reset! server (start-server)))

(defn main! []
  (println "Adsapp loaded!")
  (start!))
