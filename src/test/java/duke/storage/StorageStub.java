package duke.storage;

import java.util.ArrayList;

import duke.task.Deadlines;
import duke.task.Events;
import duke.task.Tasks;
import duke.task.Todos;

/**
 * A stub representing the Storage class. This is solely used for testing.
 */
public class StorageStub extends Storage {

    /**
     * The sole constructor of a StorageStub.
     */
    public StorageStub() {
        super();
    }

    /**
     * Preload a collection with tasks to test the program by.
     *
     * @return A collection of preloaded tasks.
     */
    public ArrayList<Tasks> preloadTaskList() {
        ArrayList<Tasks> taskList = new ArrayList<>();
        taskList.add(new Todos("Todo over"));
        taskList.add(new Events("Events over", "2012-03-03"));
        taskList.add(new Deadlines("Deadlines over", "2012-03-03"));
        taskList.add(new Todos("Testing"));
        return taskList;
    }
}
