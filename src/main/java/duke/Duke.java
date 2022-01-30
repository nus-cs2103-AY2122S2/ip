package duke;
import duke.command.ByeCommand;
import duke.command.Command;
import duke.data.Storage;
import duke.data.TaskList;
import duke.dukeexceptions.DukeException;
import duke.parser.Parser;
import duke.responses.Response;
import duke.ui.Ui;

/***
 * Duke is a program that takes in user inputs and stores them as Tasks
 */
public class Duke {
    private TaskList taskList;
    private Storage store;
    private Parser commandHandler;
    private Ui cmdLine;
    private boolean isRunning = true;

    /**
     * Constructor for Duke Chatbot
     */
    public Duke() {
        this.taskList = new TaskList();
        this.store = new Storage(taskList);
        this.commandHandler = new Parser();
        this.cmdLine = new Ui();
    }

    /**
     * Starts Duke
     */
    public void run() {
        store.initialiseStorage();
        taskList = store.loadFromDisk();
        cmdLine.callResponse(commandHandler.welcome());
        cmdLine.callResponse(commandHandler.start());
        while (isRunning) {
            try {
                String stringCmd = cmdLine.getNextLine();
                Command cmd = commandHandler.getCommand(stringCmd);
                cmd.getResources(store, taskList);
                if (cmd instanceof ByeCommand) {
                    isRunning = false;
                }
                Response feedback = cmd.execute();
                cmdLine.callResponse(feedback);
            } catch (DukeException e) {
                e.callback();
            }
        }
    }

    /**
     * Method that stops the Chatbot;
     */
    public void stop() {
        this.isRunning = false;
    }

    public static void main(String[] args) {
        new Duke().run();
    }
}
