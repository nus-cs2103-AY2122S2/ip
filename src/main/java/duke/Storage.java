package duke;

import duke.util.Constants;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Storage {
    public String exportData(List<String> storageStrings, String taskListString) throws IOException {
        String response = "The following tasks will be saved: \n" + taskListString;
        Path dir = Paths.get(Constants.DIR_PATH);
        Path store = Paths.get(Constants.STORAGE_PATH);
        if (!Files.exists(dir)) {
            Files.createDirectory(dir);
            Files.createFile(store);
        } else if (!Files.exists(store)) {
            Files.createFile(store);
        }

        FileWriter writer = new FileWriter(Constants.STORAGE_PATH);
        for (String task : storageStrings) {
            writer.write(task);
        }
        writer.close();
        return response;
    }

    public List<String> importData() throws FileNotFoundException {
        List<String> storageStrings = new ArrayList<>();
        if (Files.exists(Paths.get(Constants.STORAGE_PATH))) {
            File f = new File(Constants.STORAGE_PATH);
            Scanner fileScanner = new Scanner(f);
            while(fileScanner.hasNextLine()) {
                storageStrings.add(fileScanner.nextLine());
            }
        }
        return storageStrings;
    }
}
