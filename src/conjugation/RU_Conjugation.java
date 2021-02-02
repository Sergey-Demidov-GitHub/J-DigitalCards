/*  Copyright (C) 2021 Sergey Demidov   */

package conjugation;

public class RU_Conjugation extends Conjugation {
    private String stem;
    private EndingMapper endingMapper;

    private String RARE = "られ";

    public RU_Conjugation(String infinitive) {
        super(infinitive.trim());
        endingMapper = new EndingMapper();
        genStem();
        if (stem != null) {
            genIFNegPresent();
            genIFPosPast();
            genIFNegPast();
            genFPosPresent();
            genFNegPresent();
            genFPosPast();
            genFNegPast();
            genTeForm();
            genTaiPosPresent();
            genTaiNegPresent();
            genTaiPosPast();
            genTaiNegPast();
            genIFPotPosPresent();
            genIFPotNegPresent();
            genIFPotPosPast();
            genIFPotNegPast();
            genFPotPosPresent();
            genFPotNegPresent();
            genFPotPosPast();
            genFPotNegPast();
            genPotTeForm();
            genIFVolForm();
            genFVolForm();
            fillLookUpMap();
        }
    }

    private void genStem() {
        stem = infinitive;
        String lastChar = stem.substring(stem.length() - 1);
        if (lastChar.equals("る")){
            stem = stem.substring(0, stem.length() - 1);
        } else {
            System.err.println("[ERROR] 'RU_Conjugation.genStem()' lastChar is not る\n" +
                    "Verb-Stem for " + infinitive + " couldn't be generated!");
            stem = null;
        }
    }

    private void genIFNegPresent() {
        super.ifNegPresent = stem + endingMapper.IF_NEG_PRESENT;
    }

    private void genIFPosPast() {
        super.ifPosPast = stem + "た";
    }

    private void genIFNegPast() {
        super.ifNegPast = stem + endingMapper.IF_NEG_PAST;
    }



    private void genFPosPresent() {
        super.fPosPresent = stem + endingMapper.F_POS_PRESENT;
    }

    private void genFNegPresent() {
        super.fNegPresent = stem + endingMapper.F_NEG_PRESENT;
    }

    private void genFPosPast() {
        super.fPosPast = stem + endingMapper.F_POS_PAST;
    }

    private void genFNegPast() {
        super.fNegPast = stem + endingMapper.F_NEG_PAST;
    }


    private void genTeForm() {
        super.teForm = stem + "て";
    }


    private void genTaiPosPresent() {
        super.taiPosPresent = stem + endingMapper.TAI_POS_PRESENT;
    }

    private void genTaiNegPresent() {
        super.taiNegPresent = stem + endingMapper.TAI_NEG_PRESENT;
    }

    private void genTaiPosPast() {
        super.taiPosPast = stem + endingMapper.TAI_POS_PAST;
    }

    private void genTaiNegPast() {
        super.taiNegPast = stem + endingMapper.TAI_NEG_PAST;
    }

    private void genIFPotPosPresent() {
        super.ifPotPosPresent = stem + RARE + "る";
    }

    private void genIFPotNegPresent() {
        super.ifPotNegPresent = stem + RARE + endingMapper.IF_NEG_PRESENT;
    }

    private void genIFPotPosPast() {
        super.ifPotPosPast = stem + RARE + "た";
    }

    private void genIFPotNegPast() {
        super.ifPotNegPast = stem + RARE + endingMapper.IF_NEG_PAST;
    }

    private void genFPotPosPresent() {
        super.fPotPosPresent = stem + RARE + endingMapper.F_POS_PRESENT;
    }

    private void genFPotNegPresent() {
        super.fPotNegPresent = stem + RARE + endingMapper.F_NEG_PRESENT;
    }

    private void genFPotPosPast() {
        super.fPotPosPast = stem + RARE + endingMapper.F_POS_PAST;
    }

    private void genFPotNegPast() {
        super.fPotNegPast = stem + RARE + endingMapper.F_NEG_PAST;
    }

    private void genPotTeForm() {
        super.potTeForm = stem + RARE + "て";
    }

    private void genIFVolForm() {
        super.ifVolForm = stem + "よう";
    }

    private void genFVolForm() {
        super.fVolForm = stem + "ましょう";
    }










    public static void testRuConjugation() {
        System.out.println("=======================================================================");
        System.out.println("[TEST] RU_Conjugation");
        System.out.println("=======================================================================");
        String testWord_1 = "みる";
        String testWord_2 = "見る";
        String testWord_3 = "たべて";
        String testWord_4 = "";
        RU_Conjugation c1 = new RU_Conjugation(testWord_1);
        RU_Conjugation c2 = new RU_Conjugation(testWord_2);
        RU_Conjugation c3 = new RU_Conjugation(testWord_3);
        //RU_Conjugation c4 = new RU_Conjugation(testWord_4);

        System.out.println(c1.toString());
        //c2.toString();


        System.out.println("=======================================================================");
    }
}
