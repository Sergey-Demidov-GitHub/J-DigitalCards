package cardPackage;

import cardGui.KanjiCardGui;
import misc.Misc;

public class KanjiCard extends Card{
    private String kanji;
    private String kun;
    private String on;
    private String translation;

    public KanjiCard(int id, Score score, String kanji, String kun, String on, String translation) {
        super(id, score);
        this.kanji = kanji;
        this.kun = kun;
        this.on = on;
        this.translation = translation;
    }

    public KanjiCard(int id, String kanji, String kun, String on, String translation) {
        super(id);
        this.kanji = kanji;
        this.kun = kun;
        this.on = on;
        this.translation = translation;
    }

    /**
     * Checks if give Attributes would make a KanjiCard
     * @param id            for error message reference
     * @param kanji         obligatory
     * @param kun           optional
     * @param on            optional
     * @param translation   obligatory
     * @return              true (if valid KanjiCard parameters)
     */
    public static boolean isValid(int id, String kanji, String kun, String on, String translation) {
        boolean valid_b = true;
        if (kanji == null || Misc.isBlank(kanji)){
            System.err.println("[ERROR] KanjiCard.isValid() unacceptable kanji for card_id: " + id + ". ");
            valid_b = false;
        }
        if (translation == null || Misc.isBlank(translation)){
            System.err.println("[ERROR] KanjiCard.isValid() unacceptable translation for card_id: " + id + ". ");
            valid_b = false;
        }
        if (kun == null){
            System.err.println("[ERROR] KanjiCard.isValid() kun is null for card_id: " + id + ". ");
            valid_b = false;
        }
        if (on == null){
            System.err.println("[ERROR] KanjiCard.isValid() on is null eng1 for card_id: " + id + ". ");
            valid_b = false;
        }

        return valid_b;
    }

    public static KanjiCardGui getKanjiCardGui(KanjiCard card) {
        return new KanjiCardGui(card);
    }

    public static KanjiCardGui getKanjiCardGui_translationMode(KanjiCard card) {
        return new KanjiCardGui(card, false);
    }

    @Override
    public CardType getCardType() {
        return CardType.KANJI;
    }

    @Override
    public String toString() {
        return "KanjiCard{" +
                "kanji='" + kanji + '\'' +
                ", kun='" + kun + '\'' +
                ", on='" + on + '\'' +
                ", translation='" + translation + '\'' +
                '}';
    }

    public boolean hasKun() {
        return (this.kun != null && !Misc.isBlank(this.kun));
    }

    public boolean hasOn() {
        return (this.on != null && !Misc.isBlank(this.on));
    }

    //==============================================================================================================


    public String getKanji() {
        return kanji;
    }

    public void setKanji(String kanji) {
        this.kanji = kanji;
    }

    public String getKun() {
        return kun;
    }

    public String getKunFancy() {
        return "kun: " + kun;
    }

    public void setKun(String kun) {
        this.kun = kun;
    }

    public String getOn() {
        return on;
    }

    public String getOnFancy() {
        return "on: " + on;
    }

    public void setOn(String on) {
        this.on = on;
    }

    public String getTranslation() {
        return translation;
    }

    public void setTranslation(String translation) {
        this.translation = translation;
    }
}
