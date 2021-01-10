package cardPackage;

import cardGui.VerbCardGui;
import javafx.collections.ObservableList;

public class VerbCard extends BasicCard{
    private VerbType type;

    public VerbCard(int id, Score score, String jap1, String jap2, String eng1, String eng2) {
        super(id, score, jap1, jap2, eng1, eng2);
        this.type = VerbType.UNKNOWN;
    }

    public VerbCard(int id, String jap1, String jap2, String eng1, String eng2) {
        super(id, jap1, jap2, eng1, eng2);
        this.type = VerbType.UNKNOWN;
    }

    public VerbCard(int id, String jap1, String jap2, String eng1, String eng2, VerbType type) {
        super(id, jap1, jap2, eng1, eng2);
        this.type = type;
    }

    /**
     * Checks if give Attributes would make a VerCard
     * @param id        for error message reference
     * @param jap1      obligatory
     * @param jap2      optional
     * @param eng1      obligatory
     * @param eng2      optional
     * @param type      obligatory valid VerbType
     * @return          true (if valid VerbCard parameters)
     */
    public static boolean isValid(int id, String jap1, String jap2, String eng1, String eng2, VerbType type) {
        boolean valid_b = BasicCard.isValid(id, jap1, jap2, eng1, eng2);
        return valid_b;
    }

    public static VerbCardGui getVerbCardGui(VerbCard card) {
        return new VerbCardGui(card);
    }

    public VerbType getType() {
        return type;
    }

    public void setType(VerbType type) {
        this.type = type;
    }

    @Override
    public CardType getCardType() {
        return CardType.VERB;
    }

    public static ObservableList getExtraSpecs() {
        return VerbType.getList();
    }
}
