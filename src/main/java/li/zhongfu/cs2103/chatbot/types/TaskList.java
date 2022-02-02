package li.zhongfu.cs2103.chatbot.types;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import li.zhongfu.cs2103.chatbot.exceptions.StorageException;
import li.zhongfu.cs2103.chatbot.types.tasks.Task;

public class TaskList {
    private static Logger logger = Logger.getLogger(TaskList.class.getName());

    private List<Task> list;

    private TaskList(List<Task> list) {
        this.list = list;
    }

    public TaskList() {
        this(new ArrayList<>());
    }

    public void add(Task task) {
        if (task == null) {
            throw new NullPointerException("Expected Task, got null");
        }
        this.list.add(task);
    }

    public Task get(int idx) {
        return this.list.get(idx);
    }

    public Task remove(int idx) {
        return this.list.remove(idx);
    }

    /**
     * Returns a TaskList containing only Tasks with a name that contains the search string.
     *
     * @param query the search string
     * @return a TaskList containing only Tasks with a name that contains the search string
     */
    public TaskList find(String query) {
        return new TaskList(this.list
                .stream()
                .filter(task -> task.getName().contains(query))
                .collect(Collectors.toList()));
    }

    public boolean isEmpty() {
        return this.list.isEmpty();
    }

    public int size() {
        return this.list.size();
    }

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

    private static List<Task> load(Storage storage) throws FileNotFoundException, StorageException {
        List<Task> tasks = new ArrayList<>();
        try {
            List<Object> objs = storage.load();
            for (Object o : objs) {
                if (o instanceof Task) {
                    tasks.add((Task) o);
                } else {
                    // is this a good idea? but it catches null too
                    throw new ClassCastException(String.format("Expected Task, got %s", o instanceof Object ? o.getClass().getName() : "null"));
                }
            }
        } catch (ClassCastException e) {
            throw new StorageException("Got invalid type from Storage!", e);
        }
        return tasks;
    }
    
    public static TaskList loadFromStorage(Storage storage) throws FileNotFoundException, StorageException {
        return new TaskList(TaskList.load(storage));
    }
}
