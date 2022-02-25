package duke;

import java.util.ArrayList;
import java.util.Scanner;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;

/**
 * Class {@code Storage} for storing task into TaskList
 */
public class Storage {
    ArrayList<Task> taskList = new ArrayList<>();

    /**
     * Overloaded Constructor with filepath
     *
     * @param filePath filePath to File for storing
     */
    public Storage(String filePath) {
        loadTask(taskList, filePath);
        loadTags(taskList);
    }

    /**
     * Method to load all tags from File
     *
     * @param taskList Current session's TaskList
     */
    public void loadTags(ArrayList<Task> taskList) {
        String path = "src\\main\\java\\data\\tagList.txt";
        path = path.replace("\\", "/");
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
        } catch(NullPointerException fileInvalid) {
            System.out.println("File is Invalid!");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    /**
     * Return all task stored within File.
     *
     * @return ArrayList of current stored Task
     */
    public ArrayList<Task> getTaskList() {
        return this.taskList;
    }

    /**
     * Method to load all task from File, by supplied filePath.
     *
     * @param taskList Current session's TaskList
     * @param filePath FilePath for where File is stored.
     */
    public void loadTask(ArrayList<Task> taskList, String filePath) {
        String path = "src\\main\\java\\data\\duke.txt";
        path = path.replace("\\", "/");
        try {
            File taskFile = new File(path);
            Scanner reader = new Scanner(taskFile);
            while (reader.hasNextLine()) {
                String data = reader.nextLine();
                if (data.contains("(by:")) {
                    data = data.substring(9);
                    String[] taskText = data.split("\\(by:");
                    StringBuilder sb = new StringBuilder(taskText[1].strip());
                    sb.deleteCharAt(sb.length()-1);
                    Deadline deadlineTask = new Deadline(taskText[0].strip(), sb.toString());
                    if (data.contains("[X]")) {
                        deadlineTask.setDone();
                    }
                    taskList.add(deadlineTask);
                } else if (data.contains("(at: ")) {
                    data = data.substring(9);
                    String[] taskText = data.split("\\(at:");
                    StringBuilder sb = new StringBuilder(taskText[1].strip());
                    sb.deleteCharAt(sb.length()-1);
                    Event eventTask = new Event(taskText[0].strip(),sb.toString());
                    if (data.contains("[X]")) {
                        eventTask.setDone();
                    }
                    taskList.add(eventTask);
                } else {
                    data = data.substring(9);
                    Todo todoTask = new Todo(data);
                    if (data.contains("[X]")) {
                        todoTask.setDone();
                    }
                    taskList.add(todoTask);
                }
            }
        } catch(NullPointerException fileInvalid) {
            System.out.println("File is Invalid!");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Method to save all Task in given ArrayList of Tasks to File.
     *
     * @param taskList ArrayList of current session's Task.
     */
    // Reusable code for writing into duke.txt task list
    public void saveToTaskList(ArrayList<Task> taskList) {
        String tasksPath = "src\\main\\java\\data\\duke.txt";
        tasksPath = tasksPath.replace("\\", "/");

        String tagsPath = "src\\main\\java\\data\\tagList.txt";
        tagsPath = tagsPath.replace("\\", "/");
        try {
            // Remove current file tasks
            PrintWriter pw = new PrintWriter(tasksPath);
            pw.close();
            File taskFile = new File(tasksPath);
            FileWriter myWriter = new FileWriter(taskFile,true);
            for(int i = 0; i < taskList.size(); i++) {
                myWriter.write((i + 1) + "." + taskList.get(i).toString() + "\r\n");
            }
            myWriter.close();
        } catch(NullPointerException | IOException fileInvalid) {
            System.out.println("File is Invalid!");
        }

        try {
            PrintWriter pw = new PrintWriter(tagsPath);
            pw.close();
            File tagFile = new File(tagsPath);
            FileWriter myWriter = new FileWriter(tagFile,true);
            for(int i = 0; i < taskList.size(); i++) {
                myWriter.write((i + 1) + "." + taskList.get(i).getTags() + "\r\n");
            }
            myWriter.close();
        } catch(NullPointerException | IOException fileInvalid) {
            System.out.println("File is Invalid!");
        }

    }
}



