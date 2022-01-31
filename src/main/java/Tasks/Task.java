package tasks;

import fileHandling.FilesReader;
import fileHandling.FilesWriter;

import java.util.ArrayList;

public abstract class Task {
    protected String description;
    protected boolean isDone;
    private static ArrayList<Task> taskList = new ArrayList<Task>();
    private static int taskCount = 0;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return this.isDone ? "[X]" : "[ ]";
    }

    public static void markAsDone(int index) {
        try {
            taskList.get(index).isDone = true;
            System.out.println(" Well done!");
            System.out.println("  " + taskList.get(index).toString());
        } catch (IndexOutOfBoundsException e) {
            System.out.println(" You have not added any tasks yet.");
        }
    }

    public static void markAsNotDone(int index) {
        try {
            taskList.get(index).isDone = false;
            System.out.println(" Oops! Fixed that for you.");
            System.out.println("  " + taskList.get(index).toString());
        } catch (IndexOutOfBoundsException e) {
            System.out.println(" You have not added any tasks yet.");
        }
    }

    public static void addToList(Task task) {
        if (task.description == null) {
            System.out.println(" Please enter the description as well!");
            System.out.println(" Command format: task-type task-description /by task-duration OR /at task-deadline");
            return;
        }

        if (task instanceof Event) {
            Event event = (Event) task;
            if (event.duration == null) {
                System.out.println(" Please enter the duration as well!");
                System.out.println(" Command format: task-type task-description /by YYYY-MM-DD");
                return;
            }
        }

        if (task instanceof Deadline) {
            Deadline taskWithDeadline = (Deadline) task;
            if (taskWithDeadline.deadline == null) {
                System.out.println(" Please enter the deadline as well!");
                System.out.println(" Command format: task-type task-description /at YYYY-MM-DD");
                return;
            }
        }

        if (taskCount == 0) {
            System.out.println(" Added! Now you have 1 item in your tasks list.");
        } else {
            System.out.println(" Added! Now you have " + (taskCount + 1) + " items in your tasks list.");
        }
        taskList.add(task);
        System.out.println("  " + taskList.get(taskCount).toString());
        taskCount++;
    }

    public static void removeFromList(int index) {
        try {
            taskList.remove(index);
            System.out.println(" Okay, I've deleted this task.");
            System.out.println("  " + taskList.get(index).toString());
            taskCount--;
        } catch (IndexOutOfBoundsException e) {
            System.out.println(" You have not added any tasks yet.");
        }
    }

    public static void getSavedTasks() {
        taskList = FilesReader.getTaskListFromFile();
        taskCount = taskList.size();
    }

    public static void saveTasks() {
        for (Task task : taskList) {
            if (task instanceof Todo) {
                Todo todo = (Todo) task;
                FilesWriter.writeToFile("T : " + (todo.isDone ? "1 : " : "0 : ") + todo.description + "\n");
            } else if (task instanceof Event) {
                Event event = (Event) task;
                FilesWriter.writeToFile("E : " + (event.isDone ? "1 : " : "0 : ") + event.description + " : " + event.duration + "\n");
            } else if (task instanceof Deadline) {
                Deadline deadline = (Deadline) task;
                FilesWriter.writeToFile("D : " + (deadline.isDone ? "1 : " : "0 : ") + deadline.description + " : " + deadline.deadline + "\n");
            }
        }
    }

    public static Task convertStringToTask(String task) {
        String[] input = task.split(" : ");
        switch (input[0]) {
        case "T":
            Todo todo = new Todo(input[2]);
            todo.isDone = Integer.parseInt(input[1]) == 1;
            return todo;
        case "E":
            Event event = new Event(input[2], input[3]);
            event.isDone = Integer.parseInt(input[1]) == 1;
            return event;
        case "D":
            Deadline deadline = new Deadline(input[2], input[3]);
            deadline.isDone = Integer.parseInt(input[1]) == 1;
            return deadline;
        }
        return null;
    }

    public static void printAllTasks() {
        if (taskCount == 0) {
            System.out.println(" You have not added any tasks yet.");
            return;
        }
        System.out.println(" Here are your tasks. Make things happen!");
        for (int i = 0; i < taskCount; i++) {
            System.out.println("   " + (i + 1) + "." + taskList.get(i).toString());
        }
    }


    @Override
    public String toString() {
        return (this.getStatusIcon() + " " + this.description);
    }
}
