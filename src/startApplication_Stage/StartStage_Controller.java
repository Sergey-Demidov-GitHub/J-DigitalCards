package startApplication_Stage;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import main.MainController;

import java.io.InputStream;
import java.net.URL;
import java.util.ResourceBundle;

public class StartStage_Controller implements Initializable {
    private MainController mainController;

    @FXML
    private AnchorPane root;

    @FXML
    private ImageView centerPic_IV;

    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        root.getStylesheets().add("main.css");
        AnchorPane.setBottomAnchor(root, 0.0);
        AnchorPane.setTopAnchor(root, 0.0);
        AnchorPane.setLeftAnchor(root, 0.0);
        AnchorPane.setRightAnchor(root, 0.0);

        //loadCenterPic();
        loadAnimation();
    }

    private void loadCenterPic() {
        String path = "rsrc/Start_drawn.png";
        InputStream in = getClass().getClassLoader().getResourceAsStream(path);
        Image image = new Image(in);
        centerPic_IV.setImage(image);
    }

    @FXML
    public void onAction_begin(ActionEvent event) {
        mainController.transition_Start_ChooseDeck();
    }

    private void loadAnimation() {
        Image[] images = loadImages();

        Timeline timeline = new Timeline();
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.setAutoReverse(true);

        KeyValue kv1 = new KeyValue(centerPic_IV.imageProperty(), images[0]);
        KeyValue kv2 = new KeyValue(centerPic_IV.imageProperty(), images[1]);
        KeyValue kv3 = new KeyValue(centerPic_IV.imageProperty(), images[2]);

        KeyFrame kf1 = new KeyFrame(Duration.ZERO, kv1);
        KeyFrame kf2 = new KeyFrame(Duration.millis(1200), kv2);
        KeyFrame kf3 = new KeyFrame(Duration.millis(1250), kv2);
        KeyFrame kf4 = new KeyFrame(Duration.millis(1300), kv3);
        KeyFrame kf5 = new KeyFrame(Duration.millis(1600), kv3);
        //KeyFrame kf6 = new KeyFrame(Duration.millis(1200), kv1);

        timeline.getKeyFrames().addAll(kf1, kf2, kf3, kf4, kf5);
        timeline.play();

    }

    private Image[] loadImages() {
        String[] paths = {"rsrc/Start_0.png", "rsrc/Start_1.png", "rsrc/Start_2.png"};
        Image[] images = new Image[paths.length];
        for (int i = 0; i < paths.length; i++){
            InputStream in = getClass().getClassLoader().getResourceAsStream(paths[i]);
            Image image = new Image(in);
            images[i] = image;
        }
        return images;
    }
}
