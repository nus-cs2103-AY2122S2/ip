
package juke;

import javafx.application.Application;
import javafx.stage.Stage;

public class Juke extends Application {

    @Override
    public void start(Stage stage) {
        Storage s = new Storage();
        new UI().layout(stage, s.load());
    }

}
