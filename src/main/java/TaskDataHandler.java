import java.io.*;
import java.util.ArrayList;

/**
 * Loads and saves Tasks added by Stevie chat bot.
 */
public class TaskDataHandler {
    private static String path = "src" + File.separator + "main"
            + File.separator + "data" + File.separator + "tasks.txt";

    /**
     * Saves Tasks into a text file.
     *
     * @param tasks an arraylist of Tasks to be saved
     */
    public static void saveTasks(ArrayList<Task> tasks) {
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
    public static ArrayList<Task> loadTasks() {
        try {
            FileReader fr = new FileReader(path);
            BufferedReader br = new BufferedReader(fr);
            String line = null;
            ArrayList<Task> taskList = new ArrayList<>();
            while ((line = br.readLine()) != null) {
                char eventChar = line.charAt(0);
                String[] splits = line.split("\\|");
                Task task;
                switch (eventChar) {
                case 'T':
                    task = new ToDoTask(splits[2]);
                    break;
                case 'E':
                    task = new EventTask(splits[2], splits[3]);
                    break;
                case 'D':
                    task = new DeadlineTask(splits[2], splits[3]);
                    break;
                default:
                    throw new TaskListException("There is not such task!");
                }
                if (splits[1] == "1") {
                    task.done();
                }
                taskList.add(task);
            }
            return taskList;
        } catch (IOException | TaskListException ex) {
            return new ArrayList<>();
        }
    }

}
