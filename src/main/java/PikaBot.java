import java.util.Scanner;

public class PikaBot {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        String[] inputArr = new String[100];

        String line = "_________________________________";
        String indentation = "     ";

        System.out.println(indentation + line + "\n" + indentation +
                "Hello! I'm PikaBot " + "\n" + indentation +
                "What can I do for you? \n" + indentation +
                line);

        String input = sc.nextLine();
        int count = 0;

        while (!input.equals("bye")) {

            if (input.equals("list")) {
                 int item = 0;
                 System.out.println(indentation + line);
                 while (item < count ) {
                    System.out.println(indentation + inputArr[item]);
                    item++;
                }
                 System.out.println(indentation + line);
                input = sc.nextLine();
            } else {
                System.out.println(indentation + line + "\n" + indentation + "added: " +
                        input + "\n" + indentation + line);
                inputArr[count] = count + 1 + ". " + input;
                input = sc.nextLine();
                count++;
            }
        }

        System.out.println(indentation + line + "\n" + indentation +
                "Bye. Hope to see you again!" + "\n" + indentation +
                line);

        sc.close();
    }
}
