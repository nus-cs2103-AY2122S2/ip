package duke;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * This class receives a ParsedAnswer object and execute commands based on information
 * from the object.
 */

public class ParsedAnswerHandler {
    ParsedAnswer pa;

    ParsedAnswerHandler(ParsedAnswer pa) {
        this.pa = pa;
    }

    void assertCommandNotEmpty() {
        assert !pa.getCommand().isEmpty() : "Each ParsedAnswer object should contain a command.";
    }

    String writeTaskToFile(String task, String result) {
        String filePath = System.getProperty("user.dir")  + "/data/storage.txt";
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true));
            writer.append(task);
            writer.close();
            return result;
        } catch (IOException e) {
            return "Error saving task to file.";
        }

    }

    String changeMarkStatus(String action) {
        if (action.equalsIgnoreCase("mark")) {
            try {
                Task t = Storage.taskList.get(pa.getIndex() - 1);
                t.markAsDone();
                Storage s = new Storage();
                s.save();
                return "Successfully marked " + pa.getIndex();
            } catch (IndexOutOfBoundsException e) {
                return "Item not found. Please try again.";
            }
        } else {
            try {
                Task t = Storage.taskList.get(pa.getIndex() - 1);
                t.markAsUndone();
                Storage s = new Storage();
                s.save();
                return "Successfully ummarked " + pa.getIndex();
            } catch (IndexOutOfBoundsException e) {
                return "Item not found. Please try again.";
            }
        }
    }

    ArrayList<String> executeTodo() {
        var message = new ArrayList<String>();
        ToDos td = new ToDos(pa.getDesc());
        TaskList.add(td);
        message.add("T," +
                "1," +
                td.getDescription() + '\n');
        message.add("Successfully added todo task to list.");
        return message;
    }

    ArrayList<String> executeDeadline() {
        var message = new ArrayList<String>();
        Deadline dl = new Deadline(pa.getDesc(), pa.getDate());
        TaskList.add(dl);
        message.add("D," +
                "1," +
                dl.getDescription() +
                "," +
                dl.getBy() + '\n');
        message.add("Successfully added deadline to list.");
        return message;
    }

    ArrayList<String> executeEvent() {
        var message = new ArrayList<String>();
        Event ev = new Event(pa.getDesc(), pa.getDate());
        TaskList.add(ev);
        message.add("E," +
                "1," +
                ev.getDescription() +
                "," +
                ev.getAt() + '\n');
        message.add("Successfully added event to list.");
        return message;
    }

    Deadline generateUpdatedTaskForDeadline(ParsedAnswer pa, Deadline currentTask) {
        if (pa.getDesc().isEmpty() && !pa.getDate().isEmpty()) {
            return new Deadline(currentTask.getDescription(), pa.getDate());
        } else if (!pa.getDesc().isEmpty() && pa.getDate().isEmpty()) {
            return new Deadline(pa.getDesc(), currentTask.getBy());
        } else if (!pa.getDesc().isEmpty() && !pa.getDate().isEmpty()) {
            return new Deadline(pa.getDesc(), pa.getDate());
        }
        else {
            return currentTask;
        }
    }

    Event generateUpdatedTaskForEvent(ParsedAnswer pa, Event currentTask) {
        if (pa.getDesc().isEmpty() && !pa.getDate().isEmpty()) {
            return new Event(currentTask.getDescription(), pa.getDate());
        } else if (!pa.getDesc().isEmpty() && pa.getDate().isEmpty()) {
            return new Event(pa.getDesc(), currentTask.getAt());
        } else if (!pa.getDesc().isEmpty() && !pa.getDate().isEmpty()) {
            return new Event(pa.getDesc(), pa.getDate());
        }
        else {
            return currentTask;
        }
    }

    ToDos generateUpdatedTaskForTodo(ParsedAnswer pa, ToDos currentTask) {
        if (!pa.getDesc().isEmpty()) {
            return new ToDos(pa.getDesc());
        } else {
            return currentTask;
        }
    }

    String executeUpdate() {
        int idx = pa.getIndex();
        Storage s = new Storage();
        Task updatedTask = Storage.taskList.get(idx);

        if (pa.getType().equals("Deadline")) {
            Deadline currentTask = (Deadline) Storage.taskList.get(idx);
            updatedTask = generateUpdatedTaskForDeadline(pa, currentTask);
        }

        if (pa.getType().equals("Event")) {
            Event currentTask = (Event) Storage.taskList.get(idx);
            updatedTask = generateUpdatedTaskForEvent(pa, currentTask);
        }

        if (pa.getType().equals("Todo")) {
            ToDos currentTask = (ToDos) Storage.taskList.get(idx);
            updatedTask = generateUpdatedTaskForTodo(pa, currentTask);
        }

        Storage.taskList.remove(idx);
        Storage.taskList.add(idx, updatedTask);
        s.save();
        return "Update successful";
    }

    String execute() {
        assertCommandNotEmpty();
        switch (pa.getCommand()) {
            case "bye":
                Duke.isGoodbye = true;
                return "Have a nice day.";

            case "list":
                return TaskList.list();

            case "todo":
                return writeTaskToFile(executeTodo().get(0), executeTodo().get(1));

            case "deadline":
                return writeTaskToFile(executeDeadline().get(0), executeDeadline().get(1));

            case "event":
                return writeTaskToFile(executeEvent().get(0), executeEvent().get(1));

            case "mark":
               return changeMarkStatus("mark");

            case "unmark":
              return changeMarkStatus("unmark");

            case "error":
                return pa.getDesc();

            case "delete":
                return TaskList.delete(pa.getIndex());

            case "find":
                return TaskList.find(pa.getDesc());

            case "update":
                return executeUpdate();
        }
        return "An unexpected error has occurred.";
    }
}
