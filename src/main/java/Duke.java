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
                    if (t.getStatus() == 1)
                    {
                        System.out.printf("%d. [X] " + t.getName() + "\n", i + 1);
                    }
                    else
                    {
                        System.out.printf("%d. [ ] " + t.getName() + "\n", i + 1);
                    }
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

