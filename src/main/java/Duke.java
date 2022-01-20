import java.util.ArrayList;
import java.util.Scanner;

import helperClasses.*;

public class Duke {
    private static boolean isEnd = false;
    private static final ArrayList<Task> TaskList = new ArrayList<Task>();
    private static final Scanner myObj = new Scanner(System.in);

    public enum Command{
        BYE("bye"),
        LIST("list"),
        MARK("mark"),
        UNMARK("unmark"),
        DELETE("delete"),
        TODO("todo"),
        DEADLINE("deadline"),
        EVENT("event");

        private String command;
        Command(String commandIN) {
            this.command = commandIN;
        }

        public String getCommand() {
            return command;
        }
    }

    public static void horizontalLine(){
        System.out.println("____________________________________________________________");
    }

    public static void greet(){
        horizontalLine();
        System.out.println("\tHello I am DDX");
        System.out.println("\tWhat can I do for you?");
        horizontalLine();

    }

    public static void echo() {
        // Create a Scanner object
        String input = myObj.nextLine();
        try{
            if (input.equals(Command.BYE.getCommand())) {
                // detect bye
                Duke.bye();
            } else if (input.equals(Command.LIST.getCommand())){
                // detect list
                Duke.list();
            } else {
                if (input.startsWith(Command.MARK.getCommand())
                        || input.startsWith(Command.UNMARK.getCommand())
                        || input.startsWith(Command.DELETE.getCommand())){
                    boolean isDigit = !Character.isDigit(input.charAt(input.length() - 1));
                    int markDigit = Character.getNumericValue(input.charAt(input.length() - 1));
                    if (input.startsWith(Command.MARK.getCommand())){
                        // detect mark
                        if (isDigit) {
                            throw new DukeException("☹ OOPS!!! The mark cannot be done.");
                        }
                        if (markDigit - 1 < 0 || (markDigit > TaskList.size())){
                            throw new DukeException("☹ OOPS!!! The task of a mark cannot be reached.");
                        }
                        mark(markDigit - 1);
                        return;
                    }
                    if (input.startsWith(Command.UNMARK.getCommand())) {
                        // detect unmark
                        if (isDigit) {
                            throw new DukeException("☹ OOPS!!! The unmark cannot be done.");
                        }
                        if (markDigit - 1 < 0 || (markDigit > TaskList.size())){
                            throw new DukeException("☹ OOPS!!! The task of a unmark cannot be reached.");
                        }
                        unmark(markDigit - 1);
                        return;
                    }
                    if (input.startsWith(Command.DELETE.getCommand())) {
                        // detect delete
                        if (isDigit) {
                            throw new DukeException("☹ OOPS!!! The delete cannot be done.");
                        }
                        if (markDigit - 1 < 0 || (markDigit > TaskList.size())){
                            throw new DukeException("☹ OOPS!!! The task of a delete cannot be reached.");
                        }
                        delete(markDigit - 1);
                    }
                } else {
                    // add task
                    if (input.startsWith(Command.TODO.getCommand())) {
                        // generate
                        String description = input.substring(4);
                        if (Duke.isEmpty(description)) {
                            throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
                        }
                        addTodo(description);
                    } else if (input.startsWith(Command.DEADLINE.getCommand())) {
                        if (Duke.isEmpty(input.substring(8))){
                            throw new DukeException("☹ OOPS!!! The description of a deadline cannot be empty.");
                        }
                        if (!input.contains("/by")) {
                            throw new DukeException("☹ OOPS!!! The date of a deadline cannot be empty.");
                        }
                        int byPos = input.indexOf("/by");
                        String description = input.substring(9,byPos);
                        String by = input.substring(byPos+4);
                        addDdl(description, by);
                    } else if (input.startsWith(Command.EVENT.getCommand())) {
                        if (Duke.isEmpty(input.substring(5))){
                            throw new DukeException("☹ OOPS!!! The description of a event cannot be empty.");
                        }
                        if (!input.contains("/at")) {
                            throw new DukeException("☹ OOPS!!! The date of a event cannot be empty.");
                        }
                        int atPos = input.indexOf("/at");
                        String description = input.substring(6,atPos);
                        String at = input.substring(atPos+4);
                        addEvent(description, at);
                    } else {
                        throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                    }
                }
            }
        } catch (DukeException e){
            horizontalLine();
            System.out.println(e.getMessage());
            horizontalLine();
        }
    }

    public static boolean isEmpty(String input) {
        if (input.equals("")) {
            return true;
        }
        int curr = 0;
        while (curr < input.length()) {
            if (input.charAt(curr) != ' ') {
                return false;
            }
            curr++;
        }
        return true;
    }

    public static void bye(){
        Duke.isEnd = true;
        String result = "\tBye. Hope to see you again soon!";
        // print result
        horizontalLine();
        System.out.println(result);
        horizontalLine();
        myObj.close();
    }

    public static void list(){
        horizontalLine();
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < TaskList.size() ; i++) {
            if (TaskList.get(i) != null) {
                System.out.format("%s. %s\n", i + 1, TaskList.get(i).toString());
            }
        }
        horizontalLine();
    }

    public static void mark(int currTask){
        TaskList.get(currTask).markAsDone();
        horizontalLine();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(TaskList.get(currTask).toString());
        horizontalLine();
    }

    public static void unmark(int currTask){
        TaskList.get(currTask).markAsNotDone();
        horizontalLine();
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(TaskList.get(currTask).toString());
        horizontalLine();
    }

    public static void addTodo(String description) {
        ToDo temp = new ToDo(description);
        TaskList.add(temp);
        String result = String.format("Got it. I've added this task:\n\t%s\nNow you have %d tasks in the list.",
                temp.toString(),TaskList.size());
        horizontalLine();
        System.out.println(result);
        horizontalLine();
    }

    public static void addDdl(String description, String by){
        Deadline ddl = new Deadline(description, by);
        TaskList.add(ddl);
        String result = String.format("Got it. I've added this task:\n\t%s\nNow you have %d tasks in the list.",
                ddl.toString(), TaskList.size());
        horizontalLine();
        System.out.println(result);
        horizontalLine();
    }

    public static void addEvent(String description, String at) {
        Event event = new Event(description, at);
        TaskList.add(event);
        String result = String.format("Got it. I've added this task:\n\t%s\nNow you have %d tasks in the list.",
                event.toString(), TaskList.size());
        horizontalLine();
        System.out.println(result);
        horizontalLine();
    }

    public static void delete(int currTask) {
        horizontalLine();
        System.out.println("OK, I've removed this task:");
        System.out.println("\t" + TaskList.get(currTask).toString());
        TaskList.remove(currTask);
        System.out.format("Now you have %d tasks in the list.\n",TaskList.size());
        horizontalLine();
    }

    public static void main(String[] args) {
        greet();
        while (!isEnd) {
            echo();
        }
    }
}
