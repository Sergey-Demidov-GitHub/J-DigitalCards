/*  Copyright (C) 2021 Sergey Demidov   */

package chooseDeckPane;

import deckPackage.DeckForTableView;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class ChooseDeckController implements Initializable {
    @FXML
    private AnchorPane root;

    @FXML
    private TableView tableView;
    @FXML
    private TableColumn deckName_col;
    @FXML
    private TableColumn wordCount_col;

    @FXML
    private Button refresh_B;

    private ChooseDeckDataHandler dataHandler;
    private ChooseDeckDelegator delegator;

    private int selectedTableIndex = -1;
    private int selectedDeckIndex = -1;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.dataHandler = new ChooseDeckDataHandler();
        this.delegator = new ChooseDeckDelegator();

        this.tableView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observableValue, Object o, Object t1) {
                if (tableView.getSelectionModel().getSelectedItem() != null) {
                    updateSelection();
                }
            }
        });

        adjustUI();
        loadTableView();
    }

    @FXML
    public void onAction_back() {
        delegator.switchToStartPane();
    }

    @FXML
    public void onAction_train() {
        if (selectedTableIndex != -1)
            delegator.switchToTrainPane(tableView, selectedDeckIndex);
    }

    @FXML
    public void onAction_edit() {
        if (selectedTableIndex != -1)
            delegator.switchToEditPane(selectedDeckIndex);
    }

    @FXML
    public void onAction_new() {
        dataHandler.addNewDeck("new Deck");
        loadTableView();
    }

    @FXML
    public void onAction_remove() {
        if (selectedTableIndex != -1 && selectedDeckIndex != -1) {
            if (alertConfirmedDeletion()) {
                dataHandler.removeDeck(selectedDeckIndex);
                loadTableView();
            }
        }
    }

    @FXML
    public void onAction_refresh() {
        loadTableView();
    }

    @FXML
    public void onEditCommit(TableColumn.CellEditEvent<DeckForTableView, String> event) {
        String newDeckName = event.getNewValue();
        dataHandler.updateDeckName(selectedDeckIndex, newDeckName);
        loadTableView();
    }

    //==================================================================================================================

    /**
     * Loads the tableView with data from the database.
     */
    private void loadTableView() {
        ObservableList<DeckForTableView> deckTableData = dataHandler.getDeckTableData();

        this.deckName_col.setCellValueFactory(new PropertyValueFactory<DeckForTableView, String>("name"));
        this.deckName_col.setCellFactory(TextFieldTableCell.forTableColumn());
        this.wordCount_col.setCellValueFactory(new PropertyValueFactory<DeckForTableView, Integer>("wordCount"));

        this.tableView.setItems(deckTableData);
        this.tableView.setEditable(true);
    }

    private void updateSelection() {
        this.selectedTableIndex = tableView.getSelectionModel().getSelectedIndex();
        this.selectedDeckIndex = dataHandler.getSelectedDeckIndex(selectedTableIndex);
    }

    /**
     * Creates alert that asks to confirm deletion of selected deck
     * @return if confirmation was received
     */
    private boolean alertConfirmedDeletion() {
        String deckName = dataHandler.getDeckName(selectedDeckIndex);
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION,
                "Do you really want to delete " +
                        deckName + "? ",
                ButtonType.YES,
                ButtonType.NO
        );
        alert.setHeaderText(null);
        ButtonType result = alert.showAndWait().orElse(ButtonType.NO);

        return ButtonType.YES.equals(result);
    }


    private void adjustUI() {
        root.getStylesheets().add("main.css");

        AnchorPane.setBottomAnchor(root, 0.0);
        AnchorPane.setTopAnchor(root, 0.0);
        AnchorPane.setLeftAnchor(root, 0.0);
        AnchorPane.setRightAnchor(root, 0.0);

        refresh_B.setVisible(false);
    }

}
