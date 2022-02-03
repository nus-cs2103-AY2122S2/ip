package connor;

import connor.gui.MainWindow;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    private Connor connor;

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = loader.load();
            //this.connor = new Connor(Connor.getFilePath());
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setResizable(false);
            //loader.<MainWindow>getController().setConnor(connor);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
