package angela.gui;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;


public class UserMessage extends Message {
    private static final String RESOURCE = "/view/UserMessage.fxml";
    @FXML
    private TextFlow textDisplay;

    /**
     * Constructs reusable display text message
     *
     * @param text The displayed text
     * @throws IOException If an I/O error occurs
     */
    public UserMessage(String text) throws IOException {
        loadFxml();
        loadText(text);
    }

    /**
     * Loads FXML for the dialog box
     * @throws IOException If an I/O error occurs
     */
    @Override
    protected void loadFxml() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource(RESOURCE));
        fxmlLoader.setController(this);
        fxmlLoader.setRoot(this);
        fxmlLoader.load();
    }

    /**
     * Creates personalize text for TextFlow
     *
     * @param text The displayed text
     */
    @Override
    protected void loadText(String text) {
        Text textString = new Text(text);
        textDisplay = new TextFlow(textString);
        this.getChildren().add(textDisplay);
    }
}
