package verbConjugationDisplayModule;

import conjugation.Conjugation;
import javafx.scene.control.Label;

public class VerbConjugationDisplayModule extends VerbConjugationDisplayPattern {
    private Conjugation conjugation;

    private Label shortForm_L1, shortForm_L2, shortForm_L3, shortForm_L4;
    private Label longForm_L1, longForm_L2, longForm_L3, longForm_L4;
    private Label taiForm_L1, taiForm_L2, taiForm_L3, taiForm_L4;
    private Label teForm_L1;
    private Label shortPot_L1, shortPot_L2, shortPot_L3, shortPot_L4;
    private Label longPot_L1, longPot_L2, longPot_L3, longPot_L4;
    private Label potTeForm_L1;
    private Label volForm_L1, volForm_L2;

    public VerbConjugationDisplayModule(Conjugation conjugation) {
        super();
        this.conjugation = conjugation;
        if(this.conjugation != null) {
            setLabels();
            fillGrids();
        }
    }

    private void setLabels() {
        shortForm_L1 = new Label(conjugation.getIFPosPresent());
        shortForm_L2 = new Label(conjugation.getIFNegPresent());
        shortForm_L3 = new Label(conjugation.getIFPosPast());
        shortForm_L4 = new Label(conjugation.getIFNegPast());

        longForm_L1 = new Label(conjugation.getFPosPresent());
        longForm_L2 = new Label(conjugation.getFNegPresent());
        longForm_L3 = new Label(conjugation.getFPosPast());
        longForm_L4 = new Label(conjugation.getFNegPast());

        taiForm_L1 = new Label(conjugation.getTaiPosPresent());
        taiForm_L2 = new Label(conjugation.getTaiNegPresent());
        taiForm_L3 = new Label(conjugation.getTaiPosPast());
        taiForm_L4 = new Label(conjugation.getTaiNegPast());

        teForm_L1 = new Label(conjugation.getTeForm());

        shortPot_L1 = new Label(conjugation.getIFPotPosPresent());
        shortPot_L2 = new Label(conjugation.getIFPotNegPresent());
        shortPot_L3 = new Label(conjugation.getIFPotPosPast());
        shortPot_L4 = new Label(conjugation.getIFPotNegPast());

        longPot_L1 = new Label(conjugation.getFPotPosPresent());
        longPot_L2 = new Label(conjugation.getFPotNegPresent());
        longPot_L3 = new Label(conjugation.getFPotPosPast());
        longPot_L4 = new Label(conjugation.getFPotNegPast());

        potTeForm_L1 = new Label(conjugation.getPotTeForm());

        volForm_L1 = new Label(conjugation.getIFVolForm());
        volForm_L2 = new Label(conjugation.getFVolForm());
    }

    private void fillGrids() {
        //System.out.println(conjugation.toString());
        super.setShortFormGrid(shortForm_L1, shortForm_L2, shortForm_L3, shortForm_L4);
        super.setLongFormGrid(longForm_L1, longForm_L2, longForm_L3, longForm_L4);
        super.setTaiGrid(taiForm_L1, taiForm_L2, taiForm_L3, taiForm_L4);
        super.setTeGrid(teForm_L1);
        super.setShortPotGrid(shortPot_L1, shortPot_L2, shortPot_L3, shortPot_L4);
        super.setLongPotGrid(longPot_L1, longPot_L2, longPot_L3, longPot_L4);
        super.setPotTeGrid(potTeForm_L1);
        super.setVolGrid(volForm_L1, volForm_L2);

    }
}
