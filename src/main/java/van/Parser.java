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
        case "tag":
            output = caseTag(parameters);
            break;
        case "untag":
            output = caseUnTag(parameters);
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
                String[] taskDetails = fileLine.split("\\|");
                switch (taskDetails[0]) {
                case "E":
                    date = LocalDateTime.parse(taskDetails[3], dateFormat);
                    tasks.add(new Event(taskDetails[2], date));
                    addTags(tasks.get(i), taskDetails[4]);
                    break;
                case "D":
                    date = LocalDateTime.parse(taskDetails[3], dateFormat);
                    tasks.add(new Deadline(taskDetails[2], date));
                    addTags(tasks.get(i), taskDetails[4]);
                    break;
                case "T":
                    tasks.add(new ToDo(taskDetails[2]));
                    addTags(tasks.get(i), taskDetails[3]);
                    break;
                }
                if (taskDetails[1].equals("1")) {
                    tasks.get(i).setDone();
                }
            }
        }
        return tasks;
    }

    public static void addTags(Task task, String tag) {
        String[] tags = tag.split(" #");
        for (int i = 1; i < tags.length; i++) {
            task.addTag(tags[i]);
        }
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

    private static Command caseTag(String[] parameters) {
        try {
            if (parameters.length != 2) {
                throw new VanException("Invalid format. Please use tag <task number> #<tag>");
            }
            String[] taskArguments = parameters[1].split(" #");
            if (taskArguments.length != 2) {
                throw new VanException("Invalid format. Please use tag <task number> #<tag>");
            }
            try {
                return new TagCommand(true, Integer.parseInt(taskArguments[0]), taskArguments[1]);
            } catch (NumberFormatException ex) {
                return new InvalidCommand("Please use integers e.g. 1, 2");
            }
        } catch(VanException ex) {
            return new InvalidCommand(ex.getError());
        }
    }

    private static Command caseUnTag(String[] parameters) {
        try {
            if (parameters.length != 2) {
                throw new VanException("Invalid format. Please use untag <task number> #<tag>");
            }
            String[] taskArguments = parameters[1].split(" #");
            if (taskArguments.length != 2) {
                throw new VanException("Invalid format. Please use untag <task number> #<tag>");
            }
            try {
                return new TagCommand(false, Integer.parseInt(taskArguments[0]), taskArguments[1]);
            } catch (NumberFormatException ex) {
                return new InvalidCommand("Please use integers e.g. 1, 2");
            }
        } catch(VanException ex) {
            return new InvalidCommand(ex.getError());
        }
    }
}
