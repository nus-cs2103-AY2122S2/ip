import java.util.Scanner;

public class Duke {
  static TaskList taskList = new TaskList();
  static String logo = " __   ___\n"
      + "/  | /  / _____  _    _  _____  _____    __\n"
      + "|  |/  / / ____// \\  / \\/  _  \\/  _  \\  \\__\\\n"
      + "|     /  | |___ |  \\ | || | | || |_| /_ |  |\n"
      + "|     \\  | ____|| | \\| || | | ||  ___  ||  |\n"
      + "|  |\\  \\ | |___ | |\\ | || |_| || |___| ||  |\n"
      + "\\__/ \\__\\\\_____\\\\_/ \\__/\\_____/\\_______/|__|\n";

  static void say(String line) {
    System.out.println("    " + line);
  }

  public static void main(String[] args) {
    System.out.println(logo);
    say("Hello there, how may I help you?");

    Scanner sc = new Scanner(System.in);

    MainLoop: while (true) {
      String[] cmd = sc.nextLine().split("\\s", 2);
      String[] fields;
      Task t;

      switch (cmd[0]) {
        case "bye":
          say("Goodbye, old friend");
          break MainLoop;

        case "list":
          say("Here are the tasks in your list:");
          say(taskList.toString());
          break;

        case "mark":
          t = taskList.get(Integer.parseInt(cmd[1]) - 1);
          t.done();
          say("I've marked this task as done:");
          say(t.toString());
          break;

        case "unmark":
          t = taskList.get(Integer.parseInt(cmd[1]) - 1);
          t.undone();
          say("I've marked this task as undone:");
          say(t.toString());
          break;

        case "todo":
          String name = cmd[1];
          if (name == null) {
            say("The description of a todo cannot be empty");
            break;
          }
          t = new ToDo(name);
          taskList.add(t);
          say("added: " + t);
          break;

        case "deadline":
          fields = cmd[1].split(" /by ");
          t = new Deadline(fields[0], fields[1]);
          taskList.add(t);
          say("added: " + t);
          break;

        case "event":
          fields = cmd[1].split(" /at ");
          t = new Event(fields[0], fields[1]);
          taskList.add(t);
          say("added: " + t);
          break;

        case "delete":
          t = taskList.remove(Integer.parseInt(cmd[1]) - 1);
          say("I have removed this task: ");
          say(t.toString());
          break;

        default:
          say("The command you are using is not in the archives.");
          break;
      }
    }

    sc.close();
  }
}