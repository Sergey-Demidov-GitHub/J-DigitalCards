/*  Copyright (C) 2021 Sergey Demidov   */

package trainDeckPane.filterModule;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import main.MainController;
import misc.Misc;
import trainDeckPane.TrainDeckController;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;

public class FilterModule extends AnchorPane {
    private MainController mainController;
    private TrainDeckController trainDeckController;

    private String status_OK = "OK";
    private String status_EmptyFilter = "No Cards meet said conditions.";

    @FXML
    private Label status_L;

    @FXML
    private TextField absoluteMin_T, absoluteMax_T, streakMin_T, streakMax_T;

    public FilterModule() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("filterModule.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    public void setMainController(MainController mainController) {
        this.mainController = mainController;
        status_L.setText(status_OK);
    }

    public void setTrainDeckController(TrainDeckController trainDeckController) {
        this.trainDeckController = trainDeckController;
    }

    @FXML
    public void onAction_ok(ActionEvent event) {
        applyFilter();
        trainDeckController.reloadCurrentCard();
        trainDeckController.reloadScore();
        trainDeckController.setCardsLeft();
    }

    @FXML
    public void onAction_reset(ActionEvent event) {
        clearForm();
    }

    private void applyFilter() {
        // get Data
        Integer absoluteMin = getNumberFromString(absoluteMin_T.getText());
        Integer absoluteMax = getNumberFromString(absoluteMax_T.getText());
        Integer streakMin = getNumberFromString(streakMin_T.getText());
        Integer streakMax = getNumberFromString(streakMax_T.getText());

        boolean orderJapEng_b = !trainDeckController.isReversed_b();
        HashMap<Integer, Integer[]> scoreMap = mainController.getSession().getDeck().getScoreMap(orderJapEng_b);

        // get set of indexes that satisfy the filter conditions
        HashSet<Integer> absolute_Set = filterByAbsolute(scoreMap, absoluteMin, absoluteMax);
        HashSet<Integer> streak_Set = filterByStreak(scoreMap, streakMin, streakMax);

        // combine separate filter
        boolean absolute_b = absoluteMin != null || absoluteMax != null;
        boolean streak_b = streakMin != null || streakMax != null;
        HashSet<Integer> filtered_Set = combineFilter(absolute_Set, absolute_b, streak_Set, streak_b);

        // change call mask in mainController according
        if(filtered_Set != null){
            if (filtered_Set.isEmpty())
                status_L.setText(status_EmptyFilter);
            else
                status_L.setText(status_OK);

            mainController.getSession().initCustomCallMask(filtered_Set);
        } else {
            status_L.setText(status_OK);
            mainController.getSession().initCallMask();
        }

    }

    private HashSet<Integer> filterByAbsolute(HashMap<Integer, Integer[]> scoreMap, Integer min, Integer max) {
        return filterByIndex(scoreMap, min, max, 0);
    }

    private HashSet<Integer> filterByStreak(HashMap<Integer, Integer[]> scoreMap, Integer min, Integer max) {
        return filterByIndex(scoreMap, min, max, 1);
    }

    private HashSet<Integer> filterByIndex(HashMap<Integer, Integer[]> scoreMap, Integer min, Integer max, int scoreValueIndex) {
        HashSet<Integer> card_id_Set = new HashSet<Integer>();

        for (int card_id: scoreMap.keySet()){
            boolean min_b = false;
            boolean max_b = false;
            boolean minMax_b = false;

            if(min != null && scoreMap.get(card_id)[scoreValueIndex] >= min)
                min_b = true;
            if(max != null && scoreMap.get(card_id)[scoreValueIndex] <= max)
                max_b = true;
            if(min != null && max != null){
                minMax_b = min_b && max_b;
                min_b = false;
                max_b = false;
            }

            if(min_b ^ max_b ^ minMax_b)
                card_id_Set.add(card_id);

        }

        return card_id_Set;
    }

    /**
     * Combines Filter in respect to their validity.
     * @param filter_1      contains filter_1 data
     * @param validity_1    if filter_1 is "turned on"
     * @param filter_2      contains filter_2 data
     * @param validity_2    if filter_2 is "turned on"
     * @return              combined HashSet<Integer> | null (if no active filter)
     */
    private HashSet<Integer> combineFilter(HashSet<Integer> filter_1, boolean validity_1, HashSet<Integer> filter_2, boolean validity_2) {
        HashSet<Integer> filtered_Set = null;
        if (validity_1 && !validity_2)
            filtered_Set = new HashSet<Integer>(filter_1);
        if (!validity_1 && validity_2)
            filtered_Set = new HashSet<Integer>(filter_2);
        if (validity_1 && validity_2) {
            filtered_Set = new HashSet<Integer>(filter_1);
            filtered_Set.retainAll(filter_2);
        }


        System.out.println("filter_1: " + filter_1);
        System.out.println("filter_2: " + filter_2);
        System.out.println("combined: " + filtered_Set);

        return filtered_Set;
    }

    /**
     *  Extracts Integer from Number if possible
     * @param str string suspected of containing a number
     * @return  Integer | null (if not valid number)
     */
    private Integer getNumberFromString(String str) {
        Integer number = null;
        if (str != null) {
            str = str.trim();
            if (!Misc.isBlank(str)) {
                try {
                    number = Integer.valueOf(str);
                } catch (NumberFormatException exception) {
                    number = null;
                }
            }
        }
        return number;
    }

    public void clearForm() {
        absoluteMin_T.setText("");
        absoluteMax_T.setText("");
        streakMin_T.setText("");
        streakMax_T.setText("");
    }
}
