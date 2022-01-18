import java.util.Scanner;
import java.util.ArrayList;
public class Duke {
    public static void main(String[] args) {
        // String logo = " ____        _        \n"
        //         + "|  _ \\ _   _| | _____ \n"
        //         + "| | | | | | | |/ / _ \\\n"
        //         + "| |_| | |_| |   <  __/\n"
        //         + "|____/ \\__,_|_|\\_\\___|\n";
        // System.out.println("Hello from\n" + logo);

        // print introduction message
        String straightLine = "____________________________________________________________";
        String introductionMessage = "Good day Sir. My name is Dook. \n How may I be of assistance?";
        System.out.println(straightLine + "\n" + introductionMessage + "\n" + straightLine);
        
        Scanner scanner = new Scanner(System.in);
        ArrayList<String> userList = new ArrayList<String>();

        while (true) {
            String inputText = scanner.nextLine();
            if (inputText.equals("bye")) { // bye command
                String byeMessage = "Farewell Sir. Have a wonderful day.";
                System.out.println(straightLine + "\n" + byeMessage + "\n" + straightLine);
                scanner.close();
                break;
            
            } else if (inputText.equals("list")) { // list command
                Integer counter = 1;
                String reply = "";
                for (String item : userList) {
                    reply += "\n" + counter.toString() + ". " + item; // note: new line is at the start
                    counter++;
                }
                System.out.println(straightLine + reply + "\n" + straightLine);

            } else { // other text input
                userList.add(inputText);
                String reply = "added: " + inputText;
                System.out.println(straightLine + "\n" + reply + "\n" + straightLine);
            }
        }
    }
}
