package duke.tasklist;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.lang.IndexOutOfBoundsException;

import duke.task.Tasks;
import duke.task.Deadlines;
import duke.task.Events;
import duke.task.Todos;
import duke.storage.Storage;

public class TaskList {

    private ArrayList<Tasks> taskList;

    public TaskList(ArrayList<Tasks> taskList) {
        this.taskList = new ArrayList<Tasks>();
        this.taskList.addAll(taskList);
    }

    public TaskList() {
        this.taskList = new ArrayList<Tasks>();
    }

    // Find and return Task based on index, arraylist bypass null issue
    ArrayList<Tasks> findsTask(String filePath, int taskIndexToFind) {
        ArrayList<Tasks> filteredTaskList = new ArrayList<Tasks>();
        try {
            Tasks taskData = taskList.get(taskIndexToFind);
            filteredTaskList.add(taskData);
        } catch (IndexOutOfBoundsException err) {
            System.out.println("    Don't access a duke.task beyond the numeral boundary.");
        }
        return filteredTaskList;
    }

    // Delete duke.task -> returns duke.task deleted, then returns string to append
    public Boolean addsTask(Tasks task, Storage storage) throws IndexOutOfBoundsException {
        try {
            if (storage.appendsToDatabase(task.toDatabaseString())) {
                this.taskList.add(task);
                System.out.println("    Task has been successfully added:");
                System.out.println("       " + task.toString() + "\n");
                System.out.println("    There are " + fileContentCounter() + " tasks in the list.");
                return true;
            }
        } catch (IndexOutOfBoundsException err) {
            System.out.println("    Task index out of bound while deleting duke.task.");
        } catch (FileNotFoundException err) {
            System.out.println("    Addition of tasks unsuccessful due to: " + err);
        } catch (IOException err) {
            System.out.println("    Addition of tasks unsuccessful due to: " + err);
        }
        return false;
    }

    // Edit duke.task
    public Boolean marksTask(Storage storage, int taskIndexToMark, boolean taskCompletion)
            throws IndexOutOfBoundsException {
        ArrayList<Tasks> newList = new ArrayList<Tasks>();
        StringBuilder sb1 = new StringBuilder("");
        try {
            newList.addAll(this.taskList);
            Tasks editTask = newList.get(taskIndexToMark);
            if (taskCompletion) {
                newList.set(taskIndexToMark, editTask.completeTask());
            } else {
                newList.set(taskIndexToMark, editTask.uncompleteTask());
            }

            for (int i = 0; i < newList.size(); i++) {
                sb1.append(newList.get(i).toDatabaseString());
            }

            if (storage.writesToDatabase(sb1.toString())) {
                this.taskList.clear();
                this.taskList.addAll(newList);
                System.out.println("     Nice! I've " + (taskCompletion ? "marked this duke.task as completed"
                        : "unmarked this duke.task as uncompleted"));
                System.out.println("     " + (taskCompletion ? editTask.completeTask().toString()
                        : editTask.uncompleteTask().toString()));
                return true;
            }
        } catch (IndexOutOfBoundsException err) {
            System.out.println("    Task index out of bound while marking duke.task.");
        } catch (FileNotFoundException err) {
            System.out.println("    Marking of tasks unsuccessful due to: " + err);
        }
        return false;
    }

    // Delete duke.task -> returns duke.task deleted, then returns string to append
    public ArrayList<Tasks> deletesTask(int taskIndexToDelete, Storage storage) throws IndexOutOfBoundsException {
        ArrayList<Tasks> newList = new ArrayList<Tasks>();
        ArrayList<Tasks> returnInfo = new ArrayList<Tasks>();
        StringBuilder sb1 = new StringBuilder("");
        try {
            newList.addAll(this.taskList);
            Tasks deletedTask = newList.get(taskIndexToDelete);
            newList.remove(taskIndexToDelete);

            for (int i = 0; i < newList.size(); i++) {
                sb1.append(newList.get(i).toDatabaseString());
            }

            if (storage.writesToDatabase(sb1.toString())) {
                returnInfo.add(this.taskList.get(taskIndexToDelete));
                this.taskList.clear();
                this.taskList.addAll(newList);
                System.out.println("    I have successfully removed the duke.task from the system:");
                System.out.println("       " + deletedTask.toString() + "\n");
                System.out.println(
                        String.format("    You have %s tasks left in your list. :)", fileContentCounter()));
            }
        } catch (IndexOutOfBoundsException err) {
            System.out.println("    Task index out of bound while deleting duke.task.");
        } catch (FileNotFoundException err) {
            System.out.println("    Deleting of tasks unsuccessful due to: " + err);
        }
        return returnInfo;
    }

    // Print file content method
    public int fileContentCounter() {
        return taskList.size();
    }

    // Print file content method
    public Boolean printFileContent() {
        try {
            StringBuilder sb1 = new StringBuilder("");
            sb1.append("    Here are the tasks in your list:" + "\n");
            for (int i = 0; i < taskList.size(); i++) {
                sb1.append("       " + (i + 1) + ". " + taskList.get(i).toString() + "\n");
            }
            sb1.append(String.format("\n    You have %s tasks in your list! :)", fileContentCounter()));
            System.out.println(sb1.toString());
            return true;
        } catch (OutOfMemoryError err) {
            System.out.println("Out of Memory Error printing file content: " + err);
            return false;
        }
    }

    // Print file content method - accept only T, E, and D for tasks
    ArrayList<Tasks> filterTasks(String taskType) {
        ArrayList<Tasks> filteredList = new ArrayList<Tasks>();
        for (int i = 0; i < taskList.size(); i++) {
            Tasks currentTask = taskList.get(i);
            if (taskType.equals("T") && currentTask instanceof Todos) {
                filteredList.add(currentTask);
            } else if (taskType.equals("E") && currentTask instanceof Events) {
                filteredList.add(currentTask);
            } else if (taskType.equals("D") && currentTask instanceof Deadlines) {
                filteredList.add(currentTask);
            }
        }
        return filteredList;
    }
}
