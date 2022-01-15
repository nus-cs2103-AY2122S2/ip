import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Howdy and welcome to\n" + logo + "\n" + "Feel free to tell duke any tasks you'd like!");

        String inputData;
        ArrayList<String> list = new ArrayList<>();

        while(true) {
            Scanner scan = new Scanner(System.in);
            inputData = scan.nextLine();
            if(inputData.equals("bye")) {
                System.out.println("~BYE!~ Come back to Duke anytime");
                break;
            }
            else if(inputData.equals("list")){
                for(int i = 1; i <= list.size(); i++){
                    String text = list.get(i-1);
                    System.out.println(i + ": " + text);
                }
            }else{
                list.add(inputData);
                System.out.println("added: " + inputData);
            }
        }
    }
}
