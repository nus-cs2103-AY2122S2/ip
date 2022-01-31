public class Parser {

    public static String handleInput(String input) {
        if (input.contains(" ")) {
            input = input.substring(input.indexOf(" "));
        }
        return input;
    }

    public static String parse(String input) {
        String command = input.replaceAll(" .*", "");

        //commands = "bye", "list", "do", "undo", "todo", "deadline", "event"
        input = input.trim();
        if (input.equals("bye") || input.equals("list")) {
            return command;
        }

        String firstWord = input.replaceAll(" .*", "");
        if (firstWord.equals("do") || firstWord.equals("undo") || firstWord.equals("delete")) {
            input = input.replaceAll(".* ", "");
            if (input.matches("[0-9]+")) {
                return command;
            }
            System.out.println("You need to specify the task you want to "+ firstWord + " by its index :c");
            return "";
        }

        if (firstWord.equals("todo")) {
            input = input.substring(4).trim();
            if (input.equals("")) {
                System.out.println("Oops, you need to mention what the task is :c");
                return "";
            }
            return command;
        }

        if (firstWord.equals("deadline")) {
            input = input.substring(8).trim();
            if (!input.contains(" by ")) {
                System.out.println("Oops, you need to format deadline tasks as \"deadline X by Y\" :c");
                return "";
            }
            String lastWord = input.substring(input.lastIndexOf(" ") + 1);
            if (lastWord.equals("by")) {
                return "";
            }
            return command;
        }

        if (firstWord.equals("event")) {
            input = input.substring(5).trim();
            if (!input.contains(" at ")) {
                System.out.println("Oops, you need to format event tasks as \"event X at Y\" :c");
                return "";
            }
            String lastWord = input.substring(input.lastIndexOf(" ")+1);
            if (lastWord.equals("at")) {
                return "";
            }
            return command;
        }

        return "";
    }
}
