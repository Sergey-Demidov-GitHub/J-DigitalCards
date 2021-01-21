package verbConjugationDisplayModule;

import javafx.scene.control.RadioButton;
import trainConfig.VerbConfig;

public class VerbConjugationSettingsModule extends VerbConjugationDisplayPattern {
    private VerbConfig oldVerbConfig;
    private VerbConfig tempVerbConfig;

    private RadioButton shortForm_L1, shortForm_L2, shortForm_L3, shortForm_L4;
    private RadioButton longForm_L1, longForm_L2, longForm_L3, longForm_L4;
    private RadioButton taiForm_L1, taiForm_L2, taiForm_L3, taiForm_L4;
    private RadioButton teForm_L1;
    private RadioButton shortPot_L1, shortPot_L2, shortPot_L3, shortPot_L4;
    private RadioButton longPot_L1, longPot_L2, longPot_L3, longPot_L4;
    private RadioButton potTeForm_L1;
    private RadioButton volForm_L1, volForm_L2;

    public VerbConjugationSettingsModule(VerbConfig oldVerbConfig) {
        super();
        this.oldVerbConfig = oldVerbConfig;

        this.tempVerbConfig = new VerbConfig();
        this.tempVerbConfig.putAllConfigMap(oldVerbConfig.getConfigMap());
        setRadioButtons();
        loadConfig();
        fillGrids();
        setOnActionMethods();
    }

    private void setRadioButtons() {
        shortForm_L1 = new RadioButton();
        shortForm_L2 = new RadioButton();
        shortForm_L3 = new RadioButton();
        shortForm_L4 = new RadioButton();

        longForm_L1 = new RadioButton();
        longForm_L2 = new RadioButton();
        longForm_L3 = new RadioButton();
        longForm_L4 = new RadioButton();

        taiForm_L1 = new RadioButton();
        taiForm_L2 = new RadioButton();
        taiForm_L3 = new RadioButton();
        taiForm_L4 = new RadioButton();

        teForm_L1 = new RadioButton();

        shortPot_L1 = new RadioButton();
        shortPot_L2 = new RadioButton();
        shortPot_L3 = new RadioButton();
        shortPot_L4 = new RadioButton();

        longPot_L1 = new RadioButton();
        longPot_L2 = new RadioButton();
        longPot_L3 = new RadioButton();
        longPot_L4 = new RadioButton();

        potTeForm_L1 = new RadioButton();

        volForm_L1 = new RadioButton();
        volForm_L2 = new RadioButton();
    }

