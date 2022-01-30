package duke.util;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

public class Storage {
    boolean isDirty;
    Path savePath;

    public Storage(String savePath) {
        this.savePath = Path.of(savePath);
        isDirty = false;
    }

    public TaskList load() {
        TaskList tasks = new TaskList();

        if (!Files.exists(savePath)) {
            return tasks;
        }

        try {
            Scanner fileScanner = new Scanner(savePath);
            while (fileScanner.hasNextLine()) {
                String[] taskString = fileScanner.nextLine().split(",.,");

                Task t;
                if (taskString[0].equals("T")) {
                    t = new ToDo(taskString[2]);
                } else if (taskString[0].equals("D")) {
                    t = new Deadline(taskString[2], taskString[3]);
                } else { // "E"
                    t = new Event(taskString[2], taskString[3]);
                }

                if (taskString[1].equals("1")) {
                    t.done();
                }

                tasks.add(t);
            }
        } catch (IOException e) {
            System.out.println("Kenobi could not read the save files");
        }

        return tasks;
    }

    public void save(TaskList tasks) {
        try {
            Files.createDirectories(savePath.getParent());

            BufferedWriter out = Files.newBufferedWriter(savePath);
            for (Task task : tasks) {
                out.append(task.toSave());
            }
            out.close();
        } catch (IOException ioException) {
            System.out.println("Kenobi could save the tasks");
        }
    }
}
