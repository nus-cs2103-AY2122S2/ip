package Cleese;

public class Ui {

    public void printLine() {
        System.out.println("--------------------------------------------------");
    }

    public void printWelcomeMessage() {
        System.out.println("Good day Sir, this is Cleese, your virtual butler!\nAnd just how may I help you today?");
    }
    public void printGoodByeMessage() {
        System.out.println("If that'll be all sir, i shall retire for the day.\nI'll be in my quarters if you require me");
    }
    public void printMarkedMessage(Task task) {
        System.out.println(String.format("Ok, I've marked this task as done:\n%s",task.toString()));
    }
    public void printUnmarkedMessage(Task task) {
        System.out.println(String.format("Ok, I've marked this task as not done yet:\n%s",task.toString()));
    }
    public void printAddedAck(Task task, TaskList taskList) {
        System.out.println(String.format("Got it. I've added this task:\n%s\nNow you have %d tasks in the list",task.toString(),taskList.size()));
    }
    public void printRemovedAck(Task task, TaskList taskList) {
        System.out.println(String.format("Noted. I've removed this task:\n%s\nNow you have %d tasks in the list",task.toString(),taskList.size()));
    }
    public void printFindMessage() {
        System.out.println(String.format("Here are the matching tasks in your list:"));
    }
}
