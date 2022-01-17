import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private final Scanner scanner = new Scanner(System.in);
    private final ArrayList<Task> list = new ArrayList<>(); // arraylist to keep track of task
    private boolean endProgram = false; // state to terminate the program
    private String command = "";
    private String description = "";
    private String time = "";

    public static void main(String[] args) {
        new Duke().startProgram();
    }

    private void startProgram() {
        welcomeMessage();
        while(!endProgram) {
            String input = scanner.nextLine(); // user input

            // handle user input to command, description and time of task if applicable
            int cutPoint = input.indexOf(' ');
            int cutPoint2 = input.indexOf('/');
            processInput(input, cutPoint, cutPoint2);

            // exit program when user input "bye"
            if(command.equals("bye")) {
                endProgram();
                endProgram = true;
                break;
            }

            // list out the tasks
            if(command.equals("list")) {
                listTask(list);
                continue;
            }

            // mark certain task as done
            if(command.startsWith("mark")) {
                int n = Integer.parseInt(description.replaceAll("[\\D]", ""));
                markDone(n - 1, list);
                continue;
            }

            // mark certain task as not done yet
            if(command.startsWith("unmark")) {
                int n = Integer.parseInt(description.replaceAll("[\\D]", ""));
                markUndone(n - 1, list);
                continue;
            }

            // create ToDoTask
            if(command.equals("todo")) {
                addTask(new ToDoTask(description), list);
                continue;
            }

            // create DeadlineTask
            if(command.equals("deadline")) {
                addTask(new DeadlineTask(description, time), list);
                continue;
            }

            // create EventTask
            if(command.equals("event")) {
                addTask(new EventTask(description, time), list);
                continue;
            }

            // Invalid command inputs result
            invalidInput();
        }

        // close the scanner
        scanner.close();
    }

    private void processInput(String input, int cutPoint, int cutPoint2) {
        if(cutPoint != -1) {
            // sentence input
            command = input.substring(0, cutPoint);
            if(cutPoint2 != -1) {
                // has time input
                description = input.substring(cutPoint + 1, cutPoint2 - 1);
                time = input.substring(cutPoint2 + 1);
            } else {
                // no time input
                description = input.substring(cutPoint + 1);
            }
        } else {
            // single word input
            command = input;
        }
    }

    private void drawDivider() {
        System.out.println("________________________________________");
    }

    private void welcomeMessage() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        drawDivider();
        System.out.println("Hello! I'm Duke \n" + "What can I do for you?");
        drawDivider();
    }

    private void endProgram() {
        drawDivider();
        System.out.println("    " + "Bye. Hope to see you again soon!");
        drawDivider();
    }

    private void listTask(ArrayList<Task> list) {
        drawDivider();
        if(list.size() == 0) {
            System.out.println("Nothing on your list!");
        } else {
            System.out.println("Here are the tasks in your list:");
            for(int i = 0; i < list.size(); i++) {
                System.out.printf("%d.%s%n", i + 1, list.get(i).toString());
            }
        }
        System.out.printf("Now you have %d tasks in the list%n", list.size());
        drawDivider();
    }

    private void addTask(Task task, ArrayList<Task> list) {
        drawDivider();
        list.add(task);
        System.out.println("Got it. I've added this task: ");
        System.out.println(task.toString());
        System.out.printf("Now you have %d tasks in the list%n", list.size());
        drawDivider();
    }

    private void markDone(int i, ArrayList<Task> list) {
        if(i < 0 || i >= list.size()) {
            // handle index out of bound
            drawDivider();
            System.out.println("Invalid input");
            drawDivider();
        } else {
            drawDivider();
            list.get(i).markDone();
            System.out.println("Nice! I've marked this task as done:");
            System.out.println(list.get(i).toString());
            drawDivider();
        }
    }

    private void markUndone(int i, ArrayList<Task> list) {
        if(i < 0 || i >= list.size()) {
            // handle index out of bound
            drawDivider();
            System.out.println("Invalid input");
            drawDivider();
        } else {
            drawDivider();
            list.get(i).markUndone();
            System.out.println("OK, I've marked this task as not done yet:");
            System.out.println(list.get(i).toString());
            drawDivider();
        }
    }

    private void invalidInput() {
        drawDivider();
        System.out.println("Invalid input");
        drawDivider();
    }
}
