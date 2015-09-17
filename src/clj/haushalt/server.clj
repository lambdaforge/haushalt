(ns haushalt.server)


(defn socket-handler
  "Socket handling"
  [request]
  (with-channel request channel
    (on-close channel
              (fn [status]
                (swap! server-state update-in [:authenticated-tokens] dissoc channel)))
    (on-receive channel
                (fn [msg]
                  (let [in-msg (read-string msg)
                        {:keys [topic data token] :as out-msg}
                        (dispatch server-state channel in-msg)]
                    (send! channel (str out-msg)))))))

(defn dispatch
  "Handle incoming requests via sockets"
  [state channel {:keys [topic data token] :as msg}]
  (let [conn (:conn @state)]
    (if (authorized? state msg)
      (case topic
        :get-revenues {:topic topic :data (get-revenues conn data)}
        {:topic :error :data :unknown-request})
      {:topic :error :data :not-authorized})))

;; for changes: swap databases = tables
