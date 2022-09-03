package com.duke.gui;

import java.util.ArrayList;
import java.util.HashMap;

import com.duke.Duke;
import com.duke.modules.Ui;

import javafx.fxml.FXML;
import javafx.scene.control.Accordion;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;

/**
 * Controller for MainWindow. Provides the layout for the other controls.
 */
public class MainTabPaneWindow extends TabPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;
    @FXML
    private TabPane tabPane;
    @FXML
    private Tab chatTab;
    @FXML
    private Tab helpTab;
    @FXML
    private Accordion helpList;
    @FXML
    private TextField helpTabUserInput;
    @FXML
    private Button helpTabSendButton;


    private Duke duke;
    private HashMap<String, HelpBox> helpListDictionary = new HashMap<>();

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/user.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/bot.png"));
    private Image dukeErrorImage = new Image(this.getClass().getResourceAsStream("/images/error.png"));
    private Image dukeInvalidImage = new Image(this.getClass().getResourceAsStream("/images/disappointed.png"));


    /**
     * Initializes the TabPane.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        initializeHelpList();
        showWelcomeMessage();
    }

    public void setDuke(Duke d) {
        duke = d;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        Image responseImage = dukeImage;
        String input = userInput.getText();
        String response = duke.getResponse(input);

        if (!detectGuiCommand(response)) {
            if (response.startsWith("OOPS")) {
                responseImage = dukeErrorImage;
            } else if (response.startsWith("ERMMM")) {
                responseImage = dukeInvalidImage;
            }
            dialogContainer.getChildren().addAll(
                    DialogBox.getUserDialog(input, userImage),
                    DialogBox.getDukeDialog(response, responseImage)
            );
        }
        userInput.clear();
    }

    @FXML
    private void helpHandleUserInput() {
        Image responseImage = dukeImage;
        String input = helpTabUserInput.getText();
        String response = duke.getResponse(input);

        if (!detectGuiCommand(response)) {
            if (response.startsWith("OOPS")) {
                responseImage = dukeErrorImage;
            } else if (response.startsWith("ERMMM")) {
                responseImage = dukeInvalidImage;
            }
            dialogContainer.getChildren().addAll(
                    DialogBox.getUserDialog(input, userImage),
                    DialogBox.getDukeDialog(response, responseImage)
            );
        }
        userInput.clear();
        helpTabUserInput.clear();
    }

    private void showWelcomeMessage() {
        Image responseImage = dukeImage;
        String welcomeMessage = Ui.START_MESSAGE;
        dialogContainer.getChildren().addAll(
                DialogBox.getDukeDialog(welcomeMessage, responseImage));
    }


    private boolean detectGuiCommand(String command) {
        boolean isGuiCommand = false;

        switch (command) {
        case "<GUI->chat>":
            isGuiCommand = true;
            tabPane.getSelectionModel().select(chatTab);
            helpList.setExpandedPane(null);
            userInput.requestFocus();
            break;
        case "<GUI->help>":
            isGuiCommand = true;
            tabPane.getSelectionModel().select(helpTab);
            helpTabUserInput.requestFocus();
            break;
        default:
            if (command.matches("<GUI->help->.*>")) {
                isGuiCommand = true;
                String arg = command.substring(12, command.length() - 1);
                identifyHelpList(arg);
            }
            break;
        }

        return isGuiCommand;
    }

    private void identifyHelpList(String str) {
        HelpBox targetBox = helpListDictionary.get(str);
        if (targetBox != null) {
            helpList.setExpandedPane(targetBox);
        }
        tabPane.getSelectionModel().select(helpTab);
        helpTabUserInput.requestFocus();
    }

    private void initializeHelpList() {
        ArrayList<String> list = HelpList.getList();
        for (int i = 0; i < list.size(); i += 2) {
            int j = list.get(i).indexOf(':');
            String startWord = list.get(i).substring(0, j);
            HelpBox curr = new HelpBox(list.get(i), list.get(i + 1));
            helpList.getPanes().add(curr);
            helpListDictionary.put(startWord, curr);
        }
    }
}
