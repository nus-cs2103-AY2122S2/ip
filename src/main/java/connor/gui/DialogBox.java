package connor.gui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;

import java.io.IOException;
import java.util.Collections;

public class DialogBox extends HBox {
    @FXML
    private Label dialog;
    @FXML
    private ImageView avatar;

    private DialogBox(String text, Image img) {
        try {
            FXMLLoader loader = new FXMLLoader(MainWindow.class.getResource("/view/DialogBox.fxml"));
            loader.setController(this);
            loader.setRoot(this);
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        dialog.setText(text);
        avatar.setImage(img);
        avatar.setClip(getCircleClip());
        this.setBackground(getColorBackground("#D3D3D3"));
    }

    private void flip() {
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        Collections.reverse(tmp);
        this.getChildren().setAll(tmp);
        this.setAlignment(Pos.TOP_LEFT);
    }

    private Background getColorBackground(String colorString) {
        Paint paint = Paint.valueOf(colorString);
        BackgroundFill bf = new BackgroundFill(paint, new CornerRadii(10, false), null);
        return new Background(bf);
    }

    private Circle getCircleClip() {
        return new Circle(50, 50, 50);
    }

    public static DialogBox getUserDialog(String s, Image img) {
        return new DialogBox(s, img);
    }

    public static DialogBox getConnorDialog(String s, Image img) {
        var db = new DialogBox(s, img);
        db.flip();
        return db;
    }

}
