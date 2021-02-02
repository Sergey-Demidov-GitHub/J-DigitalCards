/*  Copyright (C) 2021 Sergey Demidov   */

package conjugation;

import misc.Misc;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This class has a list of conjugation exceptions.
 * It provides a way of modifying a conjugation by
 * substituting the verb forms which need to be changed.
 */
public class ConjugationExceptionsManager {
    private List<String> explicitExceptions;
    private Pattern があるPattern;

    public ConjugationExceptionsManager() {
        explicitExceptions = new ArrayList<String>();
        explicitExceptions.add("ある");
        explicitExceptions.add("いく");
        explicitExceptions.add("行く");
        explicitExceptions.add("もっていく");
        explicitExceptions.add("持っていく");

        があるPattern = Pattern.compile(".+ある");
    }

    public boolean isException(String infinitive) {
        return explicitExceptions.contains(infinitive) || isがあるPattern(infinitive);
    }

    private boolean isがあるPattern(String infinitive) {
        Matcher matcher = があるPattern.matcher(infinitive);
        return matcher.matches();
    }

    /**
     * modifies the conjugation based on provided infinitive
     * @param infinitive    - base for the kind of modifications
     * @param conjugation   - object to be modified
     */
    public void updateConjugation(String infinitive, Conjugation conjugation) {
        switch (infinitive) {
            case "ある": {
                ある_exceptions(conjugation);
                break;
            }
            case "いく": {
                いく_exceptions(conjugation);
                break;
            }
            case "行く": {
                行く_exceptions(conjugation);
                break;
            }
            case "もっていく": {
                もっていく_exceptions(conjugation);
                break;
            }
            case "持っていく": {
                持っていく_exceptions(conjugation);
                break;
            }
            default : {
                if (isがあるPattern(infinitive)){
                    がある_exceptions(infinitive, conjugation);
                }
            }
        }
    }

    private void がある_exceptions(String infinitive, Conjugation conjugation) {
        String ending = infinitive.substring(infinitive.length() - 2);
        if (Misc.equalsJap(ending, "ある")){
            String stem = infinitive.substring(0, infinitive.length() - 2);
            conjugation.changeLookUpMap("ifNegPresent", stem + "ない");
            conjugation.changeLookUpMap("ifNegPast", stem + "なかった");
        } else {
            System.err.println("[WARNING] 'ConjugationExceptionsManager.がある_exceptions' wrong ending: " + ending);
            conjugation.changeLookUpMap("ifNegPresent", "");
            conjugation.changeLookUpMap("ifNegPast", "");
        }
    }

    private void ある_exceptions(Conjugation conjugation) {
        conjugation.changeLookUpMap("ifNegPresent", "ない");
        conjugation.changeLookUpMap("ifNegPast", "なかった");
    }

    private void いく_exceptions(Conjugation conjugation) {
        conjugation.changeLookUpMap("ifPosPast", "いった");
        conjugation.changeLookUpMap("teForm", "いって");
    }

    private void 行く_exceptions(Conjugation conjugation) {
        conjugation.changeLookUpMap("ifPosPast", "行った");
        conjugation.changeLookUpMap("teForm", "行って");
    }

    private void もっていく_exceptions(Conjugation conjugation) {
        conjugation.changeLookUpMap("ifPosPast", "もっていった");
        conjugation.changeLookUpMap("teForm", "もっていって");
    }

    private void 持っていく_exceptions(Conjugation conjugation) {
        conjugation.changeLookUpMap("ifPosPast", "持っていった");
        conjugation.changeLookUpMap("teForm", "持っていって");
    }


}
