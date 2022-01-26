import ui.Ui;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;




public class Duke {

    private Ui ui;

    public Duke(String filePath) {

        ui = new Ui();

        File dataFolder = new File ("data");

        if (!dataFolder.exists()) {
            dataFolder.mkdir();
        }

        File dataFile = new File("data/duke.txt");
        if (!dataFile.exists()) {
            try {
                dataFile.createNewFile();
            } catch (IOException e) {
                System.out.println("An error occured :(" + e);
            }
        }
    }

    public void run(){

        Scanner sc = new Scanner(System.in);
        String userInput = sc.nextLine();

        while(processInput(userInput)) {
            userInput = sc.nextLine();
        }
    }

    private static boolean isBye(String s) {
        return !s.equals("bye");
    }


    private boolean processInput(String userInput) {
        if(userInput.equals("bye")) {
            ui.exit();
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
                String newTaskMessage = createTask(userInput);
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

                currentTask = new Event(splitString[0].substring(5),
                        DateTimeParser.parseDate(splitString[1].substring(3)));
                break;
            case "deadline":
                if(instruction.length == 1 || instruction[1].equals("")) {
                    throw new MissingNameException("Missing description");
                }

                if(splitString.length == 1 || !splitString[1].startsWith("by ")) {
                    throw new MissingDeadlineDateException("Missing date");
                }

                currentTask = new Deadline(splitString[0].substring(8),
                        DateTimeParser.parseDate(splitString[1].substring(3)));
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
