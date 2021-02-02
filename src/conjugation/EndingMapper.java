/*  Copyright (C) 2021 Sergey Demidov   */

package conjugation;

import java.util.HashMap;
import java.util.Map;

// TODO: make everything static
public class EndingMapper {

    private Map<String, String> ui_LevelMap;
    private Map<String, String> ua_LevelMap;
    private Map<String, String> ue_LevelMap;
    private Map<String, String> uo_LevelMap;
    private Map<String, String> te_FormMap;
    private Map<String, String> ta_FormMap;

    public final String IF_NEG_PRESENT = "ない";
    public final String IF_NEG_PAST = "なかった";

    public final String F_POS_PRESENT = "ます";
    public final String F_NEG_PRESENT = "ません";
    public final String F_POS_PAST = "ました";
    public final String F_NEG_PAST = "ませんでした";

    public final String TAI_POS_PRESENT = "たい";
    public final String TAI_NEG_PRESENT = "たくない";
    public final String TAI_POS_PAST = "たかった";
    public final String TAI_NEG_PAST = "たくなかった";



    //TODO: reduce to actually possible verb endings
    private String[] uLevel = {"う", "く", "す", "つ", "ぬ", "ふ", "む", "ゆ", "る", "ぐ", "ず", "づ", "ぶ", "ぷ"};

    public EndingMapper() {
        fillUiLevelMap();
        fillUaLevelMap();
        fillUeLevelMap();
        fillUoLevelMap();
        fillTeFormMap();
        fillTaFormMap();
    }

    private void fillUiLevelMap() {
        ui_LevelMap = new HashMap<String, String>();
        ui_LevelMap.put("う", "い");
        ui_LevelMap.put("く", "き");
        ui_LevelMap.put("す", "し");
        ui_LevelMap.put("つ", "ち");
        ui_LevelMap.put("ぬ", "に");
        ui_LevelMap.put("ふ", "ひ");      // x
        ui_LevelMap.put("む", "み");
        ui_LevelMap.put("ゆ", "[yi]");   // x
        ui_LevelMap.put("る", "り");
        ui_LevelMap.put("ぐ", "ぎ");
        ui_LevelMap.put("ず", "じ");      // x
        ui_LevelMap.put("づ", "ぢ");      // x
        ui_LevelMap.put("ぶ", "び");
        ui_LevelMap.put("ぷ", "ぴ");      // x
    }

    private void fillUaLevelMap() {
        ua_LevelMap = new HashMap<String, String>();
        ua_LevelMap.put("う", "わ");
        ua_LevelMap.put("く", "か");
        ua_LevelMap.put("す", "さ");
        ua_LevelMap.put("つ", "た");
        ua_LevelMap.put("ぬ", "な");
        ua_LevelMap.put("ふ", "は");      // x
        ua_LevelMap.put("む", "ま");
        ua_LevelMap.put("ゆ", "や");      // x
        ua_LevelMap.put("る", "ら");
        ua_LevelMap.put("ぐ", "が");
        ua_LevelMap.put("ず", "ざ");      // x
        ua_LevelMap.put("づ", "だ");      // x
        ua_LevelMap.put("ぶ", "ば");
        ua_LevelMap.put("ぷ", "ぱ");      // x
    }

    private void fillUeLevelMap() {
        ue_LevelMap = new HashMap<String, String>();
        ue_LevelMap.put("う", "え");
        ue_LevelMap.put("く", "け");
        ue_LevelMap.put("す", "せ");
        ue_LevelMap.put("つ", "て");
        ue_LevelMap.put("ぬ", "ね");
        ue_LevelMap.put("ふ", "へ");      // x
        ue_LevelMap.put("む", "め");
        ue_LevelMap.put("ゆ", "[ye]");   // x
        ue_LevelMap.put("る", "れ");
        ue_LevelMap.put("ぐ", "げ");
        ue_LevelMap.put("ず", "ぜ");      // x
        ue_LevelMap.put("づ", "で");      // x
        ue_LevelMap.put("ぶ", "べ");
        ue_LevelMap.put("ぷ", "ぺ");      // x
    }

    private void fillUoLevelMap() {
        uo_LevelMap = new HashMap<String, String>();
        uo_LevelMap.put("う", "お");
        uo_LevelMap.put("く", "こ");
        uo_LevelMap.put("す", "そ");
        uo_LevelMap.put("つ", "と");
        uo_LevelMap.put("ぬ", "の");
        uo_LevelMap.put("ふ", "ほ");      // x
        uo_LevelMap.put("む", "も");
        uo_LevelMap.put("ゆ", "よ");      // x
        uo_LevelMap.put("る", "ろ");
        uo_LevelMap.put("ぐ", "ご");
        uo_LevelMap.put("ず", "そ");      // x
        uo_LevelMap.put("づ", "ど");      // x
        uo_LevelMap.put("ぶ", "ぼ");
        uo_LevelMap.put("ぷ", "ぽ");      // x
    }

    private void fillTeFormMap() {
        te_FormMap = new HashMap<String, String>();
        te_FormMap.put("う", "って");
        te_FormMap.put("つ", "って");
        te_FormMap.put("る", "って");

        te_FormMap.put("む", "んで");
        te_FormMap.put("ぶ", "んで");
        te_FormMap.put("ぬ", "んで");

        te_FormMap.put("く", "いて");
        te_FormMap.put("ぐ", "いで");

        te_FormMap.put("す", "して");
    }

    private void fillTaFormMap() {
        ta_FormMap = new HashMap<String, String>();
        ta_FormMap.put("う", "った");
        ta_FormMap.put("つ", "った");
        ta_FormMap.put("る", "った");

        ta_FormMap.put("む", "んだ");
        ta_FormMap.put("ぶ", "んだ");
        ta_FormMap.put("ぬ", "んだ");

        ta_FormMap.put("く", "いた");
        ta_FormMap.put("ぐ", "いだ");

        ta_FormMap.put("す", "した");
    }


    public String getIChar(String uChar) {
        return ui_LevelMap.get(uChar);
    }

    public String getAChar(String uChar) {
        return ua_LevelMap.get(uChar);
    }

    public String getEChar(String uChar) {
        return ue_LevelMap.get(uChar);
    }

    public String getOChar(String uChar) {
        return uo_LevelMap.get(uChar);
    }

    public String getTeEnding(String uChar) {
        return te_FormMap.get(uChar);
    }

    public String getTaEnding(String uChar) {
        return ta_FormMap.get(uChar);
    }

    public String[] getULevel() {
        return uLevel;
    }
}
