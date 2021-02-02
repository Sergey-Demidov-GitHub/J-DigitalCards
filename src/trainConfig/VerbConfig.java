/*  Copyright (C) 2021 Sergey Demidov   */

package trainConfig;

import java.util.HashMap;

/*
 * configuration setting the parameters for training verbs
 */
public class VerbConfig {
    private boolean defaultMode;                    // display cards as they were created originally
    private HashMap<String, Boolean> configMap;     // which custom forms shall be part of the training process

    public VerbConfig() {
        defaultMode = true;
        fillConfigMap();
    }

    private void fillConfigMap() {
        configMap = new HashMap<String, Boolean>();
        configMap.put("ifPosPresent", false);
        configMap.put("ifNegPresent", false);
        configMap.put("ifPosPast", false);
        configMap.put("ifNegPast", false);

        configMap.put("fPosPresent", false);
        configMap.put("fNegPresent", false);
        configMap.put("fPosPast", false);
        configMap.put("fNegPast", false);

        configMap.put("teForm", false);

        configMap.put("taiPosPresent", false);
        configMap.put("taiNegPresent", false);
        configMap.put("taiPosPast", false);
        configMap.put("taiNegPast", false);

        configMap.put("fPotPosPresent", false);
        configMap.put("fPotNegPresent", false);
        configMap.put("fPotPosPast", false);
        configMap.put("fPotNegPast", false);

        configMap.put("ifPotPosPresent", false);
        configMap.put("ifPotNegPresent", false);
        configMap.put("ifPotPosPast", false);
        configMap.put("ifPotNegPast", false);

        configMap.put("potTeForm", false);

        configMap.put("fVolForm", false);
        configMap.put("ifVolForm", false);
    }

    public HashMap<String, Boolean> getConfigMap() {
        return configMap;
    }

    public void putAllConfigMap(HashMap<String, Boolean> configMap) {
        this.configMap.putAll(configMap);
    }

    public boolean getDefaultMode() {
        return defaultMode;
    }

    public void setDefaultMode(boolean defaultMode){
        this.defaultMode = defaultMode;
    }

    /**
     * counts positive Templates
     * @return number of positive Templates
     */
    public int getCount(){
        int i = 0;
        for (String key : configMap.keySet()) {
            if (configMap.get(key).booleanValue()) {
                i++;
            }
        }
        return i;
    }
}
