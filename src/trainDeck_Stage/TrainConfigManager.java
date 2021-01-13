package trainDeck_Stage;

import cardPackage.Card;
import main.MainController;
import trainConfig.TrainConfig;
import trainConfig.VerbConfig;

import java.util.*;

public class TrainConfigManager {
    private MainController mainController;
    private TrainDeck_Controller trainDeckController;
    private TrainConfig trainConfig;
    private Card card;
    private Map<Integer, ConjugationTemplateProvider> conjugationMap;

    private Set<Integer> verbIds;

    TrainConfigManager(MainController mainController, TrainDeck_Controller trainDeckController, TrainConfig trainConfig) {
        this.mainController = mainController;
        this.trainDeckController = trainDeckController;
        this.trainConfig = trainConfig;

        int verbMultiplier = trainConfig.getVerbConfig().getCount();
        mainController.getSession().extendCallMask(verbMultiplier, 4);
        verbIds = mainController.getSession().getVerbIds();

        setupConjugationMap();
    }

    public void setCard(Card card) {
        this.card = card;

        //System.out.println(card.getId());
        lookUpForm();
    }

    private void setupConjugationMap() {
        // (id -> leftConjugations as lookUpStrings) later: lookUpStrings -> actual conjugation
        conjugationMap = new HashMap<>();
        for (Integer verbId : verbIds) {
            ConjugationTemplateProvider conjugationTemplateProvider =
                    new ConjugationTemplateProvider(trainConfig.getVerbConfig());

            conjugationMap.put(verbId, conjugationTemplateProvider);
        }
        conjugationMap.keySet().forEach(key -> System.out.println("key: " + key + " value: " + conjugationMap.get(key).toString()));
    }


    private void lookUpForm() {
        int id = card.getId();
        if (!trainConfig.getVerbConfig().getDefaultMode() &&
            verbIds.contains(id)) {

            //TODO: fix display order
            String verbFormTemplate = null;
            if (!mainController.getSession().getIdChanged()){
                if (mainController.getSession().getIsNext()) {
                    verbFormTemplate = conjugationMap.get(id).getNextTemplate();
                } else {
                    verbFormTemplate = conjugationMap.get(id).getPreviousTemplate();
                }
            } else {
                verbFormTemplate = conjugationMap.get(id).getCurrentTemplate();
            }

            String verbForm = trainDeckController.getCurrentConjugation().getLookUpMapValue(verbFormTemplate);

            System.out.println("template: " + verbFormTemplate + " | verbForm: " + verbForm);
        }
    }

}

class ConjugationTemplateProvider {
    ArrayList<String> verbFormTemplates;
    int index;
    int maxIndex;

    public ConjugationTemplateProvider(VerbConfig verbConfig) {
        Map<String, Boolean> verbConfigMap = verbConfig.getConfigMap();


        verbFormTemplates = new ArrayList<String>();
        for (String key : verbConfigMap.keySet()) {
            if (verbConfigMap.get(key).booleanValue()) {
                verbFormTemplates.add(key);
            }
        }
        Collections.shuffle(verbFormTemplates, new Random());
        maxIndex = verbFormTemplates.size() - 1;
        index = 0;
        System.out.println(verbFormTemplates.size());

    }

    public String getCurrentTemplate() {
        return verbFormTemplates.get(index);
    }

    public String getPreviousTemplate() {
        if (index > 0){
            index--;
        } else {
            index = maxIndex;
        }

        return verbFormTemplates.get(index);
    }

    public String getNextTemplate() {
        if (index < maxIndex){
            index++;
        } else {
            index = 0;
        }

        return verbFormTemplates.get(index);
    }

    @Override
    public String toString() {
        return "ConjugationTemplateProvider{" +
                "verbFormTemplates=" + verbFormTemplates +
                ", index=" + index +
                '}';
    }
}
