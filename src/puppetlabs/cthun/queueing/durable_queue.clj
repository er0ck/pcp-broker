(ns puppetlabs.cthun.queueing.durable-queue
  (:require [clojure.core.async :as async]
            [clojure.tools.logging :as log]
            [durable-queue :as q]
            [puppetlabs.cthun.queueing :refer [QueueingService]]
            [puppetlabs.trapperkeeper.core :refer [defservice]]
            [puppetlabs.trapperkeeper.services :refer [service-context]]))

(defn make-queue-context
  []
  (let [queues (q/queues "/tmp" {})]
    queues))

(defn queue-message
  [queues topic message]
  (q/put! queues (symbol topic) message))

(defn subscribe-to-topic
  [queues topic callback-fn]
  (async/go-loop []
    (let [message (q/take! queues (symbol topic))]
      (callback-fn (deref message))
      (q/complete! message)
      (recur))))

(defservice queueing-service
  "durable-queue implementation of the queuing service"
  QueueingService
  []
  (init
   [this context]
   (log/info "Initializing durable-queue service")
   (assoc context :queues (make-queue-context)))
  (queue-message
   [this topic message]
   (queue-message (:queues (service-context this)) topic message))
  (subscribe-to-topic
   [this topic callback-fn]
   (subscribe-to-topic (:queues (service-context this)) topic callback-fn)))
