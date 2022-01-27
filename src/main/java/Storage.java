import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class Storage {
    private static java.nio.file.Path DIR;
    private static java.nio.file.Path FILE_PATH;

    public Storage(String dir, String fileName) {
        DIR = Paths.get(".", dir);
        FILE_PATH = Paths.get(".", dir, fileName);
    }
    public void init(TaskList taskList) throws IOException, DateTimeParseException {
        if (!Files.exists(DIR)) {
            Files.createDirectory(DIR);
        }
        if (!Files.exists(FILE_PATH)) {
            Files.createFile(FILE_PATH);
        } else {
            BufferedReader reader = Files.newBufferedReader(FILE_PATH);
            String str = null;

            while((str = reader.readLine()) != null) {
                String[] parsedList = str.split("\\|");
                String taskType = parsedList[0].split(" ")[0];
                Integer isMarked = Character.getNumericValue(parsedList[1].charAt(1));
                String taskName = parsedList[2].substring(1);

                if (taskType.equals("T")) {
                    Task temp = new ToDo(taskName);
                    if (isMarked == 1) {
                        temp.setStatus(true);
                    }
                    taskList.addTask(temp);
                } else if (taskType.equals("D")) {
                    String taskTime = parsedList[3].substring(1);
                    Task temp = new Deadline(taskName, LocalDate.parse(taskTime));
                    if (isMarked == 1) {
                        temp.setStatus(true);
                    }
                    taskList.addTask(temp);
                } else if (taskType.equals("E")) {
                    String taskTime = parsedList[3].substring(1);
                    Task temp = new Event(taskName, LocalDate.parse(taskTime));
                    if (isMarked == 1) {
                        temp.setStatus(true);
                    }
                    taskList.addTask(temp);
                }
            }
        }
        return ;
    }

    public void saveFile(TaskList taskList) throws IOException {
        BufferedWriter writer = Files.newBufferedWriter(FILE_PATH);
        for (int i = 0; i < taskList.getSize(); i++) {
            writer.write(taskList.getTaskSavingStyle(i) + "\n");
        }
        writer.flush();
        writer.close();
        return ;
    }
}
