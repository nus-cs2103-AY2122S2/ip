import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

public class Storage {

    private static final String FILEPATH = "data/duke.txt";

    private static File createFileIfNotExist(String filePath) throws IOException{
        File file = new File(filePath);
        file.createNewFile();
        return file;
    }

    public static void updateTaskFile (TaskList taskList) throws IOException {
        String tempFilePath = FILEPATH + ".new";
        File file = Storage.createFileIfNotExist(tempFilePath);
        FileWriter fw = new FileWriter(file, true);
        for (Task task : taskList.tasks) {
            fw.write(task.toFileFormat());
        }
        fw.close();
        Files.move(Paths.get(tempFilePath), Paths.get(FILEPATH), REPLACE_EXISTING);

    }

    public static TaskList readSaveFile() throws DukeException{
        TaskList tasks = new TaskList();
        File f = new File(FILEPATH);
        Scanner s = null;

        try {
            s = new Scanner(f);
            while(s.hasNextLine()) {
                String packet = s.nextLine();
                String[] packetSections = packet.split(" \\| ");
                String taskName = packetSections[2];
                boolean isDone = Integer.parseInt(packetSections[1]) == 1;

                switch (packetSections[0]){
                case "T":
                    tasks.addTask(new ToDo(taskName, isDone));
                    break;
                case "D":
                    String deadlineString = packetSections[3];
                    tasks.addTask(new Deadline(taskName, isDone, deadlineString));
                    break;
                case "E":
                    String startDateString = packetSections[3];
                    tasks.addTask(new Event(taskName, isDone, startDateString));
                    break;
                default:
                    break;
                }
            }
        } catch (FileNotFoundException e) {
            tasks = new TaskList();
        } finally {
            s.close();
        }

        return tasks;
        
    }
}