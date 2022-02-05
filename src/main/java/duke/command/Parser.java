package duke.command;

import duke.DukeException;

import java.util.Arrays;


/**
 * This class parses user input from Duke.
 * It can recognise 8 commands and deals with invalid user input.
 *
 * @author Jian Rong
 */
public class Parser {

    /**
     * Calls the appropriate method based on user input, or handles invalid user input.
     *
     * @param input Input comes from user
     * @param taskList TaskList is the list of tasks that was created in Duke
     * @return boolean Returns false if user says "bye", returns true otherwise
     */
    public static boolean parseInput(String input, TaskList taskList) {
        String[] inputArray = input.split(" ");
        String[] tempArray = Arrays.copyOfRange(inputArray, 1, inputArray.length);
        String [] command = {inputArray[0], String.join(" ", tempArray)};
        try {
            switch (command[0]) {
            case ("bye"):
              return false;
            case ("list"):
                taskList.listItem();
                break;
            case ("mark"):
                taskList.markItem(command);
                break;
            case ("unmark"):
                taskList.unmarkItem(command);
                break;
            case ("delete"):
                taskList.deleteItem(command);
                break;
            case ("todo"):
            case ("deadline"):
            case ("event"):
                taskList.addItem(command);
                break;
            default:
                throw new DukeException("Sorry I don't understand that command");
            }
        } catch (DukeException e) {
            System.out.println(e.getMessage());
            System.out.println("__________________________________");
        } catch (IndexOutOfBoundsException | NumberFormatException e) {
            System.out.println("This index doesn't exist in list");
            System.out.println("__________________________________");
        }
        return true;
    }


}
