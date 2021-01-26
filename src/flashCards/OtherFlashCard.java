package flashCards;

import cardPackage.BasicCard;

public class OtherFlashCard extends BasicFlashCard {
    private String cssStyleClass =  "card-class-other";

    public OtherFlashCard(BasicCard card) {
        super(card);
        super.setCssStyleClass(cssStyleClass);
    }


}
