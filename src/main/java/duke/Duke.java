package duke;

import java.io.IOException;

import duke.command.Command;
import duke.exception.DukeException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;


/**
 * Represents the start of the program and the main run loop.
 */
public class Duke {
    private Storage storage;
    private final TaskList tasks;
    private final Ui ui;
    private boolean isAlive;

    /**
     * Returns a Duke Object with helper classes initialized according to the /data/duke.txt file.
     */
    public Duke() {
        this.ui = new Ui();
        this.isAlive = true;
        ui.formatStartUpMessage();
        this.tasks = new TaskList();
        try {
            this.storage = new Storage(this.tasks);
        } catch (IOException e) {
            ui.formatFeedbackMessage("\n\tError reading from duke.txt\n");
        }
    }

    /**
     * Executes the instructions given by user until a Command.BYE is issued by the user.
     */
    private String executeInstruction(String input) {
        try {
            String[] commands = input.split(" ", 2);
            Command command = Parser.parseString(commands);
            switch (command) {
            case BYE:
                try {
                    storage.saveTask();
                    isAlive = false;
                    return ui.formatFeedbackMessage("\n\tBye. Hope to see you again soon!\n");
                } catch (IOException e) {
                    return ui.formatFeedbackMessage("\n\tError saving duke.txt file.\n");
                }
            case LIST:
                return ui.formatFeedbackMessage(tasks.listTasks());
            case MARK:
                return ui.formatFeedbackMessage(tasks.markAsDone(commands[1], true));
            case UNMARK:
                return ui.formatFeedbackMessage(tasks.unmarkDone(commands[1]));
            case DELETE:
                return ui.formatFeedbackMessage(tasks.deleteTask(commands[1]));
            case INVALID:
                return ui.formatFeedbackMessage("\n\t" + commands[0] + " is not a valid command\n");
            case EMPTY:
                return "";
            case FIND:
                return ui.formatFeedbackMessage(tasks.findKeyword(commands[1].trim()));
            default:
                return ui.formatFeedbackMessage(tasks.addTask(command, commands[1], true));
            }
        } catch (DukeException e) {
            return ui.formatFeedbackMessage("\n\t" + e.getMessage() + "\n");
        }
    }

    /**
     * Returns isAlive boolean value;
     *
     * @return boolean value of isAlive.
     */
    public boolean isAlive() {
        return this.isAlive;
    }

    /**
     * Executes the command given and returns a response String.
     *
     * @param text command given by user.
     * @return Response String.
     */
    public String getResponse(String text) {
        return this.executeInstruction(text.trim());
    }

}
