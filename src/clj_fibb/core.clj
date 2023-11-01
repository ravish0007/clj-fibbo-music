(ns clj-fibb.core
  (:require [clojure.java.io :as io]))
(require '[dynne.sampled-sound :refer :all])
(require '[clojure.string :as str])

(defn fibonacci
  ([] (fibonacci 0 1))
  ([a b] (lazy-seq (cons a (fibonacci b (+ a b))))))

(defn play-sound [x]
  (play (read-sound (io/resource (str x ".mp3")))))

(defn play-term [term]
  (doseq [digit (str/split (str term) #"")]
    (play-sound digit)
    (Thread/sleep 500)))

(defn play-fibonacci []
  (let [fib-seq (fibonacci)]
    (doseq [term fib-seq]
      (play-term term))))

(defn -main []
  (play-fibonacci))
