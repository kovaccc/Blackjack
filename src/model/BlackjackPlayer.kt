package model

import java.util.*
import kotlin.collections.ArrayList



open class BlackjackPlayer(
    var id: UUID = UUID.randomUUID(),
    var name: String = "Player",
    var hand: ArrayList<Card> = arrayListOf(),
    var playerTurn: Boolean = false
    ) {


    fun drawCard(card: Card) {
        hand.add(card)
    }

    fun getResult() : Int {
        val aces = hand.filter { it == Card.ACE }
        val sumWithoutAces = hand.filter { it != Card.ACE }.sumBy { it.value }

        return when(aces.size) {
            0 -> { // when there is no ace in hand just return sum of values of all cards
              sumWithoutAces
            }

            1 -> {
                if(sumWithoutAces <= 10) { // ace worth 11 if sum of all other cards is less or equal 10
                    sumWithoutAces + 11
                }
                else { // ace worth 1 if sum of all other cards is more than 10
                    sumWithoutAces + Card.ACE.value
                }
            }
            else -> {
                if(sumWithoutAces <= 10) { // if there is more than 1 ace  and sum of all other cars is less than 10 one of the ace must worth 11
                    hand.minusElement(Card.ACE).sumBy { it.value } + 11 // we remove first ace card and replace it with value of 11
                }
                else { // if player has more than two ace and if sum of all other cards is more then 10 all aces worth 1
                    hand.sumBy { it.value }
                }

            }
        }
    }

    override fun toString(): String {
        return "${this.id}, ${this.name}, ${this.hand}, ${this.playerTurn}, ${this.getResult()}"
    }

}