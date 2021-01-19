package trainDeck_Stage;

import cardGui.CardGuiManager;
import cardPackage.BasicCard;
import cardPackage.Card;
import cardPackage.VerbCard;
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

    /** Entry point for dynamic processing:
     *
     */
    public CardGuiManager buildCardGuiManager(Card card) {
        CardGuiManager cardGuiManager = null;
        this.card = card;
        //System.out.println(card.getId());


        if (!trainConfig.getVerbConfig().getDefaultMode() && verbIds.contains(card.getId())) {
            String[] verbFormInfo = lookUpForm();
            VerbCard conjugatedVerbCard = buildVerbCard(verbFormInfo[0], verbFormInfo[1]);
            cardGuiManager = new CardGuiManager(mainController, conjugatedVerbCard);
        } else {
            cardGuiManager = new CardGuiManager(mainController);
        }

        return cardGuiManager;
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
     * returns [verbFormTemplate, verbForm]
     */
    private String[] lookUpForm() {
        int id = card.getId();

        int newCallMaskPosition = mainController.getSession().getCallMaskIndex();
        int overflowCounter = mainController.getSession().getOverflowCounter();
        conjugationMap.get(id).updateCallMaskPosition(overflowCounter, newCallMaskPosition);
        String verbFormTemplate = conjugationMap.get(id).getCurrentTemplate();

        String verbForm = trainDeckController.getCurrentConjugation().getLookUpMapValue(verbFormTemplate);

        System.out.println(id + " | " + conjugationMap.get(id).toString());
        return new String[]{verbFormTemplate, verbForm};

    }

    private VerbCard buildVerbCard(String verbFormTemplate, String verbForm){
        VerbCard tempCard = (VerbCard) card;
        boolean hasJap2 = BasicCard.hasJap2(tempCard);
        VerbCard conjugatedCard = null;
        /*if (hasJap2) {
            String newJap2 = tempCard.getJap2() + "\n" + verbFormTemplate;
            conjugatedCard = new VerbCard(tempCard.getId(), tempCard.getJap1(), newJap2,
                    verbForm, tempCard.getEng2(), tempCard.getType());

        } else {
            String newJap1 = tempCard.getJap1() + "\n" + verbFormTemplate;
            conjugatedCard = new VerbCard(tempCard.getId(), newJap1, tempCard.getJap2(),
                    verbForm, tempCard.getEng2(), tempCard.getType());
        }*/

        /*
        String newJap2 = tempCard.getJap2() + "\n" + verbFormTemplate;
        conjugatedCard = new VerbCard(tempCard.getId(), tempCard.getJap1(), newJap2,
                verbForm, tempCard.getEng2(), tempCard.getType());*/

        String newJap2 = verbForm;
        String newEng2 = tempCard.getEng2() + "\n" + verbFormTemplate;
        conjugatedCard = new VerbCard(tempCard.getId(), tempCard.getJap1(), newJap2,
                tempCard.getEng1(), newEng2, tempCard.getType());


        return conjugatedCard;
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
