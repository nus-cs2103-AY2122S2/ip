package funbox.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.HashMap;

import funbox.exception.FunBoxExceptions;
import funbox.task.Deadline;
import funbox.task.Event;
import funbox.task.Task;
import funbox.task.ToDo;

/**
 * Deal with writing and reading user commands locally.
 */
public class Storage {
    private final String DIR_URL = "data/";
    private final String FILE_URL = "data/tasks.txt";
    private final String DELIMITER = ",";
    private final Ui ui;
    private final Parser parser;
    private TaskList taskList;
    private final HashMap<String, String> typeHashmap;

    /**
     * The constructor for the storage class.
     *
     * @param ui Interface which interact with users.
     * @param parser Parses user commands.
     */
    public Storage(Ui ui, Parser parser) {
        this.ui = ui;
        this.parser = parser;
        this.typeHashmap = new HashMap<>();
        this.taskList = new TaskList();
        setUpHashMap();
        createDirectory();
        createFile();
        setTaskList();
    }

    /**
     * Sets up the hashmap
     */
    private void setUpHashMap() {
        this.typeHashmap.put("D", "deadline");
        this.typeHashmap.put("T", "todo");
        this.typeHashmap.put("E", "event");
    }

    /**
     * Creates a new directory if the specified directory do not exist.
     */
    private void createDirectory() {
        if (!doesDirectoryExist()) {
            new File(DIR_URL).mkdir();
        }
    }

    /**
     * Set the task list by reading the file.
     */
    public void setTaskList() {
        try {
            this.taskList = readFile();
        } catch (FunBoxExceptions | IOException e) {
            System.out.println(e.getMessage());
        }
    }
    /**
     * Creates a new file if the specified file does not exist.
     */
    private void createFile() {
        if (!doesFileExist()) {
            try {
                new File(FILE_URL).createNewFile();
            } catch (IOException e) {
                ui.showError(e.getMessage());
            }
        }
    }

    /**
     * Creates a new file if the specified file does not exist.
     */
    private void createTempFile(String url) {
        try {
            new File(url).createNewFile();
        } catch (IOException e) {
            ui.showError(e.getMessage());
        }
    }

    /**
     * Checks if directory 'data' exists in 'ip' folder
     *
     * @return Return true if directory exist, otherwise, false
     */
    private boolean doesDirectoryExist() {
        return new File(this.DIR_URL).isDirectory();
    }

    /**
     * Checks if file tasks.txt exist in 'data'
     *
     * @return Return true if file exist, otherwise, false
     */
    private boolean doesFileExist() {
        return Files.exists(Paths.get(this.FILE_URL));
    }

    /**
     * Returns the task list.
     *
     * @return Return the task list to the user.
     */
    public TaskList getTaskList() {
        return this.taskList;
    }

    /**
     * Reads the existing file, if there are existing tasks in the file, add to the current list
     *
     * @throws FileNotFoundException If file is not found in the given URL
     * @throws IOException           If an I/O error occur
     */
    public TaskList readFile() throws IOException, FunBoxExceptions {
        File file = new File(this.FILE_URL);
        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);
        TaskList taskList = new TaskList();

        int counter = 0;

        while (true) {
            String task = br.readLine();

            if (task == null) {
                br.close();
                break;
            }

            String[] taskSplit = task.split(DELIMITER);
            String type = typeHashmap.get(taskSplit[0]);
            String description = taskSplit[2];
            boolean isDone = isTaskDone(taskSplit[1]);

            switch (type) {
            case "todo":
                ToDo todo = new ToDo(description, type);

                if (isTask(3, taskSplit)) {
                    addTagsToTask(3, taskSplit, todo);
                }

                taskList.add(todo);
                break;
            case "event":
                LocalDate eventDate = parser.stringToLocalDate(taskSplit[3]);
                String eventTime = parser.getTime(taskSplit[3]);
                Event event = new Event(description, eventDate, eventTime, type);
                if (isTask(4, taskSplit)) {
                    addTagsToTask(4, taskSplit, event);
                }
                taskList.add(event);
                break;
            case "deadline":
                LocalDate deadlineDate = parser.stringToLocalDate(taskSplit[3]);
                String deadlineTime = parser.getTime(taskSplit[3]);
                Deadline deadline = new Deadline(description, deadlineDate, deadlineTime, type);
                if (isTask(4, taskSplit)) {
                    addTagsToTask(4, taskSplit, deadline);
                }
                taskList.add(new Deadline(description, deadlineDate, deadlineTime, type));
                break;
            }
            if (isDone) {
                taskList.setPreTaskDone(counter);
            }
        }

