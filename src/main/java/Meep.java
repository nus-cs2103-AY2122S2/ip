import java.util.Scanner;

public class Meep {
    public static void main(String[] args) {
        Utils.printLogo();
        String userInput = "In", outputMsg = "Out";
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.print("You: ");
            userInput = sc.nextLine();
            if (userInput.equals("bye")) {
                System.out.println("Meep: Bye. Hope to see you again soon!");
                break;
            } else {
                outputMsg = userInput;
                System.out.println("Meep:"+outputMsg);
            }
        }
        sc.close();
    }
}
