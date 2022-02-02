package duke;

import duke.DukeException;
import duke.task.Task;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Todo;
import java.lang.Boolean;
import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {

    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads list of tasks from file.
     *
     * @throws DukeException If file not found.
     * @return List of tasks.
     */
    public ArrayList<Task> load() throws DukeException {
        ArrayList<Task> tasks = new ArrayList<Task>();
        try {
            File file = new File("duke.txt");
            if (!file.exists()) {
              throw new DukeException("File not found");
            }
            Scanner fileScanner = new Scanner(file);
            while (fileScanner.hasNextLine()) {
                String taskString = fileScanner.nextLine();
                String[] data = taskString.split(",");
                String type = data[0];
                Boolean status = Boolean.parseBoolean(data[1]);
                String text = data[2];
                if (data[0].equals("T")) {
                    tasks.add(new Todo(text, status));
                }
                else if (data[0].equals("D")) {
                    LocalDate date = LocalDate.parse(data[3]);
                    tasks.add(new Deadline(text, status, date));
                }
                else if (data[0].equals("E")) {
                    LocalDate date = LocalDate.parse(data[3]);
                    tasks.add(new Event(text, status, date));
                }
            }
            fileScanner.close();
        }
        catch (FileNotFoundException e) {
            System.out.println("Something went wrong");
        }
        return tasks;
    }

}
