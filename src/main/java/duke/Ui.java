package duke;

import duke.tasks.Task;

import java.io.PrintStream;
import java.util.Scanner;

public class Ui {

    private static final String line =          "-------------------------------------------------------------------------------------";
    private static final String doubleLine =    "=====================================================================================";
    private Scanner scanner = new Scanner(System.in);

    public PrintStream writer = System.out;

    public void showHelpMenu() {
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
    public void showBanner() {
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
    public void showBye() {
        String bye = "Bye. Hope to see you again soon!";

        writer.println(line);
        writer.println(bye);
        writer.println(line);
    }
    public void showStarting() {
        writer.println("Starting Application...");
    }

    // Error Messages
    public void showInvalidIntegerError() {
        writer.println("Invalid number entered! Please enter an integer");
    }
    public void showInvalidDateTimeError() {
        writer.println("Invalid Date/Time!");
    }
    public void showInvalidTaskNameError() {
        writer.println("Invalid Task Name!");
    }

    // Parser Error Messages
    public void showParseDateTimeError() {
        writer.println("Error parsing Date/Time!");
        writer.println("Please enter Date/Time in the form DD/MM/YYYY HHMM");
    }

    // Mark/Unmark Messages
    public void showMarked(Task t) {
        writer.printf("+++ Nice! I've marked this task as done:\n" +
                "+++ %s\n", t);
    }
    public void showUnmarked(Task t) {
        writer.printf("--- OK, I've marked this task as not done yet:\n" +
                "--- %s\n", t);
    }
    public void showUnmarkNotNeeded(Task t) {
        writer.printf("    This task has not been marked as done yet:\n" +
                "    %s\n", t);
    }
    public void showMarkNotNeeded(Task t) {
        writer.printf("    This task is already marked as done:\n" +
                "    %s\n", t);
    }
    public void showMarkOutOfBounds() {
        writer.println("Invalid number entered! No tasks marked.");
    }
    public void showUnmarkOutOfBounds() {
        writer.println("Invalid number entered! No tasks unmarked.");
    }
    public void showMarkEmptyList() {
        writer.println("There are no tasks in your task list to mark");
    }
    public void showUnmarkEmptyList() {
        writer.println("There are no tasks in your task list to unmark");
    }

    public void showList(TaskManager tm) {
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
    public void showAddedTask(Task t,int numberOfTasks) {
        writer.printf("Got it. I've added this task:\n" +
                "\t%s\n" +
                "Now you have %d tasks in the list.", t.toString(), numberOfTasks);
    }
    public void showDeletedTask(Task t, int numberOfTasks) {
        writer.printf(   "Noted. I have removed this task:\n" +
                "\t%s\n" +
                "There are now %d tasks in your task list\n", t, numberOfTasks);
    }
    public void showDeleteEmptyList() {
        writer.println("There are no tasks in your task list to delete.");
    }
    public void showDeleteFailed() {
        writer.println("Delete Task Failed");
    }
    public void showDeleteOutOfBounds(int limit) {
        writer.printf("Invalid number entered! The number entered must be between 1 and %s", limit);
    }

    // Save/Load Files
    public void showInitializeDefaults() {
        writer.println("...Initializing Defaults...");
    }
    public void showLoadFilePath(String filePath){
        writer.println("Loading task from: " + filePath);
    }
    public void showFileNotFound() {
        writer.println("Data File not found!");
    }
    public void showFileReadError() {
        writer.println("Error Reading from file!");
    }
    public void showDirNotFound() {
        writer.println("Directory not found!");
    }
    public void showDirCreating(String s){
        writer.printf("Creating %s...", s);
    }
    public void showDirCreated() {
        writer.println("Directory Created!");
    }
    public void showLoadingComplete() {
        writer.println("Loading Completed!");
    }
    public void showSavingComplete() {
        writer.println("Saving Completed!");
    }
    public void showSavingFailed() {
        writer.println("Saving Failed!");
    }
    public void showTaskLoadFail(String input) {
        writer.println("Task not loaded. Details: " + input);
    }

    public String getUserInputLine(){
        return scanner.nextLine();
    }

    public String getUserInput(){
        return scanner.next();
    }

    public void showMessage(String s){
        writer.println(s);
    }

    // Tests
    public static void main(String[] args) {

    }
}
