package lily;

import lily.task.Task;
import lily.task.Todo;
import lily.task.Deadline;
import lily.task.Event;
import lily.task.Job;

import java.util.LinkedList;

/**
 * Contains the list of Tasks and all the operations on them.
 * Can list, mark and unmark, add, and remove tasks.
 * 
 * @author Hong Yi En, Ian
 * @version Jan 2022 (AY21/22 Sem 2)
 */
public class TaskList {
    private static LinkedList<Task> list;

    /**
     * Creates a new empty TaskList.
     */
    public TaskList() {
        list = new LinkedList<>();
    }

    /**
     * Returns true if this list contains no elements.
     * 
     * @return True if this list contains no elements.
     */
    public boolean isEmpty() {
        return list.isEmpty();
    }

    /**
     * Returns the number of elements in this list.
     * 
     * @return the number of elements in this list.
     */
    public int getSize() {
        return list.size();
    }

    /**
     * Returns the contents of the list.
     * 
     * @return The TaskList.
     */
    public LinkedList<Task> getContents() {
        return list;
    }

    /**
     * Creates a new TaskList from the data in the savefile.
     * @param loadedData The TaskList from the savefile.
     * @throws LilyException If there isn't a file or data to load.
     */
    public TaskList(LinkedList<Task> loadedData) {
        list = loadedData;
    }

    /**
     * Types of operations the user can perform on Tasks.
     */
    private enum TaskOperation {
        MARK, UNMARK, REMOVE
    }

    /**
     * Marks the task and returns it.
     * 
     * @param idx The index of the task to be marked.
     * @return The task after it has been marked.
     * @throws LilyException If user hasn't done the task yet OR index exceeds list length.
     */
    public Task mark(int idx) throws LilyException {
        return operate(idx, TaskOperation.MARK);
    }

    /**
     * Unmarks the task and returns it.
     * 
     * @param idx The index of the task to be unmarked.
     * @return The task after it has been unmarked.
     * @throws LilyException If user hasn't done the task yet OR index exceeds list length.
     */
    public Task unmark(int idx) throws LilyException {
        return operate(idx, TaskOperation.UNMARK);
    }

    /**
     * Removes a Task from the TaskList.
     * 
     * @param idx The index of which was removed.
     * @return The Task that was removed.
     * @throws LilyException When the index is not in the List.
     */
    public Task remove(int idx) throws LilyException {
        return operate(idx, TaskOperation.REMOVE);
    }

    /**
     * Conducts any operation on any type of task.
     * 
     * @param idx of the item in the taskList
     * @param op operation which should be done on the item
     * @return the task after it has been operated upon
     * @throws LilyException When the index is not in the list or the task cannot be operated on.
     */
    private Task operate(int idx, TaskOperation op) throws LilyException {
        boolean isOutOfBounds = idx < 0 || idx >= list.size();
        if (isOutOfBounds) {
            throw new LilyException(LilyException.ERROR_OUT_OF_BOUNDS);
        }

        Task result;
        switch(op) {
        case MARK:
            result = list.get(idx).mark();
            break;

        case UNMARK:
            result = list.get(idx).unmark();
            break;

        case REMOVE:
            result = list.remove(idx);
            break;

        default:
            throw new LilyException(LilyException.ERROR_UNKNOWN_COMMAND);
        }
        return result;
    }

    /**
     * Types of tasks the user can add.
     */
    private enum TaskType {
        TODO, DEADLINE, EVENT, JOB
    }

    /**
     * Adds a todo to the TaskList.
     * 
     * @param desc What the todo is about.
     * @return The todo that was added.
     */
    public Task addTodo(String desc) throws LilyException {
        return addTask(TaskType.TODO, desc, "unused string");
    }

    /**
     * Adds a deadline to the TaskList.
     * 
     * @param desc What the deadline is about.
     * @param by When the deadline is due.
     * @return The deadline that was added.
     * @throws LilyException If the date was not recognizable.
     */
    public Task addDeadline(String desc, String by) throws LilyException {
        return addTask(TaskType.DEADLINE, desc, by);
    }

    /**
     * Adds a Event to the TaskList.
     * 
     * @param desc What the Event is about.
     * @param at When the Event is due.
     * @return The Event that was added.
     * @throws LilyException If the date was not recognizable.
     */
    public Task addEvent(String desc, String at) throws LilyException {
        return addTask(TaskType.EVENT, desc, at);
    }

    /**
     * Adds a Job to the TaskList.
     * 
     * @param desc What the Job is about.
     * @param dur How long the Job will take
     * @return The Job that was added.
     * @throws LilyException If the duration was not recognizable.
     */
    public Task addJob(String desc, String dur) throws LilyException {
        return addTask(TaskType.JOB, desc, dur);
    }

    /**
     * Adds any type of task to the taskList.
     * 
     * @param t The type of task being added.
     * @param desc of the task.
     * @param date of the task happening.
     * @return the task being added.
     * @throws LilyException If the date was not recognizable.
     */
    private Task addTask(TaskType t, String desc, String date) throws LilyException {
        Task task;
        switch(t) {
        case TODO:
            task = new Todo(desc);
            break;

        case DEADLINE:
            task = new Deadline(desc, date);
            break;

        case EVENT:
            task = new Event(desc, date);
            break;

        case JOB:
            task = new Job(desc, date);
            break;

        default:
            throw new LilyException(LilyException.ERROR_UNKNOWN_TASK_TYPE);
        }

        list.add(task);
        return task;
    }
}