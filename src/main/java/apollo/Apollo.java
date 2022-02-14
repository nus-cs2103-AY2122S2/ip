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
    private static Ui ui;

    /**
     * Main method to start runnning the program.
     *
     * @param args Arguments supplied to program (not used).
     */
    public static void main(String[] args) {
        initialise();
        run();
    }

    /**
     * Initialises the program and greets the user.
     */
    private static void initialise() {
        ui = new Ui();
        boolean isLoaded;
        try {
            taskList = Storage.load();
            isLoaded = true;
        } catch (ApolloIoException e) {
            taskList = new TaskList();
            isLoaded = false;
        }

        Command.setTaskList(taskList);
        Welcome.printLogo();
        ui.printMessage(Welcome.greet(isLoaded, LocalTime.now()));
    }

    /**
     * Main response loop to run program while waiting for exit signal.
     */
    private static void run() {
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

    public String getResponse(String userCommand) {
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
    public void start(Stage primaryStage) throws Exception {
        Gui gui = new Gui();
        try {
            taskList = Storage.load();
        } catch (ApolloIoException e) {
            taskList = new TaskList();
        }

        Command.setTaskList(taskList);
        gui.start(primaryStage);
    }
}
