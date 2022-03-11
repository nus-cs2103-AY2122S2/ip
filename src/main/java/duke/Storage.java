package duke;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * facilitates the storage and retrieval of data from the hard drive 
 */

public class Storage {

    private String path;

    /**
     * 
     * @param path contains the string pointing to the saved data
     */

    public Storage(String path) {
        this.path = path;
    }

    /**
     * to save the data into the hard drive
     * @param listOfTasks is the list of tasks that is to be saved in the hard drive
     */
    public void saveTask(ArrayList<Task> listOfTasks) {
        try {

            File f = new File(path);

            f.createNewFile();

            FileWriter fw = new FileWriter(f, false);
            for (Task task : listOfTasks) {
                fw.write(task.parseString());
                fw.write(System.lineSeparator());
            }
            fw.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * function to return tasks from the harddrive 
     * @return an arraylist of tasks from the hard drive
     */

    public ArrayList<Task> fetchTask() {

        File f = new File(path);
        ArrayList<Task> listOfTasks = new ArrayList<Task>();
        try {
            if (!f.exists()) {
                f.getParentFile().mkdirs();
                f.createNewFile();
            }
            Scanner scanner = new Scanner(f);

            while (scanner.hasNextLine()) {

                String input = scanner.nextLine();
                String[] data = input.split("\\|");
                boolean isDone = data[1].trim().equals("1");
                Task task;
                switch (data[0].trim()) {
                    case "T":
                        task = new ToDo(data[2].trim(), isDone);
                        listOfTasks.add(task);
                        break;
                    case "D":
                        task = new Event(data[2].trim(), data[3].trim(), isDone);
                        listOfTasks.add(task);
                        break;
                    case "E":
                        task = new Event(data[2].trim(), data[3].trim(), isDone);
                        listOfTasks.add(task);
                        break;
                }
            }
            scanner.close();

        } catch (IOException e) {
            e.printStackTrace();

        }
        return listOfTasks;
    }

}
