package messageModule;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class MessageModule {
    enum Message_Response {
        OK,
        CANCEL
    }

    @FXML
    private AnchorPane root;
    @FXML
    private Label messageBox_L;

    private Message_Response response = null;

    public MessageModule(String Message) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("messageModule.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try {
            Scene scene = new Scene(root);      //TODO: wrong root
            Stage stage = new Stage();
            stage.setScene(scene);
            fxmlLoader.load();

            root.getStylesheets().add("main.css");
            stage.show();
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    @FXML
    public void onAction_ok() {
        response = Message_Response.OK;
    }

    @FXML
    public void onAction_cancel() {
        response = Message_Response.CANCEL;
    }

    public Message_Response getResponse() {
        return response;
    }
}
