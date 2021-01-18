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

        if (!trainConfig.getVerbConfig().getDefaultMode()) {
            int verbMultiplier = trainConfig.getVerbConfig().getCount();
            mainController.getSession().extendCallMask(verbMultiplier, 4);
            verbIds = mainController.getSession().getVerbIds();
            setupConjugationMap();
        }
    }

    public void setCard(Card card) {
        this.card = card;
        //System.out.println(card.getId());
        lookUpForm();
    }

    private void setupConjugationMap() {
        // (id -> leftConjugations as template) later: template -> actually conjugated verb
        conjugationMap = new HashMap<>();
        for (Integer verbId : verbIds) {
            ConjugationTemplateProvider conjugationTemplateProvider =
                    new ConjugationTemplateProvider(trainConfig.getVerbConfig());

            conjugationMap.put(verbId, conjugationTemplateProvider);
        }
        conjugationMap.keySet().forEach(key -> System.out.println("key: " + key + " value: " + conjugationMap.get(key).toString()));
    }

    /* execution on loading new card
     * verbFormTemplate -> verbForm
     */
    private void lookUpForm() {
        int id = card.getId();
        if (verbIds.contains(id) && !trainConfig.getVerbConfig().getDefaultMode()) {
            int newCallMaskPosition = mainController.getSession().getCallMaskIndex();
            int overflowCounter = mainController.getSession().getOverflowCounter();
            conjugationMap.get(id).updateCallMaskPosition(overflowCounter, newCallMaskPosition);
            String verbFormTemplate = conjugationMap.get(id).getCurrentTemplate();

            String verbForm = trainDeckController.getCurrentConjugation().getLookUpMapValue(verbFormTemplate);

            System.out.println(id + " | " + conjugationMap.get(id).toString());
        }
    }

}
/*
 * Creates ArrayList with verbFormTemplates in a random order.
 * Synchronises the retrieval of verbFormTemplates with the
 * requested viewing direction from the TrainDeck_Controller.
 */
class ConjugationTemplateProvider {
    private ArrayList<String> verbFormTemplates;
    private int index;
    private int maxIndex;

    private int callMaskPosition = -1;       // last callMask position of card with said id
    private int overflowCounter = 0;

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

    private void previousIndex() {
        if (index > 0){
            index--;
        } else {
            index = maxIndex;
        }

    }

    private void nextIndex() {
        if (index < maxIndex){
            index++;
        } else {
            index = 0;
        }
    }


    public void updateCallMaskPosition(int overflowCounter, int callMaskPosition) {

        if (this.callMaskPosition == -1) {
            this.callMaskPosition = callMaskPosition;
            this.overflowCounter = overflowCounter;
        } else {
            if ((this.callMaskPosition < callMaskPosition && this.overflowCounter == overflowCounter)
                    || this.overflowCounter < overflowCounter) {
                //System.out.println("isRightOverflow: " + session.isRightOverflow());
                nextIndex();
            }
            if ((this.callMaskPosition > callMaskPosition && this.overflowCounter == overflowCounter)
                    || this.overflowCounter > overflowCounter) {
                //System.out.println("isLeftOverflow: " + session.isLeftOverflow());
                previousIndex();
            }

            this.overflowCounter = overflowCounter;
            this.callMaskPosition = callMaskPosition;
        }
    }

    @Override
    public String toString() {
        return "ConjugationTemplateProvider{" +
                "verbFormTemplates=" + verbFormTemplates +
                ", index=" + index +
                '}';
    }
}
