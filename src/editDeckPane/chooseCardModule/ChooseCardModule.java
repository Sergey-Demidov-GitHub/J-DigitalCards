/*  Copyright (C) 2021 Sergey Demidov   */

package editDeckPane.chooseCardModule;

import cardPackage.Card;
import cardPackage.CardTableElement;
import editDeckPane.chooseCardModule.filter.ChooseCardFilterModule;
import dbUtils.DBComm;
import dbUtils.DBCommInterface;
import deckPackage.Deck;
import editDeckPane.EditDeckController;
import fancyTableView.FancyTableView;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ComboBox;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.StringConverter;
import main.MainController;

import java.io.IOException;

public class ChooseCardModule extends AnchorPane {
    private MainController mainController;
    private EditDeckController editDeckController;
    private static DBCommInterface dBCommunicator = new DBComm();

    private ObservableList<CardTableElement> deckTableData;
    private ObservableList<LiteWeightDeck> liteWeightDeck_List;
    private int selectedDeck_id = -1;
    private Deck currentlyLoadedDeck = null;

    @FXML
    private AnchorPane root;

    @FXML
    private AnchorPane fancyTableViewHolder;
    private FancyTableView fancyTableView;

    @FXML
    private ComboBox<LiteWeightDeck> chooseDeck_ComboBox;

    @FXML
    private AnchorPane filterHolder;
    private ChooseCardFilterModule filterModule;


    public ChooseCardModule(EditDeckController editDeckController) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("chooseCardModule.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
            this.mainController = MainController.getInstance();
            this.editDeckController = editDeckController;
            init();

            AnchorPane.setBottomAnchor(root, 0.0);
            AnchorPane.setTopAnchor(root, 0.0);
            AnchorPane.setLeftAnchor(root, 0.0);
            AnchorPane.setRightAnchor(root, 0.0);
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    private void init() {
        //loadSubModules();

        Deck[] DeckList = dBCommunicator.getDeckList();
        liteWeightDeck_List = FXCollections.observableArrayList();
        for (Deck deck: DeckList) {
            liteWeightDeck_List.add(new LiteWeightDeck(deck));
        }

        setupComboBox();
        setupTableView();
    }

    private void setupComboBox() {
        chooseDeck_ComboBox.setItems(liteWeightDeck_List);
        chooseDeck_ComboBox.setConverter(new StringConverter<LiteWeightDeck>() {
            @Override
            public String toString(LiteWeightDeck liteWeightDeck) {
                if (liteWeightDeck != null)
                    return liteWeightDeck.getDeckName();
                else
                    return "Choose Deck";
            }

            @Override
            public LiteWeightDeck fromString(String s) {
                return chooseDeck_ComboBox.getItems().stream().filter(ap ->
                        ap.getDeckName().equals(s)).findFirst().orElse(null);
            }
        });

        chooseDeck_ComboBox.valueProperty().addListener(new ChangeListener<LiteWeightDeck>() {
            @Override
            public void changed(ObservableValue<? extends LiteWeightDeck> observableValue, LiteWeightDeck oldValue, LiteWeightDeck newValue) {
                if (oldValue == null || oldValue != null && oldValue.getDeck_id() != newValue.getDeck_id()){
                    //System.out.println("new Deck chosen: " + newValue.getDeck_id() + " | " + newValue.getDeckName());
                    selectedDeck_id = newValue.getDeck_id();
                    loadDeck();
                    loadTableView();
                }
            }
        });
    }

    private void setupTableView() {
        fancyTableView = new FancyTableView(currentlyLoadedDeck);
        TableView tableView = fancyTableView.getTableView();
        tableView.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (event.isPrimaryButtonDown() && event.getClickCount() == 2) {
                    int index = tableView.getSelectionModel().getSelectedIndex();
                    int card_id = fancyTableView.getSelectedCardIndexFromTableIndex(index);
                    if (card_id != -1)
                        addCard(card_id);
                }
            }
        });
        fancyTableViewHolder.getChildren().add(fancyTableView);
        tableView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
    }

    @FXML
    public void onAction_add(ActionEvent event) {
        addSelectedCards();
    }

    private void loadDeck() {
        if (selectedDeck_id != -1)
            currentlyLoadedDeck = dBCommunicator.getDeck(selectedDeck_id);
        else
            System.err.println("[ERROR] Can't load Deck in ChooseCardModule.loadDeck()");

    }

    public void loadTableView() {
        fancyTableView.loadDeck(currentlyLoadedDeck);
    }

    private void loadSubModules() {
        filterModule = new ChooseCardFilterModule();
        filterModule.setChooseCardModule(this);
        filterHolder.getChildren().add(filterModule);
    }

    /**
     * Add one specific card
     * @param card_id
     */
    private void addCard(int card_id) {
        //TODO: addCard() form editDeck_Controller ...
        editDeckController.addExistingCard(currentlyLoadedDeck.getCardMap().get(card_id));
        clearSelection();
    }

    /**
     * Add all selected cards
     */
    private void addSelectedCards() {
        TableView tableView = fancyTableView.getTableView();
        ObservableList<Integer> selectedTableIndices  = tableView.getSelectionModel().getSelectedIndices();
        //int[] selectedCards = new int[selectedTableIndices.size()];
        for (Integer tableIndex: selectedTableIndices){
            Integer card_id = ((CardTableElement) tableView.getItems().get(tableIndex)).getCard_id();
            Card card = currentlyLoadedDeck.getCardMap().get(card_id);
            editDeckController.addExistingCard(card);
        }

        clearSelection();
    }

    private void clearSelection() {
        TableView tableView = fancyTableView.getTableView();
        tableView.getSelectionModel().clearSelection();
    }

}

class LiteWeightDeck {
    private int deck_id;
    private String deckName;
    public LiteWeightDeck(Deck deck){
        this.deck_id = deck.getId();
        this.deckName = deck.getName();
    }

    public int getDeck_id() {
        return deck_id;
    }

    public void setDeck_id(int deck_id) {
        this.deck_id = deck_id;
    }

    public String getDeckName() {
        return deckName;
    }

    public void setDeckName(String deckName) {
        this.deckName = deckName;
    }
}
