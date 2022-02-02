package Duke.Processing;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import Duke.Exception.DukeException;

public class Parser {
    
    static LocalDate convert(String date) throws DukeException {
        try {
            return LocalDate.parse(date, DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm"));
        } catch (DateTimeParseException e) {
            throw new DukeException("Smth is wrong " + e);
        }
    }

    public static void use(String input, TaskList tasklist) throws DukeException{
        try {
            if(input.equalsIgnoreCase("list")) {
                tasklist.list();
            } else if(input.startsWith("todo")) {
                String item = input.substring(5);
                tasklist.addTodo(item);
            } else if(input.startsWith("deadline")) {
                String item = input.substring(9);
                String[] split = item.split(" /by ");
                tasklist.addDeadline(split);
            } else if (input.startsWith("Event")) {
                String item = input.substring(6);
                String[] split = item.split(" /at ");
                tasklist.addEvent(split);
            } else if (input.startsWith("mark")) {
                String num = input.substring(5);
                tasklist.mark(num);
            } else if (input.startsWith("unmark")) {
                String num = input.substring(7);
                tasklist.unmark(num);
            } else if (input.startsWith("delete")) {
                String num = input.substring(7);
                tasklist.delete(num);
            } else {
                throw new DukeException("I'm not sure we can do that");
            }
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("We don't even have that many things to do");
        }
    }
}
