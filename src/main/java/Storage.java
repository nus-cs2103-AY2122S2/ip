import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
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

    public static void updateTaskFile (ArrayList<Task> taskList) throws IOException {
        String tempFilePath = FILEPATH + ".new";
        File file = Storage.createFileIfNotExist(tempFilePath);
        FileWriter fw = new FileWriter(file, true);
        for (Task task : taskList) {
            fw.write(task.toFileFormat());
        }
        fw.close();
        Files.move(Paths.get(tempFilePath), Paths.get(FILEPATH), REPLACE_EXISTING);

    }

    public static ArrayList<Task> readSaveFile() {
        ArrayList<Task> taskList = new ArrayList<>();
        File f = new File(FILEPATH);
        Scanner s;

        try {
            s = new Scanner(f);
        } catch (IOException e) {
            return taskList;
        }

        while(s.hasNextLine()) {
            String packet = s.nextLine();
            String[] packetSections = packet.split(" \\| ");
            String taskName = packetSections[2];
            boolean isDone = Integer.parseInt(packetSections[1]) == 1;

            switch (packetSections[0]){
            case "T":
                taskList.add(new ToDo(taskName, isDone));
                break;
            case "D":
                String deadlineDate = packetSections[3];
                taskList.add(new Deadline(taskName, isDone, deadlineDate));
                break;
            case "E":
                String startDate = packetSections[3];
                taskList.add(new Event(taskName, isDone, startDate));
                break;
            default:
                break;
            }
        }

        s.close();
        return taskList;
        
    }
}