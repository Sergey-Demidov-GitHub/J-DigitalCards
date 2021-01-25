package conjugation;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/*
 * IF   - Informal
 * F    - Formal
 * Pos  - positive
 * Neg  - negative
 * Past
 * Present
 * Tai  - Tai Form
 * Pot  - potential Form
 * Vol  - volitional Form
 */
public class Conjugation {
    protected String infinitive;

    public static final Map<String, String> VERB_FORMS_NAMES_MAP = Collections.unmodifiableMap(new HashMap<String, String>() {
        {
            put("ifPosPresent", "short present");
            put("ifNegPresent", "short negative present");
            put("ifPosPast", "short past");
            put("ifNegPast", "short negative past");

            put("fPosPresent", "long present");
            put("fNegPresent", "long negative present");
            put("fPosPast", "long past");
            put("fNegPast", "long negative past");

            put("taiPosPresent", "たい present");
            put("taiNegPresent", "たい negative present");
            put("taiPosPast", "たい past");
            put("taiNegPast", "たい negative past");

            put("teForm", "て-Form");

            put("ifPotPosPresent", "short potential present");
            put("ifPotNegPresent", "short potential negative present");
            put("ifPotPosPast", "short potential past");
            put("ifPotNegPast", "short potential negative past");

            put("fPotPosPresent", "long potential present");
            put("fPotNegPresent", "long potential negative present");
            put("fPotPosPast", "long potential past");
            put("fPotNegPast", "long potential negative past");

            put("potTeForm", "potential て-Form");

            put("ifVolForm", "short volitional form");
            put("fVolForm", "long volitional form");
        }
    });

    protected String ifPosPresent;
    protected String ifNegPresent;
    protected String ifPosPast;
    protected String ifNegPast;

    protected String fPosPresent;
    protected String fNegPresent;
    protected String fPosPast;
    protected String fNegPast;

    protected String teForm;

    protected String taiPosPresent;
    protected String taiNegPresent;
    protected String taiPosPast;
    protected String taiNegPast;

    protected String fPotPosPresent;
    protected String fPotNegPresent;
    protected String fPotPosPast;
    protected String fPotNegPast;

    protected String ifPotPosPresent;
    protected String ifPotNegPresent;
    protected String ifPotPosPast;
    protected String ifPotNegPast;

    protected String potTeForm;

    protected String fVolForm;
    protected String ifVolForm;

    Map<String, String> lookUpMap;


    Conjugation(String infinitive) {
        this.infinitive = infinitive;
        ifPosPresent = infinitive;
    }

    protected void fillLookUpMap() {
        lookUpMap = new HashMap<String, String>();
        lookUpMap.put("ifPosPresent", ifPosPresent);
        lookUpMap.put("ifNegPresent", ifNegPresent);
        lookUpMap.put("ifPosPast", ifPosPast);
        lookUpMap.put("ifNegPast", ifNegPast);

        lookUpMap.put("fPosPresent", fPosPresent);
        lookUpMap.put("fNegPresent", fNegPresent);
        lookUpMap.put("fPosPast", fPosPast);
        lookUpMap.put("fNegPast", fNegPast);

        lookUpMap.put("teForm", teForm);

        lookUpMap.put("taiPosPresent", taiPosPresent);
        lookUpMap.put("taiNegPresent", taiNegPresent);
        lookUpMap.put("taiPosPast", taiPosPast);
        lookUpMap.put("taiNegPast", taiNegPast);

        lookUpMap.put("fPotPosPresent", fPotPosPresent);
        lookUpMap.put("fPotNegPresent", fPotNegPresent);
        lookUpMap.put("fPotPosPast", fPotPosPast);
        lookUpMap.put("fPotNegPast", fPotNegPast);

        lookUpMap.put("ifPotPosPresent", ifPotPosPresent);
        lookUpMap.put("ifPotNegPresent", ifPotNegPresent);
        lookUpMap.put("ifPotPosPast", ifPotPosPast);
        lookUpMap.put("ifPotNegPast", ifPotNegPast);

        lookUpMap.put("potTeForm", potTeForm);

        lookUpMap.put("fVolForm", fVolForm);
        lookUpMap.put("ifVolForm", ifVolForm);
    }

