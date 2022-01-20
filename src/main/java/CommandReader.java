import java.util.Scanner;
/**
 *
 * This class serves to take in the input keyed in by the user
 * and invokes methods in response to what the user has keyed in.
 *
 */
public class CommandReader {
    public static void readCommand() {
        String input;
        Storage currentStorage = new Storage();
        Scanner user_input = new Scanner(System.in);
        input = user_input.nextLine();
        while(!input.equals(Commands.COMMAND_BYE)) {
            if(input.contains(Commands.COMMAND_LIST)) {
                input = Messages.generateList(user_input, currentStorage);
            } else if (input.contains(Commands.COMMAND_BLAH)) {
                input = Messages.sayBlah(user_input);
            } else if (input.contains(Commands.COMMAND_UNMARK)){
                int taskNumber = findDigit(input);
                currentStorage.unmarkTask(taskNumber);
                Task unmarked = currentStorage.getTask(taskNumber);
                input = Messages.sayUnmark(user_input, unmarked);
            } else if (input.contains(Commands.COMMAND_MARK)){
                int taskNumber = findDigit(input);
                currentStorage.markTask(taskNumber);
                Task marked = currentStorage.getTask(taskNumber);
                input = Messages.sayMark(user_input, marked);
            } else if (input.equals("")){
                input = user_input.nextLine();
            } else {
                Task newTask = new Task(input);
                currentStorage.addTasks(newTask);
                input = Messages.sayAdd(user_input, input);
            }
        }
        Messages.sayGoodbye();
    }
    public static int findDigit(String input) {
        char[] inputChars = input.toCharArray();
        String number = "";
        for(char ch : inputChars) {
            if(Character.isDigit(ch)){
                number += ch;
            }
        }
        int digit = Integer.parseInt(number);
        return digit;
    }
}
