import javax.sound.midi.Soundbank;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        Scanner scanner = new Scanner(System.in);
        Control control = new Control();
        while (true) {
            String command = scanner.nextLine();
            String commandApp = command + " ";
            String[] commandArr = commandApp.split(" ");
            String firstWord = commandArr[0];
            if (firstWord.equals("bye")) {
                control.bye();
                break;
            }else if (firstWord.equals("list")) {
                control.list();
            } else if (firstWord.equals("mark") || firstWord.equals("unmark")) {
                control.taskCheck(command);
            } else if (firstWord.equals("todo")) {
                control.todo(command);
            } else if (firstWord.equals("deadline")) {
                control.deadline(command);
            } else if (firstWord.equals("event")) {
                control.event(command);
            } else {
                System.out.println(firstWord + " is a invalid Task");
            }
            System.out.println("________________________________");
        }
    }
//    public static void main(String[] args) {
//        String logo = " ____        _        \n"
//                + "|  _ \\ _   _| | _____ \n"
//                + "| | | | | | | |/ / _ \\\n"
//                + "| |_| | |_| |   <  __/\n"
//                + "|____/ \\__,_|_|\\_\\___|\n";
//        System.out.println("Hello from\n" + logo);
//    }
}
