import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Locale;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.File;

public class Bobby {
    private static ArrayList<Task> taskArray = new ArrayList<Task>();

    private static void updateFile() throws IOException {
        FileWriter fw = new FileWriter("bobby.txt");
        for (int i = 0; i < taskArray.size(); i++) {
            Task t = taskArray.get(i);
            t.writeToFile(fw);
        }
        fw.close();
    }

    private static void loadFile(File file) throws FileNotFoundException {
        Scanner s = new Scanner(file);
        while (s.hasNext()) {
            //System.out.println(s.nextLine());
            String line = s.nextLine();
            String[] inputs = line.split(" ; ");
            switch (inputs[0]) {
            case "T":
                Todo newToDo = new Todo(inputs[2]);
                if (inputs[1].equals("true")) {
                    newToDo.markAsDone();
                }
                taskArray.add(newToDo);
                break;
            case "D":
                Deadline newDeadline = new Deadline(inputs[2], inputs[3]);
                if (inputs[1].equals("true")) {
                    newDeadline.markAsDone();
                }
                taskArray.add(newDeadline);
                break;
            case "E":
                Event newEvent = new Event(inputs[2], inputs[3]);
                if (inputs[1].equals("true")) {
                    newEvent.markAsDone();
                }
                taskArray.add(newEvent);
                break;
            }
        }
    }

    private static void addToDo(String task) throws BobbyException, IOException {
        String[] inputs = task.split(" ", 2);
        if (inputs.length > 1) {
            Todo newTodo = new Todo(inputs[1]);
            System.out.println("Bobby heard: " + newTodo);
            taskArray.add(newTodo);
            updateFile();
            System.out.println("Bobby remembers " + taskArray.size() + " task(s).");
        } else {
            throw new BobbyException("Description cannot be empty");
        }
    }
    private static void addDeadline(String task) throws BobbyException, IOException {
        String[] inputs = task.split(" ", 2);
        if (inputs.length > 1) {
            String[] splitInputs = inputs[1].split(" /by ", 2);
            if (splitInputs.length > 1) {
                String description = splitInputs[0];
                String by = splitInputs[1];
                Deadline newDeadline = new Deadline(description, by);
                System.out.println("Bobby heard: " + newDeadline);
                taskArray.add(newDeadline);
                updateFile();
                System.out.println("Bobby remembers " + taskArray.size() + " task(s).");
            } else {
                throw new BobbyException("Date/Time format of Deadline is incorrect or empty");
            }
        } else {
            throw new BobbyException("Description cannot be empty");
        }

    }
    private static void addEvent(String task) throws BobbyException, IOException {
        String[] inputs = task.split(" ", 2);
        if (inputs.length > 1) {
            String[] splitInputs = inputs[1].split(" /at ", 2);
            if (splitInputs.length > 1) {
                String description = splitInputs[0];
                String at = splitInputs[1];
                Event newEvent = new Event(description, at);
                System.out.println("Bobby heard: " + newEvent);
                taskArray.add(newEvent);
                updateFile();
                System.out.println("Bobby remembers " + taskArray.size() + " task(s).");
            } else {
                throw new BobbyException("Date/Time format of Event is incorrect or empty");
            }
        } else {
            throw new BobbyException("Description cannot be empty");
        }
    }
    private static void delete(String task) throws BobbyException, IOException {
        String[] inputs = task.split(" ", 2);
        if (inputs.length > 1) {
            int i = Integer.parseInt(inputs[1]) - 1;
            System.out.println("Bobby has forgotten this task:\n" + taskArray.get(i));
            taskArray.remove(i);
            updateFile();
            System.out.println("Bobby remembers " + taskArray.size() + " task(s).");
        } else {
            throw new BobbyException("Indicate which task should be deleted.");
        }
    }

    public static void main(String[] args) throws IOException {
        System.out.println("Bobby greets you. Bobby is here to help.");
        try {
            File file = new File("bobby.txt");
            if (file.exists()) {
                loadFile(file);
                System.out.println("Bobby remembers previous tasks.");
            } else {
                file.createNewFile();
            }
        } catch (IOException e) {
            System.out.println("An error has occurred.");
        }
        Scanner scannerObj = new Scanner(System.in);

        scanner: while (scannerObj.hasNextLine()) {
            String userInput = scannerObj.nextLine();
            String[] inputs = userInput.split(" ", 2);
            String command = inputs[0];

            switch (Commands.valueOf(command.toUpperCase())) {
            case BYE:
                System.out.println("Bobby bids you farewell.");
                break scanner;
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
                updateFile();
                System.out.println("Bobby applauds you. This task is done:\n" + t);
                break;
            case UNMARK:
                Task i = taskArray.get(Integer.parseInt(inputs[1]) - 1);
                i.unmarkAsDone();
                updateFile();
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
