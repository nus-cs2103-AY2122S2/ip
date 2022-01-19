import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {


    private static <T> boolean sizeCheck(List<T> inputList) {
        return inputList.size() < 3;
    }

    private static void run() {
        Scanner sc = new Scanner(System.in);
        boolean exitFlag = false;
        List<String> strList = new ArrayList<>();

        while(!exitFlag) {
            String input = sc.nextLine();

            switch (input) {
                case "exit": botResponse("Pleasure to be of service, see you soon!");
                             exitFlag = true;
                             break;
                case "list": botResponse(strList);
                             break;
                default:
                    if (sizeCheck(strList)) {
                        strList.add(input);
                        botResponse("added: " + input);
                    } else {
                        botResponse("List is full, sorry! :(");
                    }
            }
        }
        sc.close();
    }

    private static <T> void botResponse(List<T> inputList) {
        String divString = "    ---------------------------------------------";
        String strPadding = "      ";
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < inputList.size(); i++) {
            sb.append(String.format(strPadding + "%02d." + inputList.get(i), i + 1));
            if (i != inputList.size() - 1) { sb.append("\n"); }

        }
        System.out.println(divString);
        System.out.println(sb.toString());
        System.out.println(divString);
    }

    private static void botResponse(String resString) {
        String divString = "    ---------------------------------------------";
        String strPadding = "      ";

        System.out.println(divString);
        System.out.println(strPadding + resString);
        System.out.println(divString);
    }

    private static void greet() {
        String strPadding = "      ";
        String botName = "Baymax";
        String greeting = "Greetings, I am " + botName + ".\n" +
                strPadding + "What can I do you for?";

        botResponse(greeting);
    }

    public static void main(String[] args) {
        greet();
        run();
    }

}
