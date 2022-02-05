package duke.storage;

import duke.task.Deadlines;
import duke.task.Events;
import duke.task.Tasks;
import duke.task.Todos;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class StorageStub extends Storage {

    public StorageStub(String databasePath) {
        super("./java/duke/data/DukeDatabaseStub.txt");
    }

    public StorageStub() {
        super("./java/duke/data/PrintDatabaseStub.txt");
    }

    public ArrayList<Tasks> preloadTaskList() {
        ArrayList<Tasks> taskList = new ArrayList<Tasks>();
        taskList.add(new Todos("Todo over"));
        taskList.add(new Events("Events over", "2012-03-03"));
        taskList.add(new Deadlines("Deadlines over", "2012-03-03"));
        taskList.add(new Todos("Testing"));
        return taskList;
    }

    @Override
    // Erase and rewrite to file method
    public boolean writesToDatabase(String textToAdd) throws FileNotFoundException {
        try {
            FileWriter fw = new FileWriter(this.databasePath);
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
            FileWriter fw = new FileWriter(databasePath, true); // Append instead of rewriting over
            fw.write(textToAppend);
            fw.close();
            return true;
        } catch (IOException err) {
            System.out.println("    An unexpected error appending to the database file.");
        }
        return false;
    }
}
