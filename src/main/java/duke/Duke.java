package duke;

import duke.command.Command;
import duke.datetime.DateTable;
import duke.task.TaskList;
import duke.util.Parser;
import duke.util.Storage;
import duke.util.Ui;

import java.io.*;
import java.util.Scanner;

public class Duke {

    private final static String DIRECTORY = "data";
    private final static String FILE_NAME = "duke.Duke.txt";
    private final static String PATH = DIRECTORY + "/" + FILE_NAME;

    private final Storage storage;
    private final TaskList taskList;
    private final Ui ui;
    private final DateTable dateTable;

    public Duke() throws IOException {
        TaskList taskList1;
        storage = new Storage(PATH, DIRECTORY);
        Scanner sc = new Scanner(System.in);
        ui = new Ui(sc);
        dateTable = new DateTable(ui);
        try {
            taskList1 = new TaskList(storage, ui, dateTable);
        } catch (IOException e) {
            ui.showLoadingError();
            taskList1 = new TaskList();
        }
        this.taskList = taskList1;
    }


    public void run() throws IOException {
        boolean isExit = false;

        while (! isExit) {
            try {
                String command = ui.readCommand();
                Command c = Parser.parse(dateTable, command);
                c.execute(taskList, ui, storage, dateTable);
                isExit = c.isExit();
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }
    public static void main(String[] args) throws IOException {
        new Duke().run();
    }
}
