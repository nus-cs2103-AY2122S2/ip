import java.util.ArrayList;
import java.util.Scanner;

public class Kenobi {
  static ArrayList<String> taskList = new ArrayList<>();

  public static void main(String[] args) {
    String logo = " __   __\n"
        + "|  | /  / _____  _    _  _____  _____    __\n"
        + "|  |/  / | ____|| \\  | ||  _  ||  _  |  |  |\n"
        + "|     /  | |___ |  \\ | || | | || |_| |_ |  |\n"
        + "|     \\  | ____|| | \\| || | | ||  ___  ||  |\n"
        + "|  |\\  \\ | |___ | |\\ | || |_| || |___| ||  |\n"
        + "|__| \\__\\|_____||_| \\__||_____||_______||__|";
    System.out.println(logo);
    System.out.println("\n    Hello there, how may I help you?");

    Scanner sc = new Scanner(System.in);

    String inString;
    while (true) {
      inString = sc.nextLine();

      if (inString.equals("bye")) {
        System.out.println("    Goodbye, old friend");
        break;
      } else if (inString.equals("list")) {
        for (int i = 0; i < taskList.size(); i++) {
          String str = String.format("    %d. %s", i + 1, taskList.get(i));
          System.out.println(str);
        }
      } else {
        taskList.add(inString);
        System.out.println("    added: " + inString);
      }
    }

    sc.close();
  }
}