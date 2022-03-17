package pyke;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.time.format.DateTimeParseException;

import pyke.command.Command;
import pyke.exception.EmptyDescriptionException;
import pyke.exception.InvalidCommandException;
import pyke.exception.InvalidNumberException;
import pyke.exception.PykeException;
import pyke.ui.Ui;
import pyke.util.Parser;
import pyke.util.Storage;
import pyke.util.TaskList;


public class Pyke {

    private Ui ui;
    private TaskList taskList;
    private Parser parser;
    private Storage storage;

    /**
     * The default constructor of Pyke class. It will load the ui, taskList, parser and storage.
     * In addition, the storage will load the default list from a local file
     */
    public Pyke() {
        ui = new Ui();
        taskList = new TaskList();
        parser = new Parser();
        storage = new Storage("data", "TaskList.txt");
    }


    /**
     * Initializes the ui component.
     * It will load the local file into taskList.
     *
     * @return the message to be sent after initialization.
     */
    public String uiInit() {
        try {
            storage.init(taskList);
        } catch (IOException e) {
            return ui.outputUiText("Oops, seems like there is an error when reading local saved files");
        }
        return ui.outputUiGreeting();
    }

    /**
     * Returns runtime processing command component for GUI application.
     * For an input given, it will evaluate and run the command and return corresponding results.
     *
     * @param input the user input received from GUI controller.
     * @return the corresponding output from running the input command.
     */
    public String processCommand(String input) {
        try {
            Command c = parser.parseCommand(input);
            return c.executeUi(taskList, ui, storage);
        } catch (InvalidCommandException e) {
            return ui.outputUiText("OOPS!!! I'm sorry, but I don't know what that means :-(");
        } catch (InvalidNumberException e) {
            return ui.outputUiText("OOPS!!! Seems like this is a invalid number :-(");
        } catch (EmptyDescriptionException e) {
            return ui.outputUiText("OOPS!!! The description cannot be empty. :-(");
        } catch (DateTimeParseException e) {
            return ui.outputUiText("OOPS!!! Please enter date in yyyy-mm-dd style. (e.g. 2002-06-25)");
        } catch (IOException e) {
            return ui.outputUiText("Oops, seems like there is an error when writing to local saved files");
        } catch (PykeException e) {
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            e.printStackTrace(pw);
            String sStackTrace = sw.toString(); // stack trace as a string
            return ui.outputUiText(sStackTrace);
        }
    }
    /**
     * The main body of the chat box. Will receive commands and do things accordingly
     */
    public void run() {
        ui.sayGreeting();
        boolean isExit = false;
        try {
            storage.init(taskList);
        } catch (IOException e) {
            ui.outputException("Oops, seems like there is an error when reading local saved files");
        }
        while (!isExit) {
            try {
                Command c = parser.parseCommand(ui.getCommand());
                c.execute(taskList, ui, storage);
                isExit = c.isExit();
            } catch (InvalidCommandException e) {
                ui.outputException("OOPS!!! I'm sorry, but I don't know what that means :-(");
            } catch (InvalidNumberException e) {
                ui.outputException("OOPS!!! Seems like this is a invalid number :-(");
            } catch (EmptyDescriptionException e) {
                ui.outputException("OOPS!!! The description cannot be empty. :-(");
            } catch (DateTimeParseException e) {
                ui.outputException("OOPS!!! Please enter date in yyyy-mm-dd style. (e.g. 2002-06-25)");
            } catch (IOException e) {
                ui.outputException("Oops, seems like there is an error when writing to local saved files");
            } catch (PykeException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        new Pyke().run();
    }
}

