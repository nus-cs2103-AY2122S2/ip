package duke;

import duke.bot.BotMessage;
import duke.bot.JJBABotMessage;
import duke.command.BotCommand;
import duke.command.Command;
import duke.command.CommandFeedback;
import duke.command.CommandType;
import duke.console.Console;
import duke.exception.DukeException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.TaskList;

import java.io.IOException;

public class Duke {
    private TaskList taskList;
    private Storage storage;
    private Console console;
    private boolean isExit = false;

    private static final String FILE_PATH = "/data/taskInfo.txt";

    public Duke() {
        this(FILE_PATH, new JJBABotMessage());
    }

    public Duke(BotMessage bot) {
        this(FILE_PATH, bot);
    }

    public Duke(String filePath, BotMessage bot){
        console = new Console(bot);
        isExit = false;

        try {
            storage = new Storage(filePath);
            taskList = storage.loadTaskList();

        } catch (IOException e) {
            console.printError("duke.storage.Storage system failure");
            console = null;

        } catch (DukeException e) {
            if (!e.isHidden) {
                console.printError(e.getMessage());
            }
            taskList = new TaskList();
        }
    }

    public static void main(String[] args) {
        new Duke(FILE_PATH, new JJBABotMessage()).run();
    }

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

    public void handleCommandFeedback(Command c, CommandFeedback cf) {
        switch (cf.cType) {
        case BOT:
            console.setBot(((BotCommand) c).getBotType());
            break;
        case EXIT:
            isExit = (cf.cType == CommandType.EXIT);
            break;
        }

        console.printCommandFeedback(cf);
    }
}
