package lucifer.gui;

import java.io.IOException;
import java.util.Objects;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lucifer.Lucifer;

/**
 * A GUI for Lucifer using FXML.
 */
public class Main extends Application {
    /** The main class of the chatbot **/
    private final Lucifer lucifer = new Lucifer();
    /** The chatbot icon image **/
    private final Image icon = new Image(Objects.requireNonNull(this.getClass().getResourceAsStream(
            "/images/icon.png")));

    public Main() throws IOException {
    }
    /**
     * Sets the GUI when chatbox initializes.
     * @param stage skeleton to be created from
     */
    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setTitle("lucifer");
            stage.getIcons().add(icon);
            stage.setScene(scene);
            stage.setResizable(false);
            fxmlLoader.<MainWindow>getController().setLucifer(lucifer);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
