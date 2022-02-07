package tasks;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import duke.DukeException;
import duke.Storage;

public class TaskList {
    private List<Task> tasks = new ArrayList<>();
    private Storage storage;

    /**
     * Constructor for TaskList.
     * @param file The file to load the saved lists of tasks from.
     * @throws DukeException
     */
    public TaskList(Storage storage, File file) throws DukeException {
        this.storage = storage;
        try {
            Scanner s = new Scanner(file);
            while (s.hasNext()) {
                String str = s.nextLine();
                String[] strings = str.split(",");
                Task task = null;
                String typeOfTask = strings[0];
                switch (typeOfTask) {
                case "E" :
                    task = new Event(str, true);
                    tasks.add(task);
                    break;
                case "D" :
                    task = new Deadline(str, true);
                    tasks.add(task);
                    break;
                case "T" :
                    task = new ToDo(str, true);
                    tasks.add(task);
                    break;
                default:
                    assert false : typeOfTask;
                }
                if (str.charAt(5) == 'X') {
                    task.setDone();
                }
            }
            s.close();
        } catch (FileNotFoundException e) {
            tasks = new ArrayList<Task>();
        }
    }

    /**
     * Constructor for TaskList when saved file has a loading error or does not exist.
     */
    public TaskList() {
        tasks = new ArrayList<>();
    }

    /**
     * Returns the user's current list of tasks.
     *
     * @return List of Tasks.
     */
    public List<Task> getTasks() {
        return this.tasks;
    }

    /**
     * Adds a task to the current list of tasks.
     *
     * @param task The task to be added.
     */
    public String addTask(Task task) throws IOException {
        tasks.add(task);
        storage.save(this);
        return "Added: " + task;
    }

    /**
     * Prints the current list of tasks.
     * @return A string containing the list of tasks.
     */
    public String printList() {
        String list = "";
        for (Task task : tasks) {
            list = list + task + "\n";
        }
        return list;
    }

    public String mark(int taskNumber) throws IOException {
        Task currTask = tasks.get(taskNumber - 1);
        currTask.setDone();
        storage.save(this);
        return "Nice! I've marked this task as done: \n" + "  " + currTask;
    }

    public String unmark(int taskNumber) throws IOException {
        Task currTask = tasks.get(taskNumber - 1);
        currTask.setNotDone();
        storage.save(this);
        return "OK, I've marked this task as not done yet:: \n" + "  " + currTask;
    }

    public String delete(int taskNumber) throws IOException {
        Task currTask = tasks.get(taskNumber - 1);
        tasks.remove(taskNumber - 1);
        storage.save(this);
        return "Okay, I have deleted " + currTask;
    }



    /**
     * Finds all the tasks containing the keyword(s) from user input.
     * @param str User input
     * @return A string that lists all the tasks containing the keyword(s).
     */
    public String find(String str) {
        String list = "";
        for (Task t : tasks) {
            if (t.toString().contains(str.substring(5))) {
                list = list + t;
            }
        }
        return list;
    }
}
