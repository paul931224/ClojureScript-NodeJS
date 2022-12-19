
(ns server.core
  (:require ["express" :as express]
            [server.wrapper :as wrapper]))
          

(defonce server (atom nil))

(def port 3000)

(def index-text "Index")

(def hello-text "Hello there D3V!")

(def products [{:name "Neura  link"
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


(def router 
  {"/"      handle-request
   "/hello" (fn [req res]  (.send res  hello-text))
   "/mapv"  (fn [req res]  (.send res (map-products)))})


(defn generate-get-route [app route handler]
  ;; Like (app.get("/" function(req, res))
  (.get app route handler))

(defn start-server []
  (let [app (express)]
    (doseq [[route handler] router]
      (generate-get-route app route handler))
    (.use app (express/static "public"))
    (.listen app port   
             (fn [] (println "Port: " port)))))
 

(defn ^:dev/before-load stop! []  
  (when @server (.close @server))) 
  

(defn ^:dev/after-load start! []
  (println "Code updated.")
  (reset! server (start-server)))


(defn start-server! []
  (start!))

  
