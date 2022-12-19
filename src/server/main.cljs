
(ns server.main
  (:require ["express" :as express]))


(defonce server (atom nil))

(def index-text "Index")

(def hello-text "Hello there D3V!")

(def products [{:name "Neuralink"
                :price 10000}
               {:name "Tesla"
                :price 30000}])

(defn map-products []
  (str
   "Products: "
   (mapv :name products) 
   " - Total-price: "
   (reduce + (map :price products))))

(defn start-server []
  (println "Starting server")
  (let [app (express)]
    (.get app "/"       (fn [req res]  (.send res index-text)))
    (.get app "/hello"  (fn [req res]  (.send res  hello-text)))
    (.get app "/mapv"   (fn [req res]  (.send res (map-products))))
    (.listen app 3000   (fn [] (println "Example app listening on port 3000!")))))
 

(defn ^:dev/before-load stop! []
  (println "Code updated before.")
  (when @server (.close @server)))
  

(defn ^:dev/after-load start! []
  (println "Code updated after.") ;index-text) 
  (reset! server (start-server)))

(defn main! []
  (println "App loaded!")
  (start!))
