package duke;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/**
 * This class receives a ParsedAnswer object and execute commands based on information
 * from the object.
 */

public class ParsedAnswerHandler {
    ParsedAnswer pa;

    ParsedAnswerHandler(ParsedAnswer pa) {
        this.pa = pa;
    }

    String execute() {
        String filePath = System.getProperty("user.dir")  + "/data/storage.txt";
        switch (pa.getCommand()) {
            case "bye":
                Duke.isGoodbye = true;
                return "Have a nice day.";

            case "list":
                return TaskList.list();

            case "todo":
                ToDos td = new ToDos(pa.getDesc());
                TaskList.add(td);
                try {
                    String sb = "T," +
                            "1," +
                            td.getDescription() + '\n';
                    BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true));
                    writer.append(sb);
                    writer.close();
                    return "Successfully added todo task to list.";
                } catch (IOException e) {
                    return "Error saving task to file.";
                }

            case "deadline":
                Deadline dl = new Deadline(pa.getDesc(), pa.getDate());
                TaskList.add(dl);
                try {
                    String sb = "D," +
                            "1," +
                            dl.getDescription() +
                            "," +
                            dl.getBy() + '\n';
                    BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true));
                    writer.append(sb);
                    writer.close();
                    return "Successfully added deadline to list.";
                } catch (IOException e) {
                    return "Error saving task to file.";
                }

            case "event":
                Event ev = new Event(pa.getDesc(), pa.getDate());
                TaskList.add(ev);
                try {
                    String sb = "E," +
                            "1," +
                            ev.getDescription() +
                            "," +
                            ev.getAt() + '\n';
                    BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true));
                    writer.append(sb);
                    writer.close();
                    return "Successfully added event to list.";
                } catch (IOException e) {
                    return "Error saving task to file.";
                }

            case "mark":
                try {
                    Task t = Storage.taskList.get(pa.getIndex() - 1);
                    t.markAsDone();
                    Storage s = new Storage();
                    s.save();
                    return "Successfully marked " + pa.getIndex();
                } catch (IndexOutOfBoundsException e) {
                    return "Item not found. Please try again.";
                }

            case "unmark":
               try {
                   Task t = Storage.taskList.get(pa.getIndex() - 1);
                   t.markAsUndone();
                   Storage s = new Storage();
                   s.save();
                   return "Successfully ummarked " + pa.getIndex();
               } catch (IndexOutOfBoundsException e) {
                   return "Item not found. Please try again.";
               }

            case "error":
                return pa.getDesc();

            case "delete":
                return TaskList.delete(pa.getIndex());

            case "find":
                return TaskList.find(pa.getDesc());
        }

        return "-1";
    }
}
