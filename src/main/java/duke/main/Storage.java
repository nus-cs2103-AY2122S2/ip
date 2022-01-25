package duke.main;

import duke.exception.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Storage {
    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public ArrayList<Task> loadTasks() throws DukeException {
        File file = new File(filePath);
        if (!file.exists()) {
            return new ArrayList<Task>();
        }
        try {
            BufferedReader fileReader = new BufferedReader(new FileReader(filePath));
            String taskInput;
            ArrayList<Task> readTasks = new ArrayList<Task>();
            while ((taskInput = fileReader.readLine()) != null) {
                String[] splitTaskInput = taskInput.split(" \\| ");
                Task newTask;
                if (splitTaskInput[0].equals("T")) {
                    newTask = new ToDo(splitTaskInput[2]);
                } else if (splitTaskInput[0].equals("D")) {
                    LocalDateTime dateTime = Parser.parseDateTime(splitTaskInput[3]);
                    newTask = new Deadline(splitTaskInput[2], dateTime);
                } else if (splitTaskInput[0].equals("E")) {
                    LocalDateTime dateTime = Parser.parseDateTime(splitTaskInput[3]);
                    newTask = new Event(splitTaskInput[2], dateTime);
                } else {
                    throw new DukeException("Error: duke.task.Task type is not T,D or E in file\n");
                }
                int i = Integer.parseInt(splitTaskInput[1]);
                if (i == 1) {
                    newTask.mark(true);
                }
                readTasks.add(newTask);
            }
            return readTasks;
        } catch (IOException e) {
            throw new DukeException("Error: File input cannot be read\n" + e.getMessage());
        } catch (NumberFormatException e) {
            throw new DukeException("Error: isDone field is not indicated by 0 or 1 in file\n" + e.getMessage());
        }
    }

    public void saveFileData(ArrayList<Task> tasks) throws DukeException {
        try {
            if (Files.notExists(Paths.get(filePath))) {
                Files.createDirectories(Paths.get("data/"));
                Files.createFile(Paths.get(filePath));
            }

            BufferedWriter bw = Files.newBufferedWriter(Paths.get(filePath), Charset.forName("UTF-8"));
            for (Task t : tasks) {
                bw.append(t.getFileSaveFormat());
                bw.append("\n");
            }
            bw.close();
        } catch (IOException e) {
            throw new DukeException("Unable to write to file\n" + e.getMessage());
        }
    }
}
