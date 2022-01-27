package duke.commands;

import duke.tasks.TaskManager;
import duke.exceptions.DateException;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.ToDo;
import duke.ui.UiManager;

import java.time.format.DateTimeParseException;

/**
 * AddTaskCommand Object that adds Task Objects to task list
 * in TaskManager Object.
 *
 */
public class AddTaskCommand extends Command {
    private String description;
    private String date;
    private TaskManager taskManager;
    private UiManager uiManager;
    private Type type;

    /**
     * Constructs AddTaskCommand Object.
     *
     * @param um UiManager Object to handle input and output processes
     * @param tm TaskManager Object to receive Task Objects
     * @param task Task Object to be added
     * @param t Type of Task Object
     * @throws DateException if an invalid String is passed
     */
    public AddTaskCommand(UiManager um, TaskManager tm, String task, Type t) throws DateException {
        this.uiManager = um;
        this.taskManager = tm;
        this.type = t;
        switch(type) {
        case TODO:
            this.description = task;
            break;
        case DEADLINE:
            String[] dInput = task.split("/by", 2);
            if (dInput.length == 1) {
                throw new DateException("deadline");
            }
            this.description = dInput[0];
            this.date = dInput[1];
            break;
        case EVENT:
            String[] eInput = task.split("/at", 2);
            if (eInput.length == 1) {
                throw new DateException("event");
            }
            this.description = eInput[0];
            this.date = eInput[1];
            break;
        }
    }

    /**
     * Inserts Task Object into TaskManager Object's task list.
     *
     * @throws DateTimeParseException if an invalid String is passed for date
     */
    public void execute() throws DateTimeParseException {
        switch (type) {
        case TODO:
            Task newToDo = new ToDo(this.description);
            taskManager.addTask(newToDo);
            break;
        case DEADLINE:
            Task newDeadline = new Deadline(this.description, this.date);
            taskManager.addTask(newDeadline);
            break;
        case EVENT:
            Task newEvent = new Event(this.description, this.date);
            taskManager.addTask(newEvent);
            break;
        }
    }
}
