package duke.command;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import duke.exception.DukeException;
import duke.function.Storage;
import duke.function.TaskList;
import duke.function.Ui;
import duke.task.DeadlineTask;
import duke.task.EventTask;
import duke.task.Task;
import duke.task.ToDoTask;

/**
 * Represents a command to add a new task.
 */
public class AddCommand extends Command {
    /**
     * Type of task. Can be 'todo', 'deadline', or 'event
     */
    private String taskType;
    /**
     * The description and name of the task.
     */
    private String taskName;
    /**
     * The preposition for the date time. Only applicable for deadlines and events.
     */
    private String taskPreposition;
    /**
     * The date time for the event. Only applicable for deadlines and events.
     */
    private LocalDateTime taskDate;
    /**
     * To store any exceptions that were thrown during the parsing of the command.
     */
    private DukeException exception;

    /**
     * Initializes the add command from user input
     *
     * @param fullCommand The user input
     */
    public AddCommand(String fullCommand) {
        super(fullCommand);

        StringTokenizer st = new StringTokenizer(fullCommand);
        String command = st.nextToken();

        //Reading in the name and preposition
        List<String> nameArray = new ArrayList<>();
        String preposition = "";
        while (st.hasMoreTokens()) {
            String nextToken = st.nextToken();
            if (command.equals("todo") || nextToken.charAt(0) != '/') {
                nameArray.add(nextToken);
            } else {
                preposition = nextToken.substring(1);
                break;
            }
        }
        String name = String.join(" ", nameArray);

        //Error handling for no name
        if (name.equals("")) {
            this.exception = new DukeException("Please add a description of your todo as it cannot be empty!");
            return;
        }
        //Setting up datetime
        String date = "";
        String time = "";
        LocalDateTime dateTime = LocalDateTime.now();
        if (st.hasMoreTokens()) {
            date = st.nextToken();
        }
        if (st.hasMoreTokens()) {
            time = st.nextToken();
        }
        if ((date.equals("") || time.equals(""))) {
            if (command.equals("deadline")) {
                this.exception = new DukeException("Please add a date and time for your deadline in the following"
                        + "format:\n'deadline <name> /by <YYYY-MM-DD> <HH:MM>' ! *quack*");
                return;
            } else if (command.equals("event")) {
                this.exception = new DukeException("Please add a date and time for your event in the following"
                        + "format:\n" + "'event <name> /on <YYYY-MM-DD> <HH:MM>' ! *quack*");
                return;
            }
        }
        //Converting datetime
        if (command.equals("deadline") || command.equals("event")) {
            try {
                dateTime = LocalDateTime.parse(date + "T" + time);
            } catch (DateTimeParseException e) {
                if (command.equals("deadline")) {
                    this.exception = new DukeException("Please add a date and time for your deadline in the following"
                            + "format:\n'deadline <name> /by <YYYY-MM-DD> <HH:MM>' ! *quack*");
                    return;
                } else if (command.equals("event")) {
                    this.exception = new DukeException("Please add a date and time for your event in the following"
                            + "format:\n'event <name> /on <YYYY-MM-DD> <HH:MM>' ! *quack*");
                    return;
                }
            }
        }
        this.taskType = command;
        this.taskName = name;
        this.taskPreposition = preposition;
        this.taskDate = dateTime;
    }

    /**
     * Executes the add command and adds the specified task and its details to Duke.
     * If any exceptions were thrown during the parsing of user input and the initializiation of the command,
     * they will be thrown here and the command will be terminated.
     *
     * @param tasks   The current tasks for the command to interact with.
     * @param ui      The ui for the command to print output.
     * @param storage The storage for the command to save and load tasks to an external file.
     * @throws DukeException
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (this.exception != null) {
            throw this.exception;
        }

        //Creating the new task
        Task newTask;
        if (this.taskType.equals("todo")) {
            newTask = new ToDoTask(this.taskName);
        } else if (this.taskType.equals("deadline")) {
            newTask = new DeadlineTask(this.taskName, this.taskPreposition, this.taskDate);
        } else if (this.taskType.equals("event")) {
            newTask = new EventTask(this.taskName, this.taskPreposition, this.taskDate);
        } else {
            throw new DukeException("Invalid command type");
        }

        //Adding the task
        tasks.add(newTask);

        //Saving the task to the save file
        storage.save(tasks);

        //Output to update user
        String output = "";
        output += ui.printReturn("Got it. I've added this task");
        output += ui.printReturn(String.format("  %s", newTask.toString()));
        output += ui.printReturn(String.format(
                "Now you have %d task%s in the list.",
                tasks.getSize(), tasks.getSize() == 1 ? "" : "s"));
        return output;
    }

    /**
     * Returns false as this is not an exit command.
     *
     * @return
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
