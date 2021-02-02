/*  Copyright (C) 2021 Sergey Demidov   */

package dbUtils;

import cardPackage.*;
import cardPackage.Types.CardType;
import deckPackage.Deck;

public class DBComm implements DBCommInterface {
    public void clearDB(){
        SQLUtility.resetCardBaseIndex();
        SQLUtility.resetDeckBaseIndex();
        SQLUtility.clearCardBase();
        SQLUtility.clearDeckBase();
    }


    //TODO: update switch from/to kanji
    public void switchCardType(Card card) {
        CardType oldCardType = SQLUtility.getCardBase_CardType(card.getId());
        SQLUtility.deleteTypeContent(oldCardType, card.getId());
        CardType newCardType = card.getCardType();
        BasicCard tempCard = (BasicCard) card;
        SQLUtility.changeCardBase_CardType(card);
        switch (newCardType) {
            case NOUN: {
                SQLUtility.insert_typeNoun(tempCard.getId(), tempCard.getJap1(), tempCard.getJap2(), tempCard.getEng1(), tempCard.getEng2());
                break;
            }
            case VERB: {
                SQLUtility.insert_typeVerb(tempCard.getId(), tempCard.getJap1(), tempCard.getJap2(), tempCard.getEng1(), tempCard.getEng2(), "UNKNOWN");
                break;
            }
            case ADJECTIVE: {
                SQLUtility.insert_typeAdjective(tempCard.getId(), tempCard.getJap1(), tempCard.getJap2(), tempCard.getEng1(), tempCard.getEng2(), "UNKNOWN");
                break;
            }
            case OTHER: {
                SQLUtility.insert_typeOther(tempCard.getId(), tempCard.getJap1(), tempCard.getJap2(), tempCard.getEng1(), tempCard.getEng2());
                break;
            }
            case KANJI: {
                break;
            }

        }
    }

    public int addCard(Card card) {
        if (card instanceof NounCard)
            return addNounCard((NounCard) card);
        if (card instanceof VerbCard)
            return addVerbCard((VerbCard) card);
        if (card instanceof AdjectiveCard)
            return addAdjectiveCard((AdjectiveCard) card);
        if (card instanceof OtherCard)
            return addOtherCard((OtherCard) card);
        if (card instanceof KanjiCard)
            return addKanjiCard((KanjiCard) card);
        return -1;
    }

    private int addNounCard(NounCard card) {
        SQLUtility.insertCardBase_Card(CardType.NOUN);
        int card_id = SQLUtility.getLatestIdCardBase();
        SQLUtility.insertScore(card_id, card.getScore());
        SQLUtility.insert_typeNoun(card_id, card.getJap1(), card.getJap2(), card.getEng1(), card.getEng2());

        return card_id;
    }

    private int addVerbCard(VerbCard card) {
        SQLUtility.insertCardBase_Card(CardType.VERB);
        int card_id = SQLUtility.getLatestIdCardBase();
        SQLUtility.insertScore(card_id, card.getScore());
        SQLUtility.insert_typeVerb(card_id, card.getJap1(), card.getJap2(), card.getEng1(), card.getEng2(), card.getType().value());

        return card_id;
    }

    private int addAdjectiveCard(AdjectiveCard card) {
        SQLUtility.insertCardBase_Card(CardType.ADJECTIVE);
        int card_id = SQLUtility.getLatestIdCardBase();
        SQLUtility.insertScore(card_id, card.getScore());
        SQLUtility.insert_typeAdjective(card_id, card.getJap1(), card.getJap2(), card.getEng1(), card.getEng2(), card.getType().value());

        return card_id;
    }

    private int addOtherCard(OtherCard card) {
        SQLUtility.insertCardBase_Card(CardType.OTHER);
        int card_id = SQLUtility.getLatestIdCardBase();
        SQLUtility.insertScore(card_id, card.getScore());
        SQLUtility.insert_typeOther(card_id, card.getJap1(), card.getJap2(), card.getEng1(), card.getEng2());

        return card_id;
    }

    private int addKanjiCard(KanjiCard card) {
        SQLUtility.insertCardBase_Card(CardType.KANJI);
        int card_id = SQLUtility.getLatestIdCardBase();
        SQLUtility.insertScore(card_id, card.getScore());
        SQLUtility.insert_typeKanji(card_id, card.getKanji(), card.getKun(), card.getOn(), card.getTranslation());

        return card_id;
    }

    public void updateCard_content(Card card) {
        if (card instanceof NounCard)
            updateNounCard_content((NounCard) card);
        if (card instanceof VerbCard)
            updateVerbCard_content((VerbCard) card);
        if (card instanceof AdjectiveCard)
            updateAdjectiveCard_content((AdjectiveCard) card);
        if (card instanceof OtherCard)
            updateOtherCard_content((OtherCard) card);
        if (card instanceof KanjiCard)
            updateKanjiCard_content((KanjiCard) card);
    }

    private void updateNounCard_content(NounCard card) {
        SQLUtility.update_typeNoun_jap1(card.getId(), card.getJap1());
        SQLUtility.update_typeNoun_jap2(card.getId(), card.getJap2());
        SQLUtility.update_typeNoun_eng1(card.getId(), card.getEng1());
        SQLUtility.update_typeNoun_eng2(card.getId(), card.getEng2());
    }

    private void updateVerbCard_content(VerbCard card) {
        SQLUtility.update_typeVerb_jap1(card.getId(), card.getJap1());
        SQLUtility.update_typeVerb_jap2(card.getId(), card.getJap2());
        SQLUtility.update_typeVerb_eng1(card.getId(), card.getEng1());
        SQLUtility.update_typeVerb_eng2(card.getId(), card.getEng2());
        SQLUtility.update_typeVerb_type(card.getId(), card.getType().value());

    }

