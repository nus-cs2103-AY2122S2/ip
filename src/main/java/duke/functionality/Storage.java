package duke.functionality;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import duke.exceptions.DukeException;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.Todo;

/**
 * Storage is a functionality class to allow users to save their tasks so
 * that they can refer to them again even if they restart the program.
 */
public class Storage {
    protected String filePath;

    /**
     * Creates a Storage object, constructor.
     *
     * @param filePath A String of the filepath.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Adds the details from a saved file previously to the tasklist, so
     * that the user can access their past tasks.
     *
     * @param taskList A tasklist to keep track of the past tasks.
     * @throws DukeException If the command from the file cannot be understood.
     */
    public void readFile(TaskList taskList) throws DukeException {
        File information = new File(this.filePath);
        if (information.exists()) {
            try {
                File file = new File(this.filePath);
                Scanner sc = new Scanner(file);
                while (sc.hasNextLine()) {
                    String text = sc.nextLine();
                    String[] data = text.split(" \\| ");
                    String initial = data[0];
                    Task history;
                    switch (initial) {
                    case "T":
                        history = new Todo(data[2]);
                        break;
                    case "D":
                        history = new Deadline(data[2], Parser.convertDate(data[3]));
                        break;
                    case "E":
                        history = new Event(data[2], Parser.convertDate(data[3]));
                        break;
                    default:
                        throw new DukeException("Cannot understand the command");
                    }
                    if (data[1].equals("mark")) {
                        history.markAsDone();
                    }
                    taskList.addGeneralTask(history);
                }
            } catch (FileNotFoundException e) {
                throw new DukeException("Cannot find file...");
            }
        }
    }

    /**
     * Writes the details of the existing tasklist into the file to be saved.
     *
     * @param taskList A tasklist which contains all the tasks to be saved.
     * @throws DukeException If there is a problem with accessing the file.
     */
    public void writeFile(TaskList taskList) throws DukeException {
        try {
            File directory = new File("data");
            if (!directory.exists()) {
                directory.mkdir();
            }
            String path = directory.getAbsolutePath() + "/Duke.txt";
            File newFile = new File(path);
            FileWriter writer = new FileWriter(newFile);
            StringBuilder data = new StringBuilder();
            for (int i = 0; i < taskList.size(); i++) {
                data.append(taskList.get(i).formatString()).append("\n");
            }
            writer.write(data.toString());
            writer.close();
        } catch (IOException e) {
            throw new DukeException("Error... try again");
        }
    }
}
