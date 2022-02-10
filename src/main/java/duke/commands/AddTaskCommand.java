package duke.commands;

import java.time.format.DateTimeParseException;

import duke.exceptions.DateException;
import duke.exceptions.DukeException;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.TaskManager;
import duke.tasks.ToDo;
import duke.ui.UiManager;


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
    public AddTaskCommand(UiManager um, TaskManager tm, String task, Type t) throws DateException, DukeException {
        this.uiManager = um;
        this.taskManager = tm;
        this.type = t;
        switch(type) {
        case TODO:
            this.description = task;
            break;
        case DEADLINE:
            String[] deadlineInputs = task.split("/by", 2);
            assert deadlineInputs.length > 0 : "Invalid task";
            if (deadlineInputs.length == 1) {
                throw new DateException("deadline");
            }
            this.description = deadlineInputs[0];
            this.date = deadlineInputs[1];
            break;
        case EVENT:
            String[] eventInputs = task.split("/at", 2);
            assert eventInputs.length > 0 : "Invalid task";
            if (eventInputs.length == 1) {
                throw new DateException("event");
            }
            this.description = eventInputs[0];
            this.date = eventInputs[1];
            break;
        default:
            throw new DukeException();
        }
    }

    /**
     * Inserts Task Object into TaskManager Object's task list.
     *
     * @return a String containing the Task Object
     * @throws DateTimeParseException if an invalid String is passed into date
     * @throws DukeException if an invalid command is given
     */
    public String execute() throws DateTimeParseException, DukeException {
        switch (type) {
        case TODO:
            Task newToDo = new ToDo(this.description);
            return taskManager.addTask(newToDo);
        case DEADLINE:
            Task newDeadline = new Deadline(this.description, this.date);
            return taskManager.addTask(newDeadline);
        case EVENT:
            Task newEvent = new Event(this.description, this.date);
            return taskManager.addTask(newEvent);
        default:
            throw new DukeException();
        }
    }
}
