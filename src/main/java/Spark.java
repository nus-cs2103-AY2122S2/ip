import java.util.*;
import java.util.Scanner;

import SparkExceptions.FormatExceptions.*;
import SparkExceptions.SparkException;
import Tasks.TaskList;

public class Spark {
    public static void main(String[] args) {
        // assuming that there will be no more than 100 tasks,
        TaskList taskList = new TaskList();

        Scanner sc = new Scanner(System.in);

        // print welcome message
        System.out.println("----------------------------------------------------------------------");
        System.out.println("Hello! I'm Spark");
        System.out.println("What can I do for you?");
        System.out.println("----------------------------------------------------------------------");

        String input;
        String[] tokens;
        String commandKeyword;
        Command c;
        do {
            // get input from user
            input = sc.nextLine();
            tokens = input.split(" "); // split command into individual keywords by single-space
            commandKeyword = tokens[0]; // assume that the first keyword is always the command word
            c = Command.getCommand(commandKeyword);

            System.out.println("----------------------------------------------------------------------");

            try {
                switch (c) {
                    case BYE:
                        System.out.println("Cool, see you around!");
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
                System.out.println(e.getMessage());
            }

            System.out.println("----------------------------------------------------------------------");
        } while (c != Command.BYE);
    }

}
