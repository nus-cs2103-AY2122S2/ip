package duke.main;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main {
    public static void main(String[] args) {
        new Duke("data/tasks.txt").run();
    }
}

//import javafx.application.Application;
//import javafx.scene.Scene;
//import javafx.stage.Stage;
//
///**
// * A GUI for Duke using FXML.
// */
//public class Main extends Application {
//
//    private Duke duke = new Duke();
//
//    @Override
//    public void start(Stage stage) {
//        MainWindow mainWindow = new MainWindow();
//        Scene scene = new Scene(mainWindow);
//        stage.setScene(scene);
//        mainWindow.setDuke(duke);
//        stage.show();
//    }
//}
