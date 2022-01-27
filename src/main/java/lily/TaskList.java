package lily;
import lily.task.Task;
import lily.task.Todo;
import lily.task.Deadline;
import lily.task.Event;

import lily.task.LilyException;

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

    public boolean isEmpty() {
        return list.isEmpty();
    }

    public int size() {
        return list.size();
    }

    public LinkedList<Task> getContents() {
        return list;
    }

    public String printTasks() {
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
     * @param idx The index of the task to be marked.
     * @return The task after it has been marked.
     * @throws IndexOutOfBoundsException
     */
    public Task mark(int idx) throws IndexOutOfBoundsException {
                    /*
                     * if (list.isEmpty())
                     * throw new Error("you cant mark something that isn't there");
                     * else if (already marked)
                     * throw new error you've already finished this
                     * if input doesn't have an int, ask which number you want to mark also.
                     */
        Task t = list.get(idx);
        t.mark();
        return t;
    }

    /**
     * Unmarks the task and returns it.
     * @param idx The index of the task to be unmarked.
     * @return The task after it has been unmarked.
     * @throws IndexOutOfBoundsException
     */
    public Task unmark(int idx) throws IndexOutOfBoundsException {
                    /*
                     * if (list.isEmpty())
                     * throw new Error("you can't unmark something thaj isn't there");
                     * else if (not marked yet)
                     * throw new error you havent done this
                     * if input doesn't have an int, ask which number you want to mark also.
                     */
        Task t = list.get(idx);
        t.unmark();
        return t;
    }

    public Task remove(int idx) throws IndexOutOfBoundsException {
        return list.remove(idx);
    }

    // in ui, print 'task added' else, todo desc cant be empty from lilyexception
    public Task addTodo(String desc) throws LilyException {
        Todo t = new Todo(desc);
        list.add(t);
        return t;
    }

                    /*
                     * if user didn't type "/by" (byIdx == -1)
                     * throw new Error "you didnt' type /by bro, try again"
                     * if user didnt' type a desc
                     * throw new error you didnt type a description man, try again
                     */
    // need to catch "no-/by" error
    // in ui, do 'task added' msg else, todo desc cant be empty from lilyexception
    public Task addDeadline(String desc, String by) throws LilyException {
        Deadline d = new Deadline(desc, by);
        list.add(d);
        return d;
    }

                    /*
                     * if user didn't type "/at" (atIdx == -1)
                     * throw new Error "you didnt' type /at bro, try again"
                     * if user didnt' type a desc
                     * throew new error you didnt type a description man, try again
                     */
    public Task addEvent(String desc, String at) throws LilyException {
        Event e = new Event(desc, at);
        list.add(e);
        return e;
    }
}