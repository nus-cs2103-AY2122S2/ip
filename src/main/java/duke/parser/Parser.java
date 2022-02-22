package duke.parser;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import duke.commands.Deadline;
import duke.commands.Delete;
import duke.commands.Event;
import duke.commands.Find;
import duke.commands.List;
import duke.commands.Mark;
import duke.commands.Todo;
import duke.commands.Unmark;
import duke.exception.DukeException;
import duke.storage.Storage;
import duke.tasks.Task;
import duke.tasks.TaskList;

/**
 * Deals with making sense of the user command.
 * What are the commands you will face? This will just abstract the whole
 * if-else block from Duke.java.
 */
public class Parser {

    protected String LINE_BREAK = "---------------";
    protected String BYE = LINE_BREAK + "\n Byeeeee, come back again ah!\n" + LINE_BREAK;

    // is this the most updated task arrayList?
    // yes, right from the start, should include all from duke.txt
    private TaskList tasks;

    /**
     * Constructor
     */
    public Parser(TaskList tasks) {
        this.tasks = tasks;
    }

    /**
     * Determines if input string is date or not.
     * 
     * @param date
     * @return boolean
     */
    public boolean isDate(String date) {
        try {
            LocalDate.parse(date);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    /**
     * Handles all the possible commands from the CLI.
     * 
     * @param input from CLI
     * @return a string value of the command
     * @throws DukeException
     * @throws IOException
     */
    public String scanInput(String input) throws DukeException, IOException {
        String[] command = input.split(" ");
        int counter = 0;
        String res = "";
        if (command[0].equals("bye")) {
            String filePath = "data/duke.txt";
            // this will update the duke.txt file
            Storage.updateDukeTxt(filePath, tasks.getTasks());
            System.out.println(BYE);
            return "bye";
        } else if (command[0].equals("list")) {
            List list = new List(tasks.getTasks());

            return list.printResults();
        } else if (command[0].equals("mark")) {
            Mark mark = new Mark(tasks.getTasks());
            int number = Integer.parseInt(command[1]) - 1;

            Task currTask = tasks.getTasks().get(number);

            return mark.markTask(currTask);
        } else if (command[0].equals("unmark")) {
            int number = Integer.parseInt(command[1]) - 1;
            Task currTask = tasks.getTasks().get(number);
            Unmark u = new Unmark(tasks.getTasks());

            return u.unmarkTask(currTask);
        } else if (command[0].equals("deadline")) {
            // deadline make some cups /by the day after
            if (command.length == 1) {
                DukeException e = new DukeException("bro why la");
                return res += e.getMessage();
            } else {
                String[] deadlineInput = input.split("/by");
                String deadline = deadlineInput[1];
                String left = deadlineInput[0];
                String description = left.substring(9, left.length() - 1);
                // create a new deadline
                Deadline d = new Deadline(tasks.getTasks());
                String result = d.handleDeadline(tasks, deadline, description, counter);
                ++counter;
                return result;
            }
        } else if (command[0].equals("event")) {

            // event project meeting /at Mon 2-4pm
            if (command.length == 1) {
                DukeException e = new DukeException("OOPS!!! The description of an event cannot be empty.");

                res += "OOPS!!! The description of an event cannot be empty." + "\n";
                res += "---------------" + "\n";
                res += e.getMessage();

                return res;
            } else {
                String[] deadlineInput = input.split("/at");
                String deadline = deadlineInput[1];
                String left = deadlineInput[0];
                String description = left.substring(6, left.length() - 1);
                Event e = new Event(tasks.getTasks());
                String result = e.handleEvent(tasks, deadline, counter, description);
                ++counter;
                return result;
            }
        } else if (command[0].equals("todo")) {
            // here we declare the new task to be added (todos)
            Todo t = new Todo(tasks.getTasks());
            String result = t.handleTodo(command, tasks, input);
            if (command.length != 1) {
                ++counter;
            }
            return result;
        } else if (command[0].equals("delete")) {
            // deleting a task
            Delete d = new Delete(tasks.getTasks());
            String result = d.handleDelete(tasks, command);
            return result;
        } else if (command[0].equals("find")) {
            // keyword
            String keyword = command[1];
            Find f = new Find(tasks.getTasks());
            return f.findRelevantTasks(keyword, tasks.getTasks());

        } else {
            DukeException e = new DukeException("Tak faham banggg, speak in my language la bayi....");
            res += e.getMessage();
            return res;
        }
    }

}
