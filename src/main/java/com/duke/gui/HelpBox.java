package com.duke.gui;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.TitledPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class HelpBox extends TitledPane {
    @FXML
    private TitledPane title;
    @FXML
    private Label description;
    @FXML
    private ImageView imageView;

    private Image image = new Image(this.getClass().getResourceAsStream("/images/error.png"));

    /**
     * Constructor for a HelpBox object that stores details of a command to be shown to the user.
     *
     * @param title Main header of the helpbox.
     * @param description Description of the issue.
     */
    public HelpBox(String title, String description) {
        try {
            FXMLLoader fxmlLoader;
            fxmlLoader = new FXMLLoader(MainTabPaneWindow.class.getResource("/view/defaultpane.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.title.setText(title);
        this.description.setText(description);
        this.imageView.setImage(image);
    }
}
