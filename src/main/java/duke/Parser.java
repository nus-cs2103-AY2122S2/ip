package duke;

import duke.TasksList;
import duke.exception.DukeException;
import duke.exception.InvalidCommandException;
import duke.util.Constants;
import duke.Storage;

import java.io.IOException;
import java.util.Arrays;

public class Parser {
    public String parse(String data, TasksList taskslist, Storage storage) {
        try {
            String[] instruction = data.split(" ");

            if (instruction.length == 0 || !Constants.commands.contains(instruction[0])) {
                throw new InvalidCommandException();
            }

            if (instruction[0].equals("bye")) {
                return "BYE";

            } else if (instruction[0].equals("list")) {
                String response = taskslist.list();
                return response;

            } else if (instruction[0].equals("mark")) {
                int taskNum = Integer.parseInt(instruction[1]);
                String response = taskslist.mark(taskNum);
                return response;

            } else if (instruction[0].equals("unmark")) {
                int taskNum = Integer.parseInt(instruction[1]);
                String response = taskslist.unmark(taskNum);
                return response;

            } else if (instruction[0].equals("delete")) {
                int taskNum = Integer.parseInt(instruction[1]);
                String response = taskslist.deleteTask(taskNum);
                return response;

            } else if (instruction[0].equals("todo") || instruction[0].equals("event") || instruction[0].equals("deadline")) {
                String response = taskslist.addTask(Arrays.asList(instruction));
                return response;

            } else if (instruction[0].equals("save")) {
                String response = storage.exportData(taskslist.toStorageStrings(), taskslist.list());
                return response;
            }
        }
        catch (DukeException e) {
            return e.getMessage();
        }
        return "Nothing";
    }
}
