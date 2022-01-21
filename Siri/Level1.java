import java.util.Scanner;

public class Level1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String line = "    __________________________________________________\n";
        System.out.println(line + "     Hi there. I'm Siri \n" + "     How may I help you? \n" + line);

        while(true) {
            String word = sc.next();
            if (word.equals("bye")) { //print goodbye text
                System.out.println(line + "     Goodbye.\n" + line);
                break;
            } else { //print whatever user inputed
                System.out.println(line + "     " + word + "\n" + line);
            }
        }
    }
}