import java.util.Arrays;
import java.util.Scanner;

public class Duke {
    static String line = "------------------------------------";
    static Scanner scanner = new Scanner(System.in);
    static Task[] arr = new Task[100];

    public static <T> int getLength(T[] arr){
        int count = 0;
        for(T el : arr)
            if (el != null)
                ++count;
        return count;
    }

    public static void main(String[] args) {

        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("How may I help you today?");
        System.out.println(line);

        while(true) {
            String input = scanner.nextLine();

            // Exit Feature
            if (input.equals("bye")) {
                System.out.println("Goodbye! I'll be here if you need anything else.");
                System.out.println(line);
                break;
            }

            // List Feature
            else if (input.equals("list")) {
                for (int i = 0; i < arr.length; i++) {
                    if (arr[i] == null) {
                        break;
                    }
                    System.out.println(i+1 + "." + arr[i].toString());
                }
                System.out.println(line);
            }

            // Mark & Unmark Feature
            else if (input.contains("mark") || input.contains("unmark")) {
                try {
                    String s = input.replaceAll("\\D+", "");
                    int clean = Integer.parseInt(s) - 1;  // Parse to find what number in list to toggle
                    // Edge Case
                    if (arr[clean] == null) {
                        System.out.println("Error! No tasked added");
                    }
                    // Mark
                    else if (input.toCharArray()[0] != 'u') {
                        arr[clean].setMarked();
                        System.out.println("Nice! I've marked this task as done:\n " +
                                "   " + arr[clean].toString());
                    }
                    // Unmark
                    else {
                        arr[clean].setUnmarked();
                        System.out.println("OK, I've marked this task as not done yet:\n " +
                                "   " + arr[clean].toString());
                    }
                }
                 catch (NumberFormatException e) {
                     System.out.println("Don't be cheeky. Please write something that makes sense.");
                 }
                System.out.println(line);
            }

            // Deadline Feature
            else if (input.contains("deadline")) {
                try {
                    String nondead = input.split("deadline ", 2)[1]; // Remove instruction
                    String task = nondead.split(" /by ", 2)[0]; // Split to task
                    String date = nondead.split(" /by ", 2)[1]; // Split to date
                    Deadline newDeadline = new Deadline(task, date);
                    int itemsInList = getLength(arr) + 1;
                    System.out.println("Got it! I've added this task: \n    " +
                            newDeadline.toString() + "\n" +
                            "Now you have " + itemsInList + " tasks in the list.");
                    arr[getLength(arr)] = newDeadline;
                }
                 catch (IndexOutOfBoundsException e) {
                    System.out.println("Don't be cheeky. Give me a deadline to stress you over.");
                }
                System.out.println(line);
            }

            // Event Feature
            else if (input.contains("event")) {
                try {
                    String nonevent = input.split("event ", 2)[1]; // Remove instruction
                    String task = nonevent.split(" /at ", 2)[0]; // Split to task
                    String date = nonevent.split(" /at ", 2)[1]; // Split to date
                    Event newEvent = new Event(task, date);
                    int itemsInList = getLength(arr) + 1;
                    System.out.println("Got it! I've added this task: \n    " +
                            newEvent.toString() + "\n" +
                            "Now you have " + itemsInList + " tasks in the list.");
                    arr[getLength(arr)] = newEvent;
                }
                 catch (IndexOutOfBoundsException e) {
                    System.out.println("Don't be cheeky. Give me an event to record.");
                }
                System.out.println(line);
            }

            // To Do Feature
            else if (input.contains("todo")) {
                try {
                    String word = input.split(" ", 2)[1]; // Remove instruction
                    ToDo newToDo = new ToDo(word);
                    int itemsInList = getLength(arr) + 1;
                    System.out.println("Got it! I've added this task: \n    " +
                            newToDo.toString() + "\n" +
                            "Now you have " + itemsInList + " tasks in the list.");
                    arr[getLength(arr)] = newToDo;
                }
                 catch (IndexOutOfBoundsException e) {
                    System.out.println("Don't be cheeky. Give me something to do.");
                }
                System.out.println(line);
            }

            // Edge Case
            else {
                System.out.println(input + " is not a valid command. Please try again.");
                System.out.println(line);
            }
        }
    }
}


