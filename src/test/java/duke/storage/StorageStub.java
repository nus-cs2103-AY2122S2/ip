package duke.storage;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import duke.task.Deadlines;
import duke.task.Events;
import duke.task.Tasks;
import duke.task.Todos;

public class StorageStub extends Storage {

    /**
     *
     * @param databasePath
     */
    public StorageStub(String databasePath) {
        super("C:/Users/benny/Desktop/Y2S2/CS2103T_Software_Engineer/"
                + "Individual_Project/src/test/java/duke/data/DukeDatabaseStub.txt");
    }

    /**
     *
     */
    public StorageStub() {
        super("C:/Users/benny/Desktop/Y2S2/CS2103T_Software_Engineer/"
                + "Individual_Project/src/test/java/duke/data/DukeDatabaseStub.txt");
    }

    /**
     *
     * @return
     */
    public ArrayList<Tasks> preloadTaskList() {
        ArrayList<Tasks> taskList = new ArrayList<>();
        taskList.add(new Todos("Todo over"));
        taskList.add(new Events("Events over", "2012-03-03"));
        taskList.add(new Deadlines("Deadlines over", "2012-03-03"));
        taskList.add(new Todos("Testing"));
        return taskList;
    }

    @Override
    // Erase and rewrite to file method
    public boolean writesToDatabase(String textToAdd) {
        try {
            FileWriter fw = new FileWriter(super.getDatabasePath());
            fw.write(textToAdd);
            fw.close();
            return true;
        } catch (IOException err) {
            System.out.println("    An unexpected error writing to the database.");
        }
        return false;
    }

    @Override
    // Append to file method
    public boolean appendsToDatabase(String textToAppend) throws IOException {
        try {
            FileWriter fw = new FileWriter(super.getDatabasePath(), true); // Append instead of rewriting over
            fw.write(textToAppend);
            fw.close();
            return true;
        } catch (IOException err) {
            System.out.println("    An unexpected error appending to the database file.");
        }
        return false;
    }
}
