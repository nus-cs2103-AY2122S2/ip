import java.util.*;

public class Duke {

    // Included error handling for invalid commands, large list, and invalid list index.
    private static void run() {
        Scanner sc = new Scanner(System.in);
        boolean exitFlag = false;
        List<Task> taskList = new ArrayList<>();

        while(!exitFlag) {
            String input = sc.nextLine();
            String[] inputArr = input.split(" ");

            try {
                Command inputCmd = Command.getByName(inputArr[0]);
                switch (inputCmd) {
                    case EXIT:
                        exitFlag = inputCmd.exitResponse();
                        break;
                    case LIST:
                        inputCmd.listResponse(taskList);
                        break;
                    case MARK:
                    case UNMARK:
                        inputCmd.toggleMarkResponse(taskList, inputArr);
                        break;
                    case TODO:
                    case EVENT:
                    case DEADLINE:
                        inputCmd.subtaskResponse(taskList, input);
                        break;
                    case REMOVE:
                    case DELETE:
                        inputCmd.deleteResponse(taskList, inputArr);
                        break;
                    case CLEAR:
                        inputCmd.clearResponse(taskList);
                        break;
                    default:
                        throw new DukeException("Invalid command!");
                }
            } catch (DukeException e) {
                e.exceptionResponse();
            }


        }
        sc.close();
    }

    private static void greet() {
        String strPadding = "      ";
        String botName = "Baymax";
        String greeting = "Greetings, I am " + botName + ".\n" +
                strPadding + "What can I do you for?";

        Command.GREET.genericResponse(greeting);
    }

    public static void main(String[] args) {
        greet();
        run();
    }

}
