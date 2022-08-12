package duke.parser;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import duke.commands.Deadline;
import duke.commands.Delete;
import duke.commands.Event;
import duke.commands.Find;
import duke.commands.List;
import duke.commands.Mark;
import duke.commands.Tag;
import duke.commands.Todo;
import duke.commands.Unmark;
import duke.exception.DukeException;
import duke.storage.Storage;
import duke.tasks.Task;
import duke.tasks.TaskList;
import java.io.IOException;

/**
 * Deals with making sense of the user command.
 */
public class Parser {

    protected String LINE_BREAK = "---------------";
    protected String BYE = LINE_BREAK + "\n Byeeeee, come back again ah!\n" + LINE_BREAK;

    // includes all tasks from duke.txt
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
     * @param input from CLI
     * @return a string value of the command
     * @throws DukeException
     * @throws IOException
     */
    public String scanInput(String input) throws DukeException, IOException {
        String[] command = input.split(" ");
        String res = "";
        if (command[0].equals("bye")) {
            return byeCommand("data/duke.txt");

        } else if (command[0].equals("list")) {
            List list = new List(tasks.getTasks());
            return list.printResults();

        } else if (command[0].equals("mark")) {
            return markCommand(command);

        } else if (command[0].equals("unmark")) {
            return unmarkCommand(command);

        } else if (command[0].equals("deadline")) {
            // find length of tasklist
            int numTasks = tasks.getTasks().size();
            return deadlineCommand(command, input, numTasks + 1);

        } else if (command[0].equals("event")) {
            int numTasks = tasks.getTasks().size();
            return eventCommand(input, command, numTasks);

        } else if (command[0].equals("todo")) {
            // here we declare the new task to be added (todos)
            if (command.length != 1) {
            }
            return todoCommand(command, input);

        } else if (command[0].equals("delete")) {
            // deleting a task
            return deleteCommand(command);

        } else if (command[0].equals("find")) {
            return findCommand(command);

        } else if (command[0].equals("tag")) {
            return tagCommand(command, tasks);

        } else {
            DukeException e = new DukeException("Tak faham banggg, speak in my language la bayi....");
            res += e.getMessage();
            return res;
        }
    }

    /**
     * Receives bye command.
     * @param filePath
     * @return
     * @throws IOException
     */
    private String byeCommand(String filePath) throws IOException {
        // this will update the duke.txt file
        Storage.updateDukeTxt(filePath, tasks.getTasks());
        System.exit(0);
        return "bye";
    }

    /**
     * Receives mark command.
     * @param command
     * @return
     */
    private String markCommand(String[] command) {
        Mark mark = new Mark(tasks.getTasks());
        int number = Integer.parseInt(command[1]) - 1;
        Task currTask = tasks.getTasks().get(number);

        return mark.markTask(currTask);
    }

    /**
     * Receives unmark command.
     * @param command
     * @return
     */
    private String unmarkCommand(String[] command) {
        int number = Integer.parseInt(command[1]) - 1;
        Task currTask = tasks.getTasks().get(number);
        Unmark u = new Unmark(tasks.getTasks());

        return u.unmarkTask(currTask);
    }

    /**
     * Receives deadline command.
     * @param command
     * @param input
     * @param counter
     * @return
     */
    private String deadlineCommand(String[] command, String input, int counter) {
        String res = "";
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
    }

    /**
     * Receives the event command and calls another method in Event.class
     * @param input
     * @param command
     * @param counter
     * @return
     */
    private String eventCommand(String input, String[] command, int counter) {
        String res = "";
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
            return result;
        }
    }

    private String todoCommand(String[] command, String input) {
        Todo t = new Todo(tasks.getTasks());
        String result = t.handleTodo(command, tasks, input);
        return result;
    }

    private String deleteCommand(String[] command) {
        Delete d = new Delete(tasks.getTasks());
        String result = d.handleDelete(tasks, command);
        return result;
    }

    /**
     * Receives the tag command. Calls on Find::findReleventTasks(...).
     * @param command
     * @return direction to Find::findRevelvantTasks(...)
     */
    private String findCommand(String[] command) {
        // keyword
        String keyword = command[1];
        Find f = new Find(tasks.getTasks());
        return f.findRelevantTasks(keyword, tasks.getTasks());
    }

    /**
     * Receives the tag command. Calls on Tag::handleTag(...) after
     * instantiating a Tag class.
     * @param command Received command from user
     * @param tasks   Current tasks available
     * @return direction to another method - Tags::handleTag(...)
     */
    private String tagCommand(String[] command, TaskList tasks) {
        int pos = Integer.parseInt(command[1]) - 1;
        String tagName = command[2];
        Tag t = new Tag(tasks.getTasks());
        return t.handleTag(tasks, pos, tagName);
    }

}
