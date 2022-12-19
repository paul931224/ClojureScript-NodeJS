
(ns server.core
  (:require ["express" :as express]
            [server.wrapper :as wrapper]))
          

(defonce server (atom nil))

(def port 3000)

(def index-text "Index")

(def hello-text "Hello there D3V!")

(def products [{:name "Neuralink"
                :price 10000}
               {:name "Tesla"
                :price 30000}])

(defn map-products []
  (str
   "Products  : "
   (mapv :name products) 
   " - Total - price: "
   (reduce + (map :price products))))


(defn handle-request [req res]
  (.send res (wrapper/render-page (.-path req))))

(defn start-server []
  (println "Starting server")
  (let [app (express)]
    (.get app "/"       handle-request)
    (.get app "/hello"  (fn [req res]  (.send res  hello-text)))
    (.get app "/mapv"   (fn [req res]  (.send res (map-products))))
    (.use app (express/static "resources/public"))
    (.listen app port   (fn [] (println "Example app listening on port " port)))))
 

(defn ^:dev/before-load stop! []
  (println "Code updated before.")
  (when @server (.close @server)))
  

(defn ^:dev/after-load start! []
  (println "Code updated after.")
  (reset! server (start-server)))


(defn start-server! []
  (println "Omg mate it's working")
  (start!))

  
