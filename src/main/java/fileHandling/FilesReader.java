package fileHandling;

import tasks.Task;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class FilesReader {
    public static ArrayList<Task> getTaskListFromFile() {
        ArrayList<Task> taskList = new ArrayList<Task>();
        try {
            File f = new File("data/heylo.txt");
            if (!f.createNewFile()) {
                System.out.println("Loading your tasks list...");
            }
            Scanner s = new Scanner(f);
            while (s.hasNext()) {
                System.out.println(s.nextLine());
            }
            return taskList;
        } catch (IOException ioException) {
            System.out.println("An unknown error occurred.");
            ioException.printStackTrace();
        }
        return taskList;
    }
}