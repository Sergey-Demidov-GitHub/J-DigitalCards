package verbConjugationDisplayModule;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
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

    /*  Grid filling pattern:
     *      node1   node2
     *      node3   node4
     */
    protected void setShortFormGrid(Node node1, Node node2, Node node3, Node node4) {
        shortFormGrid.add(node1, 1, 1);
        shortFormGrid.add(node2, 2, 1);
        shortFormGrid.add(node3, 1, 2);
        shortFormGrid.add(node4, 2, 2);
    }



}
