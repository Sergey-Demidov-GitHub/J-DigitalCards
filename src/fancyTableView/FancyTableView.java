package fancyTableView;

import cardPackage.Card;
import cardPackage.CardTableElement;
import deckPackage.Deck;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class FancyTableView extends AnchorPane {
    private Deck deck;
    private ObservableList<CardTableElement> deckTableData;

    private int selectedTableIndex = -1;
    private int selectedCardIndex = -1;
    private boolean orderJapEng = true;             // display score: jap -> eng

    @FXML
    private AnchorPane root;

    @FXML
    private TableView tableView;
    @FXML
    private TableColumn<CardTableElement, StringProperty> type_col;
    @FXML
    private TableColumn j1_col, j2_col, e1_col, e2_col, score_absolute_col, score_relative_col;
    @FXML
    private Label deckName_L;
    @FXML
    private Button switchButton;

    public FancyTableView(Deck deck) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("fancyTableView.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
            setDeck(deck);
            //this.deck = deck;
            init();
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    // Entry point after loading dependencies
    private void init() {
        AnchorPane.setBottomAnchor(root, 0.0);
        AnchorPane.setTopAnchor(root, 0.0);
        AnchorPane.setLeftAnchor(root, 0.0);
        AnchorPane.setRightAnchor(root, 0.0);
        //setDeckName_L(deck.getName());
        loadTableView();
        this.tableView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observableValue, Object o, Object t1) {
                if (tableView.getSelectionModel().getSelectedItem() != null) {
                    updateSelection();
                }
            }
        });
    }

    @FXML
    private void onAction_search() {

    }

    @FXML
    private void onAction_clear() {

    }

    @FXML
    private void onAction_switchLanguage() {
        toggleSoreOrder();
        loadTableView();
        changeTextOnSwitchButton();
    }

    // =================================================================

    public void loadTableView() {
        this.deckTableData = FXCollections.observableArrayList();

        if (deck != null){
            for (Card card : deck.getCardMap().values()) {
                deckTableData.add(new CardTableElement(card));
            }
        }


        this.type_col.setCellValueFactory(new PropertyValueFactory<CardTableElement, StringProperty>("cardType"));
        this.j1_col.setCellValueFactory(new PropertyValueFactory<CardTableElement, String>("j1"));
        this.j1_col.setCellFactory(TextFieldTableCell.forTableColumn());
        this.j2_col.setCellValueFactory(new PropertyValueFactory<CardTableElement, String>("j2"));
        this.j2_col.setCellFactory(TextFieldTableCell.forTableColumn());
        this.e1_col.setCellValueFactory(new PropertyValueFactory<CardTableElement, String>("e1"));
        this.e1_col.setCellFactory(TextFieldTableCell.forTableColumn());
        this.e2_col.setCellValueFactory(new PropertyValueFactory<CardTableElement, String>("e2"));
        this.e2_col.setCellFactory(TextFieldTableCell.forTableColumn());
        if (orderJapEng){
            this.score_absolute_col.setCellValueFactory(new PropertyValueFactory<CardTableElement, Integer>("score_absolute_j"));
            this.score_relative_col.setCellValueFactory(new PropertyValueFactory<CardTableElement, Integer>("score_relative_j"));
        } else {
            this.score_absolute_col.setCellValueFactory(new PropertyValueFactory<CardTableElement, Integer>("score_absolute_e"));
            this.score_relative_col.setCellValueFactory(new PropertyValueFactory<CardTableElement, Integer>("score_relative_e"));
        }

        this.tableView.setItems(deckTableData);

    }

    private void updateSelection() {
        this.selectedTableIndex = tableView.getSelectionModel().getSelectedIndex();
        this.selectedCardIndex = deckTableData.get(selectedTableIndex).getCard_id();
        //System.out.println("[INFO] selectedTableIndex: " + selectedTableIndex + " deck_id: " + selectedCardIndex);
    }

    // ================================================================= internal calls
    private void toggleSoreOrder(){
        orderJapEng = !orderJapEng;
    }

    private void changeTextOnSwitchButton() {
        if (orderJapEng)
            switchButton.setText("jap <-> eng");
        else
            switchButton.setText("eng <-> jap");
    }

    private void setDeckName_L(String deckName) {
        deckName_L.setText(deckName);
    }

    // ================================================================= external calls


    public TableView getTableView() {
        return tableView;
    }

    public boolean isEmpty() {
        return tableView.getItems().isEmpty();
    }

    public void addElementToTable(CardTableElement element) {
        tableView.getItems().add(element);
    }

    public void removeSelectedElementFromTable(){
        int tempIndex = selectedTableIndex;
        tableView.getItems().remove(selectedTableIndex);
        if (tempIndex != 0) {
            selectedTableIndex = tempIndex - 1;
            tableView.getSelectionModel().select(selectedTableIndex);
            tableView.getSelectionModel().focus(selectedTableIndex);
            selectedCardIndex = deckTableData.get(selectedTableIndex).getCard_id();
        }
    }

    public int getSelectedCardIndex() {
        return selectedCardIndex;
    }

    public int getSelectedCardIndexFromTableIndex(int tableIndex) {
        int index = -1;
        if (tableIndex != -1)
            index = deckTableData.get(tableIndex).getCard_id();
        return index;
    }

    public void setDeck(Deck deck) {
        this.deck = deck;
        if (deck == null)
            setDeckName_L("");
        else
            setDeckName_L(deck.getName());
    }

    public void loadDeck(Deck deck) {
        setDeck(deck);
        loadTableView();
    }
}
