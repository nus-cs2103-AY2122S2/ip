import java.util.ArrayList;
import java.util.Scanner;

import helperClasses.*;

public class Duke {
    private static boolean isEnd = false;
    private static final ArrayList<Task> TaskList = new ArrayList<Task>();
    private static final Scanner myObj = new Scanner(System.in);

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
        // default message
        String result = null;

        // detect bye
        try{
            if (input.equals("bye")) {
                Duke.isEnd = true;
                result = "\tBye. Hope to see you again soon!";
                myObj.close();
            } else if (input.equals("list")){
                // detect list
                Duke.list();
                return;
            } else {
                if (input.startsWith("mark") || input.startsWith("unmark") || input.startsWith("delete")){
                    boolean isDigit = !Character.isDigit(input.charAt(input.length() - 1));
                    int markDigit = Character.getNumericValue(input.charAt(input.length() - 1));
                    if (input.startsWith("mark")){
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
                    if (input.startsWith("unmark")) {
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
                    if (input.startsWith("delete")) {
                        // detect delete
                        if (isDigit) {
                            throw new DukeException("☹ OOPS!!! The delete cannot be done.");
                        }
                        if (markDigit - 1 < 0 || (markDigit > TaskList.size())){
                            throw new DukeException("☹ OOPS!!! The task of a delete cannot be reached.");
                        }
                        delete(markDigit - 1);
                        return;
                    }
                } else {
                    // add task
                    if (input.startsWith("todo")) {
                        // generate
                        String description = input.substring(4);
                        if (Duke.isEmpty(description)) {
                            throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
                        }
                        ToDo temp = new ToDo(description);
                        TaskList.add(temp);
                        result = String.format("Got it. I've added this task:\n\t%s\nNow you have %d tasks in the list.",
                                temp.toString(),TaskList.size());
                    } else if (input.startsWith("deadline")) {
                        if (Duke.isEmpty(input.substring(8))){
                            throw new DukeException("☹ OOPS!!! The description of a deadline cannot be empty.");
                        }
                        if (!input.contains("/by")) {
                            throw new DukeException("☹ OOPS!!! The date of a deadline cannot be empty.");
                        }
                        int byPos = input.indexOf("/by");
                        String description = input.substring(9,byPos);
                        String by = input.substring(byPos+4);
                        Deadline ddl = new Deadline(description, by);
                        TaskList.add(ddl);
                        result = String.format("Got it. I've added this task:\n\t%s\nNow you have %d tasks in the list.",
                                ddl.toString(), TaskList.size());
                    } else if (input.startsWith("event")) {
                        if (Duke.isEmpty(input.substring(5))){
                            throw new DukeException("☹ OOPS!!! The description of a event cannot be empty.");
                        }
                        if (!input.contains("/at")) {
                            throw new DukeException("☹ OOPS!!! The date of a event cannot be empty.");
                        }
                        int atPos = input.indexOf("/at");
                        String description = input.substring(6,atPos);
                        String at = input.substring(atPos+4);
                        Event event = new Event(description, at);
                        TaskList.add(event);
                        result = String.format("Got it. I've added this task:\n\t%s\nNow you have %d tasks in the list.",
                                event.toString(), TaskList.size());
                    } else {
                        throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                    }
                }
            }
            // print result
            horizontalLine();
            System.out.println(result);
            horizontalLine();
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
