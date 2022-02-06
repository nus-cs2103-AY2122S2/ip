package duke.command;

import duke.DukeException;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;

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
        String[] command = {inputArray[0], String.join(" ", tempArray)};
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
                parseAddItem(command, taskList);
                break;
            case ("find"):
                taskList.findItem(command[1]);
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

    private static void parseAddItem(String[] command, TaskList taskList) throws DukeException {
        switch (command[0]) {
        case "todo":
            if (command[1].isEmpty()) {
                throw new DukeException("Please use this format: duke.task.Todo <Activity>");
            }
            taskList.addTodo(command[1]);
            break;
        case "deadline":
            try {
                String title = command[1].split(" /by ")[0];
                String deadline = command[1].split(" /by ")[1];
                String[] deadlineList = deadline.split(" ");
                LocalDate date = LocalDate.parse(deadlineList[0].replace("/", "-"));
                LocalTime time = LocalTime.parse(deadlineList[1]);
                taskList.addDeadline(title, date, time);
            } catch (IndexOutOfBoundsException | DateTimeParseException e){
                throw new DukeException("Please tell me the deadline in this format: <Activity> /by YYYY/MM/DD HH:MM");
            }
            break;
        case "event":
            try {
                String title = command[1].split(" /at ")[0];
                String deadline = command[1].split(" /at ")[1];
                String[] deadlineList = deadline.split(" ");
                LocalDate date = LocalDate.parse(deadlineList[0].replace("/", "-"));
                LocalTime time = LocalTime.parse(deadlineList[1]);
                taskList.addEvent(title, date, time);
            } catch (IndexOutOfBoundsException | DateTimeParseException e){
                throw new DukeException("Please tell me the deadline in this format: <Activity> /at YYYY/MM/DD HH:MM");
            }
            break;
        }
        System.out.printf("You have %d tasks in your list\n", taskList.getSize());
        System.out.println("__________________________________");

    }


}
