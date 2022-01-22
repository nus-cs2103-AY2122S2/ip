import java.io.PrintStream;

public class Ui {

    private static final String line =          "-------------------------------------------------------------------------------------";
    private static final String doubleLine =    "=====================================================================================";

    public static PrintStream writer = System.out;

    public static void showHelpMenu() {
        String helpMenu = "List of available commands:\n\n" +
                "\"list\": View Current Task List\n" +
                "\"todo <name>\": Add a new Task\n" +
                "\"deadline <name> /by <date/time>\": Add a new Deadline\n" +
                "\"event <name> /at <date/time>\": Add a new Event\n" +
                "\"delete <number>\": delete the corresponding entry in your task list\n" +
                "\"mark <number>\": mark the corresponding entry in your task list as done\n" +
                "\"unmark <number>\": mark the corresponding entry in your task list as not done\n" +
                "\"bye\": Exit the chatbot application";

        writer.println(line);
        writer.println(helpMenu);
        writer.println(line);
    }
    public static void showBanner() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        String intro = "I am a Personal Assistant Chatbot that helps you to keep track of various things.\n"
                + "Let's get started.\n";

        writer.println("Hello from\n" + logo);
        writer.println(intro);
        writer.println(line);
    }
    public static void showBye() {
        String bye = "Bye. Hope to see you again soon!";

        writer.println(line);
        writer.println(bye);
        writer.println(line);
    }
    public static void showStarting() {
        writer.println("Starting Application...");
    }

    // Error Messages
    public static void showInvalidIntegerError() {
        writer.println("Invalid number entered! Please enter an integer");
    }
    public static void showInvalidDateTimeError() {
        writer.println("Invalid Date/Time!");
    }
    public static void showInvalidTaskNameError() {
        writer.println("Invalid Task Name!");
    }

    // Parser Error Messages
    public static void showParseDateTimeError() {
        writer.println("Error parsing Date/Time!");
        writer.println("Please enter Date/Time in the form DD/MM/YYYY HHMM");
    }

    // Mark/Unmark Messages
    public static void showMarked(Task t) {
        writer.printf("+++ Nice! I've marked this task as done:\n" +
                "+++ %s\n", t);
    }
    public static void showUnmarked(Task t) {
        writer.printf("--- OK, I've marked this task as not done yet:\n" +
                "--- %s\n", t);
    }
    public static void showUnmarkNotNeeded(Task t) {
        writer.printf("    This task has not been marked as done yet:\n" +
                "    %s\n", t);
    }
    public static void showMarkNotNeeded(Task t) {
        writer.printf("    This task is already marked as done:\n" +
                "    %s\n", t);
    }
    public static void showMarkOutOfBounds() {
        writer.println("Invalid number entered! No tasks marked.");
    }
    public static void showUnmarkOutOfBounds() {
        writer.println("Invalid number entered! No tasks unmarked.");
    }
    public static void showMarkEmptyList() {
        writer.println("There are no tasks in your task list to mark");
    }
    public static void showUnmarkEmptyList() {
        writer.println("There are no tasks in your task list to unmark");
    }

    public static void showList(TaskManager tm) {
        int i = 1;
        String s;
        if (tm.size() == 0){
            writer.println(doubleLine);
            writer.println("Your task list is empty.");
            writer.println(doubleLine);

        } else {
            writer.println(doubleLine);
            writer.println("Here is your task list:");
            for (Task t : tm.getTaskList()) {
                writer.printf("%d.%s\n", i, t.toString());
                i++;
            }
            writer.println(doubleLine);
        }
    }

    // Add/Delete Messages
    public static void showAddedTask(Task t,int numberOfTasks) {
        writer.printf("Got it. I've added this task:\n" +
                "\t%s\n" +
                "Now you have %d tasks in the list.", t.toString(), numberOfTasks);
    }
    public static void showDeletedTask(Task t, int numberOfTasks) {
        writer.printf(   "Noted. I have removed this task:\n" +
                "\t%s\n" +
                "There are now %d tasks in your task list", t, numberOfTasks);
    }
    public static void showDeleteEmptyList() {
        writer.println("There are no tasks in your task list to delete.");
    }
    public static void showDeleteOutOfBounds(int limit) {
        writer.printf("Invalid number entered! The number entered must be between 1 and %s", limit);
    }

    // Save/Load Files
    public static void showInitializeDefaults() {
        writer.println("...Initializing Defaults...");
    }
    public static void showFileNotFound() {
        writer.println("Data File not found!");
    }
    public static void showFileReadError() {
        writer.println("Error Reading from file!");
    }
    public static void showDirNotFound() {
        writer.println("Directory not found!");
    }
    public static void showDirCreating(String s){
        writer.printf("Creating %s...", s);
    }
    public static void showDirCreated() {
        writer.println("Directory Created!");
    }
    public static void showLoadingComplete() {
        writer.println("Loading Completed!");
    }
    public static void showSavingComplete() {
        writer.println("Saving Completed!");
    }
    public static void showTaskLoadFail(String input) {
        writer.println("Task not loaded. Details: " + input);
    }



    // Tests
    public static void main(String[] args) {
        showBanner();
        showHelpMenu();
    }
}
