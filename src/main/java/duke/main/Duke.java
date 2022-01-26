package duke.main;

import duke.exceptions.InvalidTypeException;
import duke.exceptions.MissingNameException;
import duke.exceptions.MissingEventDateException;
import duke.exceptions.MissingDeadlineDateException;
import duke.ui.Ui;
import duke.storage.Storage;
import duke.Task;
import duke.ToDo;
import duke.Event;
import duke.Deadline;
import duke.FileReading;
import duke.DateTimeParser;
import duke.parser.Parser;

import java.io.File;
import java.io.IOException;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class Duke {

    private Ui ui;
    private Storage storage;

    public Duke(String filePath) {

        ui = new Ui();
        storage = new Storage(filePath);

    }

    private void run(){

        Scanner sc = new Scanner(System.in);
        String userInput = sc.nextLine();

        while(Parser.processInput(userInput)) {
            userInput = sc.nextLine();
        }
        exit();
    }

    private void exit() {
        ui.exit();
    }

    private static boolean isBye(String s) {
        return !s.equals("bye");
    }



    public static String createTask(String input) throws InvalidTypeException, MissingNameException, MissingEventDateException, MissingDeadlineDateException{
        String[] splitString = input.split("/", 2);
        String[] instruction = splitString[0].split(" ", 2);
        Task currentTask = null;

        switch(instruction[0]) {
            case "todo":
                if(instruction.length == 1 || instruction[1].equals("")) {
                    throw new MissingNameException("Missing description");
                }

                currentTask = new ToDo(input.substring(4));
                break;
            case "event":

                if(instruction.length == 1 || instruction[1].equals("")) {
                    throw new MissingNameException("Missing description");
                }

                if(splitString.length == 1 || !splitString[1].startsWith("at ")) {
                    throw new MissingEventDateException("Missing date");
                }
                try {
                    currentTask = new Event(splitString[0].substring(5),
                            DateTimeParser.parseDate(splitString[1].substring(3)));
                } catch (DateTimeParseException e) {
                    throw new MissingEventDateException("Wrong format of date");
                }

                break;
            case "deadline":
                if(instruction.length == 1 || instruction[1].equals("")) {
                    throw new MissingNameException("Missing description");
                }

                if(splitString.length == 1 || !splitString[1].startsWith("by ")) {
                    throw new MissingDeadlineDateException("Missing date");
                }
                try {
                currentTask = new Deadline(splitString[0].substring(8),
                        DateTimeParser.parseDate(splitString[1].substring(3)));
                } catch (DateTimeParseException e) {
                    throw new MissingEventDateException("Wrong format of date");
                }
                break;

            default:
                throw new InvalidTypeException("Invalid type");
        }

        FileReading.writeToPath("data/duke.txt", Task.printArray());


        String output = "   __________________________________________________\n"
                + "       Got it! I have added this following task :D \n"
                + "       " + currentTask + "\n"
                + "       " + "Now you have " + Task.getCounter() + " tasks in your list.\n"
                + "   __________________________________________________";

        return output;
    }

    public static void main(String[] args) {
        new Duke("data/duke.txt").run();
    }
}
