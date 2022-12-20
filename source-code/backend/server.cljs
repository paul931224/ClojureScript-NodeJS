
(ns backend.server
  (:require ["express" :as express]            
            [backend.handlers :as handlers]
            [config   :refer [port]]))
          

(defonce server (atom nil))

(def router 
  {"/"      handlers/index})
  
(defn generate-get-route [app route handler]
  ;; Like (app.get("/" function(req, res))
  (.get app route handler))


(defn start-server []
  (let [app (express)]
    (doseq [[route handler] router]
      (generate-get-route app route handler))
    (.use app (express/static "resources/frontend"))
    (.listen app port   
             (fn [] (println "Port: " port)))))
 

(defn ^:dev/before-load stop! []  
  (when @server (.close @server))) 
  
 
(defn ^:dev/after-load start! []
  (println "Code updated.")
  (reset! server (start-server)))


  
