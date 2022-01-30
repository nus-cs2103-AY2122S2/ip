import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private final File file;

    private enum TaskType {
        T, E, D
    }

    public Storage(String filePath) throws DukeException {
        this.file = new File(filePath);

        try {
            if (!this.file.exists()) {
                this.file.getParentFile().mkdirs();
                this.file.createNewFile();
            }
        } catch (IOException e) {
            throw new DukeException("INVALID FILE PATH");
        }
    }

    public void clearFile() throws DukeException {
        this.writeToFile(new TaskList());
    }

    public void writeToFile(TaskList taskList) throws DukeException {
        try {
            FileWriter fw = new FileWriter(this.file);
            fw.write(taskList.toData());
            fw.close();
        } catch (IOException e) {
            throw new DukeException("INVALID FILE PATH");
        }
    }

    public ArrayList<Task> readFromFile() throws DukeException {
        ArrayList<Task> tasks = new ArrayList<>();

        try {
            Scanner sc = new Scanner(this.file);

            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                String[] breakdown = line.split(" \\| ");
                String command = breakdown[0];

                try {
                    TaskType taskType = TaskType.valueOf(command);

                    switch (taskType) {
                    case T:
                        tasks.add(new Todo(breakdown[2],
                                Boolean.parseBoolean(breakdown[1])));
                        break;
                    case E:
                        tasks.add(new Event(breakdown[2],
                                Boolean.parseBoolean(breakdown[1]), LocalDateTime.parse(breakdown[3])));
                        break;
                    case D:
                        tasks.add(new Deadline(breakdown[2],
                                Boolean.parseBoolean(breakdown[1]), LocalDateTime.parse(breakdown[3])));
                        break;
                    }
                } catch (IllegalArgumentException e) {
                    throw new DukeException("INVALID TASK TYPE");
                }
            }

            return tasks;
        } catch (IOException e) {
            throw new DukeException("INVALID FILE PATH");
        }
    }
}
