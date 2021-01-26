package cardPackage;

import cardPackage.Types.CardType;
import flashCards.NounFlashCard;

/**
 * adds noun specific flags and such
 */
public class NounCard extends BasicCard {
    public NounCard(int id, Score score, String jap1, String jap2, String eng1, String eng2) {
        super(id, score, jap1, jap2, eng1, eng2);
    }

    public NounCard(int id, String jap1, String jap2, String eng1, String eng2) {
        super(id, jap1, jap2, eng1, eng2);
    }

    /**
     * Checks if give Attributes would make a NounCard
     * @param id        for error message reference
     * @param jap1      obligatory
     * @param jap2      optional
     * @param eng1      obligatory
     * @param eng2      optional
     * @return          true (if valid NounCard parameters)
     */
    public static boolean isValid(int id, String jap1, String jap2, String eng1, String eng2) {
        boolean valid_b = BasicCard.isValid(id, jap1, jap2, eng1, eng2);
        return valid_b;
    }

    public static NounFlashCard getNounCardGui(NounCard card) {
        return new NounFlashCard(card);
    }

    @Override
    public CardType getCardType() {
        return CardType.NOUN;
    }
}
