package main.java;

import java.io.*;
import java.util.Scanner;

public class Duke {
    public Storage storage;
    public TaskList tasks;
    public Ui ui;

    public Duke(String filePath) throws ClassNotFoundException, IOException {

        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.readFromFile());
        } catch (ClassNotFoundException e) {
            throw e;
        } catch (FileNotFoundException e) {
            tasks = new TaskList();
        } catch (IOException e) {
            throw e;
        }

    }

    public void run() throws IOException, ClassNotFoundException {
        Ui.greeting();
        Scanner sc = new Scanner(System.in);
        while (sc.hasNextLine()) {
            Parser p = new Parser(storage, tasks, ui, sc.nextLine());
            p.userCommand();
        }


    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        new Duke("dukeDataSaved.txt").run();
    }

}
