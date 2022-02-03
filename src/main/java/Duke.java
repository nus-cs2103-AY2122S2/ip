import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void lineOne() {
        System.out.println("*************************************************************************");
    }

    public static void lineTwo() {
        System.out.println("|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|");
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int num = 0;
        int counter = 0;
        ArrayList<Task> lists = new ArrayList<Task>();
        lineOne();
        System.out.println("Hi! I'm Halloumi ^_^");
        System.out.println("What do you need help with today?");
        lineOne();
        do {
            String text = sc.nextLine();
            String[] textSplitOne = text.split("/"); //by and at
            String[] textSplit = textSplitOne[0].split(" ");
            switch(textSplit[0]) {
                case "bye": num = 1;
                            System.out.println("See you soon! Have a good day ^_^");
                            lineOne();
                            break;
                case "list": for(int i = 0; i < counter; i++) {
                                 System.out.println(i+1 + ". " + lists.get(i));
                             }
                             break;
                case "mark": lineOne();
                             System.out.println("Good Job! ^_^");
                             System.out.println("Task number " + textSplit[1] + " has been marked as done!");
                             int tNum = Integer.parseInt(textSplit[1]);
                             lists.get(tNum - 1).done();
                             System.out.println(lists.get(tNum - 1));
                             lineOne();
                             break;
                case "unmark": lineOne();
                               System.out.println("I've unmarked task number " + textSplit[1]);
                               System.out.println("Complete it soon! ^_^");
                               int tNo = Integer.parseInt(textSplit[1]);
                               lists.get(tNo - 1).undo();
                               System.out.println(lists.get(tNo- 1));
                               lineOne();
                               break;
                default: lineTwo();
                         System.out.println("New task added:");
                         String newText = "";
                         for(int i = 1; i < textSplit.length; i++) {
                             newText = newText + textSplit[i] + " ";
                         }
                         if(textSplit[0].equals("todo")) {
                             Task t = new ToDo(newText);
                             lists.add(t);
                             System.out.println(t);
                         }
                         else if(textSplit[0].equals("event")) {
                             Task t = new Event(newText, textSplitOne[1]);
                             lists.add(t);
                             System.out.println(t);
                         }
                         else {
                             Task t = new Deadline(newText, textSplitOne[1]);
                             lists.add(t);
                             System.out.println(t);
                         }
                         counter++;
                         System.out.println("You have " + counter + " tasks in the list now! ^_^");
                         lineTwo();
                         break;
            }
        }
        while(num == 0);
    }
}
