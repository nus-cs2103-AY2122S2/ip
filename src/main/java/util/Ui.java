
package util;

import java.time.format.DateTimeParseException;

import task.Deadline;
import task.Event;
import task.TaskList;
import task.Todo;


public class Ui {

    private final Parser parser = new Parser();
    private final DateTimeParser dtp = new DateTimeParser();
    private final CommandList cl = new CommandList();

    /**
     * Processes the user message and provides a response
     * @param input input command
     * @param tasks TaskList for tasks to be stored in
     * @return a response message to the user
     */
    public String processInput(String input, TaskList tasks) {
        parser.parse(input);
        String task = parser.getTask();
        String item = parser.getItem();


        switch (task) {
        case "todo":
            if (!item.equals("")) {
                return tasks.add(new Todo(item));
            } else {
                return "Todo cannot be empty!!! :/";
            }

        case "deadline":
            if (item.equals("")) {
                return "Deadline cannot be empty!!! :/";
            }
            try {
                dtp.parseDateTime(item, "deadline");
                return tasks.add(new Deadline(dtp.getDescription(), dtp.getTime()));
            } catch (ArrayIndexOutOfBoundsException e) {
                return "Please specify your deadline in the format 'task /by when'";
            } catch (DateTimeParseException e) {
                return "Please put your date in the format\nYYYY-MM-DD HH:mm";
            }



        case "event":
            if (item.equals("")) {
                return "Event cannot be empty!!! :/";
            }
            try {
                assert !item.equals("");
                dtp.parseDateTime(item, "event");
                return tasks.add(new Event(dtp.getDescription(), dtp.getTime()));
            } catch (ArrayIndexOutOfBoundsException e) {
                return "Please specify your event in the format 'task /at when'";
            } catch (DateTimeParseException e) {
                return "Please put your date in the format\n'YYYY-MM-DD HH:mm'";
            }

        case "list":
            StringBuilder lists = new StringBuilder();
            for (int i = 0; i < tasks.size(); i++) {
                if (i != 0) {
                    lists.append("\n");
                }
                lists.append(String.format("%d. %s", i + 1, tasks.get(i).toString()));

            }
            return lists.toString();

        case "mark":
            if (item.equals("")) {
                return "Please specify the index of the item that you would like to mark.";
            }
            try {
                assert !item.equals("");
                int index = Integer.parseInt(item);
                tasks.get(index - 1).markAsDone();
                return "Good job for accomplishing something today! I've marked this task as done:\n"
                        + tasks.get(index - 1).toString();
            } catch (IndexOutOfBoundsException e) {
                return "You can't do that! It's not in the list!";
            }

        case "unmark":
            if (item.equals("")) {
                return "Please specify the index of the item that you would like to unmark.";
            }
            try {
                assert !item.equals("");
                int index = Integer.parseInt(item);
                tasks.get(index - 1).markAsUndone();
                return "Stop procrastinating you lazy prick! I've marked this task as not done yet:\n"
                        + tasks.get(index - 1).toString();
            } catch (IndexOutOfBoundsException e) {
                return "You can't do that! It's not in the list!";
            }

        case "delete":
            return tasks.deleteItem(item);

        case "find":
            if (item.equals("")) {
                return "Please specify the item that you would like to find.";
            }
            assert !item.equals("");
            return tasks.find(item);

        case "commandlist":
            StringBuilder commands = new StringBuilder();
            for (String s: cl.getCommands()) {
                commands.append(s).append("\n");
            }
            return "Here are the list of commands:\n" + commands;


        default:
            return "This command does not exists!\nUse 'commandlist' to list out the commands";

        }


    }

    /**
     * Greets the user
     * @return greet message
     */
    public String greet() {
        return "Yawn... You woke me up! Urgh\n"
                + "I'm Snorlax! The manifestation of your laziness so that you will be stop procrastinating!\n"
                + "What do you need?";
    }

    /**
     * Displays the exit message to the user
     * @return exit message
     */

    public String exit() {
        return "Bye! Back to sleep! I hope you'll continue to be productive when I'm asleep :D";
    }


}
