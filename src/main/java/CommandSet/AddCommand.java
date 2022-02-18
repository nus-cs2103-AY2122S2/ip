package commandset;

import exceptions.MissingTimeArgumentException;
import helper.Storage;
import helper.TaskList;
import helper.Ui;
import tasks.Deadline;
import tasks.Event;
import tasks.Task;
import tasks.Todo;



/**
 * <h1>AddCommand</h1>
 * <p>
 * AddCommand class adds tasks to the list of user tasks.
 *</p>
 *
 * @author Saravanan Anuja Harish
 */
public class AddCommand extends Command {

    // stores space.
    private static final String SPACE = " ";

    // starting index of a list or a char in string.
    private static final int TASK_TYPE_INDEX = 0;

    // stores the value of empty task list.
    private static final int EMPTY_TASKLIST = 0;

    // used to differentiate between constructors.
    private static final int DUMMY_VARIABLE = 1;

    // the index of element containing the task description.
    private static final int TASK_DESC_IDX = 1;

    // Stores the split limit.
    private static final int SPLIT_LIMIT = 2;

    // Stores the command TODO.
    private static final String TODO = "TODO";

    // Stores the command DEADLINE.
    private static final String DEADLINE = "DEADLINE";

    // Stores the command EVENT.
    private static final String EVENT = "EVENT";


    /**
     * Constructor for AddCommand.
     * returns an instance of addCommand.
     */
    AddCommand() {
        super();
    }

    /**
     * adds the task to taskList.
     *
     * @param taskList the list of user tasks.
     * @param text the string of the task to be added.
     * @throws MissingTimeArgumentException if the user missed time argument out.
     */
    public static void add(TaskList taskList, String text) {
  
        assert text.contains(SPACE) : "Must contain a space to differentiate task from type of task";
      
        String[] splitText = text.split(SPACE, SPLIT_LIMIT);
        String type = splitText[TASK_TYPE_INDEX].toUpperCase();
        String message = splitText[TASK_DESC_IDX];

        Task task;

        switch (type) {
        case TODO:
            task = new Todo(message);
            break;
        case DEADLINE:
            Deadline.correctArgument(message);
            task = new Deadline(message);
            break;
        case EVENT:
            Event.correctArgument(message);
            task = new Event(message);
            break;
        default:
            task = new Task(text);
            break;
        }


        // checks if there has been a similar task
        TaskList similarTasks = taskList.getTasksContaining(task.getTask());

        if (similarTasks.numOfTasks() != EMPTY_TASKLIST) {
            Ui.printFoundSimilarTask(similarTasks);
        } else {

            // checks for any possible clashes.
            TaskList clashes = taskList.getTasksDueOn(task.getDate());

            // add the task.
            taskList.add(task);

            if (clashes.numOfTasks() == EMPTY_TASKLIST) { // no clashes.
                Ui.printAdded(task.getTask(), taskList.numOfTasks());
            } else {
                Ui.printClashes(clashes);
            }
        }


    }

    /**
     * adds the previous tasks to the list.
     *
     * @param taskList the list of user tasks.
     * @param storage the string of the task to be added.
     */
    public static void add(TaskList taskList, Storage storage) {
        for (String task: storage.loadPreviousTasks()) {
            if (Todo.isTodo(task)) {
                taskList.add(new Todo(task, DUMMY_VARIABLE));
            } else if (Deadline.isDeadline(task)) {
                taskList.add(new Deadline(task, DUMMY_VARIABLE));
            } else if (Event.isEvent(task)) {
                taskList.add(new Event(task, DUMMY_VARIABLE));
            } else {
                taskList.add(new Task(task, DUMMY_VARIABLE));
            }
        }
    }
}
