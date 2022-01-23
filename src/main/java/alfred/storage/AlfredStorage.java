package alfred.storage;

import alfred.exceptions.InvalidIndexException;
import alfred.task.Task;
import alfred.ui.AlfredUserInterface;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class AlfredStorage {
    private ArrayList<Task> taskList;
    private String dataPath;

    public AlfredStorage(String dataPath) {
        this.taskList = new ArrayList<Task>();
        this.dataPath = dataPath;
    }

    public String listToString() {
        String out = "";
        for (int i = 1; i < this.taskList.size() + 1; i++) {
            out += i + ". " + this.taskList.get(i - 1).toString() + "\n";
        }
        return out;
    }

    public void markTask(int taskId) throws InvalidIndexException {
        this.checkValidListIndex(taskId);
        this.taskList.get(taskId).markComplete();
        this.saveToFile();
    }

    public void unmarkTask(int taskId) throws InvalidIndexException {
        this.checkValidListIndex(taskId);
        this.taskList.get(taskId).markIncomplete();
        this.saveToFile();
    }

    public void deleteTasK(int taskId) throws InvalidIndexException {
        this.checkValidListIndex(taskId);
        this.taskList.remove(taskId);
        this.saveToFile();
    }

    public void addTask(Task task, AlfredUserInterface ui) {
        this.taskList.add(task);
        String out = "Yes sir, I've added this task.\n";
        out += task.toString() + "\n";
        out += this.summarizeList();
        ui.sandwichAndPrint(out);
        this.saveToFile();
    }

    public String taskToString(int taskId) throws InvalidIndexException {
        this.checkValidListIndex(taskId);
        return this.taskList.get(taskId).toString();
    }

    private void checkValidListIndex(int taskId) throws InvalidIndexException {
        if (taskId >= this.taskList.size() || taskId < 0) {
            throw new InvalidIndexException();
        }
    }

    private String summarizeList() {
        return "Now you have " + this.taskList.size() + " task(s) in the your list.";
    }

    private void saveToFile() {
        FileWriter fw;
        // save to file
        try {
            System.out.println(new File(".").getCanonicalPath());
            fw = new FileWriter(this.dataPath);
            String newList = this.listToString();
            fw.write(newList);
            fw.close();
        } catch (FileNotFoundException fe) { // create if needed
            System.out.println("Alfred.txt not created. Will create at: " + this.dataPath);
            try {
                // create the file
                File output = new File(this.dataPath);
                output.createNewFile();
                fw = new FileWriter(output);
                String newList = this.listToString();
                fw.write(newList);
                fw.close();

            } catch (IOException ioe) {
                System.out.println(
                    "Something went wrong trying to save the file: " + ioe.getMessage());
            }
        } catch (IOException ioe) {
            System.out.println("Something went wrong trying to save the file: " + ioe.getMessage());
        }
    }
}
