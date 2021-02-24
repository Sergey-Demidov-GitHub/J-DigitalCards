/*  Copyright (C) 2021 Sergey Demidov   */

package dbUtils;

import cardPackage.Card;
import deckPackage.Deck;

/**
 * Communication with db
 */
public interface DBCommInterface {
    /**
     * Clears database.
     */
    void clearDB();

    void switchCardType(Card card);

    /**
     * Creates new card entry from Card object with autogenerated id.
     * @param card - Card object
     * @return autogenerated id
     */
    int addCard(Card card);

    /**
     * Overrides content of the Card in the database (not CardType).
     * @param card - Card with updated content
     */
    void updateCard_content(Card card);

    /**
     * Returns Card-object with matching card_id from the database.
     * Depending on the CardType the object will be cast in to the appropriate subclass.
     * e.g. noun -> NounCard
     * @param card_id - id to find the correct Card in the database
     * @return Card-object with matching card_id
     */
    Card getCard(int card_id);

    /**
     * Deletes Card-object with matching card_id from the database.
     * @param card_id - id to find the correct Card in the database
     */
    void deleteCard(int card_id);

    /**
     * Checks if Card with the specified card_id is part of a deck.
     * @param card_id - id to find the correct Card in the database
     * @return true (if card with specified card_id has no deck)
     */
    boolean cardHasNoDeck(int card_id);


    /**
     * Returns the number of cards contained in the deck with specified deck_id.
     * @param deck_id - id to find the correct Deck in the database
     * @return number of cards contained in the deck with specified deck_id
     */
    int getCardCountInDeck(int deck_id);

    /**
     * Updates the Score of every Card in the database that is part of this deck.
     * @param deck - Deck of Cards whose scores ought to be updated
     */
    void updateScoresForDeck(Deck deck);

    /**
     * Returns the name of the Deck with matching deck_id from the database.
     * @param deck_id - id to find the correct Deck in the database
     * @return name of the Deck with matching deck_id
     */
    String getDeckName(int deck_id);

    /**
     * Creates new Deck with autogenerated id and specified name in the database.
     * @param deck_name - name of the new deck
     * @return autogenerated deck_id
     */
    int addDeck(String deck_name);

    /**
     * Delete Deck with matching deck_id from the database.
     * @param deck_id - id to find the correct Deck in the database
     */
    void removeDeck(int deck_id);

    /**
     * Changes the name of the Deck with matching deck_id in the database.
     * @param deck_id - id to find the correct Deck in the database
     * @param deck_name - new name
     */
    void updateDeckName(int deck_id, String deck_name);

    /**
     * Returns Deck-object, which matches the specified deck_id in the database.
     * @param deck_id - id to find the correct Deck in the database
     * @return Deck-object
     */
    Deck getDeck(int deck_id);

    /**
     * Returns array with all Decks in the database, but only their name and id are set.
     * @return array with all Decks (deck_id, deck_name)
     */
    Deck[] getDeckList();

    /**
     * Links a Card and a Deck. The Card with the specified card_id is then considered
     * to be part of the Deck with the specified deck_id.
     * @param deck_id - id to find the correct Deck in the database
     * @param card_id - id to find the correct Card in the database
     */
    void addCardToDeck_relation(int deck_id, int card_id);

    /**
     * Removes relation between a Card and a Deck. The Card with the specified card_id stops being
     * part of the Deck with the specified deck_id.
     * @param deck_id - id to find the correct Deck in the database
     * @param card_id - id to find the correct Card in the database
     */
    void removeCardFromDeck_relation(int deck_id, int card_id);

}
