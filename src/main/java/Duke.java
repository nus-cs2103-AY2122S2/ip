import java.util.ArrayList;
import java.util.Scanner;

class Duke {

    public static Deadline createDeadline(String [] input) {
       String description = "";
       String by = "";
        StringBuilder sb = new StringBuilder();
        for(int i = 1; i < input.length; i++){
            if(!(input[i]).equals("/by")) {
                sb.append(input[i] + " ");

            }
            else {
                description = sb.toString();
                sb = new StringBuilder();
            }

        }

      by = sb.toString();
     return new Deadline(description, by);
    }

    public static Todo createTodo(String [] input) {
        String description = "";
        StringBuilder sb = new StringBuilder();
        for(int i = 1; i < input.length; i++) {
            sb.append(input[i] + " ");
        }

         description = sb.toString();
        return new Todo(description);

    }

    public static Event createEvent(String [] input) {
        String description = "";
        String at = "";

        StringBuilder sb = new StringBuilder();
        for(int i = 1; i < input.length; i++){
            if(!(input[i]).equals("/at")) {
                sb.append(input[i] + " ");

            }
            else {
                description = sb.toString();
                sb = new StringBuilder();
            }

        }

        at = sb.toString();
        return new Event(description, at);
    }




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

try {
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

    } else if (input[0].equals("todo")) {
        if (input.length < 2) {
            throw new EmptyArgumentException("todo");
        }
        Todo temp = createTodo(input);
        System.out.println(temp.toString());
        tasks.add(temp);
        System.out.println("Got it. I've added this task:");
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
    } else if (input[0].equals("event")) {
        if (input.length < 2) {
            throw new EmptyArgumentException("todo");
        }
        Event temp = createEvent(input);
        System.out.println(temp.toString());
        tasks.add(temp);
        System.out.println("Got it. I've added this task:");
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
    } else if (input[0].equals("deadline")) {
        if (input.length < 2) {
            throw new EmptyArgumentException("todo");
        }
        Deadline temp = createDeadline(input);
        System.out.println(temp.toString());
        tasks.add(temp);
        System.out.println("Got it. I've added this task:");
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
    } else {
        throw new InvalidArgumentException();
    }
} catch (DukeException exception)  {
    System.out.println(exception.getMessage());
}
            str = sc.nextLine();
             input = str.split(" ");
        }

        System.out.println("Bye. Hope to see you again soon!");
        sc.close();

    }




}

