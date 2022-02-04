package heylo.tasks;

import java.util.ArrayList;

import heylo.storage.FilesReader;
import heylo.storage.FilesWriter;

/**
 * Represents a task added by the user.
 */
public abstract class Task {
    private static ArrayList<Task> taskList = new ArrayList<Task>();
    private static int taskCount = 0;
    protected String description;
    protected boolean isDone;

    /**
     * Sets description of child task classes.
     *
     * @param description String task-description.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Marks the task as done if it exists.
     *
     * @param index int Index of the task in the displayed list of tasks.
     */
    public static void markAsDone(int index) {
        try {
            taskList.get(index).isDone = true;
            System.out.println(" Well done!");
            System.out.println("  " + taskList.get(index).toString());
        } catch (IndexOutOfBoundsException e) {
            System.out.println(" You have not added any tasks yet.");
        }
    }

    /**
     * Marks the task as not done if it exists.
     *
     * @param index int Index of the task in the displayed list of tasks.
     */
    public static void markAsNotDone(int index) {
        try {
            taskList.get(index).isDone = false;
            System.out.println(" Oops! Fixed that for you.");
            System.out.println("  " + taskList.get(index).toString());
        } catch (IndexOutOfBoundsException e) {
            System.out.println(" You have not added any tasks yet.");
        }
    }

    /**
     * Adds the given task to the task list if valid and increments task count.
     *
     * @param task Task added by user.
     */
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

    /**
     * Removes the task from the task list if it exists.
     *
     * @param index int Index of the task in the displayed list of tasks.
     */
    public static void removeFromList(int index) {
        try {
            String outputTask = "  " + taskList.get(index).toString();
            taskList.remove(index);
            System.out.println(" Okay, I've deleted this task.");
            System.out.println(outputTask);
            taskCount--;
        } catch (IndexOutOfBoundsException e) {
            System.out.println(" This task does not exist.");
        }
    }

    /**
     * Populates the task list with the previously saved tasks.
     */
    public static void getSavedTasks() {
        taskList = FilesReader.getTaskListFromFile();
        taskCount = taskList.size();
    }

    /**
     * Saves the tasks in the task list for later retrieval.
     */
    public static void saveTasks() {
        for (Task task : taskList) {
            if (task instanceof Todo) {
                Todo todo = (Todo) task;
                FilesWriter.writeToFile("T : "
                        + (todo.isDone ? "1 : " : "0 : ")
                        + todo.description + "\n");
            } else if (task instanceof Event) {
                Event event = (Event) task;
                FilesWriter.writeToFile("E : "
                        + (event.isDone ? "1 : " : "0 : ")
                        + event.description + " : "
                        + event.duration + "\n");
            } else if (task instanceof Deadline) {
                Deadline deadline = (Deadline) task;
                FilesWriter.writeToFile("D : "
                        + (deadline.isDone ? "1 : " : "0 : ")
                        + deadline.description + " : "
                        + deadline.deadline + "\n");
            }
        }
    }

    /**
     * Converts the string format of the task to a Task object.
     *
     * @param task String task.
     * @return Task object.
     */
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
        default:
            return null;
        }
    }

    /**
     * Prints the list of tasks in the task list.
     */
    public static void findInList(String subString) {
        if (taskCount == 0) {
            System.out.println(" You have not added any tasks yet.");
            return;
        }

        int[] foundIndexes = new int[taskCount];
        int foundCount = 0;

        for (int i = 0; i < taskCount; i++) {
            if (taskList.get(i).description.contains(subString)) {
                foundIndexes[foundCount] = i;
                foundCount++;
            }
        }

        if (foundCount == 0) {
            System.out.println(" Sorry, I couldn't find any matching tasks :(");
        } else {
            System.out.println(" Here are the tasks you may be looking for!");
        }

        for (int i = 0; i < foundCount; i++) {
            System.out.println("   " + (i + 1) + "." + taskList.get(foundIndexes[i]).toString());
        }
    }

    /**
     * Prints all the existing tasks as a numbered list.
     */
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

    /**
     * Returns the done status of the task.
     *
     * @return String done status.
     */
    public String getStatusIcon() {
        return this.isDone ? "[X]" : "[ ]";
    }

    /**
     * Converts the task to string format.
     *
     * @return String task.
     */
    @Override
    public String toString() {
        return (this.getStatusIcon() + " " + this.description);
    }
}
