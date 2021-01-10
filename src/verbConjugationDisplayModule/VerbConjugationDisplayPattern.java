package verbConjugationDisplayModule;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import trainDeck_Stage.TrainDeck_Controller;

import java.io.IOException;

public class VerbConjugationDisplayPattern extends AnchorPane {
    private TrainDeck_Controller trainDeckController;

    @FXML
    private GridPane shortFormGrid;
    @FXML
    private GridPane longFormGrid;
    @FXML
    private GridPane taiGrid;
    @FXML
    private GridPane teGrid;

    @FXML
    private GridPane shortPotGrid;
    @FXML
    private GridPane longPotGrid;
    @FXML
    private GridPane potTeGrid;
    @FXML
    private GridPane volGrid;

    public VerbConjugationDisplayPattern() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("verbConjugationDisplayPattern.fxml"));
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
