package duke.storage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Storage {
    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
        createInitialFolder();
    }

    private void createInitialFolder() {
        File dataFolder = new File ("data");

        if (!dataFolder.exists()) {
            dataFolder.mkdir();
        }

        File dataFile = new File(this.filePath);
        if (!dataFile.exists()) {
            try {
                dataFile.createNewFile();
            } catch (IOException e) {
                System.out.println("An error occured :(" + e);
            }
        }
    }

    private void writeToFile(String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        fw.write(textToAdd);
        fw.close();
    }

    public void writeToPath(String textToAdd) {
        String file = filePath;
        try {
            writeToFile(textToAdd);
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }
}
