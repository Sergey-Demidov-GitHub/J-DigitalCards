/*  Copyright (C) 2021 Sergey Demidov   */

package trainConfig;

public class TrainConfig {
    private VerbConfig verbConfig;

    public TrainConfig() {
        verbConfig = new VerbConfig();
    }

    public VerbConfig getVerbConfig() {
        return verbConfig;
    }

    public void setVerbConfig(VerbConfig verbConfig){
        this.verbConfig = verbConfig;
    }
}
