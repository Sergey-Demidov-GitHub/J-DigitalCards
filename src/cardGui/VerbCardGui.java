package cardGui;

import cardPackage.BasicCard;

public class VerbCardGui extends BasicCardGui{
    private String cssStyleClass = "card-class-verb";

    public VerbCardGui(BasicCard card) {
        super(card);
        super.setCssStyleClass(cssStyleClass);
    }
}
