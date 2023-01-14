(ns frontend.core
  (:require [reagent.dom :as rdom]
            [secretary.core :as secretary]
            [pushy.core :as pushy]
            [frontend.home :as home])
  (:import goog.History))



(enable-console-print!)

(defn start! []
  (rdom/render [home/view] (.getElementById js/document "app")))

(pushy/push-state!
 secretary/dispatch!
 (fn [x] (when (secretary/locate-route x) x)))