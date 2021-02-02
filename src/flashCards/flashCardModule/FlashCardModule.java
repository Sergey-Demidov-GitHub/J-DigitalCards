/*  Copyright (C) 2021 Sergey Demidov   */

package flashCards.flashCardModule;

import cardPackage.*;
import flashCards.FlashCard;
import main.MainController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

import java.io.IOException;

public class FlashCardModule extends AnchorPane {
    private MainController mainController;
    private FlashCard flashCard;
    private Card card;

    private boolean reverse_b;       //TODO: reversed can't be handled in this class
    private boolean locked_b;          //TODO: lock can't be handled in this class

    @FXML
    private AnchorPane root;

    @FXML
    private Pane left_card;
    @FXML
    private Pane right_card;
    @FXML
    private HBox hBox;

    private final int HBOX_SPACING = 50;
    public static int cardDisplayHeight = 528; // TODO: find actual size for flexibility
    public static int cardDisplayWidth = 1350; // TODO: find actual size for flexibility

    public FlashCardModule(MainController mainController) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("flashCardModule.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
            AnchorPane.setBottomAnchor(root, 0.0);
            AnchorPane.setTopAnchor(root, 0.0);
            AnchorPane.setLeftAnchor(root, 0.0);
            AnchorPane.setRightAnchor(root, 0.0);
            hBox.getStylesheets().add("main.css");
            this.mainController = mainController;
            this.card = mainController.getSession().getCurrentCard();
            displayCard();
        } catch (IOException exception) {
            exception.printStackTrace();
        }

    }

    public FlashCardModule(MainController mainController, Card card) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("flashCardModule.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
            AnchorPane.setBottomAnchor(root, 0.0);
            AnchorPane.setTopAnchor(root, 0.0);
            AnchorPane.setLeftAnchor(root, 0.0);
            AnchorPane.setRightAnchor(root, 0.0);
            hBox.getStylesheets().add("main.css");
            this.mainController = mainController;
            this.card = card;
            displayCard();
        } catch (IOException exception) {
            exception.printStackTrace();
        }

    }

    private void displayCard() {
        if (card instanceof NounCard) {
            flashCard = NounCard.getNounCardGui((NounCard) card);
        }
        if (card instanceof VerbCard) {
            flashCard = VerbCard.getVerbCardGui((VerbCard) card);
        }
        if (card instanceof AdjectiveCard) {
            flashCard = AdjectiveCard.getAdjectiveCardGui((AdjectiveCard) card);
        }
        if (card instanceof OtherCard) {
            flashCard = OtherCard.getOtherCardGui((OtherCard) card);
        }
        if (card instanceof KanjiCard) {
            flashCard = KanjiCard.getKanjiCardGui((KanjiCard) card);
        }

        hBox.setSpacing(HBOX_SPACING);
        hBox.getChildren().clear();
        this.left_card = new Pane();
        this.right_card = new Pane();

        if (reverse_b) {
            this.left_card = flashCard.getRight_card();
            this.right_card = flashCard.getLeft_card();
        } else {
            this.left_card = flashCard.getLeft_card();
            this.right_card = flashCard.getRight_card();
        }




        hBox.getChildren().addAll(left_card, right_card);
        this.left_card.setVisible(true);
        this.right_card.setVisible(locked_b);
    }

    public void toggleLock() {
        locked_b = !locked_b;
        this.right_card.setVisible(locked_b);
    }

    public void setLocked(boolean locked_b) {
        this.locked_b = locked_b;
    }

    public void flipCard() {
        this.right_card.setVisible(true);
    }

    public void toggleReverse() {
        reverse_b = !reverse_b;
        displayCard();
    }

    public void setReversed(boolean reversed_b) {
        this.reverse_b = reversed_b;
    }

    public void reload(){
        displayCard();
    }


}


