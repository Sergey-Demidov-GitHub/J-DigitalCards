/*  Copyright (C) 2021 Sergey Demidov   */

package main;

import chooseDeckPane.ChooseDeckController;
import dbUtils.DBComm;
import dbUtils.DBCommInterface;
import deckPackage.Deck;
import editDeckPane.EditDeckController;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import misc.Misc;
import startApplicationPane.StartStageController;
import trainDeckPane.TrainDeckController;

import java.io.IOException;

public class MainController {
    private static MainController instance;

    private FXMLLoader loader;
    private Stage stage;
    private AnchorPane root;
    private Session session;
    private DBCommInterface DBCommunicator;

    // stage can be null, but it would be better to use the other getInstance() function
    public static MainController getInstance(Stage stage) {
        if (instance == null && stage != null) {
            instance = new MainController(stage);
            instance.loadFirstPane();
        }
        return instance;
    }

    public static MainController getInstance() {
        if (instance == null) {
            System.err.println("[ERROR] 'MainController.getInstance()': requested instance is not initialized");
        }
        return instance;
    }

    private MainController(Stage stage) {
        try {
            this.stage = stage;
            root = new AnchorPane();
            Scene scene = new Scene(root);
            root.getStyleClass().add("clean");
            //stage.setMaximized(true);
            stage.setWidth(1920);
            stage.setHeight(1080);
            stage.setResizable(false);
            stage.setScene(scene);


            this.session = null;
            this.DBCommunicator = new DBComm();
        } catch (Exception exception){
            exception.printStackTrace();
        }

    }

    private void initCloseRequestHandle() {
        stage.setOnCloseRequest(windowEvent -> {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setHeaderText(null);
            alert.setGraphic(null);
            alert.setTitle("Exit Application");
            alert.setContentText("Do you really want to exit the application? \nUnsaved changes will be lost!");
            ButtonType yes_BT = new ButtonType("Yes", ButtonBar.ButtonData.YES);
            ButtonType no_BT = new ButtonType("No", ButtonBar.ButtonData.NO);
            alert.getButtonTypes().setAll(yes_BT, no_BT);
            alert.showAndWait().ifPresent(type -> {
                if (type != yes_BT)
                    windowEvent.consume();
            });
        });
    }

    // ================================================================= load GUI
    private void load_Pane(AnchorPane pane) {
        root.getChildren().setAll(pane);
    }


    private void loadFirstPane () {
        load_Pane(getNext_StartPane());
        initCloseRequestHandle();
    }


    private AnchorPane getNext_StartPane() {
        try {
            loader = new FXMLLoader();
            AnchorPane pane = loader.load(getClass().getClassLoader().getResource("startApplicationPane/startPane.fxml").openStream());
            StartStageController startStageController = (StartStageController) loader.getController();
            stage.setTitle("Start");
            stage.show();
            return pane;
        } catch (IOException exception) {
            exception.printStackTrace();
            System.err.println("[ERROR] " + exception);
            return null;
        }
    }

    private AnchorPane getNext_ChooseDeckPane() {
        try {
            loader = new FXMLLoader();
            AnchorPane pane = loader.load(getClass().getClassLoader().getResource("chooseDeckPane/chooseDeckPane.fxml").openStream());
            ChooseDeckController chooseDeck_controller = (ChooseDeckController) loader.getController();
            stage.setTitle("Choose Deck Page");
            stage.show();
            return pane;
        } catch (IOException exception) {
            exception.printStackTrace();
            System.err.println("[ERROR] " + exception);
            return null;
        }
    }

    private AnchorPane getNext_EditDeckPane() {
        try {
            loader = new FXMLLoader();
            AnchorPane pane = loader.load(getClass().getClassLoader().getResource("editDeckPane/editDeckPane.fxml").openStream());
            EditDeckController editDeck_controller = (EditDeckController) loader.getController();
            stage.setTitle("Edit Deck Page");
            stage.show();
            return pane;
        } catch (IOException exception) {
            exception.printStackTrace();
            return null;
        }
    }

    private AnchorPane getNext_TrainDeckPane() {
        try {
            loader = new FXMLLoader();
            AnchorPane pane = loader.load(getClass().getClassLoader().getResource("trainDeckPane/trainDeckPane.fxml").openStream());
            TrainDeckController trainDeck_controller = (TrainDeckController) loader.getController();
            stage.setTitle("Train Deck Page");
            stage.show();
            return pane;
        } catch (IOException exception) {
            exception.printStackTrace();
            return null;
        }
    }

