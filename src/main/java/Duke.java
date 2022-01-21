import java.util.*;

/**
 * The type Duke.
 */
public class Duke {

    private static void run() {
        Scanner sc = new Scanner(System.in);
        boolean exitFlag = false;
        List<Task> taskList = new ArrayList<>();

        while(!exitFlag) {
            String[] input = sc.nextLine().split(" ", 2);

            try {
                Command inputCmd = Command.getByName(input[0]);
                switch (inputCmd) {
                    case EXIT:
                        exitFlag = inputCmd.exitResponse();
                        break;
                    case LIST:
                        inputCmd.listResponse(taskList);
                        break;
                    case MARK:
                    case UNMARK:
                        inputCmd.toggleMarkResponse(taskList, input);
                        break;
                    case TODO:
                    case EVENT:
                    case DEADLINE:
                        inputCmd.subtaskResponse(taskList, input[1]);
                        break;
                    case REMOVE:
                    case DELETE:
                        inputCmd.deleteResponse(taskList, input);
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

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        greet();
        run();
    }

}
