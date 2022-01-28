import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int num = 0;
        String line = "*************************************************************************";
        String lineTwo = "|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|";
        System.out.println(line);
        System.out.println("Hi! I'm Halloumi ^_^");
        System.out.println("What do you need help with today?");
        System.out.println(line);
        do {
            String text = sc.nextLine();
            if(text.equals("bye")) {
                num = 1;
                System.out.println("See you soon! Have a good day ^_^");
                System.out.println(line);
            }
            else {
                System.out.println(lineTwo);
                System.out.println(text);
                System.out.println(lineTwo);
            }
        }
        while(num == 0);
    }
}
