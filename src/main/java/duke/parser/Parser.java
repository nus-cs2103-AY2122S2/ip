package duke.parser;



import duke.exceptions.InvalidTypeException;
import duke.exceptions.MissingNameException;
import duke.exceptions.MissingEventDateException;
import duke.exceptions.MissingDeadlineDateException;
import duke.main.Duke;
import duke.storage.Storage;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.ToDo;
import duke.parser.DateTimeParser;
import duke.ui.Ui;
import duke.task.Task;

import java.time.format.DateTimeParseException;

public class Parser {

    private String userInput;

    public Parser(String userInput) {
        this.userInput = userInput;
    }


    public boolean processInput() {
        if(userInput.equals("bye")) {
            return false;
        } else if (userInput.equals("list")) {
            String listTask = Task.printArray();
            String output = Ui.createLine()
                    + listTask + "\n"
                    + Ui.createLine();
            System.out.println(output);

            return true;
        } else if (userInput.split(" ")[0].equals("mark")) {
            String[] input = userInput.split(" ");
            Task task =  Task.getTaskList()[Integer.parseInt(input[1]) - 1];
            task.markDone();

            String output = Ui.createLine()
                    + "       I have marked the following task as done! :D \n"
                    + "       " + task + "\n"
                    + Ui.createLine();

            System.out.println(output);
            return true;

        } else if (userInput.split(" ")[0].equals("unmark")) {
            String[] input = userInput.split(" ");
            Task task =  Task.getTaskList()[Integer.parseInt(input[1]) - 1];
            task.markNotDone();

            String output = Ui.createLine()
                    + "       OK I have marked the following task as not done yet! :D \n"
                    + "       " + task + "\n"
                    + Ui.createLine();
            System.out.println(output);

            return true;
        } else if (userInput.split(" ")[0].equals("delete")) {
            String[] input = userInput.split(" ");
            Task task = Task.getTaskList()[Integer.parseInt(input[1]) - 1];
            task.deleteTask(task);

            String output = Ui.createLine()
                    + "       OK I have delete the following task! :D \n"
                    + "       " + task + "\n"
                    + "       " + "Now you have " + Task.getCounter() + " tasks in your list.\n"
                    + Ui.createLine();
            System.out.println(output);

            return true;
        } else {

            try {
                String newTaskMessage = Parser.createTask(userInput);
                System.out.println(newTaskMessage);
            } catch (InvalidTypeException e) {
                String errorMsg = Ui.createLine()
                        + "       Opps, the command \"" + userInput + "\" is not supported :(\n"
                        + Ui.createLine();
                System.out.println(errorMsg);
            } catch (MissingNameException e ) {
                String errorMsg = Ui.createLine()
                        + "       You have entered \"" + userInput + "\".\n"
                        + "       You have to include name after command!\n"
                        + Ui.createLine();
                System.out.println(errorMsg);
            } catch (MissingEventDateException e) {
                String errorMsg = Ui.createLine()
                        + "       You have entered \"" + userInput + "\".\n"
                        + "       You have to include date after command!\n"
                        + "       Please follow format [event <name>/at <date>]\n"
                        + Ui.createLine();
                System.out.println(errorMsg);
            } catch (MissingDeadlineDateException e ) {
                String errorMsg = Ui.createLine()
                        + "       You have entered \"" + userInput + "\".\n"
                        + "       You have to include deadline after command!\n"
                        + "       Follow format [deadline <name>/by <date>]\n"
                        + Ui.createLine();
                System.out.println(errorMsg);
            }

            return true;
        }
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

       updateFile();


        String output = "   __________________________________________________\n"
                + "       Got it! I have added this following task :D \n"
                + "       " + currentTask + "\n"
                + "       " + "Now you have " + Task.getCounter() + " tasks in your list.\n"
                + "   __________________________________________________";

        return output;
    }

    public static void updateFile() {
        Storage storage = new Storage(Storage.filePath);
        storage.writeToPath(Task.printArray());
    }

}
