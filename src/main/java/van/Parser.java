package van;

import java.util.ArrayList;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Parser {

    public static Command parseCommand(String parameter) {
        Command output = null;
        String[] parameters = parameter.split("\\s+", 2);
        switch (parameters[0].toLowerCase()) {
        case "list":
            output = new DisplayCommand();
            break;
        case "deadline":
            output = caseDeadLine(parameters);
            break;
        case "event":
            output = caseEvent(parameters);
            break;
        case "todo":
            output = caseToDo(parameters);
            break;
        case "mark":
            output = caseMark(parameters);
            break;
        case "unmark":
            output = caseUnMark(parameters);
            break;
        case "delete":
            output = caseDelete(parameters);
            break;
        case "find":
            output = caseFind(parameters);
            break;
        case "bye":
            output = new ExitCommand();
            break;
        default:
            output = new InvalidCommand("Invalid Command");
        }
        assert output != null : "Problem with parsing command";
        return output;
    }

    public static ArrayList<Task> parseArray(ArrayList<String> taskList) {
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
                    break;
                case "D":
                    date = LocalDateTime.parse(taskDetail[3], dateFormat);
                    tasks.add(new Deadline(taskDetail[2], date));
                    break;
                case "T":
                    tasks.add(new ToDo(taskDetail[2]));
                    break;
                }
                if (taskDetail[1].equals("1")) {
                    tasks.get(i).setDone();
                }
            }
        }
        return tasks;
    }

    public static String[] parseDescription(Task task) {
        String[] keywords = task.getDescription().split(" ");
        return keywords;
    }

    private static Command caseDeadLine(String[] parameters) {
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        try {
            if (parameters.length != 2) {
                throw new VanException("Invalid format. Please use: deadline <task> /by <date>");
            }
            String[] taskArguments = parameters[1].split(" /by ");
            if (taskArguments.length != 2) {
                throw new VanException("Invalid format. Please use: deadline <task> /by <date>");
            }
            try {
                LocalDateTime date = LocalDateTime.parse(taskArguments[1], dateFormat);
                return new AddCommand(new Deadline(taskArguments[0], date));
            } catch (DateTimeParseException e) {
                throw new VanException("Invalid date format. "
                  + "Please use dd-MM-YYYY HH:mm e.g. 20-10-2022 1800");
            }
        } catch (VanException ex) {
            return new InvalidCommand(ex.getError());
        }
    }

    private static Command caseEvent(String[] parameters) {
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        try {
            if (parameters.length != 2) {
                throw new VanException("Invalid format. Please use: event <task> /at <date>");
            }
            String[] taskArguments = parameters[1].split(" /at ");
            if (taskArguments.length != 2) {
                throw new VanException("Invalid format. Please use: event <task> /at <date>");
            }
            try {
                LocalDateTime date = LocalDateTime.parse(taskArguments[1], dateFormat);
                return new AddCommand(new Event(taskArguments[0], date));
            } catch (DateTimeParseException e) {
                throw new VanException("Invalid date format. "
                  + "Please use dd-MM-YYYY HH:mm e.g. 20-10-2022 1800");
            }
        } catch(VanException ex) {
            return new InvalidCommand(ex.getError());
        }
    }

    private static Command caseToDo(String[] parameters) {
        try {
            if (parameters.length != 2) {
                throw new VanException("Invalid format. Please use: deadline <task> /by <date>");
            }
            return new AddCommand(new ToDo(parameters[1]));
        } catch(VanException ex) {
            return new InvalidCommand(ex.getError());
        }
    }

    private static Command caseMark(String[] parameters) {
        try {
            if (parameters.length != 2) {
                throw new VanException("Invalid format. Please use mark <task number>");
            }
            try {
                return new MarkCommand(true, Integer.parseInt(parameters[1]));
            } catch (NumberFormatException ex) {
                return new InvalidCommand("Please use integer numbers e.g. 1, 2");
            }
        } catch(VanException ex) {
            return new InvalidCommand(ex.getError());
        }
    }

    private static Command caseUnMark(String[] parameters) {
        try {
            if (parameters.length != 2) {
                throw new VanException("Invalid format. Please use unmark <task number>");
            }
            try {
                return new MarkCommand(false, Integer.parseInt(parameters[1]));
            } catch (NumberFormatException ex) {
                return new InvalidCommand("Please use integers e.g. 1, 2");
            }
        } catch(VanException ex) {
            return new InvalidCommand(ex.getError());
        }
    }

    private static Command caseDelete(String[] parameters) {
        try {
            if (parameters.length != 2) {
                throw new VanException("Invalid format. Please use delete <task number>");
            }
            try {
                return new DeleteCommand(Integer.parseInt(parameters[1]));
            } catch (NumberFormatException ex) {
                return new InvalidCommand("Please use integers e.g. 1, 2");
            }
        } catch(VanException ex) {
            return new InvalidCommand(ex.getError());
        }
    }

    private static Command caseFind(String[] parameters) {
        try {
            if (parameters.length != 2) {
                throw new VanException("Invalid format. Please use find <keyword>");
            }
            return new FindCommand(parameters[1]);
        } catch(VanException ex) {
            return new InvalidCommand(ex.getError());
        }
    }
}
