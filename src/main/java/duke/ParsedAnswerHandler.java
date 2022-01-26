package duke;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class ParsedAnswerHandler {
    ParsedAnswer pa;

    ParsedAnswerHandler(ParsedAnswer pa) {
        this.pa = pa;
    }

    void execute() {
        String filePath = System.getProperty("user.dir")  + "/data/storage.txt";
        switch (pa.getCommand()) {
            case "bye":
                System.out.println("Have a nice day.");
                System.exit(0);
                break;

            case "list":
                TaskList.list();
                break;

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
                    System.out.println("Successfully added todo task to list.");
                } catch (IOException e) {
                    System.out.println("Error saving task to file.");
                }
                break;

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
                    System.out.println("Successfully added deadline to list.");
                } catch (IOException e) {
                    System.out.println("Error saving task to file.");
                }
                break;

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
                    System.out.println("Successfully added event to list.");
                } catch (IOException e) {
                    System.out.println("Error saving task to file.");
                }
                break;

            case "mark":
                try {
                    Task t = Storage.taskList.get(pa.getIndex() - 1);
                    t.markAsDone();
                    Storage s = new Storage();
                    s.save();
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("Item not found. Please try again.");
                }
                break;

            case "unmark":
               try {
                   Task t = Storage.taskList.get(pa.getIndex() - 1);
                   t.markAsUndone();
                   Storage s = new Storage();
                   s.save();
               } catch (IndexOutOfBoundsException e) {
                   System.out.println("Item not found. Please try again.");
               }
               break;

            case "error":
                System.out.println(pa.getDesc());
                break;

            case "delete":
                TaskList.delete(pa.getIndex());
                break;
        }
    }
}
