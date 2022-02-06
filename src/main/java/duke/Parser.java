package duke;

import duke.exception.DukeException;
import duke.exception.InvalidCommandException;
import duke.util.Constants;

import java.util.Arrays;

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

            if (instruction.length == 0 || !Constants.commands.contains(instruction[0])) {
                throw new InvalidCommandException();
            }

            if (instruction[0].equals("bye")) {
                return "BYE";

            } else if (instruction[0].equals("list")) {
                String response = tasksList.list();
                return response;

            } else if (instruction[0].equals("mark")) {
                int taskNum = Integer.parseInt(instruction[1]);
                String response = tasksList.mark(taskNum);
                return response;

            } else if (instruction[0].equals("unmark")) {
                int taskNum = Integer.parseInt(instruction[1]);
                String response = tasksList.unmark(taskNum);
                return response;

            } else if (instruction[0].equals("delete")) {
                int taskNum = Integer.parseInt(instruction[1]);
                String response = tasksList.deleteTask(taskNum);
                return response;

            } else if (instruction[0].equals("todo") || instruction[0].equals("event") || instruction[0].equals("deadline")) {
                String response = tasksList.addTask(Arrays.asList(instruction));
                return response;

            } else if (instruction[0].equals("save")) {
                String response = storage.exportData(tasksList.toStorageStrings(), tasksList.list());
                return response;
            } else if (instruction[0].equals("find")) {
                String response = tasksList.find(Arrays.asList(instruction));
                return response;
            }
        } catch (DukeException e) {
            return e.getMessage();
        }
        return "Nothing";
    }
}
