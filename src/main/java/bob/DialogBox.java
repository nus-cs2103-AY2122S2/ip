package bob;

import java.io.IOException;
import java.util.Collections;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Circle;

public class DialogBox extends HBox {

    @FXML
    private Label dialog;
    @FXML
    private ImageView displayPicture;

    private DialogBox(String text, Image img, boolean isUser) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Circle c = new Circle(60, 60, 60);
        displayPicture.setClip(c);
        dialog.setText(text);
        if (isUser) {
            setUserDbStyle();
        } else {
            setBobDbStyle();
        }
        setImageSize(img);
    }

    private void setBobDbStyle() {
        dialog.setStyle("-fx-background-color: GAINSBORO;"
                + "-fx-background-radius: 25;"
                + "-fx-padding: 15 15 15 15");
    }

    private void setUserDbStyle() {
        dialog.setStyle("-fx-background-color: DODGERBLUE; "
                + "-fx-background-radius: 25;"
                + "-fx-padding: 15 15 15 15");
    }

    private void setImageSize(Image img) {
        displayPicture.setFitWidth(120.0);
        displayPicture.setFitHeight(120.0);
        displayPicture.setImage(img);
    }

    private void setErrorStyle() {
        dialog.setStyle("-fx-background-color: LIGHTPINK;"
                + "-fx-background-radius: 25;"
                + "-fx-padding: 15 15 15 15;"
                + "-fx-font-weight: bold");
    }

    private void flip() {
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        Collections.reverse(tmp);
        getChildren().setAll(tmp);
        setAlignment(Pos.TOP_LEFT);
    }

    public static DialogBox getUserDialog(String text, Image img) {
        return new DialogBox(text, img, true);
    }

    public static DialogBox getBobDialog(String text, Image img) {
        var db = new DialogBox(text, img, false);
        db.flip();
        return db;
    }

    public static DialogBox getErrorDialog(String text, Image img) {
        var db = getBobDialog(text, img);
        db.setErrorStyle();
        return db;
    }
}
