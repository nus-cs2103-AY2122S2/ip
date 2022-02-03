package component;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import tasks.DeadLines;
import tasks.Events;
import tasks.Tasks;
import tasks.ToDos;

/**
 * A class that belongs to the DukeComponent Package.
 * This class deals with loading tasks from a pre-constructed file and saving tasks to the file
 * from the Duke program.
 */
public class Storage {
    private final String pathName;

    /**
     * Constructor for Storage.
     * @param pathName Relative path to the file.
     */
    public Storage(String pathName) {
        this.pathName = pathName;
    }

    /**
     * Loads the content of the file to a TaskList so that it could be passed to Duke.
     * @return TaskList that is updated with contents from the previous Duke instances.
     */
    public ArrayList<Tasks> load() {
        ArrayList<Tasks> ls = new ArrayList<Tasks>();
        File data = new File(pathName);
        try {
            Scanner s = new Scanner(data);
            while (s.hasNext()) {
                String str = s.nextLine();
                String[] arr = str.split("|");
                String type = arr[0];
                boolean marked = arr[2].equals("1");
                String remainingStr = str.substring(str.lastIndexOf("|") + 1);
                switch (type) {
                case "D":
                    ls.add(new DeadLines(
                            str.substring(4, str.lastIndexOf("|")),
                            marked, remainingStr));
                    break;
                case "E":
                    ls.add(new Events(str.substring(4, str.lastIndexOf("|")),
                            marked, remainingStr));
                    break;
                default: //case "T":
                    ls.add(new ToDos(remainingStr, marked));
                    break;
                }
            }
            s.close();
        } catch (FileNotFoundException e) {
            System.out.println("There is no cache, "
                    + "duke will be initialised as per normal.");
        }
        return ls;
    }

    /**
     * Writes the Tasks into the file for storage.
     * @param arr TaskList containing all the Tasks required for storage.
     */
    public void write(TaskList arr) {
        try {
            File f = new File(pathName);
            FileWriter fw = new FileWriter(f);
            for (Tasks t : arr) {
                fw.write(t.cacheString() + System.lineSeparator());
            }
            fw.close();
        } catch (IOException e) {
            System.out.println("Something went wrong " + e.getMessage());
        }
    }
}
