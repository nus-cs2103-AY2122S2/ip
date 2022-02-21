package dukeclasses;

import java.time.LocalDate;

public class ParsedData extends ParsedInput {
    private boolean isMarked;

    /**
     * Constructor for ParsedData for commands that need 2 String and an Integer to be instantiated.
     *
     * @param command String , usually the command, that is used to instantiated Tasks.
     * @param task String , usually the description of the Task, that
     *             is used to identify the task in the task list.
     */
    public ParsedData(String command, String markIndication, String task) {
        super(command, task);
        if (markIndication.contains("X")) {
            this.isMarked = true;
        }
    }

    /**
     * Constructor for ParsedData for commands that  need 3 String,a RecurPeriod and a LocalDate to be instantiated.
     *
     * @param command String that is the command that is used to instantiate Tasks.
     * @param markIndication String that indicates whether the Task is marked as done or not.
     * @param task String that is the description of the Task.
     * @param dueDate A LocalDate which represents the due date of the Task.
     * @param recurPeriod A RecurPeriod which represents the period the task will recur if it has one.
     */
    public ParsedData(String command, String markIndication, String task, LocalDate dueDate, RecurPeriod recurPeriod) {
        super(command, task, dueDate, recurPeriod);
        if (markIndication.contains("X")) {
            this.isMarked = true;
        }
    }

    /**
     * Converts the data in ParsedData into the corresponding Task.
     *
     * @return Task that represents the data in ParsedData.
     * @throws DukeException Exception is thrown when the command of ParsedInput is not any of the following:
     *                       "todo", "deadline", "event".
     */
    @Override
    public Task convertToTask() throws DukeException {
        Task task;
        switch(super.getCommand()) {
        case "todo":
            task = new ToDo(super.getTask());
            break;
        case "deadline":
            task = new Deadline(super.getTask(), super.getDueDate(), super.getRecurPeriod());
            break;
        case "event":
            task = new Event(super.getTask(), super.getDueDate(), super.getRecurPeriod());
            break;
        default:
            throw new DukeException();
        }

        if (isMarked) {
            task.setDone(true);
        }

        return task;
    }
}
