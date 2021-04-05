package contracts

import model.Card

object BlackjackContract {

    // A standard deck of cards has four suites: hearts, clubs, spades, diamonds.
    // Each suite has thirteen cards: ace, 2, 3, 4, 5, 6, 7, 8, 9, 10, jack, queen and king.
    // Thus the entire deck has 52 cards total.
    // read-only list
    val deck = listOf(
        Card.ACE,
        Card.ACE,
        Card.ACE,
        Card.ACE,
        Card.TWO,
        Card.TWO,
        Card.TWO,
        Card.TWO,
        Card.THREE,
        Card.THREE,
        Card.THREE,
        Card.THREE,
        Card.FOUR,
        Card.FOUR,
        Card.FOUR,
        Card.FOUR,
        Card.FIVE,
        Card.FIVE,
        Card.FIVE,
        Card.FIVE,
        Card.SIX,
        Card.SIX,
        Card.SIX,
        Card.SIX,
        Card.SEVEN,
        Card.SEVEN,
        Card.SEVEN,
        Card.SEVEN,
        Card.EIGHT,
        Card.EIGHT,
        Card.EIGHT,
        Card.EIGHT,
        Card.NINE,
        Card.NINE,
        Card.NINE,
        Card.NINE,
        Card.TEN,
        Card.TEN,
        Card.TEN,
        Card.TEN,
        Card.JACK,
        Card.JACK,
        Card.JACK,
        Card.JACK,
        Card.QUEEN,
        Card.QUEEN,
        Card.QUEEN,
        Card.QUEEN,
        Card.KING,
        Card.KING,
        Card.KING,
        Card.KING)

    object GameValues {

        const val SUM_CARD_LOSS_LIMIT = 21
        const val DEALER_DRAW_LIMIT = 16
    }

}