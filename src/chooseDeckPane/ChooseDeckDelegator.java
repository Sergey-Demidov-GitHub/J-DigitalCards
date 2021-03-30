package chooseDeckPane;

import deckPackage.DeckForTableView;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableView;
import main.MainController;

/**
 * Class that handles switching to other panes[Views].
 */
public class ChooseDeckDelegator {
    MainController mainController;

    public ChooseDeckDelegator() {
        mainController = MainController.getInstance();
    }


    protected void switchToStartPane() {
        mainController.transition_ChooseDeck_Start();
    }

    protected void switchToTrainPane(TableView tableView, int selectedDeckIndex) {
            Integer wordCount = ((DeckForTableView) tableView.getSelectionModel().getSelectedItem()).getWordCount();
            if (wordCount > 0) {
                mainController.updateSession(selectedDeckIndex);
                this.mainController.transition_ChooseDeck_TrainDeck();
            } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText(null);
                alert.setGraphic(null);
                alert.setContentText("Deck is empty!");
                ButtonType ok_BT = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
                alert.getButtonTypes().setAll(ok_BT);
                alert.showAndWait().ifPresent(buttonType -> {

                });
            }
    }

    protected void switchToEditPane(int selectedDeckIndex){
        mainController.updateSession(selectedDeckIndex);
        this.mainController.transition_ChooseDeck_EditDeck();

    }
}
