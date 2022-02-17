package duke;

import duke.command.Command;
import duke.command.RemindCommand;
import duke.exception.DukeException;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Encapsulates the Duke task manager bot.
 *
 * @author William Ming
 * @version 0.1
 */
public class Duke {

    private static final String paragraphSeparator = "\n\n";
    private TaskList taskList;
    private Storage storage;
    private Ui ui;
    private boolean isExit;
    private boolean hasLoadedSuccessfully;

    /**
     * Initialises a Duke task manager bot.
     *
     * @param dirPath path to the directory where the txt file is stored.
     * @param fileName name of the txt file.
     */
    public Duke(String dirPath, String fileName) {
        ui = new Ui();
        storage = new Storage(dirPath, fileName);
        taskList = new TaskList();
        isExit = false;
        hasLoadedSuccessfully = false;
    }

    /**
     * Loads data from the file stored in this Duke's storage, then
     * gives the welcome message and the loaded data.
     *
     * @return the welcome message and the loaded data as a String.
     */
    public String loadDataAndWelcome() {
        String message = ui.showWelcome();
        message += getLoadingStatus();
        if (isNewUser()) {
            message += paragraphSeparator + ui.welcomeNewUser();
        } else {
            message += getReminders();
        }
        return message;

    }

    /** Returns the loading status after trying to load the data.**/
    private String getLoadingStatus() {
        try {
            storage.loadData(taskList);
        } catch (DukeException e) {
            return paragraphSeparator + ui.showLoadingError(e.getMessage());
        }

        hasLoadedSuccessfully = true;
        if (taskList.getLength() == 0) {
            return "";
        }
        return paragraphSeparator + ui.showLoadingSuccess();
    }

    private boolean isNewUser() {
        return hasLoadedSuccessfully && taskList.getLength() == 0;
    }

    /** Reminds the user if there are tasks due within the next DEFAULT_NUM_DAYS days.**/
    private String getReminders() {
        TaskList reminders = taskList.getUpcomingTasks(RemindCommand.DEFAULT_NUM_DAYS);
        return paragraphSeparator + ui.showRemindResult(reminders, RemindCommand.DEFAULT_NUM_DAYS);
    }

    /**
     * Gets Duke's response to a given user input.
     *
     * @param userInput the text input given by the user.
     * @return Duke's response as a String.
     */
    public String getResponse(String userInput) {
        try {
            Command command = Parser.parse(userInput, taskList.getLength());
            assert command != null : "command cannot be null";

            isExit = command.isExit();
            return command.execute(taskList, ui, storage);
        } catch (DukeException e) {
            return ui.showCommandError(e.getMessage());
        }
    }

    /**
     * Indicates whether the application should exit.
     *
     * @return true iff a ByeCommand was previously executed in getResponse.
     */
    public boolean isExit() {
        return isExit;
    }
}
