package cardGui;

import cardPackage.BasicCard;

public class NounCardGui extends BasicCardGui {
    private String cssStyleClass =  "card-class-noun";

    public NounCardGui(BasicCard card) {
        super(card);
        super.setCssStyleClass(cssStyleClass);
    }
}
