package funbox.util;

import java.io.*;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.util.HashMap;
import funbox.exception.FunBoxExceptions;
import funbox.task.Deadline;
import funbox.task.Event;
import funbox.task.Task;
import funbox.task.ToDo;

public class Storage {
    private final String DIRURL = "data/";
    private final String FILEURL = "data/tasks.txt";
    private final String TEMPFILEURL = "data/temp.txt";
    private final String DELIMITOR = ",";
    private Ui ui;
    private Parser parser;
    private TaskList taskList;
    HashMap<String, String> typeHashmap;

    public Storage(Ui ui, Parser parser) {
        this.ui = ui;
        this.parser = parser;
        this.typeHashmap = new HashMap<>();
        this.taskList = new TaskList();
        setUpHashMap();
        preCheck();
    }

    private void setUpHashMap() {
        this.typeHashmap.put("D", "deadline");
        this.typeHashmap.put("T", "todo");
        this.typeHashmap.put("E", "event");
    }

    private void preCheck() {
        if (!isDirectoryExist()) {
            boolean isDirectoryCreated = this.createDirectory();
            if (isDirectoryCreated) {
                this.ui.printDirSuccess();
                this.createFile(this.FILEURL);
            } else {
                this.ui.printDirAlreadyExist();
            }
        } else if (!isFileExist()) {
            this.createFile(this.FILEURL);
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
        return new File(this.DIRURL).isDirectory();
    }

    /**
     * Check if file tasks.txt exist in 'data'
     *
     * @return Return true if file exist, otherwise, false
     */
    private boolean isFileExist() {
        return Files.exists(Paths.get(this.FILEURL));
    }

    /**
     * Create directory 'data' under 'ip'
     *
     * @return Return true if directory exists, otherwise, false
     */
    private boolean createDirectory() {
        return new File(this.DIRURL).mkdir();
    }

    /**
     * Create file `tasks.txt` under 'data' directory
     */
    private void createFile(String fileUrl) {
        try {
            if (new File(fileUrl).createNewFile() && fileUrl == this.FILEURL) {
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
        File file = new File(this.FILEURL);
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
                }
            }

        }
        if (isDone == 1) {
            taskList.setPreTaskDone(counter);
        }
        counter++;
        return taskList;
    }

    public void deleteTask(int index) throws IOException {
        this.createFile(this.TEMPFILEURL);
        File tempFile = new File(this.TEMPFILEURL);
        File file = new File(this.FILEURL);
        BufferedReader br = new BufferedReader(new FileReader(file));
        FileWriter fw = new FileWriter(this.TEMPFILEURL, true);
        String task;
        int counter = 1;

        while (true) {
            task = br.readLine();

            if (task == null) {
                br.close();
                fw.close();
                file.delete();
                tempFile.renameTo(new File(this.FILEURL));
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
                        .concat(temp.date.toString()).concat(" ").concat(temp.time);
                break;
            case "event":
                Event temp2 = (Event) task;
                result = "E,0";
                result = result.concat(DELIMITOR).concat(temp2.description).concat(DELIMITOR)
                        .concat(temp2.date.toString()).concat(" ").concat(temp2.time);
                break;
            }
            FileWriter fw = new FileWriter(this.FILEURL, true);
            fw.write(result + "\n");
            fw.close();
        } catch (IOException e) {
            ui.showError(e.getMessage());
        }
    }
}
