import model.*

fun main () {

    startGame()
}


private fun startGame() {
    val game = Game()
    var playerOnTurn = game.nextPlayer()

    while (!game.checkGameOver()) {
        if(playerOnTurn is SeatedPlayer) {
            if(playerOnTurn.getResult() == 0) { // if player doesn't have any card it is start of a game and should draw two cards
                playerOnTurn.drawCard(game.getCardFromDeck())
                playerOnTurn.drawCard(game.getCardFromDeck())
            }
            else {
                println("Your cards are: ${playerOnTurn.hand}, and result ${playerOnTurn.getResult()}")
                println("Choose your option: 1. enter 1 for ${Options.HIT}. 2. enter 2 for ${Options.STAND}")
                val stringInput = readLine()!!
                when(playerOnTurn.chooseOption(stringInput)) {
                    Options.HIT -> {
                        playerOnTurn.drawCard(game.getCardFromDeck())
                    }
                    Options.STAND -> playerOnTurn = game.nextPlayer()
                    Options.UNDEFINED -> println("Undefined input")
                }
            }
        }
        else if(playerOnTurn is Dealer) {
            println("Dealer cards are: ${playerOnTurn.hand} and result ${playerOnTurn.getResult()}")
            playerOnTurn.drawCard(game.getCardFromDeck())

        }
    }

    game.players.forEach{
        println(it.toString())
    }

    if(game.checkWinner().size > 1 ) { // if there is more than 1 winner that means dealer and player have same result
        println("It is even")
    }
    else {
        println("Winner is ${game.checkWinner()}")
    }

    println("Do you want to start new game?Y/N")
    val stringInput = readLine()
    if (stringInput == "Y") {
        startGame()
    }
}