/*  Copyright (C) 2021 Sergey Demidov   */

package flashCards;

import cardPackage.KanjiCard;
import flashCards.flashCardModule.FlashCardModule;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;
import misc.Misc;


/**
 *  Possibilities:
 *  [kanji] : [translation, (kun), (on)]    => 1 : [1, 3]
 *  [kanji, (kun), (on)] : [translation]    => [1, 3] : 1
 */
public class KanjiFlashCard extends FlashCard {
    private KanjiCard card;
    private AnchorPane left_card;
    private AnchorPane right_card;
    private VBox left_VBox;
    private VBox right_VBox;
    private String cssStyleClass = "card-class-kanji";

    private boolean kanjiMode = true;       // true => kanji is shown alone on one side
                                            // false => translation is shown alone on one side
    private int BORDER_WIDTH = 15;
    private int LABEL_WIDTH = (int) (FlashCardModule.cardDisplayWidth * 0.5) - (2 * BORDER_WIDTH);
    private int AVAILABLE_HEIGHT = FlashCardModule.cardDisplayHeight - (2 * BORDER_WIDTH);

    private final double ROMAJI_FONT_MAGNIFICATION = 1.5;
    private final double DEFAULT_FONT_MAGNIFICATION = 1.0;


    public KanjiFlashCard(KanjiCard card) {
        this.card = card;
        buildCard();
    }

    public KanjiFlashCard(KanjiCard card, boolean kanjiMode) {
        this.card = card;
        this.kanjiMode = kanjiMode;
        buildCard();
    }

    private void buildCard() {
        left_card = new AnchorPane();
        right_card = new AnchorPane();

        boolean kun_b = card.hasKun();
        boolean on_b = card.hasOn();

        if (kanjiMode) {
            leftSingleBuild();

            if (kun_b && !on_b)
                rightDoubleBuild();
            if (!kun_b && on_b)
                rightDoubleBuild();
            if (!kun_b && !on_b)
                rightSingleBuild();
            if (kun_b && on_b)
                rightTripleBuild();

        } else {
            if (kun_b && !on_b)
                leftDoubleBuild();
            if (!kun_b && on_b)
                leftDoubleBuild();
            if (!kun_b && !on_b)
                leftSingleBuild();
            if (kun_b && on_b)
                leftTripleBuild();

            rightSingleBuild();
        }

        left_card.getChildren().add(left_VBox);
        right_card.getChildren().add(right_VBox);

        handleStyle();
    }

    // ============================ Single

    private void leftSingleBuild() {
        left_VBox = new VBox();

        Label l = new Label();
        l.setText(card.getKanji());
        l.setPrefSize(LABEL_WIDTH, AVAILABLE_HEIGHT);
        //l.setStyle("-fx-font-size: " + Misc.getCardFontSize((int) l.getWidth()) + ";");
        l.setTextAlignment(TextAlignment.CENTER);
        l.setPadding(new Insets(5, 5, 5, 5));
        l.setWrapText(true);
        l.getStyleClass().add("card-label-kanji");

        left_VBox.setPadding(new Insets(BORDER_WIDTH, BORDER_WIDTH, BORDER_WIDTH, BORDER_WIDTH));
        left_VBox.getChildren().add(l);
        setAnchor(left_VBox, 0.0);
    }

    private void rightSingleBuild() {
        right_VBox = new VBox();

        Label l = new Label();
        l.setText(card.getTranslation());
        l.setPrefSize(LABEL_WIDTH, AVAILABLE_HEIGHT);
        l.setTextAlignment(TextAlignment.CENTER);
        l.setPadding(new Insets(5, 5, 5, 5));
        l.setWrapText(true);
        l.getStyleClass().add("card-label-kanji");
        Misc.setDynamicFontSize(l, ROMAJI_FONT_MAGNIFICATION);

        right_VBox.setPadding(new Insets(BORDER_WIDTH, BORDER_WIDTH, BORDER_WIDTH, BORDER_WIDTH));
        right_VBox.getChildren().add(l);
        setAnchor(right_VBox, 0.0);
    }

    // ============================ Double

