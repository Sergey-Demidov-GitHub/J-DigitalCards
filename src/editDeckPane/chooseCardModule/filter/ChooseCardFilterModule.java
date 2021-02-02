/*  Copyright (C) 2021 Sergey Demidov   */

package editDeckPane.chooseCardModule.filter;

import editDeckPane.chooseCardModule.ChooseCardModule;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class ChooseCardFilterModule extends AnchorPane {
    private ChooseCardModule chooseCardModule;

    public ChooseCardFilterModule () {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("chooseCardFilterModule.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    public void setChooseCardModule(ChooseCardModule chooseCardModule) {
        this.chooseCardModule = chooseCardModule;
    }
}
