(ns server.wrapper
  (:require [reagent.dom.server :as reagent-dom]
            [secretary.core :as secretary :refer-macros [defroute]]
            [app.core  :as app]))
          

(enable-console-print!)

(def version 0.1)

(defn with-version [url]
  (str url "?version=" version))


(defn template [{:keys [body]}]
  [:html
   [:head
    [:meta {:charset "utf-8"}]
    [:meta {:name    "viewport"
            :content "width=device-width, initial-scale=1.0"}]
    [:link {:type "text/css", 
            :href (with-version "/css/reset.css"), 
            :rel "stylesheet"}]]
   [:body
    [:div#app [body]]
    
    [:script {:type "text/javascript" :src (with-version "js/core.js")}]]])
    



(defn ^:export render-page [path]
  (reagent-dom/render-to-static-markup (do
                                        (secretary/dispatch! path)
                                        [template {:body app/view}])))
 
