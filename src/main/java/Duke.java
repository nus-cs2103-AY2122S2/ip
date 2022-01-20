import java.util.ArrayList;
import java.util.Scanner;

class Duke {
    public static void main(String[] args) {

        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(logo);

        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");

        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();

        ArrayList<Task> tasks = new ArrayList<Task>();
        String[] input = str.split(" ");
        //TaskList tasks = new TaskList();

        while (!input[0].equals("bye")) {
            if (input[0].equals("list")) {

                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < tasks.size(); i++) {
                    System.out.println((i + 1) + ". " + tasks.get(i).toString());

                }
            } else if (input[0].equals("mark")) {
                System.out.println("Nice! I've marked this task as done: ");
            Task temp = tasks.get(Integer.parseInt(input[1]) - 1);
            temp.markAsDone();

                System.out.println(temp.toString());

            } else if (input[0].equals("unmark")) {
                System.out.println("OK, I've marked this task as not done yet:");
                Task temp = tasks.get(Integer.parseInt(input[1]) - 1);
                temp.unmark();
                System.out.println(temp.toString());

            } else {
                tasks.add(new Task(str));
            }
            str = sc.nextLine();
             input = str.split(" ");
        }

        System.out.println("Bye. Hope to see you again soon!");
        sc.close();

    }




}

