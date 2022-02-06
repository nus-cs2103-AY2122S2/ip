package duke;

import java.time.format.DateTimeParseException;
// import java.util.Scanner;

import duke.exceptions.InvalidCommandException;
import duke.exceptions.InvalidDescriptionException;

/**
 * Ui class handles the input
 *
 * @author Justin Ng Jie Ern
 */
public class Ui {

    /**
     * Constant String from Duke after every command to Duke.
     */
    private static final String FROM_DUKE = "From Duke: \n\t";

    /**
     * Storage Class to help with loading or saving or file.
     */
    private Storage storage;

    /**
     * TaskList Object is to help loaded and save.
     */
    private TaskList taskList;

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
    public String run(String command) {
        //Scanner scanner = new Scanner(System.in);
        String firstWord = "";
        String reply = "";
        //while (true) {
        try {
            //String command = scanner.nextLine();
            //System.out.print(FROM_DUKE);
            String[] commandArr = command.split(" ");
            firstWord = commandArr[0];
            if (!(firstWord.equals("bye") || firstWord.equals("list") || firstWord.equals("find")
                    || firstWord.equals("event") || firstWord.equals("todo") || firstWord.equals("deadline")
                    || firstWord.equals("delete") || firstWord.equals("save") || firstWord.equals("help")
                    || firstWord.equals("mark") || firstWord.equals("unmark"))) {
                throw new InvalidCommandException();

                // One word commands
            } else if (firstWord.equals("bye")) {
                reply = taskList.bye(storage);
                //break;
            } else if (firstWord.equals("save")) {
                reply = storage.save();
            } else if (firstWord.equals("list")) {
                reply = taskList.list();
            } else if (firstWord.equals("help")) {
                reply = taskList.help();

                //Multiple word commands
            } else if (commandArr.length < 2) {
                throw new InvalidDescriptionException();
            } else if (firstWord.equals("mark") || firstWord.equals("unmark")) {
                reply = taskList.taskCheck(command);
            } else if (firstWord.equals("find")) {
                reply = taskList.find(command);
            } else if (firstWord.equals("todo")) {
                reply = taskList.todo(command);
            } else if (firstWord.equals("deadline")) {
                reply = taskList.deadline(command);
            } else if (firstWord.equals("event")) {
                reply = taskList.event(command);
            } else { //firstWord.equals("delete")
                reply = taskList.delete(command);
            }

        } catch (InvalidCommandException e) {
            System.out.println("'" + firstWord + "' is an invalid command! Please try again!");
            reply = "'" + firstWord + "' is an invalid command! Please try again!";
        } catch (InvalidDescriptionException e) {
            System.out.println("There cannot be an empty description of your task! Please try again! ");
            reply = "There cannot be an empty description of your task! Please try again! ";
        } catch (DateTimeParseException e) {
            System.out.println("An invalid time has been added. Please use a YYYY-MM-DD HH:MM format.");
            reply = "An invalid time has been added. Please use a YYYY-MM-DD HH:MM format.";
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("That is an invalid task. Please try again!");
            reply = "That is an invalid task. Please try again!";
        } finally {
            System.out.println("__________________________________________");
            return reply + "\n";
        }
        //}

    }
}
