package cardPackage;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Wrapper class for cards to display them in TableView.
 * Same table for all card types.
 */
public class CardTableElement {
    private int card_id;
    private final StringProperty cardType = new SimpleStringProperty();
    private String j1, j2, e1, e2;
    private Integer score_absolute_j, score_relative_j,score_absolute_e, score_relative_e;

    public CardTableElement(Card card) {
        if (card instanceof KanjiCard)
            createCardTableElement((KanjiCard) card);
        else
            createCardTableElement((BasicCard) card);
    }

    public void createCardTableElement(BasicCard card) {
        card_id = card.getId();
        cardType.setValue(card.getCardType().value());
        j1 = card.getJap1();
        j2 = card.getJap2();
        e1 = card.getEng1();
        e2 = card.getEng2();
        score_absolute_j = card.getScore().getAbsolute_j();
        score_relative_j = card.getScore().getStreak_j();
        score_absolute_e = card.getScore().getAbsolute_e();
        score_relative_e = card.getScore().getStreak_e();
    }

    public void createCardTableElement(KanjiCard card) {
        card_id = card.getId();
        cardType.setValue(card.getCardType().value());
        j1 = card.getKanji();
        j2 = "kun: " + card.getKun() + "\n" +  "on: " + card.getOn();
        e1 = card.getTranslation();
        e2 = "";
        score_absolute_j = card.getScore().getAbsolute_j();
        score_relative_j = card.getScore().getStreak_j();
        score_absolute_e = card.getScore().getAbsolute_e();
        score_relative_e = card.getScore().getStreak_e();
    }

    public int getCard_id() {
        return card_id;
    }

    public void setCard_id(int card_id) {
        this.card_id = card_id;
    }

    public String getJ1() {
        return j1;
    }

    public void setJ1(String j1) {
        this.j1 = j1;
    }

    public String getJ2() {
        return j2;
    }

    public void setJ2(String j2) {
        this.j2 = j2;
    }

    public String getE1() {
        return e1;
    }

    public void setE1(String e1) {
        this.e1 = e1;
    }

    public String getE2() {
        return e2;
    }

    public void setE2(String e2) {
        this.e2 = e2;
    }

    public Integer getScore_absolute_j() {
        return score_absolute_j;
    }

    public void setScore_absolute_j(Integer score_absolute_j) {
        this.score_absolute_j = score_absolute_j;
    }

    public Integer getScore_relative_j() {
        return score_relative_j;
    }

    public void setScore_relative_j(Integer score_relative_j) {
        this.score_relative_j = score_relative_j;
    }

    public Integer getScore_absolute_e() {
        return score_absolute_e;
    }

    public void setScore_absolute_e(Integer score_absolute_e) {
        this.score_absolute_e = score_absolute_e;
    }

    public Integer getScore_relative_e() {
        return score_relative_e;
    }

    public void setScore_relative_e(Integer score_relative_e) {
        this.score_relative_e = score_relative_e;
    }

    public StringProperty cardTypeProperty() {
        return cardType;
    }

    public String getCardType() {
        return cardType.get();
    }

    public void setCardType(String cardType) {
        this.cardType.set(cardType);
    }
}
