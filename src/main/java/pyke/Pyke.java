package pyke;

import pyke.command.Command;
import pyke.exception.EmptyDescriptionException;
import pyke.exception.InvalidCommandException;
import pyke.exception.InvalidNumberException;
import pyke.exception.PykeException;
import pyke.ui.Ui;
import pyke.util.Parser;
import pyke.util.Storage;
import pyke.util.TaskList;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.time.format.DateTimeParseException;


public class Pyke {

    private Ui ui;
    private TaskList taskList;
    private Parser parser;
    private Storage storage;

    public Pyke() {
        ui = new Ui();
        taskList = new TaskList();
        parser = new Parser();
        storage = new Storage("data", "TaskList.txt");
    }


    public String UiInit() {
        try {
            storage.init(taskList);
        } catch (IOException e) {
            return ui.outputUiText("Oops, seems like there is an error when reading local saved files");
        }
        return ui.outputUiGreeting();
    }

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
        } catch (DateTimeParseException e){
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
        boolean exitFlag = false;
        try {
            storage.init(taskList);
        } catch (IOException e) {
            ui.outputException("Oops, seems like there is an error when reading local saved files");
        }
        while(!exitFlag) {
            try {
                Command c = parser.parseCommand(ui.getCommand());
                c.execute(taskList, ui, storage);
                exitFlag = c.isExit();
            } catch (InvalidCommandException e) {
                ui.outputException("OOPS!!! I'm sorry, but I don't know what that means :-(");
            } catch (InvalidNumberException e) {
                ui.outputException("OOPS!!! Seems like this is a invalid number :-(");
            } catch (EmptyDescriptionException e) {
                ui.outputException("OOPS!!! The description cannot be empty. :-(");
            } catch (DateTimeParseException e){
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

