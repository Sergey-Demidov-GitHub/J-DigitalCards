package verbConjugationDisplayModule;

import javafx.scene.control.RadioButton;
import trainConfig.VerbConfig;

public class VerbConjugationSettingsModule extends VerbConjugationDisplayPattern {
    private VerbConfig verbConfig;

    private RadioButton shortForm_L1, shortForm_L2, shortForm_L3, shortForm_L4;
    private RadioButton longForm_L1, longForm_L2, longForm_L3, longForm_L4;
    private RadioButton taiForm_L1, taiForm_L2, taiForm_L3, taiForm_L4;
    private RadioButton teForm_L1;
    private RadioButton shortPot_L1, shortPot_L2, shortPot_L3, shortPot_L4;
    private RadioButton longPot_L1, longPot_L2, longPot_L3, longPot_L4;
    private RadioButton potTeForm_L1;
    private RadioButton volForm_L1, volForm_L2;

    public VerbConjugationSettingsModule(VerbConfig verbConfig) {
        super();
        this.verbConfig = verbConfig;
        setRadioButtons();
        fillGrids();

    }

    private void setRadioButtons() {
        shortForm_L1 = new RadioButton();
        shortForm_L2 = new RadioButton();
        shortForm_L3 = new RadioButton();
        shortForm_L4 = new RadioButton();

    }

    private void fillGrids() {
        //System.out.println(conjugation.toString());
        super.setShortFormGrid(shortForm_L1, shortForm_L2, shortForm_L3, shortForm_L4);/*
        super.setLongFormGrid(longForm_L1, longForm_L2, longForm_L3, longForm_L4);
        super.setTaiGrid(taiForm_L1, taiForm_L2, taiForm_L3, taiForm_L4);
        super.setTeGrid(teForm_L1);
        super.setShortPotGrid(shortPot_L1, shortPot_L2, shortPot_L3, shortPot_L4);
        super.setLongPotGrid(longPot_L1, longPot_L2, longPot_L3, longPot_L4);
        super.setPotTeGrid(potTeForm_L1);
        super.setVolGrid(volForm_L1, volForm_L2);*/

    }
}
