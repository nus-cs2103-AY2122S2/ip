package duke;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import duke.task.Todo;
import duke.task.Deadline;
import duke.task.Event;

public class Storage {

    private final String filePath;

    Storage(String filePath) {
        this.filePath = filePath;
    }

    public TaskList load() throws IOException {
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

    public void update(TaskList tasks) throws IOException {
        File records = new File(filePath + "/data/records.txt");
        records.delete();
        records.createNewFile();
        FileWriter fw = new FileWriter(records.getPath());
        for (int i = 0; i < tasks.size(); i++) {
            fw.write(tasks.get(i).getIcon() + " " + tasks.get(i).getStatus() + " "
                    + tasks.get(i).getDescription() + "\n");
        }
        fw.close();
    }

}
