package editDeckPane.editCardModule;

import cardPackage.*;
import cardPackage.Types.AdjectiveType;
import cardPackage.Types.CardType;
import cardPackage.Types.VerbType;
import editDeckPane.EditDeckController;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import main.MainController;

import java.io.IOException;

public class EditCardModule extends AnchorPane {
    private MainController mainController;
    private EditDeckController editDeckController;
    private Integer currentCard_id = null;
    private CardType prevCardType = null;
    private CardType currentCardType = null;
    private VerbType verbType = VerbType.UNKNOWN;
    private AdjectiveType adjectiveType = AdjectiveType.UNKNOWN;

    @FXML
    private TextArea j1_T, j2_T, e1_T, e2_T;
    @FXML
    private TextArea kanji_T, translation_T, kun_T, on_T;
    @FXML
    private AnchorPane leftCardHolder, rightCardHolder;
    @FXML
    private AnchorPane leftCard, rightCard, leftCard_kanji, rightCard_kanji;
    @FXML
    private VBox specHolder;
    @FXML
    private ComboBox<String> cardType_ComboBox;




    public EditCardModule(EditDeckController editDeckController) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("editCardModule.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
            this.mainController = MainController.getInstance();
            this.editDeckController = editDeckController;
            init();
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    @FXML
    public void onAction_ok(ActionEvent event) {
        applyForm();
    }

    @FXML
    public void onAction_cancel() {
        clearForm();
        setCurrentCard_id(null);
    }

    @FXML
    public void onAction_ComboBox(ActionEvent event) {
        prevCardType = currentCardType;
        currentCardType = CardType.fromValue(cardType_ComboBox.getValue());
        updateForm(currentCardType);

        if (prevCardType != CardType.KANJI && currentCardType == CardType.KANJI) {
            clearFormBasic();
            resetCurrentCard_id();
        }
        if (prevCardType == CardType.KANJI && currentCardType != CardType.KANJI) {
            clearFormKanji();
            resetCurrentCard_id();
        }

    }

    private void init() {
        leftCard_kanji.getStyleClass().add("card-class-kanji");
        rightCard_kanji.getStyleClass().add("card-class-kanji");
        setBasicCardMode();
        this.getStylesheets().add("CardGUI.css");
        prevCardType = null;
        currentCardType = CardType.NOUN;
        cardType_ComboBox.setItems(CardType.getList());
        cardType_ComboBox.setValue(currentCardType.value());
        updateForm(currentCardType);
    }

    private void updateFormSpecs() {
        if (currentCard_id != null) {
            Card card = mainController.getSession().getDeck().getCardMap().get(currentCard_id);

            if (card instanceof VerbCard){
                VerbType verbType =  ((VerbCard) card).getType();
                this.verbType = verbType;
                //System.out.println("verbType: " + verbType);
                updateForm(verbType);
            }
            if (card instanceof AdjectiveCard) {
                AdjectiveType adjectiveType = ((AdjectiveCard) card).getType();
                this.adjectiveType = adjectiveType;
                updateForm(adjectiveType);
            }
        }
    }

    private void updateForm(VerbType verbType) {
        RadioButton rb = (RadioButton) specHolder.getChildren().get(verbType.toNumber());
        rb.setSelected(true);
    }

    private void updateForm(AdjectiveType adjectiveType) {
        RadioButton rb = (RadioButton) specHolder.getChildren().get(adjectiveType.toNumber());
        rb.setSelected(true);
    }

    public void updateForm(CardType cardType) {
        //System.out.println("voluntary? " + voluntaryCardTypeChange);
        cardType_ComboBox.setValue(cardType.value());

        if (cardType == CardType.NOUN){
            updateFormNoun();
        }
        if (cardType == CardType.VERB){
            updateFormVerb();
        }
        if (cardType == CardType.ADJECTIVE){
            updateFormAdjective();
        }
        if (cardType == CardType.OTHER){
            updateFormOther();
        }
        if (cardType == CardType.KANJI){
            updateFormKanji();
        }


        defaultSpecTypes();
    }

    private void updateFormNoun() {
        setBasicCardMode();
        clearStyleClasses(leftCard);
        clearStyleClasses(rightCard);
        leftCard.getStyleClass().add("card-class-noun");
        rightCard.getStyleClass().add("card-class-noun");

        specHolder.getChildren().clear();
    }

    private void updateFormVerb() {
        setBasicCardMode();
        clearStyleClasses(leftCard);
        clearStyleClasses(rightCard);
        leftCard.getStyleClass().add("card-class-verb");
        rightCard.getStyleClass().add("card-class-verb");

        specHolder.getChildren().clear();
        ObservableList<String> specList = VerbType.getList();
        final ToggleGroup group = new ToggleGroup();
        for (String spec: specList) {
            RadioButton radioButton =  new RadioButton(spec);
            radioButton.setOnAction(event -> setVerbType(VerbType.fromValue(radioButton.getText())));
            radioButton.setToggleGroup(group);
            specHolder.getChildren().add(radioButton);
        }

    }

    private void updateFormAdjective() {
        setBasicCardMode();
        clearStyleClasses(leftCard);
        clearStyleClasses(rightCard);
        leftCard.getStyleClass().add("card-class-adjective");
        rightCard.getStyleClass().add("card-class-adjective");

        specHolder.getChildren().clear();
        ObservableList<String> specList = AdjectiveType.getList();
        final ToggleGroup group = new ToggleGroup();
        for (String spec: specList) {
            RadioButton radioButton =  new RadioButton(spec);
            radioButton.setOnAction(event -> setAdjectiveType(AdjectiveType.fromValue(radioButton.getText())));
            radioButton.setToggleGroup(group);
            specHolder.getChildren().add(radioButton);
        }
    }

    private void updateFormOther() {
        setBasicCardMode();
        clearStyleClasses(leftCard);
        clearStyleClasses(rightCard);
        leftCard.getStyleClass().add("card-class-other");
        rightCard.getStyleClass().add("card-class-other");

        specHolder.getChildren().clear();
    }

    private void updateFormKanji() {
        setKanjiCardMode();



        specHolder.getChildren().clear();
    }

    private void clearStyleClasses(AnchorPane anchorPane) {
        anchorPane.getStyleClass().clear();
    }

    private void clearForm() {
        clearFormBasic();
        clearFormKanji();
        defaultSpecTypes();
    }

    private void clearFormBasic() {
        j1_T.setText("");
        j2_T.setText("");
        e1_T.setText("");
        e2_T.setText("");

        //currentCard_id = null;
    }

    private void clearFormKanji() {
        kanji_T.setText("");
        translation_T.setText("");
        kun_T.setText("");
        on_T.setText("");

        //currentCard_id = null;
    }

    private void defaultSpecTypes() {
        verbType = VerbType.UNKNOWN;
        adjectiveType = AdjectiveType.UNKNOWN;

        if (CardType.fromValue(cardType_ComboBox.getValue()).equals(CardType.VERB)){
            RadioButton rb = (RadioButton) specHolder.getChildren().get(0);
            rb.setSelected(true);
        }
        if (CardType.fromValue(cardType_ComboBox.getValue()).equals(CardType.ADJECTIVE)){
            RadioButton rb = (RadioButton) specHolder.getChildren().get(0);
            rb.setSelected(true);
        }
    }



    private void applyForm() {
        CardType cardType = CardType.fromValue(cardType_ComboBox.getValue());
        Card card = null;

        if (cardType == CardType.KANJI)
            card = applyFormKanji();
        else
            card = applyFormBasic(cardType);


        editDeckController.processCardChange(card);
        clearForm();
        resetCurrentCard_id();
    }

    private Card applyFormBasic(CardType cardType) {
        String j1 = j1_T.getText();
        String j2 = j2_T.getText();
        String e1 = e1_T.getText();
        String e2 = e2_T.getText();

        //TODO: check strings n stuff


        Card card = null;
        if (currentCard_id == null) {
            currentCard_id = mainController.getSession().addNewIds();
        } else {
            if (prevCardType != currentCardType){
                mainController.getSession().addChangedTypes(currentCard_id);
                prevCardType = null;
                //System.out.println("addChangedTypes: " + currentCard_id);
            }
        }

        switch (cardType) {
            case NOUN:
                card = new NounCard(currentCard_id, j1, j2, e1, e2);
                break;
            case VERB:
                //System.out.println("[DEBUG] verbType: " + verbType);
                card = new VerbCard(currentCard_id, j1, j2, e1, e2, verbType);
                break;
            case ADJECTIVE:
                card = new AdjectiveCard(currentCard_id, j1, j2, e1, e2, adjectiveType);
                break;
            case OTHER:
                card = new OtherCard(currentCard_id, j1, j2, e1, e2);
                break;
        }

        return card;
    }

    private Card applyFormKanji()  {
        Card card = null;
        String kanji = kanji_T.getText();
        String translation = translation_T.getText();
        String kun = kun_T.getText();
        String on = on_T.getText();

        if (currentCard_id == null) {
            currentCard_id = mainController.getSession().addNewIds();
        }

        card = new KanjiCard(currentCard_id, kanji, kun, on, translation);

        return card;
    }

    public void editCardInForm(int card_id) {
        clearForm();
        setCurrentCard_id(null);
        Card card = mainController.getSession().getDeck().getCardMap().get(card_id);
        setCurrentCard_id(card_id);
        setForm(card);

    }

    private void setForm(Card card) {
        CardType cardType = card.getCardType();
        prevCardType = cardType;
        currentCardType = cardType;
        //voluntaryCardTypeChange = false;
        updateForm(cardType);
        //voluntaryCardTypeChange = true;
        updateFormSpecs();
        if (card instanceof KanjiCard){
            kanji_T.setText(((KanjiCard) card).getKanji());
            translation_T.setText(((KanjiCard) card).getTranslation());
            kun_T.setText(((KanjiCard) card).getKun());
            on_T.setText(((KanjiCard) card).getOn());
        } else {
            j1_T.setText(((BasicCard) card).getJap1());
            j2_T.setText(((BasicCard) card).getJap2());
            e1_T.setText(((BasicCard) card).getEng1());
            e2_T.setText(((BasicCard) card).getEng2());
        }

    }

    private void setBasicCardMode() {
        //clearFormKanji();
        leftCardHolder.getChildren().setAll(leftCard);
        rightCardHolder.getChildren().setAll(rightCard);
    }

    private void setKanjiCardMode() {
        //clearFormBasic();
        leftCardHolder.getChildren().setAll(leftCard_kanji);
        rightCardHolder.getChildren().setAll(rightCard_kanji);
    }

    public void setCurrentCard_id(Integer currentCard_id) {
        this.currentCard_id = currentCard_id;
    }

    private void resetCurrentCard_id() {
        currentCard_id = null;
    }






    private void setVerbType(VerbType verbType) {
        this.verbType = verbType;
    }

    private void setAdjectiveType(AdjectiveType adjectiveType) {
        this.adjectiveType = adjectiveType;
    }
}
