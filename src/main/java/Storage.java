import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

public class Storage {
    File f;

    public Storage(String filePath) {
        f = new File(filePath);
        if (!f.exists()){
            f = new File("Data");
            f.mkdirs();
            f = new File("Data/tasks.txt");
        }
    }

    public File load(){
        return f;
    }

    private static void writeToFile(String filePath, String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(filePath, true);
        fw.write(textToAdd);
        fw.close();
    }

    public void save(TaskList taskList) throws IOException {
        Files.delete(Path.of("Data/tasks.txt"));
        for (Task t: taskList.getTasks()) {
            try {
                writeToFile("Data/tasks.txt", t.saveFormat + System.lineSeparator());
            } catch (IOException e) {
                File f = new File("Data");
                f.mkdirs();
                writeToFile("Data/tasks.txt", t.saveFormat + System.lineSeparator());
            }
        }
    }
}
