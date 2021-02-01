package conjugation;

import misc.Misc;

public class IRR_Conjugation extends Conjugation{
    private String stem;
    private String ending;

    public IRR_Conjugation(String infinitive) {
        super(infinitive.trim());
        splitStemEnding();
        conjugate();
    }

    private void splitStemEnding() {
        stem = infinitive;
        ending = stem.substring(stem.length() - 2);
        //TODO: sometimes the acquired number of characters doesn't fit the request
        // due to different character representations => Collator based approach needed
        stem = stem.substring(0, stem.length() - 2);
    }

    private void genSURU_Conjugation(){
        ifPosPresent = stem + "する";
        ifNegPresent = stem + "しない";
        ifPosPast = stem + "した";
        ifNegPast = stem + "しなかった";

        fPosPresent = stem + "します";
        fNegPresent = stem + "しません";
        fPosPast = stem + "しました";
        fNegPast = stem + "しませんでした";

        taiPosPresent = stem + "したい";
        taiNegPresent = stem + "したくない";
        taiPosPast = stem + "したかった";
        taiNegPast = stem + "したくなかった";

        teForm = stem + "して";

        ifPotPosPresent = stem + "できる";
        ifPotNegPresent = stem + "できない";
        ifPotPosPast = stem + "できた";
        ifPotNegPast = stem + "できなかった";

        fPotPosPresent = stem + "できます";
        fPotNegPresent = stem + "できません";
        fPotPosPast = stem + "できました";
        fPotNegPast = stem + "できませんでした";

        potTeForm = stem + "できて";

        ifVolForm = stem + "しよう";
        fVolForm = stem + "しましょう";
    }

    private void genKURU_Conjugation(){
        ifPosPresent = stem + "くる";
        ifNegPresent = stem + "こない";
        ifPosPast = stem + "きた";
        ifNegPast = stem + "こなかった";

        fPosPresent = stem + "きます";
        fNegPresent = stem + "きません";
        fPosPast = stem + "きました";
        fNegPast = stem + "きませんでした";

        taiPosPresent = stem + "きたい";
        taiNegPresent = stem + "きたくない";
        taiPosPast = stem + "きたかった";
        taiNegPast = stem + "きたくなかった";

        teForm = stem + "きて";

        ifPotPosPresent = stem + "こられる";
        ifPotNegPresent = stem + "こられない";
        ifPotPosPast = stem + "こられた";
        ifPotNegPast = stem + "こられなかった";

        fPotPosPresent = stem + "こられます";
        fPotNegPresent = stem + "こられません";
        fPotPosPast = stem + "こられました";
        fPotNegPast = stem + "こられませんでした";

        potTeForm = stem + "こられて";

        ifVolForm = stem + "こよう";
        fVolForm = stem + "きましょう";
    }

    public void conjugate() {
        if(Misc.equalsJap(ending, "する")){
            genSURU_Conjugation();
            super.fillLookUpMap();
        } else if (Misc.equalsJap(ending,"くる")) {
            genKURU_Conjugation();
            super.fillLookUpMap();
        } else {
            System.err.println("[ERROR] 'IRR_Conjugation.conjugate()' ending [" + ending + "] does not fit\n" +
                    infinitive);
        }
    }
}
