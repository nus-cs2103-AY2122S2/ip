import exceptions.DukeDeadlineException;
import exceptions.DukeEventException;
import exceptions.DukeException;
import exceptions.DukeTodoException;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.time.LocalDate;

//import java.io.*;
//import java.util.*;

/**
 * Represents the Duke bot
 */
public class Duke {

    private Storage storage;
    private TaskList tasklist;
    private Ui ui;

    public Duke(String filepath) throws DukeException {
        ui = new Ui();
        ui.showWelcome();
        storage = new Storage(filepath);
        try {
            tasklist = new TaskList(storage.getAllTasks());
        } catch (DukeException e) {
            tasklist = new TaskList();
        } catch (IOException e) {
            System.exit(0);
        } catch (DukeDeadlineException e) {
            ui.showError(e.getMessage());
            tasklist = new TaskList();
        }
    }

    public static void main(String[] args) throws DukeException {
        new Duke("src/main/data/duke.txt").run();
    }

    public void run() {
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine(); // show the divider line ("_______")
                Command c = Parser.parse(fullCommand);
                c.execute(tasklist, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            } catch (IOException e) {
                ui.showError(e.getMessage());
            } catch (DukeDeadlineException e) {
                ui.showError(e.getMessage());
            } catch (DukeEventException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }
}
