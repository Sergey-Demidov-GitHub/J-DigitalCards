/*  Copyright (C) 2021 Sergey Demidov   */

package cardPackage.Types;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.HashMap;
import java.util.Map;

public enum AdjectiveType {
    UNKNOWN,
    IRREGULAR,
    I,
    NA;

    public Integer toNumber() {
        Map<AdjectiveType, Integer> map = new HashMap<>();
        map.put(AdjectiveType.UNKNOWN, 0);
        map.put(AdjectiveType.IRREGULAR, 1);
        map.put(AdjectiveType.I, 2);
        map.put(AdjectiveType.NA, 3);
        return map.get(this);
    }

    public String value() {
        return name();
    }

    public static AdjectiveType fromValue(String str) {
        return valueOf(str);
    }

    public static ObservableList getList() {
        ObservableList<String> adjectiveTypes = FXCollections.observableArrayList();
        adjectiveTypes.add(AdjectiveType.UNKNOWN.value());
        adjectiveTypes.add(AdjectiveType.IRREGULAR.value());
        adjectiveTypes.add(AdjectiveType.I.value());
        adjectiveTypes.add(AdjectiveType.NA.value());

        return adjectiveTypes;
    }



}