    private void leftDoubleBuild() {
        String secondText = null;
        if (card.hasKun())
            secondText = card.getKunFancy();
        else
            secondText = card.getOnFancy();


        left_VBox = new VBox();
        int SPACING = 10;
        int availableHeight = AVAILABLE_HEIGHT - (1 * SPACING);

        Label l1 = new Label();
        l1.setText(card.getKanji());
        l1.getStyleClass().add("card-label-kanji");

        Label l2 = new Label();
        l2.setText(secondText);
        l2.getStyleClass().add("card-label-single");

        l1.setPrefSize(LABEL_WIDTH, availableHeight * 0.5);
        l1.setTextAlignment(TextAlignment.CENTER);
        l1.setPadding(new Insets(5, 5, 5, 5));
        l1.setWrapText(true);
        Misc.setDynamicFontSize(l1, DEFAULT_FONT_MAGNIFICATION);

        l2.setPrefSize(LABEL_WIDTH, availableHeight * 0.5);
        l2.setTextAlignment(TextAlignment.CENTER);
        l2.setPadding(new Insets(5, 5, 5, 5));
        l2.setWrapText(true);
        Misc.setDynamicFontSize(l2, DEFAULT_FONT_MAGNIFICATION);

        left_VBox.setPadding(new Insets(BORDER_WIDTH, BORDER_WIDTH, BORDER_WIDTH, BORDER_WIDTH));
        left_VBox.setSpacing(SPACING);
        //left_VBox.setPrefSize(Misc.CARDGUI_WIDTH, Misc.CARDGUI_HEIGHT);
        setAnchor(left_VBox, 0.0);
        left_VBox.getChildren().addAll(l1, l2);
    }

    private void rightDoubleBuild() {
        String secondText = null;
        if (card.hasKun())
            secondText = card.getKunFancy();
        else
            secondText = card.getOnFancy();

        right_VBox = new VBox();
        int SPACING = 10;
        int availableHeight = AVAILABLE_HEIGHT - (1 * SPACING);

        Label l1 = new Label();
        l1.setText(card.getTranslation());
        l1.getStyleClass().add("card-label-single");

        Label l2 = new Label();
        l2.setText(secondText);
        l2.getStyleClass().add("card-label-single");

        l1.setPrefSize(LABEL_WIDTH, availableHeight * 0.5);
        l1.setTextAlignment(TextAlignment.CENTER);
        l1.setPadding(new Insets(5, 5, 5, 5));
        l1.setWrapText(true);
        Misc.setDynamicFontSize(l1, ROMAJI_FONT_MAGNIFICATION);

        l2.setPrefSize(LABEL_WIDTH, availableHeight * 0.5);
        l2.setTextAlignment(TextAlignment.CENTER);
        l2.setPadding(new Insets(5, 5, 5, 5));
        l2.setWrapText(true);
        Misc.setDynamicFontSize(l2, DEFAULT_FONT_MAGNIFICATION);

        right_VBox.setPadding(new Insets(BORDER_WIDTH, BORDER_WIDTH, BORDER_WIDTH, BORDER_WIDTH));
        right_VBox.setSpacing(SPACING);
        //right_VBox.setPrefSize(Misc.CARDGUI_WIDTH, Misc.CARDGUI_HEIGHT);
        setAnchor(right_VBox, 0.0);
        right_VBox.getChildren().addAll(l1, l2);
    }

    // ============================ Triple

    private void leftTripleBuild() {
        left_VBox = new VBox();
        int SPACING = 10;
        int availableHeight = AVAILABLE_HEIGHT - (2 * SPACING);

        Label l1 = new Label();
        l1.setText(card.getKanji());
        l1.getStyleClass().add("card-label-kanji");

        Label l2 = new Label();
        l2.setText(card.getKunFancy());
        l2.getStyleClass().add("card-label-single");

        Label l3 = new Label();
        l3.setText(card.getOnFancy());
        l3.getStyleClass().add("card-label-single");


        l1.setPrefSize(LABEL_WIDTH, availableHeight * 0.5);
        l1.setTextAlignment(TextAlignment.CENTER);
        l1.setPadding(new Insets(5, 5, 5, 5));
        l1.setWrapText(true);
        Misc.setDynamicFontSize(l1, DEFAULT_FONT_MAGNIFICATION);

        l2.setPrefSize(LABEL_WIDTH, availableHeight * 0.25);
        l2.setTextAlignment(TextAlignment.CENTER);
        l2.setPadding(new Insets(5, 5, 5, 5));
        l2.setWrapText(true);
        Misc.setDynamicFontSize(l2, DEFAULT_FONT_MAGNIFICATION);

        l3.setPrefSize(LABEL_WIDTH, availableHeight * 0.25);
        l3.setTextAlignment(TextAlignment.CENTER);
        l3.setPadding(new Insets(5, 5, 5, 5));
        l3.setWrapText(true);
        Misc.setDynamicFontSize(l3, DEFAULT_FONT_MAGNIFICATION);

        left_VBox.setPadding(new Insets(BORDER_WIDTH, BORDER_WIDTH, BORDER_WIDTH, BORDER_WIDTH));
        left_VBox.setSpacing(SPACING);
        //left_VBox.setPrefSize(Misc.CARDGUI_WIDTH, Misc.CARDGUI_HEIGHT);
        setAnchor(left_VBox, 0.0);
        left_VBox.getChildren().addAll(l1, l2, l3);
    }

