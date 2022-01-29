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
    private TaskList tasks;
    private Ui ui;

    /**
     * Returns a Duke Object with helper classes initialized according to the /data/duke.txt file.
     */
    public Duke() {
        this.ui = new Ui();
        ui.showStartUpMessage();
        this.tasks = new TaskList();
        try {
            this.storage = new Storage(this.tasks);
        } catch (IOException e) {
            ui.showFeedbackMessage("\n\tError reading from duke.txt\n");
        }
    }

    /**
     * Executes the instructions given by user until a Command.BYE is issued by the user.
     */
    public void execute() {
        boolean canExit = false;
        String input;
        while (!canExit) {
            try {
                input = ui.readCommand();
                String[] commands = input.split(" ", 2);
                Command command = Parser.parseString(commands);
                switch (command) {
                case BYE:
                    canExit = true;
                    ui.showFeedbackMessage("\n\tBye. Hope to see you again soon!\n");
                    try {
                        storage.saveTask();
                    } catch (IOException e) {
                        ui.showFeedbackMessage("\n\tError saving duke.txt file.\n");
                    }
                    break;
                case LIST:
                    ui.showFeedbackMessage(tasks.listTasks());
                    break;
                case MARK:
                    ui.showFeedbackMessage(tasks.markAsDone(commands[1], true));
                    break;
                case UNMARK:
                    ui.showFeedbackMessage(tasks.unmarkDone(commands[1]));
                    break;
                case DELETE:
                    ui.showFeedbackMessage(tasks.deleteTask(commands[1]));
                    break;
                case INVALID:
                    ui.showFeedbackMessage("\n\t" + commands[0] + " is not a valid command\n");
                    break;
                case EMPTY:
                    break;
                case FIND:
                    ui.showFeedbackMessage(tasks.findKeyword(commands[1].trim()));
                    break;
                default:
                    ui.showFeedbackMessage(tasks.addTask(command, commands[1], true));
                    break;
                }
            } catch (DukeException e) {
                ui.showFeedbackMessage("\n\t" + e.getMessage() + "\n");
            }
        }
    }

    /**
     * Starts the program.
     *
     * @param args ignored
     */
    public static void main(String[] args) {
        Duke d = new Duke();
        d.execute();
    }
}
