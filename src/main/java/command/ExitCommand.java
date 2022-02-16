package command;

import java.util.Timer;
import java.util.TimerTask;

import duke.Storage;
import duke.Ui;
import duke.UiForGUI;
import task.TaskList;

/**
 * The ExitCommand class is a type of Command that is used to stop running the bot application.
 */
public class ExitCommand extends Command {
    /**
     * Constructs a ExitCommand object.
     *
     * @param command the command input by the user.
     * @param tokenizedCommand the command input by the user that is split by space.
     */
    public ExitCommand(String command, String[] tokenizedCommand) {
        super(command, tokenizedCommand);
    }

    /**
     * Executes the exit command, displays the goodbye and stops running the bot application.
     *
     * @param tasks TaskList that stores the current list of tasks.
     * @param ui Ui of the bot application.
     * @param storage Storage to store the list of tasks.
     */
    @Override
    public void execute(TaskList tasks, UiForGUI ui, Storage storage) {
        this.isRun = false;
        ui.showGoodbye();
        Timer timer = new Timer();
        TimerTask exitApp = new TimerTask() {
            public void run() {
                System.exit(0);
            };
        };
        // exits the app after 3 seconds
        timer.schedule(exitApp, 3 * 1000);
    }
}
