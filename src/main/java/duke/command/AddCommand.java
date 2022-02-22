package duke.command;

import duke.storage.Storage;
import duke.ui.Ui;
import duke.tasklist.TaskList;
import duke.task.Todo;
import duke.task.Deadline;
import duke.task.Event;
import duke.DukeException;

import java.io.IOException;
import java.time.format.DateTimeParseException;


/**
 * Represents a Command object that will add new tasks.
 */
public class AddCommand extends Command {

    private String taskType;
    private String textInput;

    /**
     * Constructor for the AddCommand object.
     *
     * @param type The type of tasks to be added,
     * (Todo, Deadline, Event).
     * @param input The user's input containing the information
     * of the task to be added.
     */
    public AddCommand(String type, String input) {
        this.textInput = input;
        this.taskType = type;
    }

    /**
     * Returns boolean value to state if the app should exit its run.
     */
    @Override
    public boolean isExit() {
        return false;
    }

    /**
     * Adds the input task to the tasklist.
     *
     * @param stg The storage object to use file writing methods.
     * @param ui The ui object to handle I/O requests.
     * @param tasks The task list which holds all tasks available.
     * @return Message indicating if the task was added successfully.
     * @throws IOException If an I/O error occurs.
     */
    @Override
    public String execute(Storage stg, Ui ui, TaskList tasks) throws IOException {
        String resultMessage = "";
        if (this.taskType.equals("todo")) {
            resultMessage = addTodo(tasks, ui, stg);
        } else if (this.taskType.equals("deadline")) {
            resultMessage = addDeadline(tasks, ui, stg);
        } else {
            assert this.taskType.equals("event") : "Command word must be event";
            resultMessage = addEvent(tasks, ui, stg);
        }
        return resultMessage;
    }

    /**
     * Adds a Todo task to the tasklist.
     *
     * @param tasks The task list which holds all tasks available.
     * @param ui The ui object to handle I/O requests.
     * @param stg The storage object to use file writing methods.
     * @return Message indicating if the Todo was added successfully.
     * @throws IOException If an I/O error occurs.
     */
    public String addTodo(TaskList tasks, Ui ui, Storage stg) {
        try {
            Todo td = Todo.createTodo(this.textInput);
            tasks.addTask(td);
            stg.pushToMemory("add");
            stg.writeToFile(td.formatText() + "\n");
            return ui.showSuccessfulAddMessage() + "\n" + td + "\n"
                    + ui.showNumberOfTasksMessage(tasks);
        } catch (DukeException e) {
            return "Please enter a valid description!";
        } catch (IOException e) {
            return "Oops, an error occurred!";
        }
    }

    /**
     * Adds a Deadline task to the tasklist.
     *
     * @param tasks The task list which holds all tasks available.
     * @param ui The ui object to handle I/O requests.
     * @param stg The storage object to use file writing methods.
     * @return Message indicating if the Deadline was added successfully.
     * @throws IOException If an I/O error occurs.
     */
    public String addDeadline(TaskList tasks, Ui ui, Storage stg) {
        try {
            Deadline dl = Deadline.createDeadline(this.textInput);
            tasks.addTask(dl);
            stg.pushToMemory("add");
            stg.writeToFile(dl.formatText() + "\n");
            return ui.showSuccessfulAddMessage() + "\n" + dl + "\n"
                    + ui.showNumberOfTasksMessage(tasks);
        } catch (DukeException e) {
            return "Please enter a valid description/date!";
        } catch (StringIndexOutOfBoundsException e) {
            return "Please enter a date (eg. /by 2019-12-12)!";
        } catch (DateTimeParseException e) {
            return "Please enter valid date in YYYY-MM-DD format!";
        } catch (IOException e) {
            return "Oops, an error occurred!";
        }
    }

    /**
     * Adds an Event task to the tasklist.
     *
     * @param tasks The task list which holds all tasks available.
     * @param ui The ui object to handle I/O requests.
     * @param stg The storage object to use file writing methods.
     * @return Message indicating if the Event was added successfully.
     * @throws IOException If an I/O error occurs.
     */
    public String addEvent(TaskList tasks, Ui ui, Storage stg) throws IOException {
        try {
            Event ev = Event.createEvent(this.textInput);
            tasks.addTask(ev);
            stg.pushToMemory("add");
            stg.writeToFile(ev.formatText() + "\n");
            return ui.showSuccessfulAddMessage() + "\n" + ev + "\n"
                    + ui.showNumberOfTasksMessage(tasks);
        } catch (DukeException e) {
            return "Please enter a valid description/date!";
        } catch (StringIndexOutOfBoundsException e) {
            return "Please enter a date (eg. /at Monday 3-4pm)!";
        }
    }
}
