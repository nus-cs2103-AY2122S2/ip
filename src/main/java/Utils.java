import java.io.IOException;
import java.util.List;

public final class Utils {
    public static void printLogo() {
        System.out.println("____________________________________________________________");
        System.out.println("**       **  ******  ******    *****");
        System.out.println("* *     * *  *       *         *    *");
        System.out.println("*  *   *  *  ******  ******    *****");
        System.out.println("*   * *   *  *       *         *");
        System.out.println("*    *    *  ******  ******    *");
        System.out.println("Hello! I'm Meep\n" + "What can I do for you?");
        System.out.println("____________________________________________________________");
    }

    public static String[] parseUserInput(String userInput, List<Task> tasks) throws InvalidInputException {
        // catch empty input
        if (userInput.trim().length() == 0)
            throw new InvalidInputException("Empty");
        String[] arr = userInput.split(" ", 2);
        // command might be "list" or "bye"
        if (arr.length == 1) {
            if (arr[0].equals("list") || arr[0].equals("bye"))
                return arr;
            else
                throw new InvalidInputException("Invalid length 1 command. Please try 'list/bye'.");
        } else {
            if (arr[0].equals("mark") || arr[0].equals("unmark") || arr[0].equals("delete")) {
                String theRest = arr[1];
                int num;
                try {
                    num = Integer.parseInt(theRest);
                    if (num < 0 || num > tasks.size())
                        throw new InvalidInputException("Task number out of range");
                    return arr;
                } catch (NumberFormatException e) {
                    throw new InvalidInputException("Invalid task number. Please enter a valid integer.");
                }
            } else if (arr[0].equals("deadline") || arr[0].equals("event")) {
                String[] output = arr[1].split("/", 2);

                if (output.length == 1)
                    throw new InvalidInputException("Invalid " + arr[0] + " format. eg. deadline return book /by Sunday.");

                String[] date = output[1].split(" ", 2);

                // check Prepositions of Time/Date
                if (!date[0].equals("on") && !date[0].equals("by") && !date[0].equals("at") && !date[0].equals("in"))
                    throw new InvalidInputException("Invalid Prepositions of Time. Please use 'in' 'at' 'by' or 'on'.");

                String[] result = new String[4];
                result[0] = arr[0];
                result[1] = output[0];
                result[2] = date[0];
                result[3] = date[1];

                return result;
            } else if (arr[0].equals("todo")) {
                return arr;
            } else {
                throw new InvalidInputException("Invalid command. Please try 'mark/unmark/event/deadline/todo'. ");
            }
        }
    }


    public static void printTaskList(List<Task> taskList) {
        int i = 1;
        for (Task task : taskList) {
            System.out.println("     " + i + ".  " + task.toString());
            i++;
        }
    }
}