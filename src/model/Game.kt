package model

import contracts.BlackjackContract


enum class Card(val value: Int){
    ACE(1), // it can also result with 11
    TWO(2),
    THREE(3),
    FOUR(4),
    FIVE(5),
    SIX(6),
    SEVEN(7),
    EIGHT(8),
    NINE(9),
    TEN(10),
    JACK(10),
    QUEEN(10),
    KING(10)
}



class Game(val players: List<BlackjackPlayer> = arrayListOf(SeatedPlayer(), Dealer()),
           private var gameDeck: ArrayList<Card> = ArrayList(arrayListOf(BlackjackContract.deck, BlackjackContract.deck,
               BlackjackContract.deck, BlackjackContract.deck, BlackjackContract.deck, BlackjackContract.deck).flatten()) // The six-deck game (312 cards) is the most popular in Blackjack

           ) {

    fun nextPlayer(): BlackjackPlayer { // set player/dealer for playing

        return if (!players.first().playerTurn) {
            players[1].playerTurn = false

            players.first().playerTurn = true
            players.first()
        } else {
            players.first().playerTurn = false

            players[1].playerTurn = true
            players[1]
        }
    }

    private fun getNewDecks() : ArrayList<Card> {
        return ArrayList(arrayListOf(BlackjackContract.deck, BlackjackContract.deck,
            BlackjackContract.deck, BlackjackContract.deck, BlackjackContract.deck, BlackjackContract.deck).flatten())
    }

    fun getCardFromDeck() : Card {
        if(gameDeck.isEmpty()) {
            gameDeck = getNewDecks()
        }

        val shuffledDeck = gameDeck.shuffled() // shuffle deck
        val card = shuffledDeck.random() // get random card from deck
        gameDeck.remove(card) // remove that card from deck
        return card
    }

    fun checkGameOver(): Boolean {
        players.forEach {
            if (it is Dealer) { // check for type of player Dealer, check if dealer result is more than 16
                if(it.getResult() >= BlackjackContract.GameValues.DEALER_DRAW_LIMIT ) return true
            }
            else { // seatedPlayer
                if(it.getResult() > BlackjackContract.GameValues.SUM_CARD_LOSS_LIMIT) return true // if player has more than 21 he lost so it is also game over
            }
        }
        return false
    }

    fun checkWinner() : List<BlackjackPlayer> {


        val maxValue = players.filter { it.getResult() <= BlackjackContract.GameValues.SUM_CARD_LOSS_LIMIT }.maxByOrNull { it.getResult() } // get max players and also less or equal then limit 21

        val maxBlackJackPlayers = players.filter { it.getResult() == maxValue?.getResult()} // get all players with max result

        val result: List<BlackjackPlayer>

        if(maxBlackJackPlayers.size > 1) { // check if dealer and player have same result
            result = if(maxValue?.getResult() == BlackjackContract.GameValues.SUM_CARD_LOSS_LIMIT) { // check if sum is 21 - possible Blackjack

                val playersWithBlackjack = maxBlackJackPlayers.filter { it.hand.size == 2} // if you have 21 in first two cards you have black jack
                if(playersWithBlackjack.isNotEmpty()) { // if some of BlackjackPlayers have black jack they are winners (dealer or player or both)
                    playersWithBlackjack
                } else { // if there is not BlackjackPlayer with blackjack and result is 21
                    maxBlackJackPlayers
                }
            }
            else {
                maxBlackJackPlayers
            }
        }
        else { // only one winner
            result = maxBlackJackPlayers
        }

        return result
    }




}