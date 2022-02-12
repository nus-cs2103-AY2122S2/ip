package tasks;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import yeowoo.YeowooException;
import yeowoo.Storage;

public class TaskList {
    private List<Task> tasks = new ArrayList<>();
    private Storage storage;

    /**
     * Constructor for TaskList.
     * @param file The file to load the saved lists of tasks from.
     * @throws YeowooException
     */
    public TaskList(Storage storage, File file) throws YeowooException {
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

    /**
     * Marks the specified task as done.
     * @param taskNumber The taskNumber of the task to be marked.
     * @return A string detailing the task being marked as done.
     * @throws IOException
     */
    public String mark(int taskNumber) throws IOException {
        Task currTask = tasks.get(taskNumber - 1);
        currTask.setDone();
        storage.save(this);
        return "Nice! I've marked this task as done: \n" + "  " + currTask;
    }

    /**
     * Marks the specified task as not done.
     * @param taskNumber The taskNumber of the task to be marked.
     * @return A string detailing the task being marked as not done.
     * @throws IOException
     */
    public String unmark(int taskNumber) throws IOException {
        Task currTask = tasks.get(taskNumber - 1);
        currTask.setNotDone();
        storage.save(this);
        return "OK, I've marked this task as not done yet:: \n" + "  " + currTask;
    }

    /**
     * Deletes the specified task.
     * @param taskNumber The taskNumber of the task to be deleted.
     * @return A string detailing the task being deleted.
     * @throws IOException
     */
    public String delete(int taskNumber) throws IOException {
        Task currTask = tasks.get(taskNumber - 1);
        tasks.remove(taskNumber - 1);
        storage.save(this);
        return "Okay, I have deleted " + currTask;
    }

    /**
     * Prints the schedule of events on a specific date.
     * @param date Date of schedule specified by user input.
     * @return A String containing the schedule of events happening on the date.
     */
    public String printSchedule(String date) {
        LocalDate localDate = LocalDate.parse(date);
        String schedule = "Here are the list of events happening on " + date + "\n";
        for (Task t : tasks) {
            if (t instanceof Event) {
                if (((Event) t).getDate().equals(localDate)) {
                    schedule = schedule + t;
                }
            }
        }
        return schedule;
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
