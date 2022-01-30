package duke;

import duke.exception.InvalidCommandException;
import duke.exception.InvalidDescriptionException;

import java.time.format.DateTimeParseException;
import java.util.Scanner;

/**
 * Ui class handles the input
 *
 * @author Justin Ng Jie Ern
 */
public class Ui {

    /**
     * Constant String from Duke after every command to Duke.
     */
    private final static String FROM_DUKE = "From Duke: \n\t";

    /**
     * Storage Class to help with loading or saving or file.
     */
    Storage storage;

    /**
     * TaskList Object is to help loaded and save.
     */
    TaskList taskList;

    /**
     * Constructor to create a Ui Object.
     *
     * @param taskList Constructor to create a Ui Object.
     * @param storage Storage Class to help with loading or saving or file.
     */
    public Ui(TaskList taskList, Storage storage) {
        this.storage = storage;
        this.taskList = taskList;
    }

    /**
     * Method to run Duke.
     */
    public void uiHandler() {
        Scanner scanner = new Scanner(System.in);
        String firstWord = "";
        while (true) {
            try {
                String command = scanner.nextLine();
                System.out.print(FROM_DUKE);
                String[] commandArr = command.split(" ");
                firstWord = commandArr[0];
                if (!(firstWord.equals("bye") || firstWord.equals("list") || firstWord.equals("find") ||
                        firstWord.equals("event") || firstWord.equals("todo") || firstWord.equals("deadline") ||
                        firstWord.equals("delete") || firstWord.equals("save") || firstWord.equals("help") ||
                        firstWord.equals("mark") || firstWord.equals("unmark"))) {
                    throw new InvalidCommandException();

                    // One word commands
                } else if (firstWord.equals("bye")) {
                    storage.writeTasksToFile();
                    taskList.bye();
                    break;
                } else if (firstWord.equals("save")) {
                    storage.save();
                } else if (firstWord.equals("list")) {
                    taskList.list();
                } else if (firstWord.equals("help")) {
                    taskList.help();

                    //Multiple word commands
                } else if (commandArr.length < 2) {
                    throw new InvalidDescriptionException();
                } else if (firstWord.equals("mark") || firstWord.equals("unmark")) {
                    taskList.taskCheck(command);
                } else if (firstWord.equals("find")) {
                    taskList.find(command);
                } else if (firstWord.equals("todo")) {
                    taskList.todo(command);
                } else if (firstWord.equals("deadline")) {
                    taskList.deadline(command);
                } else if (firstWord.equals("event")) {
                    taskList.event(command);
                } else { //firstWord.equals("delete")
                    taskList.delete(command);
                }

            } catch (InvalidCommandException e) {
                System.out.println("'" + firstWord + "' is an invalid command! Please try again!");
            } catch (InvalidDescriptionException e) {
                System.out.println("There cannot be an empty description of your task! Please try again! ");
            } catch (DateTimeParseException e) {
                System.out.println("An invalid time has been added. Please use a YYYY-MM-DD HH:MM format.");
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("That is an invalid task. Please try again!");
            } finally {
            System.out.println("__________________________________________");
        }
        }
    }
}