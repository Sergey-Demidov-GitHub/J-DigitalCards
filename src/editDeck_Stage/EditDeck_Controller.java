package editDeck_Stage;

import cardPackage.BasicCard;
import cardPackage.Card;
import cardPackage.CardTableElement;
import chooseCardModule.ChooseCardModule;
import dbUtils.DBComm;
import dbUtils.DBCommInterface;
import editCardModule.EditCardModule;
import fancyTableView.FancyTableView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import main.MainController;

import java.net.URL;
import java.util.ResourceBundle;

public class EditDeck_Controller implements Initializable {
    private static DBCommInterface dBCommunicator = new DBComm();
    private MainController mainController;
    private Boolean pendingChanges_b = false;

    @FXML
    private AnchorPane root;

    @FXML
    private TabPane tabPane;

    @FXML
    private AnchorPane fancyTableViewHolder;
    private FancyTableView fancyTableView;

    @FXML
    private AnchorPane editCardModuleHolder;
    private EditCardModule editCardModule;

    @FXML
    private AnchorPane chooseCardModuleHolder;
    private ChooseCardModule chooseCardModule;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        AnchorPane.setBottomAnchor(root, 0.0);
        AnchorPane.setTopAnchor(root, 0.0);
        AnchorPane.setLeftAnchor(root, 0.0);
        AnchorPane.setRightAnchor(root, 0.0);
        root.getStylesheets().add("main.css");
    }

    @FXML
    public void onAction_back(ActionEvent event) {
        if (pendingChanges_b) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setHeaderText(null);
            alert.setGraphic(null);
            alert.setTitle("Unsaved Changes");
            alert.setContentText("You have unsaved changes! \nDo you want to save them before leaving?");
            ButtonType save_BT = new ButtonType("Save and leave", ButtonBar.ButtonData.YES);
            ButtonType leave_BT = new ButtonType("Leave without saving", ButtonBar.ButtonData.NO);
            ButtonType cancel_BT = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);
            alert.getButtonTypes().setAll(save_BT, leave_BT, cancel_BT);
            alert.showAndWait().ifPresent(type -> {
                if (type == save_BT) {
                    onAction_save();
                    mainController.transition_EditDeck_ChooseDeck();
                } else if (type == leave_BT) {
                    mainController.transition_EditDeck_ChooseDeck();
                } else {

                }
            });
        } else {
            mainController.transition_EditDeck_ChooseDeck();
        }
    }

    @FXML
    public void onAction_save() {
        pendingChanges_b = false;
        mainController.getSession().applySessionChanges();
        onAction_refresh(new ActionEvent());
    }

    @FXML void onAction_delete(ActionEvent event) {
        removeSelection();
        onAction_refresh(new ActionEvent());
    }

    @FXML // deprecated
    public void onAction_new(ActionEvent event) {
        addNewCard();
        onAction_refresh(new ActionEvent());
    }

    @FXML
    public void onAction_refresh(ActionEvent event) {
        reloadFancyTableView();
    }

    //=================================================================================================================
    public void addExistingCard(Card card) {
        boolean isNew = !mainController.getSession().getDeck().checkCardInDeckById(card.getId());
        if(isNew) {
            mainController.getSession().addNewRelation(card.getId());
            mainController.getSession().addNewCardToDeck(card);
        } else {
            System.out.println("[INFO] Card is already in deck!");
        }

        reloadFancyTableView();
        pendingChanges_b = true;
    }

    // deprecated
    private void addNewCard() {
        int card_id = mainController.getSession().addNewIds();
        BasicCard card = new BasicCard(card_id, "j1", "", "e1", "");
        mainController.getSession().addNewCardToDeck(card);

        CardTableElement cardTableElement = new CardTableElement(card);
        fancyTableView.addElementToTable(cardTableElement);

        System.out.println(mainController.getSession().getDeck().toString());

    }

    private void removeSelection() {
        int selectedCardIndex = fancyTableView.getSelectedCardIndex();
        if (!fancyTableView.isEmpty() && selectedCardIndex != -1) {
            this.mainController.getSession().addDeletedIds(selectedCardIndex);
            this.mainController.getSession().getDeck().removeCard(selectedCardIndex);
            fancyTableView.removeSelectedElementFromTable();
            pendingChanges_b = true;
        }
    }

    public void setMainController(MainController mainController) {
        this.mainController = mainController;
        setupSubModules();
    }

    private void reloadFancyTableView() {
        fancyTableView.setDeck(this.mainController.getSession().getDeck());
        fancyTableView.loadTableView();
    }

    private void setupSubModules() {
        fancyTableView = new FancyTableView(this.mainController.getSession().getDeck());
        TableView tableView = fancyTableView.getTableView();
        tableView.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (event.isPrimaryButtonDown() && event.getClickCount() == 2) {
                    int index = tableView.getSelectionModel().getSelectedIndex();
                    int card_id = fancyTableView.getSelectedCardIndexFromTableIndex(index);
                    if (card_id != -1) {
                        editCardInEditCardModule(card_id);
                        switchToEditCardModuleTab();
                    }
                }
            }
        });
        fancyTableViewHolder.getChildren().add(fancyTableView);

        editCardModule = new EditCardModule();
        editCardModule.setMainController(mainController);
        editCardModule.setEditDeckController(this);
        editCardModuleHolder.getChildren().add(editCardModule);
        editCardModuleHolder.getStyleClass().add("clean");

        chooseCardModule = new ChooseCardModule();
        chooseCardModule.setMainController(mainController);
        chooseCardModule.setEditDeckController(this);
        chooseCardModuleHolder.getChildren().add(chooseCardModule);
        chooseCardModuleHolder.getStyleClass().add("clean");
    }

    public void editCardInEditCardModule(int card_id) {
        editCardModule.editCardInForm(card_id);
    }

    public void switchToEditCardModuleTab(){
        tabPane.getSelectionModel().select(0);
    }


    public void processCardChange(Card card){
        boolean isNew = !mainController.getSession().getDeck().checkCardInDeckById(card.getId());

        if (!isNew) {
            this.mainController.getSession().addChangedIds(card.getId());
            System.out.println("[TRIGGER] EditDeck_Controller.processCardChange()");
        }

        mainController.getSession().addNewCardToDeck(card);     // overrides if card exists


        onAction_refresh(new ActionEvent());
        pendingChanges_b = true;
    }
}
