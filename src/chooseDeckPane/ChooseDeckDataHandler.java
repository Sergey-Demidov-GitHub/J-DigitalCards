package chooseDeckPane;

import dbUtils.DBComm;
import dbUtils.DBCommInterface;
import deckPackage.Deck;
import deckPackage.DeckForTableView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * This class handles the data base operations for the ChooseDeckController.
 */
public class ChooseDeckDataHandler {
    private static DBCommInterface dBCommunicator = new DBComm();
    private ObservableList<DeckForTableView> deckTableData;

    /**
     * Returns the id of selected deck.
     * @param selectedTableIndex index of selected table entry
     * @return id of selected deck
     */
    protected int getSelectedDeckIndex(int selectedTableIndex) {
        return deckTableData.get(selectedTableIndex).getId();
    }

    /**
     * Creates a list of deck names and the number of cards in each.
     * Data is formatted in the wrapper class DeckForTableView.
     * @return list of decks in DeckForTableView format
     */
    protected ObservableList<DeckForTableView> getDeckTableData() {
        Deck[] deckList = dBCommunicator.getDeckList();
        this.deckTableData = FXCollections.observableArrayList();

        for (int i = 0; i < deckList.length; i++) {
            deckTableData.add(new DeckForTableView(deckList[i]));
        }

        return deckTableData;
    }

    protected void addNewDeck(String newDeckName) {
        dBCommunicator.addDeck(newDeckName);
    }

    protected String getDeckName(int selectedDeckIndex){
        return dBCommunicator.getDeckName(selectedDeckIndex);
    }

    protected void removeDeck(int selectedDeckIndex) {
        dBCommunicator.removeDeck(selectedDeckIndex);
    }

    protected void updateDeckName(int selectedDeckIndex, String newDeckName) {
        dBCommunicator.updateDeckName(selectedDeckIndex, newDeckName);
    }
}
