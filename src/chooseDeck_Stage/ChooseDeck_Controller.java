package chooseDeck_Stage;

import dbUtils.DBComm;
import dbUtils.DBCommInterface;
import deckPackage.Deck;
import deckPackage.DeckForTableView;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.AnchorPane;
import main.MainController;

import java.net.URL;
import java.util.ResourceBundle;

public class ChooseDeck_Controller implements Initializable {
    @FXML
    private AnchorPane root;

    @FXML
    private TableView tableView;
    @FXML
    private TableColumn deckName_col;
    @FXML
    private TableColumn wordCount_col;

    private static DBCommInterface dBCommunicator = new DBComm();
    private ObservableList<DeckForTableView> deckTableData;
    private int selectedTableIndex = -1;
    private int selectedDeckIndex = -1;

    private MainController mainController;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        root.getStylesheets().add("main.css");
        //root.getStyleClass().add("clean-effect");

        this.tableView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observableValue, Object o, Object t1) {
                if (tableView.getSelectionModel().getSelectedItem() != null) {
                    updateSelection();
                }
            }
        });
        AnchorPane.setBottomAnchor(root, 0.0);
        AnchorPane.setTopAnchor(root, 0.0);
        AnchorPane.setLeftAnchor(root, 0.0);
        AnchorPane.setRightAnchor(root, 0.0);

        loadTableView();
    }

    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }

    @FXML
    public void onAction_back(ActionEvent event) {
        mainController.transition_ChooseDeck_Start();
    }

    @FXML
    public void onAction_train(ActionEvent event) {
        switchToTrainPane();
    }

    @FXML
    public void onAction_edit(ActionEvent event) {
        switchToEditPane();
    }

    @FXML
    public void onAction_new(ActionEvent event) {
        addNewDeck();
    }

    @FXML
    public void onAction_remove(ActionEvent event) {
        if (selectedTableIndex != -1 && selectedDeckIndex != -1) {
            String deckName = dBCommunicator.getDeckName(selectedDeckIndex);
            //MessageModule m = new MessageModule("You are about to delete " + deckName + ". ");
            //m.onAction_cancel();
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Do you really want to delete " + deckName + "? ", ButtonType.YES, ButtonType.NO);
            alert.setHeaderText("headerText");
            ButtonType result = alert.showAndWait().orElse(ButtonType.NO);
            if (ButtonType.YES.equals(result)) {
                removeDeck();
            }

        }
    }

    @FXML
    public void onAction_refresh(ActionEvent event) {
        loadTableView();
    }

    @FXML
    public void onEditCommit(TableColumn.CellEditEvent<DeckForTableView, String> event) {
        String newDeckName = event.getNewValue();
        dBCommunicator.updateDeckName(selectedDeckIndex, newDeckName);
        loadTableView();
    }





    private void loadTableView() {
        Deck[] deckList = dBCommunicator.getDeckList();
        this.deckTableData = FXCollections.observableArrayList();

        for (int i = 0; i < deckList.length; i++) {
            deckTableData.add(new DeckForTableView(deckList[i]));
        }


        this.deckName_col.setCellValueFactory(new PropertyValueFactory<DeckForTableView, String>("name"));
        this.deckName_col.setCellFactory(TextFieldTableCell.forTableColumn());
        this.wordCount_col.setCellValueFactory(new PropertyValueFactory<DeckForTableView, String>("wordCount"));

        this.tableView.setItems(deckTableData);
        this.tableView.setEditable(true);
        //System.out.println("[INFO] 'loadTableView' triggered");

    }

    private void updateSelection() {
        this.selectedTableIndex = tableView.getSelectionModel().getSelectedIndex();
        this.selectedDeckIndex = deckTableData.get(selectedTableIndex).getId();
        //System.out.println("[INFO] selectedTableIndex: " + selectedTableIndex + " deck_id: " + selectedDeckIndex);
    }

    private void addNewDeck() {
        dBCommunicator.addDeck("testDeck_gen_2");
        loadTableView();
    }

    private void removeDeck() {
        if (selectedTableIndex != -1 && selectedDeckIndex != -1) {
            //tableView.getItems().remove(selectedTableIndex);
            dBCommunicator.removeDeck(selectedDeckIndex);
            loadTableView();

            //selectedTableIndex = -1;
            //selectedDeckIndex = -1;
        }
    }

    private void switchToTrainPane(){
        if (selectedTableIndex != -1) {
            mainController.updateSession(selectedDeckIndex);
            this.mainController.transition_ChooseDeck_TrainDeck();
        }
    }

    private void switchToEditPane(){
        if (selectedTableIndex != -1) {
            mainController.updateSession(selectedDeckIndex);
            this.mainController.transition_ChooseDeck_EditDeck();
        }
    }

}
