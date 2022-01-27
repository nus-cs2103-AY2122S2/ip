package johnny;

import java.util.ArrayList;

public class Parser {

    private String input;

    public static String TERMINATE = "TERMINATE";
    public static String PRINT_LIST = "PRINT_LIST";
    public static String MARK = "MARK";
    public static String UNMARK = "UNMARK";
    public static String DELETE = "DELETE";
    public static String ADD_TODO = "ADD_TODO";
    public static String ADD_DEADLINE = "ADD_DEADLINE";
    public static String ADD_EVENT = "ADD_EVENT";
    public static String FIND_EVENT = "FIND_EVENT";




    public Parser(String userInput) {
        this.input = userInput;
    }

    private static boolean isNumeric(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch(NumberFormatException e){
            return false;
        }
    }

    public ArrayList<String> parse() throws EmptyDescriptionException, NoDateException, InvalidArgumentsException {
        String[] tags = input.split(" ", 2);
        ArrayList<String> result = new ArrayList<>();

        if(input.equals("bye")) {
            result.add(TERMINATE);
        } else if(input.equals("list")) {
            result.add(PRINT_LIST);
        } else if(input.length() >= 5 && input.substring(0, 5).equals("mark ") && isNumeric(input.substring(5))) {
            result.add(MARK);
            result.add(input.substring(5));
        } else if(input.length() >= 7 && input.substring(0, 7).equals("unmark ") && isNumeric(input.substring(7))) {
            result.add(UNMARK);
            result.add(input.substring(7));
        } else if(input.length() >= 7 && input.substring(0, 7).equals("delete ") && isNumeric(input.substring(7))) {
            result.add(DELETE);
            result.add(input.substring(7));
        } else if(tags.length == 2 && tags[0].equals("find") && tags[1] instanceof String) {
            result.add(FIND_EVENT);
            result.add(tags[1]);
        } else if(tags[0].equals("todo")) {
            if(tags.length == 1 || tags[1].equals("")) {
                throw new EmptyDescriptionException();
            }

            String content = tags[1];
            if(content.equals("")) {
                throw new EmptyDescriptionException();
            }
            result.add(ADD_TODO);
            result.add(content);
        }
        else if(tags[0].equals("deadline")) {
            if(tags.length == 1 || tags[1].equals("")) {
                throw new EmptyDescriptionException();
            }

            if(!tags[1].contains("/")) {
                throw new NoDateException();
            }

            String content = tags[1];
            String[] details = content.split("/", 2);

            result.add(ADD_DEADLINE);
            result.add(details[0]);
            result.add(details[1]);
        }
        else if(tags[0].equals("event")) {
            if(tags.length == 1 || tags[1].equals("")) {
                throw new EmptyDescriptionException();
            }

            if(!tags[1].contains("/")) {
                throw new NoDateException();
            }

            String content = tags[1];
            String[] details = content.split("/", 2);

            result.add(ADD_EVENT);
            result.add(details[0]);
            result.add(details[1]);
        }
        else {
            throw new InvalidArgumentsException(input);
        }
        return result;
    }
}
