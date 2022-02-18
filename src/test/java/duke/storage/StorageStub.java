package duke.storage;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import duke.task.Deadlines;
import duke.task.Events;
import duke.task.Tasks;
import duke.task.Todos;

public class StorageStub extends Storage {

    public StorageStub() {
        super();
    }

    public ArrayList<Tasks> preloadTaskList() {
        ArrayList<Tasks> taskList = new ArrayList<>();
        taskList.add(new Todos("Todo over"));
        taskList.add(new Events("Events over", "2012-03-03"));
        taskList.add(new Deadlines("Deadlines over", "2012-03-03"));
        taskList.add(new Todos("Testing"));
        return taskList;
    }
}
