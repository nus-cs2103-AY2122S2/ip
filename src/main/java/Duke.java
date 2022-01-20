import java.util.Scanner;
import java.util.ArrayList;

public class Duke
{

    public static boolean isInteger(String input)
    {
        try {
            Integer.parseInt(input);
            return true;
        }
        catch( Exception e ) {
            return false;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Task> toDoList = new ArrayList<>();


        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("enter a command");

        String userInput = sc.nextLine();
        String[] split = userInput.split(" ");

        while (!(userInput.equals("bye")))
        {
            if (userInput.equals("list"))
            {
                for (int i = 0; i < toDoList.size(); i++)
                {
                    Task t = toDoList.get(i);
                    System.out.printf("%d. " + t.toString(), i + 1);
                }
            }
            else if (split[0].equals("unmark") && split.length > 1 && isInteger(split[1]))
            {
                int item = Integer.parseInt(split[1]);
                Task t = toDoList.get(item - 1);
                t.unmark();
                System.out.printf("Boo! more work to do: %s\n", t.getName());
            }
            else if (split[0].equals("mark") && split.length > 1 && isInteger(split[1]))
            {
                int item = Integer.parseInt(split[1]);
                Task t = toDoList.get(item - 1);
                t.mark();
                System.out.printf("great job! I've marked this task as done: %s\n", t.getName());
            }
            else if (split[0].equals("todo") && split.length > 1)
            {
                String name = userInput.substring(5);
                ToDo t = new ToDo(name);
                toDoList.add(t);
                System.out.printf("task added:\n%s", t);
                System.out.printf("you now have %d tasks\n",toDoList.size());

            }
            else if (split[0].equals("deadline") && split.length > 1 && userInput.contains("/"))
            {
                String item = userInput.substring(9);
                String[] divide = item.split("/");
                String name = divide[0];
                String dueDate = divide[1];
                Deadline d = new Deadline(name, dueDate.substring(3));
                toDoList.add(d);
                System.out.printf("task added:\n%s", d.toString());
                System.out.printf("you now have %d tasks\n", toDoList.size());

            }
            else if (split[0].equals("event") && split.length > 1 && userInput.contains("/"))
            {
                String item = userInput.substring(6);
                String[] divide = item.split("/");
                String name = divide[0];
                String time = divide[1];
                Event e = new Event(name, time.substring(3));
                toDoList.add(e);
                System.out.printf("task added:\n%s", e.toString());
                System.out.printf("you now have %d tasks\n", toDoList.size());
            }
            else
            {
                System.out.println("task added: " + userInput);
                toDoList.add(new Task(userInput));
            }
            userInput = sc.nextLine();
            split = userInput.split(" ");
        }
        System.out.println("goodbye!");
    }


}

