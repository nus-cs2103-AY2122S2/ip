package duke;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<Task>();
    }

    public void printList() {
        for(int i = 1; i <= tasks.size(); i++){
            Task task = tasks.get(i-1);
            System.out.print(i + ": ");
            task.printTask();
        }
    }

    public ArrayList<Task> getTasks() {
        return this.tasks;
    }

    public int getSize() {
        return tasks.size();
    }

    public void reset() {
        tasks.clear();
    }

    //Add from String input by user
    public void addTask(String input) {
        //identify type of task
        String[] arr = input.split(" ", 2);
        try {
            if (arr.length < 2) {
                throw new DukeException("Description of task cannot be empty!");
            }

            String taskType = arr[0];
            String taskDetails = arr[1];

            Task newTask = new Task("");

            if (taskType.equals("todo")) {
                newTask = new Todo(taskDetails);
            } else if (taskType.equals("deadline")) {
                String[] spl = taskDetails.split("/by");
                if (spl.length < 2) {
                    throw new DukeException("Description of deadline must include a date/time! Did you miss out a /by?");
                }
                String details = spl[0].trim();
                String dateTime = spl[1].trim();
                newTask = new Deadline(details, dateTime);
            } else if (taskType.equals("event")) {
                String[] spl = taskDetails.split("/at");
                if (spl.length < 2) {
                    throw new DukeException("Description of event must include a date/time! Did you miss out a /at?");
                }
                String details = spl[0].trim();
                String dateTime = spl[1].trim();
                newTask = new Event(details, dateTime);
            }

            tasks.add(newTask);
            System.out.println("Got it. The task has been added:");
            newTask.printTask();
            System.out.println("Now you have " + tasks.size() + " tasks in the list.");
        } catch (DukeException e) {
            System.out.println(e);
        }
    }

    //add task as input
    public void addTask(Task task) {
        tasks.add(task);
        System.out.println("Got it. The task has been added:");
        task.printTask();
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
    }

    //No print version of adding task for loading in from data.txt
    public void addTaskNoPrint(Task task) {
        tasks.add(task);
    }

    //Delete
    public void deleteTask(int index) {
        if (index > tasks.size() || index <= 0) {
            System.out.println("Index out of bounds, please try again");
        } else {
            tasks.remove(index - 1);
            printList();
        }
    }
    //Mark
    public void markTask(int index) {
        if (index > tasks.size() || index <= 0) {
            System.out.println("Index out of bounds, please try again");
        } else {
            tasks.get(index - 1).setDone(true);
            printList();
        }
    }

    //Unmark
    public void unmarkTask(int index) {
        if (index > tasks.size() || index <= 0) {
            System.out.println("Index out of bounds, please try again");
        } else {
            tasks.get(index - 1).setDone(false);
            printList();
        }
    }
}
