import java.util.Scanner;
import java.util.ArrayList;

public class Puke {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

    String logo = "   ____        _        \n"
            + "  |  _ \\ _   _| | _____ \n"
            + "  | |_| | | | | |/ / _ \\\n"
            + "  | |__/| |_| |   <  __/\n"
            + "  |_|    \\__,_|_|\\_\\___|\n";

    System.out.print("____________________________________________________________\n"
            + logo + "Hello! I'm Puke, your friendly neighbourhood chatbot!\n"
            + "What do you want to do?\n"
            + "____________________________________________________________\n");

    ArrayList<Task> tasks = new ArrayList<>();

    while (true) {
      System.out.print("> ");
      String input = sc.nextLine();
      String command = input.split(" ")[0]; // get the first word of the input

      if (command.equals("bye")) {
        break;
      } else if (command.equals("list")) {
        list_tasks(tasks);
      } else if (command.equals("mark")) {
        mark_task(tasks, Integer.parseInt(input.split(" ")[1]));
      } else if (command.equals("unmark")) {
        unmark_task(tasks, Integer.parseInt(input.split(" ")[1]));
      } else {
        System.out.println("added: " + input);
        tasks.add(new Task(input));
      }

      System.out.println("____________________________________________________________");
    }
    System.out.println("Alright bye. Come back to Puke anytime!\n____________________________________________________________");
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
    System.out.println("Kudos! I've marked this task as done:\n  " + t.toString());
  }

  public static void unmark_task(ArrayList<Task> tasks, int taskNo) {
    Task t = tasks.get(taskNo - 1);
    t.unmark();
    System.out.println("Alright, I've marked this task as not done yet:\n  " + t.toString());
  }
}
