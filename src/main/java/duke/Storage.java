package duke;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * A class that focuses on creating data/tasks.txt on the users hard disk alongside reading from and writing to it.
 */
public class Storage {
    private final File f;

    public Storage(String filePath) {
        this.f = new File(filePath);
    }

    /**
     * Firstly, checks if the data/tasks.txt file already exists on the user's system. If not, it creates the file in
     * the appropriate location.
     * It mainly, then, reads the file's data and calls the appropriate parsing functions to add the appropriate task
     * objects the tasklist manager.
     * @throws CustomException if the file cannot be created on the user's system
     */
    public void load() throws CustomException {
        try {
            if (!(f.exists() && !f.isDirectory())) {
                f.getParentFile().mkdirs();
                f.createNewFile();
            }
            assert f.exists();

            Scanner sc = new Scanner(f);
            while (sc.hasNextLine()) {
                String input = sc.nextLine();
                String[] details = input.split(";", 4);
                Task currTask = Parser.parseCommandFromFile(details);
                TaskList.manager.add(currTask);
            }
            sc.close();
        } catch (IOException e) {
            throw new CustomException("File cannot be created");
        }
    }

    /**
     * Does an initial check for existence of the file. If not, it creates the file in the desired location.
     * Then writes all the tasks from the Tasklist manager to the file in it's appropriate format
     */
    public void addToFile() {
        try {
            if (!(f.exists() && !f.isDirectory())) {
                f.getParentFile().mkdirs();
                f.createNewFile();
            }
            assert f.exists();
            FileWriter writer = new FileWriter(f);
            for (Task t : TaskList.manager) {
                String output;
                if (t instanceof Todo) {
                    output = "T ; " + (t.isDone ? "1 ; " : "0 ; ") + t.description;
                } else if (t instanceof Deadline) {
                    output = "D ; " + (t.isDone ? "1 ; " : "0 ; ") + t.description + " ; "
                            + ((Deadline) t).displayTimeInOriginalFormat();
                } else {
                    output = "E ; " + (t.isDone ? "1 ; " : "0 ; ") + t.description + " ; "
                            + ((Event) t).displayTimeInOriginalFormat();
                }
                writer.write(output + "\n");
                writer.flush();
            }
            writer.close();
        } catch (IOException e) {
            System.out.print(e.getMessage());
        }
    }
}
