package duke;

import java.util.ArrayList;

public class Ui {

    Parser parser;
    String line = "____________________________________________________________";
    String indentation = "    ";

    Ui() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        System.out.println("Hello from\n" + logo);
        System.out.println(indentation + line);
        System.out.println(indentation + "Hello! I'm Duke\n" + indentation + "What can I do for you?\n" + indentation + "Type /help to see the commands that I can run :)");
        System.out.println(indentation + line);
        parser = new Parser();
    }

    public void showLoadingError() {
        String message = indentation + "There is an error trying to load previous file.";
        System.out.println(indentation + line);
        System.out.println(indentation + message);
        System.out.println(indentation + line);
    }

    public void showWrongCommand() {
        System.out.println(indentation + line);
        System.out.println(indentation + " OOPS!!! I'm sorry, but I don't know what that means :-(");
        System.out.println(indentation + line);
    }

    public void showWrongFormat(String methodType) {
        System.out.println(indentation + line);
        System.out.println(indentation + " OOPS!!! You have entered an incorrect format of " + methodType);
        System.out.println(indentation + line);
    }

    public void outputMessage(String message) {
        System.out.println(indentation + line);
        System.out.println(indentation + message);
        System.out.println(indentation + line);
    }

    public void printList(ArrayList<String> messages) {
        System.out.println(indentation + line);
        for (String s: messages) {
            System.out.println(indentation + s);
        }
        System.out.println(indentation + line);
    }

}
