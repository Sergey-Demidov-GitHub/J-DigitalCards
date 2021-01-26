package trainDeckPane;

import flashCards.flashCardModule.FlashCardModule;
import cardPackage.Card;
import cardPackage.Score;
import cardPackage.VerbCard;
import conjugation.Conjugation;
import conjugation.ConjugationExceptionsManager;
import trainDeckPane.filterModule.FilterModule;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import main.MainController;
import trainConfig.TrainConfig;
import trainDeckPane.settingsModule.SettingsModule;
import verbConjugationDisplayModule.VerbConjugationDisplayModule;

import java.net.URL;
import java.util.ResourceBundle;

public class TrainDeckController implements Initializable {
    private MainController mainController;

    private boolean reversed_b = false;
    private boolean locked_b = false;

    private KeyEventHandler keyEventHandler;
    private TrainConfig trainConfig;
    private TrainConfigManager trainConfigManager;
    private ConjugationExceptionsManager conjugationExceptionsManager;


    //private AnchorPane pane;
    @FXML
    private AnchorPane root;
    @FXML
    private AnchorPane cardGuiManagerHolder;
    @FXML
    private AnchorPane filterModuleHolder;
    @FXML
    private AnchorPane conjugationHolder;

    private FlashCardModule flashCardModule;
    private FilterModule filterModule;
    private VerbConjugationDisplayModule verbConjugationDisplayModule;
    private SettingsModule settingsModule;
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

        this.keyEventHandler = new KeyEventHandler(this);
        this.keyEventHandler.applyFilterOnNode(root);

        this.conjugationExceptionsManager = new ConjugationExceptionsManager();

        trainConfig = new TrainConfig();
        trainConfigManager = new TrainConfigManager(mainController, this, trainConfig);

        reloadCurrentCard();
        reloadScore();
        setCardsLeft();
    }

    @FXML
    public void onAction_back() {
        saveChanges();
        mainController.transition_TrainDeck_ChooseDeck();
    }
    @FXML
    public void onAction_default() {
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
    public void onAction_settings() {
        buildSettingsModule();
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
        this.flashCardModule.flipCard();
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
        cardGuiManagerHolder.getChildren().remove(flashCardModule);
        mainController.getSession().nextCallMaskIndex();
        reloadCurrentCard();
        reloadScore();
        setCardsLeft();
    }

    private void loadPreviousCard() {
        cardGuiManagerHolder.getChildren().remove(flashCardModule);
        mainController.getSession().previousCallMaskIndex();
        reloadCurrentCard();
        reloadScore();
        setCardsLeft();
    }

    public void reloadCurrentCard() {
        buildConjugationDisplay();

        flashCardModule = trainConfigManager.buildCardGuiManager(mainController.getSession().getCurrentCard());       // -> lookUpForm()
        flashCardModule.setLocked(locked_b);
        flashCardModule.setReversed(reversed_b);
        flashCardModule.reload();
        cardGuiManagerHolder.getChildren().add(flashCardModule);
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
        this.flashCardModule.toggleLock();
    }

    public void toggleReverse() {
        reversed_b = !reversed_b;
        this.flashCardModule.toggleReverse();
    }

    public boolean isReversed_b() {
        return reversed_b;
    }

    public Conjugation getCurrentConjugation() {
        return verbConjugationDisplayModule.getConjugation();
    }

    public void buildConjugationDisplay() {
        this.conjugationHolder.getChildren().clear();
        Card card = mainController.getSession().getCurrentCard();

        if (card instanceof VerbCard){
            Conjugation conjugation = ((VerbCard) card).getConjugation();
            if (conjugationExceptionsManager.isException(((VerbCard) card).getJap1())) {
                conjugationExceptionsManager.updateConjugation(((VerbCard) card).getJap1(), conjugation);
            }
            if (conjugationExceptionsManager.isException(((VerbCard) card).getJap2())) {
                conjugationExceptionsManager.updateConjugation(((VerbCard) card).getJap2(), conjugation);
            }
            this.verbConjugationDisplayModule = new VerbConjugationDisplayModule(conjugation);
            this.verbConjugationDisplayModule.setTrainDeckController(this);
            this.conjugationHolder.getChildren().add(verbConjugationDisplayModule);
        }

    }

    private void buildSettingsModule() {


        final Stage settingsWindow = new Stage();
        settingsWindow.setTitle("Settings");
        settingsWindow.initModality(Modality.APPLICATION_MODAL);
        settingsWindow.initOwner(mainController.getStage());
        settingsModule = new SettingsModule(settingsWindow, mainController, this);
        Scene settingsScene = new Scene(settingsModule);
        settingsWindow.setScene(settingsScene);
        settingsWindow.show();
        settingsModule.getStylesheets().add("main.css");
    }

    public TrainConfig getTrainConfig() {
        return trainConfig;
    }

    public void reloadConfiguration() {
        mainController.getSession().initCallMask();
        trainConfigManager = new TrainConfigManager(mainController, this, trainConfig);
        reloadCurrentCard();
        reloadScore();
        setCardsLeft();
    }

}
