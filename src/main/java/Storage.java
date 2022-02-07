import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Scanner;

public class Storage {
    private final String path;

    public Storage(String path) {
        this.path = path;
    }

    public void loadTo(TaskList taskList) {
        try {
            File file = new File(path);
            file.createNewFile();
            Scanner sc = new Scanner(file);
            sc.useDelimiter(" \\| ");
            while (sc.hasNext()) {
                String taskType = sc.next();
                String isDoneSymbol = sc.next();
                String content = sc.next();
                boolean isDone;
                switch (isDoneSymbol) {
                case "0":
                    isDone = false;
                    break;
                case "1":
                    isDone = true;
                    break;
                default:
                    throw new DukeException("I cannot read the file. It is not in the expected format.");
                }
                switch (taskType) {
                case "T":
                    taskList.readFromFile(new Todo(content, isDone));
                    break;
                case "D":
                    taskList.readFromFile(new Deadline(content, LocalDate.parse(sc.next()), isDone));
                    break;
                case "E":
                    taskList.readFromFile(new Event(content, sc.next(), isDone));
                    break;
                default:
                    throw new DukeException("I cannot read the file. It is not in the expected format.");
                }
            }
        } catch (IOException e) {
            throw new DukeException("I cannot create the data file.");
        }
    }

    public void writeFrom(TaskList taskList) {
        try {
            FileWriter fw = new FileWriter(path, false);
            fw.write(taskList.fileUpdated());
            fw.close();
        } catch (IOException e) {
            System.err.println(new DukeException("I cannot save changes to file.").toString());
            System.out.println("You could save this text file manually and reboot: ");
            System.out.println(taskList.fileUpdated());
        }
    }
}
