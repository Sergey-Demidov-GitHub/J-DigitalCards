package verbConjugationDisplayModule;

import conjugation.Conjugation;
import javafx.scene.control.Label;

public class VerbConjugationDisplayModule extends VerbConjugationDisplayPattern {

    public VerbConjugationDisplayModule(Conjugation conjugation) {
        super();
        if(conjugation != null) fillGrids(conjugation);
    }

    private void fillGrids(Conjugation conjugation) {
        System.out.println(conjugation.toString());
        super.setShortFormGrid(
                new Label(conjugation.getIFPosPresent()),
                new Label(conjugation.getIFNegPresent()),
                new Label(conjugation.getIFPosPast()),
                new Label(conjugation.getIFNegPast())
        );

    }
}
