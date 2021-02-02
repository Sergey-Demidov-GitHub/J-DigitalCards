/*  Copyright (C) 2021 Sergey Demidov   */

package flashCards;

import cardPackage.BasicCard;

public class NounFlashCard extends BasicFlashCard {
    private String cssStyleClass =  "card-class-noun";

    public NounFlashCard(BasicCard card) {
        super(card);
        super.setCssStyleClass(cssStyleClass);
    }
}
