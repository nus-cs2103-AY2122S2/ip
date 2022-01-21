import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class Parser {

    public Command parseUserInput(String userInput, List<Task> tasks) throws InvalidInputException {
        // catch empty input
        if (userInput.trim().length() == 0)
            throw new InvalidInputException("Empty");

        String[] arr = userInput.split(" ", 2);
        // command might be "list" or "bye"
        if (arr[0].equals("bye") && arr.length == 1) {
            return new ExitCommand();
        } else if (arr[0].equals("list") && (arr.length == 1 || arr.length == 2)) {
            if (arr.length == 1)
                return new ListCommand();
            else
                return new ListCommand(true, parseDate(arr[1]));
        } else {
            if (arr[0].equals("mark") || arr[0].equals("unmark") || arr[0].equals("delete")) {
                String theRest = arr[1];
                int num;
                try {
                    num = Integer.parseInt(theRest);
                    if (num < 0 || num > tasks.size())
                        throw new InvalidInputException("Task number out of range");

                    if (arr[0].equals("mark"))
                        return new MarkCommand(Integer.parseInt(arr[1]));
                    else if (arr[0].equals("unmark"))
                        return new UnMarkCommand(Integer.parseInt(arr[1]));
                    else if (arr[0].equals("delete"))
                        return new DeleteCommand(Integer.parseInt(arr[1]));

                } catch (NumberFormatException e) {
                    throw new InvalidInputException("Invalid task number. Please enter a valid integer.");
                }
            } else if (arr[0].equals("deadline") || arr[0].equals("event")) {
                String[] output = arr[1].split("/", 2);

                if (output.length == 1)
                    throw new InvalidInputException("Invalid " + arr[0] + " format. eg. deadline return book /by 02/12/2019 18:00.");

                String[] date = output[1].split(" ", 2);

                // check Prepositions of Time/Date
                if (!date[0].equals("on") && !date[0].equals("by") && !date[0].equals("at") && !date[0].equals("in"))
                    throw new InvalidInputException("Invalid Prepositions of Time. Please use 'in' 'at' 'by' or 'on'.");

                if (arr[0].equals("deadline"))
                    return new AddCommand(new Deadline(output[0], parseDate(date[1])));
                else
                    return new AddCommand(new Event(output[0], parseDate(date[1])));
            } else if (arr[0].equals("todo")) {
                return new AddCommand(new ToDo(arr[1]));
            } else {
                throw new InvalidInputException("Invalid command. Please try 'list/bye/mark/unmark/event/deadline/todo'. ");
            }
            return null;
        }
    }

    public static LocalDateTime parseDate(String date) throws InvalidInputException {
        try {
            DateTimeFormatter format = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
            LocalDateTime formattedDate = LocalDateTime.parse(date, format);
            return formattedDate;
        } catch (DateTimeException e) {
            throw new InvalidInputException(date + " can't be formatted! Please format the date/time as dd/MM/yyyy HH:mm");
        }
    }

    public static String printDate(LocalDateTime date) {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("MMM d yyyy hh:mm a");
        return date.format(format);
    }

}
