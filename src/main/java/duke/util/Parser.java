package duke.util;

import duke.TaskList;
import duke.exception.CommandNotFoundException;
import duke.exception.DukeException;
import duke.exception.InvalidArgumentException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.ToDo;

public class Parser {
    public static String parse(String line, TaskList taskList) throws DukeException {
        String[] input = line.strip().split(" ", 2);

        String command = input[0];
        boolean isValidArgument = input.length == 2;

        if (Constants.TASKS.contains(command) && !isValidArgument) {
            throw new InvalidArgumentException();
        }

        switch (command) {
        case "bye":
            return "BYE";
        case "list":
            return taskList.getTasks();
        case "mark":
            int taskId = Integer.parseInt(input[1]);
            return taskList.mark(taskId, input[0]);
        case "unmark":
            taskId = Integer.parseInt(input[1]);
            return taskList.mark(taskId, input[0]);
        case "find":
            return taskList.findKeyWord(input[1]);
        case "todo":
            String toDo = input[1].strip();
            return taskList.addTask(new ToDo(toDo));
        case "event":
            String[] eventDetails = input[1].strip().split(" /at ", 2);
            return taskList.addTask(new Event(eventDetails[0], eventDetails[1]));
        case "deadline":
            String[] deadlineDetails = input[1].strip().split(" /by ", 2);
            return taskList.addTask(new Deadline(deadlineDetails[0], deadlineDetails[1]));
        case "delete":
            taskId = Integer.parseInt(input[1]);
            return taskList.remove(taskId);
        default:
            throw new CommandNotFoundException();
        }
    }
}
