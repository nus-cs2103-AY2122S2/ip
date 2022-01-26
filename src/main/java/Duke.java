import java.util.Arrays;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) throws DukeException {
        Scanner myObj = new Scanner(System.in);
        int counter = 0;
        Task[] tasks = new Task[100];

        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");

        while (true) {
            String input = myObj.nextLine();
            if (input.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            } else if (input.equals("list")){
                System.out.println("Here are the tasks in your list:");
                for(int i = 0; i < counter; i++) {
                    System.out.println( Integer.toString(i + 1) + "." + tasks[i]);
                }
            } else if(input.length() > 3 && input.substring(0,4).equals("mark")){
                try {
                    int toBeMarked = Integer.parseInt(input.substring(5));
                    Task currentTask = tasks[toBeMarked - 1];
                    currentTask.markAsDone();
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println(currentTask);
                } catch (StringIndexOutOfBoundsException e) {
                    throw new DukeException("☹ OOPS!!! Please tell me which task to mark as done!");
                }

            } else if(input.length() > 5 && input.substring(0,6).equals("unmark")){
                try {
                    int toBeUnmarked = Integer.parseInt(input.substring(7));
                    Task currentTask = tasks[toBeUnmarked - 1];
                    currentTask.markAsUndone();
                    System.out.println("OK, I've marked this task as not done yet: ");
                    System.out.println(currentTask);
                } catch (StringIndexOutOfBoundsException e) {
                    throw new DukeException("☹ OOPS!!! Please tell me which task you want me to mark as not done!");

                }

            } else if(input.length() > 3 && input.substring(0, 4).equals("todo")) {
                try {
                    String[] arrOfStr = input.split(" ", 2);
                    tasks[counter] = new Todo(arrOfStr[1]);
                    counter = counter + 1;
                    System.out.println("Got it. I've added this task:");
                    System.out.println(tasks[counter - 1]);
                    if (counter == 1) {
                        System.out.println("Now you have " + String.valueOf(counter) + " task in the list.");
                    } else {
                        System.out.println("Now you have " + String.valueOf(counter) + " tasks in the list.");
                    }

                } catch(ArrayIndexOutOfBoundsException e) {
                    throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
                }

            } else if(input.length() > 7  && (input.substring(0, 8).equals("deadline"))) {
                try {
                    String[] arrOfStr = input.split(" ", 2);
                    String[] arrOfStr2 = arrOfStr[1].split("/", 2);
                    String[] arrOfStr3 = arrOfStr2[1].split(" ", 2);

                    tasks[counter] = new Deadline(arrOfStr2[0], arrOfStr3[1]);
                    counter = counter + 1;
                    System.out.println("Got it. I've added this task:");
                    System.out.println(tasks[counter - 1]);
                    if (counter == 1) {
                        System.out.println("Now you have " + String.valueOf(counter) + " task in the list.");
                    } else {
                        System.out.println("Now you have " + String.valueOf(counter) + " tasks in the list.");
                    }
                } catch(ArrayIndexOutOfBoundsException e) {
                    throw new DukeException("☹ OOPS!!! Please provide a deadline for your task.");

                }
            } else if(input.length() > 4 && input.substring(0, 5).equals("event")) {
                try {
                    String[] arrOfStr = input.split(" ", 2);
                    String[] arrOfStr2 = arrOfStr[1].split("/", 2);
                    String[] arrOfStr3 = arrOfStr2[1].split(" ", 2);

                    tasks[counter] = new Event(arrOfStr2[0], arrOfStr3[1]);
                    counter = counter + 1;
                    System.out.println("Got it. I've added this task:");
                    System.out.println(tasks[counter - 1]);
                    if (counter == 1) {
                        System.out.println("Now you have " + String.valueOf(counter) + " task in the list.");
                    } else {
                        System.out.println("Now you have " + String.valueOf(counter) + " tasks in the list.");
                    }
                } catch(ArrayIndexOutOfBoundsException e) {
                    throw new DukeException("☹ OOPS!!! Please provide a timing for your event.");
                }
            } else {
                throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
        }
    }
}
