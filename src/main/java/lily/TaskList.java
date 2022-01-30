package lily;

import lily.task.LilyException;

import lily.task.Task;
import lily.task.Todo;
import lily.task.Deadline;
import lily.task.Event;

import java.time.format.DateTimeParseException;
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
     * Creates a new TaskList.
     */
    public TaskList() {
        list = new LinkedList<>();
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
    public int size() {
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
     * Returns the tasks in the form of a String for printing.
     * 
     * @return The tasks as Strings to be printed.
     */
    public String getTasks() {
        String listMsg = "";
        int i = 1;
        for (Task t : list) {
            listMsg += "    " + i + "."
                    + t.toString()
                    + (i == list.size() ? "" : System.lineSeparator());
            i++;
        }
        return listMsg;
    }


    /**
     * Marks the task and returns it.
     * 
     * @param idx The index of the task to be marked.
     * @return The task after it has been marked.
     * @throws IndexOutOfBoundsException If the index exceeds the list.
     * @throws LilyException If user has finished the task already.
     */
    public Task mark(int idx) throws IndexOutOfBoundsException, 
            LilyException {
                    /*
                     * if input doesn't have an int, ask which number you want to mark .
                     */
        Task t = list.get(idx);
        t.mark();
        return t;
    }

    /**
     * Unmarks the task and returns it.
     * 
     * @param idx The index of the task to be unmarked.
     * @return The task after it has been unmarked.
     * @throws IndexOutOfBoundsException If the index exceeds the list.
     * @throws LilyException If user hasn't done the task yet.
     */
    public Task unmark(int idx) throws IndexOutOfBoundsException, 
            LilyException {
        Task t = list.get(idx);
        t.unmark();
        return t;
    }

    /**
     * Adds a todo to the TaskList.
     * 
     * @param desc What the todo is about.
     * @return The todo that was added.
     */
    public Task addTodo(String desc) {
        Todo t = new Todo(desc);
        list.add(t);
        return t;
    }

    /**
     * Adds a deadline to the TaskList.
     * 
     * @param desc What the deadline is about.
     * @param by When the deadline is due.
     * @return The deadline that was added.
     * @throws DateTimeParseException If the date was not recognizable.
     */
    public Task addDeadline(String desc, String by) throws DateTimeParseException {
        Deadline d = new Deadline(desc, by);
        list.add(d);
        return d;
    }

    /**
     * Adds a Event to the TaskList.
     * 
     * @param desc What the Event is about.
     * @param at When the Event is due.
     * @return The Event that was added.
     * @throws DateTimeParseException If the date was not recognizable.
     */
    public Task addEvent(String desc, String at) throws DateTimeParseException {
        Event e = new Event(desc, at);
        list.add(e);
        return e;
    }

    /**
     * Removes a Task from the TaskList.
     * 
     * @param idx The index of which was removed.
     * @return The Task that was removed.
     * @throws IndexOutOfBoundsException When the index is not in the List.
     */
    public Task remove(int idx) throws IndexOutOfBoundsException {
        return list.remove(idx);
    }
}