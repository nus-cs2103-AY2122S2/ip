import java.io.IOException;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class Duke {
    private final static String fromDuke = "From Duke_two: \n\t";
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        Scanner scanner = new Scanner(System.in);
        Control control = new Control();
        String firstWord= "";
        while (true) {
            try {
                String command = scanner.nextLine();
                System.out.print(fromDuke);
                String[] commandArr = command.split(" ");
                firstWord = commandArr[0];
                if (!(firstWord.equals("bye") || firstWord.equals("list") || firstWord.equals("mark") || firstWord.equals("unmark") ||
                    firstWord.equals("todo") || firstWord.equals("deadline") || firstWord.equals("event") || firstWord.equals("delete"))) {
                    throw new InvalidCommandException();

                // One word commands
                } else if (firstWord.equals("bye")) {
                    control.bye();
                    break;
                } else if (firstWord.equals("list")) {
                    control.list();

                //Multiple word commands
                } else if (commandArr.length < 2) {
                    throw new InvalidDescriptionException();
                } else if (firstWord.equals("mark") || firstWord.equals("unmark")) {
                    control.taskCheck(command);
                } else if (firstWord.equals("todo")) {
                    control.todo(command);
                } else if (firstWord.equals("deadline")) {
                    control.deadline(command);
                } else if (firstWord.equals("event")) {
                    control.event(command);
                } else if (firstWord.equals("delete")) {
                    control.delete(command);
                }
            } catch (InvalidCommandException e) {
                System.out.println("'" + firstWord + "' is an invalid command! Please try again!");
            } catch (InvalidDescriptionException e) {
                System.out.println("There cannot be an empty description of your task! Please try again! ");
            } catch (DateTimeParseException e) {
                System.out.println("An invalid time has been added. Please use a YYYY-MM-DD HH:MM format.");
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("That is an invalid task. Please try again!");

            } finally {
                System.out.println("________________________________");
            }
        }
    }
}
