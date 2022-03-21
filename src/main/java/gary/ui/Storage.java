package gary.ui;

import gary.task.Task;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileOutputStream;

/**
 * Represents a storage for files used by Gary
 * Access history of chat-bot to provide
 * user with pre-recorded/incomplete to-do list
 */
public class Storage {
    private static String file;

    /**
     * Constructs a new Storage object
     *
     * @param path path to file
     */
    public Storage(String path) {
        file = path;
    }

    /**
     * Access and loads the content of the saved file
     *
     * @return content of file
     */
    public ArrayList<String> loadFile() {
        ArrayList<String> content = new ArrayList<>();
        try {
            File f = new File(file);
            Scanner sc = new Scanner(f);
            while (sc.hasNext()) {
                content.add(sc.nextLine());
            }
        } catch (FileNotFoundException e) {
            System.out.println("New file created:");
            File newFile = new File("./gary.txt");
        }
        return content;
    }

    /**
     * Saves updated TaskList in the File
     *
     * @param list TaskList to be saved
     */
    public static void saveTask(ArrayList<Task> list) {
        try {
            if (list.isEmpty()) {
                FileWriter fw = new FileWriter(file);
                fw.write(" ");
                fw.close();
            } else {
                FileWriter fw = new FileWriter(file);
                fw.write("1. " + list.get(0).toString() + System.lineSeparator()); // overwrite first line
                fw.close();
                FileWriter fw2 = new FileWriter(file, true);
                int len = list.size();
                for (int i = 1; i < len; i++) {
                    fw2.write(i + 1 + ". " + list.get(i).toString() + System.lineSeparator()); // append
                }
                fw2.close();
            }
        } catch (IOException e) {
            System.out.println("No file to save into???");
        }
    }
}