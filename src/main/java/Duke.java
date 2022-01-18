import java.sql.SQLOutput;
import java.util.Scanner;

public class Duke {
    private static final String botName = "Feline";
    private Task[] list;
    private int listIndex;

    public Duke() {
        this.list = new Task[100];
        this.listIndex = 1;
    }
    private static void greet() {
        System.out.println("Yoooo! My name is " + botName + "!\n" + "How can I help you bro?\n");
    }
    private static void farewell() {
        System.out.println("See you next time!\n");
    }
    private void addToList(String str) {    //adds task to list
        System.out.println("added: " + str + "\n");
        this.list[this.listIndex] = new Task(str);
        this.listIndex ++;
    }
    private String getTaskDescription(int i) {
        return this.list[i].description;
    }
    private boolean getTaskStatus(int i) {
        return this.list[i].isDone;
    }
    private String getTaskStatement(int i) {
        //[X] read book, or [ ] do sth
        return "[" + this.list[i].getStatusIcon() + "] " + this.getTaskDescription(i) + "\n";
    }
    private void printList() {
        for (int i = 1; i < this.listIndex; i++) {
            System.out.println(i + "." + this.getTaskStatement(i));
        }
    }
    private static String getFirstWord(String input) {
        int index = input.indexOf(' ');
        if (index > -1) {
            // more than one word
            return input.substring(0, index).trim();   // Extract first word
        } else {
            // there is only one word
            return input;
        }
    }

    private boolean isMarkAction(String action, String input) {
        if (!action.equals("mark")) return false;
        // also need to make sure there is only 1 space, and the second "word" is an int character
//        int wordCount = 0;
//        String trim = input.trim();
//        System.out.println("trim " + trim);
//        wordCount = trim.split("\\s+").length;
//        System.out.println("word count " + wordCount);
//        if (wordCount != 2) return false;

        return true;
    }

    private boolean isUnMarkAction(String action, String input) {
        if (!action.equals("unmark")) return false;
        return true;
    }

    private void mark(String input) {
        System.out.println("Nice! I've marked this task as done:\n");
        String[] words = input.split(" ", 2);
        // the second word is expected to be a number for now
        int taskNumber = Integer.parseInt(words[1]);
        this.list[taskNumber].markAsDone();
        System.out.println(this.getTaskStatement(taskNumber) + "\n");
    }

    private void unmark(String input) {
        System.out.println("OK, I've marked this task as not done yet:\n");
        String[] words = input.split(" ", 2);
        // the second word is expected to be a number for now
        int taskNumber = Integer.parseInt(words[1]);
        this.list[taskNumber].markAsUndone();
        System.out.println(this.getTaskStatement(taskNumber) + "\n");
    }
    public static void main(String[] args) {

        Duke duke = new Duke(); //initializing a Duke instance
        Duke.greet();
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        while (!input.equals("bye")) {  //terminates system when user says bye
            if (input.equals("list")) {
                duke.printList();
            } else {
                String action = Duke.getFirstWord(input);
                if (duke.isMarkAction(action, input)) {
                    duke.mark(input);
                } else if (duke.isUnMarkAction(action, input)) {
                    duke.unmark(input);
                } else {
                    duke.addToList(input);
                }
            }
            input = sc.nextLine();
        }
        sc.close();
        Duke.farewell();

    }
}
