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
    }


    /**
     * Return all task stored within File.
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
                if(data.contains("(by:")) {
                    data = data.substring(9);
                    String[] taskText = data.split("\\(by:");
                    StringBuilder sb = new StringBuilder(taskText[1].strip());
                    sb.deleteCharAt(sb.length()-1);
                    Deadline deadlineTask = new Deadline(taskText[0].strip(), sb.toString());
                    if(data.contains("[X]")) {
                        deadlineTask.setDone();
                    }
                    taskList.add(deadlineTask);
                } else if(data.contains("(at: ")) {
                    data = data.substring(9);
                    String[] taskText = data.split("\\(at:");
                    StringBuilder sb = new StringBuilder(taskText[1].strip());
                    sb.deleteCharAt(sb.length()-1);
                    Event eventTask = new Event(taskText[0].strip(),sb.toString());
                    if(data.contains("[X]")) {
                        eventTask.setDone();
                    }
                    taskList.add(eventTask);
                } else {
                    data = data.substring(9);
                    Todo todoTask = new Todo(data);
                    if(data.contains("[X]")) {
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
        String path = "src\\main\\java\\data\\duke.txt";
        path = path.replace("\\", "/");

        try {
            // Remove current file tasks
            PrintWriter pw = new PrintWriter(path);
            pw.close();
            File taskFile = new File(path);
            FileWriter myWriter = new FileWriter(taskFile,true);
            for(int i = 0; i < taskList.size(); i++) {
                myWriter.write((i + 1) + "." + taskList.get(i).toString() + "\r\n");
            }
            myWriter.close();
        } catch(NullPointerException | IOException fileInvalid) {
            System.out.println("File is Invalid!");
        }
    }
}



