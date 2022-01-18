import java.util.Scanner;
import java.util.ArrayList;

public class Puke {
  public static String line = "____________________________________________________________\n";

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

    String logo = "   ____        _        \n"
            + "  |  _ \\ _   _| | _____ \n"
            + "  | |_| | | | | |/ / _ \\\n"
            + "  | |__/| |_| |   <  __/\n"
            + "  |_|    \\__,_|_|\\_\\___|\n";

    System.out.print(line + logo + "Hello! I'm Puke, your friendly neighbourhood chatbot!\n"
            + "What do you want to do?\n" + line);

    ArrayList<Task> tasks = new ArrayList<>();

    while (true) {
      System.out.print("> ");
      String[] input = sc.nextLine().split(" ", 2);
      String command = input[0]; // get the first word of the input

      if (command.equals("bye")) break;
      else if (command.equals("list")) list_tasks(tasks);
      else {
        String argument = input[1]; // remaining input

        switch (command) {
          case "mark":
            mark_task(tasks, Integer.parseInt(argument));
            break;
          case "unmark":
            unmark_task(tasks, Integer.parseInt(argument));
            break;
          default:
            add_task(tasks, command, argument);
            break;
        }
      }

      System.out.print(line);
    }
    System.out.print("Alright bye. Come back to Puke anytime!\n" + line);
  }

  public static void list_tasks(ArrayList<Task> tasks) {
    if (tasks.size() == 0) {
      System.out.println("You have no task right now!");
      return;
    }

    System.out.println("Here are the tasks you have:");
    int i = 1;
    for (Task t : tasks) {
      System.out.println(i + "." + t.toString());
      i++;
    }
  }

  public static void mark_task(ArrayList<Task> tasks, int taskNo) {
    Task t = tasks.get(taskNo - 1);
    t.mark();
    System.out.println("Kudos! I've marked this task as done:\n  " + t);
  }

  public static void unmark_task(ArrayList<Task> tasks, int taskNo) {
    Task t = tasks.get(taskNo - 1);
    t.unmark();
    System.out.println("Alright, I've marked this task as not done yet:\n  " + t);
  }

  public static void add_task(ArrayList<Task> tasks, String type, String args) {
    Task t;
    if (type.equals("todo")) {
      t = new Task(args, 'T', null);
    } else {
      String[] taskDetail = args.split("/");
      if (type.equals("deadline")) {
        t = new Task(taskDetail[0], 'D', taskDetail[1].split(" ", 2)[1]);
      } else {
        t = new Task(taskDetail[0], 'E', taskDetail[1].split(" ", 2)[1]);
      }
    }

    tasks.add(t);
    System.out.println("Got it. I've added this task:\n  " + t
                      + "\nNow you have " + Task.getNoOfTasks() +
                      (Task.getNoOfTasks() <= 1 ? " task" : " tasks") + " in the list.");
  }
}
