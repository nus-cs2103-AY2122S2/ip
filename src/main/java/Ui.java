import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Ui {

    private Scanner sc;
    private String lastCommand;

    public Ui(){
        sc = new Scanner(System.in);
    }

    public static void printList(ArrayList<Task> tasks){
        if(tasks.size() == 0) {
            System.out.println("No Tasks Right Now");
        } else {
            for (int x = 0; x < tasks.size(); x++) {
                System.out.println((x + 1) + ". " + tasks.get(x).toString());
            }
        }
    }

    public static void printTaskAddition(Task curr, int size){
        System.out.println("Got it! I've added this task:");
        System.out.println(curr.toString());
        System.out.println("Now you have " + size + " tasks in the list");
    }

    public static void printTaskDeletion(Task curr, int size){
        System.out.println("Got it! I've removed this task:");
        System.out.println(curr.toString());
        System.out.println("Now you have " + size + " tasks in the list");
    }

    public static void printHello(){
        System.out.println("Hello! I'm Duke!");
        System.out.println("What can I do for you?");
    }

    public static void printLine(){
        System.out.println("-------------------");
    }

    public static void printBye(){
        System.out.println("Bye! Hope to see you again soon");
    }

    public String readCommand() {
        String userInput = sc.nextLine();
        lastCommand = userInput;
        return userInput;
    }

    public boolean isExit() {
        return this.lastCommand.equals("bye");
    }

    public static void printMarkCompletion(Task t){
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(t.toString());
    }

    public static void printMarkUncompletion(Task t) {
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(t.toString());
    }

    public static void showError(DukeException e){
        System.out.println(e);
    }

}
