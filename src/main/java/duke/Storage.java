package src.main.java.duke;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import src.main.java.duke.task.Task;

/**
 * Storage class manages the storage file and the actions on the storage file.
 */
public class Storage {
    private File file;
    private static String buffer = " xxx ";

    enum Action {
        ADD, MODIFY, DELETE, RESET;
    }

    /**
     * Constructor for Storage that takes in a file path that points to the storage
     * file
     * 
     * @param filePath
     */
    public Storage(String filePath) {
        this.file = new File(filePath);
    }

    /**
     * load method reads the content of the storage file and passes the data to be
     * processed by the program.
     * 
     * @return an array of Strings each representing the tasks stored in the storage
     *         file
     * @throws DukeException exception thrown when file cannot be found or is
     *                       corrupted
     */
    public String[] load() throws DukeException {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(this.file));
            String fileContent = "";
            String line = reader.readLine();

            while (line != null) {
                fileContent = fileContent + line + System.lineSeparator();

                line = reader.readLine();
            }

            String[] tasksArr = fileContent.split(System.lineSeparator());
            reader.close();

            return tasksArr;
        } catch (FileNotFoundException e) {
            throw new DukeException("File not found!! :-(");
        } catch (IOException e) {
            throw new DukeException("Issue reading file!! D:");
        }
    }

    /**
     * updateAfterAdd method takes in a task that has just been added as input and
     * reflects the addition of the task in the storage list.
     * 
     * @param task the task that has just been added to the task list
     * @throws DukeException exception thrown when there is an error accessing or
     *                       writing to the storage file
     */
    public void updateAfterAdd(Task task) throws DukeException {
        try {
            int marked = task.isDone() ? 1 : 0;

            FileWriter writer = new FileWriter(file, true);
            writer.write(task.type() + buffer + marked + buffer + task.description() + "\n");
            writer.close();
        } catch (IOException e) {
            throw new DukeException("Cannot update addition in save file!! :-(");
        }
    }

    /**
     * updateAfterMark method takes in an index that references a task that has just
     * been marked as done and reflects the change in the storage list.
     * 
     * @param idx the index of the task that has just been marked as done
     * @throws DukeException exception thrown when there is an error accessing or
     *                       writing to the storage file
     */
    public void updateAfterMark(int idx) throws DukeException {
        this.modifyFile(idx, true);
    }

    /**
     * updateAfterUnmark method takes in an index that references a task that has
     * just been marked as not yet done and reflects the change in the storage list.
     * 
     * @param idx the index of the task that has just been marked as not yet done
     * @throws DukeException exception thrown when there is an error accessing or
     *                       writing to the storage file
     */
    public void updateAfterUnmark(int idx) throws DukeException {
        this.modifyFile(idx, false);
    }

    /**
     * updateAfterDelete method takes in an index that references a taskm taht has
     * just been deleted and reflects the deletion in the storage list.
     * 
     * @param idx the index of the task that has just been deleted
     * @throws DukeException exception thrown when there is an error accessing or
     *                       writing to the storage file
     */
    public void updateAfterDelete(int idx) throws DukeException {
        try {
            int lineCounter = 0;
            BufferedReader saveReader = new BufferedReader(new FileReader(file));
            String content = "";
            String line = saveReader.readLine();

            while (line != null) {
                if (lineCounter != idx) {
                    content = content + line + System.lineSeparator();
                }

                line = saveReader.readLine();
                lineCounter++;
            }

            FileWriter saveWriter = new FileWriter(file);
            saveWriter.write(content);

            saveReader.close();
            saveWriter.close();
        } catch (FileNotFoundException e) {
            throw new DukeException("Save file not found!! :-(");
        } catch (IOException e) {
            throw new DukeException("Cannot update deletion in save file!! D:");
        }
    }

    /**
     * modifyFile method updates the storage file when there has just been a change
     * to whether a task has been marked as done or not yet done.
     * 
     * @param idx  index of the task which the action is performed on
     * @param mark true if the action marked the task as done, otherwise false
     * @throws DukeException exception thrown when there is an error accessing or
     *                       writing to the storage file
     */

    private void modifyFile(int idx, boolean mark) throws DukeException {
        try {
            int lineCounter = 0;
            BufferedReader saveReader = new BufferedReader(new FileReader(file));
            String content = "";
            String line = saveReader.readLine();

            while (line != null) {
                if (lineCounter == idx) {
                    if (line.charAt(6) == '1') {
                        if (!(mark)) {
                            line = line.replaceFirst("xxx 1 xxx", "xxx 0 xxx");
                        }
                    } else {
                        if (mark) {
                            line = line.replaceFirst("xxx 0 xxx", "xxx 1 xxx");
                        }
                    }
                }

                content = content + line + System.lineSeparator();
                line = saveReader.readLine();
                lineCounter++;
            }

            FileWriter saveWriter = new FileWriter(file);
            saveWriter.write(content);

            saveReader.close();
            saveWriter.close();
        } catch (FileNotFoundException e) {
            throw new DukeException("Save file not found!! :-(");
        } catch (IOException e) {
            throw new DukeException("Cannot update edits in save file!! D:");
        }
    }

    /**
     * resetFile method wipes the storage file of all its data.
     * 
     * @throws DukeException exception thrown when there is an error accessing or
     *                       writing to the storage file
     */
    public void resetFile() throws DukeException {
        try {
            FileWriter writer = new FileWriter(file, false);
            writer.write("");
            writer.close();
        } catch (IOException e) {
            throw new DukeException("Cannot reset the save file!! D:");
        }
    }
}