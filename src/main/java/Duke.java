import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
  static ArrayList<Task> taskList = new ArrayList<>();
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
      String cmd = sc.next();
      String[] fields;
      Task t;

      switch (cmd) {
        case "bye":
          say("Goodbye, old friend");
          break MainLoop;

        case "list":
          sc.nextLine();
          say("Here are the tasks in your list:");
          for (int i = 0; i < taskList.size(); i++) {
            String str = String.format("    %d. %s",
                i + 1, taskList.get(i).toString());
            System.out.println(str);
          }
          break;

        case "mark":
          t = taskList.get(sc.nextInt() - 1);
          t.done();
          sc.nextLine();
          say("I've marked this task as done:");
          say(t.toString());
          break;

        case "unmark":
          t = taskList.get(sc.nextInt() - 1);
          t.undone();
          sc.nextLine();
          say("I've marked this task as undone:");
          say(t.toString());
          break;

        case "todo":
          String name = sc.nextLine();
          if (name.equals(" ")) {
            say("The description of a todo cannot be empty");
            break;
          }
          t = new ToDo(sc.nextLine());
          taskList.add(t);
          say("added: " + t.toString());
          break;

        case "deadline":
          fields = sc.nextLine().split(" /by ");
          t = new Deadline(fields[0], fields[1]);
          taskList.add(t);
          say("added: " + t.toString());
          break;

        case "event":
          fields = sc.nextLine().split(" /at ");
          t = new Event(fields[0], fields[1]);
          taskList.add(t);
          say("added: " + t.toString());
          break;

        case "delete":
          t = taskList.remove(sc.nextInt() - 1);
          sc.nextLine();
          say("I have remove this task: ");
          say(t.toString());
          break;

        default:
          say("The command you are using is not in the archives.");
          sc.nextLine();
          break;
      }
    }

    sc.close();
  }
}