package trainConfig;

/*
 * configuration setting the parameters for training verbs
 */
public class VerbConfig {
    private boolean defaultMode;

    private boolean ifPosPresent = false, ifNegPresent = false, ifPosPast = false, ifNegPast = false;
    private boolean fPosPresent = false, fNegPresent = false, fPosPast = false, fNegPast = false;
    private boolean teForm = false;
    private boolean taiPosPresent = false, taiNegPresent = false, taiPosPast = false, taiNegPast = false;
    private boolean fPotPosPresent = false, fPotNegPresent = false, fPotPosPast = false, fPotNegPast = false;
    private boolean ifPotPosPresent = false, ifPotNegPresent = false, ifPotPosPast = false, ifPotNegPast = false;
    private boolean potTeForm = false;
    private boolean fVolForm = false, ifVolForm = false;

    public VerbConfig() {
        defaultMode = true;
    }

    public boolean isDefaultMode() {
        return defaultMode;
    }

    public void setDefaultMode(boolean defaultMode) {
        this.defaultMode = defaultMode;
    }

    public boolean isIfPosPresent() {
        return ifPosPresent;
    }

    public void setIfPosPresent(boolean ifPosPresent) {
        this.ifPosPresent = ifPosPresent;
    }

    public boolean isIfNegPresent() {
        return ifNegPresent;
    }

    public void setIfNegPresent(boolean ifNegPresent) {
        this.ifNegPresent = ifNegPresent;
    }

    public boolean isIfPosPast() {
        return ifPosPast;
    }

    public void setIfPosPast(boolean ifPosPast) {
        this.ifPosPast = ifPosPast;
    }

    public boolean isIfNegPast() {
        return ifNegPast;
    }

    public void setIfNegPast(boolean ifNegPast) {
        this.ifNegPast = ifNegPast;
    }

    public boolean isfPosPresent() {
        return fPosPresent;
    }

    public void setfPosPresent(boolean fPosPresent) {
        this.fPosPresent = fPosPresent;
    }

    public boolean isfNegPresent() {
        return fNegPresent;
    }

    public void setfNegPresent(boolean fNegPresent) {
        this.fNegPresent = fNegPresent;
    }

    public boolean isfPosPast() {
        return fPosPast;
    }

    public void setfPosPast(boolean fPosPast) {
        this.fPosPast = fPosPast;
    }

    public boolean isfNegPast() {
        return fNegPast;
    }

    public void setfNegPast(boolean fNegPast) {
        this.fNegPast = fNegPast;
    }

    public boolean isTeForm() {
        return teForm;
    }

    public void setTeForm(boolean teForm) {
        this.teForm = teForm;
    }

    public boolean isTaiPosPresent() {
        return taiPosPresent;
    }

    public void setTaiPosPresent(boolean taiPosPresent) {
        this.taiPosPresent = taiPosPresent;
    }

    public boolean isTaiNegPresent() {
        return taiNegPresent;
    }

    public void setTaiNegPresent(boolean taiNegPresent) {
        this.taiNegPresent = taiNegPresent;
    }

    public boolean isTaiPosPast() {
        return taiPosPast;
    }

    public void setTaiPosPast(boolean taiPosPast) {
        this.taiPosPast = taiPosPast;
    }

    public boolean isTaiNegPast() {
        return taiNegPast;
    }

    public void setTaiNegPast(boolean taiNegPast) {
        this.taiNegPast = taiNegPast;
    }

    public boolean isfPotPosPresent() {
        return fPotPosPresent;
    }

    public void setfPotPosPresent(boolean fPotPosPresent) {
        this.fPotPosPresent = fPotPosPresent;
    }

    public boolean isfPotNegPresent() {
        return fPotNegPresent;
    }

    public void setfPotNegPresent(boolean fPotNegPresent) {
        this.fPotNegPresent = fPotNegPresent;
    }

    public boolean isfPotPosPast() {
        return fPotPosPast;
    }

    public void setfPotPosPast(boolean fPotPosPast) {
        this.fPotPosPast = fPotPosPast;
    }

    public boolean isfPotNegPast() {
        return fPotNegPast;
    }

    public void setfPotNegPast(boolean fPotNegPast) {
        this.fPotNegPast = fPotNegPast;
    }

    public boolean isIfPotPosPresent() {
        return ifPotPosPresent;
    }

    public void setIfPotPosPresent(boolean ifPotPosPresent) {
        this.ifPotPosPresent = ifPotPosPresent;
    }

    public boolean isIfPotNegPresent() {
        return ifPotNegPresent;
    }

    public void setIfPotNegPresent(boolean ifPotNegPresent) {
        this.ifPotNegPresent = ifPotNegPresent;
    }

    public boolean isIfPotPosPast() {
        return ifPotPosPast;
    }

    public void setIfPotPosPast(boolean ifPotPosPast) {
        this.ifPotPosPast = ifPotPosPast;
    }

    public boolean isIfPotNegPast() {
        return ifPotNegPast;
    }

    public void setIfPotNegPast(boolean ifPotNegPast) {
        this.ifPotNegPast = ifPotNegPast;
    }

    public boolean isPotTeForm() {
        return potTeForm;
    }

    public void setPotTeForm(boolean potTeForm) {
        this.potTeForm = potTeForm;
    }

    public boolean isfVolForm() {
        return fVolForm;
    }

    public void setfVolForm(boolean fVolForm) {
        this.fVolForm = fVolForm;
    }

    public boolean isIfVolForm() {
        return ifVolForm;
    }

    public void setIfVolForm(boolean ifVolForm) {
        this.ifVolForm = ifVolForm;
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
