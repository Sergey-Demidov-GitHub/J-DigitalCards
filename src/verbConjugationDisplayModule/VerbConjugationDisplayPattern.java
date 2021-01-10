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
    protected GridPane shortFormGrid;
    @FXML
    protected GridPane longFormGrid;
    @FXML
    protected GridPane taiGrid;
    @FXML
    protected GridPane teGrid;

    @FXML
    protected GridPane shortPotGrid;
    @FXML
    protected GridPane longPotGrid;
    @FXML
    protected GridPane potTeGrid;
    @FXML
    protected GridPane volGrid;

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
