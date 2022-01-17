import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        String line = "_________________________________";
        String indentation = "     ";

        System.out.println(indentation + line + "\n" + indentation +
                "Hello! I'm PikaBot " + "\n" + indentation +
                "What can I do for you? \n" + indentation +
                line);

        String input = sc.nextLine();

        while (!input.equals("bye")) {
            System.out.println(indentation + line + "\n" + indentation +
                    input + "\n" + indentation + line);
            input = sc.nextLine();
        }

        System.out.println(indentation + line + "\n" + indentation +
                "Bye. Hope to see you again!" + "\n" + indentation +
                line);

        sc.close();
    }
}
