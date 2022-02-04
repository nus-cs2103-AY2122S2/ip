package bob;

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

import java.io.IOException;
import java.util.Collections;

public class DialogBox extends HBox {

    @FXML
    private Label dialog;
    @FXML
    private ImageView displayPicture;

    private DialogBox(String text, Image img) {
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
        displayPicture.setFitWidth(120.0);
        displayPicture.setFitHeight(120.0);
        displayPicture.setImage(img);
    }

    private void flip() {
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        Collections.reverse(tmp);
        getChildren().setAll(tmp);
        setAlignment(Pos.TOP_LEFT);
    }

    public static DialogBox getUserDialog(String text, Image img) {
        return new DialogBox(text, img);
    }

    public static DialogBox getBobDialog(String text, Image img) {
        var db = new DialogBox(text, img);
        db.flip();
        return db;
    }
//    public DialogBox(Label l, ImageView iv) {
//        text = l;
//        displayPicture = iv;
//        Circle clipper = new Circle(60, 60, 60);
//
//        text.setWrapText(true);
//        displayPicture.setFitWidth(120.0);
//        displayPicture.setFitHeight(120.0);
//        displayPicture.setClip(clipper);
//        this.setAlignment(Pos.TOP_RIGHT);
//        this.getChildren().addAll(text, displayPicture);
//    }

//    private void flip() {
//        this.setAlignment(Pos.TOP_LEFT);
//        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
//        FXCollections.reverse(tmp);
//        this.getChildren().setAll(tmp);
//    }
//
//    public static DialogBox getUserDialog(Label l, ImageView iv) {
//        l.setPadding(new Insets(0, 20, 0, 0));
//        DialogBox db = new DialogBox(l, iv);
//        db.setBackground(new Background(new BackgroundFill(Color.YELLOW, null, null)));
//        return db;
//    }
//
//    public static DialogBox getBobDialog(Label l, ImageView iv) {
//        l.setPadding(new Insets(0, 0, 0, 20));
//        var db = new DialogBox(l, iv);
//        db.flip();
//        db.setBackground(new Background(new BackgroundFill(Color.YELLOWGREEN, null, null)));
//        return db;
//    }
}
