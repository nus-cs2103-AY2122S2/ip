package gene.component;

import gene.task.Task;

import java.util.ArrayList;

/**
 * The task list class. handles all of gene's task list actions.
 *
 * @author Eugene Chia
 * @version 1.0
 * @since 2022-01-12
 */
public class TaskList {
    private ArrayList<Task> itemList = new ArrayList<>(0);
    private final Storage geneStorage;

    /**
     * Constructor for taskList
     *
     * @param geneStorage
     */
    public TaskList(Storage geneStorage) {
        this.geneStorage = geneStorage;
    }

    /**
     * read file and initialize arraylist: if dont have existing file, create.
     */
    public void initFile() {
        geneStorage.createDirectory();
        geneStorage.createFile();
        this.itemList = geneStorage.readFile();
    }

    /**
     * wrapper for array list set
     *
     * @param index
     * @param targetTask
     */
    public void set(int index, Task targetTask) {
        this.itemList.set(index, targetTask);
    }

    /**
     * wrapper for array list get
     *
     * @param index
     */
    public Task get(int index) {
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
        this.itemList.remove(index);
    }
}
