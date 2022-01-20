
import java.util.Scanner;
import java.util.regex.PatternSyntaxException;

public class Duke {
    public static void main(String[] args) throws InvalidDescriptionException, InvalidCommandException {

        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        Scanner scanner = new Scanner(System.in);
        Control control = new Control();
        while (true) {
            try {
                String command = scanner.nextLine();
//                String commandApp = command + " ";
                String[] commandArr = command.split(" ");
                String firstWord = commandArr[0];
//                System.out.println("num of strings in commandArr: " + commandArr.length);
                if (!(firstWord.equals("bye") || firstWord.equals("list") || firstWord.equals("mark") || firstWord.equals("unmark") ||
                    firstWord.equals("todo") || firstWord.equals("deadline") || firstWord.equals("event"))) {
                    throw new InvalidCommandException();
                } else if (commandArr.length < 2) {
                    throw new InvalidDescriptionException();
                } else if (firstWord.equals("bye")) {
                    control.bye();
                    break;
                } else if (firstWord.equals("list")) {
                    control.list();
                } else if (firstWord.equals("mark") || firstWord.equals("unmark")) {
                    control.taskCheck(command);
                } else if (firstWord.equals("todo")) {
                    control.todo(command);
                } else if (firstWord.equals("deadline")) {
                    control.deadline(command);
                } else if (firstWord.equals("event")) {
                    control.event(command);
                }
            } catch (InvalidCommandException e) {
                System.out.println("That is a invalid command! Please try again!");
            } catch (InvalidDescriptionException e) {
                System.out.println("There cannot be an empty description of your task! Please try again! ");
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("That is an invalid task. Please try again!");
            } finally {
                System.out.println("________________________________");
            }

        }
    }

}
