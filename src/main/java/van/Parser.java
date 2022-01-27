package van;

import java.util.ArrayList;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Parser {

  public static Command parseCommand(String parameter) {
    DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
    String[] taskArguments;
    Command output = null;
    String[] parameters = parameter.split("\\s+", 2);
    try {
      switch (parameters[0].toLowerCase()) {
        case "list":
          output = new DisplayCommand();
          break;

        case "deadline":
          if (parameters.length != 2) {
            throw new VanException("Invalid format. Please use: deadline <task> /by <date>");
          }
          taskArguments = parameters[1].split(" /by ");
          if (taskArguments.length != 2) {
            throw new VanException("Invalid format. Please use: deadline <task> /by <date>");
          }
          try {
            LocalDateTime date = LocalDateTime.parse(taskArguments[1], dateFormat);
            output = new AddCommand(new Deadline(taskArguments[0], date));
          } catch (DateTimeParseException e) {
            throw new VanException("Invalid date format. "
              + "Please use dd-MM-YYYY HH:mm e.g. 20-10-2022 1800");
          }
          break;

        case "event":
          if (parameters.length != 2) {
            throw new VanException("Invalid format. Please use: event <task> /at <date>");
          }
          taskArguments = parameters[1].split(" /by ");
          if (taskArguments.length != 2) {
            throw new VanException("Invalid format. Please use: event <task> /at <date>");
          }
          try {
            LocalDateTime date = LocalDateTime.parse(taskArguments[1], dateFormat);
            output = new AddCommand(new Event(taskArguments[0], date));
          } catch (DateTimeParseException e) {
            throw new VanException("Invalid date format. "
              + "Please use dd-MM-YYYY HH:mm e.g. 20-10-2022 1800");
          }
          break;

        case "todo":
          if (parameters.length != 2) {
            throw new VanException("Invalid format. Please use: deadline <task> /by <date>");
          }
          output = new AddCommand(new ToDo(parameters[1]));
          break;
        case "mark":
          if (parameters.length != 2) {
            throw new VanException("Invalid format. Please use mark <task number>");
          }
          try {
            output = new MarkCommand(true, Integer.parseInt(parameters[1]));
          } catch (NumberFormatException ex) {
            output = new InvalidCommand("Please use integer numbers e.g. 1, 2");
          }
          break;
        case "unmark":
          if (parameters.length != 2) {
            throw new VanException("Invalid format. Please use mark <task number>");
          }
          try {
            output = new MarkCommand(false, Integer.parseInt(parameters[1]));
          } catch (NumberFormatException ex) {
            output = new InvalidCommand("Please use integer numbers e.g. 1, 2");
          }
          break;
        case "delete":
          if (parameters.length != 2) {
            throw new VanException("Invalid format. Please use delete <task number>");
          }
          try {
            output = new DeleteCommand(Integer.parseInt(parameters[1]));
          } catch (NumberFormatException ex) {
            output = new InvalidCommand("Please use integer numbers e.g. 1, 2");
          }
          break;
        case "bye":
          output = new ExitCommand();
          break;
        default:
          output = new InvalidCommand("Invalid Command");
      }
    } catch (VanException ex) {
      output = new InvalidCommand(ex.getError());
    }
    return output;
  }

  public static ArrayList<Task>  parseArray(ArrayList<String> taskList) {
    LocalDateTime date;
    DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
    ArrayList<Task> tasks = new ArrayList<>();
    if (taskList != null) {
      for (int i = 0; i < taskList.size(); i++) {
        String fileLine = taskList.get(i);
        String[] taskDetail = fileLine.split("\\|");
        switch (taskDetail[0]) {
          case "E":
            date = LocalDateTime.parse(taskDetail[3], dateFormat);
            tasks.add(new Event(taskDetail[2], date));
            if (taskDetail[1].equals("1")) {
              tasks.get(i).setDone();
            }
            break;
          case "D":
            date = LocalDateTime.parse(taskDetail[3], dateFormat);
            tasks.add(new Deadline(taskDetail[2], date));
            if (taskDetail[1].equals("1")) {
              tasks.get(i).setDone();
            }
            break;
          case "T":
            tasks.add(new ToDo(taskDetail[2]));
            if (taskDetail[1].equals("1")) {
              tasks.get(i).setDone();
            }
            break;
        }
      }
    }
    return tasks;
  }
}
