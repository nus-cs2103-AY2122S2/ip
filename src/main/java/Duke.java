import java.io.IOException;

public class Duke {
    private TaskList taskList;
    private Storage storage;
    private Console console;

    private static final String FILE_PATH = "/data/taskInfo.txt";

    public Duke(String filePath){
        console = new Console();

        try {
            storage = new Storage(filePath);
            taskList = storage.loadTaskList();

        } catch (IOException e) {
            console.printError("Storage system failure");
            console = null;

        } catch (DukeException e) {
            if (!e.isHidden) {
                console.printError(e.getMessage());
            }
            taskList = new TaskList();
        }
    }

    public static void main(String[] args) {
        new Duke(FILE_PATH).run();
    }

    public void run() {
        console.printWelcomeMessage();
        boolean isExit = false;

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

                if (cf.cType == CommandType.BOT) {
                    console.setBot(((BotCommand) c).getBotType());
                }

                console.printCommandFeedback(cf);
                isExit = (cf.cType == CommandType.EXIT);

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


}
