package conjugation;
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

    public static final String IF_POS_PRESENT_NAME = "short present";
    public static final String IF_NEG_PRESENT_NAME = "short negative present";
    public static final String IF_POS_PAST_NAME = "short past";
    public static final String IF_NEG_PAST_NAME = "short negative past";

    public static final String F_POS_PRESENT_NAME = "long present";
    public static final String F_NEG_PRESENT_NAME = "long negative present";
    public static final String F_POS_PAST_NAME = "long past";
    public static final String F_NEG_PAST_NAME = "long negative past";

    public static final String TE_FORM = "て-Form";

    public static final String TAI_POS_PRESENT_NAME = "たい present";
    public static final String TAI_NEG_PRESENT_NAME = "たい negative present";
    public static final String TAI_POS_PAST_NAME = "たい past";
    public static final String TAI_NEG_PAST_NAME = "たい negative past";

    public static final String IF_POT_POS_PRESENT_NAME = "short potential present";
    public static final String IF_POT_NEG_PRESENT_NAME = "short potential negative present";
    public static final String IF_POT_POS_PAST_NAME = "short potential past";
    public static final String IF_POT_NEG_PAST_NAME = "short potential negative past";

    public static final String F_POT_POS_PRESENT_NAME = "long potential present";
    public static final String F_POT_NEG_PRESENT_NAME = "long potential negative present";
    public static final String F_POT_POS_PAST_NAME = "long potential past";
    public static final String F_POT_NEG_PAST_NAME = "long potential negative past";

    public static final String POT_TE_FORM = "potential て-Form";

    public static final String IF_VOL_FORM = "short volitional form";
    public static final String F_VOL_FORM = "long volitional form";



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


    Conjugation(String infinitive) {
        this.infinitive = infinitive;
        ifPosPresent = infinitive;
    }

    public String getFPosPresent() {
        return fPosPresent;
    }

    public String getIFPosPresent() {
        return ifPosPresent;
    }

    public String getFNegPresent() {
        return fNegPresent;
    }

    public String getIFNegPresent() {
        return ifNegPresent;
    }

    public String getFPosPast() {
        return fPosPast;
    }

    public String getIFPosPast() {
        return ifPosPast;
    }

    public String getFNegPast() {
        return fNegPast;
    }

    public String getIFNegPast() {
        return ifNegPast;
    }

    public String getTeForm() {
        return teForm;
    }

    public String getTaiPosPresent() {
        return taiPosPresent;
    }

    public String getTaiNegPresent() {
        return taiNegPresent;
    }

    public String getTaiPosPast() {
        return taiPosPast;
    }

    public String getTaiNegPast() {
        return taiNegPast;
    }

    public String getFPotPosPresent() {
        return fPotPosPresent;
    }

    public String getFPotNegPresent() {
        return fPotNegPresent;
    }

    public String getFPotPosPast() {
        return fPotPosPast;
    }

    public String getFPotNegPast() {
        return fPotNegPast;
    }

    public String getIFPotPosPresent() {
        return ifPotPosPresent;
    }

    public String getIFPotNegPresent() {
        return ifPotNegPresent;
    }

    public String getIFPotPosPast() {
        return ifPotPosPast;
    }

    public String getIFPotNegPast() {
        return ifPotNegPast;
    }

    public String getPotTeForm() {
        return potTeForm;
    }

    public String getFVolForm() {
        return fVolForm;
    }

    public String getIFVolForm() {
        return ifVolForm;
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
