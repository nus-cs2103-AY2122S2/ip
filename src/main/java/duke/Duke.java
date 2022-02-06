package duke;

import java.util.Scanner;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.util.Parser;
import duke.util.Ui;

/**
 * Represents a chatbot
 */
public class Duke {
    Storage storageHandler;
    TaskList tasks;

    public Duke() {
        storageHandler = new Storage();
        tasks = new TaskList();
    }

    /**
     * Main logic for the chatbot
     * Gets called in the main method
     */
    private void run() {
        storageHandler = new Storage();
        this.tasks = new TaskList(storageHandler.loadTasksFromFile());

        String botName = "Kizer";
        Ui.printGreeting(botName);

        Scanner sc = new Scanner(System.in);

        boolean canContinue = true;
        while (canContinue) {
            Parser parser = new Parser(sc.nextLine());
            String command = parser.getCommand();
            String[] inputArray = parser.getInputArray();
            String originalInput = parser.getOriginalInput();

            switch (command) {
                case "bye" -> {
                    tasks.handleBye();
                    sc.close();
                    canContinue = false;
                }
                case "list" -> tasks.handleList();
                case "mark" -> tasks.handleMark(inputArray);
                case "unmark" -> tasks.handleUnMark(inputArray);
                case "todo" -> tasks.handleTodo(inputArray, originalInput);
                case "deadline" -> tasks.handleDeadline(originalInput);
                case "event" -> tasks.handleEvent(originalInput);
                case "delete" -> tasks.handleDelete(inputArray);
                default -> Ui.printDontKnowCommand();
            }
        }
        storageHandler.saveTasksToFile(tasks.getTasks());
    }

    /**
     * Initializes a Duke chatbot object and calls Duke.Run()
     * 
     * @param args
     */
    public static void main(String[] args) {
        Duke kizer = new Duke();
        kizer.run();
    }
}
