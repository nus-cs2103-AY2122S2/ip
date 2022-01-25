package mike;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TaskList {
    private final List<Task> listOfTasks;

    public TaskList() {
        this.listOfTasks = new ArrayList<>();
    }

    public TaskList(List<Task> listOfTasks) {
        this.listOfTasks = listOfTasks;
    }

    /**
     * Prints the current lists of tasks created by the user.
     */
    String getListForPrint() {
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
     * Adds the specified task to the list.
     *
     * @param task The task to be added to the list.
     */
    void addToList(Task task) {
        this.listOfTasks.add(task);
    }

    /**
     * Adds the specified task to the list, then prints a message to inform the user of this.
     *
     * @param task The task to be added to the list.
     */
    String addToListReply(Task task) {
        String taskName = task.getTaskName();
        String addTaskOutput = "Added \"" + taskName + "\" to the list!";
        int noOfTasks = this.listOfTasks.size();
        String noOfTasksOutput = String.format("You now have *%d* task(s) in your list.", noOfTasks);
        return String.format("%s\n%s", addTaskOutput, noOfTasksOutput);
    }

    /**
     * Removes the task (specified by its index in the list) from the list.
     *
     * @param removeIndex The index of the task to be removed from the list.
     */
    String removeFromListWithMessage(int removeIndex) {
        Task removedTask = this.listOfTasks.remove(removeIndex - 1);
        String taskName = removedTask.getTaskName();
        String removeTaskOutput = "Removed \"" + taskName + "\" from the list!";
        int noOfTasks = this.listOfTasks.size();
        String noOfTasksOutput = String.format("You now have *%d* task(s) in your list.", noOfTasks);
        return String.format("%s\n%s", removeTaskOutput, noOfTasksOutput);
    }

    String markInListWithMessage(int indexFromUser) {
        int indexInList = indexFromUser - 1;
        Task oldTask = this.listOfTasks.get(indexInList);
        Task newTask = oldTask.markAsDone();
        this.listOfTasks.set(indexInList, newTask);

        String outputMessage =
                String.format("Great job, \"%s\" marked as done!", newTask.getTaskName());
        return outputMessage;
    }

    String unmarkInListWithMessage(int indexFromUser) {
        int indexInList = indexFromUser - 1;
        Task oldTask = this.listOfTasks.get(indexInList);
        Task newTask = oldTask.markAsUndone();
        this.listOfTasks.set(indexInList, newTask);

        String outputMessage =
                String.format("Sure thing, \"%s\" marked as undone!", newTask.getTaskName());
        return outputMessage;
    }

    String convertToStoredListFormat() {
        StringBuilder sb = new StringBuilder();
        for (Task task : listOfTasks) {
            sb.append(task.convertToStoredTaskFormat());
            sb.append("\n");
        }
        return sb.toString();
    }


    TaskList convertFromStoredList(File file) {
        List<Task> storedListOfTasks = new ArrayList<>();

        try {
            Scanner sc = new Scanner(file);
            while (sc.hasNext()) {
                String input = sc.nextLine();
                if (input.isEmpty()) {
                    break; //Todo: this might cause problems later
                } else {
                    storedListOfTasks.add(convertStringToTask(input));
                }
            }
        } catch(FileNotFoundException e) {
            e.printStackTrace();
            try {
                //create a new file for storedList
                File newFile = new File("storedList.txt");
                FileWriter fw = new FileWriter(newFile);
                fw.write("");
                fw.close();
            } catch(IOException ex) {
                ex.printStackTrace();
            }
            System.out.println("**storedList.txt not found. " +
                    "New file \"storedList.txt\" has been created for you.**");
        }
        return new TaskList(storedListOfTasks);
    }

    /**
     * Returns a task object after processing a string from storedList.txt.
     *
     * @param input String containing details of the task.
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
        //break;
        case "D":
            isDone = Boolean.parseBoolean(splitString[1]);
            String deadlineName = splitString[2];
            String endDate = splitString[3];
            Deadline deadline = new Deadline(deadlineName, endDate, isDone);
            return deadline;
        //break;
        case "E":
            isDone = Boolean.parseBoolean(splitString[1]);
            String eventName = splitString[2];
            String eventTime = splitString[3];
            Event event = new Event(eventName, eventTime, isDone);
            return event;
        //break;
        default:
            return new Todo("Fallthrough occurred!!");
        //break;
        }
    }
}
