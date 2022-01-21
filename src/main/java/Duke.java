import java.util.*;

public class Duke {

    private static void run() {
        Scanner sc = new Scanner(System.in);
        boolean exitFlag = false;
        List<Task> taskList = new ArrayList<>();

        while(!exitFlag) {
            try {
                String[] input = sc.nextLine().split(" ", 2);
                Command inputCmd = Command.getByName(input[0]);     //input[1] = params
                switch (inputCmd) {
                    case EXIT:
                        exitFlag = inputCmd.exitResponse();
                        break;
                    case LIST:
                        inputCmd.listResponse(taskList);
                        break;
                    case MARK:
                    case UNMARK:
                        if (input.length == 2) {
                            inputCmd.toggleMarkResponse(taskList, input[0], input[1]);
                            break;
                        } else {
                            throw new DukeException("Invalid number of inputs!");
                        }
                    case TODO:
                    case EVENT:
                    case DEADLINE:
                        if (input.length == 2) {
                            inputCmd.subtaskResponse(taskList, input[1]);
                            break;
                        } else {
                            throw new DukeException("Task description cannot be empty!");
                        }
                    case REMOVE:
                    case DELETE:
                        if (input.length == 2) {
                            inputCmd.deleteResponse(taskList, input[1]);
                            break;
                        } else {
                            throw new DukeException("Invalid number of inputs!");
                        }
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
