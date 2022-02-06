package duke;

import duke.command.ByeCommand;
import duke.command.Command;
import duke.data.Storage;
import duke.data.TaskList;
import duke.dukeexceptions.DukeException;
import duke.parser.Parser;
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
    private boolean isJavafxEnd = false;

    /**
     * Constructor for Duke Chatbot
     */
    public Duke() {
        taskList = new TaskList();
        store = new Storage(taskList);
        commandHandler = new Parser();
        cmdLine = new Ui();
        store.initialiseStorage();
        taskList = store.loadFromDisk();
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {
        try {
            Command result = commandHandler.getCommand(input);
            if (result instanceof ByeCommand) {
                this.javafxExit();
            }
            result.getResources(store, taskList);
            store.loadToDisk(taskList);
            return result.execute().callback();
        } catch (DukeException e) {
            return e.callback();
        }
    }

    /**
     * Method that stops the Chatbot;
     */
    public void stop() {
        isRunning = false;
    }

    private void javafxExit() {
        isJavafxEnd = true;
    }

    public boolean hasJavafxExited() {
        return isJavafxEnd;
    }
}