    private void setupSizeListener(AnchorPane localRoot){
        localRoot.setPrefWidth(root.getWidth());
        localRoot.setPrefHeight(root.getHeight());
        /*
        root.heightProperty().addListener( (obs, oldValue, newValue) -> {
            localRoot.setPrefHeight(newValue.doubleValue());
        });

        root.widthProperty().addListener( (obs, oldValue, newValue) -> {
            localRoot.setPrefWidth(newValue.doubleValue());
        });*/
    }
    // ================================================================= transitions

    public void transition_Start_ChooseDeck(){
        AnchorPane nextPane = getNext_ChooseDeckPane();
        AnchorPane currentPane = (AnchorPane) root.getChildren().get(0);
        animation_moveIn(currentPane, nextPane);
    }

    public void transition_ChooseDeck_Start(){
        AnchorPane nextPane = getNext_StartPane();
        AnchorPane currentPane = (AnchorPane) root.getChildren().get(0);
        animation_moveOut(nextPane, currentPane);
    }

    public void transition_ChooseDeck_EditDeck() {
        AnchorPane nextPane = getNext_EditDeckPane();
        AnchorPane currentPane = (AnchorPane) root.getChildren().get(0);
        animation_moveIn(currentPane, nextPane);
    }


    public void transition_EditDeck_ChooseDeck() {
        AnchorPane nextPane = getNext_ChooseDeckPane();
        AnchorPane currentPane = (AnchorPane) root.getChildren().get(0);
        animation_moveOut(nextPane, currentPane);
    }

    public void transition_ChooseDeck_TrainDeck() {
        AnchorPane nextPane = getNext_TrainDeckPane();
        AnchorPane currentPane = (AnchorPane) root.getChildren().get(0);
        animation_moveIn(currentPane, nextPane);
    }

    public void transition_TrainDeck_ChooseDeck() {
        AnchorPane nextPane = getNext_ChooseDeckPane();
        AnchorPane currentPane = (AnchorPane) root.getChildren().get(0);
        animation_moveOut(nextPane, currentPane);
    }

    private void animation_moveIn(AnchorPane static_P, AnchorPane moving_P) {
        moving_P.translateXProperty().set(stage.getScene().getWidth());
        root.getChildren().add(moving_P);

        Timeline timeline = new Timeline();
        KeyValue kv = new KeyValue(moving_P.translateXProperty(), 0, Interpolator.EASE_IN);
        KeyFrame kf = new KeyFrame(Duration.millis(500), kv);
        timeline.getKeyFrames().add(kf);
        timeline.setOnFinished(event -> {
            root.getChildren().remove(static_P);
        });
        timeline.play();
    }

    private void animation_moveOut(AnchorPane static_P, AnchorPane moving_P) {
        moving_P.translateXProperty().set(0);
        root.getChildren().setAll(static_P, moving_P);

        Timeline timeline = new Timeline();
        KeyValue kv = new KeyValue(moving_P.translateXProperty(), Misc.WINDOW_WIDTH, Interpolator.EASE_IN);
        KeyFrame kf = new KeyFrame(Duration.millis(500), kv);
        timeline.getKeyFrames().add(kf);
        timeline.setOnFinished(event -> {
            root.getChildren().remove(moving_P);
        });
        timeline.play();
    }

    public Stage getStage() {
        return stage;
    }













    // ================================================================= Session

    public Session getSession() {
        return session;
    }

    public void setSessionFromDeck(Deck deck) {
        this.session = new Session(deck);
    }

    /**
     * Updates existing session or creates new if it doesn't exist.
     * @param newDeck_id new deck's id if not already loaded
     */
    public void updateSession(int newDeck_id) {
        if (session != null) {
            // update
            int deck_id = session.getDeck().getId();
            if(deck_id == newDeck_id)
                setSessionFromDeck(DBCommunicator.getDeck(deck_id));        // TODO: optimize session reload
            else
                setSessionFromDeck(DBCommunicator.getDeck(newDeck_id));

        } else {
            // new session
            setSessionFromDeck(DBCommunicator.getDeck(newDeck_id));
        }
    }



}
