import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Storage {
    String filePath;

    Storage(String filePath) {
        this.filePath = filePath;
    }

    List<Task> load() throws DukeException {
        List<Task> tasks = new ArrayList<Task>();
        File file = new File(this.filePath);

        if (file.exists()) {
            try {
                Scanner fileSc = new Scanner(file);
                while (fileSc.hasNextLine()) {
                    String[] line = fileSc.nextLine().split(";");
                    Task task;
                    // Todo Task
                    if (line[0].equals("T")) {
                        task = new ToDoTask(line[2]);
                    }
                    // Deadline Task
                    else if (line[0].equals("D")) {
                        task = new DeadlineTask(line[2], line[3], LocalDateTime.parse(line[4]));
                    }
                    // Event Task
                    else if (line[0].equals("E")) {
                        task = new EventTask(line[2], line[3], LocalDateTime.parse(line[4]));
                    } else {
                        throw new DukeException("An invalid task type was read");
                    }
                    if (line[1].equals("X")) task.mark();
                    tasks.add(task);
                }
            } catch (FileNotFoundException e) {
                throw new DukeException("The file cannot be found at the specified path");
            }
        }
        return tasks;
    }

    void save(TaskList tasks) throws DukeException {
        try {
            FileWriter fileWriter = new FileWriter(this.filePath);
            for (Task task : tasks.getTasks()) {
                String taskString = "";
                if (task instanceof ToDoTask) {
                    taskString += "T;";
                } else if (task instanceof DeadlineTask) {
                    taskString += "D;";
                } else if (task instanceof EventTask) {
                    taskString += "E;";
                }
                if (task.done) {
                    taskString += "X;";
                } else {
                    taskString += "O;";
                }
                taskString += task.name;
                if (task instanceof DeadlineTask) {
                    DeadlineTask dTask = (DeadlineTask) task;
                    taskString += ";";
                    taskString += dTask.preposition;
                    taskString += ";";
                    taskString += dTask.dateTime.format(DateTimeFormatter.ISO_DATE_TIME);
                } else if (task instanceof EventTask) {
                    EventTask dTask = (EventTask) task;
                    taskString += ";";
                    taskString += dTask.preposition;
                    taskString += ";";
                    taskString += dTask.dateTime.format(DateTimeFormatter.ISO_DATE_TIME);
                }
                fileWriter.write(taskString);
                fileWriter.write(System.lineSeparator());
            }
            fileWriter.close();
        } catch (IOException e) {
            throw new DukeException("Unable to save tasks *quack*");
        }
    }
}
