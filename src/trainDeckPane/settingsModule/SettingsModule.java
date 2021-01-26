package trainDeckPane.settingsModule;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import main.MainController;
import trainConfig.TrainConfig;
import trainDeckPane.TrainDeckController;
import verbConjugationDisplayModule.VerbConjugationSettingsModule;

import java.io.IOException;

public class SettingsModule extends AnchorPane {
    private Stage stage;
    private MainController mainController;
    private TrainDeckController trainDeckController;
    private TrainConfig trainConfig;

    @FXML
    private AnchorPane root;
    @FXML
    private AnchorPane verbsPane;
    @FXML
    private AnchorPane adjectivesPane;

    private VerbConjugationSettingsModule verbConjugationSettingsModule;

    public SettingsModule(Stage stage, MainController mainController, TrainDeckController trainDeckController) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("settingsModule.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
            root.setStyle("-fx-background-color: #fff2c2;");
            this.stage = stage;
            this.mainController = mainController;
            this.trainDeckController = trainDeckController;
            this.trainConfig = trainDeckController.getTrainConfig();
            loadVerbSettings();
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    @FXML
    private void onAction_apply() {
        trainConfig.setVerbConfig(verbConjugationSettingsModule.getTempConfig());
        trainDeckController.reloadConfiguration();
        stage.close();

    }

    @FXML void onAction_cancel() {
        stage.close();
    }


    private void loadVerbSettings() {
        verbConjugationSettingsModule = new VerbConjugationSettingsModule(trainConfig.getVerbConfig());
        verbsPane.getChildren().setAll(verbConjugationSettingsModule);
    }


}
