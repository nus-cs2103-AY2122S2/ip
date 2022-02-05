import duke.*;

import java.io.IOException;
import java.io.FileNotFoundException;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

//public class Duke extends Application {
public class Duke {

    private Ui ui;
    private Storage storage;
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;
    private Image user = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));
    private boolean isFirstTimeUser = true;

    /**
     * @param directory
     * @param filePath
     * @throws FileNotFoundException
     */
    public Duke (String directory, String filePath) throws FileNotFoundException {
        ui = new Ui();
        storage = new Storage(directory, filePath);
        Parser parse = new Parser(directory, filePath);
        parse.checkDir();
        parse.checkFile();

    }

    public Duke () {

    }


    String getResponse(String input) throws IOException {
       String response = "";

       if (isFirstTimeUser) {
           response = welcomeMsg();
           isFirstTimeUser = false;
       }

       try {
           response = ui.userCommand(input);

       } catch (IOException | DukeException e) {
           response = e.getMessage();
       }
       return response;
    }

    String welcomeMsg() throws IOException {
        return ui.welcomeMsg();
    }






}
