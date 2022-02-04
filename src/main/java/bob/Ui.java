package bob;

import java.util.Scanner;

import bob.task.Task;

public class Ui {
    private Scanner sc;
    public Ui() {
        sc = new Scanner(System.in);
    }

    public String unknownError() {
        return "Bob: iDK what went wrong O_O";
    }

    public String readInput() {
        return sc.nextLine();
    }

    public String greetMessage() {
        return "Bob: What tasks are you adding to the list today? \t¯\\(°_o)/¯";
    }

    public String badReply() {
        return "Bob: ╭∩╮༼ಠ益ಠ╭∩╮༽";
    }

    public String showError(String error) {
        return "Bob: " + error;
    }

    public String noSearchResults() {
        return "Bob: I could not find such a task, did you even tell me about it?????";
    }

    /**
     * Prints reply when there are search results.
     */
    public String searchHasResults() {
        return "Bob: I found these tasks";
    }

    /**
     * Prints the user input line.
     */
    public String userReply() {
        return "You: ";
    }

    public String noTasks() {
        return "\tNo tasks saved previously~";
    }
    public String printTask(Task task) {
        return "\t" + task.printStatus();
    }

    public String finishTask() {
        return "Bob: Great job in completing your task! I've marked it as done. ᕕ(⌐■_■)ᕗ ♪♬";
    }

    public String free() {
        return "Bob: You are very free rn \t※\\(^o^)/※";
    }

    public String doneBefore() {
        return "Bob: You already did this task... or did you? (ಠ_⊙)";
    }

    /**
     * Prints reply when task is deleted.
     */
    public String deletedTask() {
        return "Bob: I have removed the following task from your list. ᕙ(⇀‸↼‶)ᕗ";
    }

    /**
     * Prints reply when a new task is added.
     *
     * @param newTask a task that has been created
     * @param tasks the list of tasks the user has
     */
    public String newTask(Task newTask, TaskList tasks) {
        return "Bob: I have added " + newTask + " to your tasks! You have " + tasks.size() + " tasks now "
                + "._.)/\\(._.\n";
    }

    public String preListReply() {
        return "Bob: Okay, these are your tasks";
    }

    public String postListFace() {
        return "      (づ｡◕‿‿◕｡)づ";
    }

    public String say(String input) {
        return input;
    }

    public String sayBye() {
        return "Bob: " + "bye bye c u next time (ʘ‿ʘ)╯ \n";
    }
}
