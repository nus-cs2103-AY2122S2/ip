package narcibot;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Scanner;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class Narcibot extends Application {
    private Storage storage;
    private TaskList taskList;
    private Ui ui;
    private Parser parser;
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;
    private Image user = new Image(this.getClass().getResourceAsStream("/images/User.png"));
    private Image narcibot = new Image(this.getClass().getResourceAsStream("/images/Narcibot.png"));

    /**
     * Constructor for a Narcibot
     */
    public Narcibot() {
        String fileName = "tasks.txt";
        String path = "./data";
        ui = new Ui();
        parser = new Parser();
        storage = new Storage(fileName, path);
        try {
            taskList = new TaskList(storage.load());
        } catch (IOException e) {
            ui.noFile();
        }
    }

    public static void main(String[] args) {
        new Narcibot().run();
    }

    /**
     * Launches the GUI interface for narcibot
     * @param stage
     */
    @Override
    public void start(Stage stage) {
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

        stage.setTitle("Narcibot");
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

        Label narciText = new Label(ui.welcome());
        dialogContainer.getChildren().add(
                new DialogBox(narciText, new ImageView(narcibot))
        );
        sendButton.setOnMouseClicked((event) -> {
            handleUserInput();
        });

        userInput.setOnAction((event) -> {
            handleUserInput();
        });

        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));
    }

    private void handleUserInput() {
        Label userText = new Label(userInput.getText());
        System.out.println(userInput.getText());
        Label narciText = new Label(getResponse(userInput.getText()));
        DialogBox userBox = new DialogBox(userText, new ImageView(user));
        userBox.setAlignment(Pos.TOP_RIGHT);
        userBox.getChildren().get(0).toFront();
        dialogContainer.getChildren().addAll(
                userBox,
                new DialogBox(narciText, new ImageView(narcibot))
        );
        userInput.clear();
    }

    private String getResponse(String input) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(baos);
        PrintStream old = System.out;
        System.setOut(ps);
        command(parser.parse(input));
        System.out.flush();
        System.setOut(old);
        return baos.toString();
    }

    /**
     * Runs the Narcibot program.
     */
    public void run()  {
        System.out.println(ui.welcome());
        String input;
        Scanner sc = new Scanner(System.in);
        while (true) {
            input = sc.nextLine();
            if(command(parser.parse(input))) {
                break;
            }
        }

        try {
                taskList.store(storage.store());
        } catch (IOException e) {
            System.out.println("File not found");
        }
    }

    private boolean command(String[] command) {
        boolean end = false;
        try {
            switch (command[0]) {
            case "bye":
                if (command.length != 1) {
                    throw new IncorrectFormatException("Please enter only one word for this command");
                }
                ui.bye();
                return true;
            case "list":
                if (command.length != 1) {
                    throw new IncorrectFormatException("Please enter only one word for this command.");
                }
                ui.list();
                taskList.list();
                break;
            case "mark":
                if (command.length != 2) {
                    throw new IncorrectFormatException("Please enter mark followed by a number for this command. Example: mark 8");
                }
                ui.mark();
                taskList.mark(command[1]);
                break;
            case "unmark":
                if (command.length != 2) {
                    throw new IncorrectFormatException("Please enter unmark followed by a number for this command. Example: unmark 7");
                }
                ui.unmark();
                taskList.unmark(command[1]);
                break;
            case "delete":
                if (command.length != 2) {
                    throw new IncorrectFormatException("Please enter delete followed by a number for this command. Example: delete 7");
                }
                ui.delete();
                taskList.delete(command[1]);
                break;
            case "todo":
                if (command.length != 2) {
                    throw new IncorrectFormatException("You want me to remind you of something but you won't tell me of what it is?");
                }
                ui.task(taskList.todo(command[1]));
                break;
            case "deadline":
                if (command.length != 3) {
                    throw new IncorrectFormatException("You want me to remind you of something but you won't tell me of what it is? The format is deadline (task) /by (time)");
                }
                ui.task(taskList.deadline(command[1], command[2]));
                break;
            case "event":
                if (command.length != 3) {
                    throw new IncorrectFormatException("You want me to remind you of something but you won't tell me of what it is? The format is event (task) /at (time)");
                }
                ui.task(taskList.event(command[1], command[2]));
                break;
            case "find":
                if (command.length != 2) {
                    throw new IncorrectFormatException("You need to give me a keyword to find something.");
                }
                ui.find();
                taskList.find(command[1]);
                break;
            default:
                ui.unknown();
            }
        }  catch (NumberFormatException e) {
            System.out.println("Are you even trying to specify a task? Please enter in digits if you're wondering.");
        } catch (IncorrectFormatException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

}
