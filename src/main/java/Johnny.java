import java.util.Scanner;
public class Johnny {
    public static void main(String[] args) {
        System.out.println("Hello! I'm Johnny \n" + "What can I do for you?");
        Scanner sc = new Scanner(System.in);
        String input;
        String[] list = new String[100];
        int count = 0;
        while(true) {
            input = sc.nextLine();
            if(input.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            }
            else if(input.equals("list")) {
                for(int i = 0; i < count; i++) {
                    System.out.print(i + 1);
                    System.out.println(". " + list[i]);
                }
            }
            else {
                System.out.println("added: " + input);
                list[count] = input;
                count++;
            }
        }
    }
}
