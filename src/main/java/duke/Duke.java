package duke;

import java.util.Scanner;

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
     * Initializes a Duke chatbot object and calls Duke.Run()
     *
     * @param args varargs
     */
    public static void main(String[] args) {
        Duke kizer = new Duke();
        kizer.run();
    }

    /**
     * Returns a String containing what should be printed in the GUI
     * @return the response string from StringBuilder.toString()
     */
    public String getResponse() {
        return response.toString();
    }

    /**
     * Main logic for the chatbot
     * Gets called in the main method
     */
    private void run() {
        storageHandler = new Storage();
        this.tasks = new TaskList(storageHandler.loadTasksFromFile());

        String botName = "Duke";
        ResponseFormatter.printGreeting(botName);

        Scanner sc = new Scanner(System.in);

        boolean canContinue = true;
        while (canContinue) {
            Parser parser = new Parser(sc.nextLine());
            String command = parser.getCommand();
            String[] inputArray = parser.getInputArray();
            String originalInput = parser.getOriginalInput();

            switch (command) {
            case "bye":
                tasks.handleBye();
                sc.close();
                canContinue = false;
                break;
            case "list":
                tasks.handleList();
                break;
            case "mark":
                tasks.handleMark(inputArray);
                break;
            case "unmark":
                tasks.handleUnMark(inputArray);
                break;
            case "todo":
                tasks.handleTodo(inputArray, originalInput);
                break;
            case "deadline":
                tasks.handleDeadline(originalInput);
                break;
            case "event":
                tasks.handleEvent(originalInput);
                break;
            case "delete":
                tasks.handleDelete(inputArray);
                break;
            case "find":
                tasks.handleFind(inputArray);
                break;
            default:
                ResponseFormatter.printDontKnowCommand();
                break;
            }
        }
        storageHandler.saveTasksToFile(tasks.getTasks());
    }
}
