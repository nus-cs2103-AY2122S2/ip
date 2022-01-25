package duke.storage;

import duke.tasks.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    protected File directory = new File("data");
    protected File file = new File("data/tasks.txt");

    public Storage() {
        if (!directory.exists()) {
            directory.mkdir();
        }

        try {
            file.createNewFile();
        } catch (IOException e) {
            System.out.println("Error creating storage file! You data will not be stored.");
        }
    }

    public ArrayList<Task> retrieveTaskList() throws FileNotFoundException {
        ArrayList<Task> tasks = new ArrayList<Task>();
        Scanner sc = new Scanner(file);
        while (sc.hasNextLine()) {
            tasks.add(stringToTask(sc.nextLine()));
        }
        return tasks;
    }

    public void saveTaskList(ArrayList<Task> tasks) throws IOException {
        FileWriter fw = new FileWriter(file);
        for (Task t: tasks) {
            fw.write(taskToString(t) + System.lineSeparator());
        }
        fw.close();
    }

    private Task stringToTask(String task) {
        char type = task.charAt(0);
        boolean isDone = task.charAt(1) == '1';
        String[] detail = task.substring(2).split(" \\| ");
        switch (type) {
            case 'T':
                return new ToDo(detail[0], isDone);
            case 'E':
                return new Event(detail[0], detail[1], isDone);
            case 'D':
                return new Deadline(detail[0], detail[1], isDone);
            default:
                return new Task("!!Looks like something went wrong with this task.");
        }
    }

    private String taskToString(Task task) {
        String isDone = task.isDone ? "1" : "0";
        if (task instanceof ToDo) {
            return "T" + isDone + task.description;
        } else if (task instanceof Deadline) {
            return "D" + isDone + task.description + " | " + ((Deadline) task).by;
        } else if (task instanceof Event) {
            return "E" + isDone + task.description + " | " + ((Event) task).at;
        } else {
            return "";
        }
    }
}
