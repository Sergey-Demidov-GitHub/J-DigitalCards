package cardPackage;

import misc.Misc;

/**
 * 4 member: jap1, jap2, eng1, eng2
 */
public class BasicCard extends Card {
    private String jap1;
    private String jap2;
    private String eng1;
    private String eng2;
    private int contentElementCount;

    public BasicCard(int id, Score score, String jap1, String jap2, String eng1, String eng2) {
        super(id, score);
        this.jap1 = jap1;
        this.jap2 = jap2;
        this.eng1 = eng1;
        this.eng2 = eng2;
        this.contentElementCount = 4;
    }

    public BasicCard(int id, String jap1, String jap2, String eng1, String eng2) {
        super(id);
        this.jap1 = jap1;
        this.jap2 = jap2;
        this.eng1 = eng1;
        this.eng2 = eng2;
        this.contentElementCount = 4;
    }

    @Override
    public String toString() {
        return "NounCard{ " +
                "id=" + super.getId() + '\'' +
                "score=" + this.getScore().toString() + '\'' +
                "jap1='" + jap1 + '\'' +
                ", jap2='" + jap2 + '\'' +
                ", eng1='" + eng1 + '\'' +
                ", eng2='" + eng2 + '\'' +
                '}';
    }

    public static boolean hasJap2(BasicCard card) {
        return !Misc.isBlank(card.getJap2());
    }

    public static boolean hasEng2(BasicCard card) {
        return !Misc.isBlank(card.getEng2());
    }

    public static boolean isValid(BasicCard card) {
        boolean isValid = false;
        boolean j1_b = card.getJap1() != null && Misc.isBlank(card.getJap1());
        boolean e1_b = card.getEng1() != null && Misc.isBlank(card.getEng1());

        if (j1_b && e1_b)
            isValid = true;

        return isValid;
    }

    /**
     * Checks if give Attributes would make a BasicCard
     * @param id        for error message reference
     * @param jap1      obligatory
     * @param jap2      optional
     * @param eng1      obligatory
     * @param eng2      optional
     * @return          true (if valid BasicCard parameters)
     */
    public static boolean isValid(int id, String jap1, String jap2, String eng1, String eng2) {
        boolean valid_b = true;
        if (jap1 == null || Misc.isBlank(jap1)){
            System.err.println("[ERROR] BasicCard.isValid() unacceptable jap1 for card_id: " + id + ". ");
            valid_b = false;
        }
        if (eng1 == null || Misc.isBlank(eng1)){
            System.err.println("[ERROR] BasicCard.isValid() unacceptable eng1 for card_id: " + id + ". ");
            valid_b = false;
        }
        if (jap2 == null){
            System.err.println("[ERROR] BasicCard.isValid() jap2 is null for card_id: " + id + ". ");
            valid_b = false;
        }
        if (eng2 == null){
            System.err.println("[ERROR] BasicCard.isValid() eng2 is null eng1 for card_id: " + id + ". ");
            valid_b = false;
        }

        return valid_b;
    }

    /*
    public static BasicCardGui getBasicCardGui(BasicCard card) {
        return new BasicCardGui(card);
    }*/



    //==============================================================================================================

    public String getJap1() {
        return jap1;
    }

    public void setJap1(String jap1) {
        this.jap1 = jap1;
    }

    public String getJap2() {
        return jap2;
    }

    public void setJap2(String jap2) {
        this.jap2 = jap2;
    }

    public String getEng1() {
        return eng1;
    }

    public void setEng1(String eng1) {
        this.eng1 = eng1;
    }

    public String getEng2() {
        return eng2;
    }

    public void setEng2(String eng2) {
        this.eng2 = eng2;
    }
}