        return taskList;
    }

    private boolean isTask(int index, String[] arr) {
        return index <= (arr.length - 1);
    }

    private void addTagsToTask(int index, String[] tags, Task task) {
        for (int i = index; i < tags.length; i++) {
            task.addTag(tags[i]);
        }
    }

    private boolean isTaskDone(String x) {
        int temp = Integer.parseInt(x);
        return temp != 0;
    }

    /**
     * The task to be deleted from the file.
     *
     * @param index The position of the task in the file.
     * @throws IOException If file does not exist.
     */
    public void deleteTask(int index) throws IOException {
        assert index >= 0 : "index should be greater or equal to 0";
        String tempFileUrl = "data/temp.txt";
        createTempFile(tempFileUrl);
        File tempFile = new File(tempFileUrl);
        File file = new File(this.FILE_URL);

        BufferedReader br = new BufferedReader(new FileReader(file));
        FileWriter fw = new FileWriter(tempFileUrl, true);

        String task;
        int counter = 0;

        while (true) {
            task = br.readLine();
            if (task == null) {
                br.close();
                fw.close();
                File temp = file;
                file.delete();
                tempFile.renameTo(temp);
                break;
            }

            if (counter != index) {
                fw.write(task + "\n");
            }
            counter++;
        }
    }

    /**
     * The task to be mark from the file.
     *
     * @param index The position of the task in the file.
     * @throws IOException If file does not exist.
     */
    public void markTask(int index) throws IOException {
        assert index >= 0 : "index should be greater or equal to 0";
        String tempFileUrl = "data/temp.txt";
        createTempFile(tempFileUrl);
        File tempFile = new File(tempFileUrl);
        File file = new File(this.FILE_URL);

        BufferedReader br = new BufferedReader(new FileReader(file));
        FileWriter fw = new FileWriter(tempFileUrl, true);

        String task;
        int counter = 0;

        while (true) {
            task = br.readLine();
            if (task == null) {
                br.close();
                fw.close();
                File temp = file;
                file.delete();
                tempFile.renameTo(temp);
                break;
            }

            if (counter != index) {
                fw.write(task + "\n");
            } else {
                String[] temp = task.split(",");
                temp[1] = "1";
                String result = String.join(",", temp);
                fw.write(result + "\n");
            }

            counter++;
        }
    }

    /**
     * The task to be unmark from the file.
     *
     * @param index The position of the task in the file.
     * @throws IOException If file does not exist.
     */
    public void unmarkTask(int index) throws IOException {
        assert index >= 0 : "index should be greater or equal to 0";
        String tempFileUrl = "data/temp.txt";
        createTempFile(tempFileUrl);
        File tempFile = new File(tempFileUrl);
        File file = new File(this.FILE_URL);

        BufferedReader br = new BufferedReader(new FileReader(file));
        FileWriter fw = new FileWriter(tempFileUrl, true);

        String task;
        int counter = 0;

        while (true) {
            task = br.readLine();
            if (task == null) {
                br.close();
                fw.close();
                File temp = file;
                file.delete();
                tempFile.renameTo(temp);
                break;
            }

            if (counter != index) {
                fw.write(task + "\n");
            } else {
                String[] temp = task.split(",");
                temp[1] = "0";
                String result = String.join(",", temp);
                fw.write(result + "\n");
            }

            counter++;
        }
    }

    /**
     * Writes task into storage
     *
     * @param task The task user inputted
     */
    public void writeTaskToStorage(Task task) {
        try {
            String prefix;
            String finalResult = "";
            assert task.type != null : "The type of task is not suppose to be null";

            switch (task.type) {
            case "todo":
                prefix = "T,0";
                finalResult = parseTask(prefix, task.description);
                break;
            case "deadline":
                prefix = "D,0";
                Deadline deadlineTemp = (Deadline) task;
                finalResult = parseTask(prefix, task.description,
                        deadlineTemp.time, deadlineTemp.date.toString());
                break;
            case "event":
                prefix = "E,0";
                Event eventTemp = (Event) task;
                finalResult = parseTask(prefix, task.description,
                        eventTemp.time, eventTemp.date.toString());
                break;
            default:
                break;
            }
            FileWriter fw = new FileWriter(this.FILE_URL, true);
            fw.write(finalResult + "\n");
            fw.close();
        } catch (IOException e) {
            ui.showError(e.getMessage());
        }
    }

    public void addTagToTask(int index, String tag) throws IOException {
        String tempFileUrl = "data/temp.txt";
        createTempFile(tempFileUrl);
        File tempFile = new File(tempFileUrl);
        File file = new File(this.FILE_URL);

        BufferedReader br = new BufferedReader(new FileReader(file));
        FileWriter fw = new FileWriter(tempFileUrl, true);

        String task;
        int counter = 0;

        while (true) {
            task = br.readLine();
            if (task == null) {
                br.close();
                fw.close();
                File temp = file;
                file.delete();
                tempFile.renameTo(temp);
                break;
            }

            if (counter != index) {
                fw.write(task + "\n");
            } else {
                task = task.concat(DELIMITER).concat(tag);
                fw.write(task + "\n");
            }

            counter++;
        }
    }

    private String parseTask(String prefix, String task) {
        return prefix.concat(DELIMITER).concat(task);
    }

    private String parseTask(String prefix, String task, String time, String date) {
        return prefix.concat(DELIMITER).concat(task).concat(DELIMITER).concat(date).concat(" ").concat(time);
    }

}
