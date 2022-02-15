package duke;

import java.time.format.DateTimeParseException;
import java.util.ArrayList;

public class CommandDeadline extends Command {
    private String commandDescription;
    private ArrayList<Task> tasks;
    private String[] split;

    public CommandDeadline(String commandDescription, ArrayList<Task> tasks) {
        this.commandDescription = commandDescription;
        this.tasks = tasks;
        split = commandDescription.split(" ");
    }

    @Override
    public String execute() {
        String output = "";
        try {
            // Check if description is empty
            String check = split[1];
            try {
                // Take the substring of user input after deadline
                String item = commandDescription.substring(9);
                // Divide the substring into task name and deadline
                String[] divide = item.split("/");
                String name = divide[0];
                String dueDate = divide[1];
                try {
                    Deadline d = new Deadline(name.trim(), dueDate.substring(3));
                    tasks.add(d);
                    output = String.format("task added:\n%s\n", d);
                    output += String.format("you now have %d tasks\n", tasks.size());
                } catch (DateTimeParseException e ) {
                    output = "date must be in the format yyyy-mm-dd\n";
                }

            } catch (IndexOutOfBoundsException e) {
                output = "deadline description must contain a date!\n";
            }
        } catch (IndexOutOfBoundsException e) {
            output = "deadline description cannot be empty!\n";
        }
        return output;
    }
}
