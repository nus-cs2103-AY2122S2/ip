import java.util.*;
public class Duke {
    static Scanner sc = new Scanner(System.in);
    static String line = "\n---------------------\n";
    public static void main(String[] args) {
        String logo = " ____         _   _     \n"
                    + "|  _ \\       | | | |\n"
                    + "| |_| |      | |-| |\n"
                    + "| |_| |  _   | |-| |\n"
                    + "|____/  |_|  |_| |_|\n";
        System.out.println("Hello, I am B.H. How can I help you?\n" + logo + line);

        BH bh = new BH();

        while(true) {
            String input = sc.nextLine();
            String[] inputArray = input.split(" ");
            if (inputArray[0].equals("bye")) {
                System.out.println(line + "GoodBye! Thanks for using B.H!" + line);
                break;
            } else if (inputArray[0].equals("list")) {
                System.out.println(bh.getList());
            } else if (inputArray[0].equals("mark") && inputArray.length > 1) {
                int index = Integer.parseInt(inputArray[1]) - 1;
                if (index < bh.getListSize()) {
                    System.out.println("Well done! \n" + bh.mark(index));
                } else {
                    System.out.println("Index out of range");
                }
            } else if (inputArray[0].equals("unmark") && inputArray.length > 1) {
                int index = Integer.parseInt(inputArray[1]) - 1;
                System.out.println("Oh no! \n" + bh.unmark(index));
            } else {bh.addToList(input);
                    System.out.println(line + "added: " + input + line);
            }
        }
    }
}
