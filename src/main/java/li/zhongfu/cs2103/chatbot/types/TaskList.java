package li.zhongfu.cs2103.chatbot.types;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import li.zhongfu.cs2103.chatbot.exceptions.StorageException;
import li.zhongfu.cs2103.chatbot.types.tasks.Task;

/**
 * A task list for storing and handling a collection of Tasks.
 */
public class TaskList {
    private static Logger logger = Logger.getLogger(TaskList.class.getName());

    private List<Task> list;

    private TaskList(List<Task> list) {
        this.list = list;
    }

    /**
     * Creates a new blank TaskList.
     */
    public TaskList() {
        this(new ArrayList<>());
    }

    /**
     * Adds a task to this task list.
     * 
     * @param task the task to be added
     */
    public void add(Task task) {
        if (task == null) {
            throw new NullPointerException("Expected Task, got null");
        }
        this.list.add(task);
    }

    /**
     * Returns the task at the given index.
     * 
     * @param idx the index of the task to be retrieved
     * @return the task at the given index
     */
    public Task get(int idx) {
        return this.list.get(idx);
    }

    /**
     * Removes the task at the given index, and returns it.
     * 
     * @param idx the index of the task to be removed
     * @return the removed task
     */
    public Task remove(int idx) {
        return this.list.remove(idx);
    }

    /**
     * Returns a boolean indicating whether this list is empty.
     * 
     * @return true if the list is empty, false otherwise
     */
    public boolean isEmpty() {
        return this.list.isEmpty();
    }

    /**
     * Returns the number of tasks in this list.
     * 
     * @return the number of tasks in this list
     */
    public int size() {
        return this.list.size();
    }

    /**
     * Saves all tasks in this list to the given Storage.
     * 
     * @param storage the Storage to be used to save tasks
     * @throws IOException if there was an error while saving tasks
     */
    public void save(Storage storage) throws IOException {
        storage.save(this.list);
    }

    /**
     * Returns a list of strings representing the tasks in this TaskList as
     * an (1-indexed) ordered list.
     *
     * Each string in the list is of the form {@code n. foo}, where {@code n} is the
     * index of the item (starting from 1)
     * and {@code foo} is the string representation of the item.
     *
     * @return a list of strings representing the tasks in this TaskList as an 1-indexed ordered list
     */
    public List<String> toEnumeratedList() {
        List<String> enumerated = new ArrayList<>();
        int idx = 0;
        for (Task item : this.list) {
            enumerated.add(String.format("%d. %s", ++idx, item));
        }
        return enumerated;
    }

    /**
     * Loads tasks from the given Storage into a List of tasks.
     * 
     * @param storage the Storage to load tasks with
     * @return a List of Tasks
     * @throws FileNotFoundException if the file configured in the Storage instance doesn't exist
     * @throws StorageException if there was any other error while loading Tasks from the given Storage
     */
    private static List<Task> load(Storage storage) throws FileNotFoundException, StorageException {
        List<Task> tasks = new ArrayList<>();
        try {
            List<Object> objs = storage.load();
            for (Object o : objs) {
                if (o instanceof Task) {
                    tasks.add((Task) o);
                } else {
                    // is this a good idea? but it catches null too
                    throw new ClassCastException(String.format(
                            "Expected Task, got %s",
                            o instanceof Object ? o.getClass().getName() : "null"));
                }
            }
        } catch (ClassCastException e) {
            throw new StorageException("Got invalid type from Storage!", e);
        }
        return tasks;
    }

    /**
     * Loads tasks from the given Storage into a TaskList.
     * 
     * @param storage the Storage to load tasks with
     * @return a TaskList containing the tasks loaded from the given Storage
     * @throws FileNotFoundException if the file configured in the Storage instance doesn't exist
     * @throws StorageException if there was any other error while loading Tasks from the given Storage
     */
    public static TaskList loadFromStorage(Storage storage) throws FileNotFoundException, StorageException {
        return new TaskList(TaskList.load(storage));
    }
}
