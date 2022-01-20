import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {
        Scanner myScanner = new Scanner(System.in);
        System.out.println("Hello from\n" + Response.LOGO);
        Response.wrapPrint(Response.WELCOME);
        boolean over = false;
        while (!over) {
            String command = myScanner.nextLine();
            if (command.equals("bye")) {
                Response.wrapPrint(Response.GOODBYE);
                over = true;
                continue;
            }
            Response.wrapPrint(command);
        }
    }
}
