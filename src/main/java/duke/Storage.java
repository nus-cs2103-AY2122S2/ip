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
    private File storageFile;
    private static String buffer = " xxx ";

    /**
     * Constructor for Storage that takes in a file path that points to the storage
     * file
     * 
     * @param filePath
     */
    public Storage(String filePath) {
        this.storageFile = new File(filePath);
    }

    /**
     * Reads the content of the storage file and passes the data to be processed by
     * the program.
     * 
     * @return an array of Strings each representing the tasks stored in the storage
     *         file
     * @throws DukeException exception thrown when file cannot be found or is
     *                       corrupted
     */
    public String[] load() throws DukeException {
        try {
            BufferedReader storageFileReader = new BufferedReader(new FileReader(this.storageFile));
            String fileContent = "";
            String dataLine = storageFileReader.readLine();

            while (dataLine != null) {
                fileContent = fileContent + dataLine + System.lineSeparator();

                dataLine = storageFileReader.readLine();
            }

            String[] tasksArr = fileContent.split(System.lineSeparator());
            storageFileReader.close();

            return tasksArr;
        } catch (FileNotFoundException e) {
            throw new DukeException("File not found!! :-(");
        } catch (IOException e) {
            throw new DukeException("Issue reading file!! D:");
        }
    }

    /**
     * Reflects the addition of the task in the storage list.
     * 
     * @param task the task that has just been added to the task list
     * @throws DukeException exception thrown when there is an error accessing or
     *                       writing to the storage file
     */
    public void updateAfterAdd(Task task) throws DukeException {
        try {
            int isMarked = task.isDone() ? 1 : 0; // isMarked is the integer representation of task.isDone(); 1 = true,
                                                  // 0 = false

            FileWriter storageFileWriter = new FileWriter(storageFile, true);
            storageFileWriter.write(task.getType() + buffer + isMarked + buffer + task.getDescription() + "\n");
            storageFileWriter.close();
        } catch (IOException e) {
            throw new DukeException("Cannot update addition in save file!! :-(");
        }
    }

    /**
     * Reflects the marked indexed task in the storage list.
     * 
     * @param index the index of the task that has just been marked as done
     * @throws DukeException exception thrown when there is an error accessing or
     *                       writing to the storage file
     */
    public void updateAfterMark(int index) throws DukeException {
        this.modifyFile(index, true);
    }

    /**
     * Reflects the unmarked indexed task in the storage list.
     * 
     * @param index the index of the task that has just been marked as not yet done
     * @throws DukeException exception thrown when there is an error accessing or
     *                       writing to the storage file
     */
    public void updateAfterUnmark(int index) throws DukeException {
        this.modifyFile(index, false);
    }

    /**
     * Reflects the deleted task in the storage list.
     * 
     * @param index the index of the task that has just been deleted
     * @throws DukeException exception thrown when there is an error accessing or
     *                       writing to the storage file
     */
    public void updateAfterDelete(int index) throws DukeException {
        try {
            int dataLineCounter = 0;
            BufferedReader storageFileReader = new BufferedReader(new FileReader(storageFile));
            String contentToBeWritten = "";
            String dateLine = storageFileReader.readLine();

            while (dateLine != null) {
                if (dataLineCounter != index) {
                    contentToBeWritten = contentToBeWritten + dateLine + System.lineSeparator();
                }

                dateLine = storageFileReader.readLine();
                dataLineCounter++;
            }

            FileWriter storageFileWriter = new FileWriter(storageFile);
            storageFileWriter.write(contentToBeWritten);

            storageFileReader.close();
            storageFileWriter.close();
        } catch (FileNotFoundException e) {
            throw new DukeException("Save file not found!! :-(");
        } catch (IOException e) {
            throw new DukeException("Cannot update deletion in save file!! D:");
        }
    }

    /**
     * Updates the storage file when there has just been a change to whether a task
     * has been marked as done or not yet done.
     * 
     * @param index        index of the task which the action is performed on
     * @param isToBeMarked true if the action marked the task as done, otherwise
     *                     false
     * @throws DukeException exception thrown when there is an error accessing or
     *                       writing to the storage file
     */

    private void modifyFile(int index, boolean isToBeMarked) throws DukeException {
        try {
            int dataLineCounter = 0;
            BufferedReader storageFileReader = new BufferedReader(new FileReader(storageFile));
            String contentToBeWritten = "";
            String dataLine = storageFileReader.readLine();

            while (dataLine != null) {
                if (dataLineCounter == index) {
                    if (dataLine.charAt(6) == '1') {
                        if (!(isToBeMarked)) {
                            dataLine = dataLine.replaceFirst("xxx 1 xxx", "xxx 0 xxx");
                        }
                    } else {
                        if (isToBeMarked) {
                            dataLine = dataLine.replaceFirst("xxx 0 xxx", "xxx 1 xxx");
                        }
                    }
                }

                contentToBeWritten = contentToBeWritten + dataLine + System.lineSeparator();
                dataLine = storageFileReader.readLine();
                dataLineCounter++;
            }

            FileWriter storageFileWriter = new FileWriter(storageFile);
            storageFileWriter.write(contentToBeWritten);

            storageFileReader.close();
            storageFileWriter.close();
        } catch (FileNotFoundException e) {
            throw new DukeException("Save file not found!! :-(");
        } catch (IOException e) {
            throw new DukeException("Cannot update edits in save file!! D:");
        }
    }

    /**
     * Wipes the storage file of all its data.
     * 
     * @throws DukeException exception thrown when there is an error accessing or
     *                       writing to the storage file
     */
    public void resetFile() throws DukeException {
        try {
            FileWriter storageFileWriter = new FileWriter(storageFile, false);
            storageFileWriter.write("");
            storageFileWriter.close();
        } catch (IOException e) {
            throw new DukeException("Cannot reset the save file!! D:");
        }
    }
}