import javax.swing.plaf.basic.BasicSplitPaneUI;
import java.util.*;

public class Duke {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println(chatBox("Hello! I'm Duke\n    What can I do for you?"));

        String[] texts = new String[100];
        String tab = "    ";

        int items = 0;

        String command = sc.nextLine();

        while (!command.equals("bye")) {
            if (!command.equals("list")) {
                items += 1;
                texts[items] = command;
                System.out.println(chatBox("added: " + command));
                System.out.println();

            } else {
                String lists = "";
                for (int i = 1; i <= items; i++) {
                    if (i != 1) {
                        lists += "\n" + tab;
                    }
                    lists += String.format("%d. %s", i, texts[i]);

                }

                System.out.println(chatBox(lists));
            }


            command = sc.nextLine();
        }

        System.out.println(chatBox("Bye. Hope to see you again soon!"));

        sc.close();
    }

    private static String chatBox(String command){
        String tab = "    ";
        String horizontalLines = tab + "___________________________________";

        return horizontalLines + "\n" + tab + command + "\n" + horizontalLines;

    }
}



