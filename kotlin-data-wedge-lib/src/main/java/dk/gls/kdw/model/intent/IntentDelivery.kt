package dk.gls.kdw.model.intent

enum class IntentDelivery(val value: Int) {

    /** Start Activity **/
    START_ACTIVITY(0),

    /** Start Service **/
    START_SERVICE(1),

    /** Start Broadcast **/
    BROADCAST(2)
}