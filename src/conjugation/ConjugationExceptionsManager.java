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
            }
        }
    }

    private void ある_exceptions(Conjugation conjugation) {
        conjugation.changeLookUpMap("ifNegPresent", "ない");
        conjugation.changeLookUpMap("ifNegPast", "なかった");
    }
}
