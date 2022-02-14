package li.zhongfu.cs2103.chatbot.ui;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import li.zhongfu.cs2103.chatbot.Duke;
import li.zhongfu.cs2103.chatbot.ui.gui.MainWindow;

public class GraphicalUi extends Application {
    private Duke duke;

    @Override
    public void init() {
        duke = new Duke("Duke");
    }

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(GraphicalUi.class.getResource("/view/MainWindow.fxml"));
            BorderPane bp = fxmlLoader.load();
            Scene scene = new Scene(bp);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setAndInitDuke(duke);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
