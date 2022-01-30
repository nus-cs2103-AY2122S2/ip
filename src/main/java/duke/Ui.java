package duke;

import duke.Task.Task;

import java.util.ArrayList;

/**
 * User Interface class, print beautiful output to user
 */
public class Ui {
    private final String lines = "____________________________" +
            "________________________________";
    private final String endline = "___________________________" +
            "_________________________________\n";

    /**
     * constructor
     */
    public Ui() {
        System.out.println("Hello!!! I am Duke, a new born chatbot\n");
        System.out.println("How may I serve you?");
    }

    /**
     * Prints bye message
     */
    public void bye() {
        System.out.println(lines);
        System.out.println("Bye! See you again");
        System.out.println(endline);
    }

    public void showAllTasks(TaskList taskList) {
        System.out.println("Here are all your tasks:");
        for (int i = 0; i < taskList.size(); i++) {
            System.out.println((i + 1) + "." + taskList.get(i).toString());
        }
    }

    /**
     * Tells the user that the task is already done
     * @param markTask current list of tasks
     */
    public void showMarkTaskAsAlreadyUndone(Task markTask) {
        System.out.println("You have already done this task!");
        System.out.println(markTask.markAsUndone());
    }

    /**
     * Tells the user that the task is marked as done
     * @param markTask current list of tasks
     */
    public void showMarkTaskAsUndone(Task markTask) {
        System.out.println("Good Job! You have marked this task as done!");
        System.out.println(markTask.markAsUndone());
    }

    /**
     * Tells the user that the task is not done yet
     * @param markTask current list of tasks
     */
    public void showMarkTaskAsAlreadyDone(Task markTask) {
        System.out.println("You have already done this task!");
        System.out.println(markTask.markAsDone());
    }

    /**
     * Tells the user that the task is marked as undone
     * @param markTask current list of tasks
     */
    public void showMarkTaskAsDone(Task markTask) {
        System.out.println("Good Job! You have marked this task as done!");
        System.out.println(markTask.markAsDone());
    }

    /**
     * Tells the user that the todo task is added
     * @param todo the task to be added
     * @param taskList current list of tasks
     */
    public void showTodoTaskAdded(Task todo,TaskList taskList) {
        System.out.println("Got it, I have added a TODO task:");
        System.out.println(todo.toString());
        System.out.println("Now you have " + taskList.size() + " tasks in the list.");
    }

    /**
     * Tells the user that the deadline task is added
     * @param deadline the task to be added
     * @param taskList current list of tasks
     */
    public void showDeadlineTaskAdded(Task deadline, TaskList taskList) {
        System.out.println("Got it, I have added a DEADLINE task:");
        System.out.println(deadline.toString());
        System.out.println("Now you have " + taskList.size() + " tasks in the list.");
    }

    /**
     * Tells the user that the event task is added
     * @param event the task to be added
     * @param taskList current list of tasks
     */
    public void showEventTaskAdded(Task event, TaskList taskList) {
        System.out.println("Got it, I have added an EVENT task:");
        System.out.println(event.toString());
        System.out.println("Now you have " + taskList.size() + " tasks in the list.");
    }



    /**
     * Tells the user that the task is deleted
     * @param taskToBeDelete a task to be deleted
     * @param taskList current list of tasks
     */
    public void showDeletedTask(Task taskToBeDelete, TaskList taskList) {
        System.out.println("Okay, I have removed this task:");
        System.out.println(taskToBeDelete);
        System.out.println("Now you have " + taskList.size() + " tasks in the list.");
    }

    public void showWhichTaskIsBeingDeleted() {
        System.out.println("Which task are you deleting? " +
                                   "Insert a number like this: delete <task number>");
    }

    /**
     * Shows error message
     * @param message the error message
     */
    public void showError(String message) {
        System.out.println(message);
    }

    /**
     * Shows line
     */
    public void showLine() {
        System.out.println(lines);
    }

    /**
     * Shows endline
     */
    public void showEndline() {
        System.out.println(endline);
    }

    /**
     * Shows found task using find command
     * @param searched_taskList founded task list, a string array
     */
    public void showFoundTask(ArrayList<String> searched_taskList) {
        if (searched_taskList.size() == 0) {
            System.out.println("There are no matching tasks in your list");
        } else {
            System.out.println("Here are the matching tasks in your list:");
            for (String s : searched_taskList) {
                System.out.println(s);
            }
        }
    }
}
