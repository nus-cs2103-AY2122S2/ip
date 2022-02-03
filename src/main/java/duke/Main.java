package duke;

import java.io.IOException;

import duke.UI.MainWindow;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {

    private Duke duke = new Duke();

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setDuke(duke);

            stage.setTitle("Pikachu");
            stage.getIcons().add(new Image("/images/pikachu-dp.png"));
            stage.setResizable(false);

            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Problem starting UI");
        }
    }

    @Override
    public void stop() throws IOException, InterruptedException {
        //System.out.println("For debugging: stop() method called!");

        //Gives time for user to see goodbye message
        Thread.sleep(888);

        duke.getStorage().writeTaskList();
    }
}