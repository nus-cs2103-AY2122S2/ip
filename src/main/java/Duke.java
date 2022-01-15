import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Howdy and welcome to\n" + logo);

        String inputData;

        while(true) {
            Scanner scan = new Scanner(System.in);
            inputData = scan.nextLine();
            if(inputData.equals("bye")) {
                System.out.println("~BYE!~ Come back to Duke anytime");
                break;
            }
            System.out.println(inputData);
        }
    }
}
