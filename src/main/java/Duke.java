import exceptions.DukeExceptions;
import exceptions.DukeInvalidInput;
import exceptions.DukeInvalidTodo;

import java.time.LocalDate;
import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void lineOne() {
        System.out.println("*************************************************************************");
    }

    public static void lineTwo() {
        System.out.println("|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|");
    }

    public static String makeDesc(String[] text, int len) {
        String newText = "";
        for (int i = 1; i < len; i++) {
            newText = newText + text[i] + " ";
        }
        return newText.trim();
    }

    public static void main(String[] args) throws DukeExceptions {

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
            String fullDesc = makeDesc(textSplit, textSplit.length);
            try {
                switch (textSplit[0]) {
                    case "bye":
                        num = 1;
                        System.out.println("See you soon! Have a good day ^_^");
                        lineOne();
                        break;
                    case "list":
                        for (int i = 0; i < lists.size(); i++) {
                            System.out.println(i + 1 + ". " + lists.get(i));
                        }
                        break;
                    case "mark":
                        lineOne();
                        System.out.println("Good Job! ^_^");
                        System.out.println("Task number " + textSplit[1] + " has been marked as done!");
                        int tNum = Integer.parseInt(textSplit[1]);
                        lists.get(tNum - 1).done();
                        System.out.println(lists.get(tNum - 1));
                        lineOne();
                        break;
                    case "unmark":
                        lineOne();
                        System.out.println("I've unmarked task number " + textSplit[1]);
                        System.out.println("Complete it soon! ^_^");
                        int tNo = Integer.parseInt(textSplit[1]);
                        lists.get(tNo - 1).undo();
                        System.out.println(lists.get(tNo - 1));
                        lineOne();
                        break;
                    case "delete" :
                        lineOne();
                        System.out.println("Noted. I've removed this task:");
                        //System.out.println("Complete it soon! ^_^");
                        int t2No = Integer.parseInt(textSplit[1]);
                        System.out.println(lists.get(t2No - 1));
                        lists.remove(t2No - 1);

                        lineOne();
                        break;
                    case "todo":
                        try {
                            if(fullDesc.equals(" ") || fullDesc.equals("")) {
                                throw new DukeInvalidTodo();
                            }
                        }
                        catch(DukeInvalidTodo e) {
                            System.err.println(e.getMessage());
                            continue;
                        }
                        System.out.println("New task added:");
                        Task t = new ToDo(fullDesc);
                        lists.add(t);
                        System.out.println(t);
                        break;
                    case "event":
                        System.out.println("New task added:");
                        Task t1 = new Event(fullDesc, textSplitOne[1]);
                        lists.add(t1);
                        System.out.println(t1);
                        break;
                    case "deadline":
                        System.out.println("New task added:");
                        String[] date = textSplitOne[1].split(" ");
                        Task t2 = new Deadline(fullDesc, LocalDate.parse(date[1]));
                        lists.add(t2);
                        System.out.println(t2);
                        break;
                    default:
                        throw new DukeInvalidInput();

                }
            } catch (DukeInvalidInput e) {
                System.err.println(e.getMessage());
            }
        }
            while (num == 0) ;

    }
}
