/*  Copyright (C) 2021 Sergey Demidov   */

package editDeckPane;

import cardPackage.Card;
import dbUtils.DBComm;
import dbUtils.DBCommInterface;
import editDeckPane.chooseCardModule.ChooseCardModule;
import editDeckPane.editCardModule.EditCardModule;
import fancyTableView.FancyTableView;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class EditDeckController implements Initializable {
    private static DBCommInterface dBCommunicator = new DBComm();
    private EditDeckDelegator delegator;
    private EditDeckDataHandler dataHandler;
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
        delegator = new EditDeckDelegator(this);
        dataHandler = new EditDeckDataHandler(this);
        setupSubModules();
        adjustUI();
    }

    @FXML
    public void onAction_back() {
        if (pendingChanges_b) {
            delegator.alertUnsavedChanges();
        } else {
            delegator.switchToChooseDeck();
        }
    }

    @FXML
    public void onAction_save() {
        pendingChanges_b = false;
        dataHandler.applySessionChanges();
        reloadFancyTableView();
    }

    @FXML void onAction_delete() {
        dataHandler.removeSelection(fancyTableView);
        reloadFancyTableView();
    }

    //=================================================================================================================
    // Wrapper function -> also used in submodule
    public void addExistingCard(Card card) {
         dataHandler.addExistingCard(card);
    }

    // Wrapper function -> also used in submodule
    public void processCardChange(Card card){
        dataHandler.processCardChange(card);
    }

    protected void reloadFancyTableView() {
        fancyTableView.setDeck(dataHandler.getCurrentlyCachedDeck());
        fancyTableView.loadTableView();
    }

    private void setupSubModules() {
        fancyTableView = new FancyTableView(dataHandler.getCurrentlyCachedDeck());
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

        editCardModule = new EditCardModule(this);
        editCardModuleHolder.getChildren().add(editCardModule);
        editCardModuleHolder.getStyleClass().add("clean");

        chooseCardModule = new ChooseCardModule(this);
        chooseCardModuleHolder.getChildren().add(chooseCardModule);
        chooseCardModuleHolder.getStyleClass().add("clean");
    }

    public void editCardInEditCardModule(int card_id) {
        editCardModule.editCardInForm(card_id);
    }

    public void switchToEditCardModuleTab(){
        tabPane.getSelectionModel().select(0);
    }


    protected boolean hasPendingChanges() {
        return pendingChanges_b;
    }

    protected void setPendingChanges(boolean pendingChanges_b) {
        this.pendingChanges_b = pendingChanges_b;
    }

    private void adjustUI() {
        AnchorPane.setBottomAnchor(root, 0.0);
        AnchorPane.setTopAnchor(root, 0.0);
        AnchorPane.setLeftAnchor(root, 0.0);
        AnchorPane.setRightAnchor(root, 0.0);
        root.getStylesheets().add("main.css");
    }
}
