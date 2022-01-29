package duke.commands;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import duke.common.DukeException;
import duke.common.Utils;
import duke.constants.Constants;
import duke.storage.Storage;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.Todo;
import duke.ui.Ui;

/**
 * Adds task to list based on user input.
 */
public class AddCommand extends Command {
    private String fullCommand;

    /**
     * Creates new AddCommand object that receives user input.
     * @param fullCommand fullCommand holds contents of the task to be added.
     */
    public AddCommand(String fullCommand) {
        this.fullCommand = fullCommand;
    }

    /**
     * Executes the add command.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        String[] taskArr = null;
        String type = "";
        String result = "Got it. I've added this task:\n";
        String textToAdd = "";
        Task task = null;

        try {
            taskArr = this.fullCommand.split(" ", 2);
            type = taskArr[0];

            if (type.equalsIgnoreCase("deadline")) {
                String[] taskData = taskArr[1].split(" /by ");
                LocalDate by = LocalDate.parse(taskData[1]);

                task = new Deadline(taskData[0], by);
                textToAdd = String.format(
                        "D | %s | %s | %s",
                        task.getCompleted() ? "1" : "0",
                        task.getDescription(),
                        taskData[1]);
            } else if (type.equalsIgnoreCase("event")) {
                String[] taskData = taskArr[1].split(" /at ");
                LocalDate at = LocalDate.parse(taskData[1]);

                task = new Event(taskData[0], at);
                textToAdd = String.format(
                        "E | %s | %s | %s",
                        task.getCompleted() ? "1" : "0",
                        task.getDescription(),
                        taskData[1]);
            } else if (type.equalsIgnoreCase("todo")) {
                if (taskArr[1].trim().length() == 0) {
                    throw new IndexOutOfBoundsException();
                }

                task = new Todo(taskArr[1]);
                textToAdd = String.format(
                        "T | %s | %s",
                        task.getCompleted() ? "1" : "0",
                        task.getDescription());
            } else {
                throw new IndexOutOfBoundsException();
            }

            taskList.add(task);
            storage.writeToFile(textToAdd);

            int noOfTasks = taskList.size();
            String pluralTask = (noOfTasks > 1) ? "tasks" : "task";

            result += "  " + task.toString() + "\n";
            result += "Now you have " + noOfTasks + " " + pluralTask + " in the list.";

            ui.output(result);
        } catch (IndexOutOfBoundsException e) {
            if (Utils.isValidType(type)) {
                if (Utils.isMissingData(taskArr)) {
                    ui.output("OOPS!!! Some data of your " + type + " task is missing. :-(");
                    return;
                }

                ui.output("OOPS!!! The description of a " + type + " cannot be empty. :-(");
            } else {
                ui.output(Constants.UNKNOWN_MSG);
            }
        } catch (IOException e) {
            throw new DukeException(Constants.STORAGE_ADD_MSG);
        } catch (DateTimeParseException e) {
            throw new DukeException(Constants.INVALID_DATE_MSG);
        }
    }
}
