package cardPackage;

import cardGui.OtherCardGui;

public class OtherCard extends BasicCard{
    public OtherCard(int id, Score score, String jap1, String jap2, String eng1, String eng2) {
        super(id, score, jap1, jap2, eng1, eng2);
    }

    public OtherCard(int id, String jap1, String jap2, String eng1, String eng2) {
        super(id, jap1, jap2, eng1, eng2);
    }

    /**
     * Checks if give Attributes would make a OtherCard
     * @param id        for error message reference
     * @param jap1      obligatory
     * @param jap2      optional
     * @param eng1      obligatory
     * @param eng2      optional
     * @return          true (if valid OtherCard parameters)
     */
    public static boolean isValid(int id, String jap1, String jap2, String eng1, String eng2) {
        boolean valid_b = BasicCard.isValid(id, jap1, jap2, eng1, eng2);
        return valid_b;
    }

    public static OtherCardGui getOtherCardGui(OtherCard card){
        return new OtherCardGui(card);
    }

    @Override
    public CardType getCardType() {
        return CardType.OTHER;
    }
}
