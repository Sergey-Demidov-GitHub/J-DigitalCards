package cardPackage;

import cardPackage.Types.AdjectiveType;
import cardPackage.Types.CardType;
import flashCards.AdjectiveFlashCard;
import javafx.collections.ObservableList;

public class AdjectiveCard extends BasicCard{
    private AdjectiveType type;

    public AdjectiveCard(int id, Score score, String jap1, String jap2, String eng1, String eng2) {
        super(id, score, jap1, jap2, eng1, eng2);
        this.type = AdjectiveType.UNKNOWN;
    }

    public AdjectiveCard(int id, String jap1, String jap2, String eng1, String eng2) {
        super(id, jap1, jap2, eng1, eng2);
        this.type = AdjectiveType.UNKNOWN;
    }

    public AdjectiveCard(int id, String jap1, String jap2, String eng1, String eng2, AdjectiveType type) {
        super(id, jap1, jap2, eng1, eng2);
        this.type = type;
    }

    /**
     * Checks if give Attributes would make an AdjectiveCard
     * @param id        for error message reference
     * @param jap1      obligatory
     * @param jap2      optional
     * @param eng1      obligatory
     * @param eng2      optional
     * @param type      obligatory valid AdjectiveType
     * @return          true (if valid AdjectiveType parameters)
     */
    public static boolean isValid(int id, String jap1, String jap2, String eng1, String eng2, AdjectiveType type) {
        boolean valid_b = BasicCard.isValid(id, jap1, jap2, eng1, eng2);
        return valid_b;
    }

    public static AdjectiveFlashCard getAdjectiveCardGui(AdjectiveCard card) {
        return new AdjectiveFlashCard(card);
    }

    public AdjectiveType getType() {
        return type;
    }

    public void setType(AdjectiveType type) {
        this.type = type;
    }

    @Override
    public CardType getCardType() {
        return CardType.ADJECTIVE;
    }

    public static ObservableList getExtraSpecs() {
        return AdjectiveType.getList();
    }
}