    public void changeLookUpMap(String key, String value) {
        lookUpMap.put(key, value);
    }

    public String getLookUpMapValue(String key) {
        return lookUpMap.get(key);
    }

    public String getFPosPresent() {
        return lookUpMap.get("fPosPresent");
    }

    public String getIFPosPresent() {
        return lookUpMap.get("ifPosPresent");
    }

    public String getFNegPresent() {
        return lookUpMap.get("fNegPresent");
    }

    public String getIFNegPresent() {
        return lookUpMap.get("ifNegPresent");
    }

    public String getFPosPast() {
        return lookUpMap.get("fPosPast");
    }

    public String getIFPosPast() {
        return lookUpMap.get("ifPosPast");
    }

    public String getFNegPast() {
        return lookUpMap.get("fNegPast");
    }

    public String getIFNegPast() {
        return lookUpMap.get("ifNegPast");
    }

    public String getTeForm() {
        return lookUpMap.get("teForm");
    }

    public String getTaiPosPresent() {
        return lookUpMap.get("taiPosPresent");
    }

    public String getTaiNegPresent() {
        return lookUpMap.get("taiNegPresent");
    }

    public String getTaiPosPast() {
        return lookUpMap.get("taiPosPast");
    }

    public String getTaiNegPast() {
        return lookUpMap.get("taiNegPast");
    }

    public String getFPotPosPresent() {
        return lookUpMap.get("fPotPosPresent");
    }

    public String getFPotNegPresent() {
        return lookUpMap.get("fPotNegPresent");
    }

    public String getFPotPosPast() {
        return lookUpMap.get("fPotPosPast");
    }

    public String getFPotNegPast() {
        return lookUpMap.get("fPotNegPast");
    }

    public String getIFPotPosPresent() {
        return lookUpMap.get("ifPotPosPresent");
    }

    public String getIFPotNegPresent() {
        return lookUpMap.get("ifPotNegPresent");
    }

    public String getIFPotPosPast() {
        return lookUpMap.get("ifPotPosPast");
    }

    public String getIFPotNegPast() {
        return lookUpMap.get("ifPotNegPast");
    }

    public String getPotTeForm() {
        return lookUpMap.get("potTeForm");
    }

    public String getFVolForm() {
        return lookUpMap.get("fVolForm");
    }

    public String getIFVolForm() {
        return lookUpMap.get("ifVolForm");
    }

    @Override
    public String toString() {
        return "Conjugation{" +
                "infinitive='" + infinitive + '\'' + "\n" +
                ", ifPosPresent='" + ifPosPresent + '\'' +
                ", ifNegPresent='" + ifNegPresent + '\'' +
                ", ifPosPast='" + ifPosPast + '\'' +
                ", ifNegPast='" + ifNegPast + '\'' + "\n" +
                ", fPosPresent='" + fPosPresent + '\'' +
                ", fNegPresent='" + fNegPresent + '\'' +
                ", fPosPast='" + fPosPast + '\'' +
                ", fNegPast='" + fNegPast + '\'' + "\n" +
                ", teForm='" + teForm + '\'' + "\n" +
                ", taiPosPresent='" + taiPosPresent + '\'' +
                ", taiNegPresent='" + taiNegPresent + '\'' +
                ", taiPosPast='" + taiPosPast + '\'' +
                ", taiNegPast='" + taiNegPast + '\'' + "\n" +
                ", ifPotPosPresent='" + ifPotPosPresent + '\'' +
                ", ifPotNegPresent='" + ifPotNegPresent + '\'' +
                ", ifPotPosPast='" + ifPotPosPast + '\'' +
                ", ifPotNegPast='" + ifPotNegPast + '\'' + "\n" +
                ", fPotPosPresent='" + fPotPosPresent + '\'' +
                ", fPotNegPresent='" + fPotNegPresent + '\'' +
                ", fPotPosPast='" + fPotPosPast + '\'' +
                ", fPotNegPast='" + fPotNegPast + '\'' + "\n" +
                ", PotTeForm='" + potTeForm + '\'' + "\n" +
                ", fVolForm='" + fVolForm + '\'' +
                ", ifVolForm='" + ifVolForm + '\'' +
                '}';
    }
}
