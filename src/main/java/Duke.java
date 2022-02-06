import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Represents a chatbot
 */
public class Duke {
    ArrayList<Task> tasks;

    private Duke() {
        tasks = new ArrayList<>();
    }

    /**
     * @param curr Task object to be added
     */
    private void processNewTask(Task curr) {
        tasks.add(curr);
        Ui.printFeedbackFooter("Got it. I've added this task:", curr, tasks);
    }

    private void handleBye() {
        Ui.printBye();
    }

    private void handleList() {
        Ui.printList(tasks);
    }

    /**
     * @param inputArray
     */
    private void handleMark(String[] inputArray) {
        int number = Integer.parseInt(inputArray[1]);
        Task curr = tasks.get(number - 1);

        curr.setDone();
        Ui.printFeedbackFooter("Nice! I've marked this task as done:", curr, tasks);
    }

    /**
     * @param inputArray
     */
    private void handleUnMark(String[] inputArray) {
        int number = Integer.parseInt(inputArray[1]);
        Task curr = tasks.get(number - 1);

        curr.setUndone();
        Ui.printFeedbackFooter("OK, I've marked this task as not done yet:", curr, tasks);
    }

    /**
     * @param inputArray
     * @param originalInput
     */
    private void handleTodo(String[] inputArray, String originalInput) {
        try {
            if (inputArray.length <= 1) {
                throw new DukeException("OOPS!!! The description of a todo cannot be empty.");
            }
        } catch (DukeException e) {
            Ui.printDukeException(e, "Please try again:");
            return;
        }

        Task curr = new Todo(originalInput.substring(4));
        processNewTask(curr);
    }

    private void handleDeadline(String originalInput) {
        String metaInfo = originalInput.split("/by")[1];
        String strippedCommand = originalInput.substring(8);
        Task curr = null;
        try {
            curr = new Deadline(" " + strippedCommand.split("/")[0], metaInfo);
        } catch (DateTimeParseException ex) {
            Ui.printMessage("Kindly input Date and Time in dd/mm/yyyy hhmm format!\nPlease try again:");
            return;
        }
        processNewTask(curr);
    }

    private void handleEvent(String originalInput) {
        String metaInfo = originalInput.split("/at")[1];
        String strippedCommand = originalInput.substring(5);
        Task curr = null;
        try {
            curr = new Event(" " + strippedCommand.split("/")[0], metaInfo);
        } catch (DateTimeParseException ex) {
            Ui.printMessage("Kindly input Date and Time in dd/mm/yyyy hhmm format!\nPlease try again:");
            return;
        }
        processNewTask(curr);
    }

    private void handleDelete(String[] inputArray) {
        int number = Integer.parseInt(inputArray[1]);

        try {
            if (((number) <= 0) || ((number) > tasks.size())) {
                throw new DukeException("Hey! That item does not exist!");
            }
        } catch (DukeException e) {
            Ui.printDukeException(e, "Please try again");
            return;
        }

        Task curr = tasks.get(number - 1);
        tasks.remove(curr);

        Ui.printFeedbackFooter("Noted. I've removed this task:", curr, tasks);
    }

    /**
     * Main logic for the chatbot
     * Gets called in the main method
     * 
     * @throws DukeException if user input is invalid
     */
    private void run() {
        Storage storageHandler = new Storage();
        Parser parser;
        this.tasks = (ArrayList<Task>) storageHandler.loadTasksFromFile();

        String botName = "Kizer";
        Ui.printGreeting(botName);

        Scanner sc = new Scanner(System.in);

        boolean canContinue = true;
        while (canContinue) {
            parser = new Parser(sc.nextLine());
            String command = parser.getCommand();
            String[] inputArray = parser.getInputArray();
            String originalInput = parser.getOriginalInput();

            switch (command) {
                case "bye":
                    handleBye();
                    sc.close();
                    canContinue = false;
                    break;
                case "list":
                    handleList();
                    break;
                case "mark":
                    handleMark(inputArray);
                    break;
                case "unmark":
                    handleUnMark(inputArray);
                    break;
                case "todo":
                    handleTodo(inputArray, originalInput);
                    break;
                case "deadline":
                    handleDeadline(originalInput);
                    break;
                case "event":
                    handleEvent(originalInput);
                    break;
                case "delete":
                    handleDelete(inputArray);
                    break;
                default:
                    Ui.printDontKnowCommand();
                    break;
            }
        }
        storageHandler.saveTaskstoFile(tasks);
    }

    /**
     * Initializes a Duke chatbot object and calls Duke.Run()
     * 
     * @param args
     * @throws DukeException if user input is invalid
     */
    public static void main(String[] args) {
        Duke kizer = new Duke();
        kizer.run();
    }
}
