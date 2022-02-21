package dukeclasses;

import java.time.LocalDate;

/**
 * Represents a wrapper class after user input is processed.Created if user input is valid.
 */
public class ParsedInput {
    private String command;
    private Integer index;
    private LocalDate dueDate;
    private String task;
    private RecurPeriod recurPeriod;

    /**
     * Constructor for ParsedInput for commands that only need String to be instantiated.
     *
     * @param command String , usually the command, that is used to instantiated Tasks.
     */
    public ParsedInput(String command) {
        this.command = command;
        this.index = null;
        this.dueDate = null;
        this.task = null;
        this.recurPeriod = null;
    }

    /**
     * Constructor for ParsedInput for commands that  need a String and Integer to be instantiated.
     *
     * @param command String , usually the command, that is used to instantiated Tasks.
     * @param index Integer , usually representing index of task in the TaskList, that is used
     *              to modify the indexed task in the TaskList.
     */
    public ParsedInput(String command, Integer index) {
        this.command = command;
        this.index = index;
        this.dueDate = null;
        this.task = null;
        this.recurPeriod = null;
    }

    /**
     * Constructor for ParsedInput for commands that  need a String and Integer to be instantiated.
     *
     * @param command String , usually the command, that is used to instantiated Tasks.
     * @param task String , usually the description of the Task, that
     *             is used to identify the task in the task list.
     */
    public ParsedInput(String command, String task) {
        this.command = command;
        this.index = null;
        this.dueDate = null;
        this.task = task;
        this.recurPeriod = null;
    }

    /**
     * Constructor for ParsedInput for commands that  need a String and Integer to be instantiated.
     *
     * @param command String , usually the command, that is used to instantiated Tasks.
     * @param task String , usually the description of the Task, that
     *             is used to identify the task in the task list.
     * @param dueDate LocalDate which represents the dateline of the Task.
     * @param recurPeriod RecurPeriod which determine how much the deateline will
     *                    extend when the task recurs.
     */
    public ParsedInput(String command, String task, LocalDate dueDate, RecurPeriod recurPeriod) {
        this.command = command;
        this.index = null;
        this.dueDate = dueDate;
        this.task = task;
        this.recurPeriod = recurPeriod;
    }

    /**
     * Returns the command the user gave.
     *
     * @return String that represents the command the user gave.
     */
    public String getCommand() {
        return this.command;
    }

    /**
     * Returns the index the user gave.
     *
     * @return Integer that represents the index the user gave.
     */
    public Integer getIndex() {
        return this.index;
    }

    /**
     * Returns the due date the user gave.
     *
     * @return LocalDate that represents the due date of the
     *         task which is given by the user.
     */
    public LocalDate getDueDate() {
        return this.dueDate;
    }

    /**
     * Returns the description of the task.
     *
     * @return String that represents the description of the task.
     */
    public String getTask() {
        return this.task;
    }

    /**
     * Returns the recurPeriod of the task.
     *
     * @return RecurPeriod that represents the recurPeriod of the task.
     */
    public RecurPeriod getRecurPeriod() {
        return this.recurPeriod;
    }

    /**
     * Converts the data in ParsedInput into the corresponding Task.
     *
     * @return Task that represents the data in ParsedInput.
     * @throws DukeException Exception is thrown when the command of ParsedInput is not any of the following:
     *                       "todo", "deadline", "event".
     */
    public Task convertToTask() throws DukeException {
        switch(command) {
        case "todo":
            return new ToDo(task);
        case "deadline":
            return new Deadline(task, dueDate, recurPeriod);
        case "event":
            return new Event(task, dueDate, recurPeriod);
        default:
            throw new DukeException();
        }
    }

}
