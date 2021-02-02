/*  Copyright (C) 2021 Sergey Demidov   */

package main;

import cardPackage.BasicCard;
import cardPackage.Score;
import cardPackage.Types.CardType;
import dbUtils.DBComm;
import dbUtils.DBCommInterface;
import dbUtils.SQLUtility;
import javafx.application.Application;
import javafx.stage.Stage;
import legacy.legacyPatcher.LegacyConverter;
import misc.Misc;

public class Main extends Application {
    public MainController mainController;
    private static DBCommInterface dBCommunicator = new DBComm();

    public static void main(String[] args){
        System.setProperty("prism.lcdtext", "false");
        TestSuite();
        //dBCommunicator.clearDB();
        //legacyPatch();
        launch(args);
    }

    public void start(Stage stage) throws Exception {
        this.mainController = new MainController(stage);
    }

    private static void legacyPatch() {
        LegacyConverter legacyConverter = new LegacyConverter();
        //legacyConverter.test_stringToCard();
        legacyConverter.convert("Book Kanji.txt");
        legacyConverter.convert("unit3.txt");
        legacyConverter.convert("unit4.txt");
        legacyConverter.convert("unit5.txt");
        legacyConverter.convert("unit6.txt");
        legacyConverter.convert("unit7.txt");
        legacyConverter.convert("unit8.txt");
        legacyConverter.convert("unit9.txt");
        legacyConverter.convert("unit10.txt");
        legacyConverter.convert("unit11.txt");
        legacyConverter.convert("unit12.txt");
        legacyConverter.convert("unit13.txt");
        legacyConverter.convert("unit14.txt");
        legacyConverter.convert("unit15.txt");

    }


























    //TODO: move test suite to different class
    private static void TestSuite() {
        //test_Card();
        //test_SQLUtility_insertCardBase();
        //test_SQLUtility_insertCardBase_id();
        //test_SQLUtility_onDeleteCascade();
        //test_SQLUtility_deleteCardBase();
        //test_SQLUtility_insertScore();
        //test_SQLUtility_changeScore();
        //test_insert_typeNoun();
        //test_SQLUtility_update_typeNoun_jap1();
        //test_SQLUtility_insertDeckBase();
        //test_SQLUtility_deleteDeckBase_Deck();
        //test_SQLUtility_changeDeckBase_name();
        //test_SQLUtility_insertDeckDistribution();
        //System.out.println("row: " + SQLUtility.getLatestIdCardBase());
        //test_DBComm_addNounCard();
        //test_DBComm_buildNounCard();

        //test_DBComm_addDeck();
        //test_DBComm_getDeck();

        //RU_Conjugation.testRuConjugation();
        //U_Conjugation.testUConjugation();

        //test_japStringMatching();

    }

    private static void test_Card() {
        BasicCard basicCard1 = new BasicCard(1, "jap_1", "jap_2", "eng_1", "eng_2");
        System.out.println(basicCard1.toString());
    }

    private static void test_SQLUtility_insertCardBase_id(){
        SQLUtility.insertCardBase_Card(4, CardType.NOUN);
    }

    private static void test_SQLUtility_insertCardBase() {
        SQLUtility.insertCardBase_Card(CardType.NOUN);
    }

    private static void test_SQLUtility_deleteCardBase() {
        SQLUtility.deleteCardBase_Card(5);
    }

    private static void test_SQLUtility_insertScore(){
        Score score = new Score(10,6, 2, 20 ,8, 0);
        SQLUtility.insertScore(4, score);
    }

    private static void test_SQLUtility_changeScore(){
        Score score = new Score(2,3,4,5, 6, 7);
        SQLUtility.changeScore(4, score);
    }

    private static void test_SQLUtility_onDeleteCascade(){
        test_SQLUtility_insertCardBase_id();
        test_SQLUtility_insertScore();
    }

    private static void test_SQLUtility_insert_typeNoun(){
        SQLUtility.insert_typeNoun(5, "jap_1", "jap_2", "eng_1", "eng_2");
    }

    private static void test_SQLUtility_update_typeNoun_jap1(){
        SQLUtility.update_typeNoun_jap1(5, "changed_jap_1");
    }

    private static void test_SQLUtility_insertDeckBase(){
        SQLUtility.insertDeckBase("unit_1");
    }

    private static void test_SQLUtility_deleteDeckBase_Deck(){
        SQLUtility.deleteDeckBase_Deck(1);
    }

    private static void test_SQLUtility_changeDeckBase_name(){
        SQLUtility.changeDeckBase_name(1, "unit_name_changed");
    }

    private static void test_SQLUtility_insertDeckDistribution(){
        SQLUtility.insertDeckDistribution(1, 5);
    }

    private static void test_DBComm_addNounCard(){
        BasicCard basicCard1 = new BasicCard(1, "jap_11", "jap_22", "eng_11", "eng_22");
        dBCommunicator.addCard(basicCard1);
    }

    private static void test_DBComm_buildNounCard(){
        dBCommunicator.getCard(22);
    }


    private static void test_DBComm_addDeck() {
        dBCommunicator.addDeck("unit_123");
    }

    private static void test_DBComm_getDeck() {
        System.out.println(dBCommunicator.getDeck(4).toString());
    }

    private static void test_japStringMatching() {
        // those 2 strings actually have different representations
        String a = "もっ​て​く​る";
        String b = "もってくる";
        boolean match = Misc.equalsJap(a, b);
        System.out.println("[TEST] matching: (" + a + " | " + b + ") => " + match );
    }


}
