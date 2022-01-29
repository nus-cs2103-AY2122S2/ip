package ui;

import exception.DukeException;
import parser.Parser;
import storage.Storage;
import task.TaskList;

import java.util.Scanner;

public class Ui {

    TaskList tasks;

    public Ui(TaskList tasks) {
        this.tasks = tasks;
    }

    public void startConversation(Parser parser, Storage storage) throws DukeException {
        Scanner sc = new Scanner(System.in);
        System.out.println("Hello! I'm Duke :) \nWhat can I do for you? :D");
        String inputString = sc.nextLine();
        String[] inputStringArray = inputString.split(" ");

        while (!inputStringArray[0].equals("bye")) {
            parser.userCommand(inputStringArray, storage);
            inputString = sc.nextLine();
            inputStringArray = inputString.split(" ");
        }
        System.out.println("Bye. Hope to see you again soon! :))");
    }

    public void showLoadingError() {
        System.out.println("File not loading. Opening a new Duke file...");
    }

    public void showIllegalTextError() {
        System.out.println("Sorry I don't know what that means O.o");
    }

}
