package cardGui;

import cardPackage.BasicCard;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;
import misc.Misc;

/**
 * build up to 4 label gui for basic card:
 * jap1, jap2, eng1, eng2
 */
public class BasicCardGui extends CardGui {
    private BasicCard card;
    private AnchorPane left_card;            // default: jap
    private AnchorPane right_card;           // default: eng

    private VBox left_VBox;
    private VBox right_VBox;

    private int BORDER_WIDTH = 15;
    private int LABEL_WIDTH = (int) (CardGuiManager.cardDisplayWidth * 0.5) - (2 * BORDER_WIDTH);
    private int AVAILABLE_HEIGHT = CardGuiManager.cardDisplayHeight - (2 * BORDER_WIDTH);

    private final double ROMAJI_FONT_MAGNIFICATION = 2.0;
    private final double DEFAULT_FONT_MAGNIFICATION = 1.0;

    public BasicCardGui(BasicCard card) {
        this.card = card;
        buildCard();
    }

    private void buildCard() {
        left_card = new AnchorPane();
        right_card = new AnchorPane();

        if(BasicCard.hasJap2(card))
            leftMultipleBuild();
        else
            leftSingleBuild();

        if(BasicCard.hasEng2(card))
            rightMultipleBuild();
        else
            rightSingleBuild();

        left_card.getChildren().add(left_VBox);
        right_card.getChildren().add(right_VBox);

        handleStyle();

    }

    private void leftSingleBuild() {
        left_VBox = new VBox();

        Label j1 = new Label();
        j1.setText(card.getJap1());
        j1.setPrefSize(LABEL_WIDTH, AVAILABLE_HEIGHT);
        j1.setMaxSize(LABEL_WIDTH, AVAILABLE_HEIGHT);
        j1.setTextAlignment(TextAlignment.CENTER);
        j1.setPadding(new Insets(5, 5, 5, 5));
        j1.setWrapText(true);
        j1.getStyleClass().add("card-label-single");
        Misc.setDynamicFontSize(j1, DEFAULT_FONT_MAGNIFICATION);

        left_VBox.setPadding(new Insets(BORDER_WIDTH, BORDER_WIDTH, BORDER_WIDTH, BORDER_WIDTH));
        left_VBox.getChildren().add(j1);
        setAnchor(left_VBox);
    }

    private void leftMultipleBuild() {
        left_VBox = new VBox();
        int SPACING = 10;
        int availableHeight = AVAILABLE_HEIGHT - (1 * SPACING);

        Label j1 = new Label();
        j1.setText(card.getJap1());

        Label j2 = new Label();
        j2.setText(card.getJap2());

        j1.setPrefSize(LABEL_WIDTH, availableHeight * 0.5);
        j1.setTextAlignment(TextAlignment.CENTER);
        j1.setPadding(new Insets(5, 5, 5, 5));
        j1.setWrapText(true);
        j1.getStyleClass().add("card-label-single");
        Misc.setDynamicFontSize(j1, DEFAULT_FONT_MAGNIFICATION);

        j2.setPrefSize(LABEL_WIDTH, availableHeight * 0.5);
        j2.setTextAlignment(TextAlignment.CENTER);
        j2.setPadding(new Insets(5, 5, 5, 5));
        j2.setWrapText(true);
        j2.getStyleClass().add("card-label-single");
        Misc.setDynamicFontSize(j2, DEFAULT_FONT_MAGNIFICATION);

        left_VBox.setPadding(new Insets(BORDER_WIDTH, BORDER_WIDTH, BORDER_WIDTH, BORDER_WIDTH));
        left_VBox.setSpacing(SPACING);
        //left_VBox.setPrefSize(Misc.CARDGUI_WIDTH, Misc.CARDGUI_HEIGHT);
        left_VBox.getChildren().addAll(j1, j2);
        setAnchor(left_VBox);
    }

