package johnny;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.ArrayList;

public class Ui {

    private InputList tasks;
    private Storage store;

    public Ui(InputList tasks, Storage store) {
        this.tasks = tasks;
        this.store = store;
    }

    public String handleUi(String input) throws InvalidArgumentsException, EmptyDescriptionException, NoDateException, DateTimeException{

        Parser parser = new Parser(input);
        ArrayList<String> parseOutput = parser.parse();
        String commandTag = parseOutput.get(0);
        String output;

        if(commandTag.equals(Parser.SAVE)) {
            tasks.writeToFile(this.store);

            output = "Your changes have been saved!";
            //break;
        }
        else if(commandTag.equals(Parser.PRINT_LIST)) {
            output = "Here are the tasks in your list:\n"
                + tasks.getList();
        }
        else if(commandTag.equals(Parser.MARK)) {
            int index = Integer.parseInt(parseOutput.get(1));
            tasks.mark(index);
            output = "Nice! I've marked this task as done:\n" +
                    tasks.get(index - 1).toString() + "\n";
        }
        else if(commandTag.equals(Parser.UNMARK)) {
            int index = Integer.parseInt(parseOutput.get(1));
            tasks.unmark(index);
            output = "OK, I've marked this task as not done yet:\n" +
                    tasks.get(index - 1).toString() + "\n";
        }
        else if(commandTag.equals(Parser.DELETE)) {
            int index = Integer.parseInt(parseOutput.get(1));
            tasks.delete(index);
            output = "Noted. I've removed this task:\n" +
                    tasks.get(index - 1).toString() + "\n";
        }
        else if(commandTag.equals(Parser.FIND_EVENT)) {
            String matches = tasks.searchEvent(parseOutput.get(1));
            output = "Here are the matching tasks in your list:\n" + matches;
        }
        else if(commandTag.equals(Parser.ADD_TODO)) {
            Task newTask = new Todo(parseOutput.get(1), false);
            tasks.add(newTask);
            output = "Got it! I've added this task:\n" + newTask + "\n" +
                    "Now you have " + tasks.getCount() + " tasks in your list.\n";
        }
        else if(commandTag.equals(Parser.ADD_DEADLINE)) {
            Task newTask = new Deadline(parseOutput.get(1),
                    LocalDate.parse(parseOutput.get(2)), false);

            String clashes = tasks.checkForClash(LocalDate.parse(parseOutput.get(2)));

            tasks.add(newTask);

            output = "Got it! I've added this task:\n" + newTask + "\n" +
                    "Now you have " + tasks.getCount() + " tasks in your list.\n";

            if(clashes != "") {
                output += "FYI, you have scheduled the following tasks on the same day:\n"
                        + clashes;
            }
        }
        else if(commandTag.equals(Parser.ADD_EVENT)) {

            Task newTask = new Event(parseOutput.get(1),
                    LocalDate.parse(parseOutput.get(2)), false);

            String clashes = tasks.checkForClash(LocalDate.parse(parseOutput.get(2)));
            tasks.add(newTask);

            output = "Got it! I've added this task:\n" + newTask + "\n" +
                    "Now you have " + tasks.getCount() + " tasks in your list.\n";

            if(clashes != "") {
                output += "FYI, you have scheduled the following tasks on the same day:\n"
                        + clashes;
            }
        }
        else {
            output = "Sorry, I didn't quite get that";
        }
        return output;
    }
}

