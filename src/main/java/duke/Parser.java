package duke;

import java.util.Arrays;

import duke.exception.DukeException;
import duke.exception.InvalidCommandException;
import duke.util.Constants;

/**
 * Main logic to parse and translate user inputs.
 */
public class Parser {

    /**
     * Translates user inputs and performs the supposed actions.
     *
     * @param data      User input.
     * @param tasksList TasksList variable from Duke.
     * @param storage   Storage variable from Duke.
     * @return Response text to be printed by the UI.
     */
    public String parse(String data, TasksList tasksList, Storage storage) {
        try {
            String[] instruction = data.split(" ");

            if (instruction.length == 0 || !Constants.COMMANDS.contains(instruction[0])) {
                throw new InvalidCommandException();
            }

            //variables needed for switch case.
            String response;
            int taskNum;

            switch(instruction[0]) {
            case "bye":
                return Constants.BYE;

            case "list":
                response = tasksList.list();
                break;

            case "todo":
            case "event":
            case "deadline":
                response = tasksList.addTask(Arrays.asList(instruction));
                break;

            case "save":
                response = storage.exportData(tasksList.toStorageStrings(), tasksList.list());
                break;

            case "find":
                response = tasksList.findMatchingTasks(Arrays.asList(instruction));
                break;

            case "mark":
                taskNum = Integer.parseInt(instruction[1]);
                response = tasksList.mark(taskNum);
                break;

            case "unmark":
                taskNum = Integer.parseInt(instruction[1]);
                response = tasksList.unmark(taskNum);
                break;

            case "delete":
                taskNum = Integer.parseInt(instruction[1]);
                response = tasksList.deleteTask(taskNum);
                break;

            case "reminder":
                response = tasksList.getTasksUnder(Arrays.asList(instruction));
                break;

            default:
                throw new DukeException("Something is wrong!");
            }

            //save after every command
            storage.exportData(tasksList.toStorageStrings(), tasksList.list());
            return response;
        } catch (DukeException e) {
            return e.getMessage();
        }
    }
}
