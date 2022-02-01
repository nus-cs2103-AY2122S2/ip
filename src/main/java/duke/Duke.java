package duke;

import java.util.List;
import java.util.Scanner;

import duke.command.ByeCommand;
import duke.command.Command;
import duke.command.Parser;
import duke.exception.DukeException;
import duke.task.Task;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;

/**
 * Main Duke class that runs the task management program, Duke.
 */
public class Duke extends Application {
    private static final String FILE_PATH = "./data/test.txt";

    private static final Ui UI = new Ui();

    private List<Task> taskList = Storage.loadFromFile(FILE_PATH);

    @Override
    public void start (Stage stage) {
        try {
            UI.buildStage(stage);
        } catch (DukeException e) {
            System.out.println(e.getMessage());
            Platform.exit();
        }
        stage.show();
    }

    public String getResponse(String userInput) {
        try {
            Command c = Parser.parse(userInput);
            if (c instanceof ByeCommand) {
                Platform.exit();
            }
            return c.execute(taskList, UI);
        } catch (DukeException e) {
            return e.getMessage();
        }
    }

    /**
     * Driver function in order to start Duke.
     * Uses Scanner to parse commands.
     */
    public static void run() {
        UI.showWelcome();
        boolean isExit = false;
        Scanner sc = new Scanner(System.in);
        List<Task> taskList = Storage.loadFromFile(FILE_PATH);
        while (!isExit) {
            try {
                String commandLine = sc.nextLine();
                Command c = Parser.parse(commandLine);
                c.execute(taskList, UI);
                isExit = c.isExit();
            } catch (DukeException e) {
                UI.printContent(e.getMessage());
            }
        }
        UI.showExitMessage();
        sc.close();
    }
}
