package duke.utility;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.io.*;
import java.util.ArrayList;
import java.util.List;


public class Storage {

    private String path;

    public Storage(String path) {
        this.path = path;
    }

    public List<Task> readAllTasks() {
        List<Task> tasks = new ArrayList<>();
        try {
            File databaseFile = new File(path);
            databaseFile.getParentFile().mkdirs();
            databaseFile.createNewFile();
            BufferedReader reader = new BufferedReader(new FileReader(databaseFile));
            String line;
            while ((line = reader.readLine()) != null) {
                Task task;
                String[] taskStr = line.split(",");
                task = storageLineParser(taskStr);
                tasks.add(task);
            }
        } catch (IOException e) {
            System.err.println("Read error! ");
        }
        return tasks;
    }
    public List<Task> query(String queryKey) {
        List<Task> tasks = new ArrayList<>();
        try {
            File file = new File(path);
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;
            while ((line = reader.readLine()) != null) {
                Task task;
                if (line.contains(queryKey)) {
                    String[] taskStr = line.split(",");
                    task = storageLineParser(taskStr);
                    tasks.add(task);
                }
            }
        } catch (IOException e) {
            System.err.println("Read got errors");
        }
        return tasks;
    }

    public Task storageLineParser(String[] taskString) {
        Task t;
        switch (taskString[0]) {
            case "T": {
                t = new Todo(taskString[2], taskString[1]);
                break;
            }
            case "E": {
                t = new Event(taskString[1], taskString[2], taskString[3]);
                break;
            }
            case "D": {
                t = new Deadline(taskString[1], taskString[2], taskString[3]);
                break;
            }
            default: {
                t = null;
                break;
            }
        }
        return t;
    }

    public void update(List<Task> tasks) {
        try {
            File file = new File(path);
            FileWriter writer = new FileWriter(file);
            for (Task task: tasks) {
                String[] formatTask = formatTask(task);
                writer.append(String.join(",", formatTask));
                writer.append('\n');
            }
            writer.flush();
            writer.close();
        } catch (IOException e) {
            System.err.println("Creation error! ");
        }
    }

    public String[] formatTask(Task task) {
        String[] formatTask = new String[] {
                task.getTaskType(),
                task.getStatus() ? "1" : "0",
                task.getTask(),
                task.getTime().isEmpty() ? "" : task.getTime().get()
        };
        return formatTask;
    }



}