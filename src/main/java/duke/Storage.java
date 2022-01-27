package main.java.duke;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {

    private String filePath;

    /**
     * Constructs an instance of Storage.
     *
     * @param filePath Path where storage is located or to be created.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads stored tasks from previous execution of Duke.
     *
     * @return An arraylist storing tasks from previous execution of Duke.
     * @throws IOException If file cannot be read.
     * @throws ArrayIndexOutOfBoundsException If file is in wrong format.
     */
    public ArrayList<Task> load() throws IOException {
        try {
            File directory = new File("data");
            if (directory.mkdirs()) {
                System.out.println("Created new directory /data");
            }
            File taskTextFile = new File("data/task.txt");
            if (taskTextFile.createNewFile()) {
                System.out.println("Created text file as /data/task.txt");
            }
            Scanner s = new Scanner(Paths.get("data/task.txt")); // create a Scanner using the File as the source

            ArrayList<Task> tasks = new ArrayList<Task>();

            while (s.hasNext()) {
                String words[] = s.nextLine().split(",");
                String done = words[1];
                String description = words[2];
                switch (words[0]) {
                case ("T"):
                    tasks.add(new ToDo(description));
                    break;
                case ("D"):
                    tasks.add(new Deadline(description, words[3]));
                    break;
                case ("E"):
                    tasks.add(new Event(description, words[3]));
                    break;
                } if (done.equals("true")) {
                    tasks.get(tasks.size() - 1).markDone();
                }
            }
            return tasks;
        } catch (IOException e) {
            throw e;
        } catch (ArrayIndexOutOfBoundsException e) {
            throw e;
        }
    }

    /**
     * Save the tasks in the hard disk automatically whenever the task list changes.
     *
     * @params taskArr Current tasks to be written to file.
     * @throws IOException If file cannot be written to.
     */
    public void writeToFile(ArrayList<Task> taskArr) throws IOException {
        FileWriter fw = new FileWriter("data/task.txt");

        for (Task i : taskArr) {
            fw.write(i.toFileFormat());
            fw.write("\n");
        }
        fw.close();
    }

}
