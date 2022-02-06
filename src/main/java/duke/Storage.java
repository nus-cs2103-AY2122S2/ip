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

/**
 * Main logic for storing data to filesystem.
 */
public class Storage {

    /**
     * Saves current tasks snapshot to a file.
     * The file is stored in "data/duke.txt".
     *
     * @param storageStrings List of texts to be saved. Each text represents 1 task.
     * @param taskListString Text from Task.list method. For printing purposes.
     * @return Response text to be printed.
     */
    public String exportData(List<String> storageStrings, String taskListString) {
        String response = "The following tasks will be saved: \n" + taskListString;
        Path dir = Paths.get(Constants.DIR_PATH);
        Path store = Paths.get(Constants.STORAGE_PATH);

        try {
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
        } catch (IOException e) {
            return e.getMessage();
        }

    }

    /**
     * Imports previous tasks snapshot from a file if present.
     * The file is stored in "data/duke.txt".
     *
     * @return Response text to be printed.
     */
    public List<String> importData() {
        List<String> storageStrings = new ArrayList<>();
        if (Files.exists(Paths.get(Constants.STORAGE_PATH))) {
            File f = new File(Constants.STORAGE_PATH);

            try {
                Scanner fileScanner = new Scanner(f);
                while (fileScanner.hasNextLine()) {
                    storageStrings.add(fileScanner.nextLine());
                }
            } catch (FileNotFoundException e) {
                return storageStrings;
            }

        }
        return storageStrings;
    }
}
