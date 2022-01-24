
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    private Ui ui;
    private Storage storage;

    public Duke (String directory,String filePath) throws FileNotFoundException {
        ui = new Ui();
        storage = new Storage(directory,filePath);
        Parser parse = new Parser(directory,filePath);
        parse.checkDir();
        parse.checkFile();


    }

    public void run() throws IOException {
        ui.welcomeMsg();
        boolean isExit = false;

        while(!isExit) {
                ui.userCommand();
                isExit = ui.isExit;

        }

    }

    public static void main(String[] args) throws DukeException, IOException {
        new Duke("C:\\DukeDirectory","C:\\DukeDirectory\\DukeSave.txt").run();
    }







}
