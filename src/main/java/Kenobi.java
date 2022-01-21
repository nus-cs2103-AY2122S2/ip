import java.util.ArrayList;
import java.util.Scanner;

public class Kenobi {
  static ArrayList<Task> taskList = new ArrayList<>();

  public static void main(String[] args) {
    String logo = " __   ___\n"
        + "/  | /  / _____  _    _  _____  _____    __\n"
        + "|  |/  / / ____// \\  / \\/  _  \\/  _  \\  \\__\\\n"
        + "|     /  | |___ |  \\ | || | | || |_| /_ |  |\n"
        + "|     \\  | ____|| | \\| || | | ||  ___  ||  |\n"
        + "|  |\\  \\ | |___ | |\\ | || |_| || |___| ||  |\n"
        + "\\__/ \\__\\\\_____\\\\_/ \\__/\\_____/\\_______/|__|";
    System.out.println(logo);
    System.out.println("\n    Hello there, how may I help you?");

    Scanner sc = new Scanner(System.in);

    String inString;
    while (true) {
      inString = sc.next();

      if (inString.equals("bye")) {
        System.out.println("    Goodbye, old friend");
        break;
      } else if (inString.equals("list")) {
        sc.nextLine();
        System.out.println("    Here are the tasks in your list:");
        for (int i = 0; i < taskList.size(); i++) {
          String str = String.format("    %d. %s",
              i + 1, taskList.get(i).toString());
          System.out.println(str);
        }
      } else if (inString.equals("mark")) {
        Task t = taskList.get(sc.nextInt() - 1);
        t.done();
        sc.nextLine();
        System.out.println("    I've marked this task as done:");
        System.out.println("    " + t.toString());
      } else if (inString.equals("unmark")) {
        Task t = taskList.get(sc.nextInt() - 1);
        t.undone();
        sc.nextLine();
        System.out.println("    I've marked this task as undone:");
        System.out.println("    " + t.toString());
      } else {
        inString += sc.nextLine();
        taskList.add(new Task(inString));
        System.out.println("    added: " + inString);
      }
    }

    sc.close();
  }
}