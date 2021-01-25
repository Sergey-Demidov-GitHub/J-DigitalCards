package conjugation;

import java.util.Arrays;

public class U_Conjugation extends Conjugation{
    private String stem;
    private String indicator;
    private EndingMapper endingMapper;

    public U_Conjugation(String infinitive) {
        super(infinitive.trim());
        endingMapper = new EndingMapper();
        genStem();
        if (stem != null && indicator != null) {
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
        if (Arrays.stream(endingMapper.getULevel()).anyMatch(lastChar::equals)){
            stem = stem.substring(0, stem.length() - 1);
            indicator = lastChar;
            //System.out.println("stem: " + stem);
            //System.out.println("indicator: " + indicator);
        } else {
            System.err.println("[ERROR] 'U_Conjugation.genStem()' lastChar is not on uLevel\n" +
                    "Verb-Stem for " + infinitive + " couldn't be generated!");
            stem = null;
            indicator = null;
        }
    }

    private void genIFNegPresent() {
        super.ifNegPresent = stem + endingMapper.getAChar(indicator) + endingMapper.IF_NEG_PRESENT;
    }

    private void genIFPosPast() {
        super.ifPosPast = stem + endingMapper.getTaEnding(indicator);
    }

    private void genIFNegPast() {
        super.ifNegPast = stem + endingMapper.getAChar(indicator) + endingMapper.IF_NEG_PAST;
    }


    private void genFPosPresent() {
        super.fPosPresent = stem + endingMapper.getIChar(indicator) + endingMapper.F_POS_PRESENT;
    }

    private void genFNegPresent() {
        super.fNegPresent = stem + endingMapper.getIChar(indicator) + endingMapper.F_NEG_PRESENT;
    }

    private void genFPosPast() {
        super.fPosPast = stem + endingMapper.getIChar(indicator) + endingMapper.F_POS_PAST;
    }

    private void genFNegPast() {
        super.fNegPast = stem + endingMapper.getIChar(indicator) + endingMapper.F_NEG_PAST;
    }

    private void genTeForm() {
        super.teForm = stem + endingMapper.getTeEnding(indicator);
    }

    private void genTaiPosPresent() {
        super.taiPosPresent = stem + endingMapper.getIChar(indicator) + endingMapper.TAI_POS_PRESENT;
    }

    private void genTaiNegPresent() {
        super.taiNegPresent = stem +  endingMapper.getIChar(indicator) + endingMapper.TAI_NEG_PRESENT;
    }

    private void genTaiPosPast() {
        super.taiPosPast = stem +  endingMapper.getIChar(indicator) + endingMapper.TAI_POS_PAST;
    }

    private void genTaiNegPast() {
        super.taiNegPast = stem +  endingMapper.getIChar(indicator) + endingMapper.TAI_NEG_PAST;
    }


    private void genIFPotPosPresent() {
        super.ifPotPosPresent = stem + endingMapper.getEChar(indicator) + "る";
    }

    private void genIFPotNegPresent() {
        super.ifPotNegPresent = stem + endingMapper.getEChar(indicator) + endingMapper.IF_NEG_PRESENT;
    }

    private void genIFPotPosPast() {
        super.ifPotPosPast = stem + endingMapper.getEChar(indicator) + "た";
    }

    private void genIFPotNegPast() {
        super.ifPotNegPast = stem + endingMapper.getEChar(indicator) + endingMapper.IF_NEG_PAST;
    }

    private void genFPotPosPresent() {
        super.fPotPosPresent = stem + endingMapper.getEChar(indicator) + endingMapper.F_POS_PRESENT;
    }

    private void genFPotNegPresent() {
        super.fPotNegPresent = stem + endingMapper.getEChar(indicator) + endingMapper.F_NEG_PRESENT;
    }

    private void genFPotPosPast() {
        super.fPotPosPast = stem + endingMapper.getEChar(indicator) + endingMapper.F_POS_PAST;
    }

    private void genFPotNegPast() {
        super.fPotNegPast = stem + endingMapper.getEChar(indicator) + endingMapper.F_NEG_PAST;
    }

    private void genPotTeForm() {
        super.potTeForm = stem + endingMapper.getEChar(indicator) + "て";
    }

    private void genIFVolForm() {
        super.ifVolForm = stem + endingMapper.getOChar(indicator) + "う";
    }

    private void genFVolForm() {
        super.fVolForm = stem + endingMapper.getIChar(indicator) + "ましょう";
    }







    public static void testUConjugation() {
        System.out.println("=======================================================================");
        System.out.println("[TEST] U_Conjugation");
        System.out.println("=======================================================================");
        String testWord_1 = "はなす";
        String testWord_2 = "話す";
        String testWord_3 = "しんで";
        String testWord_4 = "";
        U_Conjugation c1 = new U_Conjugation(testWord_1);
        U_Conjugation c2 = new U_Conjugation(testWord_2);
        U_Conjugation c3 = new U_Conjugation(testWord_3);
        //U_Conjugation c4 = new U_Conjugation(testWord_4);

        System.out.println(c1.toString());
        //c2.toString();


        System.out.println("=======================================================================");
    }
}
