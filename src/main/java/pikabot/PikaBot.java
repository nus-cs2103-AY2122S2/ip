package pikabot;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import pikabot.command.Command;
import pikabot.task.Task;


/**
 * Runs the whole application PikaBot, an application
 * that serves as a ChatBot to keep track of your tasks.
 */
public class PikaBot {

    private static final String FILEPATH = "data/tasks.txt";

    /**
     * Boots up PikaBot application.
     *
     * @param args Command line arguments entered by user.
     */
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        Storage storage = new Storage(FILEPATH);
        Ui.printWelcomeText();
        TaskList taskList = new TaskList(new ArrayList<Task>());

        if (storage.doesFileExist()) {
            try {
                taskList = Storage.fileToTaskList(new File(FILEPATH));
            } catch (FileNotFoundException e) {
                Ui.printExceptionMessage(e);
            }
        }

        String input = sc.nextLine();
        String[] strInputArr = input.split(" ", 2);

        while (!strInputArr[0].equals("bye")) {
            Command command = Parser.parseCommand(strInputArr);
            command.execute(taskList, storage);
            input = sc.nextLine();
            strInputArr = input.split(" ", 2);
        }

        Ui.printClosingText();
        sc.close();
    }
}
