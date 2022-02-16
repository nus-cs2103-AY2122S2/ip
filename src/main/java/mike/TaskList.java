package mike;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Handles the operations on the list of tasks managed by the app.
 */
public class TaskList {
    private final List<Task> listOfTasks;

    /**
     * Constructor for TaskList
     */
    public TaskList() {
        this.listOfTasks = new ArrayList<>();
    }

    /**
     * Constructor for TaskList
     *
     * @param listOfTasks
     */
    public TaskList(List<Task> listOfTasks) {
        this.listOfTasks = listOfTasks;
    }

    /**
     * Returns a string representation of the tasks that can be output for the user.
     *
     * @return String representing the full list of tasks.
     */
    protected String getListForPrint() {
        int counter = 1;
        StringBuilder sb = new StringBuilder();
        sb.append("Behold, your list!\n");
        for (Task task : listOfTasks) {
            sb.append(String.format("%d. %s\n", counter, task));
            counter++;
        }
        return sb.toString();
    }

    /**
     * Returns a String to be printed acknowledging that the user has added a task to the list.
     *
     * @param task Task that has been added to the list
     * @return String to be printed that acknowledges the new list item.
     */
    protected String addToListWithMessage(Task task) {
        this.listOfTasks.add(task);
        String taskName = task.getTaskName();
        String addTaskOutput = "Added \"" + taskName + "\" to the list!";
        int noOfTasks = this.listOfTasks.size();
        String noOfTasksOutput = String.format("You now have *%d* task(s) in your list.", noOfTasks);
        return String.format("%s\n%s", addTaskOutput, noOfTasksOutput);
    }

    /**
     * Removes the task (specified by its index) from the list and returns a String message.
     *
     * @param removeIndex The index of the task to be removed from the list.
     * @return String to be printed that acknowledges the removal of the list item.
     */
    protected String removeFromListWithMessage(int removeIndex) {
        Task removedTask = this.listOfTasks.remove(removeIndex - 1);
        String taskName = removedTask.getTaskName();
        String removeTaskOutput = "Removed \"" + taskName + "\" from the list!";
        int noOfTasks = this.listOfTasks.size();
        String noOfTasksOutput = String.format("You now have *%d* task(s) in your list.", noOfTasks);
        return String.format("%s\n%s", removeTaskOutput, noOfTasksOutput);
    }

    /**
     * Marks the task (specified by its index) as done and returns a String message.
     *
     * @param indexFromUser The index of the task to be marked in the list.
     * @return String to be printed that acknowledges the marking of the list item.
     */
    protected String markInListWithMessage(int indexFromUser) {
        int indexInList = indexFromUser - 1;
        assert indexInList >= 0 : "index should be more than or equal to 0";
        Task oldTask = this.listOfTasks.get(indexInList);
        Task newTask = oldTask.markAsDone();
        this.listOfTasks.set(indexInList, newTask);

        String outputMessage =
                String.format("Great job, \"%s\" marked as done!", newTask.getTaskName());
        return outputMessage;
    }

    /**
     * Marks the task (specified by its index) as undone and returns a String message.
     *
     * @param indexFromUser The index of the task to be marked in the list.
     * @return String to be printed that acknowledges the unmarking of the list item.
     */
    protected String unmarkInListWithMessage(int indexFromUser) {
        int indexInList = indexFromUser - 1;
        assert indexInList >= 0 : "index should be more than or equal to 0";
        Task oldTask = this.listOfTasks.get(indexInList);
        Task newTask = oldTask.markAsUndone();
        this.listOfTasks.set(indexInList, newTask);

        String outputMessage =
                String.format("Sure thing, \"%s\" marked as undone!", newTask.getTaskName());
        return outputMessage;
    }

    /**
     * Returns a String representing the tasks that the user is searching for.
     *
     * @return String with the tasks the user is looking for.
     */
    protected String findTasksInListForPrint(String searchWords) {
        int counter = 1;
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("Behold, the items containing \"%s\"!\n", searchWords));
        for (Task task : listOfTasks) {
            if (task.containsSearchWords(searchWords)) {
                sb.append(String.format("%d. %s\n", counter, task));
            }
            counter++;
        }
        return sb.toString();
    }

    /**
     * Returns a String representing the TaskList that can be stored.
     *
     * @return String representation of TaskList for storage.
     */
    protected String convertToStoredListFormat() {
        StringBuilder sb = new StringBuilder();
        for (Task task : listOfTasks) {
            sb.append(task.convertToStoredTaskFormat());
            sb.append("\n");
        }
        return sb.toString();
    }


    /**
     * Returns a new TaskList from the information in storedList.txt stored in the hard drive.
     *
     * @param file The file in which data about the list of tasks is stored.
     * @return A new TaskList based on the information in the hard drive (storedList.txt file).
     */
    protected TaskList convertFromStoredList(File file) {
        List<Task> storedListOfTasks = new ArrayList<>();

        try {
            Scanner sc = new Scanner(file);
            while (sc.hasNext()) {
                String input = sc.nextLine();
                if (input.isEmpty()) {
                    break;
                } else {
                    storedListOfTasks.add(convertStringToTask(input));
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            try {
                File newFile = new File("storedList.txt");
                FileWriter fw = new FileWriter(newFile);
                fw.write("");
                fw.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            System.out.println("**storedList.txt not found. "
                    + "New file \"storedList.txt\" has been created for you.**");
        }
        return new TaskList(storedListOfTasks);
    }

    /**
     * Returns a task object after processing a string from storedList.txt.
     *
     * @param input String (from storedList.txt) containing details of the task.
     * @return A task object instantiated with parameters provided by the input string.
     */
    Task convertStringToTask(String input) {
        String[] splitString = input.split("\\|");
        String taskType = splitString[0];
        boolean isDone;
        switch(taskType) {
        case "T":
            isDone = Boolean.parseBoolean(splitString[1]);
            String todoName = splitString[2];
            Todo todo = new Todo(todoName, isDone);
            return todo;
        case "D":
            isDone = Boolean.parseBoolean(splitString[1]);
            String deadlineName = splitString[2];
            String endDate = splitString[3];
            Deadline deadline = new Deadline(deadlineName, endDate, isDone);
            return deadline;
        case "E":
            isDone = Boolean.parseBoolean(splitString[1]);
            String eventName = splitString[2];
            String eventTime = splitString[3];
            Event event = new Event(eventName, eventTime, isDone);
            return event;
        default:
            assert false : "Error in convertStringToTask() method."
                    + "Tasks should have been stored with the appropriate format (T, D, or E)";
            return new Todo("Fallthrough occurred!");
        }
    }
}
