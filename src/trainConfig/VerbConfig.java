package trainConfig;

import java.util.HashMap;
import java.util.Map;

/*
 * configuration setting the parameters for training verbs
 */
public class VerbConfig {
    private boolean defaultMode;

    private boolean ifPosPresent = false, ifNegPresent = false, ifPosPast = false, ifNegPast = false;
    private boolean fPosPresent = false, fNegPresent = false, fPosPast = false, fNegPast = false;
    private boolean teForm = true;
    private boolean taiPosPresent = true, taiNegPresent = true, taiPosPast = true, taiNegPast = true;
    private boolean fPotPosPresent = false, fPotNegPresent = false, fPotPosPast = false, fPotNegPast = false;
    private boolean ifPotPosPresent = false, ifPotNegPresent = false, ifPotPosPast = false, ifPotNegPast = false;
    private boolean potTeForm = false;
    private boolean fVolForm = false, ifVolForm = false;

    private Map<String, Boolean> configMap;

    public VerbConfig() {
        defaultMode = false;
        fillConfigMap();
    }

    private void fillConfigMap() {
        configMap = new HashMap<String, Boolean>();
        configMap.put("ifPosPresent", ifPosPresent);
        configMap.put("ifNegPresent", ifNegPresent);
        configMap.put("ifPosPast", ifPosPast);
        configMap.put("ifNegPast", ifNegPast);

        configMap.put("fPosPresent", fPosPresent);
        configMap.put("fNegPresent", fNegPresent);
        configMap.put("fPosPast", fPosPast);
        configMap.put("fNegPast", fNegPast);

        configMap.put("teForm", teForm);

        configMap.put("taiPosPresent", taiPosPresent);
        configMap.put("taiNegPresent", taiNegPresent);
        configMap.put("taiPosPast", taiPosPast);
        configMap.put("taiNegPast", taiNegPast);

        configMap.put("fPotPosPresent", fPotPosPresent);
        configMap.put("fPotNegPresent", fPotNegPresent);
        configMap.put("fPotPosPast", fPotPosPast);
        configMap.put("fPotNegPast", fPotNegPast);

        configMap.put("ifPotPosPresent", ifPotPosPresent);
        configMap.put("ifPotNegPresent", ifPotNegPresent);
        configMap.put("ifPotPosPast", ifPotPosPast);
        configMap.put("ifPotNegPast", ifPotNegPast);

        configMap.put("potTeForm", potTeForm);

        configMap.put("fVolForm", fVolForm);
        configMap.put("ifVolForm", ifVolForm);
    }

    public Map<String, Boolean> getConfigMap() {
        return configMap;
    }

    public boolean getDefaultMode() {
        return defaultMode;
    }

    /*
     * counts positive Templates
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




    @Override
    public String toString() {
        return "VerbConfig{" +
                "defaultMode=" + defaultMode +
                ", ifPosPresent=" + ifPosPresent +
                ", ifNegPresent=" + ifNegPresent +
                ", ifPosPast=" + ifPosPast +
                ", ifNegPast=" + ifNegPast + "\n" +
                ", fPosPresent=" + fPosPresent +
                ", fNegPresent=" + fNegPresent +
                ", fPosPast=" + fPosPast +
                ", fNegPast=" + fNegPast + "\n" +
                ", teForm=" + teForm + "\n" +
                ", taiPosPresent=" + taiPosPresent +
                ", taiNegPresent=" + taiNegPresent +
                ", taiPosPast=" + taiPosPast +
                ", taiNegPast=" + taiNegPast + "\n" +
                ", fPotPosPresent=" + fPotPosPresent +
                ", fPotNegPresent=" + fPotNegPresent +
                ", fPotPosPast=" + fPotPosPast +
                ", fPotNegPast=" + fPotNegPast + "\n" +
                ", ifPotPosPresent=" + ifPotPosPresent +
                ", ifPotNegPresent=" + ifPotNegPresent +
                ", ifPotPosPast=" + ifPotPosPast +
                ", ifPotNegPast=" + ifPotNegPast + "\n" +
                ", potTeForm=" + potTeForm + "\n" +
                ", fVolForm=" + fVolForm +
                ", ifVolForm=" + ifVolForm + "\n" +
                '}';
    }
}
