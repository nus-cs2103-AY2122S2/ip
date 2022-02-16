package angela.gui;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;


public class AngelaMessage extends Message {
    private static final String RESOURCE = "/view/AngelaMessage.fxml";
    @FXML
    private TextFlow textDisplay;

    /**
     * Constructor of reusable display text message for Angela
     *
     * @param text The displayed text
     * @throws IOException If an I/O error occurs
     */
    public AngelaMessage(String text) throws IOException {
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

    @Override
    protected void loadText(String text) {
        int highlightIndex = text.indexOf("/");
        int wordStyleIndex = highlightIndex + 1;
        int afterStyleIndex = 0;
        Text[] arrayList = new Text[3];
        if (highlightIndex != -1) {
            afterStyleIndex = text.indexOf(" ", highlightIndex);
            Text beginText = new Text(text.substring(0, highlightIndex));
            Text errorText = new Text(text.substring(wordStyleIndex, afterStyleIndex));
            Text endText = new Text(text.substring(afterStyleIndex));

            errorText.setFill(Color.RED);
            errorText.setFont(Font.font("Helvetica", FontWeight.BOLD, 12));
            arrayList[0] = beginText;
            arrayList[1] = errorText;
            arrayList[2] = endText;
            textDisplay = new TextFlow(arrayList);
        } else {
            Text textString = new Text(text);
            textDisplay = new TextFlow(textString);
        }
        this.getChildren().add(textDisplay);
    }
}
