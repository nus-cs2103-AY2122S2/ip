package duke.utils;


import duke.task.Task;
import java.util.ArrayList;


/**
 * Class that handles user interactions
 */
public class Ui {

    /**
     * Stores the last command given by the user
     */
    private String lastCommand;

    /**
     * Print the list of tasks, handle
     * case where there are no tasks in
     * the list
     *
     * @param tasks Arraylist of tasks
     */
    public static String printList(ArrayList<Task> tasks) {

        if (tasks.size() == 0) {
            return "No Tasks Right Now";
        } else {
            StringBuilder st = new StringBuilder();
            for (int x = 0; x < tasks.size(); x++) {
                st.append((x + 1) + ". " + tasks.get(x).toString() + "\n");
            }
            return st.toString();
        }
    }


    /**
     * Print the list of tasks that matches
     * the user's search request
     *
     * @param tasks Arraylist of tasks
     */
    public static String printSearchList(ArrayList<Task> tasks) {

        if(tasks.size() == 0) {
            return "No Matching Tasks Found";
        } else {
            StringBuilder st = new StringBuilder();
            st.append("The Following Matching Tasks were found: \n");
            for (int x = 0; x < tasks.size(); x++) {
                st.append((x + 1) + ". " + tasks.get(x).toString() + "\n");
            }
            return st.toString();
        }
    }

    /**
     * Print the addition of a new task to the list
     *
     * @param curr Task that has been added to the list
     * @param size number of tasks in the list
     */
    public static String printTaskAddition(Task curr, int size) {
        StringBuilder st = new StringBuilder();
        st.append("Got it! I've added this task: \n");
        st.append(curr.toString() + "\n");
        st.append("Now you have " + size + " tasks in the list \n");
        return st.toString();
    }


    /**
     * Print the removal of a task from the list
     *
     * @param curr Task that has been removed from the list
     * @param size number of tasks in the list
     */
    public static String printTaskDeletion(Task curr, int size) {
        StringBuilder st = new StringBuilder();
        st.append("Got it! I've removed this task: \n");
        st.append(curr.toString() + "\n");
        st.append("Now you have " + size + " tasks in the list \n");
        return st.toString();
    }



    /**
     * Print the welcome message for the user
     */
    public static String printHello() {
        return "Hello! I'm Duke! \n What can I do for you?";
    }

    /**
     * Print the goodbye message for the user
     */
    public static String printBye() {
        return "Bye! Hope to see you again soon";
    }

    /**
     * Print a task has been marked
     * as completed
     *
     * param t Task that has been marked as complete
     */
    public static String printMarkCompletion(Task t) {
        StringBuilder st = new StringBuilder();
        st.append("Nice! I've marked this task as done: \n");
        st.append(t.toString());
        return st.toString();
    }

    /**
     * Print a task has been marked
     * as not completed
     *
     * param t Task that has been marked as not complete
     */
    public static String printMarkUncompletion(Task t) {
        StringBuilder st = new StringBuilder();
        st.append("OK, I've marked this task as not done yet: \n");
        st.append(t.toString());
        return st.toString();
    }

    /**
     * Print the error message for
     * a particular DukeException
     *
     * @param e Exception received
     */
    public static String showError(DukeException e) {
        return (e.toString());
    }

}
