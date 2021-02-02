/*  Copyright (C) 2021 Sergey Demidov   */

package cardPackage.Types;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.HashMap;
import java.util.Map;

public enum VerbType {
    UNKNOWN,
    IRREGULAR,
    U,
    RU;

    public Integer toNumber() {
        Map<VerbType, Integer> map = new HashMap<>();
        map.put(VerbType.UNKNOWN, 0);
        map.put(VerbType.IRREGULAR, 1);
        map.put(VerbType.U, 2);
        map.put(VerbType.RU, 3);
        return map.get(this);
    }

    public String value() {
        return name();
    }

    public static VerbType fromValue(String str) {
        return valueOf(str);
    }

    public static ObservableList getList() {
        ObservableList<String> verbTypes = FXCollections.observableArrayList();
        verbTypes.add(VerbType.UNKNOWN.value());
        verbTypes.add(VerbType.IRREGULAR.value());
        verbTypes.add(VerbType.U.value());
        verbTypes.add(VerbType.RU.value());

        return verbTypes;
    }
}
