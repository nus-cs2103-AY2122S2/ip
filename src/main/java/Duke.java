package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.lang.*;
import java.io.FileWriter;   // Import the FileWriter class
import java.io.IOException;  // Import the IOException class to handle errors

/**
 * Programme which serves as an interactive checklist.
 */
public class Duke {

    private duke.Storage storage;
    private duke.TaskList tasks;
    private duke.Ui ui;

    /**
     * Initializes Duke.
     * @param filePath represents the path of the file and existing tasks to be loaded
     *                 if already present.
     */
    public Duke(String filePath) {
        ui = new duke.Ui();
        storage = new duke.Storage(filePath);
        try {
            //tasks = new TaskList(storage.load());
            tasks = new duke.TaskList();
        } catch (Exception e) {
            ui.showLoadingError();
            tasks = new duke.TaskList();
        }
    }

    /**
     * Runs through live user input to add, edit the tasks.
     */
    void run() {
        ui.start();

        Scanner sc = new Scanner(System.in);


        while (sc.hasNextLine()) {
            String value = sc.nextLine();
            String[] splitStr = value.split("\\s+");

            if (value.equals("bye")) {
                ui.finalBye();
                return;

            } else if (value.equals("list")) {
                for (int i = 0; i < tasks.size(); i++) {
                    System.out.println(i + 1 + ". " + tasks.get(i));
                }

            } else if (splitStr[0].equals("mark")) {
                int index = Integer.parseInt(splitStr[1]);
                duke.Task task = (duke.Task) tasks.get(index - 1);
                task.markAsDone();
                ui.markDone(tasks.get(index - 1));


            } else if (splitStr[0].equals("unmark")) {
                int index = Integer.parseInt(splitStr[1]);
                duke.Task task = (duke.Task) tasks.get(index - 1);
                task.unmarkAsDone();
                ui.unmarkDone(tasks.get(index - 1));

            } else if (splitStr[0].equals("delete")) {
                int index = Integer.parseInt(splitStr[1]);
                duke.Task task = (duke.Task) tasks.get(index - 1);
                tasks.remove(index - 1);
                ui.removedTask(task, tasks);

            } else if (splitStr[0].equals("todo") || splitStr[0].equals("deadline") || splitStr[0].equals("event")) {

                String[] parts = value.split("/");
                String description = parts[0];
                if (parts.length > 1) {
                    if (parts[1].length() == 13) {
                        LocalDate d1 = LocalDate.parse(parts[1].substring(3));
                        parts[1] = d1.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
                    }
                    description += "(" + parts[1] + ")";
                }

                try {
                    if (splitStr[0].equals("todo")) {
                        description = description.substring(5);
                        tasks.add(new duke.ToDo(description));
                    }
                } catch (Exception e) {
                    ui.emptyInput();
                }
                try {
                    if (splitStr[0].equals("deadline")) {
                        description = description.substring(9);
                        tasks.add(new duke.Deadline(description));
                    }
                } catch (Exception e) {
                    ui.emptyInput();
                }
                try {
                    if (splitStr[0].equals("event")) {
                        description = description.substring(6);
                        tasks.add(new duke.Event(description));
                    }
                } catch (Exception e) {
                    ui.emptyInput();
                }

                ui.addTask(tasks);

            } else {
                ui.doNotUnderstand();

            }
            storage.save(tasks);
        }
    }

    /**
     * Creates an instance of Duke and runs it.
     * @param args
     */
    public static void main(String[] args) {
        new Duke("data/tasks.txt").run();
    }
}


