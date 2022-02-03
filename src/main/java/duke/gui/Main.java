package duke.gui;

import java.io.IOException;

import duke.Memory;
import duke.UserInterface;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {

    private Memory memory = new Memory();
    private UserInterface ui = new UserInterface(memory);
    private GuiDukeInterface guiDukeInterface = new GuiDukeInterface(ui);

    @Override
    public void start(Stage stage) {
        try {
            // Setup Disk
            memory.setup();

            // Setup GUI
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setTitle("Duke Duder");
            stage.setResizable(false);
            fxmlLoader.<MainWindow>getController().setRenderer(guiDukeInterface);
            fxmlLoader.<MainWindow>getController().handleGuiStart();
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
