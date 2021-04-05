package model


enum class Options {
    HIT,
    STAND,
    UNDEFINED
}


class SeatedPlayer(name: String = "SeatedPlayer") : BlackjackPlayer(name = name) {

    fun chooseOption(userInput: String) : Options {

        return when (userInput) {
            "1" -> Options.HIT
            "2" -> Options.STAND
            else -> Options.UNDEFINED
        }
    }

}