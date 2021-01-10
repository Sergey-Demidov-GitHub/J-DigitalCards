package trainDeck_Stage;

import cardGui.CardGuiManager;
import cardPackage.Score;
import filterModule.FilterModule;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import main.MainController;
import verbConjugationDisplayModule.VerbConjugationDisplayModule;

import java.net.URL;
import java.util.ResourceBundle;

public class TrainDeck_Controller implements Initializable {
    private MainController mainController;

    private boolean reversed_b = false;
    private boolean locked_b = false;

    private KeyEventHandler keyEventHandler;


    //private AnchorPane pane;
    @FXML
    private AnchorPane root;
    @FXML
    private AnchorPane cardGuiManagerHolder;
    @FXML
    private AnchorPane filterModuleHolder;
    @FXML
    private AnchorPane conjugationHolder;

    private CardGuiManager cardGuiManager;
    private FilterModule filterModule;
    private VerbConjugationDisplayModule verbConjugationDisplayModule;
    @FXML
    private Label deckName_L, absoluteScore_L, relativeScore_L, cardsLeft_L;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        root.getStylesheets().add("main.css");
        AnchorPane.setBottomAnchor(root, 0.0);
        AnchorPane.setTopAnchor(root, 0.0);
        AnchorPane.setLeftAnchor(root, 0.0);
        AnchorPane.setRightAnchor(root, 0.0);
        //root.getStyleClass().add("shadow-simple");
    }

    public void setMainController(MainController mainController) {
        this.mainController = mainController;
        this.mainController.getSession().initCallMask();
        deckName_L.setText(this.mainController.getSession().getDeck().getName());

        this.filterModule = new FilterModule();
        this.filterModule.setMainController(mainController);
        this.filterModule.setTrainDeckController(this);
        filterModuleHolder.getChildren().add(this.filterModule);

        this.verbConjugationDisplayModule = new VerbConjugationDisplayModule();
        this.verbConjugationDisplayModule.setTrainDeckController(this);
        this.conjugationHolder.getChildren().add(verbConjugationDisplayModule);

        this.keyEventHandler = new KeyEventHandler(this);
        this.keyEventHandler.applyFilterOnNode(root);

        reloadCurrentCard();
        reloadScore();
        setCardsLeft();
    }

    @FXML
    public void onAction_back(ActionEvent event) {
        saveChanges();
        mainController.transition_TrainDeck_ChooseDeck();
    }
    @FXML
    public void onAction_default(ActionEvent event) {
        //mainController.reload_TrainDeck();
        defaultDeck();
        reloadCurrentCard();
        reloadScore();
        setCardsLeft();
        filterModule.clearForm();
    }
    @FXML
    public void onAction_shuffle() {
        shuffleDeck();
    }
    @FXML
    public void onAction_reverse() {
        toggleReverse();
        reloadScore();
    }
    @FXML
    public void onAction_lock() {
        toggleLock();
    }
    @FXML
    public void onAction_left() {
        loadPreviousCard();
    }
    @FXML
    public void onAction_right() {
        loadNextCard();
    }
    @FXML
    public void onAction_correct() {
        correctAnswer();
        onAction_right();
    }
    @FXML
    public void onAction_wrong() {
        wrongAnswer();
        onAction_right();
    }
    @FXML
    public void onAction_flip() {
        this.cardGuiManager.flipCard();
    }

    public void reloadScore() {
        Score newScore = mainController.getSession().getCurrentCard().getScore();
        if (!reversed_b) {
            absoluteScore_L.setText("" + newScore.getAbsolute_j() + " %");
            relativeScore_L.setText("" + newScore.getStreak_j());
        } else {
            absoluteScore_L.setText("" + newScore.getAbsolute_e() + " %");
            relativeScore_L.setText("" + newScore.getStreak_e());
        }

    }

    private void loadNextCard() {
        cardGuiManagerHolder.getChildren().remove(cardGuiManager);
        mainController.getSession().nextCallMaskIndex();
        reloadCurrentCard();
        reloadScore();
        setCardsLeft();
    }

    private void loadPreviousCard() {
        cardGuiManagerHolder.getChildren().remove(cardGuiManager);
        mainController.getSession().previousCallMaskIndex();
        reloadCurrentCard();
        reloadScore();
        setCardsLeft();
    }

    public void reloadCurrentCard() {
        cardGuiManager = new CardGuiManager();
        cardGuiManager.setLocked(locked_b);
        cardGuiManager.setReversed(reversed_b);
        cardGuiManager.setMainController(mainController);
        cardGuiManagerHolder.getChildren().add(cardGuiManager);
    }

    private void correctAnswer() {
        mainController.getSession().getCurrentCard().getScore().plusScore(reversed_b);
        reloadScore();
    }

    private void wrongAnswer() {
        mainController.getSession().getCurrentCard().getScore().minusScore(reversed_b);
        reloadScore();
    }

    public void setCardsLeft() {
        int cardsLeft = mainController.getSession().getCardsLeft();
        this.cardsLeft_L.setText("" + cardsLeft);
    }

    private void defaultDeck() {
        mainController.getSession().initCallMask();
        reloadCurrentCard();
    }

    private void shuffleDeck() {
        mainController.getSession().shuffleCallMask();
        mainController.getSession().resetCallMaskIndex();
        reloadCurrentCard();
    }

    private void saveChanges() {
        mainController.getSession().applyScoreChanges();
    }

    public void toggleLock() {
        locked_b = !locked_b;
        this.cardGuiManager.toggleLock();
    }

    public void toggleReverse() {
        reversed_b = !reversed_b;
        this.cardGuiManager.toggleReverse();
    }

    public boolean isReversed_b() {
        return reversed_b;
    }

}
