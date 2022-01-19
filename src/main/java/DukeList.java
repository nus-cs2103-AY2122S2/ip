import exceptions.DukeException;
import exceptions.InvalidCommandException;
import exceptions.InvalidTaskParams;
import tasks.Deadline;
import tasks.Event;
import tasks.Task;
import tasks.Todo;

import java.util.ArrayList;

public class DukeList {
    private static final String TODO = "todo";
    private static final String DEADLINE = "deadline";
    private static final String EVENT = "event";

    private static final int CAPACITY = 100;
    private ArrayList<Task> list;

    /**
     * Constructor for DukeList
     */
    public DukeList() {
        this.list = new ArrayList<>(CAPACITY);
    }

    /**
     * Add a new item to the list.
     *
     * @param taskString
     */
    public String add(String taskString) throws DukeException {
        Task task = addTask(taskString);
        return "Got it. I've added this task:\n"
                + String.format("\t%s\n", task)
                + String.format("Now you have %d tasks in the list.", this.list.size());
    }

    private Task addTask(String taskString) throws InvalidCommandException, InvalidTaskParams {
        Task task;
        String[] taskArr = taskString.split(" ", 2);
        String taskType = taskArr[0];
        String[] params;

        if (taskArr.length <= 1) {
            throw new InvalidTaskParams("☹ OOPS!!! The description of a todo cannot be empty.");
        }

        switch (taskType) {
            case TODO:
                task = new Todo(taskArr[1]);
                break;

            case DEADLINE:
                params = taskArr[1].split(" /by ");
                if (params.length <= 1) {
                    throw new InvalidTaskParams("☹ OOPS!!! The deadline of a task cannot be empty.");
                }
                task = new Deadline(params[0], params[1]);
                break;

            case EVENT:
                params = taskArr[1].split(" /at ");
                if (params.length <= 1) {
                    throw new InvalidTaskParams("☹ OOPS!!! The time of an event cannot be empty.");
                }
                task = new Event(params[0], params[1]);
                break;

            default:
                throw new InvalidCommandException();
        }
        this.list.add(task);

        return task;
    }

    /**
     * Mark the task as completed given a task index.
     *
     * @param idx
     * @return
     */
    public String markTask(int idx) throws IndexOutOfBoundsException {
        Task task = this.list.get(idx - 1);
        task.markAsCompleted();

        return "Nice! I've marked this task as done:\n"
                + String.format("\t%s", task);
    }

    /**
     * Mark the task as uncompleted given a task index.
     *
     * @param idx
     * @return
     */
    public String unmarkTask(int idx) throws IndexOutOfBoundsException {
        Task task = this.list.get(idx - 1);
        task.markAsUncompleted();

        return "OK, I've marked this task as not done yet:\n"
                + String.format("\t%s", task);
    }

    @Override
    public String toString() {
        int size = this.list.size();
        StringBuilder result;

        if (size == 0) {
            result = new StringBuilder("List is empty. Add items to the list.");
        } else {
            result = new StringBuilder();
            for (int i=0; i<size; i++) {
                result.append(String.format("%d. %s\n", i + 1, this.list.get(i)));
            }
        }

        return result.toString();
    }
}
