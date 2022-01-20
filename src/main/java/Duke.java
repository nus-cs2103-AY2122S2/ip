import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    private static final String GREETING_MESSAGE = "Hey, Aeromon here! Fancy a cup of tea?";
    private static final String FAREWELL_MESSAGE = "Buai Buai! Ciao for now!";
    private static final String LIST_MESSAGE = "Konnichiwassup! Look at how much work you have to do!";
    private static final String ADD_MESSAGE = "Nicely! I've added for you: \n";
    private static final String MARK_MESSAGE = "Naisu! You've completed: \n";
    private static final String UNMARK_MESSAGE = "OI! What happened to completing: \n";
    private static final String DELETE_MESSAGE = "Okies, one less thing on the list! \n";
    private static final List<Task> toDoList = new ArrayList<>();

    /**
     * Private method that checks if the input is valid and returns the array which
     * contains information for further evaluations.
     * @param arr the array to checks for invalid inputs
     * @param command the command of the input
     * @return the array which contains information
     * For tasks, toReturn[0] contains the task name, toReturn[1] contains the time (if any).
     * For mark and unmark, toReturn[0] contains the task number in String format.
     * @throws DukeException the exception specific to Duke
     */
    private static String[] validate(String[] arr, String command, int size) throws DukeException {
        String test;
        String[] temp;
        String[] toReturn = new String[2];
        try {
            switch (command) {
                case "bye":
                case "list":
                    // do nothing as it doesn't require more information
                    break;

                case "todo":
                    toReturn[0] = arr[1];
                    break;

                case "deadline":
                    test = arr[1];
                    toReturn = test.split("/by ");
                    test = toReturn[1];
                    break;

                case "event":
                    test = arr[1];
                    toReturn = test.split("/at ");
                    test = toReturn[1];
                    break;

                case "mark":
                case "unmark":
                case "delete":
                    toReturn[0] = arr[1];
                    int index = Integer.parseInt(arr[1]);
                    if (index <= 0 || index > size) {
                        throw new DukeException("Nani is that task number sir?");
                    }
                    break;
            }
            return toReturn;
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException("I need more information than that aye >:(");
        } catch (NumberFormatException e) {
            throw new DukeException("numbers... Numbers... NUMBERS! Tell me WHICH task!");
        }
    }

    public static void main(String[] args) {
        System.out.println(GREETING_MESSAGE);

        Scanner sc = new Scanner(System.in);

        execute:
        while (sc.hasNextLine()) {
            try {
                String input = sc.nextLine();
                String[] arr = input.split(" ", 2);
                String command = arr[0];
                String[] info = validate(arr, command, toDoList.size());

                switch (command) {
                    case "bye":
                        System.out.println(FAREWELL_MESSAGE);
                        break execute;

                    case "list":
                        System.out.println(LIST_MESSAGE);
                        int count = 1;
                        for (Task item : toDoList) {
                            System.out.println(String.format("%d: %s", count, item));
                            count++;
                        }
                        break;

                    case "mark": {
                        int index = Integer.parseInt(info[0]) - 1;
                        Task temp = toDoList.get(index);
                        temp.markAsDone();
                        System.out.println(MARK_MESSAGE + temp);
                        break;
                    }

                    case "unmark": {
                        int index = Integer.parseInt(info[0]) - 1;
                        Task temp = toDoList.get(index);
                        temp.markAsNotDone();
                        System.out.println(UNMARK_MESSAGE + temp);
                        break;
                    }

                    case "todo": {
                        Task task = new ToDo(info[0]);
                        toDoList.add(task);
                        System.out.println(ADD_MESSAGE + task);
                        System.out.println(String.format("Aiyo, now you have %d tasks in the list, jiayous!", toDoList.size()));
                        break;
                    }

                    case "deadline": {
                        Task task = new Deadline(info[0], info[1]);
                        toDoList.add(task);
                        System.out.println(ADD_MESSAGE + task);
                        System.out.println(String.format("Aiyo, now you have %d tasks in the list, jiayous!", toDoList.size()));
                        break;
                    }

                    case "event": {
                        Task task = new Event(info[0], info[1]);
                        toDoList.add(task);
                        System.out.println(ADD_MESSAGE + task);
                        System.out.println(String.format("Aiyo, now you have %d tasks in the list, jiayous!", toDoList.size()));
                        break;
                    }

                    case "delete": {
                        int index = Integer.parseInt(info[0]) - 1;
                        Task temp = toDoList.get(index);
                        toDoList.remove(index);
                        System.out.println(DELETE_MESSAGE + temp);
                        System.out.println(String.format("Less burden for you since you only have %d tasks to do *smiles*", toDoList.size()));
                        break;
                    }

                    default:
                        throw new DukeException("Nani? Me no understand what you say .-.");
                }
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
