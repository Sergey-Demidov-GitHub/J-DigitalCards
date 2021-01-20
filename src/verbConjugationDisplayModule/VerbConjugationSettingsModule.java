package verbConjugationDisplayModule;

import javafx.scene.control.RadioButton;
import trainConfig.VerbConfig;

import java.util.HashMap;
import java.util.Map;

public class VerbConjugationSettingsModule extends VerbConjugationDisplayPattern {
    private VerbConfig verbConfig;
    private Map<String, Boolean> tempConfigMap;
    private Map<String, Boolean> oldConfigMap;

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
        this.oldConfigMap = verbConfig.getConfigMap();
        this.tempConfigMap = new HashMap<String, Boolean>();
        this.tempConfigMap.putAll(oldConfigMap);
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
        shortForm_L1.setSelected(tempConfigMap.get("ifPosPresent"));
        shortForm_L2.setSelected(tempConfigMap.get("ifNegPresent"));
        shortForm_L3.setSelected(tempConfigMap.get("ifPosPast"));
        shortForm_L4.setSelected(tempConfigMap.get("ifNegPast"));

        longForm_L1.setSelected(tempConfigMap.get("fPosPresent"));
        longForm_L2.setSelected(tempConfigMap.get("fNegPresent"));
        longForm_L3.setSelected(tempConfigMap.get("fPosPast"));
        longForm_L4.setSelected(tempConfigMap.get("fNegPast"));

        taiForm_L1.setSelected(tempConfigMap.get("taiPosPresent"));
        taiForm_L2.setSelected(tempConfigMap.get("taiNegPresent"));
        taiForm_L3.setSelected(tempConfigMap.get("taiPosPast"));
        taiForm_L4.setSelected(tempConfigMap.get("taiNegPast"));

        teForm_L1.setSelected(tempConfigMap.get("teForm"));

        shortPot_L1.setSelected(tempConfigMap.get("ifPotPosPresent"));
        shortPot_L2.setSelected(tempConfigMap.get("ifPotNegPresent"));
        shortPot_L3.setSelected(tempConfigMap.get("ifPotPosPast"));
        shortPot_L4.setSelected(tempConfigMap.get("ifPotNegPast"));

        longPot_L1.setSelected(tempConfigMap.get("fPotPosPresent"));
        longPot_L2.setSelected(tempConfigMap.get("fPotNegPresent"));
        longPot_L3.setSelected(tempConfigMap.get("fPotPosPast"));
        longPot_L4.setSelected(tempConfigMap.get("fPotNegPast"));

        potTeForm_L1.setSelected(tempConfigMap.get("potTeForm"));

        volForm_L1.setSelected(tempConfigMap.get("ifVolForm"));
        volForm_L2.setSelected(tempConfigMap.get("fVolForm"));
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
            tempConfigMap.put("ifPosPresent", shortForm_L1.isSelected());
        });
        shortForm_L2.setOnAction(e -> {
            tempConfigMap.put("ifNegPresent", shortForm_L2.isSelected());
        });
        shortForm_L3.setOnAction(e -> {
            tempConfigMap.put("ifPosPast", shortForm_L3.isSelected());
        });
        shortForm_L4.setOnAction(e -> {
            tempConfigMap.put("ifNegPast", shortForm_L4.isSelected());
        });
        //==========================================================================
        longForm_L1.setOnAction(e -> {
            tempConfigMap.put("fPosPresent", longForm_L1.isSelected());
        });
        longForm_L2.setOnAction(e -> {
            tempConfigMap.put("fNegPresent", longForm_L2.isSelected());
        });
        longForm_L3.setOnAction(e -> {
            tempConfigMap.put("fPosPast", longForm_L3.isSelected());
        });
        longForm_L4.setOnAction(e -> {
            tempConfigMap.put("fNegPast", longForm_L4.isSelected());
        });
        //==========================================================================
        taiForm_L1.setOnAction(e -> {
            tempConfigMap.put("taiPosPresent", taiForm_L1.isSelected());
        });
        taiForm_L2.setOnAction(e -> {
            tempConfigMap.put("taiNegPresent", taiForm_L2.isSelected());
        });
        taiForm_L3.setOnAction(e -> {
            tempConfigMap.put("taiPosPast", taiForm_L3.isSelected());
        });
        taiForm_L4.setOnAction(e -> {
            tempConfigMap.put("taiNegPast", taiForm_L4.isSelected());
        });
        //==========================================================================
        teForm_L1.setOnAction(e -> {
            tempConfigMap.put("teForm", teForm_L1.isSelected());
        });
        //==========================================================================
        shortPot_L1.setOnAction(e -> {
            tempConfigMap.put("ifPotPosPresent", shortPot_L1.isSelected());
        });
        shortPot_L2.setOnAction(e -> {
            tempConfigMap.put("ifPotNegPresent", shortPot_L2.isSelected());
        });
        shortPot_L3.setOnAction(e -> {
            tempConfigMap.put("ifPotPosPast", shortPot_L3.isSelected());
        });
        shortPot_L4.setOnAction(e -> {
            tempConfigMap.put("ifPotNegPast", shortPot_L4.isSelected());
        });
        //==========================================================================
        longPot_L1.setOnAction(e -> {
            tempConfigMap.put("fPotPosPresent", longPot_L1.isSelected());
        });
        longPot_L2.setOnAction(e -> {
            tempConfigMap.put("fPotNegPresent", longPot_L2.isSelected());
        });
        longPot_L3.setOnAction(e -> {
            tempConfigMap.put("fPotPosPast", longPot_L3.isSelected());
        });
        longPot_L4.setOnAction(e -> {
            tempConfigMap.put("fPotNegPast", longPot_L4.isSelected());
        });
        //==========================================================================
        potTeForm_L1.setOnAction(e -> {
            tempConfigMap.put("potTeForm", potTeForm_L1.isSelected());
        });
        //==========================================================================
        volForm_L1.setOnAction(e -> {
            tempConfigMap.put("ifVolForm", volForm_L1.isSelected());
        });
        volForm_L2.setOnAction(e -> {
            tempConfigMap.put("fVolForm", volForm_L2.isSelected());
        });
    }

    public Map<String, Boolean> getTempConfigMap() {
        return tempConfigMap;
    }

    public Map<String, Boolean> getOldConfigMap() {
        return oldConfigMap;
    }
}
