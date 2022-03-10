package duke.tasks;

import duke.CustomException;
import duke.Parser;
import duke.ui.Ui;
import duke.util.CustomStringBuilder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * A class that stores a list of tasks and provides complementary functionalities such as add, find, delete and mark
 * tasks.
 */
public class TaskList {
    public static ArrayList<Task> manager;
    private static Parser parser;

    public TaskList() {
        manager = new ArrayList<>();
        parser = new Parser();
    }

    /**
     * displays all the elements of the task list to the user.
     * @throws CustomException if the task list is currently empty and has nothing to display.
     */
    public static String reportList() throws CustomException {
        CustomStringBuilder customStringBuilder = new CustomStringBuilder();
        int len = manager.size();
        if (len != 0) {
            for (int i = 0; i < len; i++) {
                customStringBuilder.bulkAppend((i + 1), ". ", manager.get(i).toString(), "\n");
            }
        } else {
            throw new CustomException("your task list is currently empty. " + "Add a task first:)");
        }
        return customStringBuilder.toString();
    }

    /**
     * finds the task, at a specific index, in the task list.
     * @param num is the integer referring to the list index.
     * @return desiredTask, which is the task that the user was looking for.
     * @throws CustomException if the list is empty or if the index supplied is invalid.
     */
    public static Task findTask(int num) throws CustomException {
        Task desiredTask = new Task("empty task");
        int len = manager.size();

        if (num < 0) {
            throw new CustomException("Invalid task ID: number must be a positive integer:)");
        } else if (num > len) {
            throw new CustomException("Invalid task ID: this task number does not exist as of now.");
        } else if (len == 0){
            throw new CustomException("your task list is currently empty. " + "Add a task first:)");
        } else { //default, normal case
            for (int i = 1; i <= len; i++) {
                if (i == num) {
                    desiredTask = manager.get(i - 1);
                }
            }
            return desiredTask;
        }
    }

    /**
     * adds a task object to the tasklist
     * @param taskType a String that highlights type of task object required to be created.
     * @param instruction a String that contains details pertaining to the task.
     */
    public static String addTask(String taskType, String instruction) {
        CustomStringBuilder customStringBuilder = new CustomStringBuilder();
        try {
            Task toAdd = Parser.parseCommandFromUser(taskType, instruction);
            manager.add(toAdd);
            customStringBuilder.bulkAppend("Rodger that! Task item added: \n", toAdd);
        } catch (CustomException e) {
            Ui.displayError(e.getMessage());
        }
        return customStringBuilder.toString();
    }

    /**
     * deletes the task at a particular index in the task list.
     * @param num the integer index.
     * @throws CustomException if the index inputted is invalid.
     */
    public static String deleteTask(int num) throws CustomException {
        CustomStringBuilder customStringBuilder = new CustomStringBuilder();
        if (num <= 0) {
            throw new CustomException("Invalid task ID: number must be a positive integer:)");
        } else if (num > manager.size()) {
            throw new CustomException("Invalid task ID: this task number does not exist as of now.");
        } else { //normal case
            manager.remove(num - 1);
            customStringBuilder.bulkAppend("Sure! Task item ", num, " has now been deleted:)");
            return customStringBuilder.toString();
        }
    }

    /**
     * marks a task, on a specific index, as done.
     * @param num integer index.
     * @throws CustomException if index is invalid.
     */
    public static String markAsDone(int num) throws CustomException {
        CustomStringBuilder customStringBuilder = new CustomStringBuilder();
        Task t = findTask(num);
        t.markDone();
        customStringBuilder.bulkAppend("Congrats! Keep going:)\n", t);
        return customStringBuilder.toString();
    }

    /**
     * marks a task, on a specific index, as not done.
     * @param num integer index.
     * @throws CustomException if index is invalid.
     */
    public static String markNotDone(int num) throws CustomException {
        CustomStringBuilder customStringBuilder = new CustomStringBuilder();
        Task t = findTask(num);
        t.undo();
        customStringBuilder.bulkAppend("No worries:) Stay motivated!\n", t);
        return customStringBuilder.toString();
    }

    /**
     * finds and displays all the tasks that contain either one or some keywords as listed by the user.
     * @param keywords a String containing at least one keyword.
     */
    public static String findTasks(String keywords) {
        List<String> allKeywords = Arrays.stream(keywords.split(",")).sequential()
                .map(String::trim)
                .collect(Collectors.toList());

        ArrayList<Task> matchingTasks = manager.stream()
                .filter(x -> allKeywords.stream()
                        .anyMatch(y -> x.description.contains(y)))
                .collect(Collectors.toCollection(ArrayList<Task>::new));

        CustomStringBuilder customStringBuilder = new CustomStringBuilder();
        customStringBuilder.bulkAppend("Here are the matching tasks found~ \n");

        for (Task t : matchingTasks) {
            customStringBuilder.bulkAppend(t, "\n");
        }

        return customStringBuilder.toString();
    }

    public static String snooze(String details) throws CustomException {
        CustomStringBuilder customStringBuilder = new CustomStringBuilder();
        String[] information = details.split(" ", 3);
        int taskIndex = Integer.parseInt(information[0]);
        String changeField = information[1];
        Task taskInConsideration = findTask(taskIndex);
        String newTime = information[2];
        if (changeField.equalsIgnoreCase("both")) {
            String[] data = information[2].split(" ", 2);
            String date = data[0];
            newTime = data[1];
            if (taskInConsideration instanceof Todo) {
                throw new CustomException("todo tasks are not associated with dates and times- please recheck!");
            } else if (taskInConsideration instanceof Deadline) {
                ((Deadline) taskInConsideration).setDate(date);
            } else {
                ((Event) taskInConsideration).setDate(date);
            }
            customStringBuilder.bulkAppend("Date updated!");
        }

        if (taskInConsideration instanceof Todo) {
            throw new CustomException("todo tasks are not associated with times- please recheck!");
        } else if (taskInConsideration instanceof Deadline) {
            ((Deadline) taskInConsideration).setTime(newTime);
        } else {
            ((Event) taskInConsideration).setTime(newTime);
        }
        customStringBuilder.bulkAppend("task snoozed as desired! Check it out:  \n", taskInConsideration);
        return customStringBuilder.toString();
    }
}
