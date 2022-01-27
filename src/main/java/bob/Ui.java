package bob;

import bob.Task.Task;

import java.util.Scanner;

public class Ui {
    private final String lineSplit = "====================================================================";
    private final String logo = "██╗░░██╗██╗  ██╗██╗███╗░░░███╗  ██████╗░░█████╗░██████╗░\n"
                              + "██║░░██║██║  ██║╚█║████╗░████║  ██╔══██╗██╔══██╗██╔══██╗\n"
                              + "███████║██║  ██║░╚╝██╔████╔██║  ██████╦╝██║░░██║██████╦╝\n"
                              + "██╔══██║██║  ██║░░░██║╚██╔╝██║  ██╔══██╗██║░░██║██╔══██╗\n"
                              + "██║░░██║██║  ██║░░░██║░╚═╝░██║  ██████╦╝╚█████╔╝██████╦╝\n"
                              + "╚═╝░░╚═╝╚═╝  ╚═╝░░░╚═╝░░░░░╚═╝  ╚═════╝░░╚════╝░╚═════╝░\n";
    private Scanner sc;
    public Ui() {
        sc = new Scanner(System.in);
    }

    public String readInput() {
        return sc.nextLine();
    }

    public void logo() {
        System.out.println(logo);
        line();
    }
    public void line() {
        System.out.println(lineSplit);
    }

    public void greetMessage() {
        System.out.println("Bob: What tasks are you adding to the list today? \t¯\\(°_o)/¯");
    }

    public void badReply() {
        System.out.println("Bob: ╭∩╮༼ಠ益ಠ╭∩╮༽");
    }

    public void showError(String error) {
        System.out.println("Bob: " + error);
    }

    public void userReply() {
        line();
        System.out.print("You: ");
    }

    public void noTasks() {
        System.out.println("\tNo tasks saved previously~");
    }
    public void printTask(Task task) {
        System.out.println("\t" + task.printStatus());
    }

    public void finishTask() {
        System.out.println("Bob: Great job in completing your task! I've marked it as done. ᕕ(⌐■_■)ᕗ ♪♬");
    }

    public void free() {
        System.out.println("Bob: You are very free rn \t※\\(^o^)/※");
    }

    public void doneBefore() {
        System.out.println("Bob: You already did this task... or did you? (ಠ_⊙)");
    }

    public void deletedTask() {
        System.out.println("Bob: I have removed the following task from your list. ᕙ(⇀‸↼‶)ᕗ");
    }

    public void newTask(Task newTask, TaskList tasks) {
        System.out.print("Bob: I have added " + newTask + " to your tasks! You have " + tasks.size() + " tasks now "
                + "._.)/\\(._.\n");
    }

    public void preListReply() {
        System.out.println("Bob: Okay, these are your tasks");
    }

    public void postListFace() {
        System.out.println("      (づ｡◕‿‿◕｡)づ");
    }

    public void say(String input) {
        System.out.println(input);
    }

    public void sayBye() {
        System.out.println("Bob: "+ "bye bye c u next time (ʘ‿ʘ)╯ \n" + lineSplit);
    }
}
