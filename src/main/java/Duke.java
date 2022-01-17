import java.util.Arrays;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Printer p = new Printer();
        Scanner in = new Scanner(System.in);
        InputParser ip = new InputParser();
        ArrayList<String> arr = new ArrayList<>();

        String logo = " ____        _        \n"    
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        p.print("Hello! I'm Duke," , "What can I do for you?");

        String userInput = in.nextLine();
        while(ip.run(userInput, p, arr) != -1)
            userInput = in.nextLine();

    }



}



