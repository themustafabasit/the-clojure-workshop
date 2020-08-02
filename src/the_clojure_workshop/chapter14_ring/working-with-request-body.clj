(ns the-clojure-workshop.chapter14-worwking-with-request-body
  (:require [ring.adapter.jetty :refer [run-jetty]]
            [compojure.core :refer [defroutes GET PUT DELETE]]
            [compojure.route :as route]
            [muuntaja.middleware :as middleware]
            [clj-http.client :as http]
            [clojure.data.json :as json]
            [clojure.edn :as edn]))

(def db (atom {}))

(defroutes routes
  (GET "/data-structure" request
    (when-let [data-structure (@db :data)]
      {:body data-structure}))
  (PUT "/data-structure" request
    (println request)
    (swap! db assoc :data (:body-params request))
    {:status 201})
  (DELETE "/data-structure" request
    (swap! db dissoc :data))
  (route/not-found "Not Found"))

(defn run
  []
  (run-jetty (middleware/wrap-format routes) {:port 8080
                                              :join? false}))
(def app (run))

(-> (http/put "http://localhost:8080/data-structure"
              {:content-type :application/json
               :body (json/write-str {:a 1
                                      :b #{2 3 4}})})
    :status)

(-> (http/get "http://localhost:8080/data-structure"
              {:accept :application/edn})
    :body
    edn/read-string)












