(ns server.templates
  (:require [reagent.dom.server :as reagent-dom]
            [secretary.core :as secretary :refer-macros [defroute]]
            [app.core  :as app]))
          

(enable-console-print!)

(defn template [{:keys [body]}]
  [:html
   [:head
    [:meta {:charset "utf-8"}]
    [:meta {:name    "viewport"
            :content "width=device-width, initial-scale=1.0"}]]
   [:body
    [:div#app [body]]
    [:script {:type "text/javascript" :src "js/core.js"}]]])
    



(defn ^:export render-page [path]
  (reagent-dom/render-to-static-markup (do
                                        (secretary/dispatch! path)
                                        [template {:body app/view}])))
 