    private void updateAdjectiveCard_content(AdjectiveCard card) {
        SQLUtility.update_typeAdjective_jap1(card.getId(), card.getJap1());
        SQLUtility.update_typeAdjective_jap2(card.getId(), card.getJap2());
        SQLUtility.update_typeAdjective_eng1(card.getId(), card.getEng1());
        SQLUtility.update_typeAdjective_eng2(card.getId(), card.getEng2());
        SQLUtility.update_typeAdjective_type(card.getId(), card.getType().value());
    }

    private void updateOtherCard_content(OtherCard card) {
        SQLUtility.update_typeOther_jap1(card.getId(), card.getJap1());
        SQLUtility.update_typeOther_jap2(card.getId(), card.getJap2());
        SQLUtility.update_typeOther_eng1(card.getId(), card.getEng1());
        SQLUtility.update_typeOther_eng2(card.getId(), card.getEng2());
    }

    private void updateKanjiCard_content(KanjiCard card) {
        SQLUtility.update_typeKanji_kanji(card.getId(), card.getKanji());
        SQLUtility.update_typeKanji_kun(card.getId(), card.getKun());
        SQLUtility.update_typeKanji_on(card.getId(), card.getOn());
        SQLUtility.update_typeKanji_translation(card.getId(), card.getTranslation());
    }

    public Card getCard(int card_id) {
        Card card = null;

        CardType cardType = SQLUtility.getCardBase_CardType(card_id);
        if (cardType != null) {
            if(cardType == CardType.NOUN)
                card = buildNounCard(card_id);
            if(cardType == CardType.VERB)
                card = buildVerbCard(card_id);
            if(cardType == CardType.ADJECTIVE)
                card = buildAdjectiveCard(card_id);
            if(cardType == CardType.OTHER)
                card = buildOtherCard(card_id);
            if(cardType == CardType.KANJI)
                card = buildKanjiCard(card_id);
        } else {
            System.err.println("[ERROR] DBComm.getCard: no cardType");
        }

        return card;
    }

    public void deleteCard(int card_id) {
            SQLUtility.deleteCardBase_Card(card_id);
    }

    public boolean cardHasNoDeck(int card_id) {
        return SQLUtility.getDeckDistribution_Card(card_id).length == 0;
    }

    public int getCardCountInDeck(int deck_id) {
        return SQLUtility.getDeckDistribution_Deck(deck_id).length;
    }

    private NounCard buildNounCard(int card_id) {
        NounCard card = null;
        Score score = null;
        score = SQLUtility.getScore(card_id);
        card = SQLUtility.get_typeNoun_content(card_id);
        card.setScore(score);
        return card;
    }

    private VerbCard buildVerbCard(int card_id) {
        VerbCard card = null;
        Score score = null;
        score = SQLUtility.getScore(card_id);
        card = SQLUtility.get_typeVerb_content(card_id);
        card.setScore(score);
        return card;
    }

    private AdjectiveCard buildAdjectiveCard(int card_id) {
        AdjectiveCard card = null;
        Score score = null;
        score = SQLUtility.getScore(card_id);
        card = SQLUtility.get_typeAdjective_content(card_id);
        card.setScore(score);
        return card;
    }

    private OtherCard buildOtherCard(int card_id) {
        OtherCard card = null;
        Score score = null;
        score = SQLUtility.getScore(card_id);
        card = SQLUtility.get_typeOther_content(card_id);
        card.setScore(score);
        return card;
    }

    private KanjiCard buildKanjiCard(int card_id) {
        KanjiCard card = null;
        Score score = null;
        score = SQLUtility.getScore(card_id);
        card = SQLUtility.get_typeKanji_content(card_id);
        card.setScore(score);
        return card;
    }

    public void updateScoresForDeck(Deck deck) {
        for (int card_id: deck.getCardMap().keySet()) {
            SQLUtility.changeScore(card_id, deck.getCardMap().get(card_id).getScore());
        }
    }

    public int addDeck(String deck_name) {
        SQLUtility.insertDeckBase(deck_name);
        int deck_id = SQLUtility.getLatestIdDeckBase();
        return deck_id;
    }

    public void removeDeck(int deck_id) {
        SQLUtility.deleteDeckBase_Deck(deck_id);
    }

    public void updateDeckName(int deck_id, String deck_name) {
        SQLUtility.changeDeckBase_name(deck_id, deck_name);
    }

    public String getDeckName(int deck_id) {
        return SQLUtility.getDeckBase_name(deck_id);
    }

    public Deck getDeck(int deck_id) {
        Deck deck = null;

        String deckName = SQLUtility.getDeckBase_name(deck_id);
        Integer[] distribution = SQLUtility.getDeckDistribution_Deck(deck_id);

        deck = new Deck(deck_id, deckName);
        deck.setCardMap(Deck.buildHashMapFromArray(distribution));

        return deck;
    }

    public Deck[] getDeckList() {
        Deck[] deckList = SQLUtility.getDeckList();
        return deckList;
    }

    public void addCardToDeck_relation(int deck_id, int card_id){
        SQLUtility.insertDeckDistribution(deck_id, card_id);
    }

    public void removeCardFromDeck_relation(int deck_id, int card_id) {
        SQLUtility.deleteDeckDistribution_Card(deck_id, card_id);
    }

    //==============================================================================================================

    

}
