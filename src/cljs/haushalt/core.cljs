(ns haushalt.core
  (:require [om.core :as om]
            [om.dom :as dom]))

(enable-console-print!)

(defonce app-state (atom {:text "Welcome"}))

(defn widget [data owner]
  (reify
    om/IRender
    (render [this]
      (dom/div nil 
               (dom/h1 #js{:id :welcome-screen} (:text data))
               (dom/input #js {:type "email" :placeholder "Your email adress"})
               (dom/button #js {:id "sign-up-button"} "Signup")))))


(om/root widget app-state {:target (. js/document (getElementById "input-container"))})



(swap! app-state #(assoc-in %1 [:text] %2) "Welcome")
