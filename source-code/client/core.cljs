(ns client.core
  (:require [reagent.dom :as rdom]
            [secretary.core :as secretary]
            [pushy.core :as pushy]
            [app.core :as app])
  (:import goog.History))


(enable-console-print!)
 

(defn start-client! []
  (println "Omg mate it's working") 
  (rdom/render [app/view] (.getElementById js/document "app")))

(pushy/push-state! secretary/dispatch!
                   (fn [x] (when (secretary/locate-route x) x)))