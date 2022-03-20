package duke;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.util.Parser;
import duke.util.ResponseFormatter;


/**
 * Represents a chatbot
 */
public class Duke {
    private Storage storageHandler;
    private TaskList tasks;
    private String response;

    /**
     * Default constructor
     */
    public Duke() {
        storageHandler = new Storage();
        tasks = new TaskList();
        response = "";
    }

    /**
     * Returns a String containing what should be printed in the GUI
     *
     * @return the response string from StringBuilder.toString()
     */
    public String getResponse(String input) {
        run(input);
        assert !response.isEmpty() : "Response for Duke cannot be empty!";
        return response;
    }

    private void run(String input) {
        storageHandler = new Storage();
        this.tasks = new TaskList(storageHandler.loadTasksFromFile());

        Parser parser = new Parser(input);
        String command = parser.getCommand();
        String[] inputArray = parser.getInputArray();
        String originalInput = parser.getOriginalInput();

        switch (command) {
        case "bye":
            response = tasks.handleBye();
            break;
        case "list":
            response = tasks.handleList();
            break;
        case "mark":
            response = tasks.handleMark(inputArray);
            break;
        case "unmark":
            response = tasks.handleUnMark(inputArray);
            break;
        case "todo":
            response = tasks.handleTodo(inputArray, originalInput);
            break;
        case "deadline":
            response = tasks.handleDeadline(originalInput);
            break;
        case "event":
            response = tasks.handleEvent(originalInput);
            break;
        case "delete":
            response = tasks.handleDelete(inputArray);
            break;
        case "find":
            response = tasks.handleFind(inputArray);
            break;
        default:
            response = ResponseFormatter.printDontKnowCommand();
            break;
        }
        storageHandler.saveTasksToFile(tasks.getTasks());
    }
}
