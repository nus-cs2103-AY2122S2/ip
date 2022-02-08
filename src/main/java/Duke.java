import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String horizontalLine = "________________________________\n";
        System.out.println(horizontalLine + "Hello! I am Duke\nWhat can I do for you?\n" + horizontalLine);

        Scanner input = new Scanner(System.in);
        String userinput = "";

        while(true) {
            userinput = input.nextLine();
            if (userinput.equals("bye")) {
                System.out.println(horizontalLine + "Bye! hope to see you again soon!\n" + horizontalLine);
                break;
            }
            System.out.println(horizontalLine + userinput + "\n" + horizontalLine);
        }
        input.close();
    }
}
