package deckPackage;

import cardPackage.Card;
import cardPackage.CardType;
import cardPackage.BasicCard;
import dbUtils.DBComm;
import dbUtils.DBCommInterface;

import java.util.HashMap;
import java.util.LinkedHashMap;

public class Deck {
    private int id;
    private String name;
    private LinkedHashMap<Integer, Card> cardMap = new LinkedHashMap<Integer, Card>();      // for distribution
    private static DBCommInterface dBCommunicator = new DBComm();

    /**
     * Lite weight constructor without cardMap
     * @param id
     * @param name
     */
    public Deck(int id, String name) {
        this.id = id;
        this.name = name;
        this.cardMap = null;
    }

    public Deck(Deck deck) {
        this.id = deck.id;
        this.name = deck.name;
        this.cardMap = deck.cardMap;
    }

    public void addCard(Card card) {
        this.cardMap.put(card.getId(), card);

    }

    public static LinkedHashMap buildHashMapFromArray(Integer[] cards) {
        LinkedHashMap<Integer, Card> cardMap = new LinkedHashMap<Integer, Card>();

        for (int i = 0; i < cards.length; i++){
            Card card = dBCommunicator.getCard(cards[i]);
            cardMap.put(cards[i], card);
        }

        return cardMap;
    }

    public HashMap getScoreMap(boolean orderJapEng) {
        HashMap<Integer, Integer[]> scoreMap = new HashMap<Integer, Integer[]>();
        for (int card_id: cardMap.keySet()) {
            scoreMap.put(card_id, cardMap.get(card_id).getScore().getScoreValues(orderJapEng));
        }
        return scoreMap;
    }

    public void removeCard(int card_id) {
        this.cardMap.remove(card_id);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCardType(int card_id, CardType newCardType) {
        //TODO:
        // manual cast with rebounds if needed
    }

    public LinkedHashMap<Integer, Card> getCardMap() {
        return cardMap;
    }

    public void setCardMap(LinkedHashMap<Integer, Card> cardMap) {
        this.cardMap = cardMap;
    }

    public void setCard_jap1(int card_id, String jap_1) {
        //System.out.println(cardMap.get(card_id).toString());
        if (cardMap.get(card_id) instanceof BasicCard)
            ((BasicCard) cardMap.get(card_id)).setJap1(jap_1);
        //System.out.println(cardMap.get(card_id).toString());
    }

    public void setCard_jap2(int card_id, String jap_2) {
        if (cardMap.get(card_id) instanceof BasicCard)
            ((BasicCard) cardMap.get(card_id)).setJap2(jap_2);
    }

    public void setCard_eng1(int card_id, String eng_1) {
        if (cardMap.get(card_id) instanceof BasicCard)
            ((BasicCard) cardMap.get(card_id)).setEng1(eng_1);
    }

    public void setCard_eng2(int card_id, String eng_2) {
        if (cardMap.get(card_id) instanceof BasicCard)
            ((BasicCard) cardMap.get(card_id)).setEng2(eng_2);
    }

    @Override
    public String toString() {
        return "Deck{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", cardMap=" + cardMap +
                '}';
    }

    public boolean checkCardInDeckById(int card_id) {
        return cardMap.containsKey(card_id);
    }



}
