package duke;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.Todo;

public class Storage {

    private final String filePath;

    /**
     * Constructs a storage area in the specified folder.
     * @param filePath a path of a folder, not null
     */
    Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads previous records from the storage folder.
     *   If such file does not exist, creates a file for recording.
     * @return a list of tasks kept in the records
     * @throws IOException if an I/O error occurred
     * @throws DukeException if the records do not have the correct formats
     */
    public TaskList load() throws IOException, DukeException {
        TaskList tasks = new TaskList();
        File directory = new File(filePath + "/data");
        if (!directory.exists()) {
            directory.mkdir();
        }
        File records = new File(filePath + "/data/records.txt");
        if (!records.createNewFile()) {
            Scanner sc_file = new Scanner(records);
            while (sc_file.hasNext()) {
                String[] record = sc_file.nextLine().split(" ", 3);
                switch (record[0]) {
                case "T":
                    tasks.add(new Todo(record[2]));
                    break;
                case "D":
                    String[] desc_by = record[2].split(" /by ", 2);
                    DukeDateTime by = DukeDateTime.parse(desc_by[1]);
                    tasks.add(new Deadline(desc_by[0], by));
                    break;
                case "E":
                    String[] desc_at = record[2].split(" /at ", 2);
                    DukeDateTime at = DukeDateTime.parse(desc_at[1]);
                    tasks.add(new Event(desc_at[0], at));
                    break;
                }
                if (record[1].equals("1")) {
                    tasks.set(tasks.size() - 1, tasks.get(tasks.size() - 1).mark());
                }
            }
        }
        return tasks;
    }

    /**
     * Updates the records according to the new list of tasks.
     * @param tasks the new list of tasks
     * @throws IOException if an I/O error occurred
     */
    public void update(TaskList tasks) throws IOException {
        File records = new File(filePath + "/data/records.txt");
        records.delete();
        records.createNewFile();
        FileWriter fw = new FileWriter(records.getPath());
        for (int i = 0; i < tasks.size(); i++) {
            Task t = tasks.get(i);
            fw.write(t.toStringRecord() + "\n");
        }
        fw.close();
    }

}
