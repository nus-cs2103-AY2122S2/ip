import java.util.Scanner;
import java.util.ArrayList;
public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        Scanner sc = new Scanner(System.in);
        String input = "";
        ArrayList<String> list = new ArrayList<String>();

        myPrint("Hello! I'm Duke\n    What can I do for you?");

        input = sc.nextLine();

        while(!input.equals("bye")) {
            if (input.equals("list")) {
                String tempStr = "";

                for (int i = 1; i <= list.size(); i++) {
                    if (i != 1) {
                        tempStr += "\n    ";
                    }
                    tempStr += i + ". " + list.get(i-1);
                }
                myPrint(tempStr);

            } else {
                list.add(input);
                myPrint("added: " + input);
            }

            input = sc.nextLine();

        }

        myPrint("Bye. Hope to see you again soon!");

        sc.close();

    }

    public static void myPrint(String toPrint){
        System.out.println("    ________________________________________________________________");
        System.out.println("    " + toPrint);
        System.out.println("    ________________________________________________________________\n");

    }

}
