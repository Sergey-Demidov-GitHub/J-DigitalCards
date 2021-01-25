package conjugation;

import java.util.ArrayList;
import java.util.List;

/**
 * This class has a list of conjugation exceptions.
 * It provides a way of modifying a conjugation by
 * substituting the verb forms which need to be changed.
 */
public class ConjugationExceptionsManager {
    private List<String> exceptions;

    public ConjugationExceptionsManager() {
        exceptions = new ArrayList<String>();
        exceptions.add("ある");
        exceptions.add("いく");
        exceptions.add("行く");
        //exceptions.add("する");
    }

    public boolean isException(String infinitive) {
        return exceptions.contains(infinitive);
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
            case "する": {
                する_exceptions(conjugation);
                break;
            }
            case "来る": {
                break;
            }
            case "くる": {
                break;
            }
            case "連れてくる": {
                break;
            }
            case "つれてくる": {
                break;
            }
            case "持ってくる": {
                break;
            }
            case "もってくる": {
                break;
            }
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

    private void する_exceptions(Conjugation conjugation) {


    }


}