    private void rightTripleBuild() {
        right_VBox = new VBox();
        int SPACING = 10;
        int availableHeight = AVAILABLE_HEIGHT - (2 * SPACING);

        Label l1 = new Label();
        l1.setText(card.getTranslation());
        l1.getStyleClass().add("card-label-single");

        Label l2 = new Label();
        l2.setText(card.getKunFancy());
        l2.getStyleClass().add("card-label-single");

        Label l3 = new Label();
        l3.setText(card.getOnFancy());
        l3.getStyleClass().add("card-label-single");

        l1.setPrefSize(LABEL_WIDTH, availableHeight * 0.5);
        l1.setTextAlignment(TextAlignment.CENTER);
        l1.setPadding(new Insets(5, 5, 5, 5));
        l1.setWrapText(true);
        Misc.setDynamicFontSize(l1, ROMAJI_FONT_MAGNIFICATION);

        l2.setPrefSize(LABEL_WIDTH, availableHeight * 0.25);
        l2.setTextAlignment(TextAlignment.CENTER);
        l2.setPadding(new Insets(5, 5, 5, 5));
        l2.setWrapText(true);
        Misc.setDynamicFontSize(l2, DEFAULT_FONT_MAGNIFICATION);

        l3.setPrefSize(LABEL_WIDTH, availableHeight * 0.25);
        l3.setTextAlignment(TextAlignment.CENTER);
        l3.setPadding(new Insets(5, 5, 5, 5));
        l3.setWrapText(true);
        Misc.setDynamicFontSize(l3, DEFAULT_FONT_MAGNIFICATION);

        right_VBox.setPadding(new Insets(BORDER_WIDTH, BORDER_WIDTH, BORDER_WIDTH, BORDER_WIDTH));
        right_VBox.setSpacing(SPACING);
        //right_VBox.setPrefSize(Misc.CARDGUI_WIDTH, Misc.CARDGUI_HEIGHT);
        setAnchor(right_VBox, 0.0);
        right_VBox.getChildren().addAll(l1, l2, l3);
    }

    private void handleStyle() {
        //left_card.setMinSize(Misc.CARDGUI_WIDTH, Misc.CARDGUI_HEIGHT);
        //left_card.setPrefSize(Misc.CARDGUI_WIDTH, Misc.CARDGUI_HEIGHT);
        //left_card.setMaxSize(Misc.CARDGUI_WIDTH, Misc.CARDGUI_HEIGHT);
        setAnchor(left_card, 20.0);
        left_card.getStylesheets().add("main.css");
        left_card.getStyleClass().add(cssStyleClass);

        //right_card.setMinSize(Misc.CARDGUI_WIDTH, Misc.CARDGUI_HEIGHT);
        //right_card.setPrefSize(Misc.CARDGUI_WIDTH, Misc.CARDGUI_HEIGHT);
        //right_card.setMaxSize(Misc.CARDGUI_WIDTH, Misc.CARDGUI_HEIGHT);
        setAnchor(right_card, 20.0);
        right_card.getStylesheets().add("main.css");
        right_card.getStyleClass().add(cssStyleClass);
    }

    public boolean isKanjiMode() {
        return kanjiMode;
    }

    public void setKanjiMode(boolean kanjiMode) {
        this.kanjiMode = kanjiMode;
    }

    @Override
    public AnchorPane getLeft_card() {
        return left_card;
    }

    @Override
    public AnchorPane getRight_card() {
        return right_card;
    }

    private void setAnchor(Pane box, double value) {
        AnchorPane.setTopAnchor(box, value);
        AnchorPane.setLeftAnchor(box, value);
        AnchorPane.setRightAnchor(box, value);
        AnchorPane.setBottomAnchor(box, value);
    }
}
