import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Mike mike = new Mike();
        mike.start();
        Scanner sc = new Scanner(System.in);

        while (true) {
            mike.printNextCommandInstruction();
            String inputString = sc.nextLine();
            String trimmedInputString = inputString.trim();
            if (trimmedInputString.equals("bye")) {
                break;
            } else if (trimmedInputString.isEmpty()) {
                mike.printNoCharactersMessage();
            } else {
                mike.processInput(trimmedInputString);
                mike.saveToStoredList(); //store the current list in hard drive
            }
        }
        sc.close();
        mike.printGoodbyeMessage();
    }
}