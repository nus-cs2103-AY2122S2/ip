import java.util.Arrays;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
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
            } else if(input.substring(0,4).equals("mark")){
                int toBeMarked = Integer.parseInt(input.substring(5));
                Task currentTask = tasks[toBeMarked - 1];
                currentTask.markAsDone();
                System.out.println("Nice! I've marked this task as done:");
                System.out.println(currentTask);
            } else if(input.substring(0,6).equals("unmark")){
                int toBeUnmarked = Integer.parseInt(input.substring(7));
                Task currentTask = tasks[toBeUnmarked - 1];
                currentTask.markAsUndone();
                System.out.println("OK, I've marked this task as not done yet: ");
                System.out.println(currentTask);
            } else if(input.substring(0, 4).equals("todo")) {
                String[] arrOfStr = input.split(" ", 2);
                tasks[counter] = new Todo(arrOfStr[1]);
                counter = counter + 1;
                System.out.println("Got it. I've added this task:");
                System.out.println(tasks[counter]);
                System.out.println("Now you have " + String.valueOf(counter + 1) + " tasks in the list.");
            } else if(input.substring(0, 8).equals("deadline")) {
                String[] arrOfStr = input.split(" ", 2);
                String[] arrOfStr2 = arrOfStr[1].split("/", 2);
                String[] arrOfStr3 = arrOfStr2[1].split(" ", 2);

                tasks[counter] = new Deadline(arrOfStr2[0], arrOfStr3[1]);
                counter = counter + 1;
                System.out.println("Got it. I've added this task:");
                System.out.println(tasks[counter]);
                System.out.println("Now you have " + String.valueOf(counter + 1) + " tasks in the list.");

            } else {
                tasks[counter] = new Task(input);
                counter = counter + 1;
                System.out.println("added: " + input);
            }
        }
    }
}
