import java.sql.SQLOutput;
import java.util.Scanner;

public class Duke {
    private static final String botName = "Feline";
    private Task[] list;
    private int listIndex;
    private static final String[] commands = {"bye", "mark", "unmark", "list", "todo", "deadline", "event"};

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

    private void addTask(String str) {    //adds task to list
        String action = Duke.getFirstWord(str);
        switch (action) {
            case "todo":
                this.list[this.listIndex] = new Todo(str);
                break;
            case "deadline":
                //deadline do hw /by no idea :-p
                String[] arr = str.split("/by");
                arr[0] = arr[0].split(" ")[1];  // removing the deadline keyword
                this.list[this.listIndex] = new Deadline(arr[0].trim(), arr[1].trim());
                break;
            case "event":
                //event project meeting /at Mon 2-4pm
                String[] eventArr = str.split("/at");
                eventArr[0] = eventArr[0].split(" ")[1];  //removing the event keyword
                this.list[this.listIndex] = new Event(eventArr[0].trim(), eventArr[1].trim());
                break;
            default:
                this.list[this.listIndex] = new Task(str);
        }

        System.out.println("Got it. I've added this task:");
        System.out.println(this.list[this.listIndex].toString());
        System.out.println("Now you have " + this.listIndex + " tasks in your list.");
        this.listIndex ++;
    }

    private String getTaskStatement(int i) {
        //[X] read book
        return this.list[i].toString();
    }
    private void printList() {
        System.out.println("Here are the tasks in your list:");
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
        System.out.println(this.getTaskStatement(taskNumber));
    }

    private void unmark(String input) {
        System.out.println("OK, I've marked this task as not done yet:\n");
        String[] words = input.split(" ", 2);
        // the second word is expected to be a number for now
        int taskNumber = Integer.parseInt(words[1]);
        this.list[taskNumber].markAsUndone();
        System.out.println(this.getTaskStatement(taskNumber));
    }
    public static void main(String[] args) {

        Duke duke = new Duke(); //initializing a Duke instance
        Duke.greet();
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        // for each input, action = input.getfirstword. while input!eq bye, Duke.performAction(action, input)
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
                    duke.addTask(input);
                }
            }
            input = sc.nextLine();
        }
        sc.close();
        Duke.farewell();

    }
}
