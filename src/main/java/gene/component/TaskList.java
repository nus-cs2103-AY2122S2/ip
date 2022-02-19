package gene.component;

import java.util.ArrayList;

import gene.task.Task;

/**
 * The task list class. handles all of gene's task list actions.
 *
 * @author Eugene Chia
 * @version 1.0
 * @since 2022-01-12
 */
public class TaskList {
    private ArrayList<Task> itemList = new ArrayList<>(0);
    private final TaskStorage geneTaskStorage;

    /**
     * Constructor for taskList
     *
     * @param geneTaskStorage
     */
    public TaskList(TaskStorage geneTaskStorage) {
        this.geneTaskStorage = geneTaskStorage;
    }

    /**
     * read file and initialize arraylist: if dont have existing file, create.
     */
    public void initFile() {
        geneTaskStorage.createDirectory();
        geneTaskStorage.createFile();
        this.itemList = geneTaskStorage.readFile();
    }

    /**
     * wrapper for array list set
     *
     * @param index
     * @param targetTask
     */
    public void set(int index, Task targetTask) {
        assert (index >= 0);
        assert (index < itemList.size());
        this.itemList.set(index, targetTask);
    }

    /**
     * wrapper for array list get
     *
     * @param index
     */
    public Task get(int index) {
        assert (index >= 0);
        assert (index < itemList.size());
        return this.itemList.get(index);
    }

    /**
     * wrapper for array list add
     *
     * @param targetTask
     */
    public void add(Task targetTask) {
        this.itemList.add(targetTask);
    }

    /**
     * wrapper for array list size
     *
     */
    public int size() {
        return this.itemList.size();
    }


    /**
     * wrapper for array list remove
     *
     * @param index
     */
    public void remove(int index) {
        assert (index >= 0);
        assert (index < itemList.size());
        this.itemList.remove(index);
    }
}
