(ns backend.handlers
  (:require [backend.html :as html]))
  

(defn index [req res]
  (.send res (html/render-page (.-path req))))