package verbConjugationDisplayModule;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import trainDeck_Stage.TrainDeck_Controller;

import java.io.IOException;

public class VerbConjugationDisplayModule extends AnchorPane {
    private TrainDeck_Controller trainDeckController;

    public VerbConjugationDisplayModule() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("verbConjugationDisplayModule.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    public void setTrainDeckController(TrainDeck_Controller trainDeckController){
        this.trainDeckController = trainDeckController;
    }



}
