import java.util.Arrays;
import java.util.Scanner;

public class CommandParser {
  private Scanner scanner;
  private TaskModule taskModule;
  private String lineBreak = "\n----------------------------------------\n";

  public CommandParser() {
    this.scanner = new Scanner(System.in);
    this.taskModule = new TaskModule();
  }

  public void start() {
    while (scanner.hasNextLine()) {
      String commands = scanner.nextLine().trim();
      String[] commandsArr = commands.split(" ");

      try {
        switch (commandsArr[0]) {
          case "":
            System.out.println("*crickets*");
            break;
          case "bye":
            if (commandsArr.length == 1)
              System.out.println("LUMU: Goodbye. Hope to see you again soon");
              break;
          case "list":
            if (commandsArr.length == 1) taskModule.displayList();
              break;
          case "mark":
            taskModule.markHandler(commandsArr);
            break;
          case "unmark":
            taskModule.unmarkHandler(commandsArr);
            break;
          case "delete":
            taskModule.deleteHandler(commandsArr);
            break;
          case "todo":
          case "deadline":
          case "event":
            taskModule.taskAdder(commands);
            break;
          default:
            throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
      } catch (DukeException e) {
        System.out.println(e.getMessage());
      }
      System.out.println(lineBreak);
    }
  }
}
