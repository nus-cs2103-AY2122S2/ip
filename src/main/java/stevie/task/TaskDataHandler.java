package stevie.task;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import stevie.exception.TaskException;
/**
 * Loads and saves Tasks added by stevie.Stevie chat bot.
 */
public class TaskDataHandler {
    private final String path;

    public TaskDataHandler(String path) {
        this.path = path;
    }

    /**
     * Saves Tasks into a text file.
     *
     * @param tasks an arraylist of Tasks to be saved
     */
    public void saveTasks(ArrayList<Task> tasks) {
        try {
            FileWriter fw = new FileWriter(path);
            BufferedWriter bw = new BufferedWriter(fw);
            for (Task task : tasks) {
                bw.write(task.generateTaskSaveData());
                bw.newLine();
            }
            bw.flush();
            bw.close();
            fw.close();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    /**
     * Loads Tasks from a text file and creates an arraylist of Tasks.
     *
     * @return an arraylist of Tasks
     */
    public ArrayList<Task> loadTasks() {
        try {
            FileReader fr = new FileReader(path);
            BufferedReader br = new BufferedReader(fr);
            String line;
            ArrayList<Task> taskList = new ArrayList<>();
            while ((line = br.readLine()) != null) {
                TaskType type = TaskCreator.charToType(line.charAt(0));
                String[] splits = line.split("\\|");
                boolean done = splits[1] == "1";
                Date date = splits.length > 3
                        ? new SimpleDateFormat("dd/MM/yyyy HH:mm").parse(splits[3])
                        : null;
                taskList.add(TaskCreator.create(type, done, splits[2], date));
            }
            return taskList;
        } catch (IOException | TaskException | ParseException ex) {
            return new ArrayList<>();
        }
    }

}
