import java.util.*;

public class Duke {
    public static void main(String[] args) {
        Scanner sc= new Scanner(System.in);
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("What can I do for you?");

        ArrayList<Task> tasks = new ArrayList<> ();

        while (true){
            String str= sc.nextLine();

            // quit
            if (str.equals("bye")) {
                break;
            }

            // list
            else if (str.equals("list")) {
                for (int i = 0; i < tasks.size(); i++) {
                    Task currentTask = tasks.get(i);
                    int index = i + 1;
                    System.out.println(index + "." + currentTask.getStatusIcon() + " " + currentTask.toString());
                }
            }

            // mark
            else if (str.length() >= 4 && str.substring(0,4).equals("mark")) {
                String[] markedTask = str.split(" ");
                int indexMarked = Integer.parseInt(markedTask[1]) - 1;
                Task currentTask = tasks.get(indexMarked);
                currentTask.isDone = true;
                System.out.println("Nice! I've marked this task as done:");
                System.out.println(currentTask.getStatusIcon() + " " + currentTask.toString());
            }


            // unmark
            else if (str.length() >= 6 && str.substring(0,6).equals("unmark")) {
                String[] markedTask = str.split(" ");
                int indexMarked = Integer.parseInt(markedTask[1]) - 1;
                Task currentTask = tasks.get(indexMarked);
                currentTask.isDone = false;
                System.out.println("OK, I've marked this task as not done yet:");
                System.out.println(currentTask.getStatusIcon() + " " + currentTask.toString());
            }

            // repeat what the user says and save to tasks list
            else {
                tasks.add(new Task(str));
                System.out.println("added: " + str);
            }
        }
        System.out.println("Bye. Hope to see you again soon!\n");


    }
}
