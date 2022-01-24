package duke;

import tasks.TaskList;
import tasks.Task;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;

public class Storage implements StorageInterface{
    File f;

    public Storage(String filePath) {
        f = new File(filePath);
        if (!f.exists()){
            f = new File("Data");
            f.mkdirs();
            f = new File("Data/tasks.txt");
        }
    }

    /**
     * Loads the saved file of tasks list from directory.
     *
     * @return File of saved tasks list.
     */
    public File load(){
        return f;
    }

    /**
     * Writes data to file.
     *
     * @param filePath The directory path containing the file.
     * @param textToAdd Text to be added to the file.
     * @throws IOException If the file could not be written.
     */
    public void writeToFile(String filePath, String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(filePath, true);
        fw.write(textToAdd);
        fw.close();
    }

    /**
     * Saves the current list of tasks to the hard disk before chat-bot exits.
     *
     * @param taskList The current list of tasks.
     * @throws IOException If the file could not be written.
     */
    public void save(TaskList taskList) throws IOException {
        try{
            Files.delete(Path.of("Data/tasks.txt"));
        } catch (NoSuchFileException e){}
        finally{
            for (Task t: taskList.getTasks()) {
                try {
                    writeToFile("Data/tasks.txt", t.saveFormat + "," + t.getStatus() + System.lineSeparator());
                } catch (FileNotFoundException e) {
                    File f = new File("Data");
                    f.mkdirs();
                    writeToFile("Data/tasks.txt", t.saveFormat + "," + t.getStatus() + System.lineSeparator());
                }
            }
        }
    }
}
