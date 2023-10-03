package lily.control;

import java.io.IOException;
import java.util.Collections;
import java.util.Objects;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.text.Font;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;

/**
 * This control represents a dialog box consisting of 
 * a label containing text from the user.
 * 
 * @@author ddx-510 Referenced Dai Tianle for using Region to resize textboxes, and setting colours
 */
public class UserDialogBox extends HBox {
    @FXML
    private Label dialog;

    private int FONT_SIZE = 13;
    @FXML
    private final Font NUNITO_MEDIUM = Font.loadFont(Objects.requireNonNull(this.getClass()
            .getResourceAsStream("/fonts/Nunito-Medium.ttf")), FONT_SIZE);

    /**
     * Creates a new dialog box containing what the user is saying.
     * 
     * @param text that the user typed.
     */
    private UserDialogBox(String text) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/UserDialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        dialog.setText(text);
    }

    /**
     * Constructs a dialog box for the User.
     * 
     * @param text The user's input.
     * @return a new DialogBox from the user.
     */
    public static UserDialogBox getDialog(String text) {
        var db = new UserDialogBox(text);
        db.setMinHeight(Region.USE_PREF_SIZE);
        return db;
    }
}