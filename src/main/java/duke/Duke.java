package duke;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


public class Duke extends Application {

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;

    private Image user = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    private static void writeToFile(String filePath, String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(filePath, true);
        fw.write(textToAdd);
        fw.close();
    }

    public static void main(String[] args) {

        Outputs op = new Outputs();



        System.out.println(op.greeting + op.logo);
        System.out.println(op.border
                           + op.firstPrompt
                           + op.instructions
                           + op.border);
        ArrayList<Task> itemList = new ArrayList<>();
        while(true) {


            Scanner sc = new Scanner(System.in);
            String reply = sc.nextLine();
            String[] splittedString = reply.split(" ");

            File file = new File("data/duke.txt");
            file.delete();
            try {
                file.createNewFile();
            } catch (IOException e) {
                System.out.println(" ");
            }

            if (splittedString[0].equals("mark") && splittedString.length == 2) { // check for mark tag
                int index = Integer.valueOf(reply.split(" ")[1]);
                itemList.get(index - 1).markAsDone();
                System.out.println(op.border +
                        "     Nice! I've marked this task as done: \n" +
                        "       " + itemList.get(index - 1).getDescription() + "\n" +
                        op.instructions +
                        op.border);


            } else if (splittedString[0].equals("unmark") && splittedString.length == 2) { // check for unmark tag
                int index = Integer.valueOf(reply.split(" ")[1]);
                itemList.get(index - 1).markAsUndone();
                System.out.println(op.border +
                        "     OK, I've marked this task as not done yet: \n" +
                        "       " + itemList.get(index - 1).getDescription() + "\n" +
                        op.instructions +
                        op.border);

            } else if (splittedString[0].equals("todo")) { // check for todo tag
                if (splittedString.length == 1) { //invalid todo command
                    System.out.println(op.border +
                            "     ☹ OOPS!!! The description of a todo cannot be empty.\n" +
                            op.instructions +
                            op.border);
                } else { //valid todo command
                    Task todoTask = new Todo(reply);
                    itemList.add(todoTask);
                    System.out.println(op.border +
                            "     Got it. I've added this task: \n" +
                            "       " + todoTask.getDescription() + "\n" +
                            "     Now you have " + itemList.size() + " tasks in the list.\n" +
                            op.instructions +
                            op.border);
                }
            } else if (splittedString[0].equals("deadline")) { // check for deadline tag
                if (splittedString.length == 1) { //invalid deadline command
                    System.out.println(op.border +
                            "     ☹ OOPS!!! The description of a deadline cannot be empty.\n" +
                            op.instructions +
                            op.border);
                } else { //valid deadline command
                    Task deadlineTask = new Deadline(reply);
                    itemList.add(deadlineTask);
                    System.out.println(op.border +
                            "     Got it. I've added this task: \n" +
                            "       " + deadlineTask.getDescription() + "\n" +
                            "     Now you have " + itemList.size() + " tasks in the list.\n" +
                            op.instructions +
                            op.border);
                }
            } else if (splittedString[0].equals("event")) { // check for event tag
                if (splittedString.length == 1) { //invalid event command
                    System.out.println(op.border +
                            "     ☹ OOPS!!! The description of a event cannot be empty.\n" +
                            op.instructions +
                            op.border);
                } else { //valid event command
                    Task eventTask = new Event(reply);
                    itemList.add(eventTask);
                    System.out.println(op.border +
                            "     Got it. I've added this task: \n" +
                            "       " + eventTask.getDescription() + "\n" +
                            "     Now you have " + itemList.size() + " tasks in the list.\n" +
                            op.instructions +
                            op.border);
                }
            } else if (reply.equals("bye")) { // check for bye
                System.out.println(op.border +
                        "     Bye. Hope to see you again soon!\n" +
                        op.border);
                break;
            } else if (reply.equals("list")) { // check for list
                String totalString = op.border +
                        "    Here are the tasks in your list:\n";
                for (int i = 0; i < itemList.size(); i++) {
                    totalString += "    " + (i + 1) + ". " + itemList.get(i).getDescription() + "\n";
                }
                totalString += op.instructions +
                        op.border;
                System.out.println(totalString);
            } else if (splittedString[0].equals("delete")) { //check for delete
                try {
                    int index = Integer.valueOf(splittedString[1]);

                    String toRemove = itemList.remove(index - 1).getDescription();
                    System.out.println(op.border +
                            "     Noted. I've removed this task: \n" +
                            "       " + toRemove + "\n" +
                            "     Now you have " + itemList.size() + " tasks in the list.\n" + op.instructions +
                            op.border);
                } catch (NumberFormatException n) {
                    System.out.println("Invalid input, please enter a valid index number instead");
                }
            } else if (splittedString[0].equals("find")) {
                    String keyword = splittedString[1];
                    List<Task> filteredItemList = new ArrayList<>();
                    for (Task t : itemList) {
                        filteredItemList.add(t);
                    }
                    filteredItemList.removeIf(s -> !s.getDescription().contains(keyword));
                    String relevantTasks = "";
                    int count = 1;
                    for (Task t : filteredItemList) {
                        relevantTasks += "     " + count + "." + t.getDescription() + "\n";
                        count++;
                    }
                    System.out.println(op.border
                                       + "     Here are the matching tasks in your list:\n" + relevantTasks
                                       + op.instructions + op.border);
            } else { //check non-existing commands
                System.out.println(op.border +
                        "     ☹ OOPS!!! I'm sorry, but I don't know what that means :-(\n" +
                        op.instructions +
                        op.border);
            }

            String file2 = "data/duke.txt";
            try {
                for (Task t : itemList) {
                    writeToFile(file2, t.getDescription() + "\n");
                }
            } catch (IOException e) {
                System.out.println("");
            }
        }
    }


