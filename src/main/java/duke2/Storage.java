package duke2;// package duke1;

import java.io.FileWriter;
import java.io.IOException;
import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.ArrayList;

public class Storage {
    private static String path;

//    /**
//     * Constructor for storage
//     *
//     * @param path of storage file
//     */
//    Storage(String path) {
//        this.path = path;
//    }

    /**
     * Write text to file in path
     * 
     * @param text
     * @throws IOException
     */
    public void writeToFile(String text) throws IOException {
        FileWriter fw = new FileWriter(this.path, true);
        fw.write(text);
        fw.write(System.getProperty("line.separator"));
        fw.close();

    }

    /**
     * Loads data from storage to current tasklist
     * 
     * @return ArrayList of the tasks in the storage db
     * @throws FileNotFoundException
     */
    public static void load() throws FileNotFoundException {
        File f = new File(getPath()); // create a File for the given file path
        Scanner s = new Scanner(f); // create a Scanner using the File as the source
        ArrayList<Task> tasks = new ArrayList<>();
        while (s.hasNext()) {
            Parser p = new Parser();
            Task t = p.parseStringToTask(s.nextLine());
            tasks.add(t);
        }
        Duke.tasks = new Tasklist(tasks);
//        return tasks;
    }

    /**
     * Restores the contents of file from local directory
     *
     * @throws IOException If path is not valid
     */
    public static void createFiles() throws IOException {
        String newFilePath = getPath();
        try {
            Files.createFile(Paths.get(newFilePath));
        } catch (FileAlreadyExistsException e) {
            load();
        }
    }

    /**
     * Get path of storage
     * 
     * @return string ot the storage path
     */
    public static String getPath() {
        String parent = Paths.get("Duke.java").toAbsolutePath().getParent().toString();
        Storage.path = parent + "/duke.txt";
        System.out.println(path);
        return parent + "/duke.txt";
    }

}
