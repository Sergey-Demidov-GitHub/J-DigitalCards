/*  Copyright (C) 2021 Sergey Demidov   */

package cardPackage;

import cardPackage.Types.CardType;
import cardPackage.Types.VerbType;
import flashCards.VerbFlashCard;
import conjugation.Conjugation;
import conjugation.IRR_Conjugation;
import conjugation.RU_Conjugation;
import conjugation.U_Conjugation;
import javafx.collections.ObservableList;

import java.util.Locale;

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

    public static VerbFlashCard getVerbCardGui(VerbCard card) {
        //System.out.println(card.type);
        return new VerbFlashCard(card);
    }

    public Conjugation getConjugation() {
        Conjugation conjugation = null;

        String infinitive = "";
        if (BasicCard.hasJap2(this)) {
            infinitive = String.format(Locale.JAPANESE, this.getJap2().trim());
        } else {
            infinitive = String.format(Locale.JAPANESE, this.getJap1().trim());
        }

        switch (this.type){
            case RU: {
                conjugation = new RU_Conjugation(infinitive);
                break;
            }
            case U: {
                conjugation = new U_Conjugation(infinitive);
                break;
            }
            case IRREGULAR: {
                conjugation = new IRR_Conjugation(infinitive);
                break;
            }
            case UNKNOWN: {
                break;
            }

        }

        return conjugation;
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
