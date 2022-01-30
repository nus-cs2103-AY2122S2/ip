package funbox.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
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
    private final String TEMPFILE_URL = "data/temp.txt";
    private final String DELIMITOR = ",";
    private Ui ui;
    private Parser parser;
    private TaskList taskList;
    private HashMap<String, String> typeHashmap;

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
        preCheck();
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
     * Checks if the required directory and file exists, and reads tasks from the file and
     * insert it into a list.
     */
    private void preCheck() {
        if (!isDirectoryExist()) {
            boolean isDirectoryCreated = this.createDirectory();
            if (isDirectoryCreated) {
                this.ui.printDirSuccess();
                this.createFile(this.FILE_URL);
            } else {
                this.ui.printDirAlreadyExist();
            }
        } else if (!isFileExist()) {
            this.createFile(this.FILE_URL);
        } else {
            this.ui.printLoadData();
            try {
                this.taskList = this.readFile();
            } catch (IOException | FunBoxExceptions e) {
                ui.showError(e.getMessage());
            }
        }
    }

    /**
     * Checks if directory 'data' exists in 'ip' folder
     *
     * @return Return true if directory exist, otherwise, false
     */
    private boolean isDirectoryExist() {
        return new File(this.DIR_URL).isDirectory();
    }

    /**
     * Check if file tasks.txt exist in 'data'
     *
     * @return Return true if file exist, otherwise, false
     */
    private boolean isFileExist() {
        return Files.exists(Paths.get(this.FILE_URL));
    }

    /**
     * Creates directory 'data' under 'ip'
     *
     * @return Return true if directory exists, otherwise, false
     */
    private boolean createDirectory() {
        return new File(this.DIR_URL).mkdir();
    }

    /**
     * Creates file `tasks.txt` under 'data' directory
     */
    private void createFile(String fileUrl) {
        try {
            if (new File(fileUrl).createNewFile() && fileUrl == this.FILE_URL) {
                this.ui.printFileSuccess();
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

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
        BufferedReader br = new BufferedReader(new FileReader(file));
        String task;
        TaskList taskList = new TaskList();
        String[] resultArr;
        int isDone = 0;
        int counter = 0;

        while (true) {
            task = br.readLine();

            if (task == null) {
                br.close();
                break;
            } else {
                String[] taskSplit = task.split(this.DELIMITOR);
                String type = this.typeHashmap.get(taskSplit[0]);
                isDone = Integer.parseInt(taskSplit[1]);
                String description = taskSplit[2];

                switch (type) {
                case "todo":
                    taskList.add(new ToDo(description, type));
                    break;
                case "event":
                    taskList.add(new Event(description,
                            parser.stringToLocalDate(taskSplit[3]),
                            parser.getTime(taskSplit[3]), type));
                    break;
                case "deadline":
                    taskList.add(new Deadline(description,
                            parser.stringToLocalDate(taskSplit[3]),
                            parser.getTime(taskSplit[3]), type));
                    break;
                default:
                    break;
                }
            }

        }
        if (isDone == 1) {
            taskList.setPreTaskDone(counter);
        }
        counter++;
        return taskList;
    }

    /**
     * The task to be deleted from the file.
     *
     * @param index The position of the task in the file.
     * @throws IOException If file does not exist.
     */
    public void deleteTask(int index) throws IOException {
        this.createFile(this.TEMPFILE_URL);
        File tempFile = new File(this.TEMPFILE_URL);
        File file = new File(this.FILE_URL);
        BufferedReader br = new BufferedReader(new FileReader(file));
        FileWriter fw = new FileWriter(this.TEMPFILE_URL, true);
        String task;
        int counter = 1;

        while (true) {
            task = br.readLine();

            if (task == null) {
                br.close();
                fw.close();
                file.delete();
                tempFile.renameTo(new File(this.FILE_URL));
                break;
            } else {
                if (counter != index) {
                    fw.write(task + "\n");
                }
            }
            counter++;
        }
    }

    /**
     * Writes task into storage
     *
     * @param task The task user inputted
     */
    public void writeTaskToStorage(Task task, Ui ui) {
        try {
            String result = "";
            switch (task.type) {
            case "todo":
                result = "T,0";
                result = result.concat(DELIMITOR).concat(task.description);
                break;
            case "deadline":
                Deadline temp = (Deadline) task;
                result = "D,0";
                result = result.concat(DELIMITOR).concat(temp.description).concat(DELIMITOR)
                        .concat(temp.getDate().toString()).concat(" ").concat(temp.getTime());
                break;
            case "event":
                Event temp2 = (Event) task;
                result = "E,0";
                result = result.concat(DELIMITOR).concat(temp2.description).concat(DELIMITOR)
                        .concat(temp2.date.toString()).concat(" ").concat(temp2.time);
                break;
            default:
                break;
            }
            FileWriter fw = new FileWriter(this.FILE_URL, true);
            fw.write(result + "\n");
            fw.close();
        } catch (IOException e) {
            ui.showError(e.getMessage());
        }
    }
}
