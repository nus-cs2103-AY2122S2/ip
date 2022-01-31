package duke.commands;

import duke.exception.ChiException;
import duke.storage.Storage;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;
import duke.tasklist.TaskList;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;

public class AddCommand extends Command {

    private String command;
    private String description;

    public AddCommand(String[] s) {
        this.command = s[0].toLowerCase();
        this.description = String.join(" ", Arrays.copyOfRange(s, 1, s.length)).trim();
    }

    @Override
    public String execute(TaskList tl, Storage sge) throws ChiException {
        try {
            switch(command) {
            case "todo":
                if (validateMessageBody(this.description, command)) {
                    throw new ChiException("This todo has some problems nyan!");
                } else {
                    String s = this.description;
                    Task newTask = new Todo(s, false);
                    // Add task to list
                    tl.addTask(newTask);
                    sge.updateFile(newTask, tl, "todo");
                    return String.format("Ok! Chi-san has added:\n%s\nYou have %d tasks nyan~!\n",
                            newTask, tl.getSize());
                }
            case "deadline":
                if (validateMessageBody(this.description, command)) {
                    throw new ChiException("This deadline has some problems nyan!");
                } else {
                    Task newTask;
                    String s = getDescription(this.description, "deadline");
                    LocalDate ld = getDeadlineDate(this.description);
                    LocalTime lt = getDeadlineTiming(this.description);
                    newTask = new Deadline(s, ld, lt, false);
                    tl.addTask(newTask);
                    sge.updateFile(newTask, tl, "deadline");
                    return String.format("Ok! Chi-san has added:\n%s\nYou have %d tasks nyan~!\n",
                            newTask, tl.getSize());
                }
            case "event":
                if (validateMessageBody(this.description, command)) {
                    throw new ChiException("This event has some problems nyan!");
                } else {
                    Task newTask;
                    String s = getDescription(this.description, "event");
                    LocalDate ld = getEventDate(this.description);
                    LocalTime lt = getEventTimingStart(this.description);
                    LocalTime lt1 = getEventTimingEnd(this.description);
                    newTask = new Event(s, ld, lt,lt1, false);
                    tl.addTask(newTask);
                    sge.updateFile(newTask, tl, "deadline");
                    return String.format("Ok! Chi-san has added:\n%s\nYou have %d tasks nyan~!\n",
                            newTask, tl.getSize());
                }
            default:
                throw new ChiException("Oopsies something went wrong while parsing!");
            }
        } catch (IOException e) {
            throw new ChiException("Hey something went wrong with the IO nyan!");
        }
    }

    public boolean validateMessageBody(String msg, String type) {
        switch(type) {
        case "todo":
            return false;
        case "deadline":
            String[] separateBys = msg.split("/by");
            if (separateBys.length != 2 || separateBys[0].equals("")) {
                return true;
            }
            try {
                String datetime = msg.split("/by")[1].trim();
                LocalDateTime dt = LocalDateTime.parse(datetime, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
                return false;
            } catch (DateTimeParseException e) {
                return true;
            }
        case "event":
            String[] separateAts = msg.split("/at");
            if (separateAts.length != 2 || separateAts[0].equals("")) {
                return true;
            }
            try {
                String datetime = msg.split("/at")[1].trim();
                if (datetime.split(" ").length != 2 ||
                        datetime.split(" ")[1].split("-").length != 2) {
                    return true;
                }
                LocalDate d = LocalDate.parse(datetime.split(" ")[0].trim(),
                        DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                LocalTime t1 = LocalTime.parse(datetime.split(" ")[1].split("-")[0].trim(),
                        DateTimeFormatter.ofPattern("HH:mm"));
                LocalTime t2 = LocalTime.parse(datetime.split(" ")[1].split("-")[1].trim(),
                        DateTimeFormatter.ofPattern("HH:mm"));
                if (t1.isAfter(t2)) {
                    return true;
                }
                return false;
            } catch (DateTimeParseException e) {
                return true;
            }
        default:
            return true;
        }
    }

    public String getDescription(String msg, String type) {
        if (type.equals("deadline")) {
            return msg.split("/by")[0].trim();
        } else {
            return msg.split("/at")[0].trim();
        }
    }

    public LocalDate getDeadlineDate(String msg) {
        String datetime = msg.split("/by")[1].trim();
        LocalDate dt = LocalDate.parse(datetime.split(" ")[0].trim(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        return dt;
    }
    public LocalTime getDeadlineTiming(String msg) {
        String datetime = msg.split("/by")[1].trim();
        LocalTime dt = LocalTime.parse(datetime.split(" ")[1].trim(), DateTimeFormatter.ofPattern("HH:mm"));
        return dt;
    }


    public LocalDate getEventDate(String msg) {
        String datetime = msg.split("/at")[1].trim();
        LocalDate dt = LocalDate.parse(datetime.split(" ")[0].trim(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        return dt;
    }

    public LocalTime getEventTimingStart(String msg) {
        String datetime = msg.split("/at")[1].trim();
        LocalTime dt = LocalTime.parse(datetime.split(" ")[1].split("-")[0].trim(),
                DateTimeFormatter.ofPattern("HH:mm"));
        return dt;
    }

    public LocalTime getEventTimingEnd(String msg) {
        String datetime = msg.split("/at")[1].trim();
        LocalTime dt = LocalTime.parse(datetime.split(" ")[1].split("-")[1].trim(),
                DateTimeFormatter.ofPattern("HH:mm"));
        return dt;
    }
}
