package doge;

import java.io.File;
import java.io.IOException;

import doge.Doge;
import doge.command.ByeCommand;
import doge.exception.DogeException;
import doge.view.MainWindow;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Main extends Application {
    private Doge doge = new Doge();

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader =
                    new FXMLLoader(new File("./src/main/resource/view/MainWindow.fxml").toURI().toURL());
            AnchorPane ap = fxmlLoader.load();
            stage.setScene(new Scene(ap));
            fxmlLoader.<MainWindow>getController().setDoge(this.doge);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void stop() {
        try {
            new ByeCommand().execute(doge.getTasks(), doge.getUi(), doge.getStorage());
            doge.getStorage().save(doge.getTasks().getTaskList());
            System.out.println("exit successful");
        } catch (DogeException e) {
            System.out.println(doge.getUi().showError(e.getMessage()));
        }
    }
}
