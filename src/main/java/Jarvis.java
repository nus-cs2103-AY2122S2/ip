import exception.*;
import task.*;
import enums.*;
import ui.Ui;

import java.time.LocalDateTime;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Jarvis {
    private static Ui ui;
    private static TaskList tasks;

    private static final String dataFilePath = "data/data.txt";

    private static boolean processNext = true;

    public static void main(String[] args) throws JarvisException {
        ui = new Ui();
        ui.welcome();
        loadData();

        while (processNext) {
            try {
                String input = ui.readCommand();
                String[] tokens = input.split(" ");
                switch (Command.valueOf(tokens[0].trim().toUpperCase())) {
                case BYE:
                    processNext = false;
                    saveChanges();
                    ui.shutdown();
                    return;
                case LIST:
                    tasks.printTasks();
                    break;
                case MARK:
                    tasks.markAsDone(tokens);
                    break;
                case UNMARK:
                    tasks.markAsUndone(tokens);
                    break;
                case DELETE:
                    tasks.delete(tokens);
                    break;
                case TODO:
                    tasks.addTodo(input);
                    break;
                case DEADLINE:
                    tasks.addDeadline(input);
                    break;
                case EVENT:
                    tasks.addEvent(input);
                    break;
                default:
                    break;
                }
            } catch (IllegalArgumentException e) {
                ui.echo("I'm afraid I don't understand your request.");
            } catch (JarvisException de) {
                ui.echo("I'm afraid I wasn't able to fulfill your request.\n" + de.getMessage());
            }
        }
    }

    private static void loadData() throws JarvisException {
        try {
            List<Task> lst = new ArrayList<>();
            File dataFile = new File(dataFilePath);
            if (dataFile.exists()) {
                Scanner s = new Scanner(dataFile);
                while (s.hasNext()) {
                    String[] line = s.nextLine().split("\\|");
                    Task task;
                    switch (line[0]) {
                    case "T":
                        task = new Todo(line[2]);
                        break;
                    case "D":
                        task = new Deadline(line[2], LocalDateTime.parse(line[3]));
                        break;
                    case "E":
                        task = new Event(line[2], LocalDateTime.parse(line[3]));
                        break;
                    default:
                        throw new JarvisException("Unexpected task type encountered: " + line[0]);
                    }

                    if (line[1].equals("1")) {
                        task.markAsDone();
                    }

                    lst.add(task);
                }
            } else {
                dataFile.getParentFile().mkdirs();
                dataFile.createNewFile();
            }
            tasks = new TaskList(lst, ui);
        } catch (IOException e) {
            throw new JarvisException("An error has occurred whilst retrieving your tasks.");
        }
    }

    private static void saveChanges() throws JarvisException {
        try {
            StringBuilder sb = new StringBuilder();
            for (Task t : tasks.getTasks()) {
                sb.append(t.toFileString()).append("\n");
            }
            FileWriter fw = new FileWriter(dataFilePath);
            fw.write(sb.toString());
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
            throw new JarvisException("An error has occurred whilst saving your tasks.");
        }
    }
}
