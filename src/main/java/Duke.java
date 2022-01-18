import java.util.Scanner;
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
        
        String byeMessage = "Farewell Sir. Have a wonderful day.";
        
        Scanner scanner = new Scanner(System.in);

        while (true) {
            String inputText = scanner.nextLine();
            if (inputText.equals("bye")) {
                System.out.println(straightLine + "\n" + byeMessage + "\n" + straightLine);
                scanner.close();
                break;
            } else {
                System.out.println(straightLine + "\n" + inputText + "\n" + straightLine);
            }
        }
    }
}
