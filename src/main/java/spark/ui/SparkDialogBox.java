package spark.ui;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * An example of a custom control using FXML.
 * This control represents a dialog box consisting of an ImageView to represent the speaker's face and a label
 * containing text from the speaker.
 */
public class SparkDialogBox extends DialogBox {
    @FXML
    private Label dialog;
    @FXML
    private ImageView displayPicture;

    SparkDialogBox(String text, Image img) {
        assert(text != null);
        assert(img != null);
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/SparkDialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        dialog.setText(text);
        displayPicture.setImage(img);
    }

    public static DialogBox getDialog(String text, Image img) {
        SparkDialogBox dialogBox = new SparkDialogBox(text, img);
        dialogBox.dialog.setBackground(DialogBox.getNormalChatboxColor());

        return dialogBox;
    }

    public static SparkDialogBox getSuccessSparkDialog(String text, Image img) {
        SparkDialogBox dialogBox = new SparkDialogBox(text, img);
        dialogBox.dialog.setBackground(DialogBox.getSuccessChatboxColor());

        return dialogBox;
    }

    public static SparkDialogBox getWarningSparkDialog(String text, Image img) {
        SparkDialogBox dialogBox = new SparkDialogBox(text, img);
        dialogBox.dialog.setBackground(DialogBox.getWarningChatboxColor());

        return dialogBox;
    }

    public static SparkDialogBox getErrorSparkDialog(String text, Image img) {
        SparkDialogBox dialogBox = new SparkDialogBox(text, img);
        dialogBox.dialog.setBackground(DialogBox.getErrorChatboxColor());

        return dialogBox;
    }
}
