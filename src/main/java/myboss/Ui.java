package myboss;

import java.util.Scanner;

import java.util.ArrayList;

public class Ui {
    static String indent = "    ";
    private Scanner scanner;

    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    public void outputGreeting() {
        myBossOutput(" Hello! I'm YourBoss.\n" +
                indent +
                " What can you do for me?");
    }

    public void outputFarewell() {
        myBossOutput(" Bye. Hope I never see you again!");
        scanner.close();
    }

    public String getUserCmd() {
        return scanner.nextLine();
    }

    public void outputTaskList(ArrayList<Task> taskList) {
        StringBuilder tempOut = new StringBuilder("");
        for (int i = 0; i < taskList.size(); i++) {
            tempOut.append(" " + indent).append((i + 1) + ".").append(taskList.get(i).toString());
        }
        hLineBreak();
        printlnWithIndent(" Here are the tasks in your list:");
        System.out.print(tempOut); // newline in tempOut
        hLineBreak();
    }

    public void outputMarked(Task task) {
        myBossOutput(task.markAsDone(true));
    }

    public void outputUnmarked(Task task) {
        myBossOutput(task.markAsDone(false));
    }




    static void hLineBreak() {
        System.out.println(indent+"____________________________________________________________");
    }

    static void printlnWithIndent(String input) {
        System.out.print(indent);
        System.out.println(input);
    }

    static void myBossOutput(String output) {
        hLineBreak();
        printlnWithIndent(output);
        hLineBreak();
    }

    void addTaskOutput(Task task) {
        hLineBreak();
        printlnWithIndent(" Got it. I've added this task:");
        System.out.print(indent + "   " + task);
        printlnWithIndent(" Now you have "+ TaskList.getSize() +" tasks in the list.");
        hLineBreak();
    }

    void outputDeleteTask(Task task) {
        hLineBreak();
        printlnWithIndent(" Noted. I've removed this task:");
        System.out.print(indent + "   " + task);
        printlnWithIndent(" Now you have "+ TaskList.getSize() +" tasks in the list.");
        hLineBreak();
    }
}
