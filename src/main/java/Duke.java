import java.util.ArrayList;
import java.util.Scanner;

/**
 * Represents a chatbot
 */
public class Duke {
    ArrayList<Task> tasks;

    private static final String BAR = "____________________________________________________________";

    private Duke() {
        tasks = new ArrayList<>();
    }

    /**
     * @param curr Task object to be printed
     */
    private void printTask(Task curr) {
        System.out.println(curr.getTaskIcon() + " [" + curr.isDone() + "]" + curr);
    }

    private void printNoOfTasks() {
        System.out.println("Now you have " + tasks.size() + " tasks in the list");
    }

    /**
     * @param curr Task object to be added
     */
    private void processNewTask(Task curr) {
        tasks.add(curr);
        printTask(curr);
        printNoOfTasks();
    }

    private void handleBye() {
        System.out.println(BAR);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(BAR);
    }

    private void handleList() {
        System.out.println(BAR);
        System.out.println("Here are the tasks in your list:");
        for (int i = 1; i <= tasks.size(); i++) {
            Task curr = tasks.get(i - 1);
            System.out.print(i + ".");
            printTask(curr);
        }
        System.out.println(BAR);
    }

    
    /** 
     * @param inputArray
     */
    private void handleMark(String[] inputArray) {
        int number = Integer.parseInt(inputArray[1]);
        Task curr = tasks.get(number - 1);

        curr.setDone();
        System.out.println(BAR);
        System.out.println("Nice! I've marked this task as done:");
        printTask(curr);
        System.out.println(BAR);
    }

    
    /** 
     * @param inputArray
     */
    private void handleUnMark(String[] inputArray) {
        int number = Integer.parseInt(inputArray[1]);
        Task curr = tasks.get(number - 1);

        curr.setUndone();
        System.out.println(BAR);
        System.out.println("OK, I've marked this task as not done yet:");
        printTask(curr);
        System.out.println(BAR);
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
            System.err.println(e.getMessage());
            System.out.println("Please try again:");
            return;
        }

        System.out.println(BAR);
        System.out.println("Got it. I've added this task:");
        Task curr = new Todo(originalInput.replaceAll(".*\\btodo\\.*", ""));
        processNewTask(curr);
        System.out.println(BAR);
    }

    private void handleDeadline(String originalInput) {
        String metaInfo = originalInput.split("/by")[1];
        String strippedCommand = originalInput.replaceAll(".*\\bdeadline\\.*", "");
        System.out.println(BAR);
        System.out.println("Got it. I've added this task:");
        Task curr = new Deadline(strippedCommand.split("/")[0], metaInfo);
        processNewTask(curr);
        System.out.println(BAR);
    }

    private void handleEvent(String originalInput) {
        String metaInfo = originalInput.split("/at")[1];
        String strippedCommand = originalInput.replaceAll(".*\\bevent\\.*", "");
        System.out.println(BAR);
        System.out.println("Got it. I've added this task:");
        Task curr = new Event(strippedCommand.split("/")[0], metaInfo);
        processNewTask(curr);
        System.out.println(BAR);
    }

    private void handleDelete(String[] inputArray) {
        int number = Integer.parseInt(inputArray[1]);

        try {
            if (((number) <= 0) || ((number) > tasks.size())) {
                throw new DukeException("Hey! That item does not exist!");
            }
        } catch (DukeException e) {
            System.err.println(e.getMessage());
            System.out.println("Please try again:");
            return;
        }

        Task curr = tasks.get(number - 1);
        String message = curr.getTaskIcon() + " [" + curr.isDone() + "]" + curr;
        tasks.remove(curr);

        System.out.println(BAR);
        System.out.println("Noted. I've removed this task:");
        System.out.println(message);
        printNoOfTasks();
        System.out.println(BAR);
    }

    /**
     * Main logic for the chatbot
     * Gets called in the main method
     * 
     * @throws DukeException if user input is invalid
     */
    private void run() {
        Storage storageHandler = new Storage();
        this.tasks = (ArrayList<Task>) storageHandler.loadTasksFromFile();

        Scanner sc = new Scanner(System.in);

        String greeting = "Hello! I'm Kizer\nWhat can I do for you?";
        System.out.println(BAR);
        System.out.println(greeting);
        System.out.println(BAR);

        boolean canContinue = true;
        while (canContinue) {
            String originalInput = sc.nextLine();
            String[] inputArray = originalInput.split(" ");
            String command = inputArray[0];

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
                    System.out.println("OOPS!!! I'm sorry, but I don't know what that means :-(");
                    System.out.println("Please try again:");
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
