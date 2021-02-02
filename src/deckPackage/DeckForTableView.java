/*  Copyright (C) 2021 Sergey Demidov   */

package deckPackage;

import dbUtils.DBComm;
import dbUtils.DBCommInterface;

public class DeckForTableView extends Deck{
    private int wordCount;
    private static DBCommInterface dBCommunicator = new DBComm();

    public DeckForTableView() {
        super(-1, "temp deck");
    }

    public DeckForTableView(Deck deck) {
        super(deck);
        this.wordCount = dBCommunicator.getCardCountInDeck(deck.getId());

    }

    public int getWordCount() {
        return wordCount;
    }

    public void setWordCount(int wordCount) {
        this.wordCount = wordCount;
    }
}
