(ns haushalt.core
  (:require [om.core :as om :include-macros true]
            [om.dom :as dom :include-macros true]))

(enable-console-print!)

(defonce app-state (atom {:expenses #{}}))

(defn main []
  (om/root
    (fn [app owner]
      (reify
        om/IRender
        (render [_]
          (dom/h4 nil "Select category")
          (dom/input #js {:id "category-input"}))))
    app-state
    {:target (. js/document (getElementById "app"))}))



