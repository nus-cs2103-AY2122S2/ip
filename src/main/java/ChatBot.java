import java.util.Random;
import java.util.Scanner;

public class ChatBot {

  private static final String BORDER =
    "******************************************************************************";
  private static final String[] GREETING_QUOTES = {
    "Welcome to my inn",
    "Pull up a chair by the hearth!",
    "Come in, and shut the door, it's cold out there!",
    "Don't be scared. Come in, have a seat!",
  };
  private static final Random RANDOM_INDEX_GENERATOR = new Random();

  public static void main(String[] args) {
    greet();

    TaskList taskList = new TaskList();
    Scanner sc = new Scanner(System.in);
    while (true) {
      prompt();
      System.out.print("\nYou: ");
      String input = sc.nextLine();
      if (input.equals("bye")) {
        chat("Goodbye traveller! Hope to see you again soon!\n\n" + BORDER);
        sc.close();
        break;
      } else if (input.equals("list")) {
        chat("Here you go! \n");
        taskList.summary();
      } else {
        taskList.add(input);
        chat(input + " added to ToDo list!\n");
      }
    }
  }

  public static void greet() {
    System.out.println(BORDER);
    chat("Greetings, traveller!");
    chat(getRandomGreetingQuote());
    chat("I'm the innkeeper and im here to help you with whatever you need.\n");
  }

  public static void chat(String message) {
    System.out.print("\nInnkeeper: ");
    System.out.println(message);
  }

  public static void prompt() {
    System.out.println(BORDER);
    chat("What can I do for you today?");
    System.out.println("\n" + BORDER);
  }

  public static String getRandomGreetingQuote() {
    int randomIndex = RANDOM_INDEX_GENERATOR.nextInt(GREETING_QUOTES.length);
    return GREETING_QUOTES[randomIndex];
  }
}
