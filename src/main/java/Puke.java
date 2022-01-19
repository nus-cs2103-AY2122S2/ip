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

      String argument = null;
      if (input.length > 1) {
        argument = input[1]; // remaining input (if any)
      }

      try {
        switch (command) {
          case "list":
            if (argument == null) {
              list_tasks(tasks);
            } else {
              System.out.println("I don't need any argument for list..");
            }
            break;
          case "mark":
            mark_task(tasks, Integer.parseInt(argument));
            break;
          case "unmark":
            unmark_task(tasks, Integer.parseInt(argument));
            break;
          case "todo":
          case "deadline":
          case "event":
            add_task(tasks, command, argument);
            break;
          case "delete":
            delete_task(tasks, Integer.parseInt(argument));
            break;
          default:
            System.out.println("Are you sure you're making sense?");
            break;
        }
      } catch (DukeException e) {
        System.out.println(e.getMessage());
      } catch (NumberFormatException e) {
        System.out.println("I'll need a valid task number for it..");
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

  public static void mark_task(ArrayList<Task> tasks, int taskNo) throws DukeException {
    if (taskNo < 1 || taskNo > tasks.size()) {
      throw new DukeException(String.format("%d is not a valid task number!", taskNo));
    }
    Task t = tasks.get(taskNo - 1);
    if (t.isDone()) {
      throw new DukeException(String.format("Task #%d is already marked as done!", taskNo));
    }
    t.mark();
    System.out.println("Kudos! I've marked this task as done:\n  " + t);
  }

  public static void unmark_task(ArrayList<Task> tasks, int taskNo) throws DukeException {
    if (taskNo < 1 || taskNo > tasks.size()) {
      throw new DukeException(String.format("%d is not a valid task number!", taskNo));
    }
    Task t = tasks.get(taskNo - 1);
    if (!t.isDone()) {
      throw new DukeException(String.format("Task #%d is not yet marked as done!", taskNo));
    }
    t.unmark();
    System.out.println("Alright, I've marked this task as not done yet:\n  " + t);
  }

  public static void add_task(ArrayList<Task> tasks, String type, String args) throws DukeException {
    if (args == null) {
      throw new DukeException("I'll need a description for the task..");
    }

    Task t;
    if (type.equals("todo")) {
      t = new Todo(args);
    } else {
      String[] taskDetail = args.split("/");

      if (taskDetail.length < 2) {
        throw new DukeException("I'll need a date/time for this task..");
      }

      if (type.equals("deadline")) {
        t = new Deadline(taskDetail[0], taskDetail[1].split(" ", 2)[1]);
      } else {
        t = new Event(taskDetail[0], taskDetail[1].split(" ", 2)[1]);
      }
    }

    tasks.add(t);
    System.out.println("Got it. I've added this task:\n  " + t
                      + "\nNow you have " + Task.getNoOfTasks()
                      + (Task.getNoOfTasks() <= 1 ? " task" : " tasks") + " in the list.");
  }

  public static void delete_task(ArrayList<Task> tasks, int taskNo) throws DukeException {
    if (taskNo < 1 || taskNo > tasks.size()) {
      throw new DukeException(String.format("%d is not a valid task number!", taskNo));
    }
    String taskInfo = tasks.get(taskNo - 1).toString();
    tasks.remove(taskNo - 1);
    Task.removeTask();
    System.out.println("There's no going back now. I've removed this task:\n  " + taskInfo
                      + "\nNow you have " + Task.getNoOfTasks()
                      + (Task.getNoOfTasks() <= 1 ? " task" : " tasks") + " in the list.");
  }
}