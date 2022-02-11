package duke;

import com.sun.tools.jconsole.JConsoleContext;
import duke.command.Command;
import duke.command.UndoCommand;
import duke.stack.CallStack;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;
import duke.util.Constants;
import duke.util.Parser;

import java.awt.*;
import java.util.Scanner;

/**
 * Duke driver class, contains the main method to initiate
 * Duke chat-bot.
 */
public class Duke {
    private TaskList taskList;
    private final Ui ui;
    private final Storage storage;
    private final CallStack callStack;

    /**
     * Duke constructor method, takes no parameter,
     * initialize Ui, storage, and task list loaded from
     * saved storage.
     */
    public Duke() {
        ui = new Ui();
        storage = new Storage();
        callStack = new CallStack(10);
        try {
            this.taskList = storage.load();
        } catch (Exception e) {
            ui.showLoadingError(e);
            taskList = new TaskList();
        }
    }

    public static void main(String[] args) {
        new Duke().run();
    }

    /**
     * Run the Duke chatbot to accept user input.
     * Process user's input and stopped when user input "bye"
     */
    public void run() {
        Scanner sc = new Scanner(System.in);
        ui.greetings();
        boolean isExit = false;
        while (!isExit) {
            String input = sc.nextLine();
            String output = processUserInput(input);
            ui.log(output);
            isExit = output.equals(Constants.BYE);
        }
        ui.bye();
    }

    public String processUserInput(String input) {
        try {
            Command response = Parser.parse(input);
            String output = response.executeCommand(taskList, callStack);
            storage.updateStorage(taskList);
            return output;
        } catch (Exception e) {
            return e.getMessage();
        }
    }
}