    private void loadConfig() {
        shortForm_L1.setSelected(tempVerbConfig.getConfigMap().get("ifPosPresent"));
        shortForm_L2.setSelected(tempVerbConfig.getConfigMap().get("ifNegPresent"));
        shortForm_L3.setSelected(tempVerbConfig.getConfigMap().get("ifPosPast"));
        shortForm_L4.setSelected(tempVerbConfig.getConfigMap().get("ifNegPast"));

        longForm_L1.setSelected(tempVerbConfig.getConfigMap().get("fPosPresent"));
        longForm_L2.setSelected(tempVerbConfig.getConfigMap().get("fNegPresent"));
        longForm_L3.setSelected(tempVerbConfig.getConfigMap().get("fPosPast"));
        longForm_L4.setSelected(tempVerbConfig.getConfigMap().get("fNegPast"));

        taiForm_L1.setSelected(tempVerbConfig.getConfigMap().get("taiPosPresent"));
        taiForm_L2.setSelected(tempVerbConfig.getConfigMap().get("taiNegPresent"));
        taiForm_L3.setSelected(tempVerbConfig.getConfigMap().get("taiPosPast"));
        taiForm_L4.setSelected(tempVerbConfig.getConfigMap().get("taiNegPast"));

        teForm_L1.setSelected(tempVerbConfig.getConfigMap().get("teForm"));

        shortPot_L1.setSelected(tempVerbConfig.getConfigMap().get("ifPotPosPresent"));
        shortPot_L2.setSelected(tempVerbConfig.getConfigMap().get("ifPotNegPresent"));
        shortPot_L3.setSelected(tempVerbConfig.getConfigMap().get("ifPotPosPast"));
        shortPot_L4.setSelected(tempVerbConfig.getConfigMap().get("ifPotNegPast"));

        longPot_L1.setSelected(tempVerbConfig.getConfigMap().get("fPotPosPresent"));
        longPot_L2.setSelected(tempVerbConfig.getConfigMap().get("fPotNegPresent"));
        longPot_L3.setSelected(tempVerbConfig.getConfigMap().get("fPotPosPast"));
        longPot_L4.setSelected(tempVerbConfig.getConfigMap().get("fPotNegPast"));

        potTeForm_L1.setSelected(tempVerbConfig.getConfigMap().get("potTeForm"));

        volForm_L1.setSelected(tempVerbConfig.getConfigMap().get("ifVolForm"));
        volForm_L2.setSelected(tempVerbConfig.getConfigMap().get("fVolForm"));
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

    private void setOnActionMethods() {
        shortForm_L1.setOnAction(e -> {
            tempVerbConfig.getConfigMap().put("ifPosPresent", shortForm_L1.isSelected());
        });
        shortForm_L2.setOnAction(e -> {
            tempVerbConfig.getConfigMap().put("ifNegPresent", shortForm_L2.isSelected());
        });
        shortForm_L3.setOnAction(e -> {
            tempVerbConfig.getConfigMap().put("ifPosPast", shortForm_L3.isSelected());
        });
        shortForm_L4.setOnAction(e -> {
            tempVerbConfig.getConfigMap().put("ifNegPast", shortForm_L4.isSelected());
        });
        //==========================================================================
        longForm_L1.setOnAction(e -> {
            tempVerbConfig.getConfigMap().put("fPosPresent", longForm_L1.isSelected());
        });
        longForm_L2.setOnAction(e -> {
            tempVerbConfig.getConfigMap().put("fNegPresent", longForm_L2.isSelected());
        });
        longForm_L3.setOnAction(e -> {
            tempVerbConfig.getConfigMap().put("fPosPast", longForm_L3.isSelected());
        });
        longForm_L4.setOnAction(e -> {
            tempVerbConfig.getConfigMap().put("fNegPast", longForm_L4.isSelected());
        });
        //==========================================================================
        taiForm_L1.setOnAction(e -> {
            tempVerbConfig.getConfigMap().put("taiPosPresent", taiForm_L1.isSelected());
        });
        taiForm_L2.setOnAction(e -> {
            tempVerbConfig.getConfigMap().put("taiNegPresent", taiForm_L2.isSelected());
        });
        taiForm_L3.setOnAction(e -> {
            tempVerbConfig.getConfigMap().put("taiPosPast", taiForm_L3.isSelected());
        });
        taiForm_L4.setOnAction(e -> {
            tempVerbConfig.getConfigMap().put("taiNegPast", taiForm_L4.isSelected());
        });
        //==========================================================================
        teForm_L1.setOnAction(e -> {
            tempVerbConfig.getConfigMap().put("teForm", teForm_L1.isSelected());
        });
        //==========================================================================
        shortPot_L1.setOnAction(e -> {
            tempVerbConfig.getConfigMap().put("ifPotPosPresent", shortPot_L1.isSelected());
        });
        shortPot_L2.setOnAction(e -> {
            tempVerbConfig.getConfigMap().put("ifPotNegPresent", shortPot_L2.isSelected());
        });
        shortPot_L3.setOnAction(e -> {
            tempVerbConfig.getConfigMap().put("ifPotPosPast", shortPot_L3.isSelected());
        });
        shortPot_L4.setOnAction(e -> {
            tempVerbConfig.getConfigMap().put("ifPotNegPast", shortPot_L4.isSelected());
        });
        //==========================================================================
        longPot_L1.setOnAction(e -> {
            tempVerbConfig.getConfigMap().put("fPotPosPresent", longPot_L1.isSelected());
        });
        longPot_L2.setOnAction(e -> {
            tempVerbConfig.getConfigMap().put("fPotNegPresent", longPot_L2.isSelected());
        });
        longPot_L3.setOnAction(e -> {
            tempVerbConfig.getConfigMap().put("fPotPosPast", longPot_L3.isSelected());
        });
        longPot_L4.setOnAction(e -> {
            tempVerbConfig.getConfigMap().put("fPotNegPast", longPot_L4.isSelected());
        });
        //==========================================================================
        potTeForm_L1.setOnAction(e -> {
            tempVerbConfig.getConfigMap().put("potTeForm", potTeForm_L1.isSelected());
        });
        //==========================================================================
        volForm_L1.setOnAction(e -> {
            tempVerbConfig.getConfigMap().put("ifVolForm", volForm_L1.isSelected());
        });
        volForm_L2.setOnAction(e -> {
            tempVerbConfig.getConfigMap().put("fVolForm", volForm_L2.isSelected());
        });
    }

    public VerbConfig getOldConfig() {
        oldVerbConfig.setDefaultMode(isOldConfigDefault());
        return oldVerbConfig;
    }

    public VerbConfig getTempConfig() {
        tempVerbConfig.setDefaultMode(isTempConfigDefault());
        return tempVerbConfig;
    }

    public boolean isOldConfigDefault() {
        return isDefaultVerbConfig(oldVerbConfig);
    }

    public boolean isTempConfigDefault() {
        return isDefaultVerbConfig(tempVerbConfig);
    }

    private boolean isDefaultVerbConfig(VerbConfig verbConfig) {
        return verbConfig.getCount() == 0;
    }


}
