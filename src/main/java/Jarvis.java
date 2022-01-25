import exception.*;
import storage.Storage;
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
    private static Storage storage;

    private static boolean processNext = true;

    public static void main(String[] args) throws JarvisException {
        storage = new Storage("data/data.txt");
        ui = new Ui();
        tasks = new TaskList(storage.loadData(), ui);

        ui.welcome();

        while (processNext) {
            try {
                String input = ui.readCommand();
                String[] tokens = input.split(" ");
                switch (Command.valueOf(tokens[0].trim().toUpperCase())) {
                case BYE:
                    processNext = false;
                    storage.saveChanges(tasks);
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
}
