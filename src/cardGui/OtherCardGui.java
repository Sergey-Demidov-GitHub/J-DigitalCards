package cardGui;

import cardPackage.BasicCard;

public class OtherCardGui extends BasicCardGui {
    private String cssStyleClass =  "card-class-other";

    public OtherCardGui(BasicCard card) {
        super(card);
        super.setCssStyleClass(cssStyleClass);
    }


}
