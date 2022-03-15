package duke;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

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

    String execute() {
        assertCommandNotEmpty();
        switch (pa.getCommand()) {
            case "bye":
                Duke.isGoodbye = true;
                return "Have a nice day.";

            case "list":
                return TaskList.list();

            case "todo":
                ToDos td = new ToDos(pa.getDesc());
                TaskList.add(td);
                String todoTask = "T," +
                        "1," +
                        td.getDescription() + '\n';
                String todoResult = "Successfully added todo task to list.";
                return writeTaskToFile(todoTask, todoResult);

            case "deadline":
                Deadline dl = new Deadline(pa.getDesc(), pa.getDate());
                TaskList.add(dl);
                String deadlineTask = "D," +
                        "1," +
                        dl.getDescription() +
                        "," +
                        dl.getBy() + '\n';

                String deadlineResult = "Successfully added deadline to list.";
                return writeTaskToFile(deadlineTask, deadlineResult);

            case "event":
                Event ev = new Event(pa.getDesc(), pa.getDate());
                TaskList.add(ev);
                String eventTask = "E," +
                        "1," +
                        ev.getDescription() +
                        "," +
                        ev.getAt() + '\n';

                String eventResult = "Successfully added event to list.";
                return writeTaskToFile(eventTask, eventResult);

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
                int idx = pa.getIndex();
                Storage s = new Storage();

                if (pa.getType().equals("Deadline")) {
                    Deadline currentTask = (Deadline) Storage.taskList.get(idx);
                    Deadline updatedTask = null;
                    if (pa.getDesc().isEmpty() && !pa.getDate().isEmpty()) {
                        updatedTask = new Deadline(currentTask.getDescription(), pa.getDate());
                    } else if (!pa.getDesc().isEmpty() && pa.getDate().isEmpty()) {
                       updatedTask = new Deadline(pa.getDesc(), currentTask.getBy());
                    } else if (!pa.getDesc().isEmpty() && !pa.getDate().isEmpty()) {
                        updatedTask = new Deadline(pa.getDesc(), pa.getDate());
                    }
                    Storage.taskList.remove(idx);
                    Storage.taskList.add(idx, updatedTask);
                }

                if (pa.getType().equals("Event")) {
                    Event currentTask = (Event) Storage.taskList.get(idx);
                    Event updatedTask = null;
                    if (pa.getDesc().isEmpty() && !pa.getDate().isEmpty()) {
                        updatedTask = new Event(currentTask.getDescription(), pa.getDate());
                    } else if (!pa.getDesc().isEmpty() && pa.getDate().isEmpty()) {
                        updatedTask = new Event(pa.getDesc(), currentTask.getAt());
                    } else if (!pa.getDesc().isEmpty() && !pa.getDate().isEmpty()) {
                        updatedTask = new Event(pa.getDesc(), pa.getDate());
                    }

                    Storage.taskList.remove(idx);
                    Storage.taskList.add(idx, updatedTask);
                }

                if (pa.getType().equals("Todo")) {
                    if (!pa.getDesc().isEmpty()) {
                        ToDos updatedTask = new ToDos(pa.getDesc());
                        Storage.taskList.remove(idx);
                        Storage.taskList.add(idx, updatedTask);
                    }
                }
                
                s.save();
                return "Update successful";
        }
        return "An unexpected error has occurred.";
    }
}
