package duke;

import java.util.Scanner;
import java.util.ArrayList;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
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

    private Storage storage;
    private TaskList list = new TaskList(new ArrayList<>());
    private Ui ui;
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;
    private Image user = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            list = new TaskList(storage.load());
        } catch (IOException e) {
            System.out.println("error");
            System.out.println(e.getMessage());
        }
    }

    public Duke() {
        ui = new Ui();
        storage = new Storage("data/tasks.tx");
        try {
            list = new TaskList(storage.load());
        } catch (IOException e) {
            System.out.println("error");
            System.out.println(e.getMessage());
        }
    }

    /**
     * Runs the Price command that is given
     *
     * @param input The command to be run
     * @throws DukeException if the command is formatted wrongly
     */
    private String handle(String input) throws DukeException{
        String[] split = input.split(" ");
        String command = split[0];
        String out = "";
        if (command.equals("todo")) {
            if (split.length == 1) {
                throw new DukeException("It looks like you're missing the task description");
            } else {
                Todo t = new Todo(input.substring(5));
                list.add(t);
                out += ("Got it. I've added: \n" + t.toString() + "\n");
                out += String.format("Sheesh you've now got %d tasks in the list\n", list.size());
            }
        } else if (command.equals("deadline")) {
            if (split.length == 1) {
                throw new DukeException("It looks like you're missing the task description");
            } else {
                String[] arr = input.substring(9).split("/by ");
                if (arr.length == 1) {
                    throw new DukeException("It seems you haven't included the deadline");
                } else {
                    String[] date = arr[1].split("-");
                    if (date.length != 3) {
                        throw new DukeException("Your deadline should be in the format yyyy-mm-dd");
                    }
                    Deadline d = new Deadline(arr[0],
                            LocalDate.of(Integer.parseInt(date[0]),
                                    Integer.parseInt(date[1]),
                                    Integer.parseInt(date[2])));
                    list.add(d);
                    out += ("Got it. I've added: \n" + d.toString() + "\n");
                    out += String.format("Sheesh you've now got %d tasks in the list\n", list.size());
                }
            }
        } else if (command.equals("event")) {
            if (split.length == 1) {
                throw new DukeException("It looks like you're missing the task description");
            } else {
                String[] arr = input.substring(6).split("/at ");
                if (arr.length == 1) {
                    throw new DukeException("It seems you haven't included the event time");
                } else {
                    Event e = new Event(arr[0], arr[1]);
                    list.add(e);
                    out +=  ("Got it. I've added: \n" + e.toString() + "\n");
                    out += String.format("Sheesh you've now got %d tasks in the list\n", list.size());
                }
            }
        } else if (command.equals("bye")) {
            try {
                FileWriter fw = new FileWriter("prince.txt");
                for (Integer i = 0; i < list.size(); i++) {
                    fw.write(list.get(i).toString());
                    fw.write(System.lineSeparator());
                }
                fw.close();
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
            out += ("See you later alligator :)");
        } else if (command.equals("list")) {
            out = list.printList();
        } else if (command.equals("mark") || command.equals("unmark")) {
            if (split.length <= 1) {
                throw new DukeException("You need to specify which task :/");
            }
            int idx = Integer.parseInt(split[1]);
            if (idx > list.size()) {
                throw new DukeException("The task number you've given is invalid");
            } else {
                Task t = list.get(idx - 1);
                if (command.equals("unmark")) {
                    t.makeNotDone();
                    out += String.format("Ok boss I've marked task %s as incomplete\n", split[1]);
                } else {
                    t.makeDone();
                    out +=  String.format("Woohoo! I've marked task %s as done\n", split[1]);
                }
                out +=  (t.toString() + "\n");
            }
        } else if (command.equals("delete")) {
            if (list.size() == 0) {
                throw new DukeException("There aren't any tasks left to delete");
            } else {
                if (split.length <= 1) {
                    throw new DukeException("You need to specify which task :/");
                }
                int idx = Integer.parseInt(split[1]);
                if (idx > list.size()) {
                    throw new DukeException("The task number you've given is invalid");
                } else {
                    Task t = list.get(idx - 1);
                    list.remove(idx - 1);
                    out += String.format("Alrighty I've removed this task\n");
                    out += (t.toString() + "\n");
                    out += String.format("K you've now got %d tasks in the list\n", list.size());
                }
            }
        } else if (command.equals("find")) {
            if (list.size() == 0) {
                throw new DukeException("There aren't any tasks search :)");
            }
            String searchString = split[1];
            list.search(searchString);
        } else {
            throw new DukeException("I'm not sure what that means");
        }
        return out;
    }

    private void run() {
        Scanner sc = new Scanner(System.in);

        while (true) {
            try {
                String command = sc.nextLine();
                handle(command);
                ui.printDivider();
                if (command.equals("bye")) {
                    break;
                }
            } catch (DukeException e) {
                ui.showError(e);
            }
        }
    }

    /**
     * The main method.
     * Calls the driver methods to begin the execution of Prince.
     */
    public static void main(String[] args) {
        Application.launch(Duke.class, args);
    }

    @Override
    public void start(Stage stage) throws DukeException {
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
        stage.setTitle("Prince");
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

        sendButton.setOnMouseClicked((event) -> {
            dialogContainer.getChildren().add(getDialogLabel(userInput.getText()));
            userInput.clear();
        });

        userInput.setOnAction((event) -> {
            dialogContainer.getChildren().add(getDialogLabel(userInput.getText()));
            userInput.clear();
        });

        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));

        sendButton.setOnMouseClicked((event) -> {
            handleUserInput();
        });

        userInput.setOnAction((event) -> {
            handleUserInput();
        });
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
        try {
            return handle(input);
        } catch (DukeException e) {
            return ui.showError(e);
        }
    }

}
