package duke.internal;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;

import duke.exceptions.DukeException;
import duke.tasks.TaskList;

/**
 * Represents a storage that handles the saving/loading of
 * data from/to hard disk.
 * Stores the absolute path to the file that is used by the program.
 */
public class Storage {

    protected Path absoluteFilePath;

    /**
     * Creates an instance of a storage that stores/loads
     * from the given path.
     *
     * @param filePath the relative path of the file from program root.
     */
    public Storage(String filePath) {
        URL currentDirUrl = this.getClass().getProtectionDomain().getCodeSource().getLocation();
        String decodedUrlPath = "";
        try {
            decodedUrlPath = java.net.URLDecoder.decode(currentDirUrl.getPath(), StandardCharsets.UTF_8.name());
        } catch (Exception e) {
            //There should never be an exception
            e.printStackTrace();
        }
        File appFile = new File(decodedUrlPath);
        this.absoluteFilePath = Paths.get(appFile.getParentFile().getPath(), filePath);
    }

    /**
     * Returns a TaskList object that is loaded from the location
     * of absoluteFIlePath. If the file does not exist, throw an
     * error which indicates that there is no existing list of task.
     *
     * @return a TaskList that was saved previously by the program.
     * @throws DukeException throws an empty message exception to inform
     *                       the system that there is no saved file.
     */
    public TaskList load() throws DukeException {
        try {
            FileInputStream fis = new FileInputStream(absoluteFilePath.toString());
            ObjectInputStream ois = new ObjectInputStream(fis);
            TaskList tasks = (TaskList) ois.readObject();
            fis.close();
            ois.close();
            return tasks;
        } catch (Exception e) {
            throw new DukeException("");
        }
    }

    /**
     * Saves the TaskList object that is currently being used by
     * the program to the file specified by absoluteFilePath.
     * If the file does not exist, create the file.
     *
     * @param taskList the task list that is currently used by
     *                 the program.
     * @throws DukeException throws a DukeException to inform the user
     *                       there's an error in saving to file.
     */
    public void save(TaskList taskList) throws DukeException {
        File file = new File(absoluteFilePath.toString());
        file.getParentFile().mkdirs();
        try {
            file.createNewFile();
        } catch (IOException e) {
            //This exception should not be thrown
            e.printStackTrace();
        }

        try {
            FileOutputStream fos = new FileOutputStream(absoluteFilePath.toString());
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(taskList);
            oos.close();
            fos.close();
        } catch (IOException e) {
            throw new DukeException("Command executed but there is an error saving to disk!");
        }

    }
}
