
import java.sql.SQLOutput;
import java.util.*;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        String lines = "____________________________________________________________";
        String endline = "____________________________________________________________\n";
        System.out.println(lines);
        System.out.println("Hello from\n" + logo);
        Scanner sc = new Scanner(System.in);
        ArrayList<Task> allTasks = new ArrayList<>();
        System.out.println("How may I serve you?");
        System.out.println(endline);
        while (true) {

            String input = sc.nextLine();
            String[] parts = input.split(" ");

            //level-1 feature: for exit
            if(input.equals("bye")) {
                System.out.println(lines);
                System.out.println("Bye! See you again");
                System.out.println(endline);
                break;
            }

            if(input.equals("list")) {
                System.out.println(lines);
                System.out.println("Here are all your tasks:");
                for (int i = 0; i < allTasks.size(); i++) {
                    System.out.println((i + 1) + "." + allTasks.get(i).getStatusIcon() + allTasks.get(i).getDescription());
                }
//                for(Task t :allTasks) {
//                    System.out.println("1. " + t.getDescription());
//                }
                System.out.println(lines + "\n");
                continue;
            }
            if(parts[0].equals("mark")) {
                Task markTask = allTasks.get(Integer.parseInt(parts[1]) - 1);
                System.out.println(lines);
                if (markTask.isDone == true) {
                    System.out.println("You have already done this task!");
                    System.out.println(markTask.markAsDone());
                    continue;
                }
                System.out.println("Good Job! You have marked this task as done!");
                System.out.println(markTask.markAsDone());
                System.out.println(endline);
                continue;
            }
            if(parts[0].equals("unmark")) {
                Task markTask = allTasks.get(Integer.parseInt(parts[1])-1);
                System.out.println(lines);
                if (markTask.isDone == false) {
                    System.out.println("This task is already in undone status");
                    System.out.println(markTask.markAsUndone());
                    continue;
                }
                System.out.println("OK, I have marked this as not done yet:");
                System.out.println(markTask.markAsUndone());
                System.out.println(endline);
                continue;
            }

            //
            System.out.println(lines);
            allTasks.add(new Task(input));
            System.out.println("added: " + input);
            System.out.println(endline);

//            System.out.println(input);

        }

    }
}
