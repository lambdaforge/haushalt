(ns haushalt.core
  (:require [om.core :as om :include-macros true]
            [kioo.om :refer [do-> set-attr content listen remove-attr append]]
            [kioo.core :refer [handle-wrapper]]
            [om.dom :as dom])
  (:require-macros [kioo.om :refer [defsnippet deftemplate]]))


(enable-console-print!)

;; --- HELPERS ----
(defn handle-text-change
  "Store and update input text in view component"
  [e owner text]
  (om/set-state! owner text (.. e -target -value)))



(defonce app-state
  (atom {:expenses []
         :ws []}))

(defn save-expense
  "Save expense to local state"
  [data owner]
  (let [expense {:category (om/get-state owner :category-text)
                 :expense (om/get-state owner :expense-value)}]
    (om/transact! data :expenses (fn [old _] (concat [expense] old)))
    (om/set-state! owner :category-text "")
    (om/set-state! owner :expense-value "")))


;; --- INPUT ELEMENTS ---
(deftemplate input-fields "templates/expense-input.html"
  [data owner state]
  {[:#category-input-field] (do-> (set-attr :value (:category-text state))
                                  (listen :on-change #(handle-text-change % owner :category-text)))
   [:#expense-input-field] (do-> (set-attr :value (:expense-value state))
                                  (listen :on-change #(handle-text-change % owner :expense-value)))
   [:#expense-save-button]  (listen :on-click  (fn [e] (save-expense data owner)))})



(defn input-widget
  "Input fields for category and amount"
  [data owner]
  (reify
    om/IInitState (init-state [_] {:category-text "" :expense-value nil})
    om/IRenderState (render-state [this state]
                      (println data)
                      (input-fields data owner state))))

;; --- TABLE ELEMENTS ---
(defsnippet expense-entry "templates/expense-table.html" [:.expense-table-row]
  [{:keys [category expense]}]
  {[:.category-table-entry] (content category)
   [:.expense-table-entry] (content expense)})

(deftemplate expense-table "templates/expense-table.html"
  [data]
  {[:#expense-table-body] (content (map expense-entry (:expenses data)))})

(defn table-widget
  "Table of expenses"
  [data owner]
  (reify
    om/IRender
    (render [this]
      (expense-table data))))



;; --- ENTRY POINT ---
(om/root
 input-widget
 app-state
 {:target (. js/document (getElementById "input-container"))})

(om/root
 table-widget
 app-state
 {:target (. js/document (getElementById "table-container"))})
