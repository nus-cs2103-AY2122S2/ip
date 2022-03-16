package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * to understand user input commands
 */
public class Parser {
    private static final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");

    /**
     * Method to add todo tasks
     * @param todoTask input string
     * @return todo task
     * @throws ExceptionHandler to handle exceptions
     */
    public static AddCommand addTodo(String todoTask) throws ExceptionHandler {
        String[] temp = todoTask.split(" ");
        if (temp[0].equals("todo")) {
            if (temp.length < 2) {
                throw new ExceptionHandler("The description of a todo cannot be empty.");
            }
            String temp2 = "";
            for (int i = 1; i < temp.length; i++) {
                temp2 = temp2 + temp[i] + " ";
            }
            return new AddCommand(new ToDos(temp2));
        } else {
            throw new ExceptionHandler("I'm sorry, but I don't know what that means :-(");
        }
    }

    /**
     * Method to add event
     * @param eventTask
     * @return event task
     * @throws ExceptionHandler
     */
    public static AddCommand addEvent(String eventTask) throws ExceptionHandler {
        String[] temp = eventTask.split(" ");
        if (temp[0].equals("event")) {
            if (temp.length <= 2) {
                throw new ExceptionHandler("The description of a event cannot be empty.");
            }
            String temp2 = "";
            String time = "";
            int index = 0;
            for (int i = 1; i < temp.length; i++) {
                if (temp[i].equals("/at")) {
                    index = i + 1;
                    break;
                } else {
                    temp2 = temp2 + temp[i] + " ";
                }
            }
            for (int j = index; j < temp.length; j++) {
                time = time + temp[j] + " ";
            }
            LocalDateTime dateTime = null;
            try {
                dateTime = LocalDateTime.parse(time.trim(), dtf);
            } catch (DateTimeParseException e) {
                throw new ExceptionHandler("Please enter event in dd/mm/yyyy HHmm format");
            }
            return new AddCommand(new Event(temp2, dateTime));
        } else {
            throw new ExceptionHandler("I'm sorry, but I don't know what that means :-(");
        }
    }
    /**
     * Method to add deadline
     * @param deadLineTask
     * @return deadline task
     * @throws ExceptionHandler
     */
    public static AddCommand addDeadline(String deadLineTask) throws ExceptionHandler {
        String[] temp = deadLineTask.split(" ");
        if (temp[0].equals("deadline")) {
            if (temp.length <= 2) {
                throw new ExceptionHandler("The description of a deadline cannot be empty.");
            }
            String temp2 = "";
            String time = "";
            int index = 0;
            for (int i = 1; i < temp.length; i++) {
                if (temp[i].equals("/by")) {
                    index = i + 1;
                    break;
                } else {
                    temp2 = temp2 + temp[i] + " ";
                }
            }
            for (int j = index; j < temp.length; j++) {
                time = time + temp[j] + " ";
            }
            LocalDateTime dateTime = null;
            try {
                dateTime = LocalDateTime.parse(time.trim(), dtf);
            } catch (DateTimeParseException e) {
                throw new ExceptionHandler("Please enter deadline in dd/mm/yyyy HHmm format");
            }
            return new AddCommand(new Deadline(temp2, dateTime));
        } else {
            throw new ExceptionHandler("I'm sorry, but I don't know what that means :-(");
        }
    }

    /**
     * method to handle exceptions for command to mark task
     * @param doneTask
     * @return tasks that are done
     * @throws ExceptionHandler
     */
    public static DoneCommand addDone(String doneTask) throws ExceptionHandler {
        String[] temp = doneTask.split(" ", 2);
        if (temp[0].equals("mark")) {
            if (temp.length > 2) {
                throw new ExceptionHandler("Invalid index");
            }
            return new DoneCommand(Integer.parseInt(temp[1]) - 1);
        } else {
            throw new ExceptionHandler("I'm sorry, but I don't know what that means :-(");
        }
    }

    /**
     * Method to handle exceptions for delete command
     * @param toDeleteTask
     * @return task to be deleted
     * @throws ExceptionHandler
     */
    public static DeleteCommand addDelete(String toDeleteTask) throws ExceptionHandler {
        String[] temp = toDeleteTask.split(" ", 2);
        if (temp[0].equals("delete")) {
            if (temp.length > 2) {
                throw new ExceptionHandler("Invalid index");
            }
            return new DeleteCommand(Integer.parseInt(temp[1]) - 1);
        } else {
            throw new ExceptionHandler("I'm sorry, but I don't know what that means :-(");
        }
    }

    /**
     * Method to parse input
     *
     * @param input input string
     * @return a command
     * @throws ExceptionHandler
     */
    public static Command parse(String input) throws ExceptionHandler {
        assert input != null;
        String[] temp = input.split(" ");
        if (temp[0].equals("todo")) {
            return addTodo(input);
        } else if (temp[0].equals("event")) {
            return addEvent(input);
        } else if (temp[0].equals("deadline")) {
            return addDeadline(input);
        } else if (temp[0].equals("bye")) {
            return new ExitCommand();
        } else if (temp[0].equals("mark")) {
            return addDone(input);
        } else if (temp[0].equals("list")) {
            return new ListCommand();
        } else if (temp[0].equals("delete")) {
            return addDelete(input);
        } else if (temp[0].equals("find")) {
            if (temp.length > 2) {
                throw new ExceptionHandler("Invalid keyword");
            }
            return new FindCommand(temp[1]);
        } else if (temp[0].equals("help")) {
            return new HelpCommand();
        } else {
            throw new ExceptionHandler("I'm sorry, but I don't know what that means :-(");
        }
    }
}

