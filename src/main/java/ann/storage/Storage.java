package ann.storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

import ann.data.TaskList;

public class Storage {
    private File file;
    private String folderName, fileName;

    public Storage(String folderName, String fileName) throws StorageOperationException{
        if(!isValidFileName(fileName)) {
            throw new InvalidStorageFilePathException("Storage file should end with '.txt'");
        }
        openFolder(folderName);
        openFile(folderName, fileName);
    }

    public void openFolder(String folderName) throws StorageOperationException {
        Path path = Paths.get(folderName);
        if(!Files.exists(path)) {
            File f1 = new File(folderName);
            if(!f1.mkdirs()) {
                throw new StorageOperationException("Error opening folder: " + folderName);
            }
        }
        this.folderName = folderName;
    }

    public void openFile(String folderName, String fileName) throws StorageOperationException {
        Path filePath = Paths.get(folderName + fileName);
        if(!Files.exists(filePath)) {
            file = new File(folderName, fileName);
            try {
                file.createNewFile();
            } catch (IOException e) {
                throw new StorageOperationException("Error opening file: " + fileName);
            }
        }
        this.fileName = fileName;
    }

    public ArrayList<String> load() throws AssertionError{
        ArrayList<String> tasks = new ArrayList<>();
        try {
            Scanner sc = new Scanner(file);
            while (sc.hasNext()) {
                tasks.add(sc.nextLine());
            }
            return tasks;
        } catch (FileNotFoundException e) {
            throw new AssertionError("A non-existent file scenario is already handled earlier");
        }
    }

    public void save(TaskList taskList) throws StorageOperationException {
        try {
            String content = taskList.getFileString();
            FileWriter fw = new FileWriter(file);
            fw.write(content);
            fw.close();
        } catch (IOException e) {
            throw new StorageOperationException("Error writing to file: " + this.folderName + "/" + this.fileName);
        }
    }

    private boolean isValidFileName(String fileName) {
        return fileName.endsWith(".txt");
    }

    public static class InvalidStorageFilePathException extends IllegalArgumentException {
        public InvalidStorageFilePathException(String message) {
            super(message);
        }
    }

    public static class StorageOperationException extends Exception {
        public StorageOperationException(String message) {
            super(message);
        }
    }
}
