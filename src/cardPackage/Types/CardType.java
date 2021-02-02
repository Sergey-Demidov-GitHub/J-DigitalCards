/*  Copyright (C) 2021 Sergey Demidov   */

package cardPackage.Types;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public enum CardType {
    ADJECTIVE,
    KANJI,
    NOUN,
    OTHER,
    VERB;

    public String value() {
        return name();
    }

    public static CardType fromValue(String str) {
        return valueOf(str);
    }

    public static ObservableList getList() {
        ObservableList<String> cardTypes = FXCollections.observableArrayList();
        cardTypes.add(CardType.NOUN.value());
        cardTypes.add(CardType.VERB.value());
        cardTypes.add(CardType.ADJECTIVE.value());
        cardTypes.add(CardType.OTHER.value());
        cardTypes.add(CardType.KANJI.value());

        return cardTypes;
    }

}
