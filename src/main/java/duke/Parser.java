package duke;

import duke.commands.*;
import duke.exceptions.DukeException;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.Todo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Parser {

    public static Task parseToTask(String userInput) throws DukeException {

        if (userInput.startsWith("todo")) {
            return Parser.newToDo(userInput);
        } else if (userInput.startsWith("deadline")) {
            return Parser.newDeadline(userInput);
        } else if (userInput.startsWith("event")) {
            return Parser.newEvent(userInput);
        } else {
            // Not used
            return null;
        }
    }
    private static Todo newToDo(String s) throws DukeException{
        String taskName =  s.replaceFirst("todo","").strip();

        if (taskName.equals("")) {
            throw new DukeException("Todo Name is empty!");
        }

        return new Todo(taskName);
    }
    private static Event newEvent(String s) throws DukeException {
        String[] fields =  s.replaceFirst("event","").split("/at");

        if (fields.length != 2){
            throw new DukeException("Wrong format entered! Please enter <Event Name> /at <Event Date>");
        }

        if (fields[0].strip().equals("")) {
            throw new DukeException("Event Name is empty!");
        }

        if (fields[1].strip().equals("")) {
            throw new DukeException("No Date Specified!");
        }

        String taskName = fields[0].strip();
        LocalDateTime date = Parser.parseDateTime(fields[1].strip());
        if (date == null) {
            throw new DukeException("Invalid Date format! Please enter Date/Time in the form DD/MM/YYYY HHMM");
        }

        return new Event(taskName,date);
    }
    private static Deadline newDeadline(String s) throws DukeException{
        String[] fields =  s.replaceFirst("deadline","").split("/by");

        if (fields.length != 2){
            throw new DukeException("Wrong format entered! Please enter <Deadline Name> /by <Deadline>");
        }

        if (fields[0].strip().equals("")) {
            throw new DukeException("Deadline Name is empty!");
        }

        if (fields[1].strip().equals("")) {
            throw new DukeException("No Date Specified!");
        }

        String taskName = fields[0].strip();
        LocalDateTime date = Parser.parseDateTime(fields[1].strip());

        if (date == null) {
            throw new DukeException("Invalid Date format! Please enter Date/Time in the form DD/MM/YYYY HHMM");
        }

        return new Deadline(taskName,date);
    }

    public static Task parseToTaskFromFile(String fileInput) throws DukeException {
        // <type>\t<done>\t<name>\t<date>
        char type;
        char done;
        String name;
        LocalDateTime date;
        Task t;

        try {
            String[] split = fileInput.split("\t");
            type = split[0].toCharArray()[0];
            done = split[1].toCharArray()[0];
            name = split[2].strip();
            date = Parser.parseDateTime(split[3]);
        } catch (IndexOutOfBoundsException exception){
            throw new DukeException("Unable to load task from file!");
        }

        if (name.equals("")) {
            throw new DukeException("Unable to load task from file!");
        }


        switch(type) {

        case 'T':
            t = new Todo(name);
            break;

        case 'D':
            if (date == null) {
                throw new DukeException("Unable to load task from file!");
            }
            t = new Deadline(name, date);
            break;

        case 'E':
            if (date == null) {
                throw new DukeException("Unable to load task from file!");
            }
            t = new Event(name, date);
            break;

        default:
            throw new DukeException("Unable to load task from file!");
        }

        if (done == 'X') {
            t.markDone();
        }

        return t;
    }

    public static Command parse(String input) throws DukeException{
        if (input.startsWith("todo") || input.startsWith("deadline") || input.startsWith("event")){
            return new AddCommand(input);
        } else if (input.startsWith("list")){
            return new ListCommand();
        } else if (input.startsWith("mark") || input.startsWith("unmark")){
            return MarkCommand.of(input);
        } else if (input.equals("bye")) {
            return new ExitCommand();
        } else if (input.startsWith("delete")){
            return new DeleteTaskCommand(input);
        }
             throw new DukeException("Invalid Command!");

    }

    public static LocalDateTime parseDateTime(String input){
        DateTimeFormatter format = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
        //DateTimeFormatter formatted = DateTimeFormatter.ofPattern("d MMM yyyy, K:mma");
        try {
            LocalDateTime date = LocalDateTime.parse(input, format);
            return date;
        } catch (DateTimeParseException exception){
            return null;
        }
    }

}
