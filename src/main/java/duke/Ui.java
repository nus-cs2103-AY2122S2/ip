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
    private final static String fromDuke = "From Duke_two: \n\t";

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
                System.out.print(fromDuke);
                String[] commandArr = command.split(" ");
                firstWord = commandArr[0];
                if (!(firstWord.equals("bye") || firstWord.equals("list") || firstWord.equals("mark") ||
                        firstWord.equals("unmark") || firstWord.equals("todo") || firstWord.equals("deadline") ||
                        firstWord.equals("event") || firstWord.equals("delete") || firstWord.equals("save"))) {
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

                    //Multiple word commands
                } else if (commandArr.length < 2) {
                    throw new InvalidDescriptionException();
                } else if (firstWord.equals("mark") || firstWord.equals("unmark")) {
                    taskList.taskCheck(command);
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
            System.out.println("________________________________");
        }
        }
    }
}