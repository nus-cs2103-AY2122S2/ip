import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class LocalFileIO {
    private static final java.nio.file.Path DIR = Paths.get(".", "data");
    private static final java.nio.file.Path FILE_PATH = Paths.get(".", "data", "TaskList.txt");

    public static void init(ArrayList<Task> taskList) throws IOException {
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
                    taskList.add(temp);
                } else if (taskType.equals("D")) {
                    String taskTime = parsedList[3].substring(1);
                    Task temp = new Deadline(taskName, taskTime);
                    if (isMarked == 1) {
                        temp.setStatus(true);
                    }
                    taskList.add(temp);
                } else if (taskType.equals("E")) {
                    String taskTime = parsedList[3].substring(1);
                    Task temp = new Event(taskName, taskTime);
                    if (isMarked == 1) {
                        temp.setStatus(true);
                    }
                    taskList.add(temp);
                }
            }
        }
        return ;
    }

    public static void saveFile(ArrayList<Task> taskList) throws IOException {
        BufferedWriter writer = Files.newBufferedWriter(FILE_PATH);
        for (int i = 0; i < taskList.size(); i++) {
            Object temp = taskList.get(i);
            if (temp instanceof ToDo) {
                writer.write(((ToDo) temp).toSavedFile() + "\n");
            } else if (temp instanceof Deadline) {
                writer.write(((Deadline) temp).toSavedFile() + "\n");
            } else if (temp instanceof Event) {
                writer.write(((Event) temp).toSavedFile() + "\n");
            }
        }
        writer.flush();
        writer.close();
        return ;
    }
}
