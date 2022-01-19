import java.util.*;

public class DukeException extends Exception{
    String[] validInputs = new String[] {"deadline", "todo", "event", };
    List<String> inputList = new ArrayList<>(Arrays.asList(validInputs));
    String[] validInputs2 = new String[] {"mark", "unmark", "delete"};
    List<String> inputList2 = new ArrayList<>(Arrays.asList(validInputs2));
    String[] validInputs3 = new String[] {"list", "bye"};
    List<String> inputList3 = new ArrayList<>(Arrays.asList(validInputs3));

    public DukeException(String message) {
        super(message);
    }

    public DukeException() {}

    public void invalidChecker (String[] tempList, int tasks) throws DukeException {
        if (inputList.contains(tempList[0])) {
            if (tempList.length == 1) {
                throw new DukeException("☹ OOPS!!! The description of a task cannot be empty.");
            } else {
                if (tempList[0].equals("deadline")) {
                    String[] restOfPara  = tempList[1].split("/by ", 2);
                    if (restOfPara[0].equals("")) {
                        throw new DukeException(" ☹ OOPS!!! The description of a deadline cannot be empty.");
                    } else if (restOfPara.length == 1) {
                        throw new DukeException("☹ OOPS!!! Please enter a deadline for the task using /by.");
                    }
                } else if ((tempList[0].equals("event"))) {
                    String[] restOfPara  = tempList[1].split("/at ", 2);
                    if (restOfPara[0].equals("")) {
                        throw new DukeException(" ☹ OOPS!!! The description of a event cannot be empty.");
                    } else if (restOfPara.length == 1) {
                        throw new DukeException("☹ OOPS!!! Please enter a timeframe for the task using /at.");
                    }
                }
            }
        } else if (inputList2.contains(tempList[0])) {
            int taskNum = Integer.parseInt(tempList[1]);
            if (tempList.length == 1) {
                throw new DukeException("☹ OOPS!!! Please enter a task number.");
            } else if (!(taskNum <= tasks && taskNum > 0)) {
                throw new DukeException("☹ OOPS!!! Task number does not exist.");
            }
        } else if (!inputList3.contains(tempList[0])) {
            throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }
}
