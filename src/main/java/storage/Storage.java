package storage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Storage {
    private final String path;

    public Storage(String path) {
        this.path = path;
    }

    /**
     * Store the record of a task into storage.
     * @param info information about the task.
     **/
    public void record(String info) {
        try {
            makeDirectory();
            FileWriter myWriter = new FileWriter("../data/record.txt");
            myWriter.write(info);
            myWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
    private void makeDirectory() throws IOException {
        String fileName = this.path;
        Path path = Paths.get(fileName);
        if (!Files.exists(path)) {
            Files.createDirectory(path);
            creatFile();
        }
    }

    private void creatFile() throws IOException {
        File file = new File("../data/record.txt");
        if (!file.createNewFile())  {
            if (file.delete()) {
                creatFile();
            } else {
                System.out.println("something is wrong.");
            }
        }
    }
}
