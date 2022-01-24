import java.util.Scanner;

import SparkExceptions.FormatExceptions.*;
import SparkExceptions.SparkException;
import Tasks.TaskList.TaskList;

public class Spark {
    private final TaskList taskList;
    private final Ui ui;

    public Spark() throws SparkException {
        this.taskList = new TaskList();
        this.ui = new Ui();
    }

    private void quit() {
        ui.printMessage("Cool, see you around!");
        System.exit(0);
    }

    public void run() {
        while (true) {
            // get input from user
            String input = ui.getInput();
            String[] tokens = input.split(" "); // split command into individual keywords by single-space
            String commandKeyword = tokens[0]; // assume that the first keyword is always the command word
            Command c = Command.getCommand(commandKeyword);

//            System.out.println("----------------------------------------------------------------------");
            try {
                switch (c) {
                case BYE:
                    quit();
                    break;
                case LIST:
                    taskList.showTaskList();
                    break;
                case MARK:
                    taskList.markTask(tokens);
                    break;
                case UNMARK:
                    taskList.unMarkTask(tokens);
                    break;
                case DELETE:
                    taskList.deleteTask(tokens);
                    break;
                case TODO:
                    taskList.addToDo(tokens);
                    break;
                case DEADLINE:
                    taskList.addDeadline(tokens);
                    break;
                case EVENT:
                    taskList.addEvent(tokens);
                    break;
                case UNRECOGNISED:
                    throw new UnrecognisedCommandException();
                }
            } catch (SparkException e) {
                ui.printException(e);
            }

//            System.out.println("----------------------------------------------------------------------");
        }
    }

    public static void main(String[] args) {
        try {
            new Spark().run();
        } catch(SparkException e) {
            System.out.println("A fatal error occurred. Exiting now...");
        }
    }

}
