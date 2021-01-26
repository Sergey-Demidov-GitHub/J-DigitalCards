package flashCards;

import cardPackage.BasicCard;

public class VerbFlashCard extends BasicFlashCard {
    private String cssStyleClass = "card-class-verb";

    public VerbFlashCard(BasicCard card) {
        super(card);
        super.setCssStyleClass(cssStyleClass);
    }
}
