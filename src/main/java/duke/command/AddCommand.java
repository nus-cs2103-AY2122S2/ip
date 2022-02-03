package duke.command;

import duke.io.Storage;
import duke.task.TaskList;
import duke.Ui;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Represents a command to add a new task (ToDo, Deadline, Event) to the Duke application.
 *
 * @author Zheng Teck
 * @version 1.0
 */
public class AddCommand extends Command {

    CommandType commandType;
    String description;
    LocalDate date;
    LocalTime time;

    /**
     * Constructor to create a ToDo Command.
     *
     * @param commandType Type of this Command.
     * @param description Description of task stored.
     */
    public AddCommand(CommandType commandType, String description) {
        this.commandType = commandType;
        this.description = description;
    }

    /**
     * Constructor to create a Deadline or Event Command.
     *
     * @param commandType Type of this Command.
     * @param description Description of task stored.
     * @param date        The due date or occurring time of the task in the description.
     * @param time        The corresponding time of the due date.
     */
    public AddCommand(CommandType commandType, String description, LocalDate date, LocalTime time) {
        this.commandType = commandType;
        this.description = description;
        this.date = date;
        this.time = time;
    }

    /**
     * Execute the command stored in this instance to add a task to list.
     *
     * @param taskList The list of task in the Duke application.
     * @param storage  Storage of task in local persistent disk.
     * @exception IOException
     * @see IOException
     */
    public void execute(TaskList taskList, Storage storage) throws IOException {
        int taskId = -1;
        switch (commandType) {
        case TODO:
            taskId = taskList.addToDo(description);
            break;
        case DEADLINE:
            taskId = taskList.addDeadline(description, date, time);
            break;
        case EVENT:
            taskId = taskList.addEvent(description, date, time);
            break;
        default:
            break;
        }
        Ui.print(Ui.addTaskMsg(taskList.getTask(taskId).toString(), taskId + 1));
        storage.writeToFile(taskList);
    }

    /**
     * Generate the usage guide for this command.
     *
     * @return Returns the formatted String value for printing for the usage guide.
     */
    public static String usage() {
        return """
                There are three types of task. They are as follows
                1. ToDo
                   Todo represents simple tasks with only the description field.
                   Usage: todo <description> | i.e. eat lunch with mum
                 2. Event
                   Event represents tasks happening at a certain time with the description field.
                   Usage: event <description> /at <DD-MM-YYYY HH:MM> | i.e. event project meeting /at 30-05-2023 13:59
                 3. Deadline
                   Deadline represents tasks that are due by certain date time with the description field.
                   Usage: deadline <description> /by <DD-MM-YYYY HH:MM> | i.e. deadline do homework /by 30-05-2023 13:59

                """;
    }
}
