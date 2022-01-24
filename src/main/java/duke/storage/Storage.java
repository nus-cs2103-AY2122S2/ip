package duke.storage;

import duke.exception.DukeException;
import duke.task.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    public static final String MESSAGE_READ_FAILURE = "Something went wrong with file read!";
    public static final String MESSAGE_WRITE_FAILURE = "Something went wrong with file write!";
    public static final String MESSAGE_INVALID_FILE = "File Corrupted!";

    private String filePath;

    public Storage(String filePath) throws IOException {
        this.filePath = System.getProperty("user.dir") + filePath;

        File file = new File(filePath);
        file.getParentFile().mkdirs();
        file.createNewFile();
    }

    public void saveTaskList(TaskList taskList) throws DukeException {
        try {
            FileWriter fw = new FileWriter(filePath);

            for (int i = 0; i < taskList.size(); i++) {
                Task curTask = taskList.get(i);

                fw.write(curTask.toData());
                fw.write(System.lineSeparator());
            }
            fw.close();

        } catch (IOException e) {
            throw new DukeException(MESSAGE_WRITE_FAILURE);
        }
    }

    public TaskList loadTaskList() throws DukeException {
        try {
            ArrayList<Task> taskArr = new ArrayList<>();
            File file = new File(filePath);
            Scanner sc = new Scanner(file);

            while (sc.hasNextLine()) {
                String data = sc.nextLine();
                String[] dataArgs = data.split("\\|");

                Task curTask = dataToTask(dataArgs);
                taskArr.add(curTask);
            }

            return new TaskList(taskArr);

        } catch (FileNotFoundException e) {
            throw new DukeException(MESSAGE_READ_FAILURE);
        } catch (IOException e) {
            throw new DukeException(e.getMessage());
        }
    }

    private Task dataToTask(String[] dataArgs) throws IOException {
        try {
            int taskDone = Integer.parseInt(dataArgs[1]);
            boolean isMarkValid = taskDone == 1 || taskDone == 0;
            boolean isTaskMark = taskDone == 1;

            if (!isMarkValid) {
                throw new IOException(MESSAGE_INVALID_FILE);
            }

            switch (dataArgs[0]) {
            case "T":
                return new Todo(dataArgs[2], isTaskMark);
            case "D":
                return new Deadline(dataArgs[2], isTaskMark, LocalDate.parse(dataArgs[3]), LocalTime.parse(dataArgs[4]));
            case "E":
                return new Event(dataArgs[2], isTaskMark, LocalDate.parse(dataArgs[3]), LocalTime.parse(dataArgs[4]), LocalTime.parse(dataArgs[5]));
            default:
                throw new IOException(MESSAGE_INVALID_FILE);
            }
        } catch (NumberFormatException e) {
            throw new IOException(MESSAGE_INVALID_FILE);
        }
    }

}
