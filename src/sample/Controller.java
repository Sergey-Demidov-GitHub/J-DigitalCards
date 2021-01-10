package sample;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML
    private AnchorPane root;

    @FXML
    private HBox hBar;
    @FXML
    private VBox vBar;

    @FXML
    private Button b1, b2, b3, b4, b5;

    @FXML
    private Label d1, d2, d3;

    @FXML
    private Label t1, t2, t3;

    @FXML
    private HBox cardTable1, cardTable2;

    @FXML
    private VBox card_1, card_2, card_3, card_4;

    @FXML
    private TextArea leftText_1, leftText_2, rightText_1, rightText_2, rightText_3;

    @FXML
    private Label leftLabel_1, rightLabel_1, rightLabel_2;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        root.getStylesheets().add("main.css");
        root.getStyleClass().add("mainPane");
        root.getStyleClass().add("shadow-simple");

        styleSidebar();
        styleTopBar();
        styleEditCard();
        styleShowCard();
    }

    private void styleSidebar() {
        vBar.setId("image-sideBar");
        b1.getStyleClass().add("");
        b2.getStyleClass().add("");
        b3.getStyleClass().add("");
        b4.getStyleClass().add("");
    }

    private void styleTopBar() {
        hBar.setId("image-topBar_paper");
    }

    private void styleEditCard() {
        cardTable1.setId("image-CardTable");

        card_1.getStyleClass().add("card-class-kanji");
        card_2.getStyleClass().add("card-class-kanji");

        leftText_1.getStyleClass().add("");
        leftText_2.getStyleClass().add("");

        rightText_1.getStyleClass().add("");
        rightText_2.getStyleClass().add("");
        rightText_3.getStyleClass().add("");
    }

    private void styleShowCard() {
        cardTable2.setId("image-CardTable");

        card_3.getStyleClass().add("card-class-kanji");
        card_4.getStyleClass().add("card-class-kanji");

        leftLabel_1.getStyleClass().add("card-label-single");
        rightLabel_1.getStyleClass().add("card-label-single");
        rightLabel_2.getStyleClass().add("card-label-single");
    }
}
