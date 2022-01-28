import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.lang.*;
import java.io.FileWriter;   // Import the FileWriter class
import java.io.IOException;  // Import the IOException class to handle errors

public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            //tasks = new TaskList(storage.load());
            tasks = new TaskList();
        } catch (Exception e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

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
                Task task = (Task) tasks.get(index - 1);
                task.markAsDone();
                System.out.println("Nice! I've marked this task as done: ");
                System.out.println(tasks.get(index - 1));

            } else if (splitStr[0].equals("unmark")) {
                int index = Integer.parseInt(splitStr[1]);
                Task task = (Task) tasks.get(index - 1);
                task.unmarkAsDone();
                ui.markdone(tasks.get(index - 1));

            } else if (splitStr[0].equals("delete")) {
                int index = Integer.parseInt(splitStr[1]);
                Task task = (Task) tasks.get(index - 1);
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
                        tasks.add(new ToDo(description));
                    }
                } catch (Exception e) {
                    ui.emptyInput();
                }
                try {
                    if (splitStr[0].equals("deadline")) {
                        description = description.substring(9);
                        tasks.add(new Deadline(description));
                    }
                } catch (Exception e) {
                    ui.emptyInput();
                }
                try {
                    if (splitStr[0].equals("event")) {
                        description = description.substring(6);
                        tasks.add(new Event(description));
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


    public static void main(String[] args) {
        new Duke("data/tasks.txt").run();
    }
}


