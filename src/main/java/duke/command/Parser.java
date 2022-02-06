package duke.command;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;

import duke.error.DukeException;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Handles all user input entered into Duke <br>
 */
public class Parser {

    /**
     * Validates and processes input entered by the user. <br>
     * Inputs of incorrect format will not be processed, and an error message will be shown instead. <br>
     * <br>
     * Returns the following: <br>
     * <li>-1: Indicates command to terminate </li>
     * <li>0 : Indicates command ran successfully, without changes to the task list</li>
     * <li>1 : Indicates command ran successfully, with changes to the task list</li>
     *
     * @param inputString The input that is entered by the user
     * @param ui The Ui object that used to output the appropriate messages to the user
     * @param taskList The taskList that is used to maintain list of tasks in Duke
     * @return: Returns an integer that indicate the result of the run.
     */
    public int run(String inputString, TaskList taskList) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/M/yyyy");
        String[] args = inputString.split("\\s+");
        String action = args[0];

        try {
            validate(inputString, action, args, taskList);
        } catch (DukeException de) {
            Ui.print(de.getMessage());
            return 1;
        }

        switch(action) {
        case "bye":
            Ui.print("Bye. Hope to see you again soon!");
            return -1;
        case "list":
            if (args.length > 1) {
                switch (args[1]) {
                case "/on":
                    Ui.print(taskList.getTasksBasedOnDate(LocalDate.parse(args[2],
                            DateTimeFormatter.ofPattern("dd/M/yyyy")), 0));
                    break;
                case "/before":
                    Ui.print(taskList.getTasksBasedOnDate(LocalDate.parse(args[2],
                            DateTimeFormatter.ofPattern("dd/M/yyyy")), 1));
                    break;
                case "/after":
                    Ui.print(taskList.getTasksBasedOnDate(LocalDate.parse(args[2],
                            DateTimeFormatter.ofPattern("dd/M/yyyy")), 2));
                    break;
                default:
                    break;
                }
            } else {
                Ui.print(taskList.getList());
            }
            return 0;
        case "mark":
            taskList.getTask(Integer.parseInt(args[1]) - 1).mark();
            Ui.print("Task marked as done: ", " " + taskList.getTask(Integer.parseInt(args[1]) - 1).toString());
            return 1;
        case "unmark":
            taskList.getTask(Integer.parseInt(args[1]) - 1).unmark();
            Ui.print("Task as not done yet: ", " " + taskList.getTask(Integer.parseInt(args[1]) - 1).toString());
            return 1;
        case "todo":
            taskList.addTask((inputString).substring(5).trim(), false, null, 0);
            Ui.print("Added Task: ", " " + taskList.getLast().toString(),
                    String.format("There are now %d task(s) in the list.", taskList.getSize()));
            return 1;
        case "deadline":
            taskList.addTask(inputString.substring(9).split("/by")[0].trim(), false,
                    LocalDate.parse(inputString.split("/by")[1].substring(1), formatter), 1);
            Ui.print("Added Task: ", " " + taskList.getLast().toString(),
                    String.format("There are now %d task(s) in the list.", taskList.getSize()));
            return 1;
        case "event":
            taskList.addTask(inputString.substring(6).split("/at")[0].trim(), false,
                    LocalDate.parse(inputString.split("/at")[1].substring(1), formatter), 2);
            Ui.print("Added Task: ", " " + taskList.getLast().toString(),
                    String.format("There are now %d task(s) in the list.", taskList.getSize()));
            return 1;
        case "delete":
            String deletedTaskString = taskList.getTask(Integer.parseInt(args[1]) - 1).toString();
            taskList.removeTask(Integer.parseInt(args[1]) - 1);
            Ui.print("Deleted Task:", " " + deletedTaskString,
                    String.format("There are now %d task(s) in the list.", taskList.getSize()));
            return 1;
        case "find":
            Ui.print(taskList.findTasksContaining(args[1]), "Here are the matching tasks in your list:");
            return 1;
        default:
            Ui.print("MEOW?");
            return 0;
        }
    }

    /**
     * Validates a given String and argument list based on the given action and task list. <br>
     * A DukeException is thrown when the String or argument do not fit the corresponding <br>
     * action and task list provided. <br>
     * <br>
     *
     * @param inputString The string to be checked.
     * @param action The type action to be checked with.
     * @param args The argument list to be checked.
     * @param taskList The list of task to be checked with.
     */

    public void validate(String inputString, String action, String[] args, TaskList taskList) throws DukeException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/M/yyyy");
        switch(action) {
        case "list":
            if (args.length > 1) {
                if (args[1].equals("/on") || args[1].equals("/before") || args[1].equals("/after")) {
                    try {
                        LocalDate.parse(args[2], formatter);
                    } catch (DateTimeParseException | ArrayIndexOutOfBoundsException e) {
                        throw new DukeException("Provide the date in the format dd-mm-yyyy!");
                    }
                }
            }
            break;
        case "mark":
        case "unmark":
        case "delete":
            if (args.length == 1) {
                throw new DukeException("Task ID must be provided!");
            }
            try {
                Integer.parseInt(args[1]);
            } catch (NumberFormatException e) {
                throw new DukeException("Task ID must be an integer!");
            }
            if (taskList.getSize() == 0) {
                throw new DukeException("Task List is empty!");
            }
            if (Integer.parseInt(args[1]) > taskList.getSize()) {
                throw new DukeException("Task ID out of range!");
            }
            break;
        case "todo":
            if (args.length == 1) {
                throw new DukeException("Task Name must be provided!");
            }
            break;
        case "deadline":
        case "event":
            String flag = action.equals("deadline") ? "/by" : "/at";
            int actionLength = action.equals("deadline") ? 9 : 6;
            if (args.length == 1) {
                throw new DukeException("Task Name must be provided!");
            }
            if (Arrays.stream(args).noneMatch(flag::equals)) {
                throw new DukeException(String
                        .format("%s flag not detected. Please specify date using %s!", flag, flag));
            }
            if (inputString.trim().split(flag).length == 1) {
                throw new DukeException(String.format("Please specify deadline date after %s!", flag));
            }
            if (inputString.substring(actionLength).split(flag)[0].trim().equals("")) {
                throw new DukeException("Task Name must be provided!");
            }
            try {
                LocalDate.parse(inputString.split(flag)[1].substring(1), formatter);
            } catch (DateTimeParseException e) {
                throw new DukeException("Provide the date in the format dd-mm-yyyy!");
            }
            break;
        case "find":
            if (args.length == 1) {
                throw new DukeException("Provide keyword");
            }
            break;
        default:
            break;
        }
    }
}
