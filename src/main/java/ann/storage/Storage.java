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

/**
 * Represents the storage object which stores the tasks between uses of the program.
 *
 * @author Hong Anh
 * @version 0.1
 */
public class Storage {
    private final static String DEFAULT_STORAGE_FOLDER_NAME = "data";
    private final static String DEFAULT_STORAGE_FILE_NAME = "ann.txt";
    private File file;
    private String folderName, fileName;

    /**
     * Creates a new Storage object from the default folder and file names.
     *
     * @throws StorageOperationException
     */
    public Storage() throws StorageOperationException {
        if(!isValidFileName(DEFAULT_STORAGE_FILE_NAME)) {
            throw new InvalidStorageFilePathException("Default storage file should end with '.txt'");
        }
        openFolder(DEFAULT_STORAGE_FOLDER_NAME);
        openFile(DEFAULT_STORAGE_FOLDER_NAME, DEFAULT_STORAGE_FILE_NAME);
    }

    /**
     * Creates a new Storage object with the specified folderName and fileName.
     *
     * @param folderName a String which is the name of the folder containing the storage file.
     * @param fileName a String which is the name of the storage file.
     * @throws StorageOperationException if the storage file's name does not end with '.txt'.
     */
    public Storage(String folderName, String fileName) throws StorageOperationException{
        if(!isValidFileName(fileName)) {
            throw new InvalidStorageFilePathException("Storage file should end with '.txt'");
        }
        openFolder(folderName);
        openFile(folderName, fileName);
    }

    /**
     * Opens a new folder with the given folder name.
     *
     * @param folderName a String which is the name of the folder.
     * @throws StorageOperationException if a directory with the specified name cannot be created.
     */
    private void openFolder(String folderName) throws StorageOperationException {
        Path path = Paths.get(folderName);
        if(!Files.exists(path)) {
            File f1 = new File(folderName);
            if(!f1.mkdirs()) {
                throw new StorageOperationException("Error opening folder: " + folderName);
            }
        }
        this.folderName = folderName;
    }

    /**
     * Create a new file with the given filename in the given folder.
     *
     * @param folderName a String which is the name of the folder.
     * @param fileName a String which is the name of the file.
     * @throws StorageOperationException if the file cannot be created.
     */
    private void openFile(String folderName, String fileName) throws StorageOperationException {
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

    /**
     * Returns an ArrayList of Strings which represent the Tasks, read from the storage file.
     *
     * @return an ArrayList of Strings which represent the Tasks.
     * @throws AssertionError
     */
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

    /**
     * Writes to the storage file the description of all the tasks in the current TaskList.
     *
     * @param taskList the current TaskList of the program.
     * @throws StorageOperationException if unable to write to file.
     */
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

    /**
     * Returns a boolean which indicates whether the given filename is valid.
     *
     * @param fileName a String which is the filename.
     * @return true if the given filename ends with '.txt' and false otherwise.
     */
    private boolean isValidFileName(String fileName) {
        return fileName.endsWith(".txt");
    }

    /**
     * Represents an Exception thrown when a given filename is not valid.
     */
    public static class InvalidStorageFilePathException extends IllegalArgumentException {
        public InvalidStorageFilePathException(String message) {
            super(message);
        }
    }

    /**
     * Represents an Exception thrown when storage operations (open folder/file, save and load)
     * cannot be completed.
     */
    public static class StorageOperationException extends Exception {
        public StorageOperationException(String message) {
            super(message);
        }
    }
}