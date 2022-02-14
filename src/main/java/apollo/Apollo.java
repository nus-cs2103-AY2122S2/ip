package apollo;

import java.time.LocalTime;

import apollo.commands.Command;
import apollo.commands.ExitCommand;
import apollo.exceptions.ApolloException;
import apollo.exceptions.ApolloIoException;
import apollo.parser.Parser;
import apollo.tasks.TaskList;
import apollo.ui.Ui;
import apollo.ui.Welcome;
import apollo.ui.gui.Gui;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * Main class for program.
 */
public class Apollo extends Application {

    private static TaskList taskList;
    private static boolean isLoaded;

    /**
     * Starts running the program.
     *
     * @param args Arguments supplied to program (not used).
     */
    public static void main(String[] args) {
        initialise();
        run();
    }

    /**
     * Initialises the program
     */
    private static void initialise() {
        try {
            taskList = Storage.load();
            isLoaded = true;
        } catch (ApolloIoException e) {
            taskList = new TaskList();
            isLoaded = false;
        }
        Command.setTaskList(taskList);
    }

    /**
     * Runs main response loop of the program while waiting for exit signal.
     */
    private static void run() {
        assert taskList != null : "taskList did not initialise.";
        Ui ui = new Ui();
        ui.printMessage(Welcome.getLogo() + Welcome.greet(isLoaded, LocalTime.now()));

        Command command = null;
        do {
            String userCommand = ui.getUserCommand();
            try {
                command = new Parser().parseCommand(userCommand);
                ui.printMessage(command.execute());
                Storage.save(taskList);
            } catch (ApolloException e) {
                ui.printMessage(e.getMessage());
            }
        } while (!ExitCommand.isExit(command));
    }

    public static String getResponse(String userCommand) {
        assert taskList != null : "taskList did not initialise.";
        try {
            Command command = new Parser().parseCommand(userCommand);
            String outcome = command.execute();
            Storage.save(taskList);
            return outcome;
        } catch (ApolloException e) {
            return e.getMessage();
        }
    }

    @Override
    public void start(Stage primaryStage) {
        Gui gui = new Gui();
        initialise();
        gui.start(primaryStage, Welcome.greet(isLoaded, LocalTime.now()));
    }
}
