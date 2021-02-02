/*  Copyright (C) 2021 Sergey Demidov   */

package verbConjugationDisplayModule;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import trainDeckPane.TrainDeckController;

import java.io.IOException;

public class VerbConjugationDisplayPattern extends AnchorPane {
    private TrainDeckController trainDeckController;

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

    public void setTrainDeckController(TrainDeckController trainDeckController){
        this.trainDeckController = trainDeckController;
    }

    /*  Grid filling pattern:
     *      node1   node2
     *      node3   node4
     */
    protected void setShortFormGrid(Node node1, Node node2, Node node3, Node node4) {
        set9erGrid(shortFormGrid, node1, node2, node3, node4);
    }

    protected void setLongFormGrid(Node node1, Node node2, Node node3, Node node4) {
        set9erGrid(longFormGrid, node1, node2, node3, node4);
    }

    protected void setTaiGrid(Node node1, Node node2, Node node3, Node node4) {
        set9erGrid(taiGrid, node1, node2, node3, node4);
    }

    protected void setShortPotGrid(Node node1, Node node2, Node node3, Node node4) {
        set9erGrid(shortPotGrid, node1, node2, node3, node4);
    }

    protected void setLongPotGrid(Node node1, Node node2, Node node3, Node node4) {
        set9erGrid(longPotGrid, node1, node2, node3, node4);
    }

    protected void setTeGrid(Node node) {
        teGrid.add(node, 1, 0);
    }

    protected void setPotTeGrid(Node node) {
        potTeGrid.add(node, 1, 0);
    }

    protected void setVolGrid(Node node1, Node node2) {
        volGrid.add(node1, 1, 0);
        volGrid.add(node2, 1, 1);
    }

    private void set9erGrid(GridPane grid, Node node1, Node node2, Node node3, Node node4) {
        grid.add(node1, 1, 1);
        grid.add(node2, 3, 1);
        grid.add(node3, 1, 2);
        grid.add(node4, 3, 2);
    }



}
