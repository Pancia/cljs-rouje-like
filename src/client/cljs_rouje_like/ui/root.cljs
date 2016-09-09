(ns cljs-rouje-like.ui.root
  (:require
    [untangled.client.mutations :as mut]
    [om.dom :as dom]
    [om.next :as om :refer-macros [defui]]
    [goog.dom :as gdom]))

(defn draw-image [ctx img sx sy dx dy ssize dsize]
  (js/console.log "draw-image" ctx img)
  (js/console.log (str [(* ssize sx) (* ssize sy) ssize ssize dx dy dsize dsize]))
  (.drawImage ctx img (* ssize sx) (* ssize sy) ssize ssize dx dy dsize dsize))

(defn paint [ctx]
  (.save ctx)
  (let [grim-img (gdom/$ "grim-tile-sheet")]
    (draw-image ctx grim-img 4 2 150 150 12 30)
    (.fillRect ctx 5 5 40 20))
  (.restore ctx))

(defui ^:once Game
  Object
  (componentDidMount [this]
    (let [canvas (om/get-state this :canvas)]
      (js/console.log "canvas" canvas)
      (paint (.getContext canvas "2d"))))
  (render [this]
    (dom/div #js {:id "the-game-container"}
             (dom/canvas #js {:ref #(when % (om/update-state! this assoc :canvas %))
                              :width "300px"
                              :height "300px"}))))
(def the-game (om/factory Game))

(defui ^:once Root
  static om/IQuery
  (query [this] [:ui/react-key])
  Object
  (render [this]
    (let [{:keys [ui/react-key] :or {ui/react-key "ROOT"}} (om/props this)]
      (dom/div #js {:key react-key} "Hello World!"
               (the-game)
               (dom/img #js {:src "img/grim_12x12.png" :id "grim-tile-sheet"})))))
