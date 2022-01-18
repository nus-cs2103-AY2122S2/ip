import java.sql.SQLOutput;
import java.util.Scanner;

public class Duke {
    private static final String botName = "Feline";
    private String[] list;
    private int listIndex;

    public Duke() {
        this.list = new String[100];
        this.listIndex = 1;
    }
    private static void greet() {
        System.out.println("Yoooo! My name is " + botName + "!\n" + "How can I help you bro?\n");
    }
    private static void farewell() {
        System.out.println("See you next time!\n");
    }
    private void addToList(String str) {
        System.out.println("added: " + str + "\n");
        this.list[this.listIndex] = str;
        this.listIndex ++;
    }
    private void printList() {
        for (int i = 1; i < this.listIndex; i++) {
            System.out.println(i + ". " + this.list[i] + "\n");
        }
    }
    public static void main(String[] args) {

        Duke duke = new Duke(); //initializing a Duke instance
        Duke.greet();
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        while (!input.equals("bye")) {
            if (input.equals("list")) {
                duke.printList();
            } else {
                duke.addToList(input);
            }
            input = sc.nextLine();
        }
        sc.close();
        Duke.farewell();

    }
}
