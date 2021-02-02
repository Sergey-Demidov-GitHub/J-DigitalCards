/*  Copyright (C) 2021 Sergey Demidov   */

package flashCards;

import cardPackage.BasicCard;

public class AdjectiveFlashCard extends BasicFlashCard {
    private String cssStyleClass =  "card-class-adjective";

    public AdjectiveFlashCard(BasicCard card) {
        super(card);
        super.setCssStyleClass(cssStyleClass);
    }
}
