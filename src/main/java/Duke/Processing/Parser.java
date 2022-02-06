package Duke.Processing;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import Duke.Exception.DukeException;

public class Parser {
    
    static Date convert1(String date) throws DukeException {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        //System.out.println(date);
        try {
            Date date1 = formatter.parse(date);
            return  date1;
        } catch (ParseException e) {
            throw  new DukeException("what is wrong" + e);
        }
    }

    static Date convert2(String date) throws DukeException {
        SimpleDateFormat formatter = new SimpleDateFormat("EEE MMM d HH:mm:ss zzz yyyy");
        //System.out.println(date);
        try {
            Date date2 = formatter.parse(date);
            return date2;
        } catch (ParseException e) {
            throw  new DukeException("What is wrong " + e);
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
