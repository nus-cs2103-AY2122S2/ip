package duke.storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.NoSuchElementException;
import java.util.Scanner;

import duke.exception.DukeException;
import duke.task.Task;
import duke.task.TaskList;
import duke.user.UserProfile;

/**
 * Represents the storage system of the program. A <code>Storage</code> object can be created to
 * save and load the task list data into the hard disk. It allows data to be retained to the
 * next instance of the program.
 */
public class Storage {
    public static final String MESSAGE_READ_FAILURE = "Something went wrong with file read!";
    public static final String MESSAGE_WRITE_FAILURE = "Something went wrong with file write!";
    public static final String MESSAGE_INVALID_FILE = "File Corrupted!";

    private Scanner sc;
    private final String filePath;

    /**
     * Creates an instance of a Storage object.
     *
     * @param inputPath path to the storage file.
     * @throws IOException if the creation of directory and text file fails.
     */
    public Storage(String inputPath) throws IOException {
        this.filePath = System.getProperty("user.dir") + inputPath;

        File file = new File(this.filePath);
        file.getParentFile().mkdirs();
        file.createNewFile();
    }

    /**
     * Saves the user profile by writing the data of the user info into
     * the file specified in the Storage object.
     *
     * @param userProfile the user profile to be saved.
     * @throws DukeException if write to file fails.
     */
    public void saveUserProfile(UserProfile userProfile) throws DukeException {
        try {
            FileWriter fw = new FileWriter(filePath);

            String[] dataSegments = userProfile.toData().split("\n");

            for (String dataSegment : dataSegments) {
                fw.write(dataSegment);
                fw.write(System.lineSeparator());
            }
            fw.close();

        } catch (IOException e) {
            throw new DukeException(MESSAGE_WRITE_FAILURE);
        }
    }

    /**
     * Returns a user profile by reading the data of the file
     * specified in the Storage object.
     *
     * @return a saved user profile.
     * @throws DukeException if write to file fails.
     */
    public UserProfile loadUserProfile() throws DukeException {
        try {
            File file = new File(filePath);
            sc = new Scanner(file);

            TaskList taskList = decodeTaskList();

            // Decode user profile information
            String profileInfoArg = sc.nextLine();

            if (!profileInfoArg.equals(String.format("[%s]", UserProfile.STORAGE_HEADER))) {
                throw new IOException();
            }

            String[] profileData = new String[UserProfile.STATS_NO];
            String data;

            for (int i = 0; i < UserProfile.STATS_NO; i++) {
                data = sc.nextLine();
                profileData[i] = data;
            }
            sc.close();

            return new UserProfile(taskList, LocalDate.parse(profileData[0]),
                    Integer.parseInt(profileData[1]));

        } catch (FileNotFoundException e) {
            throw new DukeException(MESSAGE_READ_FAILURE);
        } catch (IOException e) {
            throw new DukeException(MESSAGE_INVALID_FILE);
        } catch (NoSuchElementException e) {
            throw new DukeException(e.getMessage(), true);
        }
    }

    private TaskList decodeTaskList() throws IOException {
        TaskList taskList = new TaskList();

        String data = sc.nextLine();
        String[] taskListArgs = data.split(" ");

        if (!taskListArgs[0].equals(String.format("[%s]", TaskList.STORAGE_HEADER))) {
            throw new IOException();
        }

        for (int i = 0; i < Integer.parseInt(taskListArgs[1]); i++) {
            data = sc.nextLine();

            Task curTask = Task.createTask(data);
            taskList.add(curTask);
        }

        return taskList;
    }
}
