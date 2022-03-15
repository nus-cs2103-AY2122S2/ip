package duke.admin;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import duke.exceptions.DukeException;
import duke.tasks.Task;

/**
 * Storage class manages the storage file and the actions on the storage file.
 */
public class Storage {
    private static String buffer = " xxx ";
    private File storageFile;

    /**
     * Constructor for Storage that takes in a file path that points to the storage
     * file
     * @param filePath
     */
    public Storage(String filePath) {
        this.storageFile = new File(filePath);
    }

    /**
     * Reads the content of the storage file and passes the data to be processed by
     * the program.
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
     * @param task the task that has just been added to the task list
     * @throws DukeException exception thrown when there is an error accessing or
     *                       writing to the storage file
     */
    public void updateAfterAdd(Task task, String description) throws DukeException {
        try {
            int isMarked = task.isDone() ? 1 : 0; // isMarked is the integer representation of task.isDone();

            FileWriter storageFileWriter = new FileWriter(storageFile, true);
            storageFileWriter.write(task.getType() + buffer + isMarked + buffer + description + "\n");
            storageFileWriter.close();
        } catch (IOException e) {
            throw new DukeException("Cannot update addition in save file!! :-(");
        }
    }

    /**
     * Updates the storage file after details of tasks have been updated.
     * @param typeOfTask type of task that has been updated
     * @param typeOfUpdate what details has been updated: description, date or time
     * @param index index of task that has been updated
     * @param updateValue value to be updated to
     * @throws DukeException exception thrown when there is an error accessing or writing to the storage file
     */
    public void updateAfterEdits(String typeOfTask, String typeOfUpdate, int index, String updateValue)
            throws DukeException {
        try {
            int dataLineCounter = 0; //initializing the counter
            BufferedReader storageFileReader = new BufferedReader(new FileReader(storageFile));
            String contentToBeWritten = "";
            String dataLine = storageFileReader.readLine();

            while (dataLine != null) {
                if (dataLineCounter == index) {
                    if (typeOfTask.equals("D") || typeOfTask.equals("E")) {
                        dataLine = updateNonToDoTask(typeOfUpdate, dataLine, updateValue);
                    } else if (typeOfTask.equals("T")) {
                        dataLine = updateToDoTask(typeOfUpdate, dataLine, updateValue);
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
     * Updates the non-"To Do" tasks stored in the storage file
     * @param typeOfUpdate whether description, date or time should be updated
     * @param original original To Do task to be updated
     * @param updateValue value to be updated to
     * @return updated non-"To Do" task to be stored formatted as a String
     */
    private String updateNonToDoTask(String typeOfUpdate, String original, String updateValue) {
        String updatedString = "";

        switch (typeOfUpdate) {
        case "description":
            updatedString = updateDescription(original, updateValue);
            break;
        case "date":
            updatedString = updateDate(original, updateValue);
            break;
        case "time":
            updatedString = updateTime(original, updateValue);
            break;
        default:
            break;
        }

        return updatedString;
    }

    /**
     * Updates the To Do tasks stored in the storage file
     * @param typeOfUpdate should be description only
     * @param original original To Do task to be updated
     * @param updateValue value to be updated to
     * @return updated To Do task to be stored formatted as a String
     */
    private String updateToDoTask(String typeOfUpdate, String original, String updateValue) {
        String updatedString = "";

        switch (typeOfUpdate) {
        case "description":
            updatedString = updateToDoDescription(original, updateValue);
            break;
        default:
            break;
        }

        return updatedString;
    }

    /**
     * Replaces the description in the original task stored in the storage file
     * @param original original task stored in the storage file
     * @param updateValue description to be updated to
     * @return updated task to be stored formatted as a String
     */
    private String updateDescription(String original, String updateValue) {
        int numOfCharsBeforeDescription = 12;
        int numOfCharsAfterDescription = 21;

        String prefix = original.substring(0, numOfCharsBeforeDescription);
        String suffix = original.substring(original.length() - numOfCharsAfterDescription);

        return prefix + updateValue + suffix;
    }

    /**
     * Replaces the description of the to do task stored within the storage file
     * @param original original task stored in the storage file
     * @param updateValue description to be updated to
     * @return updated task to be stored formatted as a String
     */
    private String updateToDoDescription(String original, String updateValue) {
        int numOfCharsBeforeDescription = 12;

        String prefix = original.substring(0, numOfCharsBeforeDescription);

        return prefix + updateValue;
    }

    /**
     * Replaces the date of the tasks stored within the storage file
     * @param original original task stored in the storage file
     * @param updateValue date to be updated to
     * @return updated task to be stored formatted as a String
     */
    private String updateDate(String original, String updateValue) {
        int numOfCharsFromDate = 16;
        int numOfCharsAfterDate = 6;
        int indexOfDate = original.length() - numOfCharsFromDate;

        String dateToBeUpdated = original.substring(indexOfDate, original.length() - numOfCharsAfterDate);

        return original.replaceFirst(dateToBeUpdated, updateValue);
    }

    /**
     * Replaces the time of the tasks stored within the storage file
     * @param original original task stored in the storage file
     * @param updateValue time to be updated to
     * @return updated task to be stored formatted as a String
     */
    private String updateTime(String original, String updateValue) {
        int numOfCharsFromTime = 5;

        String timeToBeUpdated = original.substring(original.length() - numOfCharsFromTime);

        return original.replaceFirst(timeToBeUpdated, updateValue);
    }

    /**
     * Updates the storage list after a clone action has been performed.
     * @param index index of task to be cloned
     * @throws DukeException exception thrown when there is an error accessing or writing to the storage file
     */
    public void updateAfterClone(int index) throws DukeException {
        try {
            int dataLineCounter = 0;
            BufferedReader storageFileReader = new BufferedReader(new FileReader(storageFile));
            String contentToBeWritten = "";
            String dataLine = storageFileReader.readLine();
            String clone = "";

            while (dataLine != null) {
                if (dataLineCounter == index) {
                    clone = clone + dataLine;
                }

                contentToBeWritten = contentToBeWritten + dataLine + System.lineSeparator();
                dataLine = storageFileReader.readLine();
                dataLineCounter++;
            }

            contentToBeWritten = contentToBeWritten + clone + System.lineSeparator();
            FileWriter storageFileWriter = new FileWriter(storageFile);
            storageFileWriter.write(contentToBeWritten);

            storageFileReader.close();
            storageFileWriter.close();
        } catch (FileNotFoundException e) {
            throw new DukeException("Save file not found!! :-(");
        } catch (IOException e) {
            throw new DukeException("Cannot update clone action in save file!! D:");
        }
    }

    /**
     * Reflects the deleted task in the storage list.
     * @param index the index of the task that has just been deleted
     * @throws DukeException exception thrown when there is an error accessing or
     *                       writing to the storage file
     */
    public void updateAfterDelete(int index) throws DukeException {
        try {
            int dataLineCounter = 0; //initializing the counter
            BufferedReader storageFileReader = new BufferedReader(new FileReader(storageFile));
            String contentToBeWritten = "";
            String dataLine = storageFileReader.readLine();

            while (dataLine != null) {
                if (dataLineCounter != index) {
                    contentToBeWritten = contentToBeWritten + dataLine + System.lineSeparator();
                }

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
            throw new DukeException("Cannot update deletion in save file!! D:");
        }
    }

    /**
     * Updates the storage file when there has just been a change to whether a task
     * has been marked as done or not yet done.
     * @param index        index of the task which the action is performed on
     * @param toMark true if the action marked the task as done, otherwise
     *                     false
     * @throws DukeException exception thrown when there is an error accessing or
     *                       writing to the storage file
     */
    public void updateAfterChangeMark(int index, boolean toMark) throws DukeException {
        try {
            int dataLineCounter = 0; //initializing the counter
            BufferedReader storageFileReader = new BufferedReader(new FileReader(storageFile));
            String contentToBeWritten = "";
            String dataLine = storageFileReader.readLine();

            while (dataLine != null) {
                if (dataLineCounter == index) {
                    if (dataLine.charAt(6) == '1') {
                        if (!(toMark)) {
                            dataLine = dataLine.replaceFirst("xxx 1 xxx", "xxx 0 xxx");
                        }
                    } else {
                        if (toMark) {
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
