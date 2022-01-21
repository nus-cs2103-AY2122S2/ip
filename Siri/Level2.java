import java.util.Scanner;
import java.util.ArrayList;

public class Level2 {
    public static void main(String[] args) {
        Scanner sc = new
                Scanner(System.in);
        ArrayList<String> arrList = new ArrayList<String>();
        String line = "    __________________________________________________\n";
        System.out.println(line + "     Hi there. I'm Siri \n" + "     How may I help you? \n" + line);

        while(true) {
            String word = sc.nextLine();
            if (word.equals("bye")) { //print goodbye text
                System.out.println(line + "     Goodbye.\n" + line);
                break;
            } else if (word.equals("list")) { //print all elements in a list with index in front
                System.out.print(line);
                int s = arrList.size();
                for(int i = 0; i < s; i++) {
                    System.out.println("     " + (i + 1) + ". " + arrList.get(i));
                }
                System.out.println(line);
            } else { //print whatever user inputed with "added" infront
                arrList.add(word);
                System.out.println(line + "     added: " + word + "\n" + line);
            }
        }
    }
}
