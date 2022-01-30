package duke;

import java.io.IOException;

import duke.bot.BotMessage;
import duke.bot.JjbaBotMessage;
import duke.command.BotCommand;
import duke.command.Command;
import duke.command.CommandFeedback;
import duke.console.Console;
import duke.exception.DukeException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.TaskList;

/**
 * Represents a task bot. A <code>Duke</code> object can be created to
 * accept user input, create tasks and respond to the user.
 */
public class Duke {
    public static final String FILE_PATH = "/data/taskInfo.txt";

    private TaskList taskList;
    private Storage storage;
    private final Console console;
    private boolean isExit;

    /**
     * Creates a default instance of a Duke object.
     */
    public Duke() {
        this(FILE_PATH, new JjbaBotMessage());
    }

    /**
     * Creates an instance of a Duke object.
     *
     * @param bot type of message bot to output messages.
     */
    public Duke(BotMessage bot) {
        this(FILE_PATH, bot);
    }

    /**
     * Creates an instance of a Duke object.
     *
     * @param filePath path to the storage file.
     * @param bot type of message bot to output messages.
     */
    public Duke(String filePath, BotMessage bot) {
        console = new Console(bot);
        isExit = false;

        try {
            storage = new Storage(filePath);
            taskList = storage.loadTaskList();

        } catch (IOException e) {
            console.printError("Storage system failure");
            storage = null;
            taskList = new TaskList();

        } catch (DukeException e) {
            if (!e.isHidden) {
                console.printError(e.getMessage());
            }
            taskList = new TaskList();
        }
    }

    /**
     * Creates an instance of Duke and run.
     *
     * @param args unused.
     */
    public static void main(String[] args) {
        new Duke(FILE_PATH, new JjbaBotMessage()).run();
    }

    /**
     * Runs the instance of Duke to start the bot.
     */
    public void run() {
        console.printWelcomeMessage();
        isExit = false;

        do {
            try {
                String input = console.read();

                if (input.isBlank()) {
                    throw new DukeException("Empty Input", true);
                }

                Command c = Parser.parseCommand(input);
                CommandFeedback cf = c.execute(taskList);

                if (storage != null) {
                    storage.saveTaskList(taskList);
                }

                handleCommandFeedback(c, cf);

            } catch (DukeException e) {
                if (!e.isHidden) {
                    if (e.isInvalidCommand) {
                        console.printInvalidCommand(e.getMessage());

                    } else {
                        console.printError(e.getMessage());
                    }
                }
            }
        } while (!isExit);
    }

    /**
     * Method to handle the output according to the different command type in the command feedback.
     *
     * @param com the type of command being executed.
     * @param comFeed the feedback of the command after being executed.
     */
    public void handleCommandFeedback(Command com, CommandFeedback comFeed) {
        switch (comFeed.cType) {
        case BOT:
            console.setBot(((BotCommand) com).getBotType());
            break;
        case EXIT:
            isExit = true;
            break;
        default:
            console.printError("Unexpected error in handleCommandFeedback!");
        }

        console.printCommandFeedback(comFeed);
    }
}
