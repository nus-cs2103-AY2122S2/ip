package Alfred.Storage;

import Alfred.Exceptions.InvalidIndexException;
import Alfred.Task.Task;
import Alfred.UI.AlfredUserInterface;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AlfredStorage {
    private ArrayList<Task> taskList;
    private String dataPath;

    public AlfredStorage(String dataPath) {
        this.taskList = new ArrayList<Task>();
        this.dataPath = dataPath;
    }

    public String listToString() {
        return this.listToString(this.taskList);
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

    public String find(String text) {

        ArrayList<Task> matches = new ArrayList<Task>();
        // iterate through arraylist to find match
        for (int i = 0; i < this.taskList.size(); i++) {
            Task task = this.taskList.get(i);
            if (task.match(text)) {
                matches.add(task);
            }
        }
        if (matches.size() == 0) {
            return "None found.";
        } else {
            return this.listToString(matches);
        }
    }

    public String taskToString(int taskId) throws InvalidIndexException {
        this.checkValidListIndex(taskId);
        return this.taskList.get(taskId).toString();
    }

    private String listToString(ArrayList<Task> taskList) {
        String out = "";
        for (int i = 1; i < taskList.size() + 1; i++) {
            out += i + ". " + taskList.get(i - 1).toString() + "\n";
        }
        return out;
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