    /**
     * Iteration 2:
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    private void handleUserInput() {
        Label userText = new Label(userInput.getText());
        Label dukeText = new Label(getResponse(userInput.getText()));
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userText, new ImageView(user)),
                DialogBox.getDukeDialog(dukeText, new ImageView(duke))
        );
        userInput.clear();
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    private String getResponse(String input) {
        return "Duke heard: " + input;
    }

    @Override
    public void start(Stage stage) {
        //Step 1. Setting up required components

        //The container for the content of the chat to scroll.
        scrollPane = new ScrollPane();
        dialogContainer = new VBox();
        scrollPane.setContent(dialogContainer);

        userInput = new TextField();
        sendButton = new Button("Send");

        AnchorPane mainLayout = new AnchorPane();
        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);

        scene = new Scene(mainLayout);

        stage.setScene(scene);
        stage.show();

        stage.setTitle("Juke");
        stage.setResizable(false);
        stage.setMinHeight(600.0);
        stage.setMinWidth(400.0);

        mainLayout.setPrefSize(400.0, 600.0);

        scrollPane.setPrefSize(385, 535);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);

        scrollPane.setVvalue(1.0);
        scrollPane.setFitToWidth(true);

        // You will need to import `javafx.scene.layout.Region` for this.
        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);

        userInput.setPrefWidth(325.0);

        sendButton.setPrefWidth(55.0);

        AnchorPane.setTopAnchor(scrollPane, 1.0);

        AnchorPane.setBottomAnchor(sendButton, 1.0);
        AnchorPane.setRightAnchor(sendButton, 1.0);

        AnchorPane.setLeftAnchor(userInput , 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);

        //event handler
        sendButton.setOnMouseClicked((event) -> {
            handleUserInput();
        });

        userInput.setOnAction((event) -> {
            handleUserInput();
        });
        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));

    }

    /**
     * Iteration 1:
     * Creates a label with the specified text and adds it to the dialog container.
     * @param text String containing text to add
     * @return a label with the specified text that has word wrap enabled.
     */
    private Label getDialogLabel(String text) {
        // You will need to import `javafx.scene.control.Label`.
        Label textToAdd = new Label(text);
        textToAdd.setWrapText(true);


        return textToAdd;
    }
}

