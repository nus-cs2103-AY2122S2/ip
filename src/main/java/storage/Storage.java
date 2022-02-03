package storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import task.Tasks;
import task.Deadlines;
import task.Events;
import task.Todos;

import storage.Storage;

public class Storage {
    String databasePath;

    public Storage(String databasePath) {
        this.databasePath = databasePath;
    }

    // Erase and rewrite to file method
    public boolean writesToDatabase(String textToAdd) throws IOException, FileNotFoundException {
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

    ArrayList<Tasks> returnsAllTasks() throws FileNotFoundException {
        ArrayList<Tasks> taskList = new ArrayList<Tasks>();
        try {
            File f = new File(databasePath);
            Scanner sc = new Scanner(f);

            while (sc.hasNextLine()) {
                String taskData = sc.nextLine();
                String[] taskDataSplit = taskData.split(" \\| ");

                switch (taskDataSplit[0]) {
                    case "T":
                        taskList.add(new Todos(taskDataSplit[2],
                                taskDataSplit[1].equals("X") ? true : false));
                        break;

                    case "E":
                        taskList.add(new Events(taskDataSplit[2],
                                taskDataSplit[1].equals("X") ? true : false, taskDataSplit[3]));
                        break;

                    case "D":
                        taskList.add(new Deadlines(taskDataSplit[2],
                                taskDataSplit[1].equals("X") ? true : false,
                                taskDataSplit[3]));
                        break;
                }
            }
            sc.close();
        } catch (FileNotFoundException err) {
            System.out.println("    The database got corrupted by unrecognisable file formats.");
        }
        return taskList;
    }

    public ArrayList<Tasks> load() throws FileNotFoundException {
        ArrayList<Tasks> taskList = new ArrayList<Tasks>();
        try {
            taskList.addAll(returnsAllTasks());
        } catch (FileNotFoundException error) {
            System.out.println("    Database loading failed.");
        }
        return taskList;
    }
}
