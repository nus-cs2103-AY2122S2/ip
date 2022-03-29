package duke;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Scanner;

public class FileHandler {

    public void handleSave(ArrayList<Task> taskList, String tasksPath, String tagsPath) {
        try {
            // Remove current file tasks
            PrintWriter pw = new PrintWriter(tasksPath);
            pw.close();
            File taskFile = new File(tasksPath);
            FileWriter myWriter = new FileWriter(taskFile, true);
            for (int i = 0; i < taskList.size(); i++) {
                myWriter.write((i + 1) + "." + taskList.get(i).toString() + "\r\n");
            }
            myWriter.close();
        } catch (NullPointerException | IOException fileInvalid) {
            System.out.println("File is Invalid!");
        }

        try {
            PrintWriter pw = new PrintWriter(tagsPath);
            pw.close();
            File tagFile = new File(tagsPath);
            FileWriter myWriter = new FileWriter(tagFile, true);
            for (int i = 0; i < taskList.size(); i++) {
                myWriter.write((i + 1) + "." + taskList.get(i).getTags() + "\r\n");
            }
            myWriter.close();
        } catch (NullPointerException | IOException fileInvalid) {
            System.out.println("ERROR!!");
        }
    }

    public void handleLoadTask(ArrayList<Task> taskList, String path) {
        try {
            File taskFile = new File(path);
            Scanner reader = new Scanner(taskFile);
            while (reader.hasNextLine()) {
                String data = reader.nextLine();
                if (data.contains("(by:")) {
                    loadDeadline(taskList, data);
                } else if (data.contains("(at: ")) {
                    loadEvent(taskList, data);
                } else {
                    loadTodo(taskList,data);
                }
            }
        } catch (Exception fileInvalid) {
            try {
                File file = new File(path);
                Files.createDirectories(Path.of(file.getParent()));
                Files.createFile(Path.of(path));
            } catch (Exception e) {
                System.out.println("Error");
            }
        }
    }

    public void loadDeadline(ArrayList<Task> taskList, String data) {
        data = data.substring(9);
        String[] taskText = data.split("\\(by:");
        StringBuilder sb = new StringBuilder(taskText[1].strip());
        sb.deleteCharAt(sb.length() - 1);
        Deadline deadlineTask = new Deadline(taskText[0].strip(), sb.toString());
        if (data.contains("[X]")) {
            deadlineTask.setDone();
        }
        taskList.add(deadlineTask);
    }

    public void loadEvent(ArrayList<Task> taskList, String data) {
        data = data.substring(9);
        String[] taskText = data.split("\\(at:");
        StringBuilder sb = new StringBuilder(taskText[1].strip());
        sb.deleteCharAt(sb.length() - 1);
        Event eventTask = new Event(taskText[0].strip(), sb.toString());
        if (data.contains("[X]")) {
            eventTask.setDone();
        }
        taskList.add(eventTask);
    }

    public void loadTodo(ArrayList<Task> taskList, String data) {
        data = data.substring(9);
        Todo todoTask = new Todo(data);
        if (data.contains("[X]")) {
            todoTask.setDone();
        }
        taskList.add(todoTask);
    }

    public void handleLoadTags(ArrayList<Task> taskList, String path) {
        try {
            File taskFile = new File(path);
            Scanner reader = new Scanner(taskFile);
            while (reader.hasNextLine()) {
                String data = reader.nextLine();
                int index = Integer.parseInt(data.substring(0,1));
                Task currentTask = taskList.get(index-1);
                data = data.substring(3,data.length()-1);
                if(!data.isEmpty()) {
                    String[] tags = data.split(",");
                    for (String tag : tags) {
                        String tagDescription = tag.strip();
                        currentTask.addTag(tagDescription);
                    }
                }
            }
        } catch(Exception fileInvalid) {
            try {
                File file = new File(path);
                Files.createDirectories(Path.of(file.getParent()));
                Files.createFile(Path.of(path));
            } catch (Exception e) {
                System.out.println("Error");
            }
        }
    }
}