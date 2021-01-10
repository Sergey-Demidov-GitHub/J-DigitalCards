package cardGui;

import cardPackage.BasicCard;

public class AdjectiveCardGui extends BasicCardGui {
    private String cssStyleClass =  "card-class-adjective";

    public AdjectiveCardGui(BasicCard card) {
        super(card);
        super.setCssStyleClass(cssStyleClass);
    }
}
