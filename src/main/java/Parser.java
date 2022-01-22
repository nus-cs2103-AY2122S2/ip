import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Parser {

    public static String formatDateTime(String str) {
        // Date/time pattern of input string
        DateFormat df = new SimpleDateFormat("dd-MM-yyyy HHmm");
        // Date/time pattern of desired output
        DateFormat outputFormat = new SimpleDateFormat("dd MMM yyyy hh:mm aa");
        Date date = null;
        String output = null;
        try{
            //Conversion of input String to date
            date= df.parse(str);
            //old date format to new date format
            output = outputFormat.format(date);
        } catch(ParseException pe){
            pe.printStackTrace();
        }
        return output;
    }

    public static Command parse(String input) throws UnknownInputException, IncompleteInputException {
        // Create a String array to read various functions
        String[] strs = input.split(" ");

        // Store first word as variable
        String firstWord = strs[0];

        if (input.equalsIgnoreCase("bye")) {
            return new ExitCommand();
        }

        else if (input.equalsIgnoreCase("list")) {
            return new PrintCommand();
        }

        else if (firstWord.equalsIgnoreCase("mark")) {
            int listIndex = Integer.parseInt(strs[1]); // retrieve the index after mark/unmark
            return new MarkCommand(listIndex);
        }

        else if (firstWord.equalsIgnoreCase("unmark")) {
            int listIndex = Integer.parseInt(strs[1]); // retrieve the index after mark/unmark
            return new UnmarkCommand(listIndex);
        }

        else if (firstWord.equalsIgnoreCase("todo")) {
            String subString = input.substring(4).trim(); // take the remaining of the input String
            if (subString.length() == 0) {
                throw new IncompleteInputException(firstWord);
            } else {
                Task toDo = new ToDo(subString);
                Ui.line();
                System.out.println("     Remember to do your task!");
                return new AddCommand(toDo);
            }
        }

        else if(firstWord.equalsIgnoreCase("deadline")) {
            String subString = input.substring(8).trim(); // take the remaining of the input String
            if (subString.length() == 0) {
                throw new IncompleteInputException(firstWord);
            } else {
                String[] temp = subString.split(" /"); // breaks the subString into 2 parts
                if (!temp[1].substring(0,3).equals("by ")) {
                    throw new UnknownInputException();
                } else {
                    String deadlineDate = temp[1].substring(3); // retrieves the String after '/by'
                    String formattedDate = formatDateTime(deadlineDate); // format date
                    Task deadline = new Deadline(temp[0], formattedDate);
                    Ui.line();
                    System.out.println("     This task is on a timer!");
                    return new AddCommand(deadline);
                }
            }
        }

        else if (firstWord.equalsIgnoreCase("event")) {
            String subString = input.substring(5).trim(); // take the remaining of the input String
            if (subString.length() == 0) {
                throw new IncompleteInputException(firstWord);
            } else {
                String[] temp = subString.split(" /"); // breaks the subString into 2 parts
                if (!temp[1].substring(0,3).equals("at ")) {
                    throw new UnknownInputException();
                } else {
                    String eventDate = temp[1].substring(3); // retrieves the String after '/at'
                    String formattedDate = formatDateTime(eventDate); // format date
                    Task event = new Event(temp[0], formattedDate);
                    Ui.line();
                    System.out.println("     Emergency event on this date!");
                    return new AddCommand(event);
                }
            }
        }

        else if (firstWord.equalsIgnoreCase("delete")) {
            int listIndex = Integer.parseInt(strs[1]); // retrieve the index after delete
            Task taskToBeDeleted = TaskList.taskArr.get(listIndex - 1);
            return new DeleteCommand(taskToBeDeleted);
        }

        else {
            throw new UnknownInputException();
        }
    } 
}
