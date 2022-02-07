package echo.storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;
import java.util.regex.Pattern;

import echo.task.DeadlineTask;
import echo.task.EventTask;
import echo.task.TaskList;
import echo.task.TodoTask;
import echo.utils.EchoException;

/**
 * Storage deals with loading tasks from the file and saving tasks in the file.
 */
public class Storage {

    /** File path containing the saved task list. */
    private final String FILE_PATH;

    /**
     * Constructor for Storage class.
     *
     * @param filePath File path of the saved task list.
     */
    public Storage(String filePath) {
        this.FILE_PATH = filePath;
    }

    /**
     * Returns FILE_PATH.
     *
     * @return FILE_PATH.
     */
    public String filePath() {
        return this.FILE_PATH;
    }

    /**
     * Loads user save file from FILE_PATH.
     *
     * @return TaskList loaded from FILE_PATH.
     *
     * @throws FileNotFoundException If file at FILE_PATH does not exist.
     * @throws EchoException If file at FILE_PATH is not formatted properly.
     */
    public TaskList load() throws FileNotFoundException, EchoException {
        File file = new File(FILE_PATH);

        Scanner s = new Scanner(file);

        TaskList tasks = new TaskList();

        // Directory that the file is located in.
        File directory = file.getParentFile();

        // Creates the directory if it does not exist.
        if (!directory.exists()) {
            directory.mkdir();
        }

        while (s.hasNext()) {
            String line = s.nextLine();
            try {
                String[] splitVerticalBar = line.split(Pattern.quote(" | "));
                String type = splitVerticalBar[0];
                String desc = splitVerticalBar[2];

                // Add task to tasks based on type.
                switch (type) {
                case "T":
                case "D":
                case "E":
                    if (type.equals("T")) {
                        tasks.add(new TodoTask(desc));
                    } else {
                        if (type.equals("D")) {
                            tasks.add(new DeadlineTask(desc, getLocalDateTime(splitVerticalBar)));
                        } else {
                            tasks.add(new EventTask(desc, getLocalDateTime(splitVerticalBar)));
                        }
                    }

                    // Marks tasks based on second input, 0 or 1.
                    if (Integer.parseInt(splitVerticalBar[1]) == 1) {
                        tasks.mark(tasks.size() - 1);
                    }
                    break;
                default:
                    break;
                }
            } catch (NumberFormatException nfe) {
                throw new EchoException(FILE_PATH + "\nSecond input must be 0 or 1 at line: " + line);
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new EchoException(FILE_PATH + "\nInvalid format at line at line: " + line);
            } catch (DateTimeParseException e) {
                throw new EchoException(FILE_PATH + "\nInvalid date time format at line at line: " + line
                        + "\nFollow the <yyyy-mm-dd> <24hr time> format. E.g. 2019-10-15 1800");
            }
        }
        s.close();
        return tasks;
    }

    /**
     * Returns local date time.
     *
     * @param splitVerticalBar Input from user, split based on "|".
     *
     * @return LocalDateTime.
     */
    private LocalDateTime getLocalDateTime(String[] splitVerticalBar) {
        return LocalDateTime.parse(splitVerticalBar[3], DateTimeFormatter.ofPattern("yyyy-M-d HHmm"));
    }

    /**
     * Saves TaskList to FILE_PATH.
     *
     * @param tasks TaskList to be saved.
     *
     * @throws IOException If file cannot be accessed.
     */
    public void save(TaskList tasks) throws IOException {
        try (FileWriter fw = new FileWriter(FILE_PATH)) {
            for (int i = 0; i < tasks.size(); i++) {
                fw.write(tasks.saveTaskFormat(i));
                if (i != tasks.size() - 1) {
                    fw.write(System.lineSeparator());
                }
            }
        }
    }
}
