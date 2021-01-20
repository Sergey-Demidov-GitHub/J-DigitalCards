package trainDeck_Stage.settingsModule;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import main.MainController;
import trainConfig.TrainConfig;
import trainDeck_Stage.TrainDeck_Controller;
import verbConjugationDisplayModule.VerbConjugationSettingsModule;

import java.io.IOException;

public class SettingsModule extends AnchorPane {
    private Stage stage;
    private MainController mainController;
    private TrainDeck_Controller trainDeckController;
    private TrainConfig trainConfig;

    @FXML
    private AnchorPane root;
    @FXML
    private AnchorPane verbsPane;
    @FXML
    private AnchorPane adjectivesPane;

    private VerbConjugationSettingsModule verbConjugationSettingsModule;

    public SettingsModule(Stage stage, MainController mainController, TrainDeck_Controller trainDeckController) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("settingsModule.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
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
        trainConfig.getVerbConfig().getConfigMap().putAll(verbConjugationSettingsModule.getTempConfigMap());
        trainDeckController.reloadConfiguration();
        stage.close();

    }

    @FXML void onAction_cancel() {
        trainConfig.getVerbConfig().getConfigMap().putAll(verbConjugationSettingsModule.getOldConfigMap());
        trainDeckController.reloadConfiguration();
        stage.close();
    }


    private void loadVerbSettings() {
        verbConjugationSettingsModule = new VerbConjugationSettingsModule(trainConfig.getVerbConfig());
        verbsPane.getChildren().setAll(verbConjugationSettingsModule);
    }


}
