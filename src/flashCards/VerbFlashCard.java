/*  Copyright (C) 2021 Sergey Demidov   */

package flashCards;

import cardPackage.BasicCard;

public class VerbFlashCard extends BasicFlashCard {
    private String cssStyleClass = "card-class-verb";

    public VerbFlashCard(BasicCard card) {
        super(card);
        super.setCssStyleClass(cssStyleClass);
    }
}
