import java.util.Scanner;

public class Duke {
    private static final String botName = "Feline";
    private Task[] list;
    private int listIndex;
    //private static final String[] commands = {"bye", "mark", "unmark", "list", "todo", "deadline", "event"};

    public Duke() {
        this.list = new Task[100];
        this.listIndex = 1;
    }

    private void performAction(String action, String input) {
        switch (action) {
            case "mark":
                mark(input);
                break;
            case "unmark":
                unmark(input);
                break;
            case "list":
                printList();
                break;
            default:
                addTask(input);
        }
    }
    private static void greet() {
        System.out.println(String.format("Yoooo! My name is %s!\nHow can i help you bro?\n", botName));
    }
    private static void farewell() {
        System.out.println("See you next time!");
    }

    private void taskAdded() {
        System.out.println("Got it. I've added this task:");
        System.out.println(this.list[this.listIndex].toString());
        System.out.println(String.format("Now you have %d task(s) in your list.", this.listIndex));
        this.listIndex ++;
    }

    private void addTask(String str) {    //adds task to list
        String action = Duke.getFirstWord(str);
        switch (action) {
            case "todo":
                try {
                    String[] todoArr = str.split(" ", 2);
                    if (todoArr.length <= 1) {
                        throw new InvalidArgumentException("todo.. todo what?");
                    }
                    this.list[this.listIndex] = new Todo(todoArr[1].trim());
                    taskAdded();
                } catch (InvalidArgumentException e) {
                    System.out.println(e.getMessage());
                } finally {
                    break;
                }
            case "deadline":
                //deadline do hw /by no idea :-p
                try {
                    String[] deadlineArr = str.split("/by", 2);
                    String[] deadlineSplit = deadlineArr[0].split(" ", 2);
                    if (deadlineSplit.length <= 1) {    // no description
                        throw new InvalidArgumentException("Sorry but.. deadline of what??");
                    }
                    if (deadlineArr.length <= 1) { // don't have /by keyword
                        throw new InvalidArgumentException("By when?? ..");
                    }
                    String description = deadlineSplit[1].trim();
                    this.list[this.listIndex] = new Deadline(description, deadlineArr[1].trim());
                    taskAdded();
                } catch (InvalidArgumentException e) {
                    System.out.println(e.getMessage());
                } finally {
                    break;
                }
            case "event":
                //event project meeting /at Mon 2-4pm
                try {
                    String[] eventArr = str.split("/at", 2);
                    String[] eventSplit = eventArr[0].split(" ", 2);
                    if (eventSplit.length <= 1) {
                        throw new InvalidArgumentException("What event? No event stated.");
                    }
                    if (eventArr.length <= 1) {
                        throw new InvalidArgumentException("At where? Please specify again");
                    }
                    String description = eventSplit[1].trim();
                    this.list[this.listIndex] = new Event(description, eventArr[1].trim());
                    taskAdded();
                } catch (InvalidArgumentException e){
                    System.out.println(e.getMessage());
                } finally {
                    break;
                }
            default:
                try {
                    throw new UnknownException(String.format("Sorry.. I don't" +
                            " know what u talking bout bro. What does \"%s\" mean?", str));
                }
                catch (UnknownException e) {
                System.out.println(e.getMessage());
                }
        }

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

    private void mark(String input) {
        try {
            String[] words = input.split(" ", 2);
            // the second word is expected to be a number for now
            int taskNumber = Integer.parseInt(words[1]);
            if (taskNumber >= this.listIndex || taskNumber <= 0)
                throw new OutOfBoundsException(String.format("The task %d does not exist!", taskNumber));
            this.list[taskNumber].markAsDone();
            System.out.println("Nice! I've marked this task as done:");
            System.out.println(this.getTaskStatement(taskNumber));
        } catch (OutOfBoundsException e) {
            System.out.println(e.getMessage());
        }
    }

    private void unmark(String input) {
        try {
            String[] words = input.split(" ", 2);
            // the second word is expected to be a number for now
            int taskNumber = Integer.parseInt(words[1]);
            if (taskNumber >= this.listIndex || taskNumber <= 0)
                throw new OutOfBoundsException(String.format("The task %d does not exist!", taskNumber));;
            this.list[taskNumber].markAsUndone();
            System.out.println("OK, I've marked this task as not done yet:");
            System.out.println(this.getTaskStatement(taskNumber));
        } catch (OutOfBoundsException e) {
            System.out.println(e.getMessage());
        }
    }
    public static void main(String[] args) {
        Duke duke = new Duke(); //initializing a Duke instance
        Duke.greet();
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        while (!input.equals("bye")) {  //terminates system when user says bye
            String action = Duke.getFirstWord(input);
            duke.performAction(action, input);
            System.out.println("\n");
            input = sc.nextLine();
        }
        sc.close();
        Duke.farewell();
    }
}
