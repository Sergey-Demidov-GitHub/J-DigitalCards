package main;

import cardPackage.Card;
import dbUtils.DBCommInterface;
import deckPackage.Deck;
import dbUtils.DBComm;
import misc.Shuffle;

import java.util.HashSet;
import java.util.Set;

public class Session {
    private Deck deck;
    private static DBCommInterface dBCommunicator = new DBComm();

    // not the most elegant way, will do for now
    private Set<Integer> changedIds = new HashSet<Integer>();    // for db update
    private Set<Integer> changedTypes = new HashSet<>();
    private Set<Integer> deletedIds = new HashSet<Integer>();
    private Set<Integer> newIds = new HashSet<>();         // temp id for working on Cards while in cache are negative
    private Set<Integer> newRelations = new HashSet<>();
    private int tempId = -1;

    private Integer[] callMask = null;             // specifies the call/display order
    private int callMaskIndex = 0;

    public Session(Deck deck) {
        this.deck = deck;
        System.out.println("[INFO] Session started");
    }

    public Deck getDeck() {
        return deck;
    }

    public void setDeck(Deck deck) {
        this.deck = deck;
    }

    public void addNewCardToDeck(Card newCard) {
        deck.addCard(newCard);
    }

    // ==================================== callMask ====================================

    public Integer[] getCallMask() {
        return callMask;
    }

    public void initCallMask() {
        this.callMask = new Integer[deck.getCardMap().size()];
        int i = 0;
        for (int card_id : deck.getCardMap().keySet()) {
            this.callMask[i] = card_id;
            i++;
        }
        callMaskIndex = 0;
    }

    public void initCustomCallMask(HashSet<Integer> customCardIndex_Set) {
        if (customCardIndex_Set.isEmpty()){
            System.out.println("[INFO] No cards meet filtering conditions.");
        } else {
            this.callMask = new Integer[customCardIndex_Set.size()];
            this.callMask = customCardIndex_Set.toArray(this.callMask);
        }
        callMaskIndex = 0;
    }

    public Card getCurrentCard() {
        return deck.getCardMap().get(callMask[callMaskIndex]);
    }

    public void resetCallMaskIndex() {
        callMaskIndex = 0;
    }

    public void nextCallMaskIndex() {
        if (callMaskIndex + 1 == callMask.length)
            callMaskIndex = 0;
        else
            callMaskIndex++;
    }

    public void previousCallMaskIndex() {
        if (callMaskIndex - 1 < 0)
            callMaskIndex = callMask.length - 1;
        else
            callMaskIndex--;
    }

    // ==================================== session changes ====================================

    public void addChangedIds(int changedId) {
        changedIds.add(changedId);
    }

    public void addChangedTypes(int card_id) {
        changedTypes.add(card_id);
    }

    public void addDeletedIds(int deletedId) {
        deletedIds.add(deletedId);
    }

    /**
     * Generates a temporary id for new cards, that will be replaced
     * by an autogenerated one upon insertion in to DB.
     * @return temporary id
     */
    public int addNewIds() {
        newIds.add(tempId);
        tempId--;
        return tempId + 1;
    }

    public void addNewRelation(int card_id) {
        newRelations.add(card_id);
    }

    public void applyScoreChanges() {
        dBCommunicator.updateScoresForDeck(deck);
    }

    /**
     * inspects all session changes and applies them to the DB
     */
    public void applySessionChanges() {
        Set<Integer> tempIntersection = new HashSet<>();
        changedTypes.removeAll(deletedIds);
        changedTypes.removeAll(newIds);
        changedIds.removeAll(changedTypes);
        changedIds.removeAll(deletedIds);
        changedIds.removeAll(newIds);

        tempIntersection.addAll(newIds);
        tempIntersection.retainAll(deletedIds);
        newIds.removeAll(tempIntersection);
        deletedIds.removeAll(tempIntersection);
        tempIntersection.clear();

        tempIntersection.addAll(newRelations);
        tempIntersection.retainAll(deletedIds);
        newRelations.removeAll(tempIntersection);
        deletedIds.removeAll(tempIntersection);
        tempIntersection.clear();



        applyChanged();
        applyChangedTypes();
        applyRemoved();
        applyNew();
        applyNewRelations();


        changedTypes.clear();
        changedIds.clear();
        newIds.clear();
        deletedIds.clear();
        newRelations.clear();
        System.out.println("[INFO] Session changes applied");
    }

    private void applyChanged() {
        for (int card_id: changedIds) {
            dBCommunicator.updateCard_content(deck.getCardMap().get(card_id));
        }
    }

    private void applyChangedTypes() {
        for (int card_id: changedTypes) {
            dBCommunicator.switchCardType(deck.getCardMap().get(card_id));
        }
    }

    private void applyRemoved() {
        for (int card_id: deletedIds) {
            dBCommunicator.removeCardFromDeck_relation(deck.getId(), card_id);
            if (dBCommunicator.cardHasNoDeck(card_id))
                dBCommunicator.deleteCard(card_id);

        }
    }

    private void applyNew() {
        for (int card_id: newIds) {
            int newID = dBCommunicator.addCard(deck.getCardMap().get(card_id));
            dBCommunicator.addCardToDeck_relation(deck.getId(), newID);
        }
    }

    private void applyNewRelations() {
        for (int card_id: newRelations) {
            dBCommunicator.addCardToDeck_relation(deck.getId(), card_id);
        }
    }

    public void resetTempId() { tempId = -1;}

    // ==================================== misc ====================================

    public int getCardsLeft() {
        return (callMask.length - (callMaskIndex + 1));
    }

    public void shuffleCallMask() {
        Shuffle.shuffle(callMask);
    }



}
