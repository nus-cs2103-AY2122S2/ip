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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;

/**
 * This control represents a dialog box consisting of 
 * an ImageView to represent a sticker sent by Lily.
 * 
 * @@author ddx-510 Referenced Dai Tianle for using Region to resize textboxes.
 */
public class LilyStickerBox extends HBox {
    @FXML
    private ImageView sticker;

    private final Image PARTY_IMAGE = new Image(Objects.requireNonNull(this.getClass()
            .getResourceAsStream("/images/lilyyay.png")));
    private final Image CLAP_IMAGE = new Image(Objects.requireNonNull(this.getClass()
            .getResourceAsStream("/images/lilyclap.png")));
    private final Image MOCK_IMAGE = new Image(Objects.requireNonNull(this.getClass()
            .getResourceAsStream("/images/lilymocking.png")));

    private LilyStickerBox(String emotion) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/LilyStickerBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        switch(emotion) {
        case "party":
            sticker.setImage(PARTY_IMAGE);
            break;

        case "clap":
            sticker.setImage(CLAP_IMAGE);
            break;

        case "mock":
            sticker.setImage(MOCK_IMAGE);
            break;

        default:
            System.err.println("Sticker emotion does not exist.");
        }
    }

    /**
     * Constructs a sticker box for Lily
     * 
     * @return a new LilyStickerBox from Lily
     */
    public static LilyStickerBox getSticker(String emotion) {
        var stk = new LilyStickerBox(emotion);
        stk.setMinHeight(Region.USE_PREF_SIZE);
        return stk;
    }
}