    private void rightSingleBuild() {
        right_VBox = new VBox();

        Label e1 = new Label();
        e1.setText(card.getEng1());

        e1.setPrefSize(LABEL_WIDTH, AVAILABLE_HEIGHT);
        e1.setMaxSize(LABEL_WIDTH, AVAILABLE_HEIGHT);
        e1.setTextAlignment(TextAlignment.CENTER);
        e1.setPadding(new Insets(5, 5, 5, 5));
        e1.setWrapText(true);
        e1.getStyleClass().add("card-label-single-romaji");
        Misc.setDynamicFontSize(e1, ROMAJI_FONT_MAGNIFICATION);

        right_VBox.setPadding(new Insets(BORDER_WIDTH, BORDER_WIDTH, BORDER_WIDTH, BORDER_WIDTH));
        right_VBox.getChildren().add(e1);
        setAnchor(right_VBox);
    }

    private void rightMultipleBuild() {
        right_VBox = new VBox();
        int SPACING = 10;
        int availableHeight = AVAILABLE_HEIGHT - (1 * SPACING);

        Label e1 = new Label();
        e1.setText(card.getEng1());

        Label e2 = new Label();
        e2.setText(card.getEng2());

        e1.setPrefSize(LABEL_WIDTH, availableHeight * 0.5);
        e1.setTextAlignment(TextAlignment.CENTER);
        e1.setPadding(new Insets(5, 5, 5, 5));
        e1.setWrapText(true);
        e1.getStyleClass().add("card-label-single-romaji");
        Misc.setDynamicFontSize(e1, ROMAJI_FONT_MAGNIFICATION);

        e2.setPrefSize(LABEL_WIDTH, availableHeight * 0.5);
        e2.setTextAlignment(TextAlignment.CENTER);
        e2.setPadding(new Insets(5, 5, 5, 5));
        e2.setWrapText(true);
        e2.getStyleClass().add("card-label-single");
        Misc.setDynamicFontSize(e2, DEFAULT_FONT_MAGNIFICATION);

        right_VBox.setPadding(new Insets(BORDER_WIDTH, BORDER_WIDTH, BORDER_WIDTH, BORDER_WIDTH));
        right_VBox.setSpacing(SPACING);
        //right_VBox.setPrefSize(Misc.CARDGUI_WIDTH, Misc.CARDGUI_HEIGHT);
        right_VBox.getChildren().addAll(e1, e2);
        setAnchor(right_VBox);
    }

    private void handleStyle() {
        //left_card.setMinSize(Misc.CARDGUI_WIDTH, Misc.CARDGUI_HEIGHT);
        //left_card.setPrefSize(Misc.CARDGUI_WIDTH, Misc.CARDGUI_HEIGHT);
        //left_card.setMaxSize(Misc.CARDGUI_WIDTH, Misc.CARDGUI_HEIGHT);
        AnchorPane.setTopAnchor(left_card, 20.0);
        AnchorPane.setLeftAnchor(left_card, 20.0);
        AnchorPane.setRightAnchor(left_card, 20.0);
        AnchorPane.setBottomAnchor(left_card, 20.0);
        left_card.getStylesheets().add("main.css");

        //right_card.setMinSize(Misc.CARDGUI_WIDTH, Misc.CARDGUI_HEIGHT);
        //right_card.setPrefSize(Misc.CARDGUI_WIDTH, Misc.CARDGUI_HEIGHT);
        //right_card.setMaxSize(Misc.CARDGUI_WIDTH, Misc.CARDGUI_HEIGHT);
        AnchorPane.setTopAnchor(right_card, 20.0);
        AnchorPane.setLeftAnchor(left_card, 20.0);
        AnchorPane.setRightAnchor(right_card, 20.0);
        AnchorPane.setBottomAnchor(right_card, 20.0);
        right_card.getStylesheets().add("main.css");
    }

    private void setAnchor(Pane box) {
        AnchorPane.setTopAnchor(box, 0.0);
        AnchorPane.setLeftAnchor(box, 0.0);
        AnchorPane.setRightAnchor(box, 0.0);
        AnchorPane.setBottomAnchor(box, 0.0);
    }

    public void setCssStyleClass(String cssStyleClass) {
        left_card.getStyleClass().add(cssStyleClass);
        right_card.getStyleClass().add(cssStyleClass);
    }

    @Override
    public Pane getLeft_card() {
        return left_card;
    }

    @Override
    public Pane getRight_card() {
        return right_card;
    }
}
