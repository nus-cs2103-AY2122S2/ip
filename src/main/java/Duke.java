
import java.util.Scanner;



public class Duke {

    private static boolean isBye(String s) {
        return !s.equals("bye");
    }

    private static void welcome() {
        String welcomeMessage = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n"
                + "Hello! I'm JJ\n"
                + "What do you want? :D \n";

        System.out.println("Hello from\n" + welcomeMessage);
    }

    private static void exit() {
        String goodByeMessage = "   __________________________________________________\n"
                + "       " + "Bye. I hope to see you soon." +"\n"
                + "   __________________________________________________";
        System.out.println(goodByeMessage);
    }



    private static boolean processInput(String userInput) {
        if(userInput.equals("bye")) {
            exit();
            return false;
        } else if (userInput.equals("list")) {
            String listTask = Task.printArray();
            String output = "   __________________________________________________\n"
                    + listTask + "\n"
                    + "   __________________________________________________";
            System.out.println(output);

            return true;
        } else if (userInput.split(" ")[0].equals("mark")) {
            String[] input = userInput.split(" ");
            Task task =  Task.getTaskList()[Integer.parseInt(input[1]) - 1];
            task.markDone();

            String output = "   __________________________________________________\n"
                    + "       I have marked the following task as done! :D \n"
                    + "       " + task + "\n"
                    + "   __________________________________________________";

            System.out.println(output);
            return true;

        } else if (userInput.split(" ")[0].equals("unmark")) {
            String[] input = userInput.split(" ");
            Task task =  Task.getTaskList()[Integer.parseInt(input[1]) - 1];
            task.markNotDone();

            String output = "   __________________________________________________\n"
                    + "       OK I have marked the following task as not done yet! :D \n"
                    + "       " + task + "\n"
                    + "   __________________________________________________";
            System.out.println(output);

            return true;
        } else if (userInput.split(" ")[0].equals("delete")) {
            String[] input = userInput.split(" ");
            Task task = Task.getTaskList()[Integer.parseInt(input[1]) - 1];
            task.deleteTask(task);

            String output = "   __________________________________________________\n"
                    + "       OK I have delete the following task! :D \n"
                    + "       " + task + "\n"
                    + "       " + "Now you have " + Task.getCounter() + " tasks in your list.\n"
                    + "   __________________________________________________";
            System.out.println(output);

            return true;
        } else {

            try {
                String newTaskMessage = createTask(userInput);
                System.out.println(newTaskMessage);
            } catch (InvalidTypeException e) {
                String errorMsg = "   __________________________________________________\n"
                        + "       Opps, the command \"" + userInput + "\" is not supported :(\n"
                        + "   __________________________________________________";
                System.out.println(errorMsg);
            } catch (MissingNameException e ) {
                String errorMsg = "   __________________________________________________\n"
                        + "       You have entered \"" + userInput + "\".\n"
                        + "       You have to include name after command!\n"
                        + "   __________________________________________________";
                System.out.println(errorMsg);
            } catch (MissingEventDateException e) {
                String errorMsg = "   __________________________________________________\n"
                        + "       You have entered \"" + userInput + "\".\n"
                        + "       You have to include date after command!\n"
                        + "       Please follow format [event <name>/at <date>]\n"
                        + "   __________________________________________________";
                System.out.println(errorMsg);
            } catch (MissingDeadlineDateException e ) {
                String errorMsg = "   __________________________________________________\n"
                        + "       You have entered \"" + userInput + "\".\n"
                        + "       You have to include deadline after command!\n"
                        + "       Follow format [deadline <name>/by <date>]\n"
                        + "   __________________________________________________";
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

        FileReading.writeToPath("/data/duke.txt", Task.printArray());


        String output = "   __________________________________________________\n"
                + "       Got it! I have added this following task :D \n"
                + "       " + currentTask + "\n"
                + "       " + "Now you have " + Task.getCounter() + " tasks in your list.\n"
                + "   __________________________________________________";

        return output;
    }

    public static void main(String[] args) {
        welcome();
        Scanner sc = new Scanner(System.in);
        String userInput = sc.nextLine();

        while(processInput(userInput)) {
            userInput = sc.nextLine();
        }

    }
}
