package dukeclasses;

import java.time.LocalDate;

public class ParsedCommand extends ParsedInput {

    /**
     * Constructor for ParsedCommand for commands that only need String to be instantiated.
     *
     * @param command String , usually the command, that is used to instantiated Tasks.
     */
    public ParsedCommand(String command) {
        super(command);
    }

    /**
     * Constructor for ParsedCommand for commands that  need a String and Integer to be instantiated.
     *
     * @param command String , usually the command, that is used to instantiated Tasks.
     * @param index Integer , usually representing index of task in the TaskList, that is used
     *              to modify the indexed task in the TaskList.
     */
    public ParsedCommand(String command, Integer index) {
        super(command, index);
    }

    /**
     * Constructor for ParsedCommand for commands that  need a String and Integer to be instantiated.
     *
     * @param command String , usually the command, that is used to instantiated Tasks.
     * @param task String , usually the description of the Task, that
     *             is used to identify the task in the task list.
     */
    public ParsedCommand(String command, String task) {
        super(command, task);
    }

    /**
     * Constructor for ParsedCommand for commands that  need a String and LocalDate to be instantiated.
     *
     * @param command String ,usually the command, that is used to instantiated Tasks.
     * @param dueDate LocalDate, usually the dateline of the task, that
     *             is used to identify the task in the task list.
     */
    /*public ParsedCommand(String command, String task, LocalDate dueDate) {
        super(command, task, dueDate, null);
    }*/


    public ParsedCommand(String command, String task, LocalDate dueDate, RecurPeriod recurPeriod) {
        super(command, task, dueDate, recurPeriod);
    }

}
