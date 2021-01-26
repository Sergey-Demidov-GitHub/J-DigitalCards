package trainDeckPane;

import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class KeyEventHandler {
    private TrainDeckController controller;

    private EventHandler keyPressedFilter;

    private final boolean DEBUG = false;


    public KeyEventHandler(TrainDeckController controller) {
        this.controller = controller;

        keyPressedFilter = new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                KeyCode keyCode = event.getCode();
                switch (keyCode) {
                    case SPACE: {
                        if (DEBUG) System.out.println("[DEBUG] SPACE-Key pressed.");
                        controller.onAction_flip();
                        break;
                    }
                    case LEFT: {
                        if (DEBUG) System.out.println("[DEBUG] LEFT_ARROW-Key pressed.");
                        controller.onAction_left();
                        break;
                    }
                    case RIGHT: {
                        if (DEBUG) System.out.println("[DEBUG] RIGHT_ARROW-Key pressed.");
                        controller.onAction_right();
                        break;
                    }
                    case UP: {
                        if (DEBUG) System.out.println("[DEBUG] UP_ARROW-Key pressed.");
                        controller.onAction_correct();
                        break;
                    }
                    case DOWN: {
                        if (DEBUG) System.out.println("[DEBUG] DOWN_ARROW-Key pressed.");
                        controller.onAction_wrong();
                        break;
                    }
                    case R: {
                        if (DEBUG) System.out.println("[DEBUG] R-Key pressed.");
                        controller.onAction_reverse();
                        break;
                    }
                    case L: {
                        if (DEBUG) System.out.println("[DEBUG] L-Key pressed.");
                        controller.onAction_lock();
                        break;
                    }
                    case S: {
                        if (DEBUG) System.out.println("[DEBUG] S-Key pressed.");
                        controller.onAction_shuffle();
                        break;
                    }
                }
                event.consume();
            }
        };


    }


    public void applyFilterOnNode(Node node) {
        node.addEventFilter(KeyEvent.KEY_PRESSED, keyPressedFilter);

    }
}
