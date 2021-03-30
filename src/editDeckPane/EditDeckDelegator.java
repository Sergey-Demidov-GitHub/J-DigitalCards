package editDeckPane;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import main.MainController;

/**
 * Class that handles switching to other panes[Views].
 */
public class EditDeckDelegator {
    private MainController mainController;
    private EditDeckController controller;

    EditDeckDelegator(EditDeckController controller) {
        mainController = MainController.getInstance();
        this.controller = controller;
    }

    /**
     * Exit EditDeckView after asking what to do with unsaved changes.
     */
    protected void alertUnsavedChanges() {
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
                controller.onAction_save();
                mainController.transition_EditDeck_ChooseDeck();
            } else if (type == leave_BT) {
                mainController.transition_EditDeck_ChooseDeck();
            } else {

            }
        });
    }

    protected void switchToChooseDeck(){
        mainController.transition_EditDeck_ChooseDeck();
    }

}
