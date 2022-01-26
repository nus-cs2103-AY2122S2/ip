package com.duke.command;

import com.duke.exception.DukeInvalidArgumentException;
import com.duke.modules.Storage;
import com.duke.modules.TaskList;
import com.duke.tasks.Deadline;
import com.duke.tasks.Event;
import com.duke.tasks.Task;
import com.duke.tasks.Todo;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class CommandAdd extends Command {
    private String inputDateTimeFormat = "dd/MM/yyyy HHmm";
    private String type;
    private String input;
    private TaskList taskList;

    public CommandAdd(String type, String input, TaskList taskList) {
        super();
        this.type = type;
        this.input = input;
        this.taskList = taskList;
    }

    @Override
    public CommandResult execute() {
        try {
            return new CommandResult(taskAdder(type, input));
        } catch (DukeInvalidArgumentException e) {
            return new CommandResult(e.getMessage());
        } catch (DateTimeParseException e) {
            return new CommandResult(
                    "Valid date and time must be given in this format: DD/MM/YYYY HHmm"
                            + "\nEg: 24/12/2022 2359");
        } catch (IOException e) {
            return new CommandResult("Unable to save list." +
                    "Please check if you have permission to write to files in the following directory: "
                    + Storage.getInstance().getDirectoryPath());
        }
    }

    private Task.TaskType taskIdentifier(String type, String args) throws DukeInvalidArgumentException {
        Task.TaskType taskType = null;
        if (type.equals("todo")) {
            if (!args.matches("\\S+.*")) {
                throw new DukeInvalidArgumentException(
                        "☹ OOPS!!! The description of a todo cannot be empty.");
            }
            taskType = Task.TaskType.TODO;
        } else if (type.equals("deadline")) {
            if (!args.matches("\\S+.*\\s/by\\s\\S+.*")) {
                throw new DukeInvalidArgumentException(
                        "☹ OOPS!!! The description/date of a deadline cannot be empty.");
            }
            taskType = Task.TaskType.DEADLINE;
        } else if (type.equals("event")) {
            if (!args.matches("\\S+.*\\s/at\\s\\S+.*")) {
                throw new DukeInvalidArgumentException(
                        "☹ OOPS!!! The description/location of a event cannot be empty.");
            }
            taskType = Task.TaskType.EVENT;
        }

        return taskType;
    }

    private String taskAdder(String type, String args)
            throws DukeInvalidArgumentException, DateTimeParseException, IOException {
        String message = "";
        Task task = null;
        Task.TaskType taskType = taskIdentifier(type, args);
        switch (taskType) {
        case TODO:
            task = new Todo(args);
            break;
        case DEADLINE:
            String timeDate = args.substring(args.indexOf(" /by ") + 5);
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(inputDateTimeFormat);
            task = new Deadline(args.substring(0, args.indexOf(" /by ")),
                    LocalDateTime.parse(timeDate, dateTimeFormatter));
            break;
        case EVENT:
            task = new Event(args.substring(0, args.indexOf(" /at ")),
                    args.substring(args.indexOf(" /at ") + 5));
            break;
        }
        taskList.addTask(task);
        return String.format("Got it. I've added this task:\n\t%s\nNow you have %d task(s) in the list",
                task.toString(), taskList.getTaskListSize());
    }

}
