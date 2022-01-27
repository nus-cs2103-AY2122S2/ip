import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * Handles creating and editing data file.
 */
public class Storage {
    private File storage;
    private final String FILE_PATH = "data/Tasks.txt";

    public Storage() {
        storage = new File(FILE_PATH);
        new File("data").mkdirs();
    }

    /**
     * Gives the file stored in hard drive.
     * @return The data file
     */
    public File getTasks() {
        return this.storage;
    }

    /**
     * Appends task to the data file
     * @param task The task to be appended
     */
    public void addTasks(Task task) {
        try {
            FileWriter fw = new FileWriter(FILE_PATH, true);
            fw.write(task.toString() + "\n");
            fw.close();
        } catch (IOException ex) {
            System.out.println("Sorry, there seems to be an Issue."
                    + "\n" + "Please restart and try again");
        }
    }

    /**
     * Edits the tasks in the data file
     * @param task The task to be edited
     * @param id 1 to mark/unmark, 2 to delete the line from file
     */
    public void editTasks(Task task, int id) {
        StringBuilder tempData = new StringBuilder();
        // This splits the modified task to help get the task info
        String[] tempArr = task.toString().split(" >> ");

        try {
            Scanner sc = new Scanner(storage);
            while (sc.hasNext()) {
                // This is the task stored in the file
                String curr = sc.nextLine();
                String[] currArr = curr.split(" >> ");
                // Checks if the modified task info matches the stored task
                if (currArr[1].equals(tempArr[1])) {
                    switch (id) {
                    // For mark/unmark
                    case 1:
                        tempData.append(task).append("\n");
                        break;
                    // For delete, by skipping append
                    case 2:
                        break;
                    }
                } else {
                    tempData.append(curr).append("\n");
                }
            }

            // Clears all info from stored file. Uses tempData to fill in updated data
            FileWriter f = new FileWriter(FILE_PATH);
            f.write(tempData.toString());
            f.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Sorry, there seems to be an Issue."
                    + "\n" + "Please restart and try again");
            e.printStackTrace();
        }
    }
}
