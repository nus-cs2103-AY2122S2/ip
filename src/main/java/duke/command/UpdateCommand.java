package duke.command;

import java.time.format.DateTimeParseException;
import java.util.List;

import duke.Storage;
import duke.Ui;
import duke.exception.DukeException;
import duke.task.DeadlineTask;
import duke.task.EventTask;
import duke.task.Task;
import duke.task.TodoTask;

public class UpdateCommand extends Command {
    private static final String MESSAGE_UPDATE = "Nice! I have updated your task as follows:";

    private static final String ERROR_EMPTY_TASK_NUMBER = "OOPS!!! Empty task number, "
            + "please select a valid task to update using the task's number";
    private static final String ERROR_INVALID_TASK_NUMBER = "OOPS!!! Invalid task number, "
            + "please select a valid task to update using the task's number";
    private static final String ERROR_INVALID_FORMAT = "OOPS!!! Invalid format, enter your edits"
            + " in the following format: /{field to edit} {content}, for eg. /title New Title"
            + "or /date 2022-02-02, or /time 11:11";
    private static final String ERROR_TODO_NODATETIME = "OOPS!!! Invalid format, todo tasks"
            + "do not have a date or time attached";

    private int taskNumber;
    private String[] editParams;

    /**
     * Constructor for the update command
     * Ensures that a valid number is passed through
     *
     * @param editParams String with taskNumber and command params
     * @throws DukeException If params are empty or is not a valid number
     */
    public UpdateCommand(String editParams) throws DukeException {
        String[] command = editParams.split(" ");
        String taskNumber = command[0];
        if (taskNumber.equals("")) {
            throw new DukeException(ERROR_EMPTY_TASK_NUMBER);
        }
        try {
            this.taskNumber = Integer.parseInt(taskNumber);
            String[] filter = new String[command.length - 1];
            for (int i = 1; i < command.length; i++) {
                filter[i - 1] = command[i];
            }
            this.editParams = filter;
        } catch (NumberFormatException e) {
            throw new DukeException(ERROR_INVALID_TASK_NUMBER);
        }
    }

    @Override
    public String execute(List<Task> tasks, Ui ui) throws DukeException {
        if (this.taskNumber > tasks.size() || this.taskNumber <= 0) {
            throw new DukeException(ERROR_INVALID_TASK_NUMBER);
        }
        Task thisTask = tasks.get(taskNumber - 1);
        if (editParams.length < 2) {
            throw new DukeException(ERROR_INVALID_FORMAT);
        }

        if (editParams[0].equals("/title")) {
            editTitle(thisTask);
        } else if (editParams[0].equals("/date") || editParams[0].equals("/time")) {
            checkDateTimeUpdatability(thisTask);
            if (editParams.length != 2) {
                throw new DukeException(ERROR_INVALID_FORMAT);
            }
            if (editParams[0].equals("/date")) {
                try {
                    setDeadlineOrEventDate(thisTask);
                } catch (DateTimeParseException e) {
                    throw new DukeException(ERROR_INVALID_FORMAT);
                }
            } else if (editParams[0].equals("/time")) {
                try {
                    setDeadlineOrEventTime(thisTask);
                } catch (DateTimeParseException e) {
                    throw new DukeException(ERROR_INVALID_FORMAT);
                }
            }
        }
        Storage.saveToFile(tasks);
        return ui.getTaskLine(thisTask, MESSAGE_UPDATE);
    }

    private void setDeadlineOrEventDate(Task task) {
        if (task instanceof EventTask) {
            EventTask event = (EventTask) task;
            event.setEventDate(editParams[1]);
        }
        if (task instanceof DeadlineTask) {
            DeadlineTask deadlineTask = (DeadlineTask) task;
            deadlineTask.setDeadlineDate(editParams[1]);
        }
    }

    private void setDeadlineOrEventTime(Task task) {
        if (task instanceof EventTask) {
            EventTask event = (EventTask) task;
            event.setEventTime(editParams[1]);
        }
        if (task instanceof DeadlineTask) {
            DeadlineTask deadlineTask = (DeadlineTask) task;
            deadlineTask.setDeadlineTime(editParams[1]);
        }
    }

    private void editTitle(Task task) {
        String editedTitle = editParams[1];
        for (int i = 2; i < editParams.length; i++) {
            editedTitle += " " + editParams[i];
        }
        task.setTitle(editedTitle);
    }

    private void checkDateTimeUpdatability(Task task) throws DukeException {
        if (task instanceof TodoTask) {
            throw new DukeException(ERROR_TODO_NODATETIME);
        }
    }
}
