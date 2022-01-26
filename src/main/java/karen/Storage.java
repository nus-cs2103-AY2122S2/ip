package karen;

import karen.task.Deadline;
import karen.task.Event;
import karen.task.Task;
import karen.task.ToDo;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Loads, manages and subsequently saves user's task list
 */
public class Storage {
    public static final String DATA_DIR = "./data/";
    public static final String DATA_PATH = "./data/karen.txt";

    public static final String NO_FILE_MESSAGE = "No previous session/data found";
    public static final String ERR_FILE_MESSAGE = "Something went wrong with reading the previous session..";

    private ArrayList<Task> taskList;
    public Ui ui;

    public Storage(Ui ui) {
        this.ui = ui;
        this.taskList = this.loadTasks();
    }

    public int getTaskCount() {
        return this.taskList.size();
    }

    private ArrayList<Task> loadTasks() {
        ArrayList<Task> taskList = new ArrayList<>();
        BufferedReader br = null;
        try {
            FileInputStream readStream = new FileInputStream(DATA_PATH);
            DataInputStream in = new DataInputStream(readStream);
            br = new BufferedReader(new InputStreamReader(in));

            String readLine;
            while ((readLine=br.readLine()) != null) {
                String[] data = readLine.split("\\|");
                Task item = this.createTask(data[0], Arrays.copyOfRange(data, 2, data.length));
                if (Boolean.parseBoolean(data[1])) {
                    item.markDone();
                }
                taskList.add(item);
            }
            in.close();
        }
        catch (FileNotFoundException err) {
            this.ui.displayWarning(NO_FILE_MESSAGE);
            File dir = new File(DATA_DIR);
            if (!dir.exists()) {
                dir.mkdirs();
            }
            return taskList;
        }
        catch (IOException err) {
            this.ui.displayWarning(ERR_FILE_MESSAGE);
            return taskList;
        }
        finally {
            try {
                br.close();
            } catch (Exception err) {
                // do nothing
            }
        }
        return taskList;
    }

    protected void saveTasks() {
        // formatting data for writing
        String data = "";
        for (Task item: this.taskList) {
            data = data.concat(String.format("%s\n", item.toSaveData()));
        }

        // writing data to local dir
        Writer writer = null;
        try {
            FileOutputStream writeStream = new FileOutputStream(DATA_PATH);
            OutputStreamWriter out = new OutputStreamWriter(writeStream);
            writer = new BufferedWriter(out);
            writer.write(data);
        }
        catch (FileNotFoundException err) {
//            this.echoWarning(String.format("Improper access for file writing.\n\tCheck if %s exists.",DATA_DIR));
        }
        catch (IOException err) {
//            this.echoWarning("Something went wrong with writing to file");
        }
        finally {
            try {
                writer.close();
            }
            catch (Exception err) {
                // do nothing
            }
        }
    }

    public Task createTask(String taskType, String[] taskArgs) {
        Task initTask;

        switch (taskType) {
            case "T":
                initTask = new ToDo(taskArgs[0]);
                break;
            case "D":
                initTask = new Deadline(taskArgs[0], taskArgs[1]);
                break;
            case "E":
                initTask = new Event(taskArgs[0], taskArgs[1]);
                break;
            default:
                initTask = null;
                break;
        }
        return initTask;
    }

    public Task getTask(int index) throws IndexOutOfBoundsException {
        return this.taskList.get(index);
    }

    public ArrayList<Task> getTaskList() {
        return this.taskList;
    }

    public void addTask(Task item) {
        this.taskList.add(item);
    }

    public void deleteTask(int index) {
        this.taskList.remove(index);
    }


}
