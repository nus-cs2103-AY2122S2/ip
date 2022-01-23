import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;


public class Alfred {
  // class constants
  private static final String GREETING = "Hello! My name is Alfred.\n"
      + "How can I be of service?";
  private static final String BYE = "Bye! Hope I was of service.";
  private static final int BREAK_CHAR_LENGTH = 100;
  private final String BREAK_LINE = this.line();
  private String projectRoot = ".";
  private String DATA_PATH = String.join(File.separator, projectRoot, "data", "Alfred.txt");

  // functional attributes
  ArrayList<Task> taskList;

  Alfred() {
    this.taskList = new ArrayList<Task>();
  }

  private void saveToFile() throws IOException {
    System.out.println(new File(".").getCanonicalPath());
    FileWriter fw;
    try {
      fw = new FileWriter(this.DATA_PATH);
      String newList = this.listToString();
      fw.write(newList);
      fw.close();
    } catch (FileNotFoundException fe) {
      System.out.println("Alfred.txt not created. Will create at: " + this.DATA_PATH);
      try {
        // create the file
        File output = new File(this.DATA_PATH);
        output.createNewFile();
        fw = new FileWriter(output);
        String newList = this.listToString();
        fw.write(newList);
        fw.close();

        // write to it
      } catch (IOException ioe) {
        throw ioe;
      }
    }
  }

  private String line() {
    String out = "";
    for (int i = 0; i < Alfred.BREAK_CHAR_LENGTH; i++) {
      out += "-";
    }
    out += "\n";
    return out;
  }

  private void sandwichAndPrint(String text) {
    String out = "";
    out += this.BREAK_LINE;
    out += text + "\n";
    out += this.BREAK_LINE;
    System.out.println(out);
  }

  private void greeting() {
    this.sandwichAndPrint(Alfred.GREETING);
  }

  private void bye() {
    this.sandwichAndPrint(Alfred.BYE);
  }

  private void addTask(Task task) {
    this.taskList.add(task);
    String text = "Yes sir, I've added this task.\n";
    text += task.toString() + "\n";
    text += this.summarizeList();
    this.sandwichAndPrint(text);
  }

  private void addTodo(String description) {
    Task task = new ToDo(description);
    this.addTask(task);
  }

  private void addDeadline(String description, String deadline) {
    Task task = new Deadline(description, deadline);
    this.addTask(task);
  }

  private void addEvent(String description, String dateAndTime) {
    Task task = new Event(description, dateAndTime);
    this.addTask(task);
  }

  private String summarizeList() {
    return "Now you have " + this.taskList.size() + " task(s) in the your list.";
  }


  private void listTasks() {
    String out = "";
    out += this.BREAK_LINE;
    out += "Sir, here are the things you need to do:\n";
    out += this.listToString();
    out += this.BREAK_LINE;
    System.out.println(out);
  }

  private String listToString() {
    String out = "";
    for (int i = 1; i < this.taskList.size() + 1; i++) {
      out += i + ". " + this.taskList.get(i - 1).toString() + "\n";
    }
    return out;
  }

  private void markTask(int taskId) {
    // update data state
    this.taskList.get(taskId).mark_complete();

    // return representation
    String text = "Good job sir. I've marked this as complete.\n";
    text += this.taskList.get(taskId).toString();
    this.sandwichAndPrint(text);
  }

  private void unmarkTask(int taskId) {
    // update data state
    this.taskList.get(taskId).mark_incomplete();

    // return representation
    String text = "I see, no worries sir. I've marked this as to-be-done.\n";
    text += this.taskList.get(taskId).toString();
    this.sandwichAndPrint(text);
  }

  private void deleteTask(int taskId) {
    // update data state
    Task task = this.taskList.remove(taskId);

    // return representation
    String text = "Noted sir. I've removed the following task:\n";
    text += task.toString();
    this.sandwichAndPrint(text);
  }

  private void callCommand(String command, String[] arguments) throws InvalidCommandException {
    int taskId;
    switch (command) {
      case "list":
        this.listTasks();
        break;
      case "mark":
        taskId = Integer.valueOf(arguments[1]) - 1;
        this.markTask(taskId);
        break;
      case "unmark":
        taskId = Integer.valueOf(arguments[1]) - 1;
        this.unmarkTask(taskId);
        break;
      case "delete":
        taskId = Integer.valueOf(arguments[1]) - 1;
        this.deleteTask(taskId);
        break;
      case "todo":
        this.addTodo(arguments[0]);
        break;
      case "event":
        this.addEvent(arguments[0], arguments[1]);
        break;
      case "deadline":
        this.addDeadline(arguments[0], arguments[1]);
        break;
      default:
        throw new InvalidCommandException();
    }
    try {
      this.saveToFile();
    } catch (IOException ioe){
      System.out.println("Something went wrong trying to save the file: " + ioe.getMessage());
    }
  }

  private void readInput(String input)
      throws InvalidCommandException, InvalidInputException, InvalidIndexException,
      MissingInputException {
    // read in arguments
    String[] arguments = input.split(" ");
    String command = arguments[0];

    // case by case, check for valid input
    // LIST
    if ((command.equals("list")) && (arguments.length == 1)) {
      // do nothing

    // (UN)MARK and DELETE
    } else if (command.equals("mark") || command.equals("unmark")
        || command.equals("delete")) {
      if (arguments.length != 2) {
        throw new InvalidInputException();
      }
      int taskId;
      try {
        taskId = Integer.valueOf(arguments[1]) - 1;
      } catch (NumberFormatException nfe) {
        throw new InvalidInputException();
      }
      if (taskId >= this.taskList.size()) {
        throw new InvalidIndexException();
      }

    // DEADLINE
    } else if (command.equals("deadline")) {
      String s = input.substring(8); // select those after keyword
      arguments = s.split(" /by ");
      arguments = Arrays.stream(arguments).filter(in -> !in.isEmpty())
          .toArray(String[]::new); // filter away empty strings
      if (arguments.length != 2) {
        throw new InvalidInputException();
      }

    // T0D0
    } else if (command.equals("todo")) {
      String description = input.substring(4);
      if ((description.length() < 1) || description.split(" ").length == 0) {
        throw new MissingInputException();
      }
      arguments = new String[]{description}; // wrap

    // EVENT
    } else if (command.equals("event")) {
      String s = input.substring(5);
      arguments = s.split(" /at ");
      arguments = Arrays.stream(arguments).filter(in -> !in.isEmpty())
          .toArray(String[]::new); // filter away empty strings
      if (arguments.length != 2) {
        throw new InvalidInputException();
      }
    }
    this.callCommand(command, arguments);
  }

  private void handleAlfredException(AlfredException e) {
    this.sandwichAndPrint(e.getMessage());
  }


  public static void main(String[] args) {
    // initialize
    Scanner sc = new Scanner(System.in);
    Alfred bot = new Alfred();

    // greet
    bot.greeting();

    // input
    String input = sc.nextLine();
    while (!input.equals("bye")) {
      try {
        bot.readInput(input);
      } catch (AlfredException e) {
        bot.handleAlfredException(e);
      }
      input = sc.nextLine();
    }

    // close
    bot.bye();

  }
}
