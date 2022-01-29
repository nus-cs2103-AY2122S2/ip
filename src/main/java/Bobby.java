import java.io.IOException;
import java.util.Locale;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.IOException;
import java.io.FileWriter;
import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Bobby {
    static ArrayList<Task> taskArray = new ArrayList<Task>();

    private static void addToDo(String task) throws BobbyException {
        String[] inputs = task.split(" ", 2);
        if (inputs.length > 1) {
            Todo newTodo = new Todo(inputs[1]);
            System.out.println("Bobby heard: " + newTodo);
            taskArray.add(newTodo);
            System.out.println("Bobby remembers " + taskArray.size() + " task(s).");
        } else {
            throw new BobbyException("Description cannot be empty");
        }
    }
    private static void addDeadline(String task) throws BobbyException {
        String[] inputs = task.split(" ", 2);
        if (inputs.length > 1) {
            String[] splitInputs = inputs[1].split(" /by ", 2);
            if (splitInputs.length > 1) {
                String description = splitInputs[0];
                LocalDate by = LocalDate.parse(splitInputs[1]);
                Deadline newDeadline = new Deadline(description, by);
                System.out.println("Bobby heard: " + newDeadline);
                taskArray.add(newDeadline);
                System.out.println("Bobby remembers " + taskArray.size() + " task(s).");
            } else {
                throw new BobbyException("Date/Time format of Deadline is incorrect or empty");
            }
        } else {
            throw new BobbyException("Description cannot be empty");
        }

    }
    private static void addEvent(String task) throws BobbyException {
        String[] inputs = task.split(" ", 2);
        if (inputs.length > 1) {
            String[] splitInputs = inputs[1].split(" /at ", 2);
            if (splitInputs.length > 1) {
                String description = splitInputs[0];
                String at = splitInputs[1];
                Event newEvent = new Event(description, at);
                System.out.println("Bobby heard: " + newEvent);
                taskArray.add(newEvent);
                System.out.println("Bobby remembers " + taskArray.size() + " task(s).");
            } else {
                throw new BobbyException("Date/Time format of Event is incorrect or empty");
            }
        } else {
            throw new BobbyException("Description cannot be empty");
        }
    }
    private static void delete(String task) throws BobbyException {
        String[] inputs = task.split(" ", 2);
        if (inputs.length > 1) {
            int i = Integer.parseInt(inputs[1]) - 1;
            System.out.println("Bobby has forgotten this task:\n" + taskArray.get(i));
            taskArray.remove(i);
            System.out.println("Bobby remembers " + taskArray.size() + " task(s).");
        } else {
            throw new BobbyException("Indicate which task should be deleted.");
        }
    }

    public static void main(String[] args) {
        System.out.println("Bobby greets you. Bobby is here to help.");
        try {
            File file = new File("bobby.txt");
            file.createNewFile();
        } catch (IOException e) {
            System.out.println("An error has occurred.");
        }
        Scanner scannerObj = new Scanner(System.in);

        while (scannerObj.hasNextLine()) {
            String userInput = scannerObj.nextLine();
            String[] inputs = userInput.split(" ", 2);
            String command = inputs[0];

            switch (Commands.valueOf(command.toUpperCase())) {
            case BYE:
                System.out.println("Bobby bids you farewell.");
                break;
            case LIST:
                System.out.println("Here is what you told Bobby:");
                for (int i = 0; i < taskArray.size(); i++) {
                    Task t = taskArray.get(i);
                    int count = i + 1;
                    System.out.println(count + "." + t);
                }
                break;
            case MARK:
                Task t = taskArray.get(Integer.parseInt(inputs[1]) - 1);
                t.markAsDone();
                System.out.println("Bobby applauds you. This task is done:\n" + t);
                break;
            case UNMARK:
                Task i = taskArray.get(Integer.parseInt(inputs[1]) - 1);
                i.unmarkAsDone();
                System.out.println("Bobby will remember that this task is not yet done:\n" + i);
                break;
            case TODO:
                try {
                    addToDo(userInput);
                } catch (BobbyException e) {
                    System.err.println(e);
                }
                break;
            case DEADLINE:
                try {
                    addDeadline(userInput);
                } catch (BobbyException e) {
                    System.err.println(e);
                }
                break;
            case EVENT:
                try {
                    addEvent(userInput);
                } catch (BobbyException e) {
                    System.err.println(e);
                }
                break;
            case DELETE:
                try {
                    delete(userInput);
                } catch (BobbyException e) {
                    System.err.println(e);
                }
                break;
            default:
                try {
                    throw new BobbyException("Bobby does not understand you. Please use valid inputs.");
                } catch (BobbyException e) {
                    System.err.println(e);
                }
            }
        }
    }
}
