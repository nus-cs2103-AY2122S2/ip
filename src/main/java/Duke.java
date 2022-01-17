import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Task> list = new ArrayList<Task>(); // arraylist to keep track of task
        boolean endProgram = false; // state to terminate the program
        welcomeMessage();

        while(!endProgram) {
            String input = scanner.nextLine(); // user input

            // exit program when user input "bye"
            if(input.equals("bye")) {
                endProgram();
                endProgram = true;
                break;
            }

            // list out the tasks
            if(input.equals("list")) {
                listTask(list);
                continue;
            }

            // add task
            addTask(input, list);
        }
    }

    private static void drawDivider() {
        System.out.println("________________________________________");
    }

    private static void welcomeMessage() {
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

    private static void endProgram() {
        drawDivider();
        System.out.println("    " + "Bye. Hope to see you again soon!");
        drawDivider();
    }

    private static void listTask(ArrayList<Task> list) {
        drawDivider();
        if(list.size() == 0) {
            System.out.println("Nothing on your list!");
        } else {
            for(int i = 0; i < list.size(); i++) {
                System.out.println(String.format("%d. %s", i + 1, list.get(i).getTaskName()));
            }
        }
        drawDivider();
    }

    private static void addTask(String taskName, ArrayList<Task> list) {
        drawDivider();
        list.add(new Task(taskName));
        System.out.println("    " + "added: " + taskName);
        drawDivider();
    }
}
