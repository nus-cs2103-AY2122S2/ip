package chibot.controllers;

import chibot.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Hyperlink;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

import java.io.IOException;
import java.util.Collections;

/**
 * An example of a custom control using FXML.
 * This control represents a dialog box consisting of an ImageView to represent the speaker's face and a label
 * containing text from the speaker.
 */
public class HelpDialogBox extends HBox {
    @FXML
    private ImageView displayPicture;
    @FXML
    private Hyperlink hyperlink;

    /**
     * Constructor for the class.
     *
     * @param img The image to be set in the DialogBox
     */
    private HelpDialogBox(Image img) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/HelpDialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        hyperlink.setText("https://github.com/WJunHong/ip");
        displayPicture.setImage(img);
        hyperlink.setOnAction(t -> new Main().getHostServices().showDocument(hyperlink.getText()));
    }

    /**
     * Flips the dialog box such that the ImageView is on the left and text on the right.
     */
    private void flip() {
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        Collections.reverse(tmp);
        getChildren().setAll(tmp);
        setAlignment(Pos.TOP_LEFT);
    }

    /**
     * Creates a HelpDialogBox for Chi.
     *
     * @param img Image for representing Chi.
     * @return A new HelpDialogBox.
     */
    public static HelpDialogBox getChiHelpDialog(Image img) {
        var db = new HelpDialogBox(img);
        db.getStyleClass().add("chiMsg");
        db.flip();
        return db;
    }

}